package com.particle.openplatform.infrastructure.doc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 开放接口文档示例代码表
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Data
@TableName("component_openplatform_doc_api_doc_example_code")
public class OpenplatformDocApiDocExampleCodeDO extends BaseDO {

    /**
    * 开发语言，字典id
    */
    private Long langDictId;

    /**
    * 示例代码片段
    */
    private String exampleCode;

    /**
    * 示例代码下载地址
    */
    private String exampleDownloadUrl;

    /**
    * 开放接口文档接口id
    */
    private Long openplatformDocApiId;

    /**
    * 开放接口文档id
    */
    private Long openplatformDocApiDocId;

	/**
	 * 排序,默认按该字段升序排序
	 */
	private Integer seq;

    /**
    * 描述
    */
    private String remark;


}
