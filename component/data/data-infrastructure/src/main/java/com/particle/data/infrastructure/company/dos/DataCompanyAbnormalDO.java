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
 * 企业经营异常表
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_abnormal")
public class DataCompanyAbnormalDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

    /**
    * 列入决定书文号
    */
    private String putNo;

    /**
    * 列入异常名录原因
    */
    private String putReason;

    /**
    * 列入异常名录日期
    */
    private LocalDate putDate;
    
    /**
    * 作出列入决定机关公司id
    */
    private Long putInstituteCompanyId;

    /**
    * 作出列入决定机关名称，冗余公司名称
    */
    private String putInstituteName;

    /**
    * 移出决定书文号
    */
    private String removeNo;

    /**
    * 移出异常名录原因
    */
    private String removeReason;

    /**
    * 移出异常名录日期
    */
    private LocalDate removeDate;
    
    /**
    * 作出移出决定机关公司id
    */
    private Long removeInstituteCompanyId;

    /**
    * 作出移出决定机关名称，冗余公司名称
    */
    private String removeInstituteName;

    /**
    * 数据md5,put_no + put_reason + put_date + put_institute_name
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}