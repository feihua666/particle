package com.particle.dict.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.dict.domain.Dict;
import com.particle.dict.domain.DictId;
import com.particle.dict.domain.gateway.DictGateway;
import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.dict.infrastructure.service.IDictService;
import com.particle.dict.infrastructure.structmapping.DictInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 字典 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
public class DictGatewayImpl extends AbstractBaseGatewayImpl<DictId,Dict> implements DictGateway {

	private IDictService iDictService;

	@Override
	public Dict getById(DictId dictId) {
		DictDO byId = iDictService.getById(dictId.getId());
		Dict dict = DomainFactory.create(Dict.class);
		dict = DictInfrastructureStructMapping.instance. dictDOToDict(dict,byId);
		return dict;
	}

	@Override
	public boolean doSave(Dict dict) {
		DictDO dictDO = DictInfrastructureStructMapping.instance.dictToDictDO(dict);
		if (dictDO.getId() == null) {
			dictDO.setAddControl(dict.getAddControl());
			DictDO add = iDictService.add(dictDO);
			dict.setId(DictId.of(add.getId()));
			return add != null;
		}
		dictDO.setUpdateControl(dict.getUpdateControl());
		DictDO update = iDictService.update(dictDO);
		return update != null;
	}

	@Override
	public boolean delete(DictId dictId) {
		return iDictService.deleteById(dictId.getId());
	}

	@Override
	public boolean delete(DictId id, IdCommand idCommand) {
		return iDictService.deleteById(idCommand);
	}

	@Autowired
	public void setIDictService(IDictService iDictService) {
		this.iDictService = iDictService;
	}
}
