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
 * 企业知识产权集成电路表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_integrated_circuit")
public class DataCompanyIprIntegratedCircuitDO extends BaseDO {

    /**
    * 公告号
    */
    private String publicNo;

    /**
    * 公告日期
    */
    private LocalDate publicDate;
    
    /**
    * 布图设计名称
    */
    private String name;

    /**
    * 布图设计登记号
    */
    private String regNo;

    /**
    * 布图设计申请日
    */
    private LocalDate applyDate;
    
    /**
    * 布图设计权利人名称
    */
    private String rightHolder;

    /**
    * 是否权利人为自然人，1=自然人，0=非自然人
    */
    private Boolean isRightHolderNaturalPerson;

    /**
    * 权利人公司id，is_right_holder_natural_person = 0 时有值
    */
    private Long rightHolderCompanyId;

    /**
    * 权利人个人id，is_right_holder_natural_person = 1 时有值
    */
    private Long rightHolderCompanyPersonId;

    /**
    * 布图设计权利人国籍,如：中国
    */
    private String rightHolderCountry;

    /**
    * 布图设计权利人地址
    */
    private String rightHolderAddress;

    /**
    * 布图设计创作人
    */
    private String designCreator;

    /**
    * 布图设计创作完成日
    */
    private LocalDate completeDate;
    
    /**
    * 布图设计类别,如：结构：MOS 技术：CMOS 功能：其他
    */
    private String typeName;

    /**
    * 首次商业利用日期 
    */
    private LocalDate firstBusinessDate;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
