package com.particle.data.app.dynamicdata.executor.representation;

import com.particle.data.app.dynamicdata.structmapping.DynamicDataCategoryAppStructMapping;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataCategoryDO;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataCategoryService;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryPageQueryCommand;
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
 * 动态数据分类 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Component
@Validated
public class DynamicDataCategoryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDynamicDataCategoryService iDynamicDataCategoryService;

	/**
	 * 执行 动态数据分类 列表查询指令
	 * @param dynamicDataCategoryQueryListCommand
	 * @return
	 */
	public MultiResponse<DynamicDataCategoryVO> execute(@Valid DynamicDataCategoryQueryListCommand dynamicDataCategoryQueryListCommand) {
		List<DynamicDataCategoryDO> dynamicDataCategoryDO = iDynamicDataCategoryService.list(dynamicDataCategoryQueryListCommand);
		List<DynamicDataCategoryVO> dynamicDataCategoryVOs = DynamicDataCategoryAppStructMapping.instance.dynamicDataCategoryDOsToDynamicDataCategoryVOs(dynamicDataCategoryDO);
		return MultiResponse.of(dynamicDataCategoryVOs);
	}
	/**
	 * 执行 动态数据分类 分页查询指令
	 * @param dynamicDataCategoryPageQueryCommand
	 * @return
	 */
	public PageResponse<DynamicDataCategoryVO> execute(@Valid DynamicDataCategoryPageQueryCommand dynamicDataCategoryPageQueryCommand) {
		Page<DynamicDataCategoryDO> page = iDynamicDataCategoryService.listPage(dynamicDataCategoryPageQueryCommand);
		return DynamicDataCategoryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 动态数据分类 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DynamicDataCategoryVO> executeDetail(IdCommand detailCommand) {
		DynamicDataCategoryDO byId = iDynamicDataCategoryService.getById(detailCommand.getId());
		DynamicDataCategoryVO dynamicDataCategoryVO = DynamicDataCategoryAppStructMapping.instance.dynamicDataCategoryDOToDynamicDataCategoryVO(byId);
		return SingleResponse.of(dynamicDataCategoryVO);
	}
	/**
	 * 执行 动态数据分类 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataCategoryVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DynamicDataCategoryDO byId = iDynamicDataCategoryService.getById(detailForUpdateCommand.getId());
		DynamicDataCategoryVO dynamicDataCategoryVO = DynamicDataCategoryAppStructMapping.instance.dynamicDataCategoryDOToDynamicDataCategoryVO(byId);
		return SingleResponse.of(dynamicDataCategoryVO);
	}


	@Autowired
	public void setIDynamicDataCategoryService(IDynamicDataCategoryService iDynamicDataCategoryService) {
		this.iDynamicDataCategoryService = iDynamicDataCategoryService;
	}
}
