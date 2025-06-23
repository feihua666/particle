package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprWorkCopyrightQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprWorkCopyrightVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprWorkCopyright;
import com.particle.data.domain.company.DataCompanyIprWorkCopyrightId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprWorkCopyrightDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权作品著作 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:45
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprWorkCopyrightAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprWorkCopyrightDO>{
	public static DataCompanyIprWorkCopyrightAppStructMapping instance = Mappers.getMapper( DataCompanyIprWorkCopyrightAppStructMapping.class );

	protected Long map(DataCompanyIprWorkCopyrightId dataCompanyIprWorkCopyrightId){
		if (dataCompanyIprWorkCopyrightId == null) {
			return null;
		}
		return dataCompanyIprWorkCopyrightId.getId();
	}
	/**
	 * 企业知识产权作品著作领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprWorkCopyrightAppStructMapping#map(DataCompanyIprWorkCopyrightId)}
	 * @param dataCompanyIprWorkCopyright
	 * @return
	 */
	public abstract DataCompanyIprWorkCopyrightVO toDataCompanyIprWorkCopyrightVO(DataCompanyIprWorkCopyright dataCompanyIprWorkCopyright);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprWorkCopyrightDO
	 * @return
	 */
	public abstract DataCompanyIprWorkCopyrightVO dataCompanyIprWorkCopyrightDOToDataCompanyIprWorkCopyrightVO(DataCompanyIprWorkCopyrightDO dataCompanyIprWorkCopyrightDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprWorkCopyrightDOs
	 * @return
	 */
	public abstract List<DataCompanyIprWorkCopyrightVO> dataCompanyIprWorkCopyrightDOsToDataCompanyIprWorkCopyrightVOs(List<DataCompanyIprWorkCopyrightDO> dataCompanyIprWorkCopyrightDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprWorkCopyrightVO> infrastructurePageToPageResponse(Page<DataCompanyIprWorkCopyrightDO> page) {
		return PageResponse.of(dataCompanyIprWorkCopyrightDOsToDataCompanyIprWorkCopyrightVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprWorkCopyrightDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprWorkCopyrightPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprWorkCopyrightPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprWorkCopyrightQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprWorkCopyrightQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprWorkCopyrightDO pageQueryCommandToDO(DataCompanyIprWorkCopyrightPageQueryCommand dataCompanyIprWorkCopyrightPageQueryCommand);

	public abstract DataCompanyIprWorkCopyrightDO queryListCommandToDO(DataCompanyIprWorkCopyrightQueryListCommand dataCompanyIprWorkCopyrightQueryListCommand);
    public abstract DataCompanyIprWorkCopyrightExWarehouseVO dataCompanyIprWorkCopyrightDOToDataCompanyIprWorkCopyrightExWarehouseVO(DataCompanyIprWorkCopyrightDO dataCompanyIprWorkCopyrightDO);
    public abstract List<DataCompanyIprWorkCopyrightExWarehouseVO> dataCompanyIprWorkCopyrightDOsToDataCompanyIprWorkCopyrightExWarehouseVOs(List<DataCompanyIprWorkCopyrightDO> dataCompanyIprWorkCopyrightDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprWorkCopyrightDO> page) {
		return PageResponse.of(dataCompanyIprWorkCopyrightDOsToDataCompanyIprWorkCopyrightExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
