package com.particle.usagecount.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordVO;
import com.particle.usagecount.domain.UsageCountRecord;
import com.particle.usagecount.domain.UsageCountRecordId;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 使用次数记录 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UsageCountRecordAppStructMapping  implements IBaseQueryCommandMapStruct<UsageCountRecordDO>{
	public static UsageCountRecordAppStructMapping instance = Mappers.getMapper( UsageCountRecordAppStructMapping.class );

	protected Long map(UsageCountRecordId usageCountRecordId){
		if (usageCountRecordId == null) {
			return null;
		}
		return usageCountRecordId.getId();
	}
	/**
	 * 使用次数记录领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountRecordAppStructMapping#map(UsageCountRecordId)}
	 * @param usageCountRecord
	 * @return
	 */
	public abstract UsageCountRecordVO toUsageCountRecordVO(UsageCountRecord usageCountRecord);


	/**
	 * 数据对象转视图对象
	 * @param usageCountRecordDO
	 * @return
	 */
	public abstract UsageCountRecordVO usageCountRecordDOToUsageCountRecordVO(UsageCountRecordDO usageCountRecordDO);

	/**
	 * 批量转换
	 * @param usageCountRecordDOs
	 * @return
	 */
	public abstract List<UsageCountRecordVO> usageCountRecordDOsToUsageCountRecordVOs(List<UsageCountRecordDO> usageCountRecordDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UsageCountRecordVO> infrastructurePageToPageResponse(Page<UsageCountRecordDO> page) {
		return PageResponse.of(usageCountRecordDOsToUsageCountRecordVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public UsageCountRecordDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UsageCountRecordPageQueryCommand) {
			return pageQueryCommandToDO((UsageCountRecordPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UsageCountRecordQueryListCommand) {
			return queryListCommandToDO(((UsageCountRecordQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UsageCountRecordDO pageQueryCommandToDO(UsageCountRecordPageQueryCommand usageCountRecordPageQueryCommand);

	public abstract UsageCountRecordDO queryListCommandToDO(UsageCountRecordQueryListCommand usageCountRecordQueryListCommand);
}
