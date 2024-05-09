package com.particle.crm.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.crm.domain.company.CrmCompany;
import com.particle.crm.domain.company.CrmCompanyId;
import com.particle.crm.infrastructure.company.dos.CrmCompanyDO;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyPageQueryCommand;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 客户公司 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CrmCompanyAppStructMapping  implements IBaseQueryCommandMapStruct<CrmCompanyDO>{
	public static CrmCompanyAppStructMapping instance = Mappers.getMapper( CrmCompanyAppStructMapping.class );

	protected Long map(CrmCompanyId crmCompanyId){
		if (crmCompanyId == null) {
			return null;
		}
		return crmCompanyId.getId();
	}
	/**
	 * 客户公司领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CrmCompanyAppStructMapping#map(CrmCompanyId)}
	 * @param crmCompany
	 * @return
	 */
	public abstract CrmCompanyVO toCrmCompanyVO(CrmCompany crmCompany);


	/**
	 * 数据对象转视图对象
	 * @param crmCompanyDO
	 * @return
	 */
	public abstract CrmCompanyVO crmCompanyDOToCrmCompanyVO(CrmCompanyDO crmCompanyDO);

	/**
	 * 批量转换
	 * @param crmCompanyDOs
	 * @return
	 */
	public abstract List<CrmCompanyVO> crmCompanyDOsToCrmCompanyVOs(List<CrmCompanyDO> crmCompanyDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CrmCompanyVO> infrastructurePageToPageResponse(Page<CrmCompanyDO> page) {
		return PageResponse.of(crmCompanyDOsToCrmCompanyVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CrmCompanyDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CrmCompanyPageQueryCommand) {
			return pageQueryCommandToDO((CrmCompanyPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CrmCompanyQueryListCommand) {
			return queryListCommandToDO(((CrmCompanyQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CrmCompanyDO pageQueryCommandToDO(CrmCompanyPageQueryCommand crmCompanyPageQueryCommand);

	public abstract CrmCompanyDO queryListCommandToDO(CrmCompanyQueryListCommand crmCompanyQueryListCommand);
}
