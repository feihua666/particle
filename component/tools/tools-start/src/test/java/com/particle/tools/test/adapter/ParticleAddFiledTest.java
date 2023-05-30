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

		addFieldCommand.setAfterFieldName(StringTool.lineToHump("join_at"));
		addFieldCommand.setDomainName("TenantUser");
		addFieldCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/tenant");


		addFieldCommand.addFieldItem(StringTool.lineToHump("is_formal"),"是否正式，1=正式，0=试用",Boolean.class.getSimpleName());

		Response response = particleController.addField(addFieldCommand);
		System.out.println(JsonTool.toJsonStr(response));
	}
}
