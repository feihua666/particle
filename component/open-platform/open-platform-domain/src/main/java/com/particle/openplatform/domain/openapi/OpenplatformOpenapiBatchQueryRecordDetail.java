package com.particle.openplatform.domain.openapi;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 开放接口批量查询记录明细 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Data
@Entity
public class OpenplatformOpenapiBatchQueryRecordDetail extends AggreateRoot {

    private OpenplatformOpenapiBatchQueryRecordDetailId id;

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



    /**
     * 创建开放接口批量查询记录明细领域模型对象
     * @return 开放接口批量查询记录明细领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformOpenapiBatchQueryRecordDetail create(){
        return DomainFactory.create(OpenplatformOpenapiBatchQueryRecordDetail.class);
    }
}
