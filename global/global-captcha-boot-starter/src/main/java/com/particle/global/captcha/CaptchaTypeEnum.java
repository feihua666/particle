package com.particle.global.captcha;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 验证码类型枚举
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 09:53
 */
public enum CaptchaTypeEnum implements ICaptchaType{


	/**
	 * 自定义图片
	 */
	customImage,
	/**
	 * 动画
	 */
	gif,
	/**
	 * 中文
	 */
	chinese,
	/**
	 * 中文动画
	 */
	chineseGif,
	/**
	 * 算术 一般为 数字运算
	 */
	arithmetic,
	;

	@Override
	public String type() {
		return this.name();
	}


	public static CaptchaTypeEnum getByName(String name) {
		return Arrays.stream(CaptchaTypeEnum.values()).sequential().filter(item -> item.name().equals(name)).findFirst().orElse(null);
	}

	/**
	 * 获取默认的长度
	 * @param captchaType
	 * @return
	 */
	public static int defaultLength(String captchaType){
		if (defaultLengthMap == null) {
			defaultLengthMap = new HashMap<>();
		}
		defaultLengthMap.put(CaptchaTypeEnum.customImage.name(), 4);
		defaultLengthMap.put(CaptchaTypeEnum.gif.name(), 3);
		defaultLengthMap.put(CaptchaTypeEnum.chinese.name(), 3);
		defaultLengthMap.put(CaptchaTypeEnum.chineseGif.name(), 3);
		defaultLengthMap.put(CaptchaTypeEnum.arithmetic.name(), 2);

		return 3;
	}

	private static Map<String,Integer> defaultLengthMap;

}
