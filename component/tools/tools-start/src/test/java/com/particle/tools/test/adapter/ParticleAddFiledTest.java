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

		addFieldCommand.setAfterFieldName(StringTool.lineToHump("serial_times"));
		// 注意首字母要大写，应该是类名称，不带后缀
		addFieldCommand.setDomainName("SsqCode");
		addFieldCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/dream");

		addFieldCommand.addFieldItem(StringTool.lineToHump("max_serial_length"),"最大连号长度",Integer.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("is_has_even_serial_num"),"是否包含偶连号，即间隔2",Boolean.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("even_serial_times"),"偶连号个数，如：2 4 22 24 25 33 7则为2，因为2 4算一个，22 24 算一个",Integer.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("even_max_serial_length"),"最大偶连号长度",Integer.class.getSimpleName());

		Response response = particleController.addField(addFieldCommand);
		System.out.println(JsonTool.toJsonStr(response));
	}
}
