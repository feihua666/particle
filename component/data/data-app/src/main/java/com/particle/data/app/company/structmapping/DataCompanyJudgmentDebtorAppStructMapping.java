package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyJudgmentDebtorQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyJudgmentDebtorVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
import com.particle.data.domain.company.DataCompanyJudgmentDebtor;
import com.particle.data.domain.company.DataCompanyJudgmentDebtorId;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDebtorDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业被执行人 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:07
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyJudgmentDebtorAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyJudgmentDebtorDO>{
	public static DataCompanyJudgmentDebtorAppStructMapping instance = Mappers.getMapper( DataCompanyJudgmentDebtorAppStructMapping.class );

	protected Long map(DataCompanyJudgmentDebtorId dataCompanyJudgmentDebtorId){
		if (dataCompanyJudgmentDebtorId == null) {
			return null;
		}
		return dataCompanyJudgmentDebtorId.getId();
	}
	/**
	 * 企业被执行人领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyJudgmentDebtorAppStructMapping#map(DataCompanyJudgmentDebtorId)}
	 * @param dataCompanyJudgmentDebtor
	 * @return
	 */
	public abstract DataCompanyJudgmentDebtorVO toDataCompanyJudgmentDebtorVO(DataCompanyJudgmentDebtor dataCompanyJudgmentDebtor);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyJudgmentDebtorDO
	 * @return
	 */
	public abstract DataCompanyJudgmentDebtorVO dataCompanyJudgmentDebtorDOToDataCompanyJudgmentDebtorVO(DataCompanyJudgmentDebtorDO dataCompanyJudgmentDebtorDO);

	/**
	 * 批量转换
	 * @param dataCompanyJudgmentDebtorDOs
	 * @return
	 */
	public abstract List<DataCompanyJudgmentDebtorVO> dataCompanyJudgmentDebtorDOsToDataCompanyJudgmentDebtorVOs(List<DataCompanyJudgmentDebtorDO> dataCompanyJudgmentDebtorDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDebtorVO> infrastructurePageToPageResponse(Page<DataCompanyJudgmentDebtorDO> page) {
		return PageResponse.of(dataCompanyJudgmentDebtorDOsToDataCompanyJudgmentDebtorVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyJudgmentDebtorDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyJudgmentDebtorPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyJudgmentDebtorPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyJudgmentDebtorQueryListCommand) {
			return queryListCommandToDO(((DataCompanyJudgmentDebtorQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyJudgmentDebtorDO pageQueryCommandToDO(DataCompanyJudgmentDebtorPageQueryCommand dataCompanyJudgmentDebtorPageQueryCommand);

	public abstract DataCompanyJudgmentDebtorDO queryListCommandToDO(DataCompanyJudgmentDebtorQueryListCommand dataCompanyJudgmentDebtorQueryListCommand);
    public abstract DataCompanyJudgmentDebtorExWarehouseVO dataCompanyJudgmentDebtorDOToDataCompanyJudgmentDebtorExWarehouseVO(DataCompanyJudgmentDebtorDO dataCompanyJudgmentDebtorDO);
    public abstract List<DataCompanyJudgmentDebtorExWarehouseVO> dataCompanyJudgmentDebtorDOsToDataCompanyJudgmentDebtorExWarehouseVOs(List<DataCompanyJudgmentDebtorDO> dataCompanyJudgmentDebtorDOs);
	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> infrastructureExWarehousePageToPageResponse(Page<DataCompanyJudgmentDebtorDO> page) {
		return PageResponse.of(dataCompanyJudgmentDebtorDOsToDataCompanyJudgmentDebtorExWarehouseVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
