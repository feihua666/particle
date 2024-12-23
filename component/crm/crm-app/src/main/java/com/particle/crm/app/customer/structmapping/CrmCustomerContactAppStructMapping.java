package com.particle.crm.app.customer.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactQueryListCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
import com.particle.crm.domain.customer.CrmCustomerContact;
import com.particle.crm.domain.customer.CrmCustomerContactId;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerContactDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 客户联系方式 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCustomerContactAppStructMapping  implements IBaseQueryCommandMapStruct<CrmCustomerContactDO>{
	public static CrmCustomerContactAppStructMapping instance = Mappers.getMapper( CrmCustomerContactAppStructMapping.class );

	protected Long map(CrmCustomerContactId crmCustomerContactId){
		if (crmCustomerContactId == null) {
			return null;
		}
		return crmCustomerContactId.getId();
	}
	/**
	 * 客户联系方式领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerContactAppStructMapping#map(CrmCustomerContactId)}
	 * @param crmCustomerContact
	 * @return
	 */
	public abstract CrmCustomerContactVO toCrmCustomerContactVO(CrmCustomerContact crmCustomerContact);


	/**
	 * 数据对象转视图对象
	 * @param crmCustomerContactDO
	 * @return
	 */
	public abstract CrmCustomerContactVO crmCustomerContactDOToCrmCustomerContactVO(CrmCustomerContactDO crmCustomerContactDO);

	/**
	 * 批量转换
	 * @param crmCustomerContactDOs
	 * @return
	 */
	public abstract List<CrmCustomerContactVO> crmCustomerContactDOsToCrmCustomerContactVOs(List<CrmCustomerContactDO> crmCustomerContactDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrmCustomerContactVO> infrastructurePageToPageResponse(Page<CrmCustomerContactDO> page) {
		return PageResponse.of(crmCustomerContactDOsToCrmCustomerContactVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrmCustomerContactDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrmCustomerContactPageQueryCommand) {
			return pageQueryCommandToDO((CrmCustomerContactPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrmCustomerContactQueryListCommand) {
			return queryListCommandToDO(((CrmCustomerContactQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrmCustomerContactDO pageQueryCommandToDO(CrmCustomerContactPageQueryCommand crmCustomerContactPageQueryCommand);

	public abstract CrmCustomerContactDO queryListCommandToDO(CrmCustomerContactQueryListCommand crmCustomerContactQueryListCommand);
}
