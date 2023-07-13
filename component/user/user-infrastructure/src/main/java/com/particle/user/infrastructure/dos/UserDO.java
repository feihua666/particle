package com.particle.user.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@TableName("component_user")
public class UserDO extends BaseDO {

    /**
     * 姓名，真实姓名
     */
    private String name;
    /**
    * 昵称，模糊查询
    */
    private String nickname;
    /**
    * 性别，字典id
    */
    private Long genderDictId;
    /**
    * 头像，建议图片相对路径
    */
    private String avatar;
    /**
    * 用户编号，可以做为员工编号
    */
    private String serialNo;
    /**
    * 公司id，冗余字段，由dept_id对应公司派生
     * 使用部门用户关系表
    */
    @TableField(exist = false)
    private Long compId;
    /**
    * 部门id
     * 使用部门用户关系表
    */
    @TableField(exist = false)
    private Long deptId;
    /**
    * 是否虚拟用户，虚拟用户代表不是一个真正存在的用户
    */
    private Boolean isVirtual;
    /**
    * 锁定状态，0=未锁定；1=锁定
    */
    private Boolean isLock;
    /**
    * 锁定原因
    */
    private String lockReason;
    /**
    * 用户分类字典，标识是哪一类用户，比如后台用户等
    */
    private Long categoryDictId;
    /**
    * 分组标识
    */
    private String groupFlag;
    /**
    * 用户来源，字典id
    */
    private Long sourceFromDictId;
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

}
