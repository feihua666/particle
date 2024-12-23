package com.particle.openplatform.infrastructure.doc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 开放接口文档接口与目录关系表
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Data
@TableName("component_openplatform_doc_api_dir_rel")
public class OpenplatformDocApiDirRelDO extends BaseDO {

    /**
    * 开放接口文档接口id
    */
    private Long openplatformDocApiId;

    /**
    * 开放接口文档目录id
    */
    private Long openplatformDocDirId;


}
