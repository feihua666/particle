package com.particle.test.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 简单用户 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Data
@Entity
public class UserSimple extends AggreateRoot {

	private UserSimpleId id;
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


	/**
	 * 创建简单用户领域模型对象
	 * @return 简单用户领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static UserSimple create(){
		return DomainFactory.create(UserSimple.class);
	}
}
