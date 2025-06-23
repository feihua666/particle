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
 * 企业知识产权出质表
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_pledge")
public class DataCompanyIprPledgeDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 知识产权登记证号
    */
    private String regNo;

    /**
    * 知识产权名称
    */
    private String name;

    /**
    * 知识产权种类
    */
    private Long typeName;

    /**
    * 出质人名称
    */
    private String pledgor;

    /**
    * 是否出质人为自然人，1=自然人，0=非自然人
    */
    private Boolean isPledgorNaturalPerson;

    /**
    * 出质人公司id，is_pledgor_natural_person = 0 时有值
    */
    private Long pledgorCompanyId;

    /**
    * 出质人个人id，is_pledgor_natural_person = 1 时有值
    */
    private Long pledgorCompanyPersonId;

    /**
    * 质权人名称
    */
    private String pledgee;

    /**
    * 是否质权人为自然人，1=自然人，0=非自然人
    */
    private Boolean isPledgeeNaturalPerson;

    /**
    * 质权人公司id，is_pledgee_natural_person = 0 时有值
    */
    private Long pledgeeCompanyId;

    /**
    * 质权人个人id，is_pledgee_natural_person = 1 时有值
    */
    private Long pledgeeCompanyPersonId;

    /**
    * 质权登记期限自
    */
    private LocalDate pledgeFromDate;
    
    /**
    * 质权登记期限至
    */
    private LocalDate pledgeToDate;
    
    /**
    * 状态,如：有效
    */
    private String statusName;

    /**
    * 公示日期
    */
    private LocalDate publishDate;
    
    /**
    * 注销日期
    */
    private LocalDate cancelDate;
    
    /**
    * 注销原因
    */
    private String cancelReason;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
