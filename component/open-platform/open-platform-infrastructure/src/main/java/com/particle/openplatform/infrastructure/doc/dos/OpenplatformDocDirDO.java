package com.particle.openplatform.infrastructure.doc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;
/**
 * <p>
 * 开放接口目录表
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Data
@TableName("component_openplatform_doc_dir")
public class OpenplatformDocDirDO extends BaseTreeDO {

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


}
