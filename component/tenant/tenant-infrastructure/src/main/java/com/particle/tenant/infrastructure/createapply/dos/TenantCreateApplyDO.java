package com.particle.tenant.infrastructure.createapply.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
/**
 * <p>
 * 租户创建申请表
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Data
@TableName("component_tenant_create_apply")
public class TenantCreateApplyDO extends BaseDO {

    /**
    * 租户名称,模糊查询
    */
    private String name;

    /**
    * 姓名
    */
    private String userName;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 手机号
    */
    private String mobile;

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
	 * 过期时间，从什么时候失效
	 */
	private LocalDateTime expireAt;

	/**
	 * 额外申请项json，如：应用和功能
	 */
	private String extJson;

    /**
    * 描述
    */
    private String remark;


}