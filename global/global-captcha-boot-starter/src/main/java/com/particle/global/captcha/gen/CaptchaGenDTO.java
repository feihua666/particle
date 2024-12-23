package com.particle.global.captcha.gen;

import com.particle.global.captcha.CaptchaBaseDTO;
import com.particle.global.captcha.CaptchaTypeEnum;
import com.particle.global.captcha.ICaptchaScene;
import com.particle.global.captcha.ICaptchaType;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 验证码生成数据传输对象
 * </p>
 *
 * @author yangwei
 * @since 2023-04-24 20:53
 */
@Data
public class CaptchaGenDTO extends CaptchaBaseDTO {

	// 验证码文本类型
	public static final int TYPE_DEFAULT = 1;  // 字母数字混合
	public static final int TYPE_ONLY_NUMBER = 2;  // 纯数字
	public static final int TYPE_ONLY_CHAR = 3;  // 纯字母
	public static final int TYPE_ONLY_UPPER = 4;  // 纯大写字母
	public static final int TYPE_ONLY_LOWER = 5;  // 纯小写字母
	public static final int TYPE_NUM_AND_UPPER = 6;  // 数字大写字母

	/**
	 * 验证码的用途或场景
	 */
	private ICaptchaScene captchaScene;

	/**
	 * 验证码类型
	 */
	private ICaptchaType captchaType = CaptchaTypeEnum.arithmetic;
	/**
	 * 验证码宽度
	 */
	private Integer width = 130;
	/**
	 * 验证码调度
	 */
	private Integer height = 48;
	/**
	 * 长度或位数
	 */
	private Integer length = 3;

	/**
	 * 文本字符串组合类型
	 */
	private int charType;
	/**
	 * 在什么时间过期
	 */
	private LocalDateTime expireAt;

	/**
	 * 是否为动态码
	 */
	private Boolean isDynamic;
	/**
	 * 使用默认数据
	 * @param captchaUniqueIdentifier
	 * @param captchaScene
	 * @return
	 */
	public static CaptchaGenDTO create(String captchaUniqueIdentifier,
									   ICaptchaScene captchaScene,
									   Boolean isDynamic

	) {
		return create(captchaUniqueIdentifier,captchaScene,null,null,null,null,null,null,isDynamic);
	}

	/**
	 * 使用全量自定义数据
	 * @param captchaUniqueIdentifier
	 * @param captchaScene
	 * @param captchaType
	 * @param width
	 * @param height
	 * @param length
	 * @param expireAt
	 * @return
	 */
	public static CaptchaGenDTO create(String captchaUniqueIdentifier,
									   ICaptchaScene captchaScene,
									   ICaptchaType captchaType,
									   Integer width,
									   Integer height,
									   Integer length,
									   Integer charType,
									   LocalDateTime expireAt,
									   Boolean isDynamic

	) {
		CaptchaGenDTO captchaGenDTO = new CaptchaGenDTO();
		captchaGenDTO.fill(captchaUniqueIdentifier, captchaScene, captchaType,width,height,length,charType,expireAt,isDynamic);
		return captchaGenDTO;
	}

	public void fill(String captchaUniqueIdentifier,
					 ICaptchaScene captchaScene,
					 ICaptchaType captchaType,
					 Integer width,
					 Integer height,
					 Integer length,
					 Integer charType,
					 LocalDateTime expireAt,
					 Boolean isDynamic
	) {
		fill(captchaUniqueIdentifier);
		this.captchaScene = captchaScene;
		if (captchaType != null) {
			this.captchaType = captchaType;
		}
		if (width != null) {
			this.width = width;
		}
		if (height != null) {
			this.height = height;
		}
		if (length != null) {
			this.length = length;
		}
		this.charType = charType != null ? charType : TYPE_DEFAULT;

		if (expireAt == null) {
			this.expireAt = LocalDateTime.now().plusMinutes(2);
		}else {
			this.expireAt = expireAt;
		}
		this.isDynamic = isDynamic;
	}
}
