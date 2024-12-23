package com.particle.crm.app.relation.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefinePageQueryCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefineQueryListCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;
import com.particle.crm.domain.relation.CrmCustomerRelationDefine;
import com.particle.crm.domain.relation.CrmCustomerRelationDefineId;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDefineDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 客户关系定义 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCustomerRelationDefineAppStructMapping  implements IBaseQueryCommandMapStruct<CrmCustomerRelationDefineDO>{
	public static CrmCustomerRelationDefineAppStructMapping instance = Mappers.getMapper( CrmCustomerRelationDefineAppStructMapping.class );

	protected Long map(CrmCustomerRelationDefineId crmCustomerRelationDefineId){
		if (crmCustomerRelationDefineId == null) {
			return null;
		}
		return crmCustomerRelationDefineId.getId();
	}
	/**
	 * 客户关系定义领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerRelationDefineAppStructMapping#map(CrmCustomerRelationDefineId)}
	 * @param crmCustomerRelationDefine
	 * @return
	 */
	public abstract CrmCustomerRelationDefineVO toCrmCustomerRelationDefineVO(CrmCustomerRelationDefine crmCustomerRelationDefine);


	/**
	 * 数据对象转视图对象
	 * @param crmCustomerRelationDefineDO
	 * @return
	 */
	public abstract CrmCustomerRelationDefineVO crmCustomerRelationDefineDOToCrmCustomerRelationDefineVO(CrmCustomerRelationDefineDO crmCustomerRelationDefineDO);

	/**
	 * 批量转换
	 * @param crmCustomerRelationDefineDOs
	 * @return
	 */
	public abstract List<CrmCustomerRelationDefineVO> crmCustomerRelationDefineDOsToCrmCustomerRelationDefineVOs(List<CrmCustomerRelationDefineDO> crmCustomerRelationDefineDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrmCustomerRelationDefineVO> infrastructurePageToPageResponse(Page<CrmCustomerRelationDefineDO> page) {
		return PageResponse.of(crmCustomerRelationDefineDOsToCrmCustomerRelationDefineVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrmCustomerRelationDefineDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrmCustomerRelationDefinePageQueryCommand) {
			return pageQueryCommandToDO((CrmCustomerRelationDefinePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrmCustomerRelationDefineQueryListCommand) {
			return queryListCommandToDO(((CrmCustomerRelationDefineQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrmCustomerRelationDefineDO pageQueryCommandToDO(CrmCustomerRelationDefinePageQueryCommand crmCustomerRelationDefinePageQueryCommand);

	public abstract CrmCustomerRelationDefineDO queryListCommandToDO(CrmCustomerRelationDefineQueryListCommand crmCustomerRelationDefineQueryListCommand);
}
