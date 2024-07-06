package com.particle.lowcode.infrastructure.generator.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.lowcode.domain.generator.LowcodeSegmentGen;
import com.particle.lowcode.domain.generator.LowcodeSegmentGenId;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentGenGateway;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentGenService;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentGenDO;
import com.particle.lowcode.infrastructure.generator.structmapping.LowcodeSegmentGenInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 低代码生成 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Component
public class LowcodeSegmentGenGatewayImpl extends AbstractBaseGatewayImpl<LowcodeSegmentGenId,LowcodeSegmentGen> implements LowcodeSegmentGenGateway {

	private ILowcodeSegmentGenService iLowcodeSegmentGenService;

	@Override
	public LowcodeSegmentGen getById(LowcodeSegmentGenId lowcodeSegmentGenId) {
		LowcodeSegmentGenDO byId = iLowcodeSegmentGenService.getById(lowcodeSegmentGenId.getId());
		LowcodeSegmentGen lowcodeSegmentGen = DomainFactory.create(LowcodeSegmentGen.class);
		lowcodeSegmentGen = LowcodeSegmentGenInfrastructureStructMapping.instance. lowcodeSegmentGenDOToLowcodeSegmentGen(lowcodeSegmentGen,byId);
		return lowcodeSegmentGen;
	}

	@Override
	public boolean doSave(LowcodeSegmentGen lowcodeSegmentGen) {
		LowcodeSegmentGenDO lowcodeSegmentGenDO = LowcodeSegmentGenInfrastructureStructMapping.instance.lowcodeSegmentGenToLowcodeSegmentGenDO(lowcodeSegmentGen);
		if (lowcodeSegmentGenDO.getId() == null) {
			lowcodeSegmentGenDO.setAddControl(lowcodeSegmentGen.getAddControl());
			LowcodeSegmentGenDO add = iLowcodeSegmentGenService.add(lowcodeSegmentGenDO);
			lowcodeSegmentGen.setId(LowcodeSegmentGenId.of(add.getId()));
			return add != null;
		}
		lowcodeSegmentGenDO.setUpdateControl(lowcodeSegmentGen.getUpdateControl());
		LowcodeSegmentGenDO update = iLowcodeSegmentGenService.update(lowcodeSegmentGenDO);
		return update != null;
	}

	@Override
	public boolean delete(LowcodeSegmentGenId lowcodeSegmentGenId) {
		iLowcodeSegmentGenService.assertByColumn(lowcodeSegmentGenId.getId(),LowcodeSegmentGenDO::getRefrenceSegmentGenId,false,"当前要删除的生成还有其它生成引用，不能删除");
		return iLowcodeSegmentGenService.deleteById(lowcodeSegmentGenId.getId());
	}

	@Override
	public boolean delete(LowcodeSegmentGenId id, IdCommand idCommand) {
		iLowcodeSegmentGenService.assertByColumn(id.getId(),LowcodeSegmentGenDO::getRefrenceSegmentGenId,false,"当前要删除的生成还有其它生成引用，不能删除");
		return iLowcodeSegmentGenService.deleteById(idCommand);
	}

	@Autowired
	public void setILowcodeSegmentGenService(ILowcodeSegmentGenService iLowcodeSegmentGenService) {
		this.iLowcodeSegmentGenService = iLowcodeSegmentGenService;
	}
}
