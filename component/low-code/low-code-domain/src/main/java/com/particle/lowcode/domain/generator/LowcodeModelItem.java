package com.particle.lowcode.domain.generator;

import java.time.LocalDateTime;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 低代码模型项目 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@Entity
public class LowcodeModelItem extends AggreateRoot {

	private LowcodeModelItemId id;
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 实体属性名称
     */
    private String propertyName;
    /**
     * 数据库类型
     */
    private String jdbcType;
    /**
     * 实体属性类型
     */
    private String propertyType;
    /**
     * 字段注释,完整的注释
     */
    private String commentFull;
    /**
     * 字段注释,简洁注释，提取列注释的第一个逗号前面的，常用于swagger
     */
    private String commentSimple;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 是否唯一，一般有唯一索引就是唯一，不算主键
     */
    private Boolean isUnique;
    /**
     * 是否必填
     */
    private Boolean isRequired;
    /**
     * 是否主键
     */
    private Boolean isKey;
    /**
     * 是否主键自增
     */
    private Boolean isKeyIdentity;
    /**
     * 是否为关键字
     */
    private Boolean isKeyWord;
    /**
     * 字段长度
     */
    private Integer columnLength;
    /**
     * 字段小数位长度
     */
    private Integer fractionLength;
    /**
     * 字段是否外键
     */
    private Boolean isForeignKey;
    /**
     * 模型id
     */
    private Long lowcodeModelId;
    /**
     * 描述,注意事项等
     */
    private String remark;


    /**
     * 根据全注释截取简洁注释
     */
    public void fillCommentSimpleByCommentFull(){
        if (commentFull == null) {
            return;
        }
        commentSimple = commentFull.split(",")[0];
        commentSimple = commentSimple.split("，")[0];
    }
	/**
	 * 创建低代码模型项目领域模型对象
	 * @return 低代码模型项目领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static LowcodeModelItem create(){
		return DomainFactory.create(LowcodeModelItem.class);
	}
}
