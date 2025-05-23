package com.particle.tenant.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 租户用户表
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Data
@TableName("component_tenant_user")
public class TenantUserDO extends BaseDO {

    public static final String COLUMN_USER_ID = "user_id";


    /**
    * 用户id
    */
    private Long userId;

    /**
    * 真实姓名
    */
    private String name;

    /**
    * 是否过期，过期后获取不到该租户
    */
    private Boolean isExpired;

    /**
    * 过期原因
    */
    private String expiredReason;

    /**
    * 到期时间，为空永不到期
    */
    private LocalDateTime expireAt;

	/**
	 * 生效日期，从什么时候开始生效
	 */
	private LocalDateTime effectiveAt;

	/**
	 * 生效日期，触发方式，一般为首次登录触发
	 */
	private Long effectiveAtTriggerDictId;

	/**
	 * 有效天数,0或空为不限制
	 */
	private Integer effectiveDays;


    /**
     * 是否离职或退出
     */
    private Boolean isLeave;

    /**
     * 离职或退出原因
     */
    private String leaveReason;

    /**
     * 离职或退出时间
     */
    private LocalDateTime leaveAt;
    /**
     * 用户加入时间
     */
    private LocalDateTime joinAt;

	/**
	 * 是否正式，1=正式，0=试用
	 */
	private Boolean isFormal;

	/**
	 * 备注
	 */
	private String remark;


}
