package com.particle.data.infrastructure.dynamicdata.gateway.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.domain.dynamicdata.DynamicDataIndicator;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorGateway;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorService;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.infrastructure.dynamicdata.structmapping.DynamicDataIndicatorInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import com.particle.global.mybatis.plus.table.TableServivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 动态数据指标 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Component
public class DynamicDataIndicatorGatewayImpl extends AbstractBaseGatewayImpl<DynamicDataIndicatorId,DynamicDataIndicator> implements DynamicDataIndicatorGateway {

    private IDynamicDataIndicatorService iDynamicDataIndicatorService;

    private TableServivce tableServivce;
    @Override
    public DynamicDataIndicator getById(DynamicDataIndicatorId dynamicDataIndicatorId) {
        DynamicDataIndicatorDO byId = iDynamicDataIndicatorService.getById(dynamicDataIndicatorId.getId());
        DynamicDataIndicator dynamicDataIndicator = DomainFactory.create(DynamicDataIndicator.class);
        dynamicDataIndicator = DynamicDataIndicatorInfrastructureStructMapping.instance. dynamicDataIndicatorDOToDynamicDataIndicator(dynamicDataIndicator,byId);
        return dynamicDataIndicator;
    }

    @Override
    public boolean doSave(DynamicDataIndicator dynamicDataIndicator) {
        DynamicDataIndicatorDO dynamicDataIndicatorDO = DynamicDataIndicatorInfrastructureStructMapping.instance.dynamicDataIndicatorToDynamicDataIndicatorDO(dynamicDataIndicator);
        if (dynamicDataIndicatorDO.getId() == null) {
            dynamicDataIndicatorDO.setAddControl(dynamicDataIndicator.getAddControl());
            DynamicDataIndicatorDO add = iDynamicDataIndicatorService.add(dynamicDataIndicatorDO);
            dynamicDataIndicator.setId(DynamicDataIndicatorId.of(add.getId()));
            return add != null;
        }
        dynamicDataIndicatorDO.setUpdateControl(dynamicDataIndicator.getUpdateControl());
        DynamicDataIndicatorDO update = iDynamicDataIndicatorService.update(dynamicDataIndicatorDO);
        return update != null;
    }

    @Override
    public boolean delete(DynamicDataIndicatorId dynamicDataIndicatorId) {
        return iDynamicDataIndicatorService.deleteById(dynamicDataIndicatorId.getId());
    }

    @Override
    public boolean delete(DynamicDataIndicatorId dynamicDataIndicatorId, IdCommand idCommand) {
        return iDynamicDataIndicatorService.deleteById(idCommand);
    }
    @Override
    public void addColumn(String tableName, String columnName, String columnType, boolean isRequired, String defaultValue, String comment) {
        // mysql在执行ddl时会隐式提交当前事件，如果ddl没有执行成功，前面的数据不会回滚
        if (!tableServivce.isExistColumn(tableName,columnName)) {
            tableServivce.addColumn(tableName,columnName,columnType,isRequired, StrUtil.emptyToNull(defaultValue),comment);
        }
    }

    @Override
    public void dropColumn(String tableName, String columnName) {
        // mysql在执行ddl时会隐式提交当前事件，如果ddl没有执行成功，前面的数据不会回滚
        if (tableServivce.isExistColumn(tableName,columnName)) {
            tableServivce.dropColumn(tableName,columnName);
        }
    }

    @Override
    public boolean isExistColumn(String tableName, String columnName) {
        return tableServivce.isExistColumn(tableName,columnName);
    }
    @Autowired
    public void setIDynamicDataIndicatorService(IDynamicDataIndicatorService iDynamicDataIndicatorService) {
        this.iDynamicDataIndicatorService = iDynamicDataIndicatorService;
    }
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }

}
