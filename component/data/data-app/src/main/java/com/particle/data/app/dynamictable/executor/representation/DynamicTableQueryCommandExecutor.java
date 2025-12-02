package com.particle.data.app.dynamictable.executor.representation;

import com.particle.data.app.dynamictable.structmapping.DynamicTableAppStructMapping;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableDataPageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableQueryListCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTablePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.mybatis.plus.table.TableServivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态数据表格 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Component
@Validated
public class DynamicTableQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDynamicTableService iDynamicTableService;
    private TableServivce tableServivce;
    private IDynamicTableFieldService iDynamicTableFieldService;
	/**
	 * 执行 动态数据表格 列表查询指令
	 * @param dynamicTableQueryListCommand
	 * @return
	 */
	public MultiResponse<DynamicTableVO> execute(@Valid DynamicTableQueryListCommand dynamicTableQueryListCommand) {
		List<DynamicTableDO> dynamicTableDO = iDynamicTableService.list(dynamicTableQueryListCommand);
		List<DynamicTableVO> dynamicTableVOs = DynamicTableAppStructMapping.instance.dynamicTableDOsToDynamicTableVOs(dynamicTableDO);
		return MultiResponse.of(dynamicTableVOs);
	}
	/**
	 * 执行 动态数据表格 分页查询指令
	 * @param dynamicTablePageQueryCommand
	 * @return
	 */
	public PageResponse<DynamicTableVO> execute(@Valid DynamicTablePageQueryCommand dynamicTablePageQueryCommand) {
		Page<DynamicTableDO> page = iDynamicTableService.listPage(dynamicTablePageQueryCommand);
		return DynamicTableAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 动态数据表格 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DynamicTableVO> executeDetail(IdCommand detailCommand) {
		DynamicTableDO byId = iDynamicTableService.getById(detailCommand.getId());
		DynamicTableVO dynamicTableVO = DynamicTableAppStructMapping.instance.dynamicTableDOToDynamicTableVO(byId);
		return SingleResponse.of(dynamicTableVO);
	}
	/**
	 * 执行 动态数据表格 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicTableVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DynamicTableDO byId = iDynamicTableService.getById(detailForUpdateCommand.getId());
		DynamicTableVO dynamicTableVO = DynamicTableAppStructMapping.instance.dynamicTableDOToDynamicTableVO(byId);
		return SingleResponse.of(dynamicTableVO);
	}

    /**
     * 执行 获取动态数据表格数据列表查询
     * @param dynamicTableDataPageQueryCommand
     * @return
     */
    public PageResponse<Map<String, Object>> dataPageQuery(@Valid DynamicTableDataPageQueryCommand dynamicTableDataPageQueryCommand) {
        DynamicTableDO dynamicTableDO = iDynamicTableService.getById(dynamicTableDataPageQueryCommand.getDynamicTableId());
        List<DynamicTableFieldDO> dynamicTableFieldDOS = iDynamicTableFieldService.listByDynamicTableId(dynamicTableDO.getId());

        List<String> columnNames = dynamicTableFieldDOS.stream().map(dynamicTableFieldDO -> dynamicTableFieldDO.getName()).collect(Collectors.toList());

        Page<Object> objectPage = new Page<>();
        objectPage.setSearchCount(true);
        objectPage.setCurrent(dynamicTableDataPageQueryCommand.getPageNo());
        objectPage.setSize(dynamicTableDataPageQueryCommand.getPageSize());
        Page<Map<String, Object>> mapListPage = tableServivce.selectPage(dynamicTableDO.getName(),
                columnNames,
                dynamicTableDataPageQueryCommand.getIsPublic(),
                dynamicTableDataPageQueryCommand.getBatchId(),
                objectPage);
        return PageResponse.of(mapListPage.getRecords(), (int) mapListPage.getTotal(), (int) mapListPage.getSize(), (int) mapListPage.getCurrent());
    }
	@Autowired
	public void setIDynamicTableService(IDynamicTableService iDynamicTableService) {
		this.iDynamicTableService = iDynamicTableService;
	}
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
    @Autowired
    public void setiDynamicTableFieldService(IDynamicTableFieldService iDynamicTableFieldService) {
        this.iDynamicTableFieldService = iDynamicTableFieldService;
    }
}
