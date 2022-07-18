package com.particle.area.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 区域 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Data
@Entity
public class Area extends AggreateRoot {

	private AreaId id;
    /**
     * 编码，唯一,模糊查询
     */
    private String code;
    /**
     * 区域名称,模糊查询
     */
    private String name;
    /**
     * 区域名称,模糊查询
     */
    private String nameSimple;
    /**
     * 第一个字的首字母
     */
    private String spellFirst;
    /**
     * 每个字的首字母
     */
    private String spellSimple;
    /**
     * 全拼
     */
    private String spell;
    /**
     * 类型，字典id
     */
    private Long typeDictId;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序,默认按该字段升序排序
     */
    private Integer seq;


	/**
	 * 创建区域领域模型对象
	 * @return 区域领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static Area create(){
		return DomainFactory.create(Area.class);
	}
}
