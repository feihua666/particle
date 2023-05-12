package com.particle.tenant.domain.createapply;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.tenant.domain.TenantCreateApplyAuditStatus;
import com.particle.tenant.domain.gateway.TenantDictGateway;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * <p>
 * 租户创建申请 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Data
@Entity
public class TenantCreateApply extends AggreateRoot {

    private TenantDictGateway tenantDictGateway;

    private TenantCreateApplyId id;

    /**
    * 租户名称,模糊查询
    */
    private String name;

    /**
    * 联系人姓名
    */
    private String contactUserName;

    /**
    * 联系人邮箱
    */
    private String contactUserEmail;

    /**
    * 联系人电话
    */
    private String contactUserPhone;

    /**
    * 租户类型字典id
    */
    private Long tenantTypeDictId;

    /**
    * 申请用户
    */
    private Long applyUserId;

    /**
    * 审核状态，字典id
    */
    private Long auditStatusDictId;

    /**
    * 审核意见
    */
    private String auditStatusComment;

    /**
    * 审核用户id
    */
    private Long auditUserId;

    /**
    * 审核通过后创建的租户id
    */
    private Long appliedTenantId;

	/**
	 * 是否正式，1=正式，0=试用
	 */
	private Boolean isFormal;

	/**
	 * 用户数量限制
	 */
	private Integer userLimitCount;

	/**
	 * 生效日期，从什么时候开始生效
	 */
	private LocalDateTime effectiveAt;

	/**
	 * 申请天数
	 */
	private Integer effectiveDays;

	/**
	 * 失效日期，从什么时候失效
	 */
	private LocalDateTime invalidAt;

	/**
	 * 额外申请项json，如：应用和功能
	 */
	private String extJson;

    /**
    * 描述
    */
    private String remark;

	public void changeExtJson(String extJson) {
		this.extJson = extJson;
	}

	public void changeEffectiveAtNowIfNull() {
		if (effectiveAt == null) {
			effectiveAt = LocalDateTime.now();
		}
	}
	/**
	 * 计算时间或天数
	 */
	public void calculateDays() {
		if (effectiveAt == null) {
			return;
		}
		if (invalidAt != null) {
			effectiveDays = Long.valueOf( LocalDateTimeUtil.between(effectiveAt, invalidAt).toDays()).intValue();
			return;
		}
		if (effectiveDays != null && effectiveDays != 0) {
			invalidAt = effectiveAt.plusDays(effectiveDays);
		}
	}
    /**
     * 设置审核状态为待审核
     * 添加时默认审核状态
     */
    public void chanageAuditStatusToUnAudit(){
        Long id = tenantDictGateway.getDictIdByGroupCodeAndItemValue(TenantCreateApplyAuditStatus.Group.tenant_create_apply_audit_status.groupCode(), TenantCreateApplyAuditStatus.un_audit.itemValue());
        this.auditStatusDictId = id;
    }

    /**
     * 是否审核通过
     * @return
     */
    public boolean checkIsAuditPass(){
        String dictValueById = tenantDictGateway.getDictValueById(auditStatusDictId);
        return TenantCreateApplyAuditStatus.audit_pass.itemValue().equals(dictValueById);
    }

    /**
     * 修改已申请的租户id，这一般是审批通过是调用
     * @param appliedTenantId
     */
    public void changeAppliedTenantId(Long appliedTenantId) {
        this.appliedTenantId = appliedTenantId;
    }

    /**
     * 创建租户创建申请领域模型对象
     * @return 租户创建申请领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static TenantCreateApply create(){
        return DomainFactory.create(TenantCreateApply.class);
    }

    @Autowired
    public void setTenantDictGateway(TenantDictGateway tenantDictGateway) {
        this.tenantDictGateway = tenantDictGateway;
    }
}