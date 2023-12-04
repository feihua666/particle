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

		addFieldCommand.setAfterFieldName(StringTool.lineToHump("out_param_success_config_json"));
		// 注意首字母要大写，应该是类名称，不带后缀
		addFieldCommand.setDomainName("DataQueryDataApi");
		addFieldCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/data-query");

		addFieldCommand.addFieldItem(StringTool.lineToHump("out_param_trans_config_json"),"出参翻译配置json",String.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("out_param_ext_config_json"),"出参扩展配置json",String.class.getSimpleName());

		Response response = particleController.addField(addFieldCommand);
		System.out.println(JsonTool.toJsonStr(response));
	}
}
