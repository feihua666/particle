package com.particle.oplog.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.oplog.client.dto.command.representation.OpLogPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogVO;
import com.particle.oplog.domain.OpLog;
import com.particle.oplog.domain.OpLogId;
import com.particle.oplog.infrastructure.dos.OpLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 操作日志 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpLogAppStructMapping  implements IBaseQueryCommandMapStruct<OpLogDO>{
	public static OpLogAppStructMapping instance = Mappers.getMapper( OpLogAppStructMapping.class );

	protected Long map(OpLogId opLogId){
		if (opLogId == null) {
			return null;
		}
		return opLogId.getId();
	}
	/**
	 * 操作日志领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogAppStructMapping#map(OpLogId)}
	 * @param opLog
	 * @return
	 */
	public abstract OpLogVO toOpLogVO(OpLog opLog);


	/**
	 * 数据对象转视图对象
	 * @param opLogDO
	 * @return
	 */
	public abstract OpLogVO opLogDOToOpLogVO(OpLogDO opLogDO);

	/**
	 * 批量转换
	 * @param opLogDOs
	 * @return
	 */
	public abstract List<OpLogVO> opLogDOsToOpLogVOs(List<OpLogDO> opLogDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpLogVO> infrastructurePageToPageResponse(Page<OpLogDO> page) {
		return PageResponse.of(opLogDOsToOpLogVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpLogDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpLogPageQueryCommand) {
			return pageQueryCommandToDO((OpLogPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpLogQueryListCommand) {
			return queryListCommandToDO(((OpLogQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpLogDO pageQueryCommandToDO(OpLogPageQueryCommand opLogPageQueryCommand);

	public abstract OpLogDO queryListCommandToDO(OpLogQueryListCommand opLogQueryListCommand);
}
