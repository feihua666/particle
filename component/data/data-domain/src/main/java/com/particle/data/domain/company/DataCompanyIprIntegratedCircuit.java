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
 * 企业知识产权集成电路 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:28
 */
@Data
@Entity
public class DataCompanyIprIntegratedCircuit extends AggreateRoot {

    private DataCompanyIprIntegratedCircuitId id;

    /**
    * 公告号
    */
    private String publicNo;

    /**
    * 公告日期
    */
    private LocalDate publicDate;

    /**
    * 布图设计名称
    */
    private String name;

    /**
    * 布图设计登记号
    */
    private String regNo;

    /**
    * 布图设计申请日
    */
    private LocalDate applyDate;

    /**
    * 布图设计权利人名称
    */
    private String rightHolder;

    /**
    * 是否权利人为自然人，1=自然人，0=非自然人
    */
    private Boolean isRightHolderNaturalPerson;

    /**
    * 权利人公司id，is_right_holder_natural_person = 0 时有值
    */
    private Long rightHolderCompanyId;

    /**
    * 权利人个人id，is_right_holder_natural_person = 1 时有值
    */
    private Long rightHolderCompanyPersonId;

    /**
    * 布图设计权利人国籍,如：中国
    */
    private String rightHolderCountry;

    /**
    * 布图设计权利人地址
    */
    private String rightHolderAddress;

    /**
    * 布图设计创作人
    */
    private String designCreator;

    /**
    * 布图设计创作完成日
    */
    private LocalDate completeDate;

    /**
    * 布图设计类别,如：结构：MOS 技术：CMOS 功能：其他
    */
    private String typeName;

    /**
    * 首次商业利用日期
    */
    private LocalDate firstBusinessDate;

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
     * 创建企业知识产权集成电路领域模型对象
     * @return 企业知识产权集成电路领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprIntegratedCircuit create(){
        return DomainFactory.create(DataCompanyIprIntegratedCircuit.class);
    }
}
