package com.particle.agi.app.model.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;
import com.particle.agi.domain.model.AgiModelProvider;
import com.particle.agi.domain.model.AgiModelProviderId;
import com.particle.agi.infrastructure.model.dos.AgiModelProviderDO;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderPageQueryCommand;
import com.particle.agi.client.model.dto.command.representation.AgiModelProviderQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * AI模型提供商 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiModelProviderAppStructMapping  implements IBaseQueryCommandMapStruct<AgiModelProviderDO>{
	public static AgiModelProviderAppStructMapping instance = Mappers.getMapper( AgiModelProviderAppStructMapping.class );

	protected Long map(AgiModelProviderId agiModelProviderId){
		if (agiModelProviderId == null) {
			return null;
		}
		return agiModelProviderId.getId();
	}
	/**
	 * AI模型提供商领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiModelProviderAppStructMapping#map(AgiModelProviderId)}
	 * @param agiModelProvider
	 * @return
	 */
	public abstract AgiModelProviderVO toAgiModelProviderVO(AgiModelProvider agiModelProvider);


	/**
	 * 数据对象转视图对象
	 * @param agiModelProviderDO
	 * @return
	 */
	public abstract AgiModelProviderVO agiModelProviderDOToAgiModelProviderVO(AgiModelProviderDO agiModelProviderDO);

	/**
	 * 批量转换
	 * @param agiModelProviderDOs
	 * @return
	 */
	public abstract List<AgiModelProviderVO> agiModelProviderDOsToAgiModelProviderVOs(List<AgiModelProviderDO> agiModelProviderDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiModelProviderVO> infrastructurePageToPageResponse(Page<AgiModelProviderDO> page) {
		return PageResponse.of(agiModelProviderDOsToAgiModelProviderVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiModelProviderDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiModelProviderPageQueryCommand) {
			return pageQueryCommandToDO((AgiModelProviderPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiModelProviderQueryListCommand) {
			return queryListCommandToDO(((AgiModelProviderQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiModelProviderDO pageQueryCommandToDO(AgiModelProviderPageQueryCommand agiModelProviderPageQueryCommand);

	public abstract AgiModelProviderDO queryListCommandToDO(AgiModelProviderQueryListCommand agiModelProviderQueryListCommand);
}
