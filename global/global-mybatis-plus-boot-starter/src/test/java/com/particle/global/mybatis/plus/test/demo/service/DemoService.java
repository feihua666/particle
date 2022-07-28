package com.particle.global.mybatis.plus.test.demo.service;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.global.mybatis.plus.test.demo.DemoDO;
import com.particle.global.mybatis.plus.test.demo.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 17:07
 */
@Component
public class DemoService extends IBaseServiceImpl<DemoMapper, DemoDO> {
	@Autowired
	private IBaseQueryCommandMapStruct<DemoDO> demoQueryCommandMapStruct;

	@Override
	protected DemoDO queryCommandToDO(QueryCommand queryCommand) {
		return demoQueryCommandMapStruct.queryCommandToDO(queryCommand);

	}
}
