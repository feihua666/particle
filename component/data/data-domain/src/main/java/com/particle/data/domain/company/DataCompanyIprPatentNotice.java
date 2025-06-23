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
 * 企业知识产权专利通知书信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Data
@Entity
public class DataCompanyIprPatentNotice extends AggreateRoot {

    private DataCompanyIprPatentNoticeId id;

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 通知发文日期
    */
    private LocalDate publicDate;

    /**
    * 通知挂号
    */
    private String mailNo;

    /**
    * 通知收件人姓名，如：中国国际贸易促进委员会专利商标事务所
    */
    private String receiverName;

    /**
    * 通知书类型,如：20112、29503
    */
    private String noticeType;

    /**
    * 通知书类型说明,如：发明专利申请公布及进入实质审查阶段通知书、国际申请进入国家阶段初步审查合格通知书、复审请求补正通知书
    */
    private String noticeTypeDescription;

	/**
	 * 数据md5,public_date + mail_no + receiver_name + notice_type + notice_type_description
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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprPatentNoticeDataMd5(publicDate,mailNo,receiverName,noticeType,noticeTypeDescription);
    }

    /**
     * 创建企业知识产权专利通知书信息领域模型对象
     * @return 企业知识产权专利通知书信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentNotice create(){
        return DomainFactory.create(DataCompanyIprPatentNotice.class);
    }
}
