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
import java.math.BigDecimal;
/**
 * <p>
 * 企业知识产权专利缴费信息表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_payment")
public class DataCompanyIprPatentPaymentDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 费用金额(元)
    */
    private BigDecimal feeAmount;
    
    /**
    * 费用种类,如：发明专利第13年年费、发明专利申请费、权利要求附加费、优先权要求费、宽限费、发明专利文印费、发明专利申请审查费、变更费、发明专利年费、发明专利登记印刷费、发明专利维持费
    */
    private String feeType;

    /**
    * 收据号
    */
    private String receiptNo;

    /**
    * 缴费人信息，如：xxxx公司
    */
    private String payer;

    /**
    * 处理状态,如：处理结束
    */
    private String handleStatus;

    /**
    * 缴费日期
    */
    private LocalDate payDate;

	/**
	 * 数据md5,fee_type + receipt_no + payer + handle_status + pay_date
	 */
	private String dataMd5;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}