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
 * 企业法院公告表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_court_announcement")
public class DataCompanyCourtAnnouncementDO extends BaseDO {

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


}
