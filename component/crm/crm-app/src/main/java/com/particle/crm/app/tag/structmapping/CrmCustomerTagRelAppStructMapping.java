package com.particle.crm.app.tag.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelPageQueryCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelQueryListCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;
import com.particle.crm.domain.tag.CrmCustomerTagRel;
import com.particle.crm.domain.tag.CrmCustomerTagRelId;
import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagRelDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 客户标签关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCustomerTagRelAppStructMapping  implements IBaseQueryCommandMapStruct<CrmCustomerTagRelDO>{
	public static CrmCustomerTagRelAppStructMapping instance = Mappers.getMapper( CrmCustomerTagRelAppStructMapping.class );

	protected Long map(CrmCustomerTagRelId crmCustomerTagRelId){
		if (crmCustomerTagRelId == null) {
			return null;
		}
		return crmCustomerTagRelId.getId();
	}
	/**
	 * 客户标签关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerTagRelAppStructMapping#map(CrmCustomerTagRelId)}
	 * @param crmCustomerTagRel
	 * @return
	 */
	public abstract CrmCustomerTagRelVO toCrmCustomerTagRelVO(CrmCustomerTagRel crmCustomerTagRel);


	/**
	 * 数据对象转视图对象
	 * @param crmCustomerTagRelDO
	 * @return
	 */
	public abstract CrmCustomerTagRelVO crmCustomerTagRelDOToCrmCustomerTagRelVO(CrmCustomerTagRelDO crmCustomerTagRelDO);

	/**
	 * 批量转换
	 * @param crmCustomerTagRelDOs
	 * @return
	 */
	public abstract List<CrmCustomerTagRelVO> crmCustomerTagRelDOsToCrmCustomerTagRelVOs(List<CrmCustomerTagRelDO> crmCustomerTagRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrmCustomerTagRelVO> infrastructurePageToPageResponse(Page<CrmCustomerTagRelDO> page) {
		return PageResponse.of(crmCustomerTagRelDOsToCrmCustomerTagRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrmCustomerTagRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrmCustomerTagRelPageQueryCommand) {
			return pageQueryCommandToDO((CrmCustomerTagRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrmCustomerTagRelQueryListCommand) {
			return queryListCommandToDO(((CrmCustomerTagRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrmCustomerTagRelDO pageQueryCommandToDO(CrmCustomerTagRelPageQueryCommand crmCustomerTagRelPageQueryCommand);

	public abstract CrmCustomerTagRelDO queryListCommandToDO(CrmCustomerTagRelQueryListCommand crmCustomerTagRelQueryListCommand);
}
