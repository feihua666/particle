package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprGeogra;
import com.particle.data.domain.company.DataCompanyIprGeograId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权地理标识 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprGeograAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprGeograDO>{
	public static DataCompanyIprGeograAppStructMapping instance = Mappers.getMapper( DataCompanyIprGeograAppStructMapping.class );

	protected Long map(DataCompanyIprGeograId dataCompanyIprGeograId){
		if (dataCompanyIprGeograId == null) {
			return null;
		}
		return dataCompanyIprGeograId.getId();
	}
	/**
	 * 企业知识产权地理标识领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprGeograAppStructMapping#map(DataCompanyIprGeograId)}
	 * @param dataCompanyIprGeogra
	 * @return
	 */
	public abstract DataCompanyIprGeograVO toDataCompanyIprGeograVO(DataCompanyIprGeogra dataCompanyIprGeogra);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprGeograDO
	 * @return
	 */
	public abstract DataCompanyIprGeograVO dataCompanyIprGeograDOToDataCompanyIprGeograVO(DataCompanyIprGeograDO dataCompanyIprGeograDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprGeograDOs
	 * @return
	 */
	public abstract List<DataCompanyIprGeograVO> dataCompanyIprGeograDOsToDataCompanyIprGeograVOs(List<DataCompanyIprGeograDO> dataCompanyIprGeograDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograVO> infrastructurePageToPageResponse(Page<DataCompanyIprGeograDO> page) {
		return PageResponse.of(dataCompanyIprGeograDOsToDataCompanyIprGeograVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprGeograDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprGeograPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprGeograPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprGeograQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprGeograQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprGeograDO pageQueryCommandToDO(DataCompanyIprGeograPageQueryCommand dataCompanyIprGeograPageQueryCommand);

	public abstract DataCompanyIprGeograDO queryListCommandToDO(DataCompanyIprGeograQueryListCommand dataCompanyIprGeograQueryListCommand);
    public abstract DataCompanyIprGeograExWarehouseVO dataCompanyIprGeograDOToDataCompanyIprGeograExWarehouseVO(DataCompanyIprGeograDO dataCompanyIprGeograDO);
    public abstract List<DataCompanyIprGeograExWarehouseVO> dataCompanyIprGeograDOsToDataCompanyIprGeograExWarehouseVOs(List<DataCompanyIprGeograDO> dataCompanyIprGeograDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprGeograDO> page) {
		return PageResponse.of(dataCompanyIprGeograDOsToDataCompanyIprGeograExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
