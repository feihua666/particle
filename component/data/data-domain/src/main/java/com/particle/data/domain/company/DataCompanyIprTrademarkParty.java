package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标当事人 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:34
 */
@Data
@Entity
public class DataCompanyIprTrademarkParty extends AggreateRoot {

    private DataCompanyIprTrademarkPartyId id;

    /**
    * 企业知识产权商标表id
    */
    private Long companyIprTrademarkId;

    /**
    * 当事人名称原始名称，is_party_natural_person 等于0时为人名，等于1时为公司名
    */
    private String partyName;

    /**
    * 当事人名称英文名称，is_party_natural_person 等于0时为人名，等于1时为公司名
    */
    private String partyNameEn;

    /**
    * 当事人名称中文名称，is_party_natural_person 等于0时为人名，等于1时为公司名
    */
    private String partyNameCn;

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
    * 是否申请人，1=申请人，0=非申请人
    */
    private Boolean isApplicant;

    /**
    * 是否代理人，1=代理人，0=非代理人
    */
    private Boolean isAgent;

    /**
    * 原始地址
    */
    private String address;

    /**
    * 中文地址
    */
    private String addressCn;

    /**
    * 英文地址
    */
    private String addressEn;

    /**
    * 区域编码
    */
    private String areaCode;

    /**
    * 数据md5,party_name + is_applicant + is_agent
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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkPartyDataMd5(partyName,isApplicant,isAgent);
    }


    /**
     * 创建企业知识产权商标当事人领域模型对象
     * @return 企业知识产权商标当事人领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprTrademarkParty create(){
        return DomainFactory.create(DataCompanyIprTrademarkParty.class);
    }
}
