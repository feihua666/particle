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
 * 企业知识产权专利证书信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Data
@Entity
public class DataCompanyIprPatentCertificate extends AggreateRoot {

    private DataCompanyIprPatentCertificateId id;

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 专利证书发文日
    */
    private LocalDate publicDate;

    /**
    * 专利证书挂号
    */
    private String mailNo;

    /**
    * 专利证书收件人姓名
    */
    private String receiverName;

    /**
    * 专利证书收件人地址
    */
    private String receiverAddress;

	/**
	 * 数据md5,public_date + mail_no + receiver_name + receiver_address
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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprPatentCertificateDataMd5(publicDate,mailNo,receiverName,receiverAddress);
    }

    /**
     * 创建企业知识产权专利证书信息领域模型对象
     * @return 企业知识产权专利证书信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentCertificate create(){
        return DomainFactory.create(DataCompanyIprPatentCertificate.class);
    }
}
