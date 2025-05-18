package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利内容 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Data
@Entity
public class DataCompanyIprPatentContent extends AggreateRoot {

    private DataCompanyIprPatentContentId id;

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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprPatentContentDataMd5(abstractContent);
    }

    /**
     * 创建企业知识产权专利内容领域模型对象
     * @return 企业知识产权专利内容领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentContent create(){
        return DomainFactory.create(DataCompanyIprPatentContent.class);
    }
}