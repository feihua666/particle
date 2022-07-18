package com.particle.area.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@Data
@TableName("component_area")
public class AreaDO extends BaseTreeDO {
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

}
