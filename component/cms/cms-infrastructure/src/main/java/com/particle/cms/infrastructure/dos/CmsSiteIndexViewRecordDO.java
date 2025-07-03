package com.particle.cms.infrastructure.dos;

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
 * 站点首页访问记录表
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Accessors(chain = true)
@Data
@TableName("component_cms_site_index_view_record")
public class CmsSiteIndexViewRecordDO extends BaseDO {

    /**
    * 站点id
    */
    private Long cmsSiteId;

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
    

}
