package com.particle.data.domain.company;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * <p>
 * 企业个人 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Data
@Entity
public class DataCompanyPerson extends AggreateRoot {

    private DataCompanyPersonId id;

    /**
    * 姓名
    */
    private String name;

    /**
    * 证号
    */
    private String idNo;

	/**
	 * 脱敏证号
	 */
	private String idNoDesensitized;

	/**
	 * 证号md5
	 */
	private String idNoMd5;

	/**
	 * 证号sha256
	 */
	private String idNoSha256;

	/**
	 * 证号sm3
	 */
	private String idNoSm3;

	/**
	 * 证号密文
	 */
	private String idNoEncrypt;

    /**
    * 手机号
    */
    private String mobile;

    /**
    * 手机号运营商
    */
    private Long mobileOperatorDictId;

    /**
    * 手机号1
    */
    private String mobile1;

    /**
    * 手机号1运营商
    */
    private Long mobile1OperatorDictId;

    /**
    * 手机号2
    */
    private String mobile2;

    /**
    * 手机号2运营商
    */
    private Long mobile2OperatorDictId;

	/**
	 * 处理备注，用来标识一些处理备注
	 */
	private String handleRemark;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;

	public void initForAdd() {
		LocalDateTime now = LocalDateTime.now();
		this.latestHandleAt = now;
		initByIdNo();
	}
	public void initForUpdate() {
		LocalDateTime now = LocalDateTime.now();
		this.latestHandleAt = now;
		initByIdNo();
	}

	private void initByIdNo() {
		if (StrUtil.isNotEmpty(this.idNo)) {
			this.idNoMd5 = DigestUtil.md5Hex(this.idNo);
			this.idNoSha256 = DigestUtil.sha256Hex(this.idNo);
			this.idNoSm3 = SmUtil.sm3(this.idNo);
		}
	}

    /**
     * 创建企业个人领域模型对象
     * @return 企业个人领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyPerson create(){
        return DomainFactory.create(DataCompanyPerson.class);
    }
}
