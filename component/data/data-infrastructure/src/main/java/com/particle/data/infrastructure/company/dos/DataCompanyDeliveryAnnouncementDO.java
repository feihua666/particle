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
 * 企业送达公告表
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_delivery_announcement")
public class DataCompanyDeliveryAnnouncementDO extends BaseDO {

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
    

}