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

		addFieldCommand.setAfterFieldName(StringTool.lineToHump("red_hot10_ratio"));
		// 注意首字母要大写，应该是类名称，不带后缀
		addFieldCommand.setDomainName("SsqCodeOpened");
		addFieldCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/dream");

		addFieldCommand.addFieldItem(StringTool.lineToHump("seq_no"),"序号，从1开始递增",Integer.class.getSimpleName());

		Response response = particleController.addField(addFieldCommand);
		System.out.println(JsonTool.toJsonStr(response));
	}
}
