package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificatePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificateQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCertificateExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentCertificate;
import com.particle.data.domain.company.DataCompanyIprPatentCertificateId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCertificateDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利证书信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentCertificateAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentCertificateDO>{
	public static DataCompanyIprPatentCertificateAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentCertificateAppStructMapping.class );

	protected Long map(DataCompanyIprPatentCertificateId dataCompanyIprPatentCertificateId){
		if (dataCompanyIprPatentCertificateId == null) {
			return null;
		}
		return dataCompanyIprPatentCertificateId.getId();
	}
	/**
	 * 企业知识产权专利证书信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentCertificateAppStructMapping#map(DataCompanyIprPatentCertificateId)}
	 * @param dataCompanyIprPatentCertificate
	 * @return
	 */
	public abstract DataCompanyIprPatentCertificateVO toDataCompanyIprPatentCertificateVO(DataCompanyIprPatentCertificate dataCompanyIprPatentCertificate);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentCertificateDO
	 * @return
	 */
	public abstract DataCompanyIprPatentCertificateVO dataCompanyIprPatentCertificateDOToDataCompanyIprPatentCertificateVO(DataCompanyIprPatentCertificateDO dataCompanyIprPatentCertificateDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentCertificateDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentCertificateVO> dataCompanyIprPatentCertificateDOsToDataCompanyIprPatentCertificateVOs(List<DataCompanyIprPatentCertificateDO> dataCompanyIprPatentCertificateDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCertificateVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentCertificateDO> page) {
		return PageResponse.of(dataCompanyIprPatentCertificateDOsToDataCompanyIprPatentCertificateVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentCertificateDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentCertificatePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentCertificatePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentCertificateQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentCertificateQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentCertificateDO pageQueryCommandToDO(DataCompanyIprPatentCertificatePageQueryCommand dataCompanyIprPatentCertificatePageQueryCommand);

	public abstract DataCompanyIprPatentCertificateDO queryListCommandToDO(DataCompanyIprPatentCertificateQueryListCommand dataCompanyIprPatentCertificateQueryListCommand);
    public abstract DataCompanyIprPatentCertificateExWarehouseVO dataCompanyIprPatentCertificateDOToDataCompanyIprPatentCertificateExWarehouseVO(DataCompanyIprPatentCertificateDO dataCompanyIprPatentCertificateDO);
    public abstract List<DataCompanyIprPatentCertificateExWarehouseVO> dataCompanyIprPatentCertificateDOsToDataCompanyIprPatentCertificateExWarehouseVOs(List<DataCompanyIprPatentCertificateDO> dataCompanyIprPatentCertificateDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentCertificateExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentCertificateDO> page) {
		return PageResponse.of(dataCompanyIprPatentCertificateDOsToDataCompanyIprPatentCertificateExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
