package com.particle.global.captcha.endpoint;

import cn.hutool.core.lang.Pair;
import com.particle.global.captcha.CaptchaTypeEnum;
import com.particle.global.captcha.ICaptchaScene;
import com.particle.global.captcha.ICaptchaType;
import com.particle.global.captcha.gen.CaptchaGenDTO;
import com.particle.global.dto.basic.Command;

import com.particle.global.dto.basic.QueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.stream.Collectors;

import static com.particle.global.captcha.gen.CaptchaGenDTO.*;

/**
 * <p>
 * 验证码生成命令
 * </p>
 *
 * @author yangwei
 * @since 2023-04-25 14:28
 */
@Data
@Schema
public class CaptchaGenCommand extends QueryCommand {

	@NotEmpty(message = "验证码的用途或场景标识不能为空")
	@Schema(title = "验证码的用途或场景标识",requiredMode = Schema.RequiredMode.REQUIRED,description = "任意的字符串",example = "/login")
	private String captchaScene;

	/**
	 * 验证码类型
	 * 实现了 {@link ICaptchaType}接口的类型,默认实现参见{@link CaptchaTypeEnum}
	 */
	@Schema(title = "验证码类型",description =
			("customImage = 自定义图片,") +
			("gif = 动画,") +
			("chinese = 中文,") +
			("chineseGif = 中文动画,") +
			("arithmetic = 算术数字运算")
					)
	private String captchaType;

	@Max(value = 500,message = "验证码宽度最大不能超过500")
	@Schema(description = "验证码宽度")
	private Integer width = 130;

	@Max(value = 70,message = "验证码高度最大不能超过70")
	@Schema(description = "验证码高度")
	private Integer height = 48;

	@Schema(description = "验证码长度")
	private Integer length;

	/**
	 * 仅自定义图片时有效 即 {@link CaptchaGenCommand#captchaType} = {@link CaptchaTypeEnum#customImage}
	 */
	@Schema(title = "文本字符串组合类型",description =
			(TYPE_DEFAULT + "= 字母数字混合,") +
					(TYPE_ONLY_NUMBER + "= 纯数字,") +
					(TYPE_ONLY_CHAR + "= 纯字母,") +
					(TYPE_ONLY_UPPER + "= 纯大写字母,") +
					(TYPE_ONLY_LOWER + "= 纯小写字母,") +
					(TYPE_NUM_AND_UPPER + "= 数字大写字母"))
	private Integer charType = TYPE_ONLY_NUMBER;
	
}
