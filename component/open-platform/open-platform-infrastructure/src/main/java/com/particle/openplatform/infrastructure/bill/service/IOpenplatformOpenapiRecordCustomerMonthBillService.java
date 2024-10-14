package com.particle.openplatform.infrastructure.bill.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordCustomerMonthBillDO;
import com.particle.global.mybatis.plus.crud.IBaseService;

/**
 * <p>
 * 开放平台客户月账单 服务类
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
public interface IOpenplatformOpenapiRecordCustomerMonthBillService extends IBaseService<OpenplatformOpenapiRecordCustomerMonthBillDO> {

    /**
     * 获取某一月的数据
     * @param customerId
     * @param year
     * @param month
     * @return
     */
    default public OpenplatformOpenapiRecordCustomerMonthBillDO getByCustomerIdAndYearAndMonth(Long customerId, Integer year, Integer month){
        LambdaQueryWrapper<OpenplatformOpenapiRecordCustomerMonthBillDO> lambdaQueryWrapper = Wrappers.<OpenplatformOpenapiRecordCustomerMonthBillDO>lambdaQuery()
                .eq(OpenplatformOpenapiRecordCustomerMonthBillDO::getCustomerId, customerId)
                .eq(OpenplatformOpenapiRecordCustomerMonthBillDO::getYear, year)
                .eq(OpenplatformOpenapiRecordCustomerMonthBillDO::getMonth, month);
        return getOne(lambdaQueryWrapper);
    }














}
