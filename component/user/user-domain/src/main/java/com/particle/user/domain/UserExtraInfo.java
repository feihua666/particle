package com.particle.user.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 用户扩展信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-08-30 23:39:50
 */
@Data
@Entity
public class UserExtraInfo extends AggreateRoot {

    private UserExtraInfoId id;

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



    /**
     * 创建用户扩展信息领域模型对象
     * @return 用户扩展信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static UserExtraInfo create(){
        return DomainFactory.create(UserExtraInfo.class);
    }
}
