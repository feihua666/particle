package com.particle.global.openapi.api;

import brave.Tracer;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.Assert;
import com.particle.global.openapi.api.limitrule.GlobalOpenapiRequestLimitService;
import com.particle.global.openapi.api.limitrule.ratelimit.GlobalOpenapiRateLimitService;
import com.particle.global.openapi.collect.OpenapiCollectTool;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.data.*;
import com.particle.global.openapi.enums.LimitRuleType;
import com.particle.global.openapi.exception.ErrorCodeOpenapiEnum;
import com.particle.global.security.security.PermissionService;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.web.filter.RequestResponseLogFilter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.TransientSecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
/**
 * <p>
 * 开放接口帮助类
 * </p>
 *
 * @author yangwei
 * @since 2023-08-03 11:48
 */
@Slf4j
public class OpenapiHelper {

	@Autowired
	private Tracer tracer;

	@Autowired(required = false)
	private GlobalOpenapiCollectPersistentService globalOpenapiCollectPersistentService;

	/**
	 * 限流支持
	 */
	@Autowired(required = false)
	private GlobalOpenapiRateLimitService globalOpenapiRateLimitService;
	@Autowired
	private GlobalOpenapiRequestLimitService globalOpenapiRequestLimitService;

	/**
	 * 开始
	 * @param openapiContext
	 */
	public void openApiStart(OpenapiContext openapiContext) {
		OpenapiCollectTool.startOpenApi();
		OpenapiCollectTool.putContext(openapiContext);
	}
	/**
	 * 开放接口请求开始处理，入口
	 * @param request
	 * @param openApi
	 */
	public void requestStart(HttpServletRequest request, OpenApi openApi) {
		// 标记开始
		OpenapiContext openapiContext = new OpenapiContext();
		// 开始
		openApiStart(openapiContext);
		openapiContext.setRequestStartAt(LocalDateTime.now());

		BasicHeaderDTO basicHeaderDTO = openApi.obtainBasicHeaderDTO(request);
		IgnoreResult ignoreNormal = isIgnore(basicHeaderDTO);
		IgnoreResult ignoreOauth2 = isIgnore();

		if (ignoreNormal.isIgnore() && ignoreOauth2.isIgnore()) {
			OpenapiCollectTool.clear();
			return;
		}


		String clientId = null;
		// 不符合规则忽略处理，每个接口都有权限，这里忽略了也没有关系，后面的逻辑处理会判断接口权限，如：接口添加了@PreAuthorize 注解
		if (ignoreNormal.isIgnore()) {
			log.warn("ignored openapi request. because some of basic header parameter [{}] not exist",ignoreNormal.getReason());
		}else {
			clientId = ignoreNormal.getClientId();
		}
		if (ignoreOauth2.isIgnore()) {
			log.warn("ignored openapi request. because oauth2 is not authenticated. detail reason is {}",ignoreOauth2.getReason());
		}else {
			clientId = ignoreOauth2.getClientId();
		}

		openapiContext.setBasicHeaderDTO(basicHeaderDTO);
		openapiContext.setIgnoreNormal(ignoreNormal);
		openapiContext.setIgnoreOauth2(ignoreOauth2);

		// traceId
		openapiContext.setTraceId(tracer.currentSpan().context().traceIdString());

		RequestParameterDTO requestParameterDTO = openApi.obtainRequestParameterDTO(request);
		openapiContext.setRequestParameterDTO(requestParameterDTO);

		// 请求接口地址
		String requestURI = request.getRequestURI();
		String contextPath = request.getServletContext().getContextPath();
		if (StrUtil.isNotEmpty(contextPath)) {
			requestURI = requestURI.substring(contextPath.length());
		}
		openapiContext.setRequestUrl(requestURI);
		// 设置接口的权限及接口信息,如果获取不到，说明没有配置，应该提示接口不存在，但这里没有提示，因为有可能是通过其它方式访问,通过后面的处理最终一般来说会提示没有权限
		ApiInfo apiInfo = openApi.getApiInfo(requestURI,clientId);
		if (apiInfo != null) {
			openapiContext.setApiInfo(apiInfo);
			// 设置接口的权限，以供后面逻辑校验使用
			PermissionService.putPermission(apiInfo.getPermission());
		}


		// 是否需要自行认证
		boolean isNeedAuthenticate = !ignoreNormal.isIgnore() && ignoreOauth2.isIgnore();

		// 到这里 clientId 肯定是不为空的
		OpenapiClient openapiClient = openApi.getOpenapiClient(clientId,true,isNeedAuthenticate);

		Assert.notNull(openapiClient,ErrorCodeOpenapiEnum.OPENAPI_CLIENT_ID_NOT_EXIST);
		Assert.isTrue(openapiClient.getIsDisabled() != null && !openapiClient.getIsDisabled(),ErrorCodeOpenapiEnum.OPENAPI_CLIENT_DISABLED);
		openapiContext.setOpenapiClient(openapiClient);

		// 校验 时间戳
		boolean timestampValid = openApi.verifyTimestamp(basicHeaderDTO);
		Assert.isTrue(timestampValid, ErrorCodeOpenapiEnum.OPENAPI_ILLEGAL_REQUEST_EXPIRED_REQUEST_ERROR);

		// 校验请求流水号
		boolean nonceValid = openApi.verifyNonce(basicHeaderDTO);
		Assert.isTrue(nonceValid, ErrorCodeOpenapiEnum.OPENAPI_ILLEGAL_REQUEST_REPEATED_REQUEST_ERROR);

		// 是否校验签名
		if (openapiClient.getIsCheckSignature() != null && !openapiClient.getIsCheckSignature()) {
			log.warn("openapi request check signature has disabled. clientId={}",basicHeaderDTO.getClientId());
		}else {
			boolean signatureMatch = openApi.verifySignature(basicHeaderDTO, requestParameterDTO, openapiClient);
			Assert.isTrue(signatureMatch, ErrorCodeOpenapiEnum.OPENAPI_ILLEGAL_REQUEST_INVALID_SIGNATURE_ERROR);
		}


		// 如果不是 oauth2 认证，需要自行认证一下，否则没有权限
		if (isNeedAuthenticate) {
			GlobalOpenapiBasicHeaderAuthenticationToken globalOpenapiBasicHeaderAuthenticationToken
					= new GlobalOpenapiBasicHeaderAuthenticationToken(basicHeaderDTO, createAuthorityList(openapiClient.getAuthorities()));
			globalOpenapiBasicHeaderAuthenticationToken.setAuthenticated(true);
			/**
			 * 创建不使用session存储的上下文，这一点spring security很厉害，考虑到了
			 * 我们一般默认都使用{@link SecurityContextHolder#createEmptyContext()} 来创建
			 */
			SecurityContext context = new TransientSecurityContext();
			context.setAuthentication(globalOpenapiBasicHeaderAuthenticationToken);
			SecurityContextHolder.setContext(context);
		}
		// 应用额度限制
		globalOpenapiRequestLimitService.requestAppQuotaLimit(clientId);

		// 限制规则支持
		if (apiInfo != null) {

			OpenapiLimitRuleInfo clientLimitRuleInfo = apiInfo.getClientLimitRuleInfo();
			if (clientLimitRuleInfo != null) {
				// 	流量限制
				if (globalOpenapiRateLimitService != null) {
					globalOpenapiRateLimitService.threadLocalRateLimit(clientLimitRuleInfo,clientId,apiInfo.getApiCode(),apiInfo.getApiUrl());
				}
				// 	请求限制
				globalOpenapiRequestLimitService.requestLimit(clientLimitRuleInfo,clientId,apiInfo.getApiCode(),apiInfo.getApiUrl());

			}
			OpenapiLimitRuleInfo clientAndOpenapiLimitRuleInfo = apiInfo.getClientAndOpenapiLimitRuleInfo();

			if (clientAndOpenapiLimitRuleInfo != null) {
				// 	流量限制
				if (globalOpenapiRateLimitService != null) {
					globalOpenapiRateLimitService.threadLocalRateLimit(clientAndOpenapiLimitRuleInfo,clientId,apiInfo.getApiCode(),apiInfo.getApiUrl());
				}
				// 	请求限制
				globalOpenapiRequestLimitService.requestLimit(clientAndOpenapiLimitRuleInfo,clientId,apiInfo.getApiCode(),apiInfo.getApiUrl());

			}

		}

	}

	/**
	 * 执行结束，将收集的数据持久化
	 * @param request
	 * @param response
	 */
	public void requestFinished(HttpServletRequest request, HttpServletResponse response) {
		// 如果没有start也就没有必要处理了
		if (!OpenapiCollectTool.isStartOpenApi()) {
			return;
		}
		if (globalOpenapiRateLimitService != null) {
			globalOpenapiRateLimitService.threadLocalRateLimitRemove();
		}
		OpenapiContext context = OpenapiCollectTool.getContext();

		// 收集结束时相关信息
		handleOpenapiContextForFinished(context, response);
		if (globalOpenapiCollectPersistentService != null) {
			if (context != null) {
				globalOpenapiCollectPersistentService.save(context);
			}
		}else {
			log.warn("globalOpenapiCollectPersistentService is null the context data is {}", context == null ? null : JsonTool.toJsonStr(context));
		}
	}

	/**
	 * 收集并补充结束时相关信息
	 * 需要配合 {@link RequestResponseLogFilter} 来获取响应数据
	 * @param context
	 * @param httpServletResponse
	 */
	private void handleOpenapiContextForFinished(OpenapiContext context,HttpServletResponse httpServletResponse) {
		if (context == null) {
			return;
		}
		context.setRequestEndAt(LocalDateTime.now());

		if (context.getIsResponseHasEffectiveValue() == null) {
			if (context.getThrowable() != null) {
				context.setIsResponseHasEffectiveValue(false);
				context.addRemark(" [isResponseHasEffectiveValue=false by throwable is not null] ");
			}
		}
		// 这里不能覆盖，只有效补充
		if (context.getResponseResult() == null) {
			String responseMessagePayload = RequestResponseLogFilter.getResponseMessagePayload(httpServletResponse);
			context.setResponseResult(responseMessagePayload);
		}

		// 如果响应结果是否有值为空，尝试解析判断
		if (context.getIsResponseHasEffectiveValue() == null) {
			Object responseResult = context.getResponseResult();
			if (responseResult instanceof String) {
				try {
					JSONObject responseJsonObject = JSONUtil.parseObj(((String) responseResult));
					if (responseJsonObject.containsKey(Response.FIELD_SUCCESS)
							&& responseJsonObject.containsKey(Response.FIELD_STATUS)
							&& responseJsonObject.containsKey(Response.FIELD_ERRCODE)
							&& responseJsonObject.containsKey(Response.FIELD_ERRMESSAGE)) {
						Boolean success = responseJsonObject.getBool(Response.FIELD_SUCCESS);
						Long businessStatus = responseJsonObject.getLong(Response.FIELD_STATUS);
						if (success != null) {
							context.setIsResponseHasEffectiveValue(success);
							context.setResponseBusinessStatus(businessStatus == null ? null : businessStatus.toString());
							context.addRemark(" [isResponseHasEffectiveValue=" + success + " by string check for com.particle.global.dto.response.Response type] ");
						}
					}
				} catch (Exception e) {
				}

			} else if (responseResult instanceof Response) {
				Boolean success = ((Response) responseResult).isSuccess();
				Long businessStatus = ((Response) responseResult).getStatus();
				if (success != null) {
					context.setIsResponseHasEffectiveValue(success);
					context.setResponseBusinessStatus(businessStatus == null ? null : businessStatus.toString());
					context.addRemark(" [isResponseHasEffectiveValue=" + success + " by instanceof com.particle.global.dto.response.Response type] ");
				}
			}
		}

		int httpStatus = httpServletResponse.getStatus();
		context.setResponseHttpStatus(httpStatus);
	}

	/**
	 * 权限码字符串转对象
	 * @param authorities
	 * @return
	 */
	public static List<GrantedAuthority> createAuthorityList(Set<String> authorities) {
		if (CollectionUtil.isEmpty(authorities)) {
			return Collections.emptyList();
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities.size());
		for (String authority : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority));
		}
		return grantedAuthorities;
	}

	/**
	 * 校验参数
	 * @param basicHeaderDTO
	 */
	private void validateBasicHeader(BasicHeaderDTO basicHeaderDTO) {
		Assert.notEmpty(basicHeaderDTO.getClientId(), ErrorCodeOpenapiEnum.OPENAPI_BAD_REQUEST_ERROR);
		Assert.isTrue(basicHeaderDTO.getTimestamp() != null, ErrorCodeOpenapiEnum.OPENAPI_BAD_REQUEST_ERROR);
		Assert.notEmpty(basicHeaderDTO.getNonce(), ErrorCodeOpenapiEnum.OPENAPI_BAD_REQUEST_ERROR);
		Assert.notEmpty(basicHeaderDTO.getSignature(), ErrorCodeOpenapiEnum.OPENAPI_BAD_REQUEST_ERROR);
	}

	/**
	 * basicHeader 模式是否忽略
	 * @param basicHeaderDTO
	 * @return
	 */
	private IgnoreResult isIgnore(BasicHeaderDTO basicHeaderDTO) {
		IgnoreResult ignoreResult = new IgnoreResult();
		ignoreResult.setIgnore(false);

		StringBuffer sb = new StringBuffer();
		if (StrUtil.isEmpty(basicHeaderDTO.getClientId())) {
			ignoreResult.setIgnore(true);
			sb.append(" clientId");
		}else {
			ignoreResult.setClientId(basicHeaderDTO.getClientId());
		}
		if (basicHeaderDTO.getTimestamp() == null) {
			ignoreResult.setIgnore(true);
			sb.append(" timestamp");
		}
		if (StrUtil.isEmpty(basicHeaderDTO.getNonce())) {
			ignoreResult.setIgnore(true);
			sb.append(" nonce");
		}
		if (StrUtil.isEmpty(basicHeaderDTO.getSignature())) {
			ignoreResult.setIgnore(true);
			sb.append(" signature");
		}
		if (sb.length() > 0) {
			ignoreResult.setReason(sb.toString());
		}

		return ignoreResult;
	}

	/**
	 * oauth2 模式是否忽略
	 * @return
	 */
	private IgnoreResult isIgnore(){
		IgnoreResult ignoreResult = new IgnoreResult();
		ignoreResult.setIgnore(false);

		StringBuffer sb = new StringBuffer();
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			ignoreResult.setIgnore(true);
			sb.append("oauth2 context is null");
			ignoreResult.setReason(sb.toString());
			return ignoreResult;

		}
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			ignoreResult.setIgnore(true);
			sb.append("oauth2 authentication is null");
			ignoreResult.setReason(sb.toString());
			return ignoreResult;
		}
		boolean authenticated = authentication.isAuthenticated();
		if (!authenticated) {
			ignoreResult.setIgnore(true);
			sb.append("oauth2 isAuthenticated is false");
			ignoreResult.setReason(sb.toString());
			return ignoreResult;
		}
		boolean jwtToken = authentication instanceof JwtAuthenticationToken;
		boolean opaqueToken = authentication instanceof BearerTokenAuthentication;
		if (jwtToken || opaqueToken) {
			if (jwtToken) {
				Object principal = ((JwtAuthenticationToken) authentication).getName();
				ignoreResult.setClientId(principal.toString());

			} else if (opaqueToken) {
				Object principal = ((BearerTokenAuthentication) authentication).getPrincipal();
				if (principal instanceof OAuth2IntrospectionAuthenticatedPrincipal) {
					String name = ((OAuth2IntrospectionAuthenticatedPrincipal) principal).getName();
					ignoreResult.setClientId(name);
				}
			}

			return ignoreResult;
		}else {
			ignoreResult.setIgnore(true);
			sb.append("authentication is not JwtAuthenticationToken or BearerTokenAuthentication");
			ignoreResult.setReason(sb.toString());
			return ignoreResult;
		}

	}


	/**
	 * 忽略请求解析结果
	 */
	@Data
	public static class IgnoreResult {
		/**
		 * 如果不忽略时，提取clientId
		 */
		private String clientId;

		/**
		 * 是否忽略
		 */
		private boolean ignore;
		/**
		 * 原因
		 */
		private String reason;
	}
}
