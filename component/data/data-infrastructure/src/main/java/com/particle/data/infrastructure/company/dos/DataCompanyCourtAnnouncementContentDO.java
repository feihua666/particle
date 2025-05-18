package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业法院公告内容表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_court_announcement_content")
public class DataCompanyCourtAnnouncementContentDO extends BaseDO {

    /**
    * 法院公告表id
    */
    private Long companyCourtAnnouncementId;

    /**
    * 公告内容
    */
    private String content;

	/**
	 * 数据md5,content
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}