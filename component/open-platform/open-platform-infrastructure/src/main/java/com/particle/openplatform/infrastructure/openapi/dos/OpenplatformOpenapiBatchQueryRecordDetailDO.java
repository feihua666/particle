package com.particle.openplatform.infrastructure.openapi.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
/**
 * <p>
 * 开放接口批量查询记录明细表
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Accessors(chain = true)
@Data
@TableName("component_openplatform_openapi_batch_query_record_detail")
public class OpenplatformOpenapiBatchQueryRecordDetailDO extends BaseDO {

    /**
    * 开放接口批量查询记录id
    */
    private Long openplatformOpenapiBatchQueryRecordId;

    /**
    * 执行状态，字典id
    */
    private Long executeStatusDictId;

    /**
    * 是否成功，查询后有值
    */
    private Boolean isSuccess;

	/**
	 * 请求流水号，查询后有值
	 */
	private String requestNonce;

    /**
    * 查询时间，查询后有值
    */
    private LocalDateTime queryAt;

    /**
    * 追踪id，查询后有值
    */
    private String traceId;

    /**
    * 追踪分支id，查询后有值
    */
    private String spanId;

    /**
    * 请求参数，解析文件后有值
    */
    private String requestParam;

    /**
    * 请求查询字符串，解析文件后有值
    */
    private String queryString;

    /**
    * 响应结果
    */
    private String responseResult;


}
