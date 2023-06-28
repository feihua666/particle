package com.particle.global.wxjava.cp.config;

import com.google.common.collect.Maps;
import com.particle.global.wxjava.cp.handler.*;
import lombok.val;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import me.chanjar.weixin.cp.constant.WxCpConsts;
import me.chanjar.weixin.cp.message.WxCpMessageRouter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业微信配置，支持多个企业微信
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 14:40:51
 */
public class WxCpConfiguration implements InitializingBean {

    @Autowired
    private LogHandler logHandler;
    @Autowired
    private MenuClickHandler menuClickHandler;
    @Autowired
    private LocationHandler locationHandler;
    @Autowired
    private MenuHandler menuHandler;
    @Autowired
    private MsgHandler msgHandler;
    @Autowired
    private ContactChangeHandler contactChangeHandler;
    @Autowired
    private UnsubscribeHandler unsubscribeHandler;
    @Autowired
    private SubscribeHandler subscribeHandler;
    @Autowired
    private ScanHandler scanHandler;
    @Autowired
    private EnterAgentHandler enterAgentHandler;
    @Autowired
    private WxCpProperties properties;

    private static Map<String, WxCpMessageRouter> routers = Maps.newHashMap();
    private static Map<String, WxCpService> cpServices = Maps.newHashMap();


    public static Map<String, WxCpMessageRouter> getRouters() {
        return routers;
    }

    public static WxCpService getCpService(String corpId, Integer agentId) {
        WxCpService cpService = cpServices.get(corpId + agentId);
        return Optional.ofNullable(cpService).orElseThrow(() -> new WxRuntimeException("未配置此service"));
    }

    /**
     * 初始化
     */
    public void initServices() {
        cpServices = this.properties.getAppConfigs().stream().map(a -> {

            WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
            config.setCorpId(a.getCorpId());
            config.setAgentId(a.getAgentId());
            config.setCorpSecret(a.getSecret());
            config.setToken(a.getToken());
            config.setAesKey(a.getAesKey());

            val service = new WxCpServiceImpl();
            service.setWxCpConfigStorage(config);

            routers.put(a.getCorpId() + a.getAgentId(), this.newRouter(service));
            return service;
        }).collect(Collectors.toMap(service -> service.getWxCpConfigStorage().getCorpId() + service.getWxCpConfigStorage().getAgentId(), a -> a));
    }

    private WxCpMessageRouter newRouter(WxCpService wxCpService) {
        final val newRouter = new WxCpMessageRouter(wxCpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.logHandler).next();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.MenuButtonType.CLICK).handler(this.menuHandler).end();

        // 点击菜单链接事件（这里使用了一个空的处理器，可以根据自己需要进行扩展）
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.MenuButtonType.VIEW).handler(this.menuClickHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.SUBSCRIBE).handler(this.subscribeHandler)
                .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.UNSUBSCRIBE)
                .handler(this.unsubscribeHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.LOCATION).handler(this.locationHandler)
                .end();

        // 接收地理位置消息
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.LOCATION)
                .handler(this.locationHandler).end();

        // 扫码事件（这里使用了一个空的处理器，可以根据自己需要进行扩展）
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxConsts.EventType.SCAN).handler(this.scanHandler).end();

        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxCpConsts.EventType.CHANGE_CONTACT).handler(this.contactChangeHandler).end();

        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
                .event(WxCpConsts.EventType.ENTER_AGENT).handler(enterAgentHandler).end();

        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();

        return newRouter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initServices();
    }
}
