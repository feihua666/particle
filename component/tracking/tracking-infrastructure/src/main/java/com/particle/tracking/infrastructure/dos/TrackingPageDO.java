package com.particle.tracking.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;
/**
 * <p>
 * 埋点页面表
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:39:06
 */
@Data
@TableName("component_tracking_page")
public class TrackingPageDO extends BaseTreeDO {

    /**
    * 页面编码,模糊查询
    */
    private String code;

    /**
    * 页面名称,模糊查询
    */
    private String name;

    /**
    * 页面图片地址
    */
    private String imageUrl;

    /**
    * 页面访问地址
    */
    private String absoluteUrl;

    /**
    * 路径说明，说明如何进入到该页面
    */
    private String pathMemo;

    /**
    * 页面版本
    */
    private String pageVersion;

    /**
    * 分组标识，一个字符串可以区分不同的页面，pc、app等
    */
    private String groupFlag;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 描述
    */
    private String remark;


}
