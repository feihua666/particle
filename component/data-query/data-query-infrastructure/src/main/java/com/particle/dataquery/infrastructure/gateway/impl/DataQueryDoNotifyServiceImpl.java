package com.particle.dataquery.infrastructure.gateway.impl;

import cn.hutool.core.util.ClassLoaderUtil;
import com.particle.global.light.share.constant.ClassAdapterConstants;

/**
 * <p>
 * 发送通知
 * </p>
 *
 * @author yangwei
 * @since 2024/1/19 16:14
 */
public class DataQueryDoNotifyServiceImpl implements IDataQueryDoNotifyService{

    public void notifySystem(String title, String contentType, String suggest, String content) {
        if (ClassLoaderUtil.isPresent(ClassAdapterConstants.NOTIFY_TOOL_CLASS_NAME)) {
            com.particle.global.notification.notify.NotifyParam notifyParam = com.particle.global.notification.notify.NotifyParam.system()
                    .setTitle(title)
                    .setContentType(contentType)
                    .setSuggest(suggest)
                    .setContent(content);
            com.particle.global.notification.notify.NotifyTool.notify(notifyParam);
        }
    }
}
