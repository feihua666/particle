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
 * 企业知识产权专利统计表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_statistic")
public class DataCompanyIprPatentStatisticDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 同族专利数量
    */
    private Integer familyNum;

    /**
    * 扩展同族专利数量
    */
    private Integer extFamilyNum;

    /**
    * 被引证数量
    */
    private Integer citedNum;

    /**
    * 引证专利数量
    */
    private Integer quoteNum;

    /**
    * 权利要求数量
    */
    private Integer claimNum;

    /**
    * 独权数
    */
    private Integer independentClaimNum;

    /**
    * 从权数
    */
    private Integer dependentClaimNum;

    /**
    * 转让次数
    */
    private Integer transferNum;

    /**
    * 许可次数
    */
    private Integer licenseNum;

    /**
    * 质押次数
    */
    private Integer pledgeNum;

    /**
    * 无效次数
    */
    private Integer invalidNum;

    /**
    * 诉讼次数
    */
    private Integer litigationNum;

    /**
    * IPC分类数量
    */
    private Integer ipcCategoryNum;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
