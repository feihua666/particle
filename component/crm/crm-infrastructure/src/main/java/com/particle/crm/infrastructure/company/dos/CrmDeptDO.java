package com.particle.crm.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 客户公司部门表
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Data
@TableName("component_crm_dept")
public class CrmDeptDO extends BaseTreeDO {

    /**
    * 部门编码
    */
    private String code;

    /**
    * 部门名称
    */
    private String name;

    /**
    * 客户公司id
    */
    private Long crmCompanyId;

    /**
    * 备注
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}
