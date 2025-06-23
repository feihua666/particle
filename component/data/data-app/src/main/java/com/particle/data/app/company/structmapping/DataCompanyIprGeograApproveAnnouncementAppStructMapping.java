package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncement;
import com.particle.data.domain.company.DataCompanyIprGeograApproveAnnouncementId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograApproveAnnouncementDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权地理标识核准公告 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprGeograApproveAnnouncementAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprGeograApproveAnnouncementDO>{
	public static DataCompanyIprGeograApproveAnnouncementAppStructMapping instance = Mappers.getMapper( DataCompanyIprGeograApproveAnnouncementAppStructMapping.class );

	protected Long map(DataCompanyIprGeograApproveAnnouncementId dataCompanyIprGeograApproveAnnouncementId){
		if (dataCompanyIprGeograApproveAnnouncementId == null) {
			return null;
		}
		return dataCompanyIprGeograApproveAnnouncementId.getId();
	}
	/**
	 * 企业知识产权地理标识核准公告领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprGeograApproveAnnouncementAppStructMapping#map(DataCompanyIprGeograApproveAnnouncementId)}
	 * @param dataCompanyIprGeograApproveAnnouncement
	 * @return
	 */
	public abstract DataCompanyIprGeograApproveAnnouncementVO toDataCompanyIprGeograApproveAnnouncementVO(DataCompanyIprGeograApproveAnnouncement dataCompanyIprGeograApproveAnnouncement);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprGeograApproveAnnouncementDO
	 * @return
	 */
	public abstract DataCompanyIprGeograApproveAnnouncementVO dataCompanyIprGeograApproveAnnouncementDOToDataCompanyIprGeograApproveAnnouncementVO(DataCompanyIprGeograApproveAnnouncementDO dataCompanyIprGeograApproveAnnouncementDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprGeograApproveAnnouncementDOs
	 * @return
	 */
	public abstract List<DataCompanyIprGeograApproveAnnouncementVO> dataCompanyIprGeograApproveAnnouncementDOsToDataCompanyIprGeograApproveAnnouncementVOs(List<DataCompanyIprGeograApproveAnnouncementDO> dataCompanyIprGeograApproveAnnouncementDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograApproveAnnouncementVO> infrastructurePageToPageResponse(Page<DataCompanyIprGeograApproveAnnouncementDO> page) {
		return PageResponse.of(dataCompanyIprGeograApproveAnnouncementDOsToDataCompanyIprGeograApproveAnnouncementVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprGeograApproveAnnouncementDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprGeograApproveAnnouncementPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprGeograApproveAnnouncementPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprGeograApproveAnnouncementQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprGeograApproveAnnouncementQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprGeograApproveAnnouncementDO pageQueryCommandToDO(DataCompanyIprGeograApproveAnnouncementPageQueryCommand dataCompanyIprGeograApproveAnnouncementPageQueryCommand);

	public abstract DataCompanyIprGeograApproveAnnouncementDO queryListCommandToDO(DataCompanyIprGeograApproveAnnouncementQueryListCommand dataCompanyIprGeograApproveAnnouncementQueryListCommand);
    public abstract DataCompanyIprGeograApproveAnnouncementExWarehouseVO dataCompanyIprGeograApproveAnnouncementDOToDataCompanyIprGeograApproveAnnouncementExWarehouseVO(DataCompanyIprGeograApproveAnnouncementDO dataCompanyIprGeograApproveAnnouncementDO);
    public abstract List<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> dataCompanyIprGeograApproveAnnouncementDOsToDataCompanyIprGeograApproveAnnouncementExWarehouseVOs(List<DataCompanyIprGeograApproveAnnouncementDO> dataCompanyIprGeograApproveAnnouncementDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprGeograApproveAnnouncementDO> page) {
		return PageResponse.of(dataCompanyIprGeograApproveAnnouncementDOsToDataCompanyIprGeograApproveAnnouncementExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
