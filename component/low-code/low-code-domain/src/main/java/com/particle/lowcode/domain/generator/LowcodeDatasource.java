package com.particle.lowcode.domain.generator;

import java.time.LocalDateTime;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 低代码数据源 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Data
@Entity
public class LowcodeDatasource extends AggreateRoot {

	private LowcodeDatasourceId id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 驱动类名
     */
    private String driverClassName;
    /**
     * 地址
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 描述,注意事项等
     */
    private String remark;


	/**
	 * 创建低代码数据源领域模型对象
	 * @return 低代码数据源领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static LowcodeDatasource create(){
		return DomainFactory.create(LowcodeDatasource.class);
	}
}
