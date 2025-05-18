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
 * 企业限制高消费 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Data
@Entity
public class DataCompanyRestrictHighConsume extends AggreateRoot {

    private DataCompanyRestrictHighConsumeId id;

    /**
    * 企业表ID
    */
    private Long companyId;

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
        this.dataMd5 = SomeMd5Tool.dataCompanyRestrictHighConsumeDataMd5(caseNo,fileCaseDate,publishDate,executeCourtName);
    }

    /**
     * 创建企业限制高消费领域模型对象
     * @return 企业限制高消费领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyRestrictHighConsume create(){
        return DomainFactory.create(DataCompanyRestrictHighConsume.class);
    }
}
