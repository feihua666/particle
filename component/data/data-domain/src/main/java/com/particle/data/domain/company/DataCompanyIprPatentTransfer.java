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
 * 企业知识产权专利转让信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Data
@Entity
public class DataCompanyIprPatentTransfer extends AggreateRoot {

    private DataCompanyIprPatentTransferId id;

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 转移类型,如：1、2
    */
    private String transferType;

    /**
    * 部门
    */
    private String dept;

    /**
    * 变更前权利人，如：xxxx公司
    */
    private String changeBeforeRightHolder;

    /**
    * 变更前地址
    */
    private String changeBeforeAddress;

    /**
    * 变更后权利人，如：xxxx公司
    */
    private String changeAfterRightHolder;

    /**
    * 变更后地址
    */
    private String changeAfterAddress;

    /**
    * 当前权利人，如：xxxx公司
    */
    private String currentRightHolder;

    /**
    * 当前地址
    */
    private String currentAddress;

    /**
    * 变更生效日期
    */
    private LocalDate changeEffectiveDate;

	/**
	 * 数据md5,transfer_type + dept + change_before_right_holder + change_after_right_holder + current_right_holder
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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprPatentTransferDataMd5(transferType,dept,changeBeforeRightHolder,changeAfterRightHolder,currentRightHolder);
    }

    /**
     * 创建企业知识产权专利转让信息领域模型对象
     * @return 企业知识产权专利转让信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentTransfer create(){
        return DomainFactory.create(DataCompanyIprPatentTransfer.class);
    }
}
