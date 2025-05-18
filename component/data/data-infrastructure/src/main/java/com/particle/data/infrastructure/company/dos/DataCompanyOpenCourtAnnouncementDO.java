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
 * 企业开庭公告表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_open_court_announcement")
public class DataCompanyOpenCourtAnnouncementDO extends BaseDO {

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
    * 观看链接/视频链接
    */
    private String videoUrl;

	/**
	 * 数据md5,case_no + case_reason + open_date
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
