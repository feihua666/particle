package com.particle.openplatform.infrastructure.doc.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 开放接口目录名称表
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Data
@TableName("component_openplatform_doc_dir_name")
public class OpenplatformDocDirNameDO extends BaseDO {

	/**
	 * 编码，唯一
	 */
	private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 描述
    */
    private String remark;


}
