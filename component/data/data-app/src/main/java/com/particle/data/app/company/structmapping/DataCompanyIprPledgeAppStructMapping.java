package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPledge;
import com.particle.data.domain.company.DataCompanyIprPledgeId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPledgeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权出质 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPledgeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPledgeDO>{
	public static DataCompanyIprPledgeAppStructMapping instance = Mappers.getMapper( DataCompanyIprPledgeAppStructMapping.class );

	protected Long map(DataCompanyIprPledgeId dataCompanyIprPledgeId){
		if (dataCompanyIprPledgeId == null) {
			return null;
		}
		return dataCompanyIprPledgeId.getId();
	}
	/**
	 * 企业知识产权出质领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPledgeAppStructMapping#map(DataCompanyIprPledgeId)}
	 * @param dataCompanyIprPledge
	 * @return
	 */
	public abstract DataCompanyIprPledgeVO toDataCompanyIprPledgeVO(DataCompanyIprPledge dataCompanyIprPledge);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPledgeDO
	 * @return
	 */
	public abstract DataCompanyIprPledgeVO dataCompanyIprPledgeDOToDataCompanyIprPledgeVO(DataCompanyIprPledgeDO dataCompanyIprPledgeDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPledgeDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPledgeVO> dataCompanyIprPledgeDOsToDataCompanyIprPledgeVOs(List<DataCompanyIprPledgeDO> dataCompanyIprPledgeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPledgeVO> infrastructurePageToPageResponse(Page<DataCompanyIprPledgeDO> page) {
		return PageResponse.of(dataCompanyIprPledgeDOsToDataCompanyIprPledgeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPledgeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPledgePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPledgePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPledgeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPledgeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPledgeDO pageQueryCommandToDO(DataCompanyIprPledgePageQueryCommand dataCompanyIprPledgePageQueryCommand);

	public abstract DataCompanyIprPledgeDO queryListCommandToDO(DataCompanyIprPledgeQueryListCommand dataCompanyIprPledgeQueryListCommand);
    public abstract DataCompanyIprPledgeExWarehouseVO dataCompanyIprPledgeDOToDataCompanyIprPledgeExWarehouseVO(DataCompanyIprPledgeDO dataCompanyIprPledgeDO);
    public abstract List<DataCompanyIprPledgeExWarehouseVO> dataCompanyIprPledgeDOsToDataCompanyIprPledgeExWarehouseVOs(List<DataCompanyIprPledgeDO> dataCompanyIprPledgeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPledgeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPledgeDO> page) {
		return PageResponse.of(dataCompanyIprPledgeDOsToDataCompanyIprPledgeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
