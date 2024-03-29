package com.particle.area.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.tool.pinyin.Pinyin;
import com.particle.global.tool.pinyin.PinyinTool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * <p>
 * 区域 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@Slf4j
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
	 * 父级id
	 */
	private Long parentId;


	/**
	 * 填充拼音
	 */
	public void fillSpell(){
		try {
			Pinyin pinyin = PinyinTool.getPinyin(name);
			this.spell = pinyin.getFull();
			this.spellFirst = pinyin.getFirst();
			this.spellSimple = pinyin.getSimple();
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			log.error("获取拼音失败",e);
		}
	}

	/**
	 * 创建区域领域模型对象
	 * @return 区域领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static Area create(){
		return DomainFactory.create(Area.class);
	}
}
