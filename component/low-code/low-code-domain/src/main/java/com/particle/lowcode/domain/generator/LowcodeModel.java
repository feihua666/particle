package com.particle.lowcode.domain.generator;

import java.time.LocalDateTime;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 低代码模型 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@Entity
public class LowcodeModel extends AggreateRoot {

	private LowcodeModelId id;
    /**
     * 名称
     */
    private String name;
	/**
	 * 名称
	 */
	private String nameEn;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 模型类型，rel,tree,normal
     */
    private String tableType;
	/**
     * 描述,注意事项等
     */
    private String remark;


	/**
	 * 创建低代码模型领域模型对象
	 * @return 低代码模型领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static LowcodeModel create(){
		return DomainFactory.create(LowcodeModel.class);
	}
}
