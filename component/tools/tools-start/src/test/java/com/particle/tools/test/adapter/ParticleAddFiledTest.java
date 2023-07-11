package com.particle.tools.test.adapter;

import com.particle.global.dto.response.Response;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.str.StringTool;
import com.particle.tools.adapter.web.front.ParticleController;
import com.particle.tools.client.dto.command.AddFieldCommand;

import java.time.LocalDateTime;

/**
 * <p>
 * 测试添加字段
 * </p>
 *
 * @author yangwei
 * @since 2023-05-11 13:51
 */
public class ParticleAddFiledTest {

	public static void main(String[] args) {
		ParticleController particleController = new ParticleController();

		AddFieldCommand addFieldCommand = new AddFieldCommand();

		addFieldCommand.setAfterFieldName(StringTool.lineToHump("mobile"));
		addFieldCommand.setDomainName("TenantCreateApply");
		addFieldCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/tenant");


		addFieldCommand.addFieldItem(StringTool.lineToHump("password"),"密码，没有指定 applyUserId 时，用户创建用户登录密码",String.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("is_send_email_notice"),"是否发送邮件通知，1=发送，0=不发送，仅存在邮箱时发送",String.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("is_send_mobile_notice"),"密码，没有指定 applyUserId 时，用户创建用户登录密码",String.class.getSimpleName());

		Response response = particleController.addField(addFieldCommand);
		System.out.println(JsonTool.toJsonStr(response));
	}
}
