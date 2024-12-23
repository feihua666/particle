package com.particle.crm.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.crm.client.company.dto.command.representation.CrmDeptPageQueryCommand;
import com.particle.crm.client.company.dto.command.representation.CrmDeptQueryListCommand;
import com.particle.crm.client.company.dto.data.CrmDeptVO;
import com.particle.crm.domain.company.CrmDept;
import com.particle.crm.domain.company.CrmDeptId;
import com.particle.crm.infrastructure.company.dos.CrmDeptDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 客户公司部门 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmDeptAppStructMapping  implements IBaseQueryCommandMapStruct<CrmDeptDO>{
	public static CrmDeptAppStructMapping instance = Mappers.getMapper( CrmDeptAppStructMapping.class );

	protected Long map(CrmDeptId crmDeptId){
		if (crmDeptId == null) {
			return null;
		}
		return crmDeptId.getId();
	}
	/**
	 * 客户公司部门领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmDeptAppStructMapping#map(CrmDeptId)}
	 * @param crmDept
	 * @return
	 */
	public abstract CrmDeptVO toCrmDeptVO(CrmDept crmDept);


	/**
	 * 数据对象转视图对象
	 * @param crmDeptDO
	 * @return
	 */
	public abstract CrmDeptVO crmDeptDOToCrmDeptVO(CrmDeptDO crmDeptDO);

	/**
	 * 批量转换
	 * @param crmDeptDOs
	 * @return
	 */
	public abstract List<CrmDeptVO> crmDeptDOsToCrmDeptVOs(List<CrmDeptDO> crmDeptDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrmDeptVO> infrastructurePageToPageResponse(Page<CrmDeptDO> page) {
		return PageResponse.of(crmDeptDOsToCrmDeptVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrmDeptDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrmDeptPageQueryCommand) {
			return pageQueryCommandToDO((CrmDeptPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrmDeptQueryListCommand) {
			return queryListCommandToDO(((CrmDeptQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrmDeptDO pageQueryCommandToDO(CrmDeptPageQueryCommand crmDeptPageQueryCommand);

	public abstract CrmDeptDO queryListCommandToDO(CrmDeptQueryListCommand crmDeptQueryListCommand);
}
