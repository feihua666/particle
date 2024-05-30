package com.particle.dream.app.ssq.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dream.app.ssq.structmapping.SsqCodeOpenedAppStructMapping;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedPageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeOpenedService;
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
 * 双色球开奖 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Component
@Validated
public class SsqCodeOpenedQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ISsqCodeOpenedService iSsqCodeOpenedService;

	/**
	 * 执行 双色球开奖 列表查询指令
	 * @param ssqCodeOpenedQueryListCommand
	 * @return
	 */
	public MultiResponse<SsqCodeOpenedVO> execute(@Valid SsqCodeOpenedQueryListCommand ssqCodeOpenedQueryListCommand) {
		List<SsqCodeOpenedDO> ssqCodeOpenedDO = iSsqCodeOpenedService.list(ssqCodeOpenedQueryListCommand);
		List<SsqCodeOpenedVO> ssqCodeOpenedVOs = SsqCodeOpenedAppStructMapping.instance.ssqCodeOpenedDOsToSsqCodeOpenedVOs(ssqCodeOpenedDO);
		return MultiResponse.of(ssqCodeOpenedVOs);
	}
	/**
	 * 执行 双色球开奖 分页查询指令
	 * @param ssqCodeOpenedPageQueryCommand
	 * @return
	 */
	public PageResponse<SsqCodeOpenedVO> execute(@Valid SsqCodeOpenedPageQueryCommand ssqCodeOpenedPageQueryCommand) {
		Page<SsqCodeOpenedDO> page = iSsqCodeOpenedService.listPage(ssqCodeOpenedPageQueryCommand);
		return SsqCodeOpenedAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 双色球开奖 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<SsqCodeOpenedVO> executeDetail(IdCommand detailCommand) {
		SsqCodeOpenedDO byId = iSsqCodeOpenedService.getById(detailCommand.getId());
		SsqCodeOpenedVO ssqCodeOpenedVO = SsqCodeOpenedAppStructMapping.instance.ssqCodeOpenedDOToSsqCodeOpenedVO(byId);
		return SingleResponse.of(ssqCodeOpenedVO);
	}


	@Autowired
	public void setISsqCodeOpenedService(ISsqCodeOpenedService iSsqCodeOpenedService) {
		this.iSsqCodeOpenedService = iSsqCodeOpenedService;
	}
}
