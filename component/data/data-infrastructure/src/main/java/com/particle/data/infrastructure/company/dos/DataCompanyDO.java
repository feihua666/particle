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
 * 企业表
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company")
public class DataCompanyDO extends BaseDO {

    /**
    * 企业名称
    */
    private String name;

    /**
    * 统一社会信用代码，unified_social_credit_code
    */
    private String uscc;

    /**
    * 注册号，registration_no
    */
    private String regNo;

    /**
    * 组织机构代码，organization_code
    */
    private String orgCode;

    /**
    * 英文名称，english_name
    */
    private String enName;

    /**
    * 父级id，如果存在父级id表示该企业为分支机构
    */
    private Long parentId;

    /**
    * 最后更新时间，相关联的只要有更新，就需要更新该值
    */
    private LocalDateTime latestUpdateAt;
    

}
