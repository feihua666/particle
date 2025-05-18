package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPaymentExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentPayment;
import com.particle.data.domain.company.DataCompanyIprPatentPaymentId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPaymentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利缴费信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentPaymentAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentPaymentDO>{
	public static DataCompanyIprPatentPaymentAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentPaymentAppStructMapping.class );

	protected Long map(DataCompanyIprPatentPaymentId dataCompanyIprPatentPaymentId){
		if (dataCompanyIprPatentPaymentId == null) {
			return null;
		}
		return dataCompanyIprPatentPaymentId.getId();
	}
	/**
	 * 企业知识产权专利缴费信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentPaymentAppStructMapping#map(DataCompanyIprPatentPaymentId)}
	 * @param dataCompanyIprPatentPayment
	 * @return
	 */
	public abstract DataCompanyIprPatentPaymentVO toDataCompanyIprPatentPaymentVO(DataCompanyIprPatentPayment dataCompanyIprPatentPayment);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentPaymentDO
	 * @return
	 */
	public abstract DataCompanyIprPatentPaymentVO dataCompanyIprPatentPaymentDOToDataCompanyIprPatentPaymentVO(DataCompanyIprPatentPaymentDO dataCompanyIprPatentPaymentDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentPaymentDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentPaymentVO> dataCompanyIprPatentPaymentDOsToDataCompanyIprPatentPaymentVOs(List<DataCompanyIprPatentPaymentDO> dataCompanyIprPatentPaymentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPaymentVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentPaymentDO> page) {
		return PageResponse.of(dataCompanyIprPatentPaymentDOsToDataCompanyIprPatentPaymentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentPaymentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentPaymentPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentPaymentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentPaymentQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentPaymentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentPaymentDO pageQueryCommandToDO(DataCompanyIprPatentPaymentPageQueryCommand dataCompanyIprPatentPaymentPageQueryCommand);

	public abstract DataCompanyIprPatentPaymentDO queryListCommandToDO(DataCompanyIprPatentPaymentQueryListCommand dataCompanyIprPatentPaymentQueryListCommand);
    public abstract DataCompanyIprPatentPaymentExWarehouseVO dataCompanyIprPatentPaymentDOToDataCompanyIprPatentPaymentExWarehouseVO(DataCompanyIprPatentPaymentDO dataCompanyIprPatentPaymentDO);
    public abstract List<DataCompanyIprPatentPaymentExWarehouseVO> dataCompanyIprPatentPaymentDOsToDataCompanyIprPatentPaymentExWarehouseVOs(List<DataCompanyIprPatentPaymentDO> dataCompanyIprPatentPaymentDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPaymentExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentPaymentDO> page) {
		return PageResponse.of(dataCompanyIprPatentPaymentDOsToDataCompanyIprPatentPaymentExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
