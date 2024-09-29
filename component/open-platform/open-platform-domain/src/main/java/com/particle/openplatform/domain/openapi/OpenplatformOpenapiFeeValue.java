package com.particle.openplatform.domain.openapi;

import com.particle.common.domain.ValueObjRoot;
import com.particle.openplatform.domain.enums.OpenPlatformDeduplicateType;
import com.particle.openplatform.domain.enums.OpenPlatformFeeType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 计费值对象
 * Created by yangwei
 * Created at 2024-09-28 12:40:53
 */
@Setter
@Getter
public class OpenplatformOpenapiFeeValue extends ValueObjRoot {


    /**
     * 单价，分/条
     */
    private Integer price;
    /**
     * 计费类型
     */
    private OpenPlatformFeeType openPlatformFeeType;
    /**
     * 去重方式
     */
    private OpenPlatformDeduplicateType openPlatformDeduplicateType;

    /**
     * 去重条数，如果去重表示每多少条算一条
     */
    private Integer deduplicateCount;


    /**
     * 是否按请求参数去重，1=按参数去重，0=按接口去重
     */
    private Boolean isDeduplicateByParameter;

    /**
     * 是否检查是否返回值，1=检查，如果没有返回值不计费，0=不检查，直接计费
     */
    private Boolean isCheckHasValue;

    /**
     * 是否检查处理时长，1=检查，0=不检查
     */
    private Boolean isCheckHandleDuration;

    /**
     * 处理时长，单位毫秒，如果检查处理时长，超过该时长不计费
     */
    private Integer handleDuration;
}
