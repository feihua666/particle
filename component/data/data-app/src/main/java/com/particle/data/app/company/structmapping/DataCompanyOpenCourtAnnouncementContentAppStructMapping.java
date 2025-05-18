package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseVO;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementContent;
import com.particle.data.domain.company.DataCompanyOpenCourtAnnouncementContentId;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementContentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业开庭公告内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyOpenCourtAnnouncementContentAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyOpenCourtAnnouncementContentDO>{
	public static DataCompanyOpenCourtAnnouncementContentAppStructMapping instance = Mappers.getMapper( DataCompanyOpenCourtAnnouncementContentAppStructMapping.class );

	protected Long map(DataCompanyOpenCourtAnnouncementContentId dataCompanyOpenCourtAnnouncementContentId){
		if (dataCompanyOpenCourtAnnouncementContentId == null) {
			return null;
		}
		return dataCompanyOpenCourtAnnouncementContentId.getId();
	}
	/**
	 * 企业开庭公告内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyOpenCourtAnnouncementContentAppStructMapping#map(DataCompanyOpenCourtAnnouncementContentId)}
	 * @param dataCompanyOpenCourtAnnouncementContent
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncementContentVO toDataCompanyOpenCourtAnnouncementContentVO(DataCompanyOpenCourtAnnouncementContent dataCompanyOpenCourtAnnouncementContent);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyOpenCourtAnnouncementContentDO
	 * @return
	 */
	public abstract DataCompanyOpenCourtAnnouncementContentVO dataCompanyOpenCourtAnnouncementContentDOToDataCompanyOpenCourtAnnouncementContentVO(DataCompanyOpenCourtAnnouncementContentDO dataCompanyOpenCourtAnnouncementContentDO);

	/**
	 * 批量转换
	 * @param dataCompanyOpenCourtAnnouncementContentDOs
	 * @return
	 */
	public abstract List<DataCompanyOpenCourtAnnouncementContentVO> dataCompanyOpenCourtAnnouncementContentDOsToDataCompanyOpenCourtAnnouncementContentVOs(List<DataCompanyOpenCourtAnnouncementContentDO> dataCompanyOpenCourtAnnouncementContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementContentVO> infrastructurePageToPageResponse(Page<DataCompanyOpenCourtAnnouncementContentDO> page) {
		return PageResponse.of(dataCompanyOpenCourtAnnouncementContentDOsToDataCompanyOpenCourtAnnouncementContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyOpenCourtAnnouncementContentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyOpenCourtAnnouncementContentPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyOpenCourtAnnouncementContentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyOpenCourtAnnouncementContentQueryListCommand) {
			return queryListCommandToDO(((DataCompanyOpenCourtAnnouncementContentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyOpenCourtAnnouncementContentDO pageQueryCommandToDO(DataCompanyOpenCourtAnnouncementContentPageQueryCommand dataCompanyOpenCourtAnnouncementContentPageQueryCommand);

	public abstract DataCompanyOpenCourtAnnouncementContentDO queryListCommandToDO(DataCompanyOpenCourtAnnouncementContentQueryListCommand dataCompanyOpenCourtAnnouncementContentQueryListCommand);
    public abstract DataCompanyOpenCourtAnnouncementContentExWarehouseVO dataCompanyOpenCourtAnnouncementContentDOToDataCompanyOpenCourtAnnouncementContentExWarehouseVO(DataCompanyOpenCourtAnnouncementContentDO dataCompanyOpenCourtAnnouncementContentDO);
    public abstract List<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> dataCompanyOpenCourtAnnouncementContentDOsToDataCompanyOpenCourtAnnouncementContentExWarehouseVOs(List<DataCompanyOpenCourtAnnouncementContentDO> dataCompanyOpenCourtAnnouncementContentDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyOpenCourtAnnouncementContentDO> page) {
		return PageResponse.of(dataCompanyOpenCourtAnnouncementContentDOsToDataCompanyOpenCourtAnnouncementContentExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
