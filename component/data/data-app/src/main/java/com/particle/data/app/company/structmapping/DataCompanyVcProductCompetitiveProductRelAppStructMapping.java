package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductCompetitiveProductRelQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseVO;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRel;
import com.particle.data.domain.company.DataCompanyVcProductCompetitiveProductRelId;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductCompetitiveProductRelDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业融资产品竞品关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcProductCompetitiveProductRelAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyVcProductCompetitiveProductRelDO>{
	public static DataCompanyVcProductCompetitiveProductRelAppStructMapping instance = Mappers.getMapper( DataCompanyVcProductCompetitiveProductRelAppStructMapping.class );

	protected Long map(DataCompanyVcProductCompetitiveProductRelId dataCompanyVcProductCompetitiveProductRelId){
		if (dataCompanyVcProductCompetitiveProductRelId == null) {
			return null;
		}
		return dataCompanyVcProductCompetitiveProductRelId.getId();
	}
	/**
	 * 企业融资产品竞品关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcProductCompetitiveProductRelAppStructMapping#map(DataCompanyVcProductCompetitiveProductRelId)}
	 * @param dataCompanyVcProductCompetitiveProductRel
	 * @return
	 */
	public abstract DataCompanyVcProductCompetitiveProductRelVO toDataCompanyVcProductCompetitiveProductRelVO(DataCompanyVcProductCompetitiveProductRel dataCompanyVcProductCompetitiveProductRel);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyVcProductCompetitiveProductRelDO
	 * @return
	 */
	public abstract DataCompanyVcProductCompetitiveProductRelVO dataCompanyVcProductCompetitiveProductRelDOToDataCompanyVcProductCompetitiveProductRelVO(DataCompanyVcProductCompetitiveProductRelDO dataCompanyVcProductCompetitiveProductRelDO);

	/**
	 * 批量转换
	 * @param dataCompanyVcProductCompetitiveProductRelDOs
	 * @return
	 */
	public abstract List<DataCompanyVcProductCompetitiveProductRelVO> dataCompanyVcProductCompetitiveProductRelDOsToDataCompanyVcProductCompetitiveProductRelVOs(List<DataCompanyVcProductCompetitiveProductRelDO> dataCompanyVcProductCompetitiveProductRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcProductCompetitiveProductRelVO> infrastructurePageToPageResponse(Page<DataCompanyVcProductCompetitiveProductRelDO> page) {
		return PageResponse.of(dataCompanyVcProductCompetitiveProductRelDOsToDataCompanyVcProductCompetitiveProductRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyVcProductCompetitiveProductRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyVcProductCompetitiveProductRelPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyVcProductCompetitiveProductRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyVcProductCompetitiveProductRelQueryListCommand) {
			return queryListCommandToDO(((DataCompanyVcProductCompetitiveProductRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyVcProductCompetitiveProductRelDO pageQueryCommandToDO(DataCompanyVcProductCompetitiveProductRelPageQueryCommand dataCompanyVcProductCompetitiveProductRelPageQueryCommand);

	public abstract DataCompanyVcProductCompetitiveProductRelDO queryListCommandToDO(DataCompanyVcProductCompetitiveProductRelQueryListCommand dataCompanyVcProductCompetitiveProductRelQueryListCommand);
    public abstract DataCompanyVcProductCompetitiveProductRelExWarehouseVO dataCompanyVcProductCompetitiveProductRelDOToDataCompanyVcProductCompetitiveProductRelExWarehouseVO(DataCompanyVcProductCompetitiveProductRelDO dataCompanyVcProductCompetitiveProductRelDO);
    public abstract List<DataCompanyVcProductCompetitiveProductRelExWarehouseVO> dataCompanyVcProductCompetitiveProductRelDOsToDataCompanyVcProductCompetitiveProductRelExWarehouseVOs(List<DataCompanyVcProductCompetitiveProductRelDO> dataCompanyVcProductCompetitiveProductRelDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcProductCompetitiveProductRelExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyVcProductCompetitiveProductRelDO> page) {
		return PageResponse.of(dataCompanyVcProductCompetitiveProductRelDOsToDataCompanyVcProductCompetitiveProductRelExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
