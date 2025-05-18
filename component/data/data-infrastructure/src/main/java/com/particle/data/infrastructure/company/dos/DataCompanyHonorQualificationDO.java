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
 * 企业荣誉资质表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_honor_qualification")
public class DataCompanyHonorQualificationDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 名称,如：高新技术企业
    */
    private String name;

    /**
    * 等级，如：国家级
    */
    private String level;

    /**
    * 发布日期
    */
    private LocalDate publishDate;
    
    /**
    * 发布日期
    */
    private LocalDate startDate;
    
    /**
    * 发布日期
    */
    private LocalDate endDate;
    
    /**
    * 证书编号
    */
    private String certificateNo;

    /**
    * 发布单位，如：福州市工业和信息化局
    */
    private String publishOffice;

    /**
    * 发布标题，如：福州市2022年工业龙头企业名单
    */
    private String publishTitle;

	/**
	 * 数据md5,name + level + certificate_no + publish_office + publish_title
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}