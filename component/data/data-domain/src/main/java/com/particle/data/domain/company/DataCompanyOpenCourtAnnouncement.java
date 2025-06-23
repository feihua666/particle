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
 * 企业开庭公告 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Data
@Entity
public class DataCompanyOpenCourtAnnouncement extends AggreateRoot {

    private DataCompanyOpenCourtAnnouncementId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 案号
    */
    private String caseNo;

    /**
    * 案由
    */
    private String caseReason;

    /**
    * 法院名称
    */
    private String courtName;

    /**
    * 法庭
    */
    private String courtRoom;

    /**
    * 承办部门
    */
    private String undertakeDept;

    /**
    * 开庭日期
    */
    private LocalDate openDate;

    /**
    * 排期日期
    */
    private LocalDate planDate;

    /**
    * 审判长/主审人
    */
    private String presidingJudge;

    /**
    * 公告内容
    */
    private String content;

    /**
    * 观看链接/视频链接
    */
    private String videoUrl;

	/**
	 * 数据md5,case_no + case_reason + open_date + content
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
        this.dataMd5 = SomeMd5Tool.dataCompanyOpenCourtAnnouncementDataMd5(caseNo, caseReason, openDate);
    }
    /**
     * 创建企业开庭公告领域模型对象
     * @return 企业开庭公告领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyOpenCourtAnnouncement create(){
        return DomainFactory.create(DataCompanyOpenCourtAnnouncement.class);
    }
}
