package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsTemplateVO;
import com.particle.cms.domain.CmsTemplate;
import com.particle.cms.domain.CmsTemplateId;
import com.particle.cms.infrastructure.dos.CmsTemplateDO;
import com.particle.cms.client.dto.command.representation.CmsTemplatePageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplateQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 模板 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsTemplateAppStructMapping  implements IBaseQueryCommandMapStruct<CmsTemplateDO>{
	public static CmsTemplateAppStructMapping instance = Mappers.getMapper( CmsTemplateAppStructMapping.class );

	protected Long map(CmsTemplateId cmsTemplateId){
		if (cmsTemplateId == null) {
			return null;
		}
		return cmsTemplateId.getId();
	}
	/**
	 * 模板领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsTemplateAppStructMapping#map(CmsTemplateId)}
	 * @param cmsTemplate
	 * @return
	 */
	public abstract CmsTemplateVO toCmsTemplateVO(CmsTemplate cmsTemplate);


	/**
	 * 数据对象转视图对象
	 * @param cmsTemplateDO
	 * @return
	 */
	public abstract CmsTemplateVO cmsTemplateDOToCmsTemplateVO(CmsTemplateDO cmsTemplateDO);

	/**
	 * 批量转换
	 * @param cmsTemplateDOs
	 * @return
	 */
	public abstract List<CmsTemplateVO> cmsTemplateDOsToCmsTemplateVOs(List<CmsTemplateDO> cmsTemplateDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsTemplateVO> infrastructurePageToPageResponse(Page<CmsTemplateDO> page) {
		return PageResponse.of(cmsTemplateDOsToCmsTemplateVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsTemplatePageQueryCommand) {
			return pageQueryCommandToDO((CmsTemplatePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsTemplateQueryListCommand) {
			return queryListCommandToDO(((CmsTemplateQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsTemplateDO pageQueryCommandToDO(CmsTemplatePageQueryCommand cmsTemplatePageQueryCommand);

	public abstract CmsTemplateDO queryListCommandToDO(CmsTemplateQueryListCommand cmsTemplateQueryListCommand);
}
