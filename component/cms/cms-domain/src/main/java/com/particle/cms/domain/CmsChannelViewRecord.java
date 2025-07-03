package com.particle.cms.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 栏目访问记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Data
@Entity
public class CmsChannelViewRecord extends AggreateRoot {

    private CmsChannelViewRecordId id;

    /**
    * 站点id
    */
    private Long cmsSiteId;

    /**
    * 栏目id
    */
    private Long cmsChannelId;

    /**
    * 访问终端设备id
    */
    private String deviceId;

    /**
    * 访问终端设备ip
    */
    private String ip;

    /**
    * 访问时间
    */
    private LocalDateTime viewAt;
    


    /**
     * 创建栏目访问记录领域模型对象
     * @return 栏目访问记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CmsChannelViewRecord create(){
        return DomainFactory.create(CmsChannelViewRecord.class);
    }
}
