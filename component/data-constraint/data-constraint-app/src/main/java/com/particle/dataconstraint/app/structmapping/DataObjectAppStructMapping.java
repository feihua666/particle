package com.particle.dataconstraint.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectPageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.dataconstraint.domain.DataObject;
import com.particle.dataconstraint.domain.DataObjectId;
import com.particle.dataconstraint.infrastructure.dos.DataObjectDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 数据对象 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataObjectAppStructMapping  implements IBaseQueryCommandMapStruct<DataObjectDO>{
	public static DataObjectAppStructMapping instance = Mappers.getMapper( DataObjectAppStructMapping.class );

	protected Long map(DataObjectId dataObjectId){
		if (dataObjectId == null) {
			return null;
		}
		return dataObjectId.getId();
	}
	/**
	 * 数据对象领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataObjectAppStructMapping#map(DataObjectId)}
	 * @param dataObject
	 * @return
	 */
	public abstract DataObjectVO toDataObjectVO(DataObject dataObject);


	/**
	 * 数据对象转视图对象
	 * @param dataObjectDO
	 * @return
	 */
	public abstract DataObjectVO dataObjectDOToDataObjectVO(DataObjectDO dataObjectDO);

	/**
	 * 批量转换
	 * @param dataObjectDOs
	 * @return
	 */
	public abstract List<DataObjectVO> dataObjectDOsToDataObjectVOs(List<DataObjectDO> dataObjectDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataObjectVO> infrastructurePageToPageResponse(Page<DataObjectDO> page) {
		return PageResponse.of(dataObjectDOsToDataObjectVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataObjectDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataObjectPageQueryCommand) {
			return pageQueryCommandToDO((DataObjectPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataObjectQueryListCommand) {
			return queryListCommandToDO(((DataObjectQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataObjectDO pageQueryCommandToDO(DataObjectPageQueryCommand dataObjectPageQueryCommand);

	public abstract DataObjectDO queryListCommandToDO(DataObjectQueryListCommand dataObjectQueryListCommand);
}
