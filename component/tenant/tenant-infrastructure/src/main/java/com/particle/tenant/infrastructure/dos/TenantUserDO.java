package com.particle.tenant.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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


}
