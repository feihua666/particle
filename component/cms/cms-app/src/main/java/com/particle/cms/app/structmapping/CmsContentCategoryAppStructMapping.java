package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;
import com.particle.cms.domain.CmsContentCategory;
import com.particle.cms.domain.CmsContentCategoryId;
import com.particle.cms.infrastructure.dos.CmsContentCategoryDO;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 内容分类 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsContentCategoryAppStructMapping  implements IBaseQueryCommandMapStruct<CmsContentCategoryDO>{
	public static CmsContentCategoryAppStructMapping instance = Mappers.getMapper( CmsContentCategoryAppStructMapping.class );

	protected Long map(CmsContentCategoryId cmsContentCategoryId){
		if (cmsContentCategoryId == null) {
			return null;
		}
		return cmsContentCategoryId.getId();
	}
	/**
	 * 内容分类领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentCategoryAppStructMapping#map(CmsContentCategoryId)}
	 * @param cmsContentCategory
	 * @return
	 */
	public abstract CmsContentCategoryVO toCmsContentCategoryVO(CmsContentCategory cmsContentCategory);


	/**
	 * 数据对象转视图对象
	 * @param cmsContentCategoryDO
	 * @return
	 */
	public abstract CmsContentCategoryVO cmsContentCategoryDOToCmsContentCategoryVO(CmsContentCategoryDO cmsContentCategoryDO);

	/**
	 * 批量转换
	 * @param cmsContentCategoryDOs
	 * @return
	 */
	public abstract List<CmsContentCategoryVO> cmsContentCategoryDOsToCmsContentCategoryVOs(List<CmsContentCategoryDO> cmsContentCategoryDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsContentCategoryVO> infrastructurePageToPageResponse(Page<CmsContentCategoryDO> page) {
		return PageResponse.of(cmsContentCategoryDOsToCmsContentCategoryVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsContentCategoryDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsContentCategoryPageQueryCommand) {
			return pageQueryCommandToDO((CmsContentCategoryPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsContentCategoryQueryListCommand) {
			return queryListCommandToDO(((CmsContentCategoryQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsContentCategoryDO pageQueryCommandToDO(CmsContentCategoryPageQueryCommand cmsContentCategoryPageQueryCommand);

	public abstract CmsContentCategoryDO queryListCommandToDO(CmsContentCategoryQueryListCommand cmsContentCategoryQueryListCommand);
}
