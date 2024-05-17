package com.particle.dream.infrastructure.ssq.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import com.particle.dream.infrastructure.ssq.mapper.SsqCodeOpenedMapper;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeOpenedService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 双色球开奖 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Component
public class SsqCodeOpenedServiceImpl extends IBaseServiceImpl<SsqCodeOpenedMapper, SsqCodeOpenedDO> implements ISsqCodeOpenedService {
	private IBaseQueryCommandMapStruct<SsqCodeOpenedDO> queryCommandMapStruct;

	@Override
	protected SsqCodeOpenedDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<SsqCodeOpenedDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(SsqCodeOpenedDO po) {
	}

	@Override
	protected void preUpdate(SsqCodeOpenedDO po) {
    
	}
}
