package com.particle.test.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 简单用户表
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Data
@TableName("component_user_simple")
public class UserSimpleDO extends BaseDO {
    /**
    * 昵称，姓名,模糊查询
    */
    private String nickname;
    /**
    * 性别，字典id
    */
    private Long genderDictId;
    /**
    * 头像，图片绝对路径
    */
    private String avatar;
    /**
    * 锁定状态，0=未锁定；1=锁定
    */
    private Boolean isLock;
    /**
    * 锁定原因
    */
    private String lockReason;
    /**
    * 分组标识
    */
    private String groupFlag;
    /**
    * 用户来源，字典id
    */
    private Long sourceFromDictId;

}
