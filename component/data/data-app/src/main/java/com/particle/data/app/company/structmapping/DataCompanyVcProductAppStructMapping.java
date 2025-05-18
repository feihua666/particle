package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyVcProductQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;
import com.particle.data.domain.company.DataCompanyVcProduct;
import com.particle.data.domain.company.DataCompanyVcProductId;
import com.particle.data.infrastructure.company.dos.DataCompanyVcProductDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业融资产品 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyVcProductAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyVcProductDO>{
	public static DataCompanyVcProductAppStructMapping instance = Mappers.getMapper( DataCompanyVcProductAppStructMapping.class );

	protected Long map(DataCompanyVcProductId dataCompanyVcProductId){
		if (dataCompanyVcProductId == null) {
			return null;
		}
		return dataCompanyVcProductId.getId();
	}
	/**
	 * 企业融资产品领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyVcProductAppStructMapping#map(DataCompanyVcProductId)}
	 * @param dataCompanyVcProduct
	 * @return
	 */
	public abstract DataCompanyVcProductVO toDataCompanyVcProductVO(DataCompanyVcProduct dataCompanyVcProduct);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyVcProductDO
	 * @return
	 */
	public abstract DataCompanyVcProductVO dataCompanyVcProductDOToDataCompanyVcProductVO(DataCompanyVcProductDO dataCompanyVcProductDO);

	/**
	 * 批量转换
	 * @param dataCompanyVcProductDOs
	 * @return
	 */
	public abstract List<DataCompanyVcProductVO> dataCompanyVcProductDOsToDataCompanyVcProductVOs(List<DataCompanyVcProductDO> dataCompanyVcProductDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcProductVO> infrastructurePageToPageResponse(Page<DataCompanyVcProductDO> page) {
		return PageResponse.of(dataCompanyVcProductDOsToDataCompanyVcProductVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyVcProductDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyVcProductPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyVcProductPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyVcProductQueryListCommand) {
			return queryListCommandToDO(((DataCompanyVcProductQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyVcProductDO pageQueryCommandToDO(DataCompanyVcProductPageQueryCommand dataCompanyVcProductPageQueryCommand);

	public abstract DataCompanyVcProductDO queryListCommandToDO(DataCompanyVcProductQueryListCommand dataCompanyVcProductQueryListCommand);
    public abstract DataCompanyVcProductExWarehouseVO dataCompanyVcProductDOToDataCompanyVcProductExWarehouseVO(DataCompanyVcProductDO dataCompanyVcProductDO);
    public abstract List<DataCompanyVcProductExWarehouseVO> dataCompanyVcProductDOsToDataCompanyVcProductExWarehouseVOs(List<DataCompanyVcProductDO> dataCompanyVcProductDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyVcProductExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyVcProductDO> page) {
		return PageResponse.of(dataCompanyVcProductDOsToDataCompanyVcProductExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
