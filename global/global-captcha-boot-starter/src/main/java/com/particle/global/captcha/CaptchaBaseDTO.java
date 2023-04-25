package com.particle.global.captcha;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 验证码参数基类
 * </p>
 *
 * @author yangwei
 * @since 2023-04-24 18:23
 */
@Data
public class CaptchaBaseDTO extends DTO {

	/**
	 * 唯一标识
	 */
	private String captchaUniqueIdentifier;



	/**
	 * 填充数据
	 * @param captchaUniqueIdentifier
	 */
	public void fill(String captchaUniqueIdentifier
	) {
		this.captchaUniqueIdentifier = captchaUniqueIdentifier;
	}
}
