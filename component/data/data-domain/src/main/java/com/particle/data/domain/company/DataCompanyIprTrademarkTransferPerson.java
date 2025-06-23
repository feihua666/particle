package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标转让人 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:13
 */
@Data
@Entity
public class DataCompanyIprTrademarkTransferPerson extends AggreateRoot {

    private DataCompanyIprTrademarkTransferPersonId id;

    /**
    * 企业知识产权商标转让表id
    */
    private Long companyIprTrademarkTransferId;

    /**
    * 原始转让人名称
    */
    private String transferName;

    /**
    * 中文转让人名称
    */
    private String transferNameCn;

    /**
    * 英文转让人名称
    */
    private String transferNameEn;

    /**
    * 是否被转让人，1=被转让人，0=转让人
    */
    private Boolean isTransferred;

    /**
    * 数据md5,transfer_name + is_transferred
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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkTransferPersonDataMd5(transferName,isTransferred);
    }


    /**
     * 创建企业知识产权商标转让人领域模型对象
     * @return 企业知识产权商标转让人领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprTrademarkTransferPerson create(){
        return DomainFactory.create(DataCompanyIprTrademarkTransferPerson.class);
    }
}
