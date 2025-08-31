package com.particle.user.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 用户扩展信息表
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Accessors(chain = true)
@Data
@TableName("component_user_extra_info")
public class UserExtraInfoDO extends BaseDO {

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 单位名称
    */
    private String orgName;

    /**
    * 职称
    */
    private String jobTitle;

    /**
    * 个人简介
    */
    private String profile;

    /**
    * 额外自定义非查询信息
    */
    private String extraInfoJson;


}
