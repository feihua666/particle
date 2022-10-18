package com.particle.area.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.area.app.structmapping.AreaAppStructMapping;
import com.particle.area.client.dto.command.representation.AreaPageQueryCommand;
import com.particle.area.client.dto.command.representation.AreaQueryDetailCommand;
import com.particle.area.client.dto.command.representation.AreaQueryDetailForUpdateCommand;
import com.particle.area.client.dto.command.representation.AreaQueryListCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.area.infrastructure.service.IAreaService;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
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
	public SingleResponse<AreaVO> execute(AreaQueryDetailCommand areaQueryDetailCommand) {
		AreaDO byId = iAreaService.getById(areaQueryDetailCommand.getId());
		AreaVO areaVO = AreaAppStructMapping.instance.areaDOToAreaVO(byId);
		return SingleResponse.of(areaVO);
	}
	/**
	 * 执行 区域 更新用详情查询指令
	 * @param areaQueryDetailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AreaVO> execute(AreaQueryDetailForUpdateCommand areaQueryDetailForUpdateCommand) {
		AreaDO byId = iAreaService.getById(areaQueryDetailForUpdateCommand.getId());
		AreaVO areaVO = AreaAppStructMapping.instance.areaDOToAreaVO(byId);
		return SingleResponse.of(areaVO);
	}

	@Autowired
	public void setIAreaService(IAreaService iAreaService) {
		this.iAreaService = iAreaService;
	}
}
