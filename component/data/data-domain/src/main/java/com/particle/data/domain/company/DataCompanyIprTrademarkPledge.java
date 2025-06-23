package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标质押信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Data
@Entity
public class DataCompanyIprTrademarkPledge extends AggreateRoot {

    private DataCompanyIprTrademarkPledgeId id;

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

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }

    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkPledgeDataMd5(pledgor,pledgee,pledgePublicPageNo);
    }


    /**
     * 创建企业知识产权商标质押信息领域模型对象
     * @return 企业知识产权商标质押信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprTrademarkPledge create(){
        return DomainFactory.create(DataCompanyIprTrademarkPledge.class);
    }
}
