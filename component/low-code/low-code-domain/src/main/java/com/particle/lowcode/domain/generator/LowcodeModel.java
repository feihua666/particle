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
	 * 实体名称，首字母大写，符合java类名规范
	 */
	private String nameEnEntity;

	/**
	 * 实体变量名称，name_en_entity的首字母小写
	 */
	private String nameEnEntityVar;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 模型表类型字典id，rel,tree,normal
     */
    private Long tableTypeDictId;
	/**
	 * 请求路径，不要以斜杠开关如：user
	 */
	private String requestPath;
	/**
	 * 建表语句
	 */
	private String tableCreateSql;
	/**
     * 描述,注意事项等
     */
    private String remark;


    public void changeTableCreateSql( String tableCreateSql){
		this.tableCreateSql = tableCreateSql.replace(" CHARACTER SET utf8 COLLATE utf8_bin", "").replace("CHARSET=utf8mb3","CHARSET=utf8");
	}
	/**
	 * 创建低代码模型领域模型对象
	 * @return 低代码模型领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static LowcodeModel create(){
		return DomainFactory.create(LowcodeModel.class);
	}
}
