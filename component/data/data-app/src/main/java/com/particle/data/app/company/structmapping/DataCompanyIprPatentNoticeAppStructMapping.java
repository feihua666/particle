package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentNoticeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentNotice;
import com.particle.data.domain.company.DataCompanyIprPatentNoticeId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentNoticeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利通知书信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentNoticeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentNoticeDO>{
	public static DataCompanyIprPatentNoticeAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentNoticeAppStructMapping.class );

	protected Long map(DataCompanyIprPatentNoticeId dataCompanyIprPatentNoticeId){
		if (dataCompanyIprPatentNoticeId == null) {
			return null;
		}
		return dataCompanyIprPatentNoticeId.getId();
	}
	/**
	 * 企业知识产权专利通知书信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentNoticeAppStructMapping#map(DataCompanyIprPatentNoticeId)}
	 * @param dataCompanyIprPatentNotice
	 * @return
	 */
	public abstract DataCompanyIprPatentNoticeVO toDataCompanyIprPatentNoticeVO(DataCompanyIprPatentNotice dataCompanyIprPatentNotice);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentNoticeDO
	 * @return
	 */
	public abstract DataCompanyIprPatentNoticeVO dataCompanyIprPatentNoticeDOToDataCompanyIprPatentNoticeVO(DataCompanyIprPatentNoticeDO dataCompanyIprPatentNoticeDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentNoticeDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentNoticeVO> dataCompanyIprPatentNoticeDOsToDataCompanyIprPatentNoticeVOs(List<DataCompanyIprPatentNoticeDO> dataCompanyIprPatentNoticeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentNoticeVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentNoticeDO> page) {
		return PageResponse.of(dataCompanyIprPatentNoticeDOsToDataCompanyIprPatentNoticeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentNoticeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentNoticePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentNoticePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentNoticeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentNoticeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentNoticeDO pageQueryCommandToDO(DataCompanyIprPatentNoticePageQueryCommand dataCompanyIprPatentNoticePageQueryCommand);

	public abstract DataCompanyIprPatentNoticeDO queryListCommandToDO(DataCompanyIprPatentNoticeQueryListCommand dataCompanyIprPatentNoticeQueryListCommand);
    public abstract DataCompanyIprPatentNoticeExWarehouseVO dataCompanyIprPatentNoticeDOToDataCompanyIprPatentNoticeExWarehouseVO(DataCompanyIprPatentNoticeDO dataCompanyIprPatentNoticeDO);
    public abstract List<DataCompanyIprPatentNoticeExWarehouseVO> dataCompanyIprPatentNoticeDOsToDataCompanyIprPatentNoticeExWarehouseVOs(List<DataCompanyIprPatentNoticeDO> dataCompanyIprPatentNoticeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentNoticeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentNoticeDO> page) {
		return PageResponse.of(dataCompanyIprPatentNoticeDOsToDataCompanyIprPatentNoticeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
