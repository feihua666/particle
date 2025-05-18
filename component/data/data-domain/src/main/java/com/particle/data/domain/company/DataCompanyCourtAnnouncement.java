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
 * 企业法院公告 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Data
@Entity
public class DataCompanyCourtAnnouncement extends AggreateRoot {

    private DataCompanyCourtAnnouncementId id;

    /**
    * 公告号
    */
    private String announcementNo;

    /**
    * 公告类型
    */
    private String announcementType;

    /**
    * 案号
    */
    private String caseNo;

    /**
    * 案由
    */
    private String caseReason;

    /**
    * 法院公司id
    */
    private Long courtCompanyId;

    /**
    * 法院名称，冗余公司名称
    */
    private String courtName;

    /**
    * 刊登板面页码
    */
    private String publishPage;

    /**
    * 刊登板面日期
    */
    private LocalDate publishPageDate;

    /**
    * 发布日期
    */
    private LocalDate publishDate;

	/**
	 * 数据md5,announcement_no + announcement_type + case_no + case_reason + publish_date
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
        this.dataMd5 = SomeMd5Tool.dataCompanyCourtAnnouncementDataMd5(announcementNo, announcementType, caseNo, caseReason, publishDate);
    }

    /**
     * 创建企业法院公告领域模型对象
     * @return 企业法院公告领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyCourtAnnouncement create(){
        return DomainFactory.create(DataCompanyCourtAnnouncement.class);
    }
}
