package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业裁判文书当事人 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:05
 */
@Data
@Entity
public class DataCompanyJudgmentDocumentParty extends AggreateRoot {

    private DataCompanyJudgmentDocumentPartyId id;

    /**
    * 裁判文书表id
    */
    private Long companyJudgmentDocumentId;

    /**
    * 当事人名称
    */
    private String partyName;

    /**
    * 是否当事人为自然人，1=自然人，0=非自然人
    */
    private Boolean isPartyNaturalPerson;

    /**
    * 当事人公司id，is_party_natural_person = 0 时有值
    */
    private Long partyCompanyId;

    /**
    * 当事人个人id，is_party_natural_person = 1 时有值
    */
    private Long partyCompanyPersonId;

    /**
    * 当事人角色,字典id，如：原告、被告
    */
    private Long partyRoleDictId;

    /**
    * 当事人描述信息
    */
    private String partyDescription;

	/**
	 * 数据md5,party_name
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
        this.dataMd5 = SomeMd5Tool.dataCompanyJudgmentDocumentPartyDataMd5(partyName);
    }
    /**
     * 创建企业裁判文书当事人领域模型对象
     * @return 企业裁判文书当事人领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyJudgmentDocumentParty create(){
        return DomainFactory.create(DataCompanyJudgmentDocumentParty.class);
    }
}
