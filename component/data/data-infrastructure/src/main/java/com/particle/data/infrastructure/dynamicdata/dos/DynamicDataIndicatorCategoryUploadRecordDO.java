package com.particle.data.infrastructure.dynamicdata.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 动态数据指标分类上传记录表
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Accessors(chain = true)
@Data
@TableName("component_data_dynamic_data_indicator_category_upload_record")
public class DynamicDataIndicatorCategoryUploadRecordDO extends BaseDO {

    /**
    * 动态数据指标分类id
    */
    private Long dynamicDataIndicatorCategoryId;

    /**
    * 上传文件名称
    */
    private String uploadFileName;

    /**
    * 上传文件地址
    */
    private String uploadFileUrl;

    /**
    * 上传指标数量
    */
    private Integer uploadIndicatorNum;

    /**
    * 上传数据数量
    */
    private Integer uploadIndicatorDataNum;

	/**
	 * 是否发布，1=是，0=否
	 */
	private Boolean isPublic;


}