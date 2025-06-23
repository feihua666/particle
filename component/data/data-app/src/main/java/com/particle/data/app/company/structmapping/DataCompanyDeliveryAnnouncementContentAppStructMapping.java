package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseVO;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContent;
import com.particle.data.domain.company.DataCompanyDeliveryAnnouncementContentId;
import com.particle.data.infrastructure.company.dos.DataCompanyDeliveryAnnouncementContentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业送达公告内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyDeliveryAnnouncementContentAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyDeliveryAnnouncementContentDO>{
	public static DataCompanyDeliveryAnnouncementContentAppStructMapping instance = Mappers.getMapper( DataCompanyDeliveryAnnouncementContentAppStructMapping.class );

	protected Long map(DataCompanyDeliveryAnnouncementContentId dataCompanyDeliveryAnnouncementContentId){
		if (dataCompanyDeliveryAnnouncementContentId == null) {
			return null;
		}
		return dataCompanyDeliveryAnnouncementContentId.getId();
	}
	/**
	 * 企业送达公告内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDeliveryAnnouncementContentAppStructMapping#map(DataCompanyDeliveryAnnouncementContentId)}
	 * @param dataCompanyDeliveryAnnouncementContent
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementContentVO toDataCompanyDeliveryAnnouncementContentVO(DataCompanyDeliveryAnnouncementContent dataCompanyDeliveryAnnouncementContent);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyDeliveryAnnouncementContentDO
	 * @return
	 */
	public abstract DataCompanyDeliveryAnnouncementContentVO dataCompanyDeliveryAnnouncementContentDOToDataCompanyDeliveryAnnouncementContentVO(DataCompanyDeliveryAnnouncementContentDO dataCompanyDeliveryAnnouncementContentDO);

	/**
	 * 批量转换
	 * @param dataCompanyDeliveryAnnouncementContentDOs
	 * @return
	 */
	public abstract List<DataCompanyDeliveryAnnouncementContentVO> dataCompanyDeliveryAnnouncementContentDOsToDataCompanyDeliveryAnnouncementContentVOs(List<DataCompanyDeliveryAnnouncementContentDO> dataCompanyDeliveryAnnouncementContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementContentVO> infrastructurePageToPageResponse(Page<DataCompanyDeliveryAnnouncementContentDO> page) {
		return PageResponse.of(dataCompanyDeliveryAnnouncementContentDOsToDataCompanyDeliveryAnnouncementContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyDeliveryAnnouncementContentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyDeliveryAnnouncementContentPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyDeliveryAnnouncementContentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyDeliveryAnnouncementContentQueryListCommand) {
			return queryListCommandToDO(((DataCompanyDeliveryAnnouncementContentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyDeliveryAnnouncementContentDO pageQueryCommandToDO(DataCompanyDeliveryAnnouncementContentPageQueryCommand dataCompanyDeliveryAnnouncementContentPageQueryCommand);

	public abstract DataCompanyDeliveryAnnouncementContentDO queryListCommandToDO(DataCompanyDeliveryAnnouncementContentQueryListCommand dataCompanyDeliveryAnnouncementContentQueryListCommand);
    public abstract DataCompanyDeliveryAnnouncementContentExWarehouseVO dataCompanyDeliveryAnnouncementContentDOToDataCompanyDeliveryAnnouncementContentExWarehouseVO(DataCompanyDeliveryAnnouncementContentDO dataCompanyDeliveryAnnouncementContentDO);
    public abstract List<DataCompanyDeliveryAnnouncementContentExWarehouseVO> dataCompanyDeliveryAnnouncementContentDOsToDataCompanyDeliveryAnnouncementContentExWarehouseVOs(List<DataCompanyDeliveryAnnouncementContentDO> dataCompanyDeliveryAnnouncementContentDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyDeliveryAnnouncementContentExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyDeliveryAnnouncementContentDO> page) {
		return PageResponse.of(dataCompanyDeliveryAnnouncementContentDOsToDataCompanyDeliveryAnnouncementContentExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
