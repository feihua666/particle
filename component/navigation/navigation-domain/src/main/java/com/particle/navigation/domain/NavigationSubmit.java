package com.particle.navigation.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.navigation.domain.enums.NavigationSubmitStatus;
import com.particle.navigation.domain.gateway.NavigationDictGateway;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
/**
 * <p>
 * 导航提交 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Data
@Entity
public class NavigationSubmit extends AggreateRoot {

    private NavigationSubmitId id;

    /**
    * 网站名称
    */
    private String name;

    /**
    * 网站标题
    */
    private String title;

    /**
    * 网站地址
    */
    private String url;

    /**
    * 提交时间
    */
    private LocalDateTime submitAt;

    /**
    * 状态，字典id，新提交，数据补充中，数据补充完成，已提交
    */
    private Long statusDictId;

	/**
	 * 网站数据json，数据补充时先补充到这里，最后提交到网站表中
	 */
	private String siteDataJson;

    /**
    * 描述
    */
    private String remark;


    /**
     * 添加时使用
     */
    public void initForAdd() {
        if (this.submitAt == null) {
            this.submitAt = LocalDateTime.now();
        }
        this.statusDictId = navigationDictGateway.getDictIdByGroupCodeAndItemValue(NavigationSubmitStatus.Group.navigation_submit_status.groupCode(),
                NavigationSubmitStatus.new_submit.itemValue());
    }

    public void changeStatusToSubmitted() {
        this.statusDictId = navigationDictGateway.getDictIdByGroupCodeAndItemValue(NavigationSubmitStatus.Group.navigation_submit_status.groupCode(),
                NavigationSubmitStatus.submitted.itemValue());
    }

    /**
     * 创建导航提交领域模型对象
     * @return 导航提交领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static NavigationSubmit create(){
        return DomainFactory.create(NavigationSubmit.class);
    }

    private NavigationDictGateway navigationDictGateway;

    @Autowired
    public void setNavigationDictGateway(NavigationDictGateway navigationDictGateway) {
        this.navigationDictGateway = navigationDictGateway;
    }

}
