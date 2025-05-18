package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementId;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业法院公告 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCourtAnnouncementAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyCourtAnnouncementDO>{
	public static DataCompanyCourtAnnouncementAppStructMapping instance = Mappers.getMapper( DataCompanyCourtAnnouncementAppStructMapping.class );

	protected Long map(DataCompanyCourtAnnouncementId dataCompanyCourtAnnouncementId){
		if (dataCompanyCourtAnnouncementId == null) {
			return null;
		}
		return dataCompanyCourtAnnouncementId.getId();
	}
	/**
	 * 企业法院公告领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCourtAnnouncementAppStructMapping#map(DataCompanyCourtAnnouncementId)}
	 * @param dataCompanyCourtAnnouncement
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementVO toDataCompanyCourtAnnouncementVO(DataCompanyCourtAnnouncement dataCompanyCourtAnnouncement);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyCourtAnnouncementDO
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementVO dataCompanyCourtAnnouncementDOToDataCompanyCourtAnnouncementVO(DataCompanyCourtAnnouncementDO dataCompanyCourtAnnouncementDO);

	/**
	 * 批量转换
	 * @param dataCompanyCourtAnnouncementDOs
	 * @return
	 */
	public abstract List<DataCompanyCourtAnnouncementVO> dataCompanyCourtAnnouncementDOsToDataCompanyCourtAnnouncementVOs(List<DataCompanyCourtAnnouncementDO> dataCompanyCourtAnnouncementDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementVO> infrastructurePageToPageResponse(Page<DataCompanyCourtAnnouncementDO> page) {
		return PageResponse.of(dataCompanyCourtAnnouncementDOsToDataCompanyCourtAnnouncementVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyCourtAnnouncementDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyCourtAnnouncementPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyCourtAnnouncementPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyCourtAnnouncementQueryListCommand) {
			return queryListCommandToDO(((DataCompanyCourtAnnouncementQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyCourtAnnouncementDO pageQueryCommandToDO(DataCompanyCourtAnnouncementPageQueryCommand dataCompanyCourtAnnouncementPageQueryCommand);

	public abstract DataCompanyCourtAnnouncementDO queryListCommandToDO(DataCompanyCourtAnnouncementQueryListCommand dataCompanyCourtAnnouncementQueryListCommand);
    public abstract DataCompanyCourtAnnouncementExWarehouseVO dataCompanyCourtAnnouncementDOToDataCompanyCourtAnnouncementExWarehouseVO(DataCompanyCourtAnnouncementDO dataCompanyCourtAnnouncementDO);
    public abstract List<DataCompanyCourtAnnouncementExWarehouseVO> dataCompanyCourtAnnouncementDOsToDataCompanyCourtAnnouncementExWarehouseVOs(List<DataCompanyCourtAnnouncementDO> dataCompanyCourtAnnouncementDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyCourtAnnouncementDO> page) {
		return PageResponse.of(dataCompanyCourtAnnouncementDOsToDataCompanyCourtAnnouncementExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
