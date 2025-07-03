package com.particle.cms.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 内容访问记录 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Data
@Entity
public class CmsContentViewRecord extends AggreateRoot {

    private CmsContentViewRecordId id;

    /**
    * 站点id
    */
    private Long cmsSiteId;

    /**
    * 内容id
    */
    private Long cmsContentId;

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
     * 创建内容访问记录领域模型对象
     * @return 内容访问记录领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CmsContentViewRecord create(){
        return DomainFactory.create(CmsContentViewRecord.class);
    }
}
