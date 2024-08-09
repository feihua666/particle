package com.particle.oplog.app.error.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.oplog.client.error.dto.data.OpLogErrorContentVO;
import com.particle.oplog.domain.error.OpLogErrorContent;
import com.particle.oplog.domain.error.OpLogErrorContentId;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorContentDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 操作异常日志内容 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpLogErrorContentAppStructMapping  implements IBaseQueryCommandMapStruct<OpLogErrorContentDO>{
	public static OpLogErrorContentAppStructMapping instance = Mappers.getMapper( OpLogErrorContentAppStructMapping.class );

	protected Long map(OpLogErrorContentId opLogErrorContentId){
		if (opLogErrorContentId == null) {
			return null;
		}
		return opLogErrorContentId.getId();
	}
	/**
	 * 操作异常日志内容领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpLogErrorContentAppStructMapping#map(OpLogErrorContentId)}
	 * @param opLogErrorContent
	 * @return
	 */
	public abstract OpLogErrorContentVO toOpLogErrorContentVO(OpLogErrorContent opLogErrorContent);


	/**
	 * 数据对象转视图对象
	 * @param opLogErrorContentDO
	 * @return
	 */
	public abstract OpLogErrorContentVO opLogErrorContentDOToOpLogErrorContentVO(OpLogErrorContentDO opLogErrorContentDO);

	/**
	 * 批量转换
	 * @param opLogErrorContentDOs
	 * @return
	 */
	public abstract List<OpLogErrorContentVO> opLogErrorContentDOsToOpLogErrorContentVOs(List<OpLogErrorContentDO> opLogErrorContentDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpLogErrorContentVO> infrastructurePageToPageResponse(Page<OpLogErrorContentDO> page) {
		return PageResponse.of(opLogErrorContentDOsToOpLogErrorContentVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpLogErrorContentDO queryCommandToDO(QueryCommand queryCommand) {

		return null;
	}


}
