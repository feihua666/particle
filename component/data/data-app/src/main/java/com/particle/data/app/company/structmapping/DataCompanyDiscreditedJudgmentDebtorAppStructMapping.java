package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDiscreditedJudgmentDebtorQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDiscreditedJudgmentDebtorVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyDiscreditedJudgmentDebtorId;
import com.particle.data.infrastructure.company.dos.DataCompanyDiscreditedJudgmentDebtorDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业失信被执行人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyDiscreditedJudgmentDebtorAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyDiscreditedJudgmentDebtorDO>{
	public static DataCompanyDiscreditedJudgmentDebtorAppStructMapping instance = Mappers.getMapper( DataCompanyDiscreditedJudgmentDebtorAppStructMapping.class );

	protected Long map(DataCompanyDiscreditedJudgmentDebtorId dataCompanyDiscreditedJudgmentDebtorId){
		if (dataCompanyDiscreditedJudgmentDebtorId == null) {
			return null;
		}
		return dataCompanyDiscreditedJudgmentDebtorId.getId();
	}
	/**
	 * 企业失信被执行人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyDiscreditedJudgmentDebtorAppStructMapping#map(DataCompanyDiscreditedJudgmentDebtorId)}
	 * @param dataCompanyDiscreditedJudgmentDebtor
	 * @return
	 */
	public abstract DataCompanyDiscreditedJudgmentDebtorVO toDataCompanyDiscreditedJudgmentDebtorVO(DataCompanyDiscreditedJudgmentDebtor dataCompanyDiscreditedJudgmentDebtor);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyDiscreditedJudgmentDebtorDO
	 * @return
	 */
	public abstract DataCompanyDiscreditedJudgmentDebtorVO dataCompanyDiscreditedJudgmentDebtorDOToDataCompanyDiscreditedJudgmentDebtorVO(DataCompanyDiscreditedJudgmentDebtorDO dataCompanyDiscreditedJudgmentDebtorDO);

	/**
	 * 批量转换
	 * @param dataCompanyDiscreditedJudgmentDebtorDOs
	 * @return
	 */
	public abstract List<DataCompanyDiscreditedJudgmentDebtorVO> dataCompanyDiscreditedJudgmentDebtorDOsToDataCompanyDiscreditedJudgmentDebtorVOs(List<DataCompanyDiscreditedJudgmentDebtorDO> dataCompanyDiscreditedJudgmentDebtorDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyDiscreditedJudgmentDebtorVO> infrastructurePageToPageResponse(Page<DataCompanyDiscreditedJudgmentDebtorDO> page) {
		return PageResponse.of(dataCompanyDiscreditedJudgmentDebtorDOsToDataCompanyDiscreditedJudgmentDebtorVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyDiscreditedJudgmentDebtorDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyDiscreditedJudgmentDebtorPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyDiscreditedJudgmentDebtorPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyDiscreditedJudgmentDebtorQueryListCommand) {
			return queryListCommandToDO(((DataCompanyDiscreditedJudgmentDebtorQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyDiscreditedJudgmentDebtorDO pageQueryCommandToDO(DataCompanyDiscreditedJudgmentDebtorPageQueryCommand dataCompanyDiscreditedJudgmentDebtorPageQueryCommand);

	public abstract DataCompanyDiscreditedJudgmentDebtorDO queryListCommandToDO(DataCompanyDiscreditedJudgmentDebtorQueryListCommand dataCompanyDiscreditedJudgmentDebtorQueryListCommand);
    public abstract DataCompanyDiscreditedJudgmentDebtorExWarehouseVO dataCompanyDiscreditedJudgmentDebtorDOToDataCompanyDiscreditedJudgmentDebtorExWarehouseVO(DataCompanyDiscreditedJudgmentDebtorDO dataCompanyDiscreditedJudgmentDebtorDO);
    public abstract List<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> dataCompanyDiscreditedJudgmentDebtorDOsToDataCompanyDiscreditedJudgmentDebtorExWarehouseVOs(List<DataCompanyDiscreditedJudgmentDebtorDO> dataCompanyDiscreditedJudgmentDebtorDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyDiscreditedJudgmentDebtorDO> page) {
		return PageResponse.of(dataCompanyDiscreditedJudgmentDebtorDOsToDataCompanyDiscreditedJudgmentDebtorExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
