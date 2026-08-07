package com.particle.agi.app.model.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.model.dto.data.AgiAiModelVO;
import com.particle.agi.domain.model.AgiAiModel;
import com.particle.agi.domain.model.AgiAiModelId;
import com.particle.agi.infrastructure.model.dos.AgiAiModelDO;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelPageQueryCommand;
import com.particle.agi.client.model.dto.command.representation.AgiAiModelQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * AI模型 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAiModelAppStructMapping  implements IBaseQueryCommandMapStruct<AgiAiModelDO>{
	public static AgiAiModelAppStructMapping instance = Mappers.getMapper( AgiAiModelAppStructMapping.class );

	protected Long map(AgiAiModelId agiAiModelId){
		if (agiAiModelId == null) {
			return null;
		}
		return agiAiModelId.getId();
	}
	/**
	 * AI模型领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAiModelAppStructMapping#map(AgiAiModelId)}
	 * @param agiAiModel
	 * @return
	 */
	public abstract AgiAiModelVO toAgiAiModelVO(AgiAiModel agiAiModel);


	/**
	 * 数据对象转视图对象
	 * @param agiAiModelDO
	 * @return
	 */
	public abstract AgiAiModelVO agiAiModelDOToAgiAiModelVO(AgiAiModelDO agiAiModelDO);

	/**
	 * 批量转换
	 * @param agiAiModelDOs
	 * @return
	 */
	public abstract List<AgiAiModelVO> agiAiModelDOsToAgiAiModelVOs(List<AgiAiModelDO> agiAiModelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiAiModelVO> infrastructurePageToPageResponse(Page<AgiAiModelDO> page) {
		return PageResponse.of(agiAiModelDOsToAgiAiModelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiAiModelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiAiModelPageQueryCommand) {
			return pageQueryCommandToDO((AgiAiModelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiAiModelQueryListCommand) {
			return queryListCommandToDO(((AgiAiModelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiAiModelDO pageQueryCommandToDO(AgiAiModelPageQueryCommand agiAiModelPageQueryCommand);

	public abstract AgiAiModelDO queryListCommandToDO(AgiAiModelQueryListCommand agiAiModelQueryListCommand);
}
