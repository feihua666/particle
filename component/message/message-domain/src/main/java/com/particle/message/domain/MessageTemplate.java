package com.particle.message.domain;

import cn.hutool.json.JSONUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.ap.internal.util.Strings;

import java.util.Map;

/**
 * <p>
 * 消息模板 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Data
@Entity
public class MessageTemplate extends AggreateRoot {

    private MessageTemplateId id;

    /**
    * 编码，不能重复
    */
    private String code;

    /**
    * 模板名称
    */
    private String name;

    /**
    * 消息标题模板
    */
    private String titleTpl;

    /**
    * 消息标题模板说明
    */
    private String titleTplMemo;

    /**
    * 消息内容模板
    */
    private String contentTpl;

    /**
    * 消息内容模板说明
    */
    private String contentTplMemo;

	/**
	 * 详细配置，可根据不同的通知内容细化配置，如短信内容
     * 其对象形式如：{@link ContentDetailJson}
     * 主要是针对特殊的模板内容配置，该配置会根据不同的通知类型 {@link com.particle.global.notification.notify.NotifyParam.Type} 具体配置模板
	 */
	private String contentDetailJson;

	/**
	 * 内容是否为html，1=是，0=否
	 */
	private Boolean isContentHtml;


    /**
    * 消息模板分类，字典id
    */
    private Long typeDictId;

    /**
    * 是否为分组，1=分组，0=模板
    */
    private Boolean isGroup;

    /**
    * 分组标识
    */
    private String groupFlag;

    /**
    * 分组标识备忘
    */
    private String groupFlagMemo;

    /**
    * 标签，多个以逗号分隔，用来区分字典项
    */
    private String tags;

    /**
    * 描述
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 父级
    */
    private Long parentId;

    /**
     * 获取内容配置个性化数据
     * @return
     */
    public ContentDetailJson contentDetailJson(){
        return parseContentDetailJson(contentDetailJson);
    }


    public static ContentDetailJson parseContentDetailJson(String contentDetailJson) {
        if (Strings.isEmpty(contentDetailJson)) {
            return null;
        }
        return JSONUtil.toBean(contentDetailJson, ContentDetailJson.class);
    }

    /**
     * 创建消息模板领域模型对象
     * @return 消息模板领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static MessageTemplate create(){
        return DomainFactory.create(MessageTemplate.class);
    }

    /**
     * {@link MessageTemplate#contentDetailJson} 对象形式
     */
    @Setter
    @Getter
    public static class ContentDetailJson{
        /**
         * key为类型 和 {@link com.particle.global.notification.notify.NotifyParam.Type} 保持一致
         */
        private Map<String,ContentDetail> contentDetails;

    }
    @Setter
    @Getter
    public static class ContentDetail{
        /**
         * 消息内容模板
         */
        private String contentTpl;

        /**
         * 内容是否为html，1=是，0=否
         */
        private Boolean isContentHtml;

        /**
         * 第三方的模板编码
         */
        private String thirdTemplateCode;
    }
}
