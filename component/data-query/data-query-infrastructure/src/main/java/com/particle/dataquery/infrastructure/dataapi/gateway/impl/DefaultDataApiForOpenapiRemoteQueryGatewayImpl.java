package com.particle.dataquery.infrastructure.dataapi.gateway.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;
import com.particle.dataquery.domain.dataapi.gateway.DataApiRemoteQueryGateway;
import com.particle.global.openapi.api.AbstractGlobalOpenapi;
import com.particle.global.openapi.collect.OpenapiCollectTool;
import com.particle.global.openapi.data.BasicHeaderDTO;
import com.particle.global.openapi.data.RequestParameterDTO;
import com.particle.global.openapi.enums.OpenapiDigestAlgorithm;
import com.particle.global.tool.http.HttpClientTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.proxy.ProxyConfig;
import lombok.Data;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.function.Supplier;

/**
 * <p>
 * 默认的可配置远程开放接口调用
 * </p>
 *
 * @author yangwei
 * @since 2023-08-23 16:05
 */

@ConfigurationProperties(prefix = "particle.dataquery.openapi")
public class DefaultDataApiForOpenapiRemoteQueryGatewayImpl implements DataApiRemoteQueryGateway {

	@Setter
	private Config config;

	@Override
	public boolean support(DataQueryDataApi dataQueryDataApi, Object param, String queryString) {
		if (OpenapiCollectTool.isStartOpenApi()) {
			return false;
		}
		if (config != null) {
			return config.isEnabled();
		}
		return false;
	}

	@SneakyThrows
	@Override
	public Object query(DataQueryDataApi dataQueryDataApi, Object param, String queryString, Supplier realTimeSupplier) {

		String url = dataQueryDataApi.getUrl();

		long timestamp = System.currentTimeMillis();
		String nonce = "dq_" + UUID.randomUUID().toString(true);

		String remoteUrl = config.requestUrl(url,queryString);
		String body = JsonTool.toJsonStr(param);
		HttpClientTool.ExtConfig extConfig = HttpClientTool.ExtConfig.create();
		extConfig.addHeader("timestamp", timestamp + "");
		extConfig.addHeader("nonce", nonce);
		extConfig.addHeader("clientId", config.getAppId());

		BasicHeaderDTO basicHeaderDTO = BasicHeaderDTO.createDefault(config.getAppId(), timestamp, nonce, null);
		RequestParameterDTO requestParameterDTO = RequestParameterDTO.create(null, queryString, null, body);

		String prepareSignatureData = AbstractGlobalOpenapi.prepareSignatureData(basicHeaderDTO, requestParameterDTO, config.getAppSecret());
		String digestHex = OpenapiDigestAlgorithm.SHA_256.digestHex(prepareSignatureData);

		extConfig.addHeader("signature", digestHex);

		extConfig.setProxyConfig(config.getProxyConfig());
		String s = HttpClientTool.postJson(remoteUrl, body, extConfig);
		return s;
	}

	@Data
	public static class Config{
		/**
		 * 是否启用
		 */
		private boolean enabled;
		/**
		 * 远程地址：如：http://localhost:8080
		 */
		private String remoteDomain;
		private String appId;
		private String appSecret;

		/**
		 * 开放接口的前缀
		 */
		private String urlPrefix = "/openapi/dq";

		/**
		 * 代理配置
		 */
		private ProxyConfig proxyConfig;

		/**
		 * 获取最终的url
		 * @param url
		 * @param queryString
		 * @return
		 */
		public String requestUrl(String url, String queryString) {
			String result = remoteDomain + urlPrefix + url;
			if (StrUtil.isNotEmpty(queryString)) {
				result = result + "?" + queryString;
			}
			return result;
		}
	}
}
