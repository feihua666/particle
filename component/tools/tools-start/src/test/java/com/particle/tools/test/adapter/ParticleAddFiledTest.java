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

		addFieldCommand.setAfterFieldName(StringTool.lineToHump("expire_at"));
		// 注意首字母要大家，应该是类名称，不带后缀
		addFieldCommand.setDomainName("TenantUser");
		addFieldCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/tenant");


		addFieldCommand.addFieldItem(StringTool.lineToHump("effective_at"),"生效日期，从什么时候开始生效",LocalDateTime.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("effective_at_trigger_dict_id"),"生效日期，触发方式，一般为首次登录触发",Long.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("effective_days"),"有效天数,0或空为不限制",Integer.class.getSimpleName());

		Response response = particleController.addField(addFieldCommand);
		System.out.println(JsonTool.toJsonStr(response));
	}
}
