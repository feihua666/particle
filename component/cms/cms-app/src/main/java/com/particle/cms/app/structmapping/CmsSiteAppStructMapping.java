package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.domain.CmsSite;
import com.particle.cms.domain.CmsSiteId;
import com.particle.cms.infrastructure.dos.CmsSiteDO;
import com.particle.cms.client.dto.command.representation.CmsSitePageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsSiteQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 站点 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsSiteAppStructMapping  implements IBaseQueryCommandMapStruct<CmsSiteDO>{
	public static CmsSiteAppStructMapping instance = Mappers.getMapper( CmsSiteAppStructMapping.class );

	protected Long map(CmsSiteId cmsSiteId){
		if (cmsSiteId == null) {
			return null;
		}
		return cmsSiteId.getId();
	}
	/**
	 * 站点领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsSiteAppStructMapping#map(CmsSiteId)}
	 * @param cmsSite
	 * @return
	 */
	public abstract CmsSiteVO toCmsSiteVO(CmsSite cmsSite);


	/**
	 * 数据对象转视图对象
	 * @param cmsSiteDO
	 * @return
	 */
	public abstract CmsSiteVO cmsSiteDOToCmsSiteVO(CmsSiteDO cmsSiteDO);

	/**
	 * 批量转换
	 * @param cmsSiteDOs
	 * @return
	 */
	public abstract List<CmsSiteVO> cmsSiteDOsToCmsSiteVOs(List<CmsSiteDO> cmsSiteDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsSiteVO> infrastructurePageToPageResponse(Page<CmsSiteDO> page) {
		return PageResponse.of(cmsSiteDOsToCmsSiteVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsSiteDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsSitePageQueryCommand) {
			return pageQueryCommandToDO((CmsSitePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsSiteQueryListCommand) {
			return queryListCommandToDO(((CmsSiteQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsSiteDO pageQueryCommandToDO(CmsSitePageQueryCommand cmsSitePageQueryCommand);

	public abstract CmsSiteDO queryListCommandToDO(CmsSiteQueryListCommand cmsSiteQueryListCommand);
}
