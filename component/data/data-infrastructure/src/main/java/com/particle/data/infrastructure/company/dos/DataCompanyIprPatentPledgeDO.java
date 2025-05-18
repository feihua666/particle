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
 * 企业知识产权专利质押信息表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_pledge")
public class DataCompanyIprPatentPledgeDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 质押登记号
    */
    private String pledgeNo;

    /**
    * 质押保全类型，如：1;1;1、1
    */
    private String pledgePreserveType;

    /**
    * 质押保全权力类型，如：2;2;2;2;2;2、2
    */
    private String pledgePreserveRightType;

    /**
    * 生效日期
    */
    private LocalDate effectiveDate;
    
    /**
    * 变更日期
    */
    private LocalDate changeDate;
    
    /**
    * 出质人，如：xxxx公司
    */
    private String pledgor;

    /**
    * 质权人，如：xxxx公司
    */
    private String pledgee;

    /**
    * 解除日期
    */
    private LocalDate rescissionDate;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
