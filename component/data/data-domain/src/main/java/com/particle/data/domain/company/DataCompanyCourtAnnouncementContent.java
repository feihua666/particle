package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业法院公告内容 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Data
@Entity
public class DataCompanyCourtAnnouncementContent extends AggreateRoot {

    private DataCompanyCourtAnnouncementContentId id;

    /**
    * 法院公告表id
    */
    private Long companyCourtAnnouncementId;

    /**
    * 公告内容
    */
    private String content;

	/**
	 * 数据md5,content
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
        this.dataMd5 = SomeMd5Tool.dataCompanyCourtAnnouncementContentDataMd5(content);
    }

    /**
     * 创建企业法院公告内容领域模型对象
     * @return 企业法院公告内容领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyCourtAnnouncementContent create(){
        return DomainFactory.create(DataCompanyCourtAnnouncementContent.class);
    }
}
