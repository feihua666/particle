package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsContentAppStructMapping;
import com.particle.cms.client.dto.command.CmsContentUpdateCommand;
import com.particle.cms.client.dto.data.CmsContentVO;
import com.particle.cms.domain.CmsContent;
import com.particle.cms.domain.CmsContentId;
import com.particle.cms.domain.gateway.CmsAuditGateway;
import com.particle.cms.domain.gateway.CmsContentGateway;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.CommonAuditCommand;
import com.particle.common.client.dto.command.CommonPublicCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.light.share.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentGateway cmsContentGateway;

	private CmsAuditGateway cmsAuditGateway;
	/**
	 * 执行 内容 更新指令
	 * @param cmsContentUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsContentVO> execute(@Valid CmsContentUpdateCommand cmsContentUpdateCommand) {
		CmsContent cmsContent = createByCmsContentUpdateCommand(cmsContentUpdateCommand);
		cmsContent.setUpdateControl(cmsContentUpdateCommand);

		cmsContent.auditWait();
		cmsContent.unPublish();
		boolean save = cmsContentGateway.save(cmsContent);
		if (save) {
			return SingleResponse.of(CmsContentAppStructMapping.instance.toCmsContentVO(cmsContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 执行 内容 发布指令
	 * @param cmsContentPublicCommand
	 * @return
	 */
	public SingleResponse<CmsContentVO> publish(@Valid CommonPublicCommand cmsContentPublicCommand) {
		CmsContent cmsContent = CmsContent.create(CmsContentId.of(cmsContentPublicCommand.getId()));
		if (cmsContentPublicCommand.getIsPublic()) {
			cmsContent.publish();
		}else {
			cmsContent.unPublish();
		}

		boolean save = cmsContentGateway.save(cmsContent);
		if (save) {
			return SingleResponse.of(CmsContentAppStructMapping.instance.toCmsContentVO(cmsContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 执行 内容 审核指令
	 * @param commonAuditCommand
	 * @return
	 */
	public SingleResponse<CmsContentVO> audit(@Valid CommonAuditCommand commonAuditCommand) {
		CmsContent cmsContent = cmsContentGateway.getById(CmsContentId.of(commonAuditCommand.getId()));

		// 审核前状态
		Long preStatusDictId = cmsContent.getAuditStatusDictId();

		Long auditResultDictId = commonAuditCommand.getAuditResultDictId();
		Boolean isPassAudit = commonAuditCommand.getIsPassAudit();


		// 根据审核结果字典id 判断是否通过审核
		if (auditResultDictId != null) {
			Long passDictId = cmsAuditGateway.getPassAuditResultDictId();
			if (auditResultDictId.equals(passDictId)) {
				isPassAudit = true;
			}else {
				Long unPassDictId = cmsAuditGateway.getUnPassAuditResultDictId();
				if (auditResultDictId.equals(unPassDictId)) {
					isPassAudit = false;
				}
			}
		}
		// 根据是否通过审核，修改审核状态
		if(isPassAudit != null){
			if (isPassAudit) {
				if (auditResultDictId == null) {
					auditResultDictId = cmsAuditGateway.getPassAuditResultDictId();
				}
				cmsContent.auditPass();
			}else {
				if (auditResultDictId == null) {
					auditResultDictId = cmsAuditGateway.getUnPassAuditResultDictId();
				}
				cmsContent.auditUnPass();
			}
		}else {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR);
		}

		boolean save = cmsContentGateway.save(cmsContent);
		if (save) {
			Long postStatusDictId = cmsContent.getAuditStatusDictId();
			cmsAuditGateway.createCmsContentAuditRecord(cmsContent.getId(),
					auditResultDictId,
					commonAuditCommand.getLoginUserId(),
					preStatusDictId,
					postStatusDictId,
					commonAuditCommand.getComment()
			);
			return SingleResponse.of(CmsContentAppStructMapping.instance.toCmsContentVO(cmsContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 根据内容更新指令创建内容模型
	 * @param cmsContentUpdateCommand
	 * @return
	 */
	private CmsContent createByCmsContentUpdateCommand(CmsContentUpdateCommand cmsContentUpdateCommand){
		CmsContent cmsContent = CmsContent.create();
		CmsContentUpdateCommandToCmsContentMapping.instance.fillCmsContentByCmsContentUpdateCommand(cmsContent, cmsContentUpdateCommand);
		return cmsContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsContentUpdateCommandToCmsContentMapping{
		CmsContentUpdateCommandToCmsContentMapping instance = Mappers.getMapper(CmsContentUpdateCommandToCmsContentMapping.class );

		default CmsContentId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsContent
		 * @param cmsContentUpdateCommand
		 */
		void fillCmsContentByCmsContentUpdateCommand(@MappingTarget CmsContent cmsContent, CmsContentUpdateCommand cmsContentUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsContentGateway
	 */
	@Autowired
	public void setCmsContentGateway(CmsContentGateway cmsContentGateway) {
		this.cmsContentGateway = cmsContentGateway;
	}

	@Autowired
	public void setCmsAuditGateway(CmsAuditGateway cmsAuditGateway) {
		this.cmsAuditGateway = cmsAuditGateway;
	}
}
