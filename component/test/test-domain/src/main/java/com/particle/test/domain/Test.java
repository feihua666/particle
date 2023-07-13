package com.particle.test.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 测试 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Data
@Entity
public class Test extends AggreateRoot {

	private TestId id;
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


	/**
	 * 创建测试领域模型对象
	 * @return 测试领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static Test create(){
		return DomainFactory.create(Test.class);
	}
}
