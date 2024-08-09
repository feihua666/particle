package com.particle.oplog.app.error.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
import com.particle.oplog.domain.error.OpLogError;
import com.particle.oplog.domain.error.OpLogErrorId;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorDO;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorPageQueryCommand;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 操作异常日志 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpLogErrorAppStructMapping  implements IBaseQueryCommandMapStruct<OpLogErrorDO>{
	public static OpLogErrorAppStructMapping instance = Mappers.getMapper( OpLogErrorAppStructMapping.class );

	protected Long map(OpLogErrorId opLogErrorId){
		if (opLogErrorId == null) {
			return null;
		}
		return opLogErrorId.getId();
	}
	/**
	 * 操作异常日志领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogErrorAppStructMapping#map(OpLogErrorId)}
	 * @param opLogError
	 * @return
	 */
	public abstract OpLogErrorVO toOpLogErrorVO(OpLogError opLogError);


	/**
	 * 数据对象转视图对象
	 * @param opLogErrorDO
	 * @return
	 */
	public abstract OpLogErrorVO opLogErrorDOToOpLogErrorVO(OpLogErrorDO opLogErrorDO);

	/**
	 * 批量转换
	 * @param opLogErrorDOs
	 * @return
	 */
	public abstract List<OpLogErrorVO> opLogErrorDOsToOpLogErrorVOs(List<OpLogErrorDO> opLogErrorDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpLogErrorVO> infrastructurePageToPageResponse(Page<OpLogErrorDO> page) {
		return PageResponse.of(opLogErrorDOsToOpLogErrorVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpLogErrorDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpLogErrorPageQueryCommand) {
			return pageQueryCommandToDO((OpLogErrorPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpLogErrorQueryListCommand) {
			return queryListCommandToDO(((OpLogErrorQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpLogErrorDO pageQueryCommandToDO(OpLogErrorPageQueryCommand opLogErrorPageQueryCommand);

	public abstract OpLogErrorDO queryListCommandToDO(OpLogErrorQueryListCommand opLogErrorQueryListCommand);
}
