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
 * 企业知识产权专利转让信息表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_transfer")
public class DataCompanyIprPatentTransferDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 转移类型,如：1、2
    */
    private String transferType;

    /**
    * 部门
    */
    private String dept;

    /**
    * 变更前权利人，如：xxxx公司
    */
    private String changeBeforeRightHolder;

    /**
    * 变更前地址
    */
    private String changeBeforeAddress;

    /**
    * 变更后权利人，如：xxxx公司
    */
    private String changeAfterRightHolder;

    /**
    * 变更后地址
    */
    private String changeAfterAddress;

    /**
    * 当前权利人，如：xxxx公司
    */
    private String currentRightHolder;

    /**
    * 当前地址
    */
    private String currentAddress;

    /**
    * 变更生效日期
    */
    private LocalDate changeEffectiveDate;

	/**
	 * 数据md5,transfer_type + dept + change_before_right_holder + change_after_right_holder + current_right_holder
	 */
	private String dataMd5;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}