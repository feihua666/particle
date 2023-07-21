package com.particle.tenant.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 租户用户 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Data
@Entity
public class TenantUser extends AggreateRoot {

    private TenantUserId id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 真实姓名
    */
    private String name;

    /**
    * 是否过期，过期后该密码不能登录
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
     * 租户id
     */
    private Long tenantId;


    /**
     * 修改加入时间为当前时间
     */
    public void changeJoinAtToCurrent() {
        this.joinAt = LocalDateTime.now();
    }
    /**
     * 修改是否离职为false
     * 主要为添加是设置默认值
     */
    public void changeIsLeaveToFalseIfNull() {
        if (this.isLeave == null) {
            this.isLeave = false;
            this.leaveReason = null;
        }
    }

    /**
     * 如果已离职且离职时间为空，设置离职时间为当前时间
     */
    public void changeLeaveAtToCurrentIfNullAndHasLeft(){
        if (this.isLeave != null && this.isLeave && this.leaveAt == null) {
            this.leaveAt = LocalDateTime.now();
        }
    }

    /**
     * 创建租户用户领域模型对象
     * @return 租户用户领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static TenantUser create(){
        return DomainFactory.create(TenantUser.class);
    }
}