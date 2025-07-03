package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;
import com.particle.cms.domain.CmsTemplateContent;
import com.particle.cms.domain.CmsTemplateContentId;
import com.particle.cms.infrastructure.dos.CmsTemplateContentDO;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 模板内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsTemplateContentAppStructMapping  implements IBaseQueryCommandMapStruct<CmsTemplateContentDO>{
	public static CmsTemplateContentAppStructMapping instance = Mappers.getMapper( CmsTemplateContentAppStructMapping.class );

	protected Long map(CmsTemplateContentId cmsTemplateContentId){
		if (cmsTemplateContentId == null) {
			return null;
		}
		return cmsTemplateContentId.getId();
	}
	/**
	 * 模板内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsTemplateContentAppStructMapping#map(CmsTemplateContentId)}
	 * @param cmsTemplateContent
	 * @return
	 */
	public abstract CmsTemplateContentVO toCmsTemplateContentVO(CmsTemplateContent cmsTemplateContent);


	/**
	 * 数据对象转视图对象
	 * @param cmsTemplateContentDO
	 * @return
	 */
	public abstract CmsTemplateContentVO cmsTemplateContentDOToCmsTemplateContentVO(CmsTemplateContentDO cmsTemplateContentDO);

	/**
	 * 批量转换
	 * @param cmsTemplateContentDOs
	 * @return
	 */
	public abstract List<CmsTemplateContentVO> cmsTemplateContentDOsToCmsTemplateContentVOs(List<CmsTemplateContentDO> cmsTemplateContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsTemplateContentVO> infrastructurePageToPageResponse(Page<CmsTemplateContentDO> page) {
		return PageResponse.of(cmsTemplateContentDOsToCmsTemplateContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsTemplateContentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsTemplateContentPageQueryCommand) {
			return pageQueryCommandToDO((CmsTemplateContentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsTemplateContentQueryListCommand) {
			return queryListCommandToDO(((CmsTemplateContentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsTemplateContentDO pageQueryCommandToDO(CmsTemplateContentPageQueryCommand cmsTemplateContentPageQueryCommand);

	public abstract CmsTemplateContentDO queryListCommandToDO(CmsTemplateContentQueryListCommand cmsTemplateContentQueryListCommand);
}
