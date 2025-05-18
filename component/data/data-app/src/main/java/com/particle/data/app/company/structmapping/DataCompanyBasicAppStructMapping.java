package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyBasicQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.data.domain.company.DataCompanyBasic;
import com.particle.data.domain.company.DataCompanyBasicId;
import com.particle.data.infrastructure.company.dos.DataCompanyBasicDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业基本信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyBasicAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyBasicDO>{
	public static DataCompanyBasicAppStructMapping instance = Mappers.getMapper( DataCompanyBasicAppStructMapping.class );

	protected Long map(DataCompanyBasicId dataCompanyBasicId){
		if (dataCompanyBasicId == null) {
			return null;
		}
		return dataCompanyBasicId.getId();
	}
	/**
	 * 企业基本信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyBasicAppStructMapping#map(DataCompanyBasicId)}
	 * @param dataCompanyBasic
	 * @return
	 */
	public abstract DataCompanyBasicVO toDataCompanyBasicVO(DataCompanyBasic dataCompanyBasic);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyBasicDO
	 * @return
	 */
	public abstract DataCompanyBasicVO dataCompanyBasicDOToDataCompanyBasicVO(DataCompanyBasicDO dataCompanyBasicDO);

	/**
	 * 批量转换
	 * @param dataCompanyBasicDOs
	 * @return
	 */
	public abstract List<DataCompanyBasicVO> dataCompanyBasicDOsToDataCompanyBasicVOs(List<DataCompanyBasicDO> dataCompanyBasicDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyBasicVO> infrastructurePageToPageResponse(Page<DataCompanyBasicDO> page) {
		return PageResponse.of(dataCompanyBasicDOsToDataCompanyBasicVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyBasicDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyBasicPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyBasicPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyBasicQueryListCommand) {
			return queryListCommandToDO(((DataCompanyBasicQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyBasicDO pageQueryCommandToDO(DataCompanyBasicPageQueryCommand dataCompanyBasicPageQueryCommand);

	public abstract DataCompanyBasicDO queryListCommandToDO(DataCompanyBasicQueryListCommand dataCompanyBasicQueryListCommand);

	public abstract DataCompanyBasicExWarehouseVO dataCompanyBasicDOToDataCompanyBasicExWarehouseVO(DataCompanyBasicDO dataCompanyBasicDO);

	public abstract List<DataCompanyBasicExWarehouseVO> dataCompanyBasicDOsToDataCompanyBasicExWarehouseVOs(List<DataCompanyBasicDO> dataCompanyBasicDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyBasicExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyBasicDO> page) {
		return PageResponse.of(dataCompanyBasicDOsToDataCompanyBasicExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
