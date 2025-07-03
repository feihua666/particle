package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsChannelVO;
import com.particle.cms.domain.CmsChannel;
import com.particle.cms.domain.CmsChannelId;
import com.particle.cms.infrastructure.dos.CmsChannelDO;
import com.particle.cms.client.dto.command.representation.CmsChannelPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 栏目 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsChannelAppStructMapping  implements IBaseQueryCommandMapStruct<CmsChannelDO>{
	public static CmsChannelAppStructMapping instance = Mappers.getMapper( CmsChannelAppStructMapping.class );

	protected Long map(CmsChannelId cmsChannelId){
		if (cmsChannelId == null) {
			return null;
		}
		return cmsChannelId.getId();
	}
	/**
	 * 栏目领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsChannelAppStructMapping#map(CmsChannelId)}
	 * @param cmsChannel
	 * @return
	 */
	public abstract CmsChannelVO toCmsChannelVO(CmsChannel cmsChannel);


	/**
	 * 数据对象转视图对象
	 * @param cmsChannelDO
	 * @return
	 */
	public abstract CmsChannelVO cmsChannelDOToCmsChannelVO(CmsChannelDO cmsChannelDO);

	/**
	 * 批量转换
	 * @param cmsChannelDOs
	 * @return
	 */
	public abstract List<CmsChannelVO> cmsChannelDOsToCmsChannelVOs(List<CmsChannelDO> cmsChannelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsChannelVO> infrastructurePageToPageResponse(Page<CmsChannelDO> page) {
		return PageResponse.of(cmsChannelDOsToCmsChannelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsChannelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsChannelPageQueryCommand) {
			return pageQueryCommandToDO((CmsChannelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsChannelQueryListCommand) {
			return queryListCommandToDO(((CmsChannelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsChannelDO pageQueryCommandToDO(CmsChannelPageQueryCommand cmsChannelPageQueryCommand);

	public abstract CmsChannelDO queryListCommandToDO(CmsChannelQueryListCommand cmsChannelQueryListCommand);
}
