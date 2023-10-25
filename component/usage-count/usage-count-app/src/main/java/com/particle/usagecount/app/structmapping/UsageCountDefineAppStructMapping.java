package com.particle.usagecount.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;
import com.particle.usagecount.domain.UsageCountDefine;
import com.particle.usagecount.domain.UsageCountDefineId;
import com.particle.usagecount.infrastructure.dos.UsageCountDefineDO;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefinePageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefineQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 使用次数定义 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UsageCountDefineAppStructMapping  implements IBaseQueryCommandMapStruct<UsageCountDefineDO>{
	public static UsageCountDefineAppStructMapping instance = Mappers.getMapper( UsageCountDefineAppStructMapping.class );

	protected Long map(UsageCountDefineId usageCountDefineId){
		if (usageCountDefineId == null) {
			return null;
		}
		return usageCountDefineId.getId();
	}
	/**
	 * 使用次数定义领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountDefineAppStructMapping#map(UsageCountDefineId)}
	 * @param usageCountDefine
	 * @return
	 */
	public abstract UsageCountDefineVO toUsageCountDefineVO(UsageCountDefine usageCountDefine);


	/**
	 * 数据对象转视图对象
	 * @param usageCountDefineDO
	 * @return
	 */
	public abstract UsageCountDefineVO usageCountDefineDOToUsageCountDefineVO(UsageCountDefineDO usageCountDefineDO);

	/**
	 * 批量转换
	 * @param usageCountDefineDOs
	 * @return
	 */
	public abstract List<UsageCountDefineVO> usageCountDefineDOsToUsageCountDefineVOs(List<UsageCountDefineDO> usageCountDefineDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UsageCountDefineVO> infrastructurePageToPageResponse(Page<UsageCountDefineDO> page) {
		return PageResponse.of(usageCountDefineDOsToUsageCountDefineVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public UsageCountDefineDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UsageCountDefinePageQueryCommand) {
			return pageQueryCommandToDO((UsageCountDefinePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UsageCountDefineQueryListCommand) {
			return queryListCommandToDO(((UsageCountDefineQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UsageCountDefineDO pageQueryCommandToDO(UsageCountDefinePageQueryCommand usageCountDefinePageQueryCommand);

	public abstract UsageCountDefineDO queryListCommandToDO(UsageCountDefineQueryListCommand usageCountDefineQueryListCommand);
}
