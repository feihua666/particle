package com.particle.dataquery.infrastructure.gateway.impl;

import com.particle.dataquery.domain.gateway.DataQueryNotifyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 发送通知
 * </p>
 *
 * @author yangwei
 * @since 2024/1/19 16:11
 */
@Component
public class DataQueryNotifyGatewayImpl implements DataQueryNotifyGateway {

    private IDataQueryDoNotifyService iDataQueryDoNotifyService;

    @Override
    public void notifySystem(String title, String contentType, String suggest, String content) {
        if (iDataQueryDoNotifyService != null) {
            iDataQueryDoNotifyService.notifySystem(title,contentType,suggest,content);
        }
    }



    @Autowired(required = false)
    public void setiDataQueryDoNotifyService(IDataQueryDoNotifyService iDataQueryDoNotifyService) {
        this.iDataQueryDoNotifyService = iDataQueryDoNotifyService;
    }
}

