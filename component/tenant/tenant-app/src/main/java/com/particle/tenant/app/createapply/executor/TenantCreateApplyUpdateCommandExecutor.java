package com.particle.tenant.app.createapply.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.spring.SpringContextHolder;
import com.particle.tenant.app.createapply.structmapping.TenantCreateApplyAppStructMapping;
import com.particle.tenant.app.executor.TenantCreateCommandExecutor;
import com.particle.tenant.app.executor.TenantUserCreateCommandExecutor;
import com.particle.tenant.client.createapply.dto.command.*;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.client.dto.command.TenantCreateCommand;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyAuditDomainEvent;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.tenant.domain.gateway.TenantDictGateway;
import com.particle.tenant.domain.gateway.TenantRoleGateway;
import com.particle.tenant.domain.tenantfunc.TenantFunc;
import com.particle.tenant.domain.tenantfunc.gateway.TenantFuncGateway;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplication;
import com.particle.tenant.domain.tenantfuncapplication.gateway.TenantFuncApplicationGateway;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 租户创建申请 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Slf4j
@Component
@Validated
public class TenantCreateApplyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private TenantCreateApplyGateway tenantCreateApplyGateway;

	private TenantCreateCommandExecutor tenantCreateCommandExecutor;

	private TenantUserCreateCommandExecutor tenantUserCreateCommandExecutor;

	private TenantRoleGateway tenantRoleGateway;

	private TenantDictGateway tenantDictGateway;

	private TenantFuncApplicationGateway tenantFuncApplicationGateway;

	private TenantFuncGateway tenantFuncGateway;

	/**
	 * 执行 租户创建申请 更新指令
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> execute(@Valid TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand) {
		TenantCreateApply tenantCreateApply = createByTenantCreateApplyUpdateCommand(tenantCreateApplyUpdateCommand);
		if (tenantCreateApplyUpdateCommand.getExtJsonObj() != null) {
			MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = SpringContextHolder.getBean(MappingJackson2HttpMessageConverter.class);

			tenantCreateApply.changeExtJson(JsonTool.toJsonStrForHttp(tenantCreateApplyUpdateCommand.getExtJsonObj(),jackson2HttpMessageConverter.getObjectMapper()));
		}
		// 依赖用户选择，如果没有填写不计算天数
		//tenantCreateApply.changeEffectiveAtNowIfNull();
		tenantCreateApply.calculateDays();

		tenantCreateApply.setUpdateControl(tenantCreateApplyUpdateCommand);
		boolean save = tenantCreateApplyGateway.save(tenantCreateApply);
		if (save) {
			return SingleResponse.of(TenantCreateApplyAppStructMapping.instance.toTenantCreateApplyVO(tenantCreateApply));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}
	/**
	 * 执行 租户创建申请 审核指令
	 * @param tenantCreateApplyAuditCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> audit(@Valid TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand) {
		TenantCreateApply tenantCreateApply = createByTenantCreateApplyAuditCommand(tenantCreateApplyAuditCommand);
		tenantCreateApply.setUpdateControl(tenantCreateApplyAuditCommand);

		boolean save = tenantCreateApplyGateway.save(tenantCreateApply);
		if (save) {

			if (tenantCreateApply.checkIsAuditPass()) {
				log.info("租户申请审批通过 applyId={}",tenantCreateApplyAuditCommand.getId());
				onAuditPass(tenantCreateApply,tenantCreateApplyAuditCommand);
			}

			return SingleResponse.of(TenantCreateApplyAppStructMapping.instance.toTenantCreateApplyVO(tenantCreateApply));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 审核通过后处理
	 * @param tenantCreateApply
	 * @param tenantCreateApplyAuditCommand
	 */
	private void onAuditPass(TenantCreateApply tenantCreateApply, TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand){
		// 如果审核通过，
		// 1 添加租户
		// 2 将用户绑定到租户下
		// 3 添加租户下超级管理员角色
		// 4 为租户分配应用
		// 5 绑定用户
		// 6 通知
		TenantCreateApply byId = tenantCreateApplyGateway.getById(tenantCreateApply.getId());
		TenantCreateCommand tenantCreateCommand = new TenantCreateCommand();
		tenantCreateCommand.setName(byId.getName());
		tenantCreateCommand.setCode(IdUtil.fastSimpleUUID());
		tenantCreateCommand.setIsDisabled(false);
		tenantCreateCommand.setContactUserName(byId.getContactUserName());
		tenantCreateCommand.setContactUserPhone(byId.getContactUserPhone());
		tenantCreateCommand.setContactUserEmail(byId.getContactUserEmail());
		tenantCreateCommand.setIsFormal(byId.getIsFormal());
		tenantCreateCommand.setUserLimitCount(byId.getUserLimitCount());
		tenantCreateCommand.setEffectiveAt(byId.getEffectiveAt());
		tenantCreateCommand.setInvalidAt(byId.getInvalidAt());
		tenantCreateCommand.setMasterUserId(byId.getApplyUserId());
		// 1 添加租户
		SingleResponse<TenantVO> tenantSave = tenantCreateCommandExecutor.execute(tenantCreateCommand);
		if (tenantSave.isSuccess()) {
			log.info("添加租户成功 tenantName={}",tenantCreateCommand.getName());
			// 设置已申请的租户id
			byId.changeAppliedTenantId(tenantSave.getData().getId());
			tenantCreateApplyGateway.save(byId);


			// 2 将用户绑定到租户下
			TenantUserCreateCommand tenantUserCreateCommand = new TenantUserCreateCommand();
			tenantUserCreateCommand.setUserId(byId.getApplyUserId());
			tenantUserCreateCommand.setIsExpired(false);
			tenantUserCreateCommand.setIsLeave(false);
			tenantUserCreateCommand.setTenantId(tenantSave.getData().getId());
			SingleResponse<TenantUserVO> tenantUserSave = tenantUserCreateCommandExecutor.execute(tenantUserCreateCommand);
			if (tenantUserSave.isSuccess()) {
				log.info("将用户绑定到租户下成功 userId={}",tenantUserCreateCommand.getUserId());

				// 3 添加租户下超级管理员角色
				Long roleId = tenantRoleGateway.createRole(tenantCreateApplyAuditCommand.getTenantSuperAdminRoleCode(), "超级管理员", false, tenantSave.getData().getId());
				log.info("添加租户下超级管理员角色成功 roleId={}",roleId);

				// 4 为租户分配应用及应用下的功能
				String extJson = byId.getExtJson();
				if (StrUtil.isNotEmpty(extJson)) {
					log.info("开始为租户分配应用及应用下的功能");

					TenantCreateApplyExtJsonCommand tenantCreateApplyExtJsonCommand = JSONUtil.toBean(extJson, TenantCreateApplyExtJsonCommand.class);
					if (CollectionUtil.isNotEmpty(tenantCreateApplyExtJsonCommand.getFuncApplications())) {
						for (TenantCreateApplyExtJsonFuncApplicationCommand funcApplication : tenantCreateApplyExtJsonCommand.getFuncApplications()) {
							TenantFuncApplication tenantFuncApplication = TenantFuncApplication.create();
							tenantFuncApplication.simpleFill(funcApplication.getFuncApplicationId(),tenantSave.getData().getId());
							tenantFuncApplicationGateway.save(tenantFuncApplication);
							log.info("添加租户应用成功 funcApplicationId={}",tenantFuncApplication.getFuncApplicationId());

							//	保存完应用，保存应用下的功能
							List<Long> funcIds = funcApplication.getFuncIds();
							if (CollectionUtil.isNotEmpty(funcIds)) {
								for (Long funcId : funcIds) {
									TenantFunc tenantFunc = TenantFunc.create();
									tenantFunc.simpleFill(funcId,funcApplication.getFuncApplicationId(),tenantSave.getData().getId());
									tenantFuncGateway.save(tenantFunc);
									log.info("添加租户功能成功 funcApplicationId={},funcId={}",tenantFuncApplication.getFuncApplicationId(),funcId);

								}
							}
						}
					}
				}
				// 5 绑定用户
				tenantRoleGateway.createRoleUserRel(roleId,byId.getApplyUserId(), tenantSave.getData().getId());
				//	6 通知
				// todo 通知逻辑
			}
		}
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyUpdateCommand(TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand){
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyUpdateCommand(tenantCreateApply, tenantCreateApplyUpdateCommand);
		return tenantCreateApply;
	}
	/**
	 * 根据区域创建指令创建区域模型
	 * @param tenantCreateApplyAuditCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyAuditCommand(TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand){
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyAuditCommand(tenantCreateApply, tenantCreateApplyAuditCommand);
		return tenantCreateApply;
	}
	@Mapper
	interface TenantCreateApplyUpdateCommandToTenantCreateApplyMapping{
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping instance = Mappers.getMapper(TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.class );

		default TenantCreateApplyId map(Long id){
			if (id == null) {
				return null;
			}
			return TenantCreateApplyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param tenantCreateApply
		 * @param tenantCreateApplyUpdateCommand
		 */
		void fillTenantCreateApplyByTenantCreateApplyUpdateCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand);
		void fillTenantCreateApplyByTenantCreateApplyAuditCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand);
	}

	/**
	 * 注入使用set方法
	 * @param tenantCreateApplyGateway
	 */
	@Autowired
	public void setTenantCreateApplyGateway(TenantCreateApplyGateway tenantCreateApplyGateway) {
		this.tenantCreateApplyGateway = tenantCreateApplyGateway;
	}
	@Autowired
	public void setTenantCreateCommandExecutor(TenantCreateCommandExecutor tenantCreateCommandExecutor) {
		this.tenantCreateCommandExecutor = tenantCreateCommandExecutor;
	}
	@Autowired
	public void setTenantUserCreateCommandExecutor(TenantUserCreateCommandExecutor tenantUserCreateCommandExecutor) {
		this.tenantUserCreateCommandExecutor = tenantUserCreateCommandExecutor;
	}

	@Autowired
	public void setTenantRoleGateway(TenantRoleGateway tenantRoleGateway) {
		this.tenantRoleGateway = tenantRoleGateway;
	}

	@Autowired
	public void setTenantDictGateway(TenantDictGateway tenantDictGateway) {
		this.tenantDictGateway = tenantDictGateway;
	}

	@Autowired
	public void setTenantFuncApplicationGateway(TenantFuncApplicationGateway tenantFuncApplicationGateway) {
		this.tenantFuncApplicationGateway = tenantFuncApplicationGateway;
	}
	@Autowired
	public void setTenantFuncGateway(TenantFuncGateway tenantFuncGateway) {
		this.tenantFuncGateway = tenantFuncGateway;
	}
}
