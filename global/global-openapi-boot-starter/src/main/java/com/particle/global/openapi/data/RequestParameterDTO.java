package com.particle.global.openapi.data;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.particle.global.dto.basic.DTO;
import com.particle.global.openapi.tool.OpenapiTool;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 请求参数相关
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 15:40
 */
@Data
public class RequestParameterDTO extends DTO {

	/**
	 * path参数，没有key,目前预留，
	 * 如：spring mvc 的 /xxxxx/{name}/{id}
	 * 则：按顺序存储 ['name值','id值']
	 */
	private List<String> pathParameters;

	/**
	 * 请求参数，不带问号
	 * 如：name=张三&id=1
	 */
	private String queryParameters;

	/**
	 * 请求参数，一般用于form参数，请求 content-type = {@link MediaType#APPLICATION_FORM_URLENCODED_VALUE}
	 */
	private Map<String, String> formParameters;

	/**
	 * 请求参数，一般用于除form的情况，请求 content-type = {@link MediaType#APPLICATION_JSON_VALUE} 或 其它 String 类型
	 */
	private String bodyParameters;


	/**
	 * 生成待签名的字符串
	 * 请求参数按如下顺序拼接：{@link pathParameters}、{@link queryParameters}、{@link formParameters}、{@link bodyParameters}
	 * {@link pathParameters} 直接按顺序拼接
	 * {@link queryParameters} 以 key=value 按key字典顺序拼接
	 * {@link formParameters} 以 key=value 按key字典顺序拼接
	 * {@link bodyParameters} 直接拼接
	 * 将以上每一部分拼接完成的，按顺序整体拼接
	 * @return
	 */
	public String buildStringForSignature() {

		StringBuffer sb = new StringBuffer();
		// 按照path中的顺序将所有value进行拼接
		if (CollectionUtil.isNotEmpty(pathParameters)) {
			sb.append(pathParameters.stream().collect(Collectors.joining()));
		}
		// 按照key字典序排序，将所有key=value进行拼接
		if (StrUtil.isNotEmpty(queryParameters)) {
			String[] split = queryParameters.split("&");
			TreeMap<String, String> treeMap = Arrays.stream(split).map(item -> {
				String[] splitKeyValue = item.split("=");
				return Pair.<String, String>of(splitKeyValue[0], splitKeyValue[1]);
			}).collect(Collectors.toMap(Pair::getKey, Pair::getValue, (o, n) -> n, TreeMap::new));

			return OpenapiTool.buildMapToStringForSignature(treeMap);
		}
		// 按照key字典序排序，将所有key=value进行拼接
		if (CollectionUtil.isNotEmpty(formParameters)) {
			TreeMap<String, String> treeMap = MapUtil.sort(formParameters);
			return OpenapiTool.buildMapToStringForSignature(treeMap);
		}
		// 不拼接，直接按原始的值，因为这肯定是不变的
		if (StrUtil.isNotEmpty(bodyParameters)) {
			sb.append(bodyParameters);
		}
		return sb.toString();
	}

	public static RequestParameterDTO create(List<String> pathParameters,
											 String queryParameters,
											 Map<String, String> formParameters,
											 String bodyParameters) {
		RequestParameterDTO requestParameterDTO = new RequestParameterDTO();
		requestParameterDTO.pathParameters = pathParameters;
		requestParameterDTO.queryParameters = queryParameters;
		requestParameterDTO.formParameters = formParameters;
		requestParameterDTO.bodyParameters = bodyParameters;
		return requestParameterDTO;
	}

	@SneakyThrows
	public static RequestParameterDTO create(HttpServletRequest request) {
		String queryString = request.getQueryString();
		Map<String, String> formParameters = null;
		String bodyParameters = null;
		String header = request.getHeader(HttpHeaders.CONTENT_TYPE);
		if (StrUtil.startWith(header, MediaType.APPLICATION_FORM_URLENCODED_VALUE) || StrUtil.startWith(header, MediaType.MULTIPART_FORM_DATA_VALUE)) {
			// 获取所有的参数，但该参数包括 queryString和form中的参数，再根据queryString有的去除
			Map<String, String> paramMap = JakartaServletUtil.getParamMap(request);
			if (CollectionUtil.isNotEmpty(paramMap)) {
				formParameters = new HashMap<>();
				for (Map.Entry<String, String> stringStringEntry : paramMap.entrySet()) {
					if (!StrUtil.containsAny(queryString, stringStringEntry.getKey() + "=")) {
						formParameters.put(stringStringEntry.getKey(), stringStringEntry.getValue());
					}
				}
			}
		}else {
			ServletInputStream inputStream = request.getInputStream();
			bodyParameters = IoUtil.readUtf8(inputStream);
		}
		return create(null, queryString, formParameters, bodyParameters);
	}


}
