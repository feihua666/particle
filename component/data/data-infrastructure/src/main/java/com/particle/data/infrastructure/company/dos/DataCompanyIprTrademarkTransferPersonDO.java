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
 * 企业知识产权商标转让人表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_trademark_transfer_person")
public class DataCompanyIprTrademarkTransferPersonDO extends BaseDO {

    /**
    * 企业知识产权商标转让表id
    */
    private Long companyIprTrademarkTransferId;

    /**
    * 原始转让人名称
    */
    private String transferName;

    /**
    * 中文转让人名称
    */
    private String transferNameCn;

    /**
    * 英文转让人名称
    */
    private String transferNameEn;

    /**
    * 是否被转让人，1=被转让人，0=转让人
    */
    private Boolean isTransferred;

    /**
    * 数据md5,transfer_name + is_transferred
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
