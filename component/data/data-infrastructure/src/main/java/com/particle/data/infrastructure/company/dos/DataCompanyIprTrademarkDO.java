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
 * 企业知识产权商标表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_trademark")
public class DataCompanyIprTrademarkDO extends BaseDO {

    /**
    * 商标名称
    */
    private String name;

    /**
    * 商标名称
    */
    private String nameCn;

    /**
    * 商标名称
    */
    private String nameEn;

    /**
    * 注册号
    */
    private String regNo;

    /**
    * 申请号
    */
    private String applyNo;

    /**
    * 尼斯分类号
    */
    private String niceCategoryNo;

    /**
    * 当前权利状态名称，如：已注册、待审中
    */
    private String rightStatusName;

    /**
    * 商标类型名称，如：普通商标
    */
    private String typeName;

    /**
    * 商标图片地址
    */
    private String trademarkImageUrl;

    /**
    * 注册日期
    */
    private LocalDate regDate;
    
    /**
    * 申请日期
    */
    private LocalDate applyDate;
    
    /**
    * 是否共享，1=共享，0=不共享
    */
    private Boolean isShare;

    /**
    * 是否驰名商标，1=驰名，0=非驰名
    */
    private Boolean isWellKnown;

    /**
    * 是否指定颜色，1=是，0=否
    */
    private Boolean isSpecifyColor;

    /**
    * 优先权日期
    */
    private LocalDate priorityDate;
    
    /**
    * 专用权期限开始日期
    */
    private LocalDate specialStartDate;
    
    /**
    * 专用权期限截止日期
    */
    private LocalDate specialEndDate;
    
    /**
    * 初审公告日期
    */
    private LocalDate firstTrialPublicDate;
    
    /**
    * 初审公告号
    */
    private String firstTrialPublicNo;

    /**
    * 后期指定日期
    */
    private LocalDate lateSpecifyDate;
    
    /**
    * 异议截止日期
    */
    private LocalDate dissentEndDate;
    
    /**
    * 原始语种
    */
    private String originLang;

    /**
    * 注册公告期号
    */
    private String regPublicIssueNo;

    /**
    * 国际注册日期
    */
    private LocalDate internationalRegDate;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
