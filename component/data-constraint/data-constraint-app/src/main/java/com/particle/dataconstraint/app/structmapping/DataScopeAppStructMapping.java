package com.particle.dataconstraint.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.dataconstraint.domain.DataScope;
import com.particle.dataconstraint.domain.DataScopeId;
import com.particle.dataconstraint.infrastructure.dos.DataScopeDO;
import com.particle.dataconstraint.client.dto.command.representation.DataScopePageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 数据范围 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataScopeAppStructMapping  implements IBaseQueryCommandMapStruct<DataScopeDO>{
	public static DataScopeAppStructMapping instance = Mappers.getMapper( DataScopeAppStructMapping.class );

	protected Long map(DataScopeId dataScopeId){
		if (dataScopeId == null) {
			return null;
		}
		return dataScopeId.getId();
	}
	/**
	 * 数据范围领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataScopeAppStructMapping#map(DataScopeId)}
	 * @param dataScope
	 * @return
	 */
	public abstract DataScopeVO toDataScopeVO(DataScope dataScope);


	/**
	 * 数据对象转视图对象
	 * @param dataScopeDO
	 * @return
	 */
	public abstract DataScopeVO dataScopeDOToDataScopeVO(DataScopeDO dataScopeDO);

	/**
	 * 批量转换
	 * @param dataScopeDOs
	 * @return
	 */
	public abstract List<DataScopeVO> dataScopeDOsToDataScopeVOs(List<DataScopeDO> dataScopeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataScopeVO> infrastructurePageToPageResponse(Page<DataScopeDO> page) {
		return PageResponse.of(dataScopeDOsToDataScopeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataScopeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataScopePageQueryCommand) {
			return pageQueryCommandToDO((DataScopePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataScopeQueryListCommand) {
			return queryListCommandToDO(((DataScopeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataScopeDO pageQueryCommandToDO(DataScopePageQueryCommand dataScopePageQueryCommand);

	public abstract DataScopeDO queryListCommandToDO(DataScopeQueryListCommand dataScopeQueryListCommand);
}
