package com.particle.cms.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;
import com.particle.cms.domain.CmsSiteIndexViewRecord;
import com.particle.cms.domain.CmsSiteIndexViewRecordId;
import com.particle.cms.infrastructure.dos.CmsSiteIndexViewRecordDO;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 站点首页访问记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CmsSiteIndexViewRecordAppStructMapping  implements IBaseQueryCommandMapStruct<CmsSiteIndexViewRecordDO>{
	public static CmsSiteIndexViewRecordAppStructMapping instance = Mappers.getMapper( CmsSiteIndexViewRecordAppStructMapping.class );

	protected Long map(CmsSiteIndexViewRecordId cmsSiteIndexViewRecordId){
		if (cmsSiteIndexViewRecordId == null) {
			return null;
		}
		return cmsSiteIndexViewRecordId.getId();
	}
	/**
	 * 站点首页访问记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link CmsSiteIndexViewRecordAppStructMapping#map(CmsSiteIndexViewRecordId)}
	 * @param cmsSiteIndexViewRecord
	 * @return
	 */
	public abstract CmsSiteIndexViewRecordVO toCmsSiteIndexViewRecordVO(CmsSiteIndexViewRecord cmsSiteIndexViewRecord);


	/**
	 * 数据对象转视图对象
	 * @param cmsSiteIndexViewRecordDO
	 * @return
	 */
	public abstract CmsSiteIndexViewRecordVO cmsSiteIndexViewRecordDOToCmsSiteIndexViewRecordVO(CmsSiteIndexViewRecordDO cmsSiteIndexViewRecordDO);

	/**
	 * 批量转换
	 * @param cmsSiteIndexViewRecordDOs
	 * @return
	 */
	public abstract List<CmsSiteIndexViewRecordVO> cmsSiteIndexViewRecordDOsToCmsSiteIndexViewRecordVOs(List<CmsSiteIndexViewRecordDO> cmsSiteIndexViewRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<CmsSiteIndexViewRecordVO> infrastructurePageToPageResponse(Page<CmsSiteIndexViewRecordDO> page) {
		return PageResponse.of(cmsSiteIndexViewRecordDOsToCmsSiteIndexViewRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public CmsSiteIndexViewRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof CmsSiteIndexViewRecordPageQueryCommand) {
			return pageQueryCommandToDO((CmsSiteIndexViewRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof CmsSiteIndexViewRecordQueryListCommand) {
			return queryListCommandToDO(((CmsSiteIndexViewRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract CmsSiteIndexViewRecordDO pageQueryCommandToDO(CmsSiteIndexViewRecordPageQueryCommand cmsSiteIndexViewRecordPageQueryCommand);

	public abstract CmsSiteIndexViewRecordDO queryListCommandToDO(CmsSiteIndexViewRecordQueryListCommand cmsSiteIndexViewRecordQueryListCommand);
}
