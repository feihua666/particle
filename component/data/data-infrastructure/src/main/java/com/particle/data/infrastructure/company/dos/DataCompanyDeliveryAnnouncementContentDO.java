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
 * 企业送达公告内容表
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_delivery_announcement_content")
public class DataCompanyDeliveryAnnouncementContentDO extends BaseDO {

    /**
    * 企业送达公告id
    */
    private Long companyDeliveryAnnouncementId;

    /**
    * 公告内容
    */
    private String content;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
