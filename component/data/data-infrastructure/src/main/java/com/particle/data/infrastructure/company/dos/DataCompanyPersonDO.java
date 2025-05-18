package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业个人表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:50
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_person")
public class DataCompanyPersonDO extends BaseDO {

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
    

}
