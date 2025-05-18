package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyQueryListCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import com.particle.data.domain.company.DataCompany;
import com.particle.data.domain.company.DataCompanyId;
import com.particle.data.infrastructure.company.dos.DataCompanyDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyDO>{
	public static DataCompanyAppStructMapping instance = Mappers.getMapper( DataCompanyAppStructMapping.class );

	protected Long map(DataCompanyId dataCompanyId){
		if (dataCompanyId == null) {
			return null;
		}
		return dataCompanyId.getId();
	}
	/**
	 * 企业领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyAppStructMapping#map(DataCompanyId)}
	 * @param dataCompany
	 * @return
	 */
	public abstract DataCompanyVO toDataCompanyVO(DataCompany dataCompany);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyDO
	 * @return
	 */
	public abstract DataCompanyVO dataCompanyDOToDataCompanyVO(DataCompanyDO dataCompanyDO);

	/**
	 * 批量转换
	 * @param dataCompanyDOs
	 * @return
	 */
	public abstract List<DataCompanyVO> dataCompanyDOsToDataCompanyVOs(List<DataCompanyDO> dataCompanyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVO> infrastructurePageToPageResponse(Page<DataCompanyDO> page) {
		return PageResponse.of(dataCompanyDOsToDataCompanyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyQueryListCommand) {
			return queryListCommandToDO(((DataCompanyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyDO pageQueryCommandToDO(DataCompanyPageQueryCommand dataCompanyPageQueryCommand);

	public abstract DataCompanyDO queryListCommandToDO(DataCompanyQueryListCommand dataCompanyQueryListCommand);


	/**
	 * 对象转换映射
	 * @param dataCompanyDO
	 * @return
	 */
	public abstract DataCompanyExWarehouseCandidateVO mappingExWarehouseCandidateVOCandidate(DataCompanyDO dataCompanyDO);
	/**
	 * 对象转换映射
	 * @param dataCompanyDO
	 * @return
	 */
	public abstract DataCompanyExWarehouseVO mappingExWarehouseVO(DataCompanyDO dataCompanyDO);
}
