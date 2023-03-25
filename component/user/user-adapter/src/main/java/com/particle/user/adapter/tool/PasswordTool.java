package com.particle.user.adapter.tool;

import com.particle.global.security.security.PasswordEncryptEnum;
import com.particle.global.tool.security.PasswordComplexityTool;
import com.particle.user.client.identifier.dto.command.UserIdentifierPasswordCommand;

/**
 * <p>
 * 密码工具
 * </p>
 *
 * @author yangwei
 * @since 2022-12-26 11:38
 */
public class PasswordTool {


	/**
	 * 加密密码
	 * 注意：原始密码不能包含 & 字符，由于本框架原因，使用 form登录，在登录时会截取 & 前面的字符导致登录失败
	 * @param userIdentifierPasswordCommand
	 */
	public static void encodePassword(UserIdentifierPasswordCommand userIdentifierPasswordCommand) {
		PasswordComplexityTool.PasswordComplexityResult complexityLevel = PasswordComplexityTool.getComplexityLevel(userIdentifierPasswordCommand.getPassword());
		userIdentifierPasswordCommand.setPwdComplexity(complexityLevel.getComplexityLevel());
		PasswordEncryptEnum.PrefixEncodedPassword encode = PasswordEncryptEnum.encode(userIdentifierPasswordCommand.getPassword());
		userIdentifierPasswordCommand.setPwdEncoded(encode.getRawEncodePassword());
		userIdentifierPasswordCommand.setPwdEncryptFlag(encode.getEncrypt());
	}
}
