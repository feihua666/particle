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
 * 企业知识产权软件著作表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_software_copyright")
public class DataCompanyIprSoftwareCopyrightDO extends BaseDO {

    /**
    * 注册号
    */
    private String regNo;

    /**
    * 分类号
    */
    private String categoryNo;

    /**
    * 软件全称
    */
    private String name;

    /**
    * 软件简称
    */
    private String nameSimple;

    /**
    * 版本号
    */
    private String versionNo;

    /**
    * 著作权人
    */
    private String copyrightOwner;

    /**
    * 是否著作权人为自然人，1=自然人，0=非自然人
    */
    private Boolean isCopyrightOwnerNaturalPerson;

    /**
    * 著作权人公司id，is_copyright_owner_natural_person = 0 时有值
    */
    private Long copyrightOwnerCompanyId;

    /**
    * 著作权人个人id，is_copyright_owner_natural_person = 1 时有值
    */
    private Long copyrightOwnerCompanyPersonId;

    /**
    * 首次发表日期
    */
    private LocalDate firstPublicDate;
    
    /**
    * 登记日期
    */
    private LocalDate regDate;
    
    /**
    * 国家，如：中国
    */
    private String country;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
