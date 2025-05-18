package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyRestrictHighConsumeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyRestrictHighConsumeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyRestrictHighConsume;
import com.particle.data.domain.company.DataCompanyRestrictHighConsumeId;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业限制高消费 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyRestrictHighConsumeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyRestrictHighConsumeDO>{
	public static DataCompanyRestrictHighConsumeAppStructMapping instance = Mappers.getMapper( DataCompanyRestrictHighConsumeAppStructMapping.class );

	protected Long map(DataCompanyRestrictHighConsumeId dataCompanyRestrictHighConsumeId){
		if (dataCompanyRestrictHighConsumeId == null) {
			return null;
		}
		return dataCompanyRestrictHighConsumeId.getId();
	}
	/**
	 * 企业限制高消费领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyRestrictHighConsumeAppStructMapping#map(DataCompanyRestrictHighConsumeId)}
	 * @param dataCompanyRestrictHighConsume
	 * @return
	 */
	public abstract DataCompanyRestrictHighConsumeVO toDataCompanyRestrictHighConsumeVO(DataCompanyRestrictHighConsume dataCompanyRestrictHighConsume);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyRestrictHighConsumeDO
	 * @return
	 */
	public abstract DataCompanyRestrictHighConsumeVO dataCompanyRestrictHighConsumeDOToDataCompanyRestrictHighConsumeVO(DataCompanyRestrictHighConsumeDO dataCompanyRestrictHighConsumeDO);

	/**
	 * 批量转换
	 * @param dataCompanyRestrictHighConsumeDOs
	 * @return
	 */
	public abstract List<DataCompanyRestrictHighConsumeVO> dataCompanyRestrictHighConsumeDOsToDataCompanyRestrictHighConsumeVOs(List<DataCompanyRestrictHighConsumeDO> dataCompanyRestrictHighConsumeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumeVO> infrastructurePageToPageResponse(Page<DataCompanyRestrictHighConsumeDO> page) {
		return PageResponse.of(dataCompanyRestrictHighConsumeDOsToDataCompanyRestrictHighConsumeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyRestrictHighConsumeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyRestrictHighConsumePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyRestrictHighConsumePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyRestrictHighConsumeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyRestrictHighConsumeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyRestrictHighConsumeDO pageQueryCommandToDO(DataCompanyRestrictHighConsumePageQueryCommand dataCompanyRestrictHighConsumePageQueryCommand);

	public abstract DataCompanyRestrictHighConsumeDO queryListCommandToDO(DataCompanyRestrictHighConsumeQueryListCommand dataCompanyRestrictHighConsumeQueryListCommand);
    public abstract DataCompanyRestrictHighConsumeExWarehouseVO dataCompanyRestrictHighConsumeDOToDataCompanyRestrictHighConsumeExWarehouseVO(DataCompanyRestrictHighConsumeDO dataCompanyRestrictHighConsumeDO);
    public abstract List<DataCompanyRestrictHighConsumeExWarehouseVO> dataCompanyRestrictHighConsumeDOsToDataCompanyRestrictHighConsumeExWarehouseVOs(List<DataCompanyRestrictHighConsumeDO> dataCompanyRestrictHighConsumeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyRestrictHighConsumeDO> page) {
		return PageResponse.of(dataCompanyRestrictHighConsumeDOsToDataCompanyRestrictHighConsumeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
