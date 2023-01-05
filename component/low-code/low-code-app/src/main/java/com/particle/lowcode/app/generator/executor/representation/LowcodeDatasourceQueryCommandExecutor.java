package com.particle.lowcode.app.generator.executor.representation;

import com.particle.lowcode.app.generator.structmapping.LowcodeDatasourceAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourceQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeDatasourceDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeDatasourceService;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourcePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 低代码数据源 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class LowcodeDatasourceQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ILowcodeDatasourceService iLowcodeDatasourceService;

	/**
	 * 执行 低代码数据源 列表查询指令
	 * @param lowcodeDatasourceQueryListCommand
	 * @return
	 */
	public MultiResponse<LowcodeDatasourceVO> execute(@Valid LowcodeDatasourceQueryListCommand lowcodeDatasourceQueryListCommand) {
		List<LowcodeDatasourceDO> lowcodeDatasourceDO = iLowcodeDatasourceService.list(lowcodeDatasourceQueryListCommand);
		List<LowcodeDatasourceVO> lowcodeDatasourceVOs = LowcodeDatasourceAppStructMapping.instance.lowcodeDatasourceDOsToLowcodeDatasourceVOs(lowcodeDatasourceDO);
		return MultiResponse.of(lowcodeDatasourceVOs);
	}
	/**
	 * 执行 低代码数据源 分页查询指令
	 * @param lowcodeDatasourcePageQueryCommand
	 * @return
	 */
	public PageResponse<LowcodeDatasourceVO> execute(@Valid LowcodeDatasourcePageQueryCommand lowcodeDatasourcePageQueryCommand) {
		Page<LowcodeDatasourceDO> page = iLowcodeDatasourceService.listPage(lowcodeDatasourcePageQueryCommand);
		return LowcodeDatasourceAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 低代码数据源 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<LowcodeDatasourceVO> executeDetail(IdCommand detailCommand) {
		LowcodeDatasourceDO byId = iLowcodeDatasourceService.getById(detailCommand.getId());
		LowcodeDatasourceVO lowcodeDatasourceVO = LowcodeDatasourceAppStructMapping.instance.lowcodeDatasourceDOToLowcodeDatasourceVO(byId);
		return SingleResponse.of(lowcodeDatasourceVO);
	}
	/**
	 * 执行 低代码数据源 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeDatasourceVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		LowcodeDatasourceDO byId = iLowcodeDatasourceService.getById(detailForUpdateCommand.getId());
		LowcodeDatasourceVO lowcodeDatasourceVO = LowcodeDatasourceAppStructMapping.instance.lowcodeDatasourceDOToLowcodeDatasourceVO(byId);
		return SingleResponse.of(lowcodeDatasourceVO);
	}

	@Autowired
	public void setILowcodeDatasourceService(ILowcodeDatasourceService iLowcodeDatasourceService) {
		this.iLowcodeDatasourceService = iLowcodeDatasourceService;
	}
}
