package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业立案信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Data
@Entity
public class DataCompanyCaseFiling extends AggreateRoot {

    private DataCompanyCaseFilingId id;

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

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }

    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanyCaseFilingDataMd5(caseNo,caseReason,executeCourtName,caseTrialProcedure);
    }

    /**
     * 创建企业立案信息领域模型对象
     * @return 企业立案信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyCaseFiling create(){
        return DomainFactory.create(DataCompanyCaseFiling.class);
    }
}
