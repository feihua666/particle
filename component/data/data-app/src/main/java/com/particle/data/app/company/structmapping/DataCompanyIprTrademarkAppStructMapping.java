package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprTrademarkQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprTrademark;
import com.particle.data.domain.company.DataCompanyIprTrademarkId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权商标 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprTrademarkAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprTrademarkDO>{
	public static DataCompanyIprTrademarkAppStructMapping instance = Mappers.getMapper( DataCompanyIprTrademarkAppStructMapping.class );

	protected Long map(DataCompanyIprTrademarkId dataCompanyIprTrademarkId){
		if (dataCompanyIprTrademarkId == null) {
			return null;
		}
		return dataCompanyIprTrademarkId.getId();
	}
	/**
	 * 企业知识产权商标领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprTrademarkAppStructMapping#map(DataCompanyIprTrademarkId)}
	 * @param dataCompanyIprTrademark
	 * @return
	 */
	public abstract DataCompanyIprTrademarkVO toDataCompanyIprTrademarkVO(DataCompanyIprTrademark dataCompanyIprTrademark);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprTrademarkDO
	 * @return
	 */
	public abstract DataCompanyIprTrademarkVO dataCompanyIprTrademarkDOToDataCompanyIprTrademarkVO(DataCompanyIprTrademarkDO dataCompanyIprTrademarkDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprTrademarkDOs
	 * @return
	 */
	public abstract List<DataCompanyIprTrademarkVO> dataCompanyIprTrademarkDOsToDataCompanyIprTrademarkVOs(List<DataCompanyIprTrademarkDO> dataCompanyIprTrademarkDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkVO> infrastructurePageToPageResponse(Page<DataCompanyIprTrademarkDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkDOsToDataCompanyIprTrademarkVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprTrademarkDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprTrademarkPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprTrademarkPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprTrademarkQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprTrademarkQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprTrademarkDO pageQueryCommandToDO(DataCompanyIprTrademarkPageQueryCommand dataCompanyIprTrademarkPageQueryCommand);

	public abstract DataCompanyIprTrademarkDO queryListCommandToDO(DataCompanyIprTrademarkQueryListCommand dataCompanyIprTrademarkQueryListCommand);
    public abstract DataCompanyIprTrademarkExWarehouseVO dataCompanyIprTrademarkDOToDataCompanyIprTrademarkExWarehouseVO(DataCompanyIprTrademarkDO dataCompanyIprTrademarkDO);
    public abstract List<DataCompanyIprTrademarkExWarehouseVO> dataCompanyIprTrademarkDOsToDataCompanyIprTrademarkExWarehouseVOs(List<DataCompanyIprTrademarkDO> dataCompanyIprTrademarkDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprTrademarkDO> page) {
		return PageResponse.of(dataCompanyIprTrademarkDOsToDataCompanyIprTrademarkExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
