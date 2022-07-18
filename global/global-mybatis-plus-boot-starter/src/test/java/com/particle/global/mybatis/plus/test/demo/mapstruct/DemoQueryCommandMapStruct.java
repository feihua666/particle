package com.particle.global.mybatis.plus.test.demo.mapstruct;

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
public interface DemoQueryCommandMapStruct extends IBaseQueryCommandMapStruct<DemoDO, DemoQueryCommand,DemoPageQueryCommand> {
}
