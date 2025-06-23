package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransfer;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权商标转让信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkTransferAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprTrademarkTransferDO>{
	public static DataCompanyIprTrademarkTransferAppStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkTransferAppStructMapping.class );

	protected Long map(DataCompanyIprTrademarkTransferId dataCompanyIprTrademarkTransferId){
		if (dataCompanyIprTrademarkTransferId == null) {
			return null;
		}
		return dataCompanyIprTrademarkTransferId.getId();
	}
	/**
	 * 企业知识产权商标转让信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkTransferAppStructMapping#map(DataCompanyIprTrademarkTransferId)}
	 * @param dataCompanyIprTrademarkTransfer
	 * @return
	 */
	public abstract DataCompanyIprTrademarkTransferVO toDataCompanyIprTrademarkTransferVO(DataCompanyIprTrademarkTransfer dataCompanyIprTrademarkTransfer);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprTrademarkTransferDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkTransferVO dataCompanyIprTrademarkTransferDOToDataCompanyIprTrademarkTransferVO(DataCompanyIprTrademarkTransferDO dataCompanyIprTrademarkTransferDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprTrademarkTransferDOs
	 * @return
	 */
	public abstract List<DataCompanyIprTrademarkTransferVO> dataCompanyIprTrademarkTransferDOsToDataCompanyIprTrademarkTransferVOs(List<DataCompanyIprTrademarkTransferDO> dataCompanyIprTrademarkTransferDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferVO> infrastructurePageToPageResponse(Page<DataCompanyIprTrademarkTransferDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkTransferDOsToDataCompanyIprTrademarkTransferVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprTrademarkTransferDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprTrademarkTransferPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprTrademarkTransferPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprTrademarkTransferQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprTrademarkTransferQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprTrademarkTransferDO pageQueryCommandToDO(DataCompanyIprTrademarkTransferPageQueryCommand dataCompanyIprTrademarkTransferPageQueryCommand);

	public abstract DataCompanyIprTrademarkTransferDO queryListCommandToDO(DataCompanyIprTrademarkTransferQueryListCommand dataCompanyIprTrademarkTransferQueryListCommand);
    public abstract DataCompanyIprTrademarkTransferExWarehouseVO dataCompanyIprTrademarkTransferDOToDataCompanyIprTrademarkTransferExWarehouseVO(DataCompanyIprTrademarkTransferDO dataCompanyIprTrademarkTransferDO);
    public abstract List<DataCompanyIprTrademarkTransferExWarehouseVO> dataCompanyIprTrademarkTransferDOsToDataCompanyIprTrademarkTransferExWarehouseVOs(List<DataCompanyIprTrademarkTransferDO> dataCompanyIprTrademarkTransferDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprTrademarkTransferDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkTransferDOsToDataCompanyIprTrademarkTransferExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
