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
 * 企业知识产权专利法律状态 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Data
@Entity
public class DataCompanyIprPatentLegalStatus extends AggreateRoot {

    private DataCompanyIprPatentLegalStatusId id;

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 法律状态，字典id
    */
    private Long legalStatusDictId;

	/**
	 * 法律状态代码
	 */
	private String legalStatusCode;

	/**
	 * 原始法律状态名称
	 */
	private String legalStatusName;

	/**
	 * 英文法律状态名称
	 */
	private String legalStatusNameEn;

	/**
	 * 中文法律状态名称
	 */
	private String legalStatusNameCn;

    /**
    * 原始法律状态详情
    */
    private String legalStatusDetail;

	/**
	 * 英文法律状态详情
	 */
	private String legalStatusDetailEn;

	/**
	 * 中文法律状态详情
	 */
	private String legalStatusDetailCn;

    /**
    * 法律状态日期
    */
    private LocalDate legalStatusDate;

	/**
	 * 数据md5,legal_status_name + legal_status_detail + legal_status_date
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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprPatentLegalStatusDataMd5(legalStatusName,legalStatusDetail,legalStatusDate);
    }

    /**
     * 创建企业知识产权专利法律状态领域模型对象
     * @return 企业知识产权专利法律状态领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentLegalStatus create(){
        return DomainFactory.create(DataCompanyIprPatentLegalStatus.class);
    }
}
