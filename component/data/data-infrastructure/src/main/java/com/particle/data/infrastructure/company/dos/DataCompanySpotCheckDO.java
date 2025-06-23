package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业抽查检查表
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_spot_check")
public class DataCompanySpotCheckDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 检查实施机关
    */
    private String checkInstitute;

    /**
    * 检查实施机关公司id
    */
    private Long checkInstituteCompanyId;

    /**
    * 检查类型，如：检查
    */
    private String checkTypeName;

    /**
    * 检查日期
    */
    private LocalDate checkDate;
    
    /**
    * 检查结果
    */
    private String checkResult;

    /**
    * 数据md5,check_institute + check_type_name + check_date + check_result
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
