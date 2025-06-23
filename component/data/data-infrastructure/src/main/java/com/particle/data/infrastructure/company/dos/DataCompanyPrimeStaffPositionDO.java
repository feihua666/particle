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
 * 企业主要人员职位表
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_prime_staff_position")
public class DataCompanyPrimeStaffPositionDO extends BaseDO {

    /**
    * 企业主要人员表ID
    */
    private Long companyPrimeStaffId;

    /**
    * 职位名称
    */
    private String positionName;

    /**
    * 职位，字典id
    */
    private Long positionDictId;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
