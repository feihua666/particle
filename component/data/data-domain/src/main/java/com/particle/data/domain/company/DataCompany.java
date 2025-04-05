package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 企业 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Entity
public class DataCompany extends AggreateRoot {

    private DataCompanyId id;

    /**
    * 企业名称
    */
    private String name;

    /**
    * 统一社会信用代码，unified_social_credit_code
    */
    private String uscc;

    /**
    * 注册号，registration_no
    */
    private String regNo;

    /**
    * 组织机构代码，organization_code
    */
    private String orgCode;

    /**
    * 英文名称，english_name
    */
    private String enName;

    /**
    * 父级id，如果存在父级id表示该企业为分支机构
    */
    private Long parentId;

	/**
	 * 分类，1=企业，2=个体，3=组代
	 */
	private Integer category;

    /**
    * 最后更新时间，相关联的只要有更新，就需要更新该值
    */
    private LocalDateTime latestUpdateAt;

	/**
	 * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
	 */
	private LocalDateTime latestHandleAt;



    /**
     * 创建企业领域模型对象
     * @return 企业领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompany create(){
        return DomainFactory.create(DataCompany.class);
    }
}