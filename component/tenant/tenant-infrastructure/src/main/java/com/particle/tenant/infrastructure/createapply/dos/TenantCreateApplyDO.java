package com.particle.tenant.infrastructure.createapply.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
    * 描述
    */
    private String remark;


}
