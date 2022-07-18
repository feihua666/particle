package com.particle.generator.domain.component;

import com.particle.global.dto.basic.Value;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-07-05 23:12
 */
@Data
@Builder
public class DatasourceConf extends Value {

	private String url;
	private String username;
	private String password;

	/**
	 * 创建对象
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public static DatasourceConf create(String url,
										String username,
										String password) {
		return DatasourceConf.builder()
				.url(url)
				.username(username)
				.password(password)
				.build();
	}
}
