package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentContentExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentContent;
import com.particle.data.domain.company.DataCompanyIprPatentContentId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentContentAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentContentDO>{
	public static DataCompanyIprPatentContentAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentContentAppStructMapping.class );

	protected Long map(DataCompanyIprPatentContentId dataCompanyIprPatentContentId){
		if (dataCompanyIprPatentContentId == null) {
			return null;
		}
		return dataCompanyIprPatentContentId.getId();
	}
	/**
	 * 企业知识产权专利内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentContentAppStructMapping#map(DataCompanyIprPatentContentId)}
	 * @param dataCompanyIprPatentContent
	 * @return
	 */
	public abstract DataCompanyIprPatentContentVO toDataCompanyIprPatentContentVO(DataCompanyIprPatentContent dataCompanyIprPatentContent);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentContentDO
	 * @return
	 */
	public abstract DataCompanyIprPatentContentVO dataCompanyIprPatentContentDOToDataCompanyIprPatentContentVO(DataCompanyIprPatentContentDO dataCompanyIprPatentContentDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentContentDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentContentVO> dataCompanyIprPatentContentDOsToDataCompanyIprPatentContentVOs(List<DataCompanyIprPatentContentDO> dataCompanyIprPatentContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentContentVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentContentDO> page) {
		return PageResponse.of(dataCompanyIprPatentContentDOsToDataCompanyIprPatentContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentContentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentContentPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentContentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentContentQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentContentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentContentDO pageQueryCommandToDO(DataCompanyIprPatentContentPageQueryCommand dataCompanyIprPatentContentPageQueryCommand);

	public abstract DataCompanyIprPatentContentDO queryListCommandToDO(DataCompanyIprPatentContentQueryListCommand dataCompanyIprPatentContentQueryListCommand);
    public abstract DataCompanyIprPatentContentExWarehouseVO dataCompanyIprPatentContentDOToDataCompanyIprPatentContentExWarehouseVO(DataCompanyIprPatentContentDO dataCompanyIprPatentContentDO);
    public abstract List<DataCompanyIprPatentContentExWarehouseVO> dataCompanyIprPatentContentDOsToDataCompanyIprPatentContentExWarehouseVOs(List<DataCompanyIprPatentContentDO> dataCompanyIprPatentContentDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentContentExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentContentDO> page) {
		return PageResponse.of(dataCompanyIprPatentContentDOsToDataCompanyIprPatentContentExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
