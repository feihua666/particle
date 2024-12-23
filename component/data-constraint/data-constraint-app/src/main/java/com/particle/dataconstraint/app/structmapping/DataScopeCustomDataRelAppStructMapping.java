package com.particle.dataconstraint.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelPageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.dataconstraint.domain.DataScopeCustomDataRel;
import com.particle.dataconstraint.domain.DataScopeCustomDataRelId;
import com.particle.dataconstraint.infrastructure.dos.DataScopeCustomDataRelDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 数据范围自定义数据关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataScopeCustomDataRelAppStructMapping  implements IBaseQueryCommandMapStruct<DataScopeCustomDataRelDO>{
	public static DataScopeCustomDataRelAppStructMapping instance = Mappers.getMapper( DataScopeCustomDataRelAppStructMapping.class );

	protected Long map(DataScopeCustomDataRelId dataScopeCustomDataRelId){
		if (dataScopeCustomDataRelId == null) {
			return null;
		}
		return dataScopeCustomDataRelId.getId();
	}
	/**
	 * 数据范围自定义数据关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataScopeCustomDataRelAppStructMapping#map(DataScopeCustomDataRelId)}
	 * @param dataScopeCustomDataRel
	 * @return
	 */
	public abstract DataScopeCustomDataRelVO toDataScopeCustomDataRelVO(DataScopeCustomDataRel dataScopeCustomDataRel);


	/**
	 * 数据对象转视图对象
	 * @param dataScopeCustomDataRelDO
	 * @return
	 */
	public abstract DataScopeCustomDataRelVO dataScopeCustomDataRelDOToDataScopeCustomDataRelVO(DataScopeCustomDataRelDO dataScopeCustomDataRelDO);

	/**
	 * 批量转换
	 * @param dataScopeCustomDataRelDOs
	 * @return
	 */
	public abstract List<DataScopeCustomDataRelVO> dataScopeCustomDataRelDOsToDataScopeCustomDataRelVOs(List<DataScopeCustomDataRelDO> dataScopeCustomDataRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataScopeCustomDataRelVO> infrastructurePageToPageResponse(Page<DataScopeCustomDataRelDO> page) {
		return PageResponse.of(dataScopeCustomDataRelDOsToDataScopeCustomDataRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataScopeCustomDataRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataScopeCustomDataRelPageQueryCommand) {
			return pageQueryCommandToDO((DataScopeCustomDataRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataScopeCustomDataRelQueryListCommand) {
			return queryListCommandToDO(((DataScopeCustomDataRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataScopeCustomDataRelDO pageQueryCommandToDO(DataScopeCustomDataRelPageQueryCommand dataScopeCustomDataRelPageQueryCommand);

	public abstract DataScopeCustomDataRelDO queryListCommandToDO(DataScopeCustomDataRelQueryListCommand dataScopeCustomDataRelQueryListCommand);
}
