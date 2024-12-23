package com.particle.usagecount.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;
import com.particle.usagecount.domain.UsageCountConfig;
import com.particle.usagecount.domain.UsageCountConfigId;
import com.particle.usagecount.infrastructure.dos.UsageCountConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 使用次数配置 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UsageCountConfigAppStructMapping  implements IBaseQueryCommandMapStruct<UsageCountConfigDO>{
	public static UsageCountConfigAppStructMapping instance = Mappers.getMapper( UsageCountConfigAppStructMapping.class );

	protected Long map(UsageCountConfigId usageCountConfigId){
		if (usageCountConfigId == null) {
			return null;
		}
		return usageCountConfigId.getId();
	}
	/**
	 * 使用次数配置领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountConfigAppStructMapping#map(UsageCountConfigId)}
	 * @param usageCountConfig
	 * @return
	 */
	public abstract UsageCountConfigVO toUsageCountConfigVO(UsageCountConfig usageCountConfig);


	/**
	 * 数据对象转视图对象
	 * @param usageCountConfigDO
	 * @return
	 */
	public abstract UsageCountConfigVO usageCountConfigDOToUsageCountConfigVO(UsageCountConfigDO usageCountConfigDO);

	/**
	 * 批量转换
	 * @param usageCountConfigDOs
	 * @return
	 */
	public abstract List<UsageCountConfigVO> usageCountConfigDOsToUsageCountConfigVOs(List<UsageCountConfigDO> usageCountConfigDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UsageCountConfigVO> infrastructurePageToPageResponse(Page<UsageCountConfigDO> page) {
		return PageResponse.of(usageCountConfigDOsToUsageCountConfigVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public UsageCountConfigDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UsageCountConfigPageQueryCommand) {
			return pageQueryCommandToDO((UsageCountConfigPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UsageCountConfigQueryListCommand) {
			return queryListCommandToDO(((UsageCountConfigQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UsageCountConfigDO pageQueryCommandToDO(UsageCountConfigPageQueryCommand usageCountConfigPageQueryCommand);

	public abstract UsageCountConfigDO queryListCommandToDO(UsageCountConfigQueryListCommand usageCountConfigQueryListCommand);
}
