package com.particle.lowcode.infrastructure.generator.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeDatasourceDO;
import com.particle.lowcode.infrastructure.generator.mapper.LowcodeDatasourceMapper;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeDatasourceService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 低代码数据源 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
public class LowcodeDatasourceServiceImpl extends IBaseServiceImpl<LowcodeDatasourceMapper, LowcodeDatasourceDO> implements ILowcodeDatasourceService {
	private IBaseQueryCommandMapStruct<LowcodeDatasourceDO> queryCommandMapStruct;

	@Override
	protected LowcodeDatasourceDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<LowcodeDatasourceDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(LowcodeDatasourceDO po) {
		// 编码已存在不能添加
		assertByColumn(po.getCode(),LowcodeDatasourceDO::getCode,false);
	}

	@Override
	protected void preUpdate(LowcodeDatasourceDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			LowcodeDatasourceDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),LowcodeDatasourceDO::getCode,false);
			}
		}
	}
}
