package com.particle.crm.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 客户公司表
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Data
@TableName("component_crm_company")
public class CrmCompanyDO extends BaseTreeDO {

    /**
    * 公司编码
    */
    private String code;

    /**
    * 公司名称
    */
    private String name;

    /**
    * 公司简称名称
    */
    private String nameSimple;

    /**
    * 备注
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}
