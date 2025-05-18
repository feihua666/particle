package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuotePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuoteQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentQuoteExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentQuote;
import com.particle.data.domain.company.DataCompanyIprPatentQuoteId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentQuoteDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利引证信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentQuoteAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentQuoteDO>{
	public static DataCompanyIprPatentQuoteAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentQuoteAppStructMapping.class );

	protected Long map(DataCompanyIprPatentQuoteId dataCompanyIprPatentQuoteId){
		if (dataCompanyIprPatentQuoteId == null) {
			return null;
		}
		return dataCompanyIprPatentQuoteId.getId();
	}
	/**
	 * 企业知识产权专利引证信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentQuoteAppStructMapping#map(DataCompanyIprPatentQuoteId)}
	 * @param dataCompanyIprPatentQuote
	 * @return
	 */
	public abstract DataCompanyIprPatentQuoteVO toDataCompanyIprPatentQuoteVO(DataCompanyIprPatentQuote dataCompanyIprPatentQuote);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentQuoteDO
	 * @return
	 */
	public abstract DataCompanyIprPatentQuoteVO dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuoteVO(DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentQuoteDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentQuoteVO> dataCompanyIprPatentQuoteDOsToDataCompanyIprPatentQuoteVOs(List<DataCompanyIprPatentQuoteDO> dataCompanyIprPatentQuoteDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentQuoteVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentQuoteDO> page) {
		return PageResponse.of(dataCompanyIprPatentQuoteDOsToDataCompanyIprPatentQuoteVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentQuoteDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentQuotePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentQuotePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentQuoteQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentQuoteQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentQuoteDO pageQueryCommandToDO(DataCompanyIprPatentQuotePageQueryCommand dataCompanyIprPatentQuotePageQueryCommand);

	public abstract DataCompanyIprPatentQuoteDO queryListCommandToDO(DataCompanyIprPatentQuoteQueryListCommand dataCompanyIprPatentQuoteQueryListCommand);
    public abstract DataCompanyIprPatentQuoteExWarehouseVO dataCompanyIprPatentQuoteDOToDataCompanyIprPatentQuoteExWarehouseVO(DataCompanyIprPatentQuoteDO dataCompanyIprPatentQuoteDO);
    public abstract List<DataCompanyIprPatentQuoteExWarehouseVO> dataCompanyIprPatentQuoteDOsToDataCompanyIprPatentQuoteExWarehouseVOs(List<DataCompanyIprPatentQuoteDO> dataCompanyIprPatentQuoteDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentQuoteExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentQuoteDO> page) {
		return PageResponse.of(dataCompanyIprPatentQuoteDOsToDataCompanyIprPatentQuoteExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
