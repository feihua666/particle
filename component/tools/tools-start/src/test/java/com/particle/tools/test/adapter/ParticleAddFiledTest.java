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

		addFieldCommand.setAfterFieldName(StringTool.lineToHump("name"));
		// 注意首字母要大写，应该是类名称，不带后缀
		addFieldCommand.setDomainName("CrmCustomerRelationDefine");
		addFieldCommand.setComponentBackendAbsolutePath("/Users/yw/fh/git-source/particle/component/crm");

		addFieldCommand.addFieldItem(StringTool.lineToHump("is_bidirectional"),"是否为双向关系,不是双向就是单身",Boolean.class.getSimpleName());
		addFieldCommand.addFieldItem(StringTool.lineToHump("bidirectional_id"),"双向关系id，如果为单向关系，则必填，存储对应的双向关系id",Long.class.getSimpleName());

		Response response = particleController.addField(addFieldCommand);
		System.out.println(JsonTool.toJsonStr(response));
	}
}
