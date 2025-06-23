package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业送达公告内容 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Data
@Entity
public class DataCompanyDeliveryAnnouncementContent extends AggreateRoot {

    private DataCompanyDeliveryAnnouncementContentId id;

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
    


    /**
     * 创建企业送达公告内容领域模型对象
     * @return 企业送达公告内容领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyDeliveryAnnouncementContent create(){
        return DomainFactory.create(DataCompanyDeliveryAnnouncementContent.class);
    }
}
