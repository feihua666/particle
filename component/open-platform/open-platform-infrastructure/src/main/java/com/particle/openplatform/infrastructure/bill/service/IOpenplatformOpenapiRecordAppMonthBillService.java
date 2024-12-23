package com.particle.openplatform.infrastructure.bill.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppMonthBillDO;

/**
 * <p>
 * 开放平台应用月账单 服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
public interface IOpenplatformOpenapiRecordAppMonthBillService extends IBaseService<OpenplatformOpenapiRecordAppMonthBillDO> {

    /**
     * 获取某一月的数据
     * @param openplatformAppId
     * @param year
     * @param month
     * @return
     */
    default public OpenplatformOpenapiRecordAppMonthBillDO getByopenplatformAppIdAndYearAndMonth(Long openplatformAppId, Integer year, Integer month){
        LambdaQueryWrapper<OpenplatformOpenapiRecordAppMonthBillDO> lambdaQueryWrapper = Wrappers.<OpenplatformOpenapiRecordAppMonthBillDO>lambdaQuery()
                .eq(OpenplatformOpenapiRecordAppMonthBillDO::getOpenplatformAppId, openplatformAppId)
                .eq(OpenplatformOpenapiRecordAppMonthBillDO::getYear, year)
                .eq(OpenplatformOpenapiRecordAppMonthBillDO::getMonth, month);
        return getOne(lambdaQueryWrapper);
    }














}
