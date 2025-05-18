package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncement;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementId;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业开庭公告 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyOpenCourtAnnouncementAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyOpenCourtAnnouncementDO>{
	public static DataCompanyOpenCourtAnnouncementAppStructMapping instance = Mappers.getMapper( DataCompanyOpenCourtAnnouncementAppStructMapping.class );

	protected Long map(DataCompanyOpenCourtAnnouncementId dataCompanyOpenCourtAnnouncementId){
		if (dataCompanyOpenCourtAnnouncementId == null) {
			return null;
		}
		return dataCompanyOpenCourtAnnouncementId.getId();
	}
	/**
	 * 企业开庭公告领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyOpenCourtAnnouncementAppStructMapping#map(DataCompanyOpenCourtAnnouncementId)}
	 * @param dataCompanyOpenCourtAnnouncement
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncementVO toDataCompanyOpenCourtAnnouncementVO(DataCompanyOpenCourtAnnouncement dataCompanyOpenCourtAnnouncement);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyOpenCourtAnnouncementDO
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncementVO dataCompanyOpenCourtAnnouncementDOToDataCompanyOpenCourtAnnouncementVO(DataCompanyOpenCourtAnnouncementDO dataCompanyOpenCourtAnnouncementDO);

	/**
	 * 批量转换
	 * @param dataCompanyOpenCourtAnnouncementDOs
	 * @return
	 */
	public abstract List<DataCompanyOpenCourtAnnouncementVO> dataCompanyOpenCourtAnnouncementDOsToDataCompanyOpenCourtAnnouncementVOs(List<DataCompanyOpenCourtAnnouncementDO> dataCompanyOpenCourtAnnouncementDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementVO> infrastructurePageToPageResponse(Page<DataCompanyOpenCourtAnnouncementDO> page) {
		return PageResponse.of(dataCompanyOpenCourtAnnouncementDOsToDataCompanyOpenCourtAnnouncementVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyOpenCourtAnnouncementDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyOpenCourtAnnouncementPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyOpenCourtAnnouncementPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyOpenCourtAnnouncementQueryListCommand) {
			return queryListCommandToDO(((DataCompanyOpenCourtAnnouncementQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyOpenCourtAnnouncementDO pageQueryCommandToDO(DataCompanyOpenCourtAnnouncementPageQueryCommand dataCompanyOpenCourtAnnouncementPageQueryCommand);

	public abstract DataCompanyOpenCourtAnnouncementDO queryListCommandToDO(DataCompanyOpenCourtAnnouncementQueryListCommand dataCompanyOpenCourtAnnouncementQueryListCommand);
    public abstract DataCompanyOpenCourtAnnouncementExWarehouseVO dataCompanyOpenCourtAnnouncementDOToDataCompanyOpenCourtAnnouncementExWarehouseVO(DataCompanyOpenCourtAnnouncementDO dataCompanyOpenCourtAnnouncementDO);
    public abstract List<DataCompanyOpenCourtAnnouncementExWarehouseVO> dataCompanyOpenCourtAnnouncementDOsToDataCompanyOpenCourtAnnouncementExWarehouseVOs(List<DataCompanyOpenCourtAnnouncementDO> dataCompanyOpenCourtAnnouncementDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyOpenCourtAnnouncementDO> page) {
		return PageResponse.of(dataCompanyOpenCourtAnnouncementDOsToDataCompanyOpenCourtAnnouncementExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
