package com.particle.agi.app.agent.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.agi.client.agent.dto.data.AgiAgentVO;
import com.particle.agi.domain.agent.AgiAgent;
import com.particle.agi.domain.agent.AgiAgentId;
import com.particle.agi.infrastructure.agent.dos.AgiAgentDO;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentPageQueryCommand;
import com.particle.agi.client.agent.dto.command.representation.AgiAgentQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 智能体 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2025-02-06 15:50:10
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AgiAgentAppStructMapping  implements IBaseQueryCommandMapStruct<AgiAgentDO>{
	public static AgiAgentAppStructMapping instance = Mappers.getMapper( AgiAgentAppStructMapping.class );

	protected Long map(AgiAgentId agiAgentId){
		if (agiAgentId == null) {
			return null;
		}
		return agiAgentId.getId();
	}
	/**
	 * 智能体领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AgiAgentAppStructMapping#map(AgiAgentId)}
	 * @param agiAgent
	 * @return
	 */
	public abstract AgiAgentVO toAgiAgentVO(AgiAgent agiAgent);


	/**
	 * 数据对象转视图对象
	 * @param agiAgentDO
	 * @return
	 */
	public abstract AgiAgentVO agiAgentDOToAgiAgentVO(AgiAgentDO agiAgentDO);

	/**
	 * 批量转换
	 * @param agiAgentDOs
	 * @return
	 */
	public abstract List<AgiAgentVO> agiAgentDOsToAgiAgentVOs(List<AgiAgentDO> agiAgentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AgiAgentVO> infrastructurePageToPageResponse(Page<AgiAgentDO> page) {
		return PageResponse.of(agiAgentDOsToAgiAgentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public AgiAgentDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AgiAgentPageQueryCommand) {
			return pageQueryCommandToDO((AgiAgentPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AgiAgentQueryListCommand) {
			return queryListCommandToDO(((AgiAgentQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract AgiAgentDO pageQueryCommandToDO(AgiAgentPageQueryCommand agiAgentPageQueryCommand);

	public abstract AgiAgentDO queryListCommandToDO(AgiAgentQueryListCommand agiAgentQueryListCommand);
}
