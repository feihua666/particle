package com.particle.data.app.dynamictable.executor.representation;

import com.particle.data.app.dynamictable.structmapping.DynamicTableUploadRecordAppStructMapping;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordQueryListCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableUploadRecordDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableUploadRecordService;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableUploadRecordPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态数据表格上传记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Component
@Validated
public class DynamicTableUploadRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDynamicTableUploadRecordService iDynamicTableUploadRecordService;

	/**
	 * 执行 动态数据表格上传记录 列表查询指令
	 * @param dynamicTableUploadRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<DynamicTableUploadRecordVO> execute(@Valid DynamicTableUploadRecordQueryListCommand dynamicTableUploadRecordQueryListCommand) {
		List<DynamicTableUploadRecordDO> dynamicTableUploadRecordDO = iDynamicTableUploadRecordService.list(dynamicTableUploadRecordQueryListCommand);
		List<DynamicTableUploadRecordVO> dynamicTableUploadRecordVOs = DynamicTableUploadRecordAppStructMapping.instance.dynamicTableUploadRecordDOsToDynamicTableUploadRecordVOs(dynamicTableUploadRecordDO);
		return MultiResponse.of(dynamicTableUploadRecordVOs);
	}
	/**
	 * 执行 动态数据表格上传记录 分页查询指令
	 * @param dynamicTableUploadRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<DynamicTableUploadRecordVO> execute(@Valid DynamicTableUploadRecordPageQueryCommand dynamicTableUploadRecordPageQueryCommand) {
		Page<DynamicTableUploadRecordDO> page = iDynamicTableUploadRecordService.listPage(dynamicTableUploadRecordPageQueryCommand);
		return DynamicTableUploadRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 动态数据表格上传记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DynamicTableUploadRecordVO> executeDetail(IdCommand detailCommand) {
		DynamicTableUploadRecordDO byId = iDynamicTableUploadRecordService.getById(detailCommand.getId());
		DynamicTableUploadRecordVO dynamicTableUploadRecordVO = DynamicTableUploadRecordAppStructMapping.instance.dynamicTableUploadRecordDOToDynamicTableUploadRecordVO(byId);
		return SingleResponse.of(dynamicTableUploadRecordVO);
	}
	/**
	 * 执行 动态数据表格上传记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicTableUploadRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DynamicTableUploadRecordDO byId = iDynamicTableUploadRecordService.getById(detailForUpdateCommand.getId());
		DynamicTableUploadRecordVO dynamicTableUploadRecordVO = DynamicTableUploadRecordAppStructMapping.instance.dynamicTableUploadRecordDOToDynamicTableUploadRecordVO(byId);
		return SingleResponse.of(dynamicTableUploadRecordVO);
	}


	@Autowired
	public void setIDynamicTableUploadRecordService(IDynamicTableUploadRecordService iDynamicTableUploadRecordService) {
		this.iDynamicTableUploadRecordService = iDynamicTableUploadRecordService;
	}
}
