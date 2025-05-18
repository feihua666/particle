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
 * 企业立案信息表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_case_filing")
public class DataCompanyCaseFilingDO extends BaseDO {

    /**
    * 案号
    */
    private String caseNo;

    /**
    * 案由
    */
    private String caseReason;

    /**
    * 执行法院公司id
    */
    private Long executeCourtCompanyId;

    /**
    * 执行法院名称，冗余公司名称
    */
    private String executeCourtName;

    /**
    * 立案日期
    */
    private LocalDate fileCaseDate;

    /**
    * 开庭日期
    */
    private LocalDate openCourtDate;

    /**
    * 结束日期
    */
    private LocalDate finishedCourtDate;

    /**
    * 是否已结案，1=已结案，0=未结案，执行中
    */
    private Boolean isFinished;

    /**
    * 承办部门
    */
    private String undertakeDept;

    /**
    * 承办法官
    */
    private String undertakeJudger;

    /**
    * 法官助理
    */
    private String assistant;

    /**
    * 案件审理程序，如： 民事再审
    */
    private String caseTrialProcedure;

	/**
	 * 数据md5,case_no + case_reason + execute_court_name + case_trial_procedure
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
