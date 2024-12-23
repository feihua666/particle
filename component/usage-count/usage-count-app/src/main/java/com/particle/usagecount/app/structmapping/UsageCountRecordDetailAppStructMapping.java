package com.particle.usagecount.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordDetailVO;
import com.particle.usagecount.domain.UsageCountRecordDetail;
import com.particle.usagecount.domain.UsageCountRecordDetailId;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDetailDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 使用次数记录明细 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UsageCountRecordDetailAppStructMapping  implements IBaseQueryCommandMapStruct<UsageCountRecordDetailDO>{
	public static UsageCountRecordDetailAppStructMapping instance = Mappers.getMapper( UsageCountRecordDetailAppStructMapping.class );

	protected Long map(UsageCountRecordDetailId usageCountRecordDetailId){
		if (usageCountRecordDetailId == null) {
			return null;
		}
		return usageCountRecordDetailId.getId();
	}
	/**
	 * 使用次数记录明细领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UsageCountRecordDetailAppStructMapping#map(UsageCountRecordDetailId)}
	 * @param usageCountRecordDetail
	 * @return
	 */
	public abstract UsageCountRecordDetailVO toUsageCountRecordDetailVO(UsageCountRecordDetail usageCountRecordDetail);


	/**
	 * 数据对象转视图对象
	 * @param usageCountRecordDetailDO
	 * @return
	 */
	public abstract UsageCountRecordDetailVO usageCountRecordDetailDOToUsageCountRecordDetailVO(UsageCountRecordDetailDO usageCountRecordDetailDO);

	/**
	 * 批量转换
	 * @param usageCountRecordDetailDOs
	 * @return
	 */
	public abstract List<UsageCountRecordDetailVO> usageCountRecordDetailDOsToUsageCountRecordDetailVOs(List<UsageCountRecordDetailDO> usageCountRecordDetailDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UsageCountRecordDetailVO> infrastructurePageToPageResponse(Page<UsageCountRecordDetailDO> page) {
		return PageResponse.of(usageCountRecordDetailDOsToUsageCountRecordDetailVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public UsageCountRecordDetailDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof UsageCountRecordDetailPageQueryCommand) {
			return pageQueryCommandToDO((UsageCountRecordDetailPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof UsageCountRecordDetailQueryListCommand) {
			return queryListCommandToDO(((UsageCountRecordDetailQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract UsageCountRecordDetailDO pageQueryCommandToDO(UsageCountRecordDetailPageQueryCommand usageCountRecordDetailPageQueryCommand);

	public abstract UsageCountRecordDetailDO queryListCommandToDO(UsageCountRecordDetailQueryListCommand usageCountRecordDetailQueryListCommand);
}
