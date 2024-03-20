package com.particle.openplatform.domain.doc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放接口目录 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Data
@Entity
public class OpenplatformDocDir extends AggreateRoot {

    private OpenplatformDocDirId id;

    /**
    * 名称
    */
    private String name;

    /**
    * 简称
    */
    private String nameSimple;

    /**
    * 开放接口文档目录名称id
    */
    private Long openplatformDocDirNameId;

    /**
    * 备注
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建开放接口目录领域模型对象
     * @return 开放接口目录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformDocDir create(){
        return DomainFactory.create(OpenplatformDocDir.class);
    }
}
