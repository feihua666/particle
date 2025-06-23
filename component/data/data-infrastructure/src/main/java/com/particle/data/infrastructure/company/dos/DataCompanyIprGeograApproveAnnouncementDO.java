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
 * 企业知识产权地理标识核准公告表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_geogra_approve_announcement")
public class DataCompanyIprGeograApproveAnnouncementDO extends BaseDO {

    /**
    * 企业知识产权地理标识id
    */
    private Long companyIprGeograId;

    /**
    * 核准公告编号
    */
    private String approvePublicNo;

    /**
    * 企业名称
    */
    private String companyName;

    /**
    * 核准地址
    */
    private String approveAddress;

    /**
    * 核准法人代表
    */
    private String approveLegalPersonName;

    /**
    * 产品名称
    */
    private String productName;

    /**
    * 核准商标
    */
    private String approveTrademark;

    /**
    * 核准日期
    */
    private LocalDate approveDate;
    
    /**
    * 核准备注
    */
    private String approveRemark;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
