package com.particle.global.notification.alert;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.global.actuator.monitor.MonitorTool;
import com.particle.global.notification.GlobalNotificationProperties;
import com.particle.global.notification.notify.AbstractNotifyListener;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.tool.proxy.ProxyConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpGroupRobotService;
import me.chanjar.weixin.cp.api.impl.WxCpGroupRobotServiceImpl;
import me.chanjar.weixin.cp.api.impl.WxCpServiceApacheHttpClientImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 报警通知
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 15:54
 */
@Slf4j
public class AlertNotifyListener  extends AbstractNotifyListener {
	@Autowired
	private GlobalNotificationProperties globalNotificationProperties;

	private WxCpGroupRobotService  wxCpGroupRobotService;

	@Override
	protected boolean support(NotifyParam param) {
		if (StrUtil.isEmpty(param.getTypes())) {
			return true;
		}
		return super.support(param);

	}

	@Override
	protected String supportType() {
		return NotifyParam.Type.alert.name();
	}

	@Override
	public void doNotify(NotifyParam notifyParam) {
		AlertAccount alertAccount = globalNotificationProperties.getAlert();
		if (alertAccount != null) {
			ProxyConfig proxyConfig = ProxyConfig.finalProxyConfig(alertAccount.getProxy());
			doWxcpWebhooks(alertAccount.getWxcpWebhooks(), notifyParam,proxyConfig);
		}else {
			log.debug("alert notify ignored. because of alertAccount is null");
		}
	}

	/**
	 * 执行企业微信webhook
	 * @param webhooks
	 */
	private void doWxcpWebhooks(List<String> webhooks, NotifyParam notifyParam, ProxyConfig proxyConfig) {
		if (CollectionUtil.isEmpty(webhooks)) {
			log.debug("wxcp webhooks is empty. ignored");
			return;
		}
		if (wxCpGroupRobotService == null) {
			WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
			if (proxyConfig != null) {
				config.setHttpProxyHost(proxyConfig.getProxyAddress());
				config.setHttpProxyPort(Integer.parseInt(proxyConfig.getProxyPort()));
				config.setHttpProxyUsername(proxyConfig.getProxyUsername());
				config.setHttpProxyPassword(proxyConfig.getProxyPassword());
			}

			WxCpServiceApacheHttpClientImpl wxCpServiceApacheHttpClient = new WxCpServiceApacheHttpClientImpl();
			wxCpServiceApacheHttpClient.setWxCpConfigStorage(config);
			wxCpServiceApacheHttpClient.initHttp();
			wxCpGroupRobotService = new WxCpGroupRobotServiceImpl(wxCpServiceApacheHttpClient);
		}
		for (String webhook : webhooks) {
			// 获取格式化内容并换行展示
			String content = formatParam(notifyParam).stream().collect(Collectors.joining("\n"));
			try {
				wxCpGroupRobotService.sendText(webhook,content,null,null);
			} catch (WxErrorException e) {
				log.error("企业微信通知异常，type={},content={}",supportType(),content,e);
				MonitorTool.count("notify.exception","企业微信通知异常","supportType",supportType());
			}
		}
	}



	public void setWxCpGroupRobotService(WxCpGroupRobotService wxCpGroupRobotService) {
		this.wxCpGroupRobotService = wxCpGroupRobotService;
	}
}
