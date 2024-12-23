package com.particle.oplog.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogAuditDataVO;
import com.particle.oplog.domain.OpLogAuditData;
import com.particle.oplog.domain.OpLogAuditDataId;
import com.particle.oplog.infrastructure.dos.OpLogAuditDataDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 操作日志审计数据 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpLogAuditDataAppStructMapping  implements IBaseQueryCommandMapStruct<OpLogAuditDataDO>{
	public static OpLogAuditDataAppStructMapping instance = Mappers.getMapper( OpLogAuditDataAppStructMapping.class );

	protected Long map(OpLogAuditDataId opLogAuditDataId){
		if (opLogAuditDataId == null) {
			return null;
		}
		return opLogAuditDataId.getId();
	}
	/**
	 * 操作日志审计数据领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogAuditDataAppStructMapping#map(OpLogAuditDataId)}
	 * @param opLogAuditData
	 * @return
	 */
	public abstract OpLogAuditDataVO toOpLogAuditDataVO(OpLogAuditData opLogAuditData);


	/**
	 * 数据对象转视图对象
	 * @param opLogAuditDataDO
	 * @return
	 */
	public abstract OpLogAuditDataVO opLogAuditDataDOToOpLogAuditDataVO(OpLogAuditDataDO opLogAuditDataDO);

	/**
	 * 批量转换
	 * @param opLogAuditDataDOs
	 * @return
	 */
	public abstract List<OpLogAuditDataVO> opLogAuditDataDOsToOpLogAuditDataVOs(List<OpLogAuditDataDO> opLogAuditDataDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpLogAuditDataVO> infrastructurePageToPageResponse(Page<OpLogAuditDataDO> page) {
		return PageResponse.of(opLogAuditDataDOsToOpLogAuditDataVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpLogAuditDataDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpLogAuditDataPageQueryCommand) {
			return pageQueryCommandToDO((OpLogAuditDataPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpLogAuditDataQueryListCommand) {
			return queryListCommandToDO(((OpLogAuditDataQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpLogAuditDataDO pageQueryCommandToDO(OpLogAuditDataPageQueryCommand opLogAuditDataPageQueryCommand);

	public abstract OpLogAuditDataDO queryListCommandToDO(OpLogAuditDataQueryListCommand opLogAuditDataQueryListCommand);
}
