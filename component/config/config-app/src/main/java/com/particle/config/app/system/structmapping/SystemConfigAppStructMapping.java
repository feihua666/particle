package com.particle.config.app.system.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.config.client.system.dto.command.representation.SystemConfigPageQueryCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigQueryListCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.config.domain.system.SystemConfig;
import com.particle.config.domain.system.SystemConfigId;
import com.particle.config.infrastructure.system.dos.SystemConfigDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 系统参数配置 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SystemConfigAppStructMapping  implements IBaseQueryCommandMapStruct<SystemConfigDO>{
	public static SystemConfigAppStructMapping instance = Mappers.getMapper( SystemConfigAppStructMapping.class );

	protected Long map(SystemConfigId systemConfigId){
		if (systemConfigId == null) {
			return null;
		}
		return systemConfigId.getId();
	}
	/**
	 * 系统参数配置领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SystemConfigAppStructMapping#map(SystemConfigId)}
	 * @param systemConfig
	 * @return
	 */
	public abstract SystemConfigVO toSystemConfigVO(SystemConfig systemConfig);


	/**
	 * 数据对象转视图对象
	 * @param systemConfigDO
	 * @return
	 */
	public abstract SystemConfigVO systemConfigDOToSystemConfigVO(SystemConfigDO systemConfigDO);

	/**
	 * 批量转换
	 * @param systemConfigDOs
	 * @return
	 */
	public abstract List<SystemConfigVO> systemConfigDOsToSystemConfigVOs(List<SystemConfigDO> systemConfigDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<SystemConfigVO> infrastructurePageToPageResponse(Page<SystemConfigDO> page) {
		return PageResponse.of(systemConfigDOsToSystemConfigVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public SystemConfigDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof SystemConfigPageQueryCommand) {
			return pageQueryCommandToDO((SystemConfigPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof SystemConfigQueryListCommand) {
			return queryListCommandToDO(((SystemConfigQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract SystemConfigDO pageQueryCommandToDO(SystemConfigPageQueryCommand systemConfigPageQueryCommand);

	public abstract SystemConfigDO queryListCommandToDO(SystemConfigQueryListCommand systemConfigQueryListCommand);
}
