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
 * 企业知识产权专利通知书信息表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_notice")
public class DataCompanyIprPatentNoticeDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 通知发文日期
    */
    private LocalDate publicDate;
    
    /**
    * 通知挂号
    */
    private String mailNo;

    /**
    * 通知收件人姓名，如：中国国际贸易促进委员会专利商标事务所
    */
    private String receiverName;

    /**
    * 通知书类型,如：20112、29503
    */
    private String noticeType;

    /**
    * 通知书类型说明,如：发明专利申请公布及进入实质审查阶段通知书、国际申请进入国家阶段初步审查合格通知书、复审请求补正通知书
    */
    private String noticeTypeDescription;

	/**
	 * 数据md5,public_date + mail_no + receiver_name + notice_type + notice_type_description
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}