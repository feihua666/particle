package com.particle.tenant.app.createapply.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.domain.event.TemplatingDomainMessageEvent;
import com.particle.component.light.share.message.MessageConstants;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.spring.SpringContextHolder;
import com.particle.tenant.app.createapply.structmapping.TenantCreateApplyAppStructMapping;
import com.particle.tenant.app.executor.TenantCreateCommandExecutor;
import com.particle.tenant.app.executor.TenantUserCreateCommandExecutor;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyAuditCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyExtJsonCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyExtJsonFuncApplicationCommand;
import com.particle.tenant.client.createapply.dto.command.TenantCreateApplyUpdateCommand;
import com.particle.tenant.client.createapply.dto.data.TenantCreateApplyVO;
import com.particle.tenant.client.dto.command.TenantCreateCommand;
import com.particle.tenant.client.dto.command.TenantUserCreateCommand;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.client.dto.data.TenantVO;
import com.particle.tenant.domain.createapply.TenantCreateApply;
import com.particle.tenant.domain.createapply.TenantCreateApplyAuditPassDomainEvent;
import com.particle.tenant.domain.createapply.TenantCreateApplyId;
import com.particle.tenant.domain.createapply.gateway.TenantCreateApplyGateway;
import com.particle.tenant.domain.gateway.TenantDictGateway;
import com.particle.tenant.domain.gateway.TenantRoleGateway;
import com.particle.tenant.domain.gateway.TenantUserUserGateway;
import com.particle.tenant.domain.tenantfunc.TenantFunc;
import com.particle.tenant.domain.tenantfunc.gateway.TenantFuncGateway;
import com.particle.tenant.domain.tenantfuncapplication.TenantFuncApplication;
import com.particle.tenant.domain.tenantfuncapplication.gateway.TenantFuncApplicationGateway;
import com.particle.user.domain.enums.UserAccountType;
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
import java.util.Optional;

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
public class TenantCreateApplyUpdateCommandExecutor extends AbstractBaseExecutor {

	/**
	 * 审核通过并添加用户时场景
	 */
	public static String tenantCreateApplyUserAddScene = "tenantCreateApplyUserAddScene";



	private TenantCreateApplyGateway tenantCreateApplyGateway;

	private TenantCreateCommandExecutor tenantCreateCommandExecutor;

	private TenantUserCreateCommandExecutor tenantUserCreateCommandExecutor;

	private TenantRoleGateway tenantRoleGateway;

	private TenantDictGateway tenantDictGateway;

	private TenantFuncApplicationGateway tenantFuncApplicationGateway;

	private TenantFuncGateway tenantFuncGateway;

	private TenantUserUserGateway tenantUserUserGateway;

	/**
	 * 执行 租户创建申请 更新指令
	 *
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> execute(@Valid TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand) {
		TenantCreateApply tenantCreateApply = createByTenantCreateApplyUpdateCommand(tenantCreateApplyUpdateCommand);
		if (tenantCreateApplyUpdateCommand.getExtJsonObj() != null) {
			MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = SpringContextHolder.getBean(MappingJackson2HttpMessageConverter.class);

			tenantCreateApply.changeExtJson(JsonTool.toJsonStrForHttp(tenantCreateApplyUpdateCommand.getExtJsonObj(), jackson2HttpMessageConverter.getObjectMapper()));
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
	 *
	 * @param tenantCreateApplyAuditCommand
	 * @return
	 */
	public SingleResponse<TenantCreateApplyVO> audit(@Valid TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand) {
		TenantCreateApply tenantCreateApply = createByTenantCreateApplyAuditCommand(tenantCreateApplyAuditCommand);
		tenantCreateApply.setUpdateControl(tenantCreateApplyAuditCommand);

		boolean save = tenantCreateApplyGateway.save(tenantCreateApply);
		if (save) {

			// 审批通过后处理逻辑
			if (tenantCreateApply.checkIsAuditPass()) {
				log.info("租户申请审批通过 applyId={}", tenantCreateApplyAuditCommand.getId());
				tenantCreateApply = onAuditPass(tenantCreateApply, tenantCreateApplyAuditCommand);
			}

			return SingleResponse.of(TenantCreateApplyAppStructMapping.instance.toTenantCreateApplyVO(tenantCreateApply));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 审核通过后将做如下处理：
	 * 1 创建或获取申请用户
	 * 2 添加租户
	 * 3 将用户绑定到租户下
	 * 4 添加租户下超级管理员角色
	 * 5 为租户分配应用
	 * 6 绑定用户在租户下的超级管理员角色
	 * 7 通知
	 *
	 * @param tenantCreateApply
	 * @param tenantCreateApplyAuditCommand
	 */
	private TenantCreateApply onAuditPass(TenantCreateApply tenantCreateApply, TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand) {

		// 获取申请数据
		TenantCreateApply tenantCreateApplyDb = tenantCreateApplyGateway.getById(tenantCreateApply.getId());
		Long applyUserId = tenantCreateApplyDb.getApplyUserId();

		// 如果添加用户，为用户设置密码，这里没必要先初始化随机密码，等真正使用时初始化
		String password = null;
		// 1 创建或获取申请用户
		if (applyUserId == null) {
			//	userId为空，代表可能需要添加用户，如果根据登录标识能够获取到用户就不用再添加用户了
			Assert.isTrue(StrUtil.isNotEmpty(tenantCreateApplyDb.getContactUserEmail()) || StrUtil.isNotEmpty(tenantCreateApplyDb.getContactUserPhone()), "联系人邮箱和电话必须填写一个，以用于匹配用户");
			if (StrUtil.isNotEmpty(tenantCreateApplyDb.getContactUserEmail())) {
				applyUserId = tenantUserUserGateway.getByUserIdentifier(tenantCreateApplyDb.getContactUserEmail());
			}
			if (applyUserId != null) {
				log.info("用户已存在，直接使用对应的id，email={}，userId={}", tenantCreateApplyDb.getContactUserEmail(), applyUserId);
			}
			// 还为空根据手机号获取
			if (applyUserId == null) {
				if (StrUtil.isNotEmpty(tenantCreateApplyDb.getContactUserPhone())) {
					applyUserId = tenantUserUserGateway.getByUserIdentifier(tenantCreateApplyDb.getContactUserPhone());
				}
			}
			if (applyUserId != null) {
				log.info("用户已存在，直接使用对应的id，mobile={}，userId={}", tenantCreateApplyDb.getContactUserPhone(), applyUserId);
			}
			// 还为空直接创建用户
			if (applyUserId == null) {
				log.info("开始创建用户，email={}，mobile={}", tenantCreateApplyDb.getContactUserEmail(), tenantCreateApplyDb.getContactUserPhone());

				//tenantDictGateway
				String account = null;
				Long accountTypeDictId = null;
				// 如果邮箱不为空，直接以邮箱为账号
				if (StrUtil.isNotEmpty(tenantCreateApplyDb.getContactUserEmail())) {
					account = tenantCreateApplyDb.getContactUserEmail();
					Long dictIdByGroupCodeAndItemValue = tenantDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), UserAccountType.front_email.itemValue());
					accountTypeDictId = dictIdByGroupCodeAndItemValue;
				}
				// 如果邮箱为空，以手机号为账号
				else if (StrUtil.isNotEmpty(tenantCreateApplyDb.getContactUserPhone())) {
					account = tenantCreateApplyDb.getContactUserPhone();
					Long dictIdByGroupCodeAndItemValue = tenantDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), UserAccountType.front_mobile.itemValue());
					accountTypeDictId = dictIdByGroupCodeAndItemValue;
				}
				String nickname = Optional.ofNullable(tenantCreateApplyDb.getContactUserName()).orElse("未填写");
				if (StrUtil.isEmpty(password)) {
					password = RandomUtil.randomString(16);
				}
				applyUserId = tenantUserUserGateway.createUser(nickname, nickname, account, accountTypeDictId, password, tenantCreateApplyUserAddScene);
				log.info("用户创建完成，userId={},password={}", applyUserId, password);

			}
		}
		// 2 添加租户
		TenantCreateCommand tenantCreateCommand = new TenantCreateCommand();
		tenantCreateCommand.setName(tenantCreateApplyDb.getName());
		tenantCreateCommand.setCode(IdUtil.fastSimpleUUID());
		tenantCreateCommand.setIsDisabled(false);
		tenantCreateCommand.setContactUserName(tenantCreateApplyDb.getContactUserName());
		tenantCreateCommand.setContactUserPhone(tenantCreateApplyDb.getContactUserPhone());
		tenantCreateCommand.setContactUserEmail(tenantCreateApplyDb.getContactUserEmail());
		tenantCreateCommand.setIsFormal(tenantCreateApplyDb.getIsFormal());
		tenantCreateCommand.setUserLimitCount(tenantCreateApplyDb.getUserLimitCount());
		tenantCreateCommand.setEffectiveAt(tenantCreateApplyDb.getEffectiveAt());
		tenantCreateCommand.setInvalidAt(tenantCreateApplyDb.getInvalidAt());
		tenantCreateCommand.setMasterUserId(applyUserId);

		SingleResponse<TenantVO> tenantSave = tenantCreateCommandExecutor.execute(tenantCreateCommand);
		if (tenantSave.isSuccess()) {
			log.info("添加租户成功 tenantName={}", tenantCreateCommand.getName());
			// 设置已申请的租户id
			tenantCreateApplyDb.changeAppliedTenantId(tenantSave.getData().getId());
			tenantCreateApplyGateway.save(tenantCreateApplyDb);


			// 3 将用户绑定到租户下
			TenantUserCreateCommand tenantUserCreateCommand = new TenantUserCreateCommand();
			tenantUserCreateCommand.setUserId(applyUserId);
			tenantUserCreateCommand.setIsExpired(false);
			tenantUserCreateCommand.setIsLeave(false);
			tenantUserCreateCommand.setTenantId(tenantSave.getData().getId());
			SingleResponse<TenantUserVO> tenantUserSave = tenantUserCreateCommandExecutor.execute(tenantUserCreateCommand);
			if (tenantUserSave.isSuccess()) {
				log.info("将用户绑定到租户下成功 userId={}", tenantUserCreateCommand.getUserId());

				// 4 添加租户下超级管理员角色
				Long roleId = tenantRoleGateway.createRole(tenantCreateApplyAuditCommand.getTenantSuperAdminRoleCode(), "超级管理员", false, tenantSave.getData().getId());
				log.info("添加租户下超级管理员角色成功 roleId={}", roleId);

				// 5 为租户分配应用及应用下的功能
				String extJson = tenantCreateApplyDb.getExtJson();
				if (StrUtil.isNotEmpty(extJson)) {
					log.info("开始为租户分配应用及应用下的功能");

					TenantCreateApplyExtJsonCommand tenantCreateApplyExtJsonCommand = JSONUtil.toBean(extJson, TenantCreateApplyExtJsonCommand.class);
					if (CollectionUtil.isNotEmpty(tenantCreateApplyExtJsonCommand.getFuncApplications())) {
						for (TenantCreateApplyExtJsonFuncApplicationCommand funcApplication : tenantCreateApplyExtJsonCommand.getFuncApplications()) {
							TenantFuncApplication tenantFuncApplication = TenantFuncApplication.create();
							tenantFuncApplication.simpleFill(funcApplication.getFuncApplicationId(), tenantSave.getData().getId());
							tenantFuncApplicationGateway.save(tenantFuncApplication);
							log.info("添加租户应用成功 funcApplicationId={}", tenantFuncApplication.getFuncApplicationId());

							//	保存完应用，保存应用下的功能
							List<Long> funcIds = funcApplication.getFuncIds();
							if (CollectionUtil.isNotEmpty(funcIds)) {
								for (Long funcId : funcIds) {
									TenantFunc tenantFunc = TenantFunc.create();
									tenantFunc.simpleFill(funcId, funcApplication.getFuncApplicationId(), tenantSave.getData().getId());
									tenantFuncGateway.save(tenantFunc);
									log.info("添加租户功能成功 funcApplicationId={},funcId={}", tenantFuncApplication.getFuncApplicationId(), funcId);

								}
							}
						}
					}
				}
				// 6 绑定用户在租户下的超级管理员角色
				tenantRoleGateway.createRoleUserRel(roleId, applyUserId, tenantSave.getData().getId());
				//	7 通知
				if (StrUtil.isNotEmpty(tenantCreateApplyDb.getContactUserEmail())) {
					TenantCreateApplyAuditPassDomainEvent.DataContent dataContent = TenantCreateApplyAuditPassDomainEvent.DataContent.create(
							// url为空请在模板中指定
							"",
							tenantCreateApplyDb.getContactUserEmail(),
							password,
							tenantCreateApplyDb.getContactUserPhone(),
							tenantCreateApplyDb.getIsFormal(),
							tenantCreateApplyDb.getInvalidAt()
					);

					TenantCreateApplyAuditPassDomainEvent event = new TenantCreateApplyAuditPassDomainEvent(dataContent);
					TemplatingDomainMessageEvent templatingDomainMessageEvent = event.toTemplatingDomainMessageEvent(applyUserId,tenantCreateApplyAuditCommand.getAuditUserId());
					tenantCreateApplyGateway.sendDomainEvent(templatingDomainMessageEvent);
				}

			}
		}
		return tenantCreateApplyDb;
	}

	/**
	 * 根据区域创建指令创建区域模型
	 *
	 * @param tenantCreateApplyUpdateCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyUpdateCommand(TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand) {
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyUpdateCommand(tenantCreateApply, tenantCreateApplyUpdateCommand);
		return tenantCreateApply;
	}

	/**
	 * 根据区域创建指令创建区域模型
	 *
	 * @param tenantCreateApplyAuditCommand
	 * @return
	 */
	private TenantCreateApply createByTenantCreateApplyAuditCommand(TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand) {
		TenantCreateApply tenantCreateApply = TenantCreateApply.create();
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.instance.fillTenantCreateApplyByTenantCreateApplyAuditCommand(tenantCreateApply, tenantCreateApplyAuditCommand);
		return tenantCreateApply;
	}

	@Mapper
	interface TenantCreateApplyUpdateCommandToTenantCreateApplyMapping {
		TenantCreateApplyUpdateCommandToTenantCreateApplyMapping instance = Mappers.getMapper(TenantCreateApplyUpdateCommandToTenantCreateApplyMapping.class);

		default TenantCreateApplyId map(Long id) {
			if (id == null) {
				return null;
			}
			return TenantCreateApplyId.of(id);
		}

		/**
		 * 同名属性会自动映射，包括枚举
		 *
		 * @param tenantCreateApply
		 * @param tenantCreateApplyUpdateCommand
		 */
		void fillTenantCreateApplyByTenantCreateApplyUpdateCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyUpdateCommand tenantCreateApplyUpdateCommand);

		void fillTenantCreateApplyByTenantCreateApplyAuditCommand(@MappingTarget TenantCreateApply tenantCreateApply, TenantCreateApplyAuditCommand tenantCreateApplyAuditCommand);
	}

	/**
	 * 注入使用set方法
	 *
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

	@Autowired
	public void setTenantUserUserGateway(TenantUserUserGateway tenantUserUserGateway) {
		this.tenantUserUserGateway = tenantUserUserGateway;
	}
}
