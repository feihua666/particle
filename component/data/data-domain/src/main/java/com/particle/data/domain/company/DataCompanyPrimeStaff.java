package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业主要人员 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Data
@Entity
public class DataCompanyPrimeStaff extends AggreateRoot {

    private DataCompanyPrimeStaffId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 姓名
    */
    private String staffName;

    /**
    * 个人表ID
    */
    private Long staffCompanyPersonId;

    /**
    * 职位名称
    */
    private String positionNames;


    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
    }

    /**
     * 创建企业主要人员领域模型对象
     * @return 企业主要人员领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyPrimeStaff create(){
        return DomainFactory.create(DataCompanyPrimeStaff.class);
    }
}
