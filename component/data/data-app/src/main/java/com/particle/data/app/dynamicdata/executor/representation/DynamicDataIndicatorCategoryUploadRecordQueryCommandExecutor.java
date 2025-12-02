package com.particle.data.app.dynamicdata.executor.representation;

import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorCategoryUploadRecordAppStructMapping;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryUploadRecordDO;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryUploadRecordService;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryUploadRecordPageQueryCommand;
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
 * 动态数据指标分类上传记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Component
@Validated
public class DynamicDataIndicatorCategoryUploadRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDynamicDataIndicatorCategoryUploadRecordService iDynamicDataIndicatorCategoryUploadRecordService;

	/**
	 * 执行 动态数据指标分类上传记录 列表查询指令
	 * @param dynamicDataIndicatorCategoryUploadRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<DynamicDataIndicatorCategoryUploadRecordVO> execute(@Valid DynamicDataIndicatorCategoryUploadRecordQueryListCommand dynamicDataIndicatorCategoryUploadRecordQueryListCommand) {
		List<DynamicDataIndicatorCategoryUploadRecordDO> dynamicDataIndicatorCategoryUploadRecordDO = iDynamicDataIndicatorCategoryUploadRecordService.list(dynamicDataIndicatorCategoryUploadRecordQueryListCommand);
		List<DynamicDataIndicatorCategoryUploadRecordVO> dynamicDataIndicatorCategoryUploadRecordVOs = DynamicDataIndicatorCategoryUploadRecordAppStructMapping.instance.dynamicDataIndicatorCategoryUploadRecordDOsToDynamicDataIndicatorCategoryUploadRecordVOs(dynamicDataIndicatorCategoryUploadRecordDO);
		return MultiResponse.of(dynamicDataIndicatorCategoryUploadRecordVOs);
	}
	/**
	 * 执行 动态数据指标分类上传记录 分页查询指令
	 * @param dynamicDataIndicatorCategoryUploadRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<DynamicDataIndicatorCategoryUploadRecordVO> execute(@Valid DynamicDataIndicatorCategoryUploadRecordPageQueryCommand dynamicDataIndicatorCategoryUploadRecordPageQueryCommand) {
		Page<DynamicDataIndicatorCategoryUploadRecordDO> page = iDynamicDataIndicatorCategoryUploadRecordService.listPage(dynamicDataIndicatorCategoryUploadRecordPageQueryCommand);
		return DynamicDataIndicatorCategoryUploadRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 动态数据指标分类上传记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> executeDetail(IdCommand detailCommand) {
		DynamicDataIndicatorCategoryUploadRecordDO byId = iDynamicDataIndicatorCategoryUploadRecordService.getById(detailCommand.getId());
		DynamicDataIndicatorCategoryUploadRecordVO dynamicDataIndicatorCategoryUploadRecordVO = DynamicDataIndicatorCategoryUploadRecordAppStructMapping.instance.dynamicDataIndicatorCategoryUploadRecordDOToDynamicDataIndicatorCategoryUploadRecordVO(byId);
		return SingleResponse.of(dynamicDataIndicatorCategoryUploadRecordVO);
	}
	/**
	 * 执行 动态数据指标分类上传记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DynamicDataIndicatorCategoryUploadRecordDO byId = iDynamicDataIndicatorCategoryUploadRecordService.getById(detailForUpdateCommand.getId());
		DynamicDataIndicatorCategoryUploadRecordVO dynamicDataIndicatorCategoryUploadRecordVO = DynamicDataIndicatorCategoryUploadRecordAppStructMapping.instance.dynamicDataIndicatorCategoryUploadRecordDOToDynamicDataIndicatorCategoryUploadRecordVO(byId);
		return SingleResponse.of(dynamicDataIndicatorCategoryUploadRecordVO);
	}


	@Autowired
	public void setIDynamicDataIndicatorCategoryUploadRecordService(IDynamicDataIndicatorCategoryUploadRecordService iDynamicDataIndicatorCategoryUploadRecordService) {
		this.iDynamicDataIndicatorCategoryUploadRecordService = iDynamicDataIndicatorCategoryUploadRecordService;
	}
}
