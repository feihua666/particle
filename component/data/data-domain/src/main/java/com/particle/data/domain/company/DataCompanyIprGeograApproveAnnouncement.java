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
 * 企业知识产权地理标识核准公告 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Data
@Entity
public class DataCompanyIprGeograApproveAnnouncement extends AggreateRoot {

    private DataCompanyIprGeograApproveAnnouncementId id;

    /**
    * 企业知识产权地理标识id
    */
    private Long companyIprGeograId;

    /**
    * 核准公告编号
    */
    private String approvePublicNo;

    /**
    * 企业名称
    */
    private String companyName;

    /**
    * 核准地址
    */
    private String approveAddress;

    /**
    * 核准法人代表
    */
    private String approveLegalPersonName;

    /**
    * 产品名称
    */
    private String productName;

    /**
    * 核准商标
    */
    private String approveTrademark;

    /**
    * 核准日期
    */
    private LocalDate approveDate;

    /**
    * 核准备注
    */
    private String approveRemark;

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
     * 创建企业知识产权地理标识核准公告领域模型对象
     * @return 企业知识产权地理标识核准公告领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprGeograApproveAnnouncement create(){
        return DomainFactory.create(DataCompanyIprGeograApproveAnnouncement.class);
    }
}
