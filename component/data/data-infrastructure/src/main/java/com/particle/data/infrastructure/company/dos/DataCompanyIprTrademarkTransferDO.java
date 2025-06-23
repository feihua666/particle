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
 * 企业知识产权商标转让信息表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:04
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_trademark_transfer")
public class DataCompanyIprTrademarkTransferDO extends BaseDO {

    /**
    * 企业知识产权商标表id
    */
    private Long companyIprTrademarkId;

    /**
    * 转让期号
    */
    private String transferIssueNo;

    /**
    * 转让页码
    */
    private String transferPageNo;

    /**
    * 转让公告日期
    */
    private LocalDate transferPublicDate;
    
    /**
    * 数据md5,transfer_issue_no
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
