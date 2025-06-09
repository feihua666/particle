package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业经营异常 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Data
@Entity
public class DataCompanyAbnormal extends AggreateRoot {

    private DataCompanyAbnormalId id;

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
    * 列入异常名录原因
    */
    private String putReason;

    /**
    * 列入异常名录日期
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
    * 移出异常名录原因
    */
    private String removeReason;

    /**
    * 移出异常名录日期
    */
    private LocalDate removeDate;
    
    /**
    * 作出移出决定机关公司id
    */
    private Long removeInstituteCompanyId;

    /**
    * 作出移出决定机关名称，冗余公司名称
    */
    private String removeInstituteName;

    /**
    * 数据md5,put_no + put_reason + put_date + put_institute_name
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    


    /**
     * 创建企业经营异常领域模型对象
     * @return 企业经营异常领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyAbnormal create(){
        return DomainFactory.create(DataCompanyAbnormal.class);
    }
}