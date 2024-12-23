package com.particle.crm.app.tag.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagPageQueryCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagQueryListCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;
import com.particle.crm.domain.tag.CrmCustomerTag;
import com.particle.crm.domain.tag.CrmCustomerTagId;
import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 客户标签 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCustomerTagAppStructMapping  implements IBaseQueryCommandMapStruct<CrmCustomerTagDO>{
	public static CrmCustomerTagAppStructMapping instance = Mappers.getMapper( CrmCustomerTagAppStructMapping.class );

	protected Long map(CrmCustomerTagId crmCustomerTagId){
		if (crmCustomerTagId == null) {
			return null;
		}
		return crmCustomerTagId.getId();
	}
	/**
	 * 客户标签领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCustomerTagAppStructMapping#map(CrmCustomerTagId)}
	 * @param crmCustomerTag
	 * @return
	 */
	public abstract CrmCustomerTagVO toCrmCustomerTagVO(CrmCustomerTag crmCustomerTag);


	/**
	 * 数据对象转视图对象
	 * @param crmCustomerTagDO
	 * @return
	 */
	public abstract CrmCustomerTagVO crmCustomerTagDOToCrmCustomerTagVO(CrmCustomerTagDO crmCustomerTagDO);

	/**
	 * 批量转换
	 * @param crmCustomerTagDOs
	 * @return
	 */
	public abstract List<CrmCustomerTagVO> crmCustomerTagDOsToCrmCustomerTagVOs(List<CrmCustomerTagDO> crmCustomerTagDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrmCustomerTagVO> infrastructurePageToPageResponse(Page<CrmCustomerTagDO> page) {
		return PageResponse.of(crmCustomerTagDOsToCrmCustomerTagVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrmCustomerTagDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrmCustomerTagPageQueryCommand) {
			return pageQueryCommandToDO((CrmCustomerTagPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrmCustomerTagQueryListCommand) {
			return queryListCommandToDO(((CrmCustomerTagQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrmCustomerTagDO pageQueryCommandToDO(CrmCustomerTagPageQueryCommand crmCustomerTagPageQueryCommand);

	public abstract CrmCustomerTagDO queryListCommandToDO(CrmCustomerTagQueryListCommand crmCustomerTagQueryListCommand);
}
