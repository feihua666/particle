package com.particle.openplatform.app.openapi.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRule;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRuleId;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiLimitRuleDO;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRulePageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRuleQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台开放接口限制规则 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformOpenapiLimitRuleAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformOpenapiLimitRuleDO>{
	public static OpenplatformOpenapiLimitRuleAppStructMapping instance = Mappers.getMapper( OpenplatformOpenapiLimitRuleAppStructMapping.class );

	protected Long map(OpenplatformOpenapiLimitRuleId openplatformOpenapiLimitRuleId){
		if (openplatformOpenapiLimitRuleId == null) {
			return null;
		}
		return openplatformOpenapiLimitRuleId.getId();
	}
	/**
	 * 开放平台开放接口限制规则领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformOpenapiLimitRuleAppStructMapping#map(OpenplatformOpenapiLimitRuleId)}
	 * @param openplatformOpenapiLimitRule
	 * @return
	 */
	public abstract OpenplatformOpenapiLimitRuleVO toOpenplatformOpenapiLimitRuleVO(OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule);


	/**
	 * 数据对象转视图对象
	 * @param openplatformOpenapiLimitRuleDO
	 * @return
	 */
	public abstract OpenplatformOpenapiLimitRuleVO openplatformOpenapiLimitRuleDOToOpenplatformOpenapiLimitRuleVO(OpenplatformOpenapiLimitRuleDO openplatformOpenapiLimitRuleDO);

	/**
	 * 批量转换
	 * @param openplatformOpenapiLimitRuleDOs
	 * @return
	 */
	public abstract List<OpenplatformOpenapiLimitRuleVO> openplatformOpenapiLimitRuleDOsToOpenplatformOpenapiLimitRuleVOs(List<OpenplatformOpenapiLimitRuleDO> openplatformOpenapiLimitRuleDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiLimitRuleVO> infrastructurePageToPageResponse(Page<OpenplatformOpenapiLimitRuleDO> page) {
		return PageResponse.of(openplatformOpenapiLimitRuleDOsToOpenplatformOpenapiLimitRuleVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformOpenapiLimitRuleDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformOpenapiLimitRulePageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformOpenapiLimitRulePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformOpenapiLimitRuleQueryListCommand) {
			return queryListCommandToDO(((OpenplatformOpenapiLimitRuleQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformOpenapiLimitRuleDO pageQueryCommandToDO(OpenplatformOpenapiLimitRulePageQueryCommand openplatformOpenapiLimitRulePageQueryCommand);

	public abstract OpenplatformOpenapiLimitRuleDO queryListCommandToDO(OpenplatformOpenapiLimitRuleQueryListCommand openplatformOpenapiLimitRuleQueryListCommand);
}
