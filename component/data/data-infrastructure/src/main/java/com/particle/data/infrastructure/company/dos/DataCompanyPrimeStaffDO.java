package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业主要人员表
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_prime_staff")
public class DataCompanyPrimeStaffDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 姓名
    */
    private String staffName;

    /**
    * 个人表ID
    */
    private Long staffCompanyPersonId;

    /**
    * 职位名称
    */
    private String positionNames;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
