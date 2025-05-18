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
 * 企业知识产权专利内容表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_content")
public class DataCompanyIprPatentContentDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 原始摘要内容
    */
    private String abstractContent;

	/**
	 * 英文摘要内容
	 */
	private String abstractContentEn;

	/**
	 * 中文摘要内容
	 */
	private String abstractContentCn;

	/**
	 * 原始摘要内容地址
	 */
	private String abstractContentUrl;

	/**
	 * 英文摘要内容地址
	 */
	private String abstractContentEnUrl;

	/**
	 * 中文摘要内容地址
	 */
	private String abstractContentCnUrl;

	/**
	 * 原始简要说明
	 */
	private String briefContent;

	/**
	 * 英文简要说明
	 */
	private String briefContentEn;

	/**
	 * 中文简要说明
	 */
	private String briefContentCn;

	/**
	 * 原始说明书
	 */
	private String instructionContent;

	/**
	 * 英文说明书
	 */
	private String instructionContentEn;

	/**
	 * 中文说明书
	 */
	private String instructionContentCn;

	/**
	 * 原始说明书地址
	 */
	private String instructionContentUrl;

	/**
	 * 英文说明书地址
	 */
	private String instructionContentEnUrl;

	/**
	 * 中文说明书地址
	 */
	private String instructionContentCnUrl;

	/**
	 * 原始权利要求书
	 */
	private String claimContent;

	/**
	 * 英文权利要求书
	 */
	private String claimContentEn;

	/**
	 * 中文权利要求书
	 */
	private String claimContentCn;

	/**
	 * 原始权利要求书地址
	 */
	private String claimContentUrl;

	/**
	 * 英文权利要求书地址
	 */
	private String claimContentEnUrl;

	/**
	 * 中文权利要求书地址
	 */
	private String claimContentCnUrl;

	/**
	 * 数据md5,abstract_content
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}