package com.particle.audit.app.auditrecord.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.audit.app.auditrecord.structmapping.AuditRecordAppStructMapping;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordPageQueryCommand;
import com.particle.audit.client.auditrecord.dto.command.representation.AuditRecordQueryListCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.audit.client.auditrecord.dto.data.AuditResultDictVO;
import com.particle.audit.domain.AuditDictItemInfo;
import com.particle.audit.domain.enums.AuditResult;
import com.particle.audit.domain.gateway.AuditDictGateway;
import com.particle.audit.infrastructure.auditrecord.dos.AuditRecordDO;
import com.particle.audit.infrastructure.auditrecord.service.IAuditRecordService;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 审核记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Component
@Validated
public class AuditRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IAuditRecordService iAuditRecordService;

	private AuditDictGateway auditDictGateway;
	/**
	 * 执行 审核记录 列表查询指令
	 * @param auditRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<AuditRecordVO> execute(@Valid AuditRecordQueryListCommand auditRecordQueryListCommand) {
		List<AuditRecordDO> auditRecordDO = iAuditRecordService.list(auditRecordQueryListCommand);
		List<AuditRecordVO> auditRecordVOs = AuditRecordAppStructMapping.instance.auditRecordDOsToAuditRecordVOs(auditRecordDO);
		return MultiResponse.of(auditRecordVOs);
	}
	/**
	 * 执行 审核记录 分页查询指令
	 * @param auditRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<AuditRecordVO> execute(@Valid AuditRecordPageQueryCommand auditRecordPageQueryCommand) {
		Page<AuditRecordDO> page = iAuditRecordService.listPage(auditRecordPageQueryCommand);
		return AuditRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 审核记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<AuditRecordVO> executeDetail(CommonIdCommand detailCommand) {
		AuditRecordDO byId = iAuditRecordService.getById(detailCommand.getId());
		AuditRecordVO auditRecordVO = AuditRecordAppStructMapping.instance.auditRecordDOToAuditRecordVO(byId);
		return SingleResponse.of(auditRecordVO);
	}
	/**
	 * 执行 审核记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<AuditRecordVO> executeDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
		AuditRecordDO byId = iAuditRecordService.getById(detailForUpdateCommand.getId());
		AuditRecordVO auditRecordVO = AuditRecordAppStructMapping.instance.auditRecordDOToAuditRecordVO(byId);
		return SingleResponse.of(auditRecordVO);
	}

	public SingleResponse<AuditResultDictVO> auditResultDict() {
		List<AuditDictItemInfo> auditDictItemInfoList = auditDictGateway.getDictItemListByGroupCode(AuditResult.Group.audit_result.groupCode());
		AuditResultDictVO auditResultDictVO = new AuditResultDictVO();
		List<AuditResultDictVO.AuditResultDictItemVO> auditResultDictItemVOList = new ArrayList<>(auditDictItemInfoList.size());
		auditResultDictVO.setAuditResultDictItemVOList(auditResultDictItemVOList);

		for (AuditDictItemInfo auditDictItemInfo : auditDictItemInfoList) {
			Boolean isPass = null;
			Boolean isUnPass = null;
            if (AuditResult.pass.itemValue().equals(auditDictItemInfo.getValue())) {
				isPass = true;
            }
			if (AuditResult.unpass.itemValue().equals(auditDictItemInfo.getValue())) {
				isUnPass = true;
			}

			AuditResultDictVO.AuditResultDictItemVO auditResultDictItemVO = AuditResultDictVO.createItem(
					auditDictItemInfo.getId(),
					auditDictItemInfo.getCode(),
					auditDictItemInfo.getName(),
					auditDictItemInfo.getValue(),
					isPass, isUnPass,
					auditDictItemInfo.getPrivateFlag(),
					auditDictItemInfo.getPrivateFlagMemo(),
					auditDictItemInfo.getGroupFlag(),
					auditDictItemInfo.getGroupFlagMemo(),
					auditDictItemInfo.getTags()
			);
			auditResultDictItemVOList.add(auditResultDictItemVO);
			if (isPass != null && isPass) {
				auditResultDictVO.setPass(auditResultDictItemVO);
			}
			if (isUnPass != null && isUnPass) {
				auditResultDictVO.setUnPass(auditResultDictItemVO);
			}

		}

		return SingleResponse.of(auditResultDictVO);
	}
	@Autowired
	public void setIAuditRecordService(IAuditRecordService iAuditRecordService) {
		this.iAuditRecordService = iAuditRecordService;
	}
	@Autowired
	public void setAuditDictGateway(AuditDictGateway auditDictGateway) {
		this.auditDictGateway = auditDictGateway;
	}
}
