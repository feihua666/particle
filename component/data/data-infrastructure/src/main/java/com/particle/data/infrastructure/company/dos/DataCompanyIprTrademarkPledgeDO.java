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
 * 企业知识产权商标质押信息表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_trademark_pledge")
public class DataCompanyIprTrademarkPledgeDO extends BaseDO {

    /**
    * 企业知识产权商标表id
    */
    private Long companyIprTrademarkId;

    /**
    * 出质人，如：xxxx公司
    */
    private String pledgor;

    /**
    * 质权人，如：xxxx公司
    */
    private String pledgee;

    /**
    * 质权登记起始日期
    */
    private LocalDate pledgeRegStartDate;
    
    /**
    * 质权登记截止日期
    */
    private LocalDate pledgeRegEndDate;
    
    /**
    * 质权公告页号
    */
    private String pledgePublicPageNo;
    
    /**
    * 数据md5,pledgor + pledgee + pledge_public_page_no
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
