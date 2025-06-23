package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprSoftwareCopyrightPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprSoftwareCopyrightQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprSoftwareCopyrightVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyright;
import com.particle.data.domain.company.DataCompanyIprSoftwareCopyrightId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprSoftwareCopyrightDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权软件著作 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprSoftwareCopyrightAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprSoftwareCopyrightDO>{
	public static DataCompanyIprSoftwareCopyrightAppStructMapping instance = Mappers.getMapper( DataCompanyIprSoftwareCopyrightAppStructMapping.class );

	protected Long map(DataCompanyIprSoftwareCopyrightId dataCompanyIprSoftwareCopyrightId){
		if (dataCompanyIprSoftwareCopyrightId == null) {
			return null;
		}
		return dataCompanyIprSoftwareCopyrightId.getId();
	}
	/**
	 * 企业知识产权软件著作领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprSoftwareCopyrightAppStructMapping#map(DataCompanyIprSoftwareCopyrightId)}
	 * @param dataCompanyIprSoftwareCopyright
	 * @return
	 */
	public abstract DataCompanyIprSoftwareCopyrightVO toDataCompanyIprSoftwareCopyrightVO(DataCompanyIprSoftwareCopyright dataCompanyIprSoftwareCopyright);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprSoftwareCopyrightDO
	 * @return
	 */
	public abstract DataCompanyIprSoftwareCopyrightVO dataCompanyIprSoftwareCopyrightDOToDataCompanyIprSoftwareCopyrightVO(DataCompanyIprSoftwareCopyrightDO dataCompanyIprSoftwareCopyrightDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprSoftwareCopyrightDOs
	 * @return
	 */
	public abstract List<DataCompanyIprSoftwareCopyrightVO> dataCompanyIprSoftwareCopyrightDOsToDataCompanyIprSoftwareCopyrightVOs(List<DataCompanyIprSoftwareCopyrightDO> dataCompanyIprSoftwareCopyrightDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprSoftwareCopyrightVO> infrastructurePageToPageResponse(Page<DataCompanyIprSoftwareCopyrightDO> page) {
		return PageResponse.of(dataCompanyIprSoftwareCopyrightDOsToDataCompanyIprSoftwareCopyrightVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprSoftwareCopyrightDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprSoftwareCopyrightPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprSoftwareCopyrightPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprSoftwareCopyrightQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprSoftwareCopyrightQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprSoftwareCopyrightDO pageQueryCommandToDO(DataCompanyIprSoftwareCopyrightPageQueryCommand dataCompanyIprSoftwareCopyrightPageQueryCommand);

	public abstract DataCompanyIprSoftwareCopyrightDO queryListCommandToDO(DataCompanyIprSoftwareCopyrightQueryListCommand dataCompanyIprSoftwareCopyrightQueryListCommand);
    public abstract DataCompanyIprSoftwareCopyrightExWarehouseVO dataCompanyIprSoftwareCopyrightDOToDataCompanyIprSoftwareCopyrightExWarehouseVO(DataCompanyIprSoftwareCopyrightDO dataCompanyIprSoftwareCopyrightDO);
    public abstract List<DataCompanyIprSoftwareCopyrightExWarehouseVO> dataCompanyIprSoftwareCopyrightDOsToDataCompanyIprSoftwareCopyrightExWarehouseVOs(List<DataCompanyIprSoftwareCopyrightDO> dataCompanyIprSoftwareCopyrightDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprSoftwareCopyrightDO> page) {
		return PageResponse.of(dataCompanyIprSoftwareCopyrightDOsToDataCompanyIprSoftwareCopyrightExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
