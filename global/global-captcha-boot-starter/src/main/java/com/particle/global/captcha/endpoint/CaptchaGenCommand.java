package com.particle.global.captcha.endpoint;

import cn.hutool.core.lang.Pair;
import com.particle.global.captcha.CaptchaTypeEnum;
import com.particle.global.captcha.ICaptchaScene;
import com.particle.global.captcha.ICaptchaType;
import com.particle.global.captcha.gen.CaptchaGenDTO;
import com.particle.global.dto.basic.Command;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel
public class CaptchaGenCommand extends Command {

	@NotEmpty(message = "验证码的用途或场景标识不能为空")
	@ApiModelProperty(value = "验证码的用途或场景标识",required = true,example = "任意的字符串")
	private String captchaScene;

	/**
	 * 验证码类型
	 */
	@ApiModelProperty(value = "验证码类型",example = 
			(TYPE_DEFAULT + "= 字母数字混合") +
			(TYPE_ONLY_NUMBER + "= 纯数字") +
			(TYPE_ONLY_CHAR + "= 纯字母") +
			(TYPE_ONLY_UPPER + "= 纯大写字母") +
			(TYPE_ONLY_LOWER + "= 纯小写字母") +
			(TYPE_NUM_AND_UPPER + "= 数字大写字母"))
	private String captchaType;

	@ApiModelProperty("验证码宽度")
	private Integer width = 130;

	@ApiModelProperty("验证码高度")
	private Integer height = 48;

	@ApiModelProperty("验证码长度")
	private Integer length = 5;

	@ApiModelProperty("验证码长度")
	private int charType;
	
}
