package com.particle.crm.app.customer.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;
import com.particle.crm.domain.customer.CrmCustomer;
import com.particle.crm.domain.customer.CrmCustomerId;
import com.particle.crm.infrastructure.customer.dos.CrmCustomerDO;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 客户 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCustomerAppStructMapping  implements IBaseQueryCommandMapStruct<CrmCustomerDO>{
	public static CrmCustomerAppStructMapping instance = Mappers.getMapper( CrmCustomerAppStructMapping.class );

	protected Long map(CrmCustomerId crmCustomerId){
		if (crmCustomerId == null) {
			return null;
		}
		return crmCustomerId.getId();
	}
	/**
	 * 客户领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerAppStructMapping#map(CrmCustomerId)}
	 * @param crmCustomer
	 * @return
	 */
	public abstract CrmCustomerVO toCrmCustomerVO(CrmCustomer crmCustomer);


	/**
	 * 数据对象转视图对象
	 * @param crmCustomerDO
	 * @return
	 */
	public abstract CrmCustomerVO crmCustomerDOToCrmCustomerVO(CrmCustomerDO crmCustomerDO);

	/**
	 * 批量转换
	 * @param crmCustomerDOs
	 * @return
	 */
	public abstract List<CrmCustomerVO> crmCustomerDOsToCrmCustomerVOs(List<CrmCustomerDO> crmCustomerDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrmCustomerVO> infrastructurePageToPageResponse(Page<CrmCustomerDO> page) {
		return PageResponse.of(crmCustomerDOsToCrmCustomerVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrmCustomerDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrmCustomerPageQueryCommand) {
			return pageQueryCommandToDO((CrmCustomerPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrmCustomerQueryListCommand) {
			return queryListCommandToDO(((CrmCustomerQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrmCustomerDO pageQueryCommandToDO(CrmCustomerPageQueryCommand crmCustomerPageQueryCommand);

	public abstract CrmCustomerDO queryListCommandToDO(CrmCustomerQueryListCommand crmCustomerQueryListCommand);
}
