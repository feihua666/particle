package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import com.particle.cms.domain.CmsContentMultimedia;
import com.particle.cms.domain.CmsContentMultimediaId;
import com.particle.cms.infrastructure.dos.CmsContentMultimediaDO;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 内容多媒体 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsContentMultimediaAppStructMapping  implements IBaseQueryCommandMapStruct<CmsContentMultimediaDO>{
	public static CmsContentMultimediaAppStructMapping instance = Mappers.getMapper( CmsContentMultimediaAppStructMapping.class );

	protected Long map(CmsContentMultimediaId cmsContentMultimediaId){
		if (cmsContentMultimediaId == null) {
			return null;
		}
		return cmsContentMultimediaId.getId();
	}
	/**
	 * 内容多媒体领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentMultimediaAppStructMapping#map(CmsContentMultimediaId)}
	 * @param cmsContentMultimedia
	 * @return
	 */
	public abstract CmsContentMultimediaVO toCmsContentMultimediaVO(CmsContentMultimedia cmsContentMultimedia);


	/**
	 * 数据对象转视图对象
	 * @param cmsContentMultimediaDO
	 * @return
	 */
	public abstract CmsContentMultimediaVO cmsContentMultimediaDOToCmsContentMultimediaVO(CmsContentMultimediaDO cmsContentMultimediaDO);

	/**
	 * 批量转换
	 * @param cmsContentMultimediaDOs
	 * @return
	 */
	public abstract List<CmsContentMultimediaVO> cmsContentMultimediaDOsToCmsContentMultimediaVOs(List<CmsContentMultimediaDO> cmsContentMultimediaDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsContentMultimediaVO> infrastructurePageToPageResponse(Page<CmsContentMultimediaDO> page) {
		return PageResponse.of(cmsContentMultimediaDOsToCmsContentMultimediaVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsContentMultimediaDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsContentMultimediaPageQueryCommand) {
			return pageQueryCommandToDO((CmsContentMultimediaPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsContentMultimediaQueryListCommand) {
			return queryListCommandToDO(((CmsContentMultimediaQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsContentMultimediaDO pageQueryCommandToDO(CmsContentMultimediaPageQueryCommand cmsContentMultimediaPageQueryCommand);

	public abstract CmsContentMultimediaDO queryListCommandToDO(CmsContentMultimediaQueryListCommand cmsContentMultimediaQueryListCommand);
}
