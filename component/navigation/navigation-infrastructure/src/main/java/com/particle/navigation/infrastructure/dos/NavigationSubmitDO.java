package com.particle.navigation.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
/**
 * <p>
 * 导航提交表
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Accessors(chain = true)
@Data
@TableName("component_navigation_submit")
public class NavigationSubmitDO extends BaseDO {

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


}
