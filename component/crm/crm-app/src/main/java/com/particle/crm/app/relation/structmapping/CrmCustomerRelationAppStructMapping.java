package com.particle.crm.app.relation.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;
import com.particle.crm.domain.relation.CrmCustomerRelation;
import com.particle.crm.domain.relation.CrmCustomerRelationId;
import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDO;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationPageQueryCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 客户与客户关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCustomerRelationAppStructMapping  implements IBaseQueryCommandMapStruct<CrmCustomerRelationDO>{
	public static CrmCustomerRelationAppStructMapping instance = Mappers.getMapper( CrmCustomerRelationAppStructMapping.class );

	protected Long map(CrmCustomerRelationId crmCustomerRelationId){
		if (crmCustomerRelationId == null) {
			return null;
		}
		return crmCustomerRelationId.getId();
	}
	/**
	 * 客户与客户关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerRelationAppStructMapping#map(CrmCustomerRelationId)}
	 * @param crmCustomerRelation
	 * @return
	 */
	public abstract CrmCustomerRelationVO toCrmCustomerRelationVO(CrmCustomerRelation crmCustomerRelation);


	/**
	 * 数据对象转视图对象
	 * @param crmCustomerRelationDO
	 * @return
	 */
	public abstract CrmCustomerRelationVO crmCustomerRelationDOToCrmCustomerRelationVO(CrmCustomerRelationDO crmCustomerRelationDO);

	/**
	 * 批量转换
	 * @param crmCustomerRelationDOs
	 * @return
	 */
	public abstract List<CrmCustomerRelationVO> crmCustomerRelationDOsToCrmCustomerRelationVOs(List<CrmCustomerRelationDO> crmCustomerRelationDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrmCustomerRelationVO> infrastructurePageToPageResponse(Page<CrmCustomerRelationDO> page) {
		return PageResponse.of(crmCustomerRelationDOsToCrmCustomerRelationVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrmCustomerRelationDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrmCustomerRelationPageQueryCommand) {
			return pageQueryCommandToDO((CrmCustomerRelationPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrmCustomerRelationQueryListCommand) {
			return queryListCommandToDO(((CrmCustomerRelationQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrmCustomerRelationDO pageQueryCommandToDO(CrmCustomerRelationPageQueryCommand crmCustomerRelationPageQueryCommand);

	public abstract CrmCustomerRelationDO queryListCommandToDO(CrmCustomerRelationQueryListCommand crmCustomerRelationQueryListCommand);
}
