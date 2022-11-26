package com.particle.user.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Entity
public class User extends AggreateRoot {

	private UserId id;
    /**
     * 昵称，姓名，模糊查询
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
     * 用户编号，可以做为员工编号
     */
    private String serialNo;
    /**
     * 公司id，冗余字段，由dept_id对应公司派生
     */
    private Long compId;
    /**
     * 部门id
     */
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


	/**
	 * 创建用户领域模型对象
	 * @return 用户领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static User create(){
		return DomainFactory.create(User.class);
	}
}
