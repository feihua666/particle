package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.domain.CmsContent;
import com.particle.cms.domain.CmsContentId;
import com.particle.cms.infrastructure.dos.CmsContentDO;
import com.particle.cms.client.dto.command.representation.CmsContentPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsContentAppStructMapping  implements IBaseQueryCommandMapStruct<CmsContentDO>{
	public static CmsContentAppStructMapping instance = Mappers.getMapper( CmsContentAppStructMapping.class );

	protected Long map(CmsContentId cmsContentId){
		if (cmsContentId == null) {
			return null;
		}
		return cmsContentId.getId();
	}
	/**
	 * 内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentAppStructMapping#map(CmsContentId)}
	 * @param cmsContent
	 * @return
	 */
	public abstract CmsContentVO toCmsContentVO(CmsContent cmsContent);


	/**
	 * 数据对象转视图对象
	 * @param cmsContentDO
	 * @return
	 */
	public abstract CmsContentVO cmsContentDOToCmsContentVO(CmsContentDO cmsContentDO);

	/**
	 * 批量转换
	 * @param cmsContentDOs
	 * @return
	 */
	public abstract List<CmsContentVO> cmsContentDOsToCmsContentVOs(List<CmsContentDO> cmsContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsContentVO> infrastructurePageToPageResponse(Page<CmsContentDO> page) {
		return PageResponse.of(cmsContentDOsToCmsContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsContentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsContentPageQueryCommand) {
			return pageQueryCommandToDO((CmsContentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsContentQueryListCommand) {
			return queryListCommandToDO(((CmsContentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsContentDO pageQueryCommandToDO(CmsContentPageQueryCommand cmsContentPageQueryCommand);

	public abstract CmsContentDO queryListCommandToDO(CmsContentQueryListCommand cmsContentQueryListCommand);
}
