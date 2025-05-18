package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.data.domain.company.DataCompanyHonorQualification;
import com.particle.data.domain.company.DataCompanyHonorQualificationId;
import com.particle.data.infrastructure.company.dos.DataCompanyHonorQualificationDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业荣誉资质 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyHonorQualificationAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyHonorQualificationDO>{
	public static DataCompanyHonorQualificationAppStructMapping instance = Mappers.getMapper( DataCompanyHonorQualificationAppStructMapping.class );

	protected Long map(DataCompanyHonorQualificationId dataCompanyHonorQualificationId){
		if (dataCompanyHonorQualificationId == null) {
			return null;
		}
		return dataCompanyHonorQualificationId.getId();
	}
	/**
	 * 企业荣誉资质领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyHonorQualificationAppStructMapping#map(DataCompanyHonorQualificationId)}
	 * @param dataCompanyHonorQualification
	 * @return
	 */
	public abstract DataCompanyHonorQualificationVO toDataCompanyHonorQualificationVO(DataCompanyHonorQualification dataCompanyHonorQualification);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyHonorQualificationDO
	 * @return
	 */
	public abstract DataCompanyHonorQualificationVO dataCompanyHonorQualificationDOToDataCompanyHonorQualificationVO(DataCompanyHonorQualificationDO dataCompanyHonorQualificationDO);

	/**
	 * 批量转换
	 * @param dataCompanyHonorQualificationDOs
	 * @return
	 */
	public abstract List<DataCompanyHonorQualificationVO> dataCompanyHonorQualificationDOsToDataCompanyHonorQualificationVOs(List<DataCompanyHonorQualificationDO> dataCompanyHonorQualificationDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyHonorQualificationVO> infrastructurePageToPageResponse(Page<DataCompanyHonorQualificationDO> page) {
		return PageResponse.of(dataCompanyHonorQualificationDOsToDataCompanyHonorQualificationVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyHonorQualificationDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyHonorQualificationPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyHonorQualificationPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyHonorQualificationQueryListCommand) {
			return queryListCommandToDO(((DataCompanyHonorQualificationQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyHonorQualificationDO pageQueryCommandToDO(DataCompanyHonorQualificationPageQueryCommand dataCompanyHonorQualificationPageQueryCommand);

	public abstract DataCompanyHonorQualificationDO queryListCommandToDO(DataCompanyHonorQualificationQueryListCommand dataCompanyHonorQualificationQueryListCommand);
    public abstract DataCompanyHonorQualificationExWarehouseVO dataCompanyHonorQualificationDOToDataCompanyHonorQualificationExWarehouseVO(DataCompanyHonorQualificationDO dataCompanyHonorQualificationDO);
    public abstract List<DataCompanyHonorQualificationExWarehouseVO> dataCompanyHonorQualificationDOsToDataCompanyHonorQualificationExWarehouseVOs(List<DataCompanyHonorQualificationDO> dataCompanyHonorQualificationDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyHonorQualificationExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyHonorQualificationDO> page) {
		return PageResponse.of(dataCompanyHonorQualificationDOsToDataCompanyHonorQualificationExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
