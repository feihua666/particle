package com.particle.data.infrastructure.dynamictable.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 动态数据表格上传记录表
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Accessors(chain = true)
@Data
@TableName("component_data_dynamic_table_upload_record")
public class DynamicTableUploadRecordDO extends BaseDO {

    /**
    * 动态数据表格id
    */
    private Long dynamicTableId;

    /**
    * 上传文件名称
    */
    private String uploadFileName;

    /**
    * 上传文件地址
    */
    private String uploadFileUrl;

    /**
    * 上传字段数量
    */
    private Integer uploadTableFieldNum;

    /**
    * 上传数据数量
    */
    private Integer uploadTableDataNum;

	/**
	 * 是否发布，1=是，0=否
	 */
	private Boolean isPublic;


}