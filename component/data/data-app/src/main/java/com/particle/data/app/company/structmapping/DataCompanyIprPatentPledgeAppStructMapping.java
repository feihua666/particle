package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPledgeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPledgeExWarehouseVO;
import com.particle.data.domain.company.DataCompanyIprPatentPledge;
import com.particle.data.domain.company.DataCompanyIprPatentPledgeId;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentPledgeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业知识产权专利质押信息 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyIprPatentPledgeAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyIprPatentPledgeDO>{
	public static DataCompanyIprPatentPledgeAppStructMapping instance = Mappers.getMapper( DataCompanyIprPatentPledgeAppStructMapping.class );

	protected Long map(DataCompanyIprPatentPledgeId dataCompanyIprPatentPledgeId){
		if (dataCompanyIprPatentPledgeId == null) {
			return null;
		}
		return dataCompanyIprPatentPledgeId.getId();
	}
	/**
	 * 企业知识产权专利质押信息领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyIprPatentPledgeAppStructMapping#map(DataCompanyIprPatentPledgeId)}
	 * @param dataCompanyIprPatentPledge
	 * @return
	 */
	public abstract DataCompanyIprPatentPledgeVO toDataCompanyIprPatentPledgeVO(DataCompanyIprPatentPledge dataCompanyIprPatentPledge);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyIprPatentPledgeDO
	 * @return
	 */
	public abstract DataCompanyIprPatentPledgeVO dataCompanyIprPatentPledgeDOToDataCompanyIprPatentPledgeVO(DataCompanyIprPatentPledgeDO dataCompanyIprPatentPledgeDO);

	/**
	 * 批量转换
	 * @param dataCompanyIprPatentPledgeDOs
	 * @return
	 */
	public abstract List<DataCompanyIprPatentPledgeVO> dataCompanyIprPatentPledgeDOsToDataCompanyIprPatentPledgeVOs(List<DataCompanyIprPatentPledgeDO> dataCompanyIprPatentPledgeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPledgeVO> infrastructurePageToPageResponse(Page<DataCompanyIprPatentPledgeDO> page) {
		return PageResponse.of(dataCompanyIprPatentPledgeDOsToDataCompanyIprPatentPledgeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyIprPatentPledgeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyIprPatentPledgePageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyIprPatentPledgePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyIprPatentPledgeQueryListCommand) {
			return queryListCommandToDO(((DataCompanyIprPatentPledgeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyIprPatentPledgeDO pageQueryCommandToDO(DataCompanyIprPatentPledgePageQueryCommand dataCompanyIprPatentPledgePageQueryCommand);

	public abstract DataCompanyIprPatentPledgeDO queryListCommandToDO(DataCompanyIprPatentPledgeQueryListCommand dataCompanyIprPatentPledgeQueryListCommand);
    public abstract DataCompanyIprPatentPledgeExWarehouseVO dataCompanyIprPatentPledgeDOToDataCompanyIprPatentPledgeExWarehouseVO(DataCompanyIprPatentPledgeDO dataCompanyIprPatentPledgeDO);
    public abstract List<DataCompanyIprPatentPledgeExWarehouseVO> dataCompanyIprPatentPledgeDOsToDataCompanyIprPatentPledgeExWarehouseVOs(List<DataCompanyIprPatentPledgeDO> dataCompanyIprPatentPledgeDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentPledgeExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyIprPatentPledgeDO> page) {
		return PageResponse.of(dataCompanyIprPatentPledgeDOsToDataCompanyIprPatentPledgeExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
