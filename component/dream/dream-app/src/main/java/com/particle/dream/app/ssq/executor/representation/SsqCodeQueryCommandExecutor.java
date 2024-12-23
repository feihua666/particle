package com.particle.dream.app.ssq.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dream.app.ssq.structmapping.SsqCodeAppStructMapping;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodePageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeVO;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeDO;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 双色球号码 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Component
@Validated
public class SsqCodeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ISsqCodeService iSsqCodeService;

	/**
	 * 执行 双色球号码 列表查询指令
	 * @param ssqCodeQueryListCommand
	 * @return
	 */
	public MultiResponse<SsqCodeVO> execute(@Valid SsqCodeQueryListCommand ssqCodeQueryListCommand) {
		List<SsqCodeDO> ssqCodeDO = iSsqCodeService.list(ssqCodeQueryListCommand);
		List<SsqCodeVO> ssqCodeVOs = SsqCodeAppStructMapping.instance.ssqCodeDOsToSsqCodeVOs(ssqCodeDO);
		return MultiResponse.of(ssqCodeVOs);
	}
	/**
	 * 执行 双色球号码 分页查询指令
	 * @param ssqCodePageQueryCommand
	 * @return
	 */
	public PageResponse<SsqCodeVO> execute(@Valid SsqCodePageQueryCommand ssqCodePageQueryCommand) {
		Page<SsqCodeDO> page = iSsqCodeService.listPage(ssqCodePageQueryCommand);
		return SsqCodeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 双色球号码 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<SsqCodeVO> executeDetail(IdCommand detailCommand) {
		SsqCodeDO byId = iSsqCodeService.getById(detailCommand.getId());
		SsqCodeVO ssqCodeVO = SsqCodeAppStructMapping.instance.ssqCodeDOToSsqCodeVO(byId);
		return SingleResponse.of(ssqCodeVO);
	}

	@Autowired
	public void setISsqCodeService(ISsqCodeService iSsqCodeService) {
		this.iSsqCodeService = iSsqCodeService;
	}
}
