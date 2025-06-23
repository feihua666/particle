package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkTransferPersonQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkTransferPersonVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkTransferPersonExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkTransferPersonId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkTransferPersonDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权商标转让人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkTransferPersonAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprTrademarkTransferPersonDO>{
	public static DataCompanyIprTrademarkTransferPersonAppStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkTransferPersonAppStructMapping.class );

	protected Long map(DataCompanyIprTrademarkTransferPersonId dataCompanyIprTrademarkTransferPersonId){
		if (dataCompanyIprTrademarkTransferPersonId == null) {
			return null;
		}
		return dataCompanyIprTrademarkTransferPersonId.getId();
	}
	/**
	 * 企业知识产权商标转让人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkTransferPersonAppStructMapping#map(DataCompanyIprTrademarkTransferPersonId)}
	 * @param dataCompanyIprTrademarkTransferPerson
	 * @return
	 */
	public abstract DataCompanyIprTrademarkTransferPersonVO toDataCompanyIprTrademarkTransferPersonVO(DataCompanyIprTrademarkTransferPerson dataCompanyIprTrademarkTransferPerson);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprTrademarkTransferPersonDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkTransferPersonVO dataCompanyIprTrademarkTransferPersonDOToDataCompanyIprTrademarkTransferPersonVO(DataCompanyIprTrademarkTransferPersonDO dataCompanyIprTrademarkTransferPersonDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprTrademarkTransferPersonDOs
	 * @return
	 */
	public abstract List<DataCompanyIprTrademarkTransferPersonVO> dataCompanyIprTrademarkTransferPersonDOsToDataCompanyIprTrademarkTransferPersonVOs(List<DataCompanyIprTrademarkTransferPersonDO> dataCompanyIprTrademarkTransferPersonDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferPersonVO> infrastructurePageToPageResponse(Page<DataCompanyIprTrademarkTransferPersonDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkTransferPersonDOsToDataCompanyIprTrademarkTransferPersonVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprTrademarkTransferPersonDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprTrademarkTransferPersonPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprTrademarkTransferPersonPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprTrademarkTransferPersonQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprTrademarkTransferPersonQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprTrademarkTransferPersonDO pageQueryCommandToDO(DataCompanyIprTrademarkTransferPersonPageQueryCommand dataCompanyIprTrademarkTransferPersonPageQueryCommand);

	public abstract DataCompanyIprTrademarkTransferPersonDO queryListCommandToDO(DataCompanyIprTrademarkTransferPersonQueryListCommand dataCompanyIprTrademarkTransferPersonQueryListCommand);
    public abstract DataCompanyIprTrademarkTransferPersonExWarehouseVO dataCompanyIprTrademarkTransferPersonDOToDataCompanyIprTrademarkTransferPersonExWarehouseVO(DataCompanyIprTrademarkTransferPersonDO dataCompanyIprTrademarkTransferPersonDO);
    public abstract List<DataCompanyIprTrademarkTransferPersonExWarehouseVO> dataCompanyIprTrademarkTransferPersonDOsToDataCompanyIprTrademarkTransferPersonExWarehouseVOs(List<DataCompanyIprTrademarkTransferPersonDO> dataCompanyIprTrademarkTransferPersonDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkTransferPersonExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprTrademarkTransferPersonDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkTransferPersonDOsToDataCompanyIprTrademarkTransferPersonExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
