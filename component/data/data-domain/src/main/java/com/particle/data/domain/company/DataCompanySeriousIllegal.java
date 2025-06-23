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
 * 企业严重违法 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Data
@Entity
public class DataCompanySeriousIllegal extends AggreateRoot {

    private DataCompanySeriousIllegalId id;

    /**
    * 企业表ID
    */
    private Long companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 列入决定书文号
	 */
	private String putNo;

    /**
    * 列入原因
    */
    private String putReason;

    /**
    * 列入日期
    */
    private LocalDate putDate;

    /**
    * 作出列入决定机关公司id
    */
    private Long putInstituteCompanyId;

    /**
    * 作出列入决定机关名称，冗余公司名称
    */
    private String putInstituteName;

	/**
	 * 移出决定书文号
	 */
	private String removeNo;

    /**
    * 移除原因
    */
    private String removeReason;

    /**
    * 移除日期
    */
    private LocalDate removeDate;

    /**
    * 作出移除决定机关公司id
    */
    private Long removeInstituteCompanyId;

    /**
    * 作出移除决定机关名称，冗余公司名称
    */
    private String removeInstituteName;

	/**
	 * 数据md5,type + put_reason + put_date
	 */
	private String dataMd5;

	/**
	 * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
	 */
	private LocalDateTime latestHandleAt;

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }

    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanySeriousIllegalDataMd5(putNo,putReason,putDate,putInstituteName);
    }

    /**
     * 创建企业严重违法领域模型对象
     * @return 企业严重违法领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanySeriousIllegal create(){
        return DomainFactory.create(DataCompanySeriousIllegal.class);
    }
}
