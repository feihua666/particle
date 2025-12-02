package com.particle.data.app.dynamictable.executor.representation;

import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryCreateCommandExecutor;
import com.particle.data.app.dynamictable.structmapping.DynamicTableFieldAppStructMapping;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldQueryListCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
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
 * 动态数据表格字段 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Component
@Validated
public class DynamicTableFieldQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDynamicTableFieldService iDynamicTableFieldService;
    private IDynamicTableService iDynamicTableService;

	/**
	 * 执行 动态数据表格字段 列表查询指令
	 * @param dynamicTableFieldQueryListCommand
	 * @return
	 */
	public MultiResponse<DynamicTableFieldVO> execute(@Valid DynamicTableFieldQueryListCommand dynamicTableFieldQueryListCommand) {
        Long dynamicTableId = tryGetDynamicTableIdIfNeed(dynamicTableFieldQueryListCommand.getDynamicTableId(), dynamicTableFieldQueryListCommand.getDynamicDataIndicatorCategoryId());
        dynamicTableFieldQueryListCommand.setDynamicTableId(dynamicTableId);
		List<DynamicTableFieldDO> dynamicTableFieldDO = iDynamicTableFieldService.list(dynamicTableFieldQueryListCommand);
		List<DynamicTableFieldVO> dynamicTableFieldVOs = DynamicTableFieldAppStructMapping.instance.dynamicTableFieldDOsToDynamicTableFieldVOs(dynamicTableFieldDO);
		return MultiResponse.of(dynamicTableFieldVOs);
	}
	/**
	 * 执行 动态数据表格字段 分页查询指令
	 * @param dynamicTableFieldPageQueryCommand
	 * @return
	 */
	public PageResponse<DynamicTableFieldVO> execute(@Valid DynamicTableFieldPageQueryCommand dynamicTableFieldPageQueryCommand) {
        Long dynamicTableId = tryGetDynamicTableIdIfNeed(dynamicTableFieldPageQueryCommand.getDynamicTableId(), dynamicTableFieldPageQueryCommand.getDynamicDataIndicatorCategoryId());
        dynamicTableFieldPageQueryCommand.setDynamicTableId(dynamicTableId);
		Page<DynamicTableFieldDO> page = iDynamicTableFieldService.listPage(dynamicTableFieldPageQueryCommand);
		return DynamicTableFieldAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 动态数据表格字段 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DynamicTableFieldVO> executeDetail(IdCommand detailCommand) {
		DynamicTableFieldDO byId = iDynamicTableFieldService.getById(detailCommand.getId());
		DynamicTableFieldVO dynamicTableFieldVO = DynamicTableFieldAppStructMapping.instance.dynamicTableFieldDOToDynamicTableFieldVO(byId);
		return SingleResponse.of(dynamicTableFieldVO);
	}
	/**
	 * 执行 动态数据表格字段 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicTableFieldVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DynamicTableFieldDO byId = iDynamicTableFieldService.getById(detailForUpdateCommand.getId());
		DynamicTableFieldVO dynamicTableFieldVO = DynamicTableFieldAppStructMapping.instance.dynamicTableFieldDOToDynamicTableFieldVO(byId);
		return SingleResponse.of(dynamicTableFieldVO);
	}
    private Long tryGetDynamicTableIdIfNeed(Long dynamicTableId,Long dynamicDataIndicatorCategoryId) {
        if (dynamicTableId != null) {
            return dynamicTableId;
        }
        return getDynamicTableId(dynamicDataIndicatorCategoryId);
    }
    /**
     * 尝试获取动态数据表格id
     * @param dynamicDataIndicatorCategoryId
     * @return
     */
    private Long getDynamicTableId(Long dynamicDataIndicatorCategoryId) {
        if (dynamicDataIndicatorCategoryId == null) {
            return null;
        }
        DynamicTableDO dynamicTableDO = iDynamicTableService.getByName(DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(dynamicDataIndicatorCategoryId));
        return dynamicTableDO.getId();
    }

	@Autowired
	public void setIDynamicTableFieldService(IDynamicTableFieldService iDynamicTableFieldService) {
		this.iDynamicTableFieldService = iDynamicTableFieldService;
	}
    @Autowired
    public void setIDynamicTableService(IDynamicTableService iDynamicTableService) {
        this.iDynamicTableService = iDynamicTableService;
    }
}
