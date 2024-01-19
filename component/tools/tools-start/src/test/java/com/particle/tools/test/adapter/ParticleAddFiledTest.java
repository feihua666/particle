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

		addFieldCommand.setAfterFieldName(StringTool.lineToHump("is_use_remote"));
		// 注意首字母要大写，应该是类名称，不带后缀
		addFieldCommand.setDomainName("DataQueryDataApi");
		addFieldCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/data-query");

		addFieldCommand.addFieldItem(StringTool.lineToHump("is_published"),"是否已发布，已发布不能修改和删除",Boolean.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("is_master"),"是否为主版本，非主版本视为开发版本",Boolean.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("master_id"),"主版本id",Long.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("is_test_passed"),"是否测试通过，测试通过才能发布",Boolean.class.getSimpleName());

		Response response = particleController.addField(addFieldCommand);
		System.out.println(JsonTool.toJsonStr(response));
	}
}
