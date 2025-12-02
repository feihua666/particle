package com.particle.data.app.dynamicdata.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryCreateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCreateCommandExecutor;
import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorCategoryAppStructMapping;
import com.particle.data.app.dynamictable.executor.representation.DynamicTableQueryCommandExecutor;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryDataPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableDataPageQueryCommand;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryDO;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryService;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.mybatis.plus.table.TableServivce;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态数据指标分类 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Component
@Validated
public class DynamicDataIndicatorCategoryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDynamicDataIndicatorCategoryService iDynamicDataIndicatorCategoryService;
    private DynamicTableQueryCommandExecutor  dynamicTableQueryCommandExecutor;
    private IDynamicTableService dynamicTableService;
    private TableServivce tableServivce;
    private IDynamicDataIndicatorService iDynamicDataIndicatorService;
	/**
	 * 执行 动态数据指标分类 列表查询指令
	 * @param dynamicDataIndicatorCategoryQueryListCommand
	 * @return
	 */
	public MultiResponse<DynamicDataIndicatorCategoryVO> execute(@Valid DynamicDataIndicatorCategoryQueryListCommand dynamicDataIndicatorCategoryQueryListCommand) {
		List<DynamicDataIndicatorCategoryDO> dynamicDataIndicatorCategoryDO = iDynamicDataIndicatorCategoryService.list(dynamicDataIndicatorCategoryQueryListCommand);
		List<DynamicDataIndicatorCategoryVO> dynamicDataIndicatorCategoryVOs = DynamicDataIndicatorCategoryAppStructMapping.instance.dynamicDataIndicatorCategoryDOsToDynamicDataIndicatorCategoryVOs(dynamicDataIndicatorCategoryDO);
		return MultiResponse.of(dynamicDataIndicatorCategoryVOs);
	}
	/**
	 * 执行 动态数据指标分类 分页查询指令
	 * @param dynamicDataIndicatorCategoryPageQueryCommand
	 * @return
	 */
	public PageResponse<DynamicDataIndicatorCategoryVO> execute(@Valid DynamicDataIndicatorCategoryPageQueryCommand dynamicDataIndicatorCategoryPageQueryCommand) {
		Page<DynamicDataIndicatorCategoryDO> page = iDynamicDataIndicatorCategoryService.listPage(dynamicDataIndicatorCategoryPageQueryCommand);
		return DynamicDataIndicatorCategoryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 动态数据指标分类 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryVO> executeDetail(IdCommand detailCommand) {
		DynamicDataIndicatorCategoryDO byId = iDynamicDataIndicatorCategoryService.getById(detailCommand.getId());
		DynamicDataIndicatorCategoryVO dynamicDataIndicatorCategoryVO = DynamicDataIndicatorCategoryAppStructMapping.instance.dynamicDataIndicatorCategoryDOToDynamicDataIndicatorCategoryVO(byId);
		return SingleResponse.of(dynamicDataIndicatorCategoryVO);
	}
	/**
	 * 执行 动态数据指标分类 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DynamicDataIndicatorCategoryDO byId = iDynamicDataIndicatorCategoryService.getById(detailForUpdateCommand.getId());
		DynamicDataIndicatorCategoryVO dynamicDataIndicatorCategoryVO = DynamicDataIndicatorCategoryAppStructMapping.instance.dynamicDataIndicatorCategoryDOToDynamicDataIndicatorCategoryVO(byId);
		return SingleResponse.of(dynamicDataIndicatorCategoryVO);
	}

    /**
     * 分页获取数据
     * @param dynamicDataIndicatorCategoryDataPageQueryCommand
     * @return
     */
    public PageResponse<Map<String, Object>> dataPageQuery(@Valid DynamicDataIndicatorCategoryDataPageQueryCommand dynamicDataIndicatorCategoryDataPageQueryCommand) {
        boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;
        Boolean isPublic = dynamicDataIndicatorCategoryDataPageQueryCommand.getIsPublic();
        Long batchId = dynamicDataIndicatorCategoryDataPageQueryCommand.getBatchId();
        String tableName = DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(dynamicDataIndicatorCategoryDataPageQueryCommand.getDynamicDataIndicatorCategoryId());
        if (useDynamicTable) {
            DynamicTableDO dynamicTableDO = dynamicTableService.getByName(tableName);

            DynamicTableDataPageQueryCommand dynamicTableDataPageQueryCommand = new DynamicTableDataPageQueryCommand();
            dynamicTableDataPageQueryCommand.setDynamicTableId(dynamicTableDO.getId());
            dynamicTableDataPageQueryCommand.setIsPublic(isPublic);
            dynamicTableDataPageQueryCommand.setBatchId(batchId);
            dynamicTableDataPageQueryCommand.setPageNo(dynamicDataIndicatorCategoryDataPageQueryCommand.getPageNo());
            dynamicTableDataPageQueryCommand.setPageSize(dynamicDataIndicatorCategoryDataPageQueryCommand.getPageSize());
            return dynamicTableQueryCommandExecutor.dataPageQuery(dynamicTableDataPageQueryCommand);
        }else{
            List<DynamicDataIndicatorDO> dynamicDataIndicatorDOS = iDynamicDataIndicatorService.listByDynamicDataIndicatorCategoryId(dynamicDataIndicatorCategoryDataPageQueryCommand.getDynamicDataIndicatorCategoryId());
            List<String> columnNames = dynamicDataIndicatorDOS.stream()
                    .map(dynamicTableFieldDO -> DynamicDataIndicatorCreateCommandExecutor.wrapTableColumn(dynamicTableFieldDO.getId()))
                    .collect(Collectors.toList());

            Page<Object> objectPage = new Page<>();
            objectPage.setSearchCount(true);
            objectPage.setCurrent(dynamicDataIndicatorCategoryDataPageQueryCommand.getPageNo());
            objectPage.setSize(dynamicDataIndicatorCategoryDataPageQueryCommand.getPageSize());
            Page<Map<String, Object>> mapListPage = tableServivce.selectPage(tableName, columnNames,isPublic,batchId, objectPage);
            return PageResponse.of(mapListPage.getRecords(), (int) mapListPage.getTotal(), (int) mapListPage.getSize(), (int) mapListPage.getCurrent());
        }
    }
	@Autowired
	public void setIDynamicDataIndicatorCategoryService(IDynamicDataIndicatorCategoryService iDynamicDataIndicatorCategoryService) {
		this.iDynamicDataIndicatorCategoryService = iDynamicDataIndicatorCategoryService;
	}
    @Autowired
    public void setDynamicTableQueryCommandExecutor(DynamicTableQueryCommandExecutor dynamicTableQueryCommandExecutor) {
        this.dynamicTableQueryCommandExecutor = dynamicTableQueryCommandExecutor;
    }
    @Autowired
    public void setDynamicTableService(IDynamicTableService dynamicTableService) {
        this.dynamicTableService = dynamicTableService;
    }
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
    @Autowired
    public void setiDynamicDataIndicatorService(IDynamicDataIndicatorService iDynamicDataIndicatorService) {
        this.iDynamicDataIndicatorService = iDynamicDataIndicatorService;
    }
}
