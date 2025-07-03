package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;
import com.particle.cms.domain.CmsContentViewRecord;
import com.particle.cms.domain.CmsContentViewRecordId;
import com.particle.cms.infrastructure.dos.CmsContentViewRecordDO;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 内容访问记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsContentViewRecordAppStructMapping  implements IBaseQueryCommandMapStruct<CmsContentViewRecordDO>{
	public static CmsContentViewRecordAppStructMapping instance = Mappers.getMapper( CmsContentViewRecordAppStructMapping.class );

	protected Long map(CmsContentViewRecordId cmsContentViewRecordId){
		if (cmsContentViewRecordId == null) {
			return null;
		}
		return cmsContentViewRecordId.getId();
	}
	/**
	 * 内容访问记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsContentViewRecordAppStructMapping#map(CmsContentViewRecordId)}
	 * @param cmsContentViewRecord
	 * @return
	 */
	public abstract CmsContentViewRecordVO toCmsContentViewRecordVO(CmsContentViewRecord cmsContentViewRecord);


	/**
	 * 数据对象转视图对象
	 * @param cmsContentViewRecordDO
	 * @return
	 */
	public abstract CmsContentViewRecordVO cmsContentViewRecordDOToCmsContentViewRecordVO(CmsContentViewRecordDO cmsContentViewRecordDO);

	/**
	 * 批量转换
	 * @param cmsContentViewRecordDOs
	 * @return
	 */
	public abstract List<CmsContentViewRecordVO> cmsContentViewRecordDOsToCmsContentViewRecordVOs(List<CmsContentViewRecordDO> cmsContentViewRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsContentViewRecordVO> infrastructurePageToPageResponse(Page<CmsContentViewRecordDO> page) {
		return PageResponse.of(cmsContentViewRecordDOsToCmsContentViewRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsContentViewRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsContentViewRecordPageQueryCommand) {
			return pageQueryCommandToDO((CmsContentViewRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsContentViewRecordQueryListCommand) {
			return queryListCommandToDO(((CmsContentViewRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsContentViewRecordDO pageQueryCommandToDO(CmsContentViewRecordPageQueryCommand cmsContentViewRecordPageQueryCommand);

	public abstract CmsContentViewRecordDO queryListCommandToDO(CmsContentViewRecordQueryListCommand cmsContentViewRecordQueryListCommand);
}
