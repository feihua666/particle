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
 * 企业荣誉资质 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Data
@Entity
public class DataCompanyHonorQualification extends AggreateRoot {

    private DataCompanyHonorQualificationId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 名称,如：高新技术企业
    */
    private String name;

    /**
    * 等级，如：国家级
    */
    private String grade;

    /**
    * 发布日期
    */
    private LocalDate publishDate;

    /**
    * 发布日期
    */
    private LocalDate startDate;

    /**
    * 发布日期
    */
    private LocalDate endDate;

    /**
    * 证书编号
    */
    private String certificateNo;

    /**
    * 发布单位，如：福州市工业和信息化局
    */
    private String publishOffice;

    /**
    * 发布标题，如：福州市2022年工业龙头企业名单
    */
    private String publishTitle;

	/**
	 * 数据md5,name + level + certificate_no + publish_office + publish_title
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
        this.dataMd5 = SomeMd5Tool.dataCompanyHonorQualificationDataMd5(name,grade,certificateNo,publishOffice,publishTitle);
    }

    /**
     * 创建企业荣誉资质领域模型对象
     * @return 企业荣誉资质领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyHonorQualification create(){
        return DomainFactory.create(DataCompanyHonorQualification.class);
    }
}
