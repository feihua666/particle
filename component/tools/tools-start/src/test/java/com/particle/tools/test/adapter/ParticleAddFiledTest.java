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

		addFieldCommand.setAfterFieldName(StringTool.lineToHump("max_length"));
		// 注意首字母要大写，应该是类名称，不带后缀
		addFieldCommand.setDomainName("OpenplatformDocApiDocParamField");
		addFieldCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/open-platform");

		addFieldCommand.addFieldItem(StringTool.lineToHump("dict_group_dict_id"),"字典组字典，字典组id，字典组下面的字典项为字段枚举",Long.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("dict_item_tags"),"字典项标签，如果某一个字典组下的字典项过多可以根据标签过滤",String.class.getSimpleName());

		Response response = particleController.addField(addFieldCommand);
		System.out.println(JsonTool.toJsonStr(response));
	}
}
