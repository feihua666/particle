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
 * 企业送达公告 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Data
@Entity
public class DataCompanyDeliveryAnnouncement extends AggreateRoot {

    private DataCompanyDeliveryAnnouncementId id;

    /**
    * 案号
    */
    private String caseNo;

    /**
    * 案由
    */
    private String caseReason;

    /**
    * 公告标题
    */
    private String title;

    /**
    * 法院名称
    */
    private String courtName;

    /**
    * 发布日期
    */
    private LocalDate publishDate;

	/**
	 * 数据md5,case_no + case_reason + title + court_name + publish_date
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
        this.dataMd5 = SomeMd5Tool.dataCompanyDeliveryAnnouncementDataMd5(caseNo,caseReason,title,courtName,publishDate);
    }

    /**
     * 创建企业送达公告领域模型对象
     * @return 企业送达公告领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyDeliveryAnnouncement create(){
        return DomainFactory.create(DataCompanyDeliveryAnnouncement.class);
    }
}
