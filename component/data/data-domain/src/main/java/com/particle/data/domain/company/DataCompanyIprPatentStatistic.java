package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利统计 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Data
@Entity
public class DataCompanyIprPatentStatistic extends AggreateRoot {

    private DataCompanyIprPatentStatisticId id;

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 同族专利数量
    */
    private Integer familyNum;

    /**
    * 扩展同族专利数量
    */
    private Integer extFamilyNum;

    /**
    * 被引证数量
    */
    private Integer citedNum;

    /**
    * 引证专利数量
    */
    private Integer quoteNum;

    /**
    * 权利要求数量
    */
    private Integer claimNum;

    /**
    * 独权数
    */
    private Integer independentClaimNum;

    /**
    * 从权数
    */
    private Integer dependentClaimNum;

    /**
    * 转让次数
    */
    private Integer transferNum;

    /**
    * 许可次数
    */
    private Integer licenseNum;

    /**
    * 质押次数
    */
    private Integer pledgeNum;

    /**
    * 无效次数
    */
    private Integer invalidNum;

    /**
    * 诉讼次数
    */
    private Integer litigationNum;

    /**
    * IPC分类数量
    */
    private Integer ipcCategoryNum;

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
     * 创建企业知识产权专利统计领域模型对象
     * @return 企业知识产权专利统计领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentStatistic create(){
        return DomainFactory.create(DataCompanyIprPatentStatistic.class);
    }
}
