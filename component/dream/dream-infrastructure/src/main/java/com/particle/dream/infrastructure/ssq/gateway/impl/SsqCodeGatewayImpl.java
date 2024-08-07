package com.particle.dream.infrastructure.ssq.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.dream.domain.ssq.SsqCode;
import com.particle.dream.domain.ssq.SsqCodeId;
import com.particle.dream.domain.ssq.gateway.SsqCodeGateway;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeDO;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeService;
import com.particle.dream.infrastructure.ssq.structmapping.SsqCodeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 双色球号码 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Component
public class SsqCodeGatewayImpl extends AbstractBaseGatewayImpl<SsqCodeId,SsqCode> implements SsqCodeGateway {

	private ISsqCodeService iSsqCodeService;

	@Override
	public SsqCode getById(SsqCodeId ssqCodeId) {
		SsqCodeDO byId = iSsqCodeService.getById(ssqCodeId.getId());
		SsqCode ssqCode = DomainFactory.create(SsqCode.class);
		ssqCode = SsqCodeInfrastructureStructMapping.instance. ssqCodeDOToSsqCode(ssqCode,byId);
		return ssqCode;
	}

	@Override
	public boolean doSave(SsqCode ssqCode) {
		SsqCodeDO ssqCodeDO = SsqCodeInfrastructureStructMapping.instance.ssqCodeToSsqCodeDO(ssqCode);
		if (ssqCodeDO.getId() == null) {
			ssqCodeDO.setAddControl(ssqCode.getAddControl());
			SsqCodeDO add = iSsqCodeService.add(ssqCodeDO);
			ssqCode.setId(SsqCodeId.of(add.getId()));
			return add != null;
		}
		ssqCodeDO.setUpdateControl(ssqCode.getUpdateControl());
		SsqCodeDO update = iSsqCodeService.update(ssqCodeDO);
		return update != null;
	}

	@Override
	public boolean delete(SsqCodeId ssqCodeId) {
		return iSsqCodeService.deleteById(ssqCodeId.getId());
	}

	@Override
	public boolean delete(SsqCodeId id, IdCommand idCommand) {
		return iSsqCodeService.deleteById(idCommand);
	}

	@Override
	public SsqCode getByBall(Integer red1, Integer red2, Integer red3, Integer red4, Integer red5, Integer red6, Integer blue) {
		SsqCodeDO ssqCodeDO = iSsqCodeService.getByBall(red1, red2, red3, red4, red5, red6, blue);
		SsqCode ssqCode = DomainFactory.create(SsqCode.class);
		ssqCode = SsqCodeInfrastructureStructMapping.instance. ssqCodeDOToSsqCode(ssqCode,ssqCodeDO);

		return ssqCode;
	}

	@Override
	public boolean addBatch(List<SsqCode> aggreateRoots) {
		List<SsqCodeDO> ssqCodeDOList = aggreateRoots.stream()
				.map(SsqCodeInfrastructureStructMapping.instance::ssqCodeToSsqCodeDO)
				.collect(Collectors.toList());
		return iSsqCodeService.saveBatch(ssqCodeDOList);
	}

	@Override
	public boolean updateBatch(List<SsqCode> aggreateRoots) {

		List<SsqCodeDO> ssqCodeDOList = aggreateRoots.stream()
				.map(SsqCodeInfrastructureStructMapping.instance::ssqCodeToSsqCodeDO)
				.collect(Collectors.toList());
		return iSsqCodeService.updateBatchById(ssqCodeDOList);
	}

	@Autowired
	public void setISsqCodeService(ISsqCodeService iSsqCodeService) {
		this.iSsqCodeService = iSsqCodeService;
	}

}
