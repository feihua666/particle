package com.particle.area.app.executor.representation;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.area.app.structmapping.AreaAppStructMapping;
import com.particle.area.client.dto.command.representation.AreaItemsQueryListCommonCommand;
import com.particle.area.client.dto.command.representation.AreaPageQueryCommand;
import com.particle.area.client.dto.command.representation.AreaQueryListCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.domain.enums.AreaType;
import com.particle.area.domain.gateway.AreaDictGateway;
import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.area.infrastructure.service.IAreaService;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 区域 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2022-07-18
 */
@Component
@Validated
public class AreaQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAreaService iAreaService;

	private AreaDictGateway areaDictGateway;

	/**
	 * 执行 区域 列表查询指令
	 * @param areaQueryListCommand
	 * @return
	 */
	public MultiResponse<AreaVO> execute(@Valid AreaQueryListCommand areaQueryListCommand) {
		List<AreaDO> areaDO = iAreaService.list(areaQueryListCommand);
		List<AreaVO> areaVOs = AreaAppStructMapping.instance.areaDOsToAreaVOs(areaDO);
		return MultiResponse.of(areaVOs);
	}
	/**
	 * 执行 区域 分页查询指令
	 * @param areaPageQueryCommand
	 * @return
	 */
	public PageResponse<AreaVO> execute(@Valid AreaPageQueryCommand areaPageQueryCommand) {
		Page<AreaDO> page = iAreaService.listPage(areaPageQueryCommand);
		return AreaAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 区域 展示用详情查询指令
	 * @param areaQueryDetailCommand
	 * @return
	 */
	public SingleResponse<AreaVO> executeDetail(IdCommand areaQueryDetailCommand) {
		AreaDO byId = iAreaService.getById(areaQueryDetailCommand.getId());
		AreaVO areaVO = AreaAppStructMapping.instance.areaDOToAreaVO(byId);
		return SingleResponse.of(areaVO);
	}
	/**
	 * 执行 区域 更新用详情查询指令
	 * @param areaQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AreaVO> executeDetailForUpdate(IdCommand areaQueryDetailForUpdateCommand) {
		AreaDO byId = iAreaService.getById(areaQueryDetailForUpdateCommand.getId());
		AreaVO areaVO = AreaAppStructMapping.instance.areaDOToAreaVO(byId);
		return SingleResponse.of(areaVO);
	}

	/**
	 * 列表查询，主要用于下拉选择
	 * @param areaItemsQueryListCommonCommand
	 * @return
	 */
	public MultiResponse<AreaVO> queryItems(AreaItemsQueryListCommonCommand areaItemsQueryListCommonCommand) {
		Long parentId = areaItemsQueryListCommonCommand.getParentId();
		Long typeDictId = areaItemsQueryListCommonCommand.getTypeDictId();
		if (typeDictId == null && StrUtil.isNotEmpty(areaItemsQueryListCommonCommand.getTypeDictValue())) {
			typeDictId = areaDictGateway.getDictIdByGroupCodeAndItemValue(AreaType.Group.area_type.groupCode(),  areaItemsQueryListCommonCommand.getTypeDictValue());
		}

		Assert.isTrue(parentId != null || typeDictId != null, "parentId和typeDictId或typeDictValue不能同时为空");

		List<AreaDO> list = iAreaService.list(
				Wrappers.<AreaDO>lambdaQuery()
						.eq(parentId != null,AreaDO::getParentId, parentId)
						.eq(typeDictId != null,AreaDO::getTypeDictId, typeDictId)
						.orderByAsc(AreaDO::getSeq)

		);
		List<AreaVO> areaVOs = AreaAppStructMapping.instance.areaDOsToAreaVOs(list);
		return MultiResponse.of(areaVOs);
	}
	@Autowired
	public void setIAreaService(IAreaService iAreaService) {
		this.iAreaService = iAreaService;
	}
	@Autowired
	public void setAreaDictGateway(AreaDictGateway areaDictGateway) {
		this.areaDictGateway = areaDictGateway;
	}
}
