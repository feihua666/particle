package com.particle.lowcode.app.generator.executor.representation;

import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentGenAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentGenDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentGenService;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenPageQueryCommand;
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
 * 低代码生成 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-02-10
 */
@Component
@Validated
public class LowcodeSegmentGenQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ILowcodeSegmentGenService iLowcodeSegmentGenService;

	/**
	 * 执行 低代码生成 列表查询指令
	 * @param lowcodeSegmentGenQueryListCommand
	 * @return
	 */
	public MultiResponse<LowcodeSegmentGenVO> execute(@Valid LowcodeSegmentGenQueryListCommand lowcodeSegmentGenQueryListCommand) {
		List<LowcodeSegmentGenDO> lowcodeSegmentGenDO = iLowcodeSegmentGenService.list(lowcodeSegmentGenQueryListCommand);
		List<LowcodeSegmentGenVO> lowcodeSegmentGenVOs = LowcodeSegmentGenAppStructMapping.instance.lowcodeSegmentGenDOsToLowcodeSegmentGenVOs(lowcodeSegmentGenDO);
		return MultiResponse.of(lowcodeSegmentGenVOs);
	}
	/**
	 * 执行 低代码生成 分页查询指令
	 * @param lowcodeSegmentGenPageQueryCommand
	 * @return
	 */
	public PageResponse<LowcodeSegmentGenVO> execute(@Valid LowcodeSegmentGenPageQueryCommand lowcodeSegmentGenPageQueryCommand) {
		Page<LowcodeSegmentGenDO> page = iLowcodeSegmentGenService.listPage(lowcodeSegmentGenPageQueryCommand);
		return LowcodeSegmentGenAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 低代码生成 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentGenVO> executeDetail(IdCommand detailCommand) {
		LowcodeSegmentGenDO byId = iLowcodeSegmentGenService.getById(detailCommand.getId());
		LowcodeSegmentGenVO lowcodeSegmentGenVO = LowcodeSegmentGenAppStructMapping.instance.lowcodeSegmentGenDOToLowcodeSegmentGenVO(byId);
		return SingleResponse.of(lowcodeSegmentGenVO);
	}
	/**
	 * 执行 低代码生成 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentGenVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		LowcodeSegmentGenDO byId = iLowcodeSegmentGenService.getById(detailForUpdateCommand.getId());
		LowcodeSegmentGenVO lowcodeSegmentGenVO = LowcodeSegmentGenAppStructMapping.instance.lowcodeSegmentGenDOToLowcodeSegmentGenVO(byId);
		return SingleResponse.of(lowcodeSegmentGenVO);
	}

	@Autowired
	public void setILowcodeSegmentGenService(ILowcodeSegmentGenService iLowcodeSegmentGenService) {
		this.iLowcodeSegmentGenService = iLowcodeSegmentGenService;
	}
}
