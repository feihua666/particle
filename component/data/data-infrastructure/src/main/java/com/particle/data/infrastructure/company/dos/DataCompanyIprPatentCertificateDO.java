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
 * 企业知识产权专利证书信息表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_certificate")
public class DataCompanyIprPatentCertificateDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 专利证书发文日
    */
    private LocalDate publicDate;
    
    /**
    * 专利证书挂号
    */
    private String mailNo;

    /**
    * 专利证书收件人姓名
    */
    private String receiverName;

    /**
    * 专利证书收件人地址
    */
    private String receiverAddress;

	/**
	 * 数据md5,public_date + mail_no + receiver_name + receiver_address
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}