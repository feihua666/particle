package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;
import com.particle.cms.domain.CmsChannelViewRecord;
import com.particle.cms.domain.CmsChannelViewRecordId;
import com.particle.cms.infrastructure.dos.CmsChannelViewRecordDO;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 栏目访问记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsChannelViewRecordAppStructMapping  implements IBaseQueryCommandMapStruct<CmsChannelViewRecordDO>{
	public static CmsChannelViewRecordAppStructMapping instance = Mappers.getMapper( CmsChannelViewRecordAppStructMapping.class );

	protected Long map(CmsChannelViewRecordId cmsChannelViewRecordId){
		if (cmsChannelViewRecordId == null) {
			return null;
		}
		return cmsChannelViewRecordId.getId();
	}
	/**
	 * 栏目访问记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsChannelViewRecordAppStructMapping#map(CmsChannelViewRecordId)}
	 * @param cmsChannelViewRecord
	 * @return
	 */
	public abstract CmsChannelViewRecordVO toCmsChannelViewRecordVO(CmsChannelViewRecord cmsChannelViewRecord);


	/**
	 * 数据对象转视图对象
	 * @param cmsChannelViewRecordDO
	 * @return
	 */
	public abstract CmsChannelViewRecordVO cmsChannelViewRecordDOToCmsChannelViewRecordVO(CmsChannelViewRecordDO cmsChannelViewRecordDO);

	/**
	 * 批量转换
	 * @param cmsChannelViewRecordDOs
	 * @return
	 */
	public abstract List<CmsChannelViewRecordVO> cmsChannelViewRecordDOsToCmsChannelViewRecordVOs(List<CmsChannelViewRecordDO> cmsChannelViewRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsChannelViewRecordVO> infrastructurePageToPageResponse(Page<CmsChannelViewRecordDO> page) {
		return PageResponse.of(cmsChannelViewRecordDOsToCmsChannelViewRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsChannelViewRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsChannelViewRecordPageQueryCommand) {
			return pageQueryCommandToDO((CmsChannelViewRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsChannelViewRecordQueryListCommand) {
			return queryListCommandToDO(((CmsChannelViewRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsChannelViewRecordDO pageQueryCommandToDO(CmsChannelViewRecordPageQueryCommand cmsChannelViewRecordPageQueryCommand);

	public abstract CmsChannelViewRecordDO queryListCommandToDO(CmsChannelViewRecordQueryListCommand cmsChannelViewRecordQueryListCommand);
}
