package com.particle.global.mybatis.plus.test.demo.mapstruct;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.global.mybatis.plus.test.demo.DemoDO;
import com.particle.global.mybatis.plus.test.demo.DemoPageQueryCommand;
import com.particle.global.mybatis.plus.test.demo.DemoQueryCommand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 17:09
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DemoQueryCommandMapStruct implements IBaseQueryCommandMapStruct<DemoDO> {

	@Override
	public DemoDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DemoPageQueryCommand) {
			return pageQueryCommandToDO((DemoPageQueryCommand) queryCommand);
		}
		return QueryListCommandToDO(((DemoQueryCommand) queryCommand));
	}

	public abstract DemoDO pageQueryCommandToDO(DemoPageQueryCommand demoPageQueryCommand);

	public abstract DemoDO QueryListCommandToDO(DemoQueryCommand demoQueryCommand);
}
