package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 企业年报网站网店 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Data
@Entity
public class DataCompanyAnnualReportWebsite extends AggreateRoot {

    private DataCompanyAnnualReportWebsiteId id;

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
    * 类型，字典id，如：网站、网店
    */
    private Long typeDictId;

	/**
	 * 类型名称，如：网站、网店
	 */
	private String typeName;

    /**
    * 名称
    */
    private String name;

    /**
    * 网址
    */
    private String url;

	/**
	 * 数据md5,type_name + name + url
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
        this.dataMd5 = SomeMd5Tool.dataCompanyAnnualReportWebsiteDataMd5(typeName,name,url);
    }

    /**
     * 创建企业年报网站网店领域模型对象
     * @return 企业年报网站网店领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyAnnualReportWebsite create(){
        return DomainFactory.create(DataCompanyAnnualReportWebsite.class);
    }
}
