package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentTransferExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentTransfer;
import com.particle.data.domain.company.DataCompanyIprPatentTransferId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentTransferDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利转让信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentTransferAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentTransferDO>{
	public static DataCompanyIprPatentTransferAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentTransferAppStructMapping.class );

	protected Long map(DataCompanyIprPatentTransferId dataCompanyIprPatentTransferId){
		if (dataCompanyIprPatentTransferId == null) {
			return null;
		}
		return dataCompanyIprPatentTransferId.getId();
	}
	/**
	 * 企业知识产权专利转让信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentTransferAppStructMapping#map(DataCompanyIprPatentTransferId)}
	 * @param dataCompanyIprPatentTransfer
	 * @return
	 */
	public abstract DataCompanyIprPatentTransferVO toDataCompanyIprPatentTransferVO(DataCompanyIprPatentTransfer dataCompanyIprPatentTransfer);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentTransferDO
	 * @return
	 */
	public abstract DataCompanyIprPatentTransferVO dataCompanyIprPatentTransferDOToDataCompanyIprPatentTransferVO(DataCompanyIprPatentTransferDO dataCompanyIprPatentTransferDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentTransferDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentTransferVO> dataCompanyIprPatentTransferDOsToDataCompanyIprPatentTransferVOs(List<DataCompanyIprPatentTransferDO> dataCompanyIprPatentTransferDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentTransferVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentTransferDO> page) {
		return PageResponse.of(dataCompanyIprPatentTransferDOsToDataCompanyIprPatentTransferVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentTransferDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentTransferPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentTransferPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentTransferQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentTransferQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentTransferDO pageQueryCommandToDO(DataCompanyIprPatentTransferPageQueryCommand dataCompanyIprPatentTransferPageQueryCommand);

	public abstract DataCompanyIprPatentTransferDO queryListCommandToDO(DataCompanyIprPatentTransferQueryListCommand dataCompanyIprPatentTransferQueryListCommand);
    public abstract DataCompanyIprPatentTransferExWarehouseVO dataCompanyIprPatentTransferDOToDataCompanyIprPatentTransferExWarehouseVO(DataCompanyIprPatentTransferDO dataCompanyIprPatentTransferDO);
    public abstract List<DataCompanyIprPatentTransferExWarehouseVO> dataCompanyIprPatentTransferDOsToDataCompanyIprPatentTransferExWarehouseVOs(List<DataCompanyIprPatentTransferDO> dataCompanyIprPatentTransferDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentTransferExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentTransferDO> page) {
		return PageResponse.of(dataCompanyIprPatentTransferDOsToDataCompanyIprPatentTransferExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}

}
