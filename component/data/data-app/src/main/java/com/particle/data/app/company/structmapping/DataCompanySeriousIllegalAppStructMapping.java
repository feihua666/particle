package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanySeriousIllegalQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.data.domain.company.DataCompanySeriousIllegal;
import com.particle.data.domain.company.DataCompanySeriousIllegalId;
import com.particle.data.infrastructure.company.dos.DataCompanySeriousIllegalDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业严重违法 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanySeriousIllegalAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanySeriousIllegalDO>{
	public static DataCompanySeriousIllegalAppStructMapping instance = Mappers.getMapper( DataCompanySeriousIllegalAppStructMapping.class );

	protected Long map(DataCompanySeriousIllegalId dataCompanySeriousIllegalId){
		if (dataCompanySeriousIllegalId == null) {
			return null;
		}
		return dataCompanySeriousIllegalId.getId();
	}
	/**
	 * 企业严重违法领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanySeriousIllegalAppStructMapping#map(DataCompanySeriousIllegalId)}
	 * @param dataCompanySeriousIllegal
	 * @return
	 */
	public abstract DataCompanySeriousIllegalVO toDataCompanySeriousIllegalVO(DataCompanySeriousIllegal dataCompanySeriousIllegal);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanySeriousIllegalDO
	 * @return
	 */
	public abstract DataCompanySeriousIllegalVO dataCompanySeriousIllegalDOToDataCompanySeriousIllegalVO(DataCompanySeriousIllegalDO dataCompanySeriousIllegalDO);

	/**
	 * 批量转换
	 * @param dataCompanySeriousIllegalDOs
	 * @return
	 */
	public abstract List<DataCompanySeriousIllegalVO> dataCompanySeriousIllegalDOsToDataCompanySeriousIllegalVOs(List<DataCompanySeriousIllegalDO> dataCompanySeriousIllegalDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanySeriousIllegalVO> infrastructurePageToPageResponse(Page<DataCompanySeriousIllegalDO> page) {
		return PageResponse.of(dataCompanySeriousIllegalDOsToDataCompanySeriousIllegalVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanySeriousIllegalDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanySeriousIllegalPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanySeriousIllegalPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanySeriousIllegalQueryListCommand) {
			return queryListCommandToDO(((DataCompanySeriousIllegalQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanySeriousIllegalDO pageQueryCommandToDO(DataCompanySeriousIllegalPageQueryCommand dataCompanySeriousIllegalPageQueryCommand);

	public abstract DataCompanySeriousIllegalDO queryListCommandToDO(DataCompanySeriousIllegalQueryListCommand dataCompanySeriousIllegalQueryListCommand);
    public abstract DataCompanySeriousIllegalExWarehouseVO dataCompanySeriousIllegalDOToDataCompanySeriousIllegalExWarehouseVO(DataCompanySeriousIllegalDO dataCompanySeriousIllegalDO);
    public abstract List<DataCompanySeriousIllegalExWarehouseVO> dataCompanySeriousIllegalDOsToDataCompanySeriousIllegalExWarehouseVOs(List<DataCompanySeriousIllegalDO> dataCompanySeriousIllegalDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanySeriousIllegalExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanySeriousIllegalDO> page) {
		return PageResponse.of(dataCompanySeriousIllegalDOsToDataCompanySeriousIllegalExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
