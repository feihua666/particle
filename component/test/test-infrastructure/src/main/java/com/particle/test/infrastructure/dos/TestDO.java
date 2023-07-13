package com.particle.test.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;

/**
 * <p>
 * 测试表
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Data
@TableName("component_test")
public class TestDO extends BaseDO {
    /**
    * 昵称，姓名,模糊查询
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
