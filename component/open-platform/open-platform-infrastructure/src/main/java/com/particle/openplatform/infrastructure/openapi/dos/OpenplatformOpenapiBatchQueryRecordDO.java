package com.particle.openplatform.infrastructure.openapi.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
/**
 * <p>
 * 开放接口批量查询记录表
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Accessors(chain = true)
@Data
@TableName("component_openplatform_openapi_batch_query_record")
public class OpenplatformOpenapiBatchQueryRecordDO extends BaseDO {

    /**
    * 开放平台应用id
    */
    private Long openplatformAppId;

    /**
    * 开放接口id，这里只存储接口，不存储分组
    */
    private Long openplatformOpenapiId;

    /**
    * 客户id
    */
    private Long customerId;

    /**
    * 执行状态，字典id
    */
    private Long executeStatusDictId;

    /**
    * 成功条数
    */
    private Integer successCount;

    /**
    * 失败条数
    */
    private Integer failCount;

    /**
    * 总条数
    */
    private Integer totalCount;

    /**
    * 用户id,谁操作的
    */
    private Long userId;

    /**
    * 查询时间
    */
    private LocalDateTime queryAt;

    /**
    * 追踪id
    */
    private String traceId;

	/**
	 * 上传文件名
	 */
	private String uploadFileName;

	/**
	 * 导出的文件地址
	 */
	private String exportFileUrl;

    /**
    * 描述
    */
    private String remark;


}
