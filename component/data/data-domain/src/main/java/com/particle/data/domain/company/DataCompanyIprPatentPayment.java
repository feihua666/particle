package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业知识产权专利缴费信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Data
@Entity
public class DataCompanyIprPatentPayment extends AggreateRoot {

    private DataCompanyIprPatentPaymentId id;

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

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }

    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanyIprPatentPaymentDataMd5(feeType,receiptNo,payer,handleStatus,payDate);
    }

    /**
     * 创建企业知识产权专利缴费信息领域模型对象
     * @return 企业知识产权专利缴费信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentPayment create(){
        return DomainFactory.create(DataCompanyIprPatentPayment.class);
    }
}
