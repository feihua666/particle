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
 * 企业年报变更 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Data
@Entity
public class DataCompanyAnnualReportChange extends AggreateRoot {

    private DataCompanyAnnualReportChangeId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 企业年报表ID
    */
    private Long companyAnnualReportId;

    /**
    * 年报年度
    */
    private Integer year;

    /**
    * 序号
    */
    private Integer serialNumber;

    /**
    * 变更事项,字典id
    */
    private Long changeItemDictId;

    /**
    * 变更事项
    */
    private String changeItemName;

    /**
    * 变更前内容
    */
    private String contentBefore;

    /**
    * 变更后内容
    */
    private String contentAfter;

    /**
    * 变更日期
    */
    private LocalDate changeDate;

	/**
	 * 数据md5,change_item_name + content_before + content_after + change_date
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
        this.dataMd5 = SomeMd5Tool.dataCompanyAnnualReportChangeDataMd5(changeItemName, contentBefore, contentAfter, changeDate);
    }

    /**
     * 创建企业年报变更领域模型对象
     * @return 企业年报变更领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyAnnualReportChange create(){
        return DomainFactory.create(DataCompanyAnnualReportChange.class);
    }
}
