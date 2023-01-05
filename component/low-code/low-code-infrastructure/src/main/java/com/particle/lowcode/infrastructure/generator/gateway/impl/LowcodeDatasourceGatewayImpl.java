package com.particle.lowcode.infrastructure.generator.gateway.impl;

import com.particle.lowcode.domain.generator.LowcodeDatasource;
import com.particle.lowcode.domain.generator.LowcodeDatasourceId;
import com.particle.lowcode.domain.generator.gateway.LowcodeDatasourceGateway;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeDatasourceService;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeDatasourceDO;
import com.particle.lowcode.infrastructure.generator.structmapping.LowcodeDatasourceInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 低代码数据源 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
public class LowcodeDatasourceGatewayImpl extends AbstractBaseGatewayImpl<LowcodeDatasourceId,LowcodeDatasource> implements LowcodeDatasourceGateway {

	private ILowcodeDatasourceService iLowcodeDatasourceService;

	@Override
	public LowcodeDatasource getById(LowcodeDatasourceId lowcodeDatasourceId) {
		LowcodeDatasourceDO byId = iLowcodeDatasourceService.getById(lowcodeDatasourceId.getId());
		LowcodeDatasource lowcodeDatasource = DomainFactory.create(LowcodeDatasource.class);
		lowcodeDatasource = LowcodeDatasourceInfrastructureStructMapping.instance. lowcodeDatasourceDOToLowcodeDatasource(lowcodeDatasource,byId);
		return lowcodeDatasource;
	}

	@Override
	public boolean doSave(LowcodeDatasource lowcodeDatasource) {
		LowcodeDatasourceDO lowcodeDatasourceDO = LowcodeDatasourceInfrastructureStructMapping.instance.lowcodeDatasourceToLowcodeDatasourceDO(lowcodeDatasource);
		if (lowcodeDatasourceDO.getId() == null) {
			lowcodeDatasourceDO.setAddControl(lowcodeDatasource.getAddControl());
			LowcodeDatasourceDO add = iLowcodeDatasourceService.add(lowcodeDatasourceDO);
			lowcodeDatasource.setId(LowcodeDatasourceId.of(add.getId()));
			return add != null;
		}
		lowcodeDatasourceDO.setUpdateControl(lowcodeDatasource.getUpdateControl());
		LowcodeDatasourceDO update = iLowcodeDatasourceService.update(lowcodeDatasourceDO);
		return update != null;
	}

	@Override
	public boolean delete(LowcodeDatasourceId lowcodeDatasourceId) {
		return iLowcodeDatasourceService.deleteById(lowcodeDatasourceId.getId());
	}


	@Autowired
	public void setILowcodeDatasourceService(ILowcodeDatasourceService iLowcodeDatasourceService) {
		this.iLowcodeDatasourceService = iLowcodeDatasourceService;
	}
}
