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
 * 企业限制高消费表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_restrict_high_consume")
public class DataCompanyRestrictHighConsumeDO extends BaseDO {

    /**
    * 案号
    */
    private String caseNo;

    /**
    * 文件链接，外部链接
    */
    private String attachUrl;

    /**
    * 文件快照链接，内部链接
    */
    private String attachSnapshotUrl;

    /**
    * 立案日期
    */
    private LocalDate fileCaseDate;

    /**
    * 发布日期
    */
    private LocalDate publishDate;

    /**
    * 执行法院公司id
    */
    private Long executeCourtCompanyId;

    /**
    * 执行法院名称，冗余公司名称
    */
    private String executeCourtName;

	/**
	 * 数据md5,case_no + file_case_date + publish_date + execute_court_name
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
