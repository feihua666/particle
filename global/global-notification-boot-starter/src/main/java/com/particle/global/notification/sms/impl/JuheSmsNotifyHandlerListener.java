package com.particle.global.notification.sms.impl;

import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.notification.sms.ISmsNotifyHandlerListener;
import com.particle.global.tool.http.HttpClientTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.sms.SmsAccount;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 聚合短信实现
 * 文档：https://www.juhe.cn/docs/api/id/54
 * </p>
 *
 * @author yangwei
 * @since 2023-05-24 13:42
 */
@Slf4j
public class JuheSmsNotifyHandlerListener implements ISmsNotifyHandlerListener {

	public static final String juheChannel = "juhe";

	@Override
	public boolean support(SmsAccount smsAccount) {
		return juheChannel.equals(smsAccount.getChannel());
	}

	@Override
	public void doNotify(NotifyParam notifyParam) {

		SmsAccount smsAccount = notifyParam.getSmsParam().getSmsAccount();

		Map<String, String> params = new HashMap<>();
		params.put("mobile", notifyParam.getToUser().iterator().next());
		params.put("tpl_id", notifyParam.getThirdTemplateCode());

		params.put("tpl_value", notifyParam.getContent());
		//params.put("vars", null);

		params.put("key", smsAccount.getPassword());

		// 扩展码，9位以内的数字，具体模板支持的长度可以提前咨询客服
		//params.put("ext", null);

		//返回数据的格式,xml或json，默认json
		params.put("dtype", "json");

		String url = smsAccount.concatDomain("/sms/send");
		log.info("juhe sms send. url={},params={}",url, JsonTool.toJsonStr(params));
		try {
			String result = HttpClientTool.postForm(
					url,
					params,
					HttpClientTool.ExtConfig.builder().proxyConfig(smsAccount.getProxy()).build()
			);
			log.info("juhe sms send. result={}",result);
		} catch (IOException | URISyntaxException | ParseException e) {
			throw new RuntimeException("juhe sms send error", e);
		}


	}
}
