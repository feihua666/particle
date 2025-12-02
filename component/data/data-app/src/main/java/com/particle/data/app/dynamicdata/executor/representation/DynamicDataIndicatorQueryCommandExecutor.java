package com.particle.data.app.dynamicdata.executor.representation;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCategoryCreateCommandExecutor;
import com.particle.data.app.dynamicdata.executor.DynamicDataIndicatorCreateCommandExecutor;
import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorAppStructMapping;
import com.particle.data.app.dynamictable.structmapping.DynamicTableFieldAppStructMapping;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryCreateCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorWithDynamicTableFieldVO;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorService;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
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
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态数据指标 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Component
@Validated
public class DynamicDataIndicatorQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IDynamicDataIndicatorService iDynamicDataIndicatorService;
    private IDynamicTableFieldService iDynamicTableFieldService;
    private IDynamicTableService iDynamicTableService;

	/**
	 * 执行 动态数据指标 列表查询指令
	 * @param dynamicDataIndicatorQueryListCommand
	 * @return
	 */
	public MultiResponse<DynamicDataIndicatorVO> execute(@Valid DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand) {
		List<DynamicDataIndicatorDO> dynamicDataIndicatorDO = iDynamicDataIndicatorService.list(dynamicDataIndicatorQueryListCommand);
		List<DynamicDataIndicatorVO> dynamicDataIndicatorVOs = DynamicDataIndicatorAppStructMapping.instance.dynamicDataIndicatorDOsToDynamicDataIndicatorVOs(dynamicDataIndicatorDO);
		return MultiResponse.of(dynamicDataIndicatorVOs);
	}
    /**
	 * 执行 动态数据指标 列表查询指令
     * 注意：不适合大数据量返回结果的处理，一般1000条以内，是为了查询动态数据指标分类下的指标，建议传递动态数据指标分类id作为查询条件
	 * @param dynamicDataIndicatorQueryListCommand
	 * @return
	 */
	public MultiResponse<DynamicDataIndicatorWithDynamicTableFieldVO> queryListWithDynamicTableField(@Valid DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand) {
        // 查询动态数据指标
		List<DynamicDataIndicatorDO> dynamicDataIndicatorDO = iDynamicDataIndicatorService.list(dynamicDataIndicatorQueryListCommand);
        // 动态数据指标转VO
		List<DynamicDataIndicatorVO> dynamicDataIndicatorVOs = DynamicDataIndicatorAppStructMapping.instance.dynamicDataIndicatorDOsToDynamicDataIndicatorVOs(dynamicDataIndicatorDO);
        // 定义最终返回结果
        List<DynamicDataIndicatorWithDynamicTableFieldVO> result = wrapDynamicDataIndicatorWithDynamicTableFieldVO(dynamicDataIndicatorVOs);
		return MultiResponse.of(result);
	}

    /**
     * 将动态数据指标转VO转为结果VO
     * @param dynamicDataIndicatorVOs
     * @return
     */
    private List<DynamicDataIndicatorWithDynamicTableFieldVO> wrapDynamicDataIndicatorWithDynamicTableFieldVO(List<DynamicDataIndicatorVO> dynamicDataIndicatorVOs) {
        // 定义最终返回结果
        List<DynamicDataIndicatorWithDynamicTableFieldVO> result = null;
        // 如果结果不为空才处理
        if(CollectionUtil.isNotEmpty(dynamicDataIndicatorVOs)){
            // 将动态数据指标转VO转为结果VO
            result = dynamicDataIndicatorVOs.stream().map(dynamicDataIndicatorVO -> {
                return DynamicDataIndicatorAppStructMapping.instance.dynamicDataIndicatorVOToDynamicDataIndicatorWithDynamicTableFieldVO(dynamicDataIndicatorVO);
            }).collect(Collectors.toList());
            boolean useDynamicTable = DynamicDataIndicatorCategoryCreateCommandExecutor.useDynamicTable;

            if(useDynamicTable){
                // 将动态数据指标转VO提取动态数据指标分类ids
                List<Long> dynamicDataIndicatorCategoryIds = dynamicDataIndicatorVOs.stream().map(dynamicDataIndicatorVO -> dynamicDataIndicatorVO.getDynamicDataIndicatorCategoryId()).collect(Collectors.toList());
                // 将提取的动态数据指标分类ids转为表名
                List<String> tableNames = dynamicDataIndicatorCategoryIds.stream().map(dynamicDataIndicatorCategoryId -> DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(dynamicDataIndicatorCategoryId)).collect(Collectors.toList());
                // 根据表名查询动态数据表格
                List<DynamicTableDO> dynamicTableDOS = iDynamicTableService.getByNames(tableNames);
                // 将 dynamicTableDOS 转为以 表名 为 key 和以 dynamicTableId 为 value 的 map
                Map<String, Long> dynamicTableDOsTableIdMap = dynamicTableDOS.stream().collect(Collectors.toMap(dynamicTableDO -> dynamicTableDO.getName(), dynamicTableDO -> dynamicTableDO.getId()));

                // 提取动态数据表格ids
                List<Long> dynamicTableIds = dynamicTableDOS.stream().map(dynamicTableDO -> dynamicTableDO.getId()).collect(Collectors.toList());
                // 根据动态数据表格ids查询动态数据表格字段
                List<DynamicTableFieldDO> dynamicTableFieldDOS = iDynamicTableFieldService.listByDynamicTableIds(dynamicTableIds);
                // 将dynamicTableFieldDOS 转为以 dynamicTableId+字段名 的 map
                Map<String, DynamicTableFieldDO> dynamicTableFieldDOsTableIdMap = dynamicTableFieldDOS.stream().collect(Collectors.toMap(dynamicTableFieldDO -> dynamicTableFieldDO.getDynamicTableId() + dynamicTableFieldDO.getName(), dynamicTableFieldDO -> dynamicTableFieldDO));
                // 遍历结果VO 填充动数据表格字段VO
                for (DynamicDataIndicatorWithDynamicTableFieldVO dynamicDataIndicatorVO : result) {
                    // 将动数据指标分类ids转为表名
                    String tableName = DynamicDataIndicatorCategoryCreateCommandExecutor.wrapTableName(dynamicDataIndicatorVO.getDynamicDataIndicatorCategoryId());
                    // 根据表名获取对应的 dynamicTableId
                    Long dynamicTableId = dynamicTableDOsTableIdMap.get(tableName);
                    // 最终找到对应的动数据表格字段
                    DynamicTableFieldDO dynamicTableFieldDO = dynamicTableFieldDOsTableIdMap.get(dynamicTableId + DynamicDataIndicatorCreateCommandExecutor.wrapTableColumn(dynamicDataIndicatorVO.getId()));
                    DynamicTableFieldVO dynamicTableFieldVO = DynamicTableFieldAppStructMapping.instance.dynamicTableFieldDOToDynamicTableFieldVO(dynamicTableFieldDO);
                    dynamicDataIndicatorVO.setDynamicTableField(dynamicTableFieldVO);
                }
            }


        }
        return result;
    }
	/**
	 * 执行 动态数据指标 分页查询指令
	 * @param dynamicDataIndicatorPageQueryCommand
	 * @return
	 */
	public PageResponse<DynamicDataIndicatorVO> execute(@Valid DynamicDataIndicatorPageQueryCommand dynamicDataIndicatorPageQueryCommand) {
		Page<DynamicDataIndicatorDO> page = iDynamicDataIndicatorService.listPage(dynamicDataIndicatorPageQueryCommand);
		return DynamicDataIndicatorAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 动态数据指标 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorVO> executeDetail(IdCommand detailCommand) {
		DynamicDataIndicatorDO byId = iDynamicDataIndicatorService.getById(detailCommand.getId());
		DynamicDataIndicatorVO dynamicDataIndicatorVO = DynamicDataIndicatorAppStructMapping.instance.dynamicDataIndicatorDOToDynamicDataIndicatorVO(byId);
		return SingleResponse.of(dynamicDataIndicatorVO);
	}
	/**
	 * 执行 动态数据指标 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		DynamicDataIndicatorDO byId = iDynamicDataIndicatorService.getById(detailForUpdateCommand.getId());
		DynamicDataIndicatorVO dynamicDataIndicatorVO = DynamicDataIndicatorAppStructMapping.instance.dynamicDataIndicatorDOToDynamicDataIndicatorVO(byId);
		return SingleResponse.of(dynamicDataIndicatorVO);
	}


	@Autowired
	public void setIDynamicDataIndicatorService(IDynamicDataIndicatorService iDynamicDataIndicatorService) {
		this.iDynamicDataIndicatorService = iDynamicDataIndicatorService;
	}
    @Autowired
    public void setiDynamicTableFieldService(IDynamicTableFieldService iDynamicTableFieldService) {
        this.iDynamicTableFieldService = iDynamicTableFieldService;
    }
    @Autowired
    public void setiDynamicTableService(IDynamicTableService iDynamicTableService) {
        this.iDynamicTableService = iDynamicTableService;
    }
}
