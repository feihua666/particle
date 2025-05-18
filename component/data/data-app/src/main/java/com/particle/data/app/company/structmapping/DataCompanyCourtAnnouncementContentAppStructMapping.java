package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContent;
import com.particle.data.domain.company.DataCompanyCourtAnnouncementContentId;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementContentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业法院公告内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyCourtAnnouncementContentAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyCourtAnnouncementContentDO>{
	public static DataCompanyCourtAnnouncementContentAppStructMapping instance = Mappers.getMapper( DataCompanyCourtAnnouncementContentAppStructMapping.class );

	protected Long map(DataCompanyCourtAnnouncementContentId dataCompanyCourtAnnouncementContentId){
		if (dataCompanyCourtAnnouncementContentId == null) {
			return null;
		}
		return dataCompanyCourtAnnouncementContentId.getId();
	}
	/**
	 * 企业法院公告内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyCourtAnnouncementContentAppStructMapping#map(DataCompanyCourtAnnouncementContentId)}
	 * @param dataCompanyCourtAnnouncementContent
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementContentVO toDataCompanyCourtAnnouncementContentVO(DataCompanyCourtAnnouncementContent dataCompanyCourtAnnouncementContent);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyCourtAnnouncementContentDO
	 * @return
	 */
	public abstract DataCompanyCourtAnnouncementContentVO dataCompanyCourtAnnouncementContentDOToDataCompanyCourtAnnouncementContentVO(DataCompanyCourtAnnouncementContentDO dataCompanyCourtAnnouncementContentDO);

	/**
	 * 批量转换
	 * @param dataCompanyCourtAnnouncementContentDOs
	 * @return
	 */
	public abstract List<DataCompanyCourtAnnouncementContentVO> dataCompanyCourtAnnouncementContentDOsToDataCompanyCourtAnnouncementContentVOs(List<DataCompanyCourtAnnouncementContentDO> dataCompanyCourtAnnouncementContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementContentVO> infrastructurePageToPageResponse(Page<DataCompanyCourtAnnouncementContentDO> page) {
		return PageResponse.of(dataCompanyCourtAnnouncementContentDOsToDataCompanyCourtAnnouncementContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyCourtAnnouncementContentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyCourtAnnouncementContentPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyCourtAnnouncementContentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyCourtAnnouncementContentQueryListCommand) {
			return queryListCommandToDO(((DataCompanyCourtAnnouncementContentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyCourtAnnouncementContentDO pageQueryCommandToDO(DataCompanyCourtAnnouncementContentPageQueryCommand dataCompanyCourtAnnouncementContentPageQueryCommand);

	public abstract DataCompanyCourtAnnouncementContentDO queryListCommandToDO(DataCompanyCourtAnnouncementContentQueryListCommand dataCompanyCourtAnnouncementContentQueryListCommand);
    public abstract DataCompanyCourtAnnouncementContentExWarehouseVO dataCompanyCourtAnnouncementContentDOToDataCompanyCourtAnnouncementContentExWarehouseVO(DataCompanyCourtAnnouncementContentDO dataCompanyCourtAnnouncementContentDO);
    public abstract List<DataCompanyCourtAnnouncementContentExWarehouseVO> dataCompanyCourtAnnouncementContentDOsToDataCompanyCourtAnnouncementContentExWarehouseVOs(List<DataCompanyCourtAnnouncementContentDO> dataCompanyCourtAnnouncementContentDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyCourtAnnouncementContentDO> page) {
		return PageResponse.of(dataCompanyCourtAnnouncementContentDOsToDataCompanyCourtAnnouncementContentExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
