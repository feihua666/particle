package com.particle.global.openapi.data;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * <p>
 * 基本请求头数据,主要包括请求客户端id、时间戳、随机值、签名
 * </p>
 *
 * @author yangwei
 * @since 2023-08-02 14:59
 */
@Data
public class BasicHeaderDTO extends DTO {

	/**
	 * 请求头配置，目的是可以兼容多种请求头
	 */
	private BasicHeaderConfigDTO basicHeaderConfigDTO;

	/**
	 * 请求客户端id
	 */
	private String clientId;
	/**
	 * 请求时间戳
	 */
	private Long timestamp;
	/**
	 * 请求随机字符器，建议uuid
	 */
	private String nonce;
	/**
	 * 数据签名
	 */
	private String signature;

	/**
	 * 生成待签名的字符串
	 * 请求头按如下顺序以 key=value 拼接：clientId、timestamp、nonce 如： clientId=xxxtimestamp=xxxnonce=xxx
	 * @return
	 */
	public String buildStringForSignature() {
		StringBuffer sb = new StringBuffer();
		sb.append(basicHeaderConfigDTO.getClientId()).append("=").append(clientId);
		sb.append(basicHeaderConfigDTO.getTimestamp()).append("=").append(timestamp);
		sb.append(basicHeaderConfigDTO.getNonce()).append("=").append(nonce);
		return sb.toString();
	}
	public static BasicHeaderDTO create(
			String clientId,
			Long timestamp,
			String nonce,
			String signature, BasicHeaderConfigDTO basicHeaderConfigDTO) {
		BasicHeaderDTO basicHeaderDTO = new BasicHeaderDTO();
		basicHeaderDTO.basicHeaderConfigDTO = (basicHeaderConfigDTO);

		basicHeaderDTO.clientId = clientId;
		basicHeaderDTO.timestamp = timestamp;
		basicHeaderDTO.nonce = nonce;
		basicHeaderDTO.signature = signature;
		return basicHeaderDTO;
	}
	public static BasicHeaderDTO createDefault(
			String clientId,
			Long timestamp,
			String nonce,
			String signature) {
		return create(clientId, timestamp, nonce, signature, BasicHeaderConfigDTO.createDefault());
	}

	public static BasicHeaderDTO createByHttpServletRequest(HttpServletRequest request, BasicHeaderConfigDTO basicHeaderConfigDTO) {

		return create(
				request.getHeader(basicHeaderConfigDTO.getClientId()),
				Optional.ofNullable(request.getHeader(basicHeaderConfigDTO.getTimestamp())).map(Long::new).orElse(null),
				request.getHeader(basicHeaderConfigDTO.getNonce()),
				request.getHeader(basicHeaderConfigDTO.getSignature()),
				basicHeaderConfigDTO
		);
	}

	public static BasicHeaderDTO createDefaultByHttpServletRequest(HttpServletRequest request) {
		return createByHttpServletRequest(request, BasicHeaderConfigDTO.createDefault());
	}


	@Data
	public static class BasicHeaderConfigDTO extends DTO {
		/**
		 * 默认的请求头 clientId 已分配的客户端id
		 */
		public static final String default_header_clientId = "clientId";
		/**
		 * 默认的请求头 timestamp 请求时间戳，单位毫秒
		 */
		public static final String default_header_timestamp = "timestamp";
		/**
		 * 默认的请求头 nonce 临时字符串，可以是一个traceId 或请求流水号，每次请求必须不同
		 */
		public static final String default_header_nonce = "nonce";
		/**
		 * 默认的请求头 signature 签名
		 */
		public static final String default_header_signature = "signature";

		/**
		 * 请求客户端id
		 */
		private String clientId;
		/**
		 * 请求时间戳
		 */
		private String timestamp;
		/**
		 * 请求随机字符器，建议uuid
		 */
		private String nonce;
		/**
		 * 数据签名
		 */
		private String signature;


		public static BasicHeaderConfigDTO create( String clientId,
												   String timestamp,
												   String nonce,
												   String signature) {
			BasicHeaderConfigDTO basicHeaderConfigDTO = new BasicHeaderConfigDTO();
			basicHeaderConfigDTO.setClientId(clientId);
			basicHeaderConfigDTO.setTimestamp(timestamp);
			basicHeaderConfigDTO.setNonce(nonce);
			basicHeaderConfigDTO.setSignature(signature);
			return basicHeaderConfigDTO;
		}

		/**
		 * 创建默认的
		 * @return
		 */
		public static BasicHeaderConfigDTO createDefault() {
			return create(default_header_clientId,
					default_header_timestamp,
					default_header_nonce,
					default_header_signature
			);
		}
	}

}
