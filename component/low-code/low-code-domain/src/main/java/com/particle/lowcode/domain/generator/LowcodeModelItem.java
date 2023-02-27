package com.particle.lowcode.domain.generator;

import cn.hutool.json.JSONUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.tool.json.JsonTool;
import com.particle.lowcode.domain.generator.enums.LowcodeModelItemDesignJsonScope;
import lombok.Data;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 低代码模型项目 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@Entity
public class LowcodeModelItem extends AggreateRoot {

	private LowcodeModelItemId id;
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 实体属性名称
     */
    private String propertyName;
    /**
     * 数据库类型
     */
    private String jdbcType;
    /**
     * 实体属性类型
     */
    private String propertyType;
    /**
     * 实体属性类型全路径
     */
    private String propertyFullType;
    /**
     * 字段注释,完整的注释
     */
    private String commentFull;
    /**
     * 字段注释,简洁注释，提取列注释的第一个逗号前面的，常用于swagger
     */
    private String commentSimple;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 是否唯一，一般有唯一索引就是唯一，不算主键
     */
    private Boolean isUnique;
    /**
     * 是否必填
     */
    private Boolean isRequired;
    /**
     * 是否主键
     */
    private Boolean isKey;
    /**
     * 是否主键自增
     */
    private Boolean isKeyIdentity;
    /**
     * 是否为关键字
     */
    private Boolean isKeyWord;
    /**
     * 字段长度
     */
    private Integer columnLength;
    /**
     * 字段小数位长度
     */
    private Integer fractionLength;
    /**
     * 字段是否外键
     */
    private Boolean isForeignKey;
    /**
     * 模型id
     */
    private Long lowcodeModelId;
    /**
     * 设计json数据，包括一般CRUD相关配置信息
     */
    private String designJson;
    /**
     * 描述,注意事项等
     */
    private String remark;

    /**
     * 转换为bean
     * 一般用来处理数据
     * @return
     */
    public DesignJson designJsonStrToDesignJsonBean(){
        return JSONUtil.toBean(this.designJson, DesignJson.class);
    }

    /**
     * 转换为json数据
     * @param designJson
     * @return
     */
    public String parseToDesignJsonStr(DesignJson designJson) {
        this.designJson = JsonTool.toJsonStr(designJson);
        return this.designJson;
    }

    /**
     * 初始化设计json数据
     * @param ignorePropertyNames
     */
    public void initDesignJson( Set<String> ignorePropertyNames) {
        Map<LowcodeModelItemDesignJsonScope, LowcodeModelItem.DesignJsonItem> designJsonItemMap = Arrays.stream(LowcodeModelItemDesignJsonScope.values()).collect(Collectors.toMap(Function.identity(), (scope) -> {

            LowcodeModelItem.DesignJsonItem designJsonItem = LowcodeModelItem.DesignJsonItem.create(
                    !ignorePropertyNames.contains(getPropertyName()),
                    getIsRequired(),
                    false,
                    false
            );
            return designJsonItem;
        }));
        parseToDesignJsonStr(LowcodeModelItem.DesignJson.create(designJsonItemMap));

    }

    /**
     * 根据全注释截取简洁注释
     */
    public void fillCommentSimpleByCommentFull(){
        if (commentFull == null) {
            return;
        }
        commentSimple = commentFull.split(",")[0];
        commentSimple = commentSimple.split("，")[0];
    }
	/**
	 * 创建低代码模型项目领域模型对象
	 * @return 低代码模型项目领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static LowcodeModelItem create(){
		return DomainFactory.create(LowcodeModelItem.class);
	}

    /**
     * {@link LowcodeModelItem#designJson} 对应的数据结构
     */
	@Data
	public static class DesignJson{

        /**
         * json数据项
         */
        private Map<LowcodeModelItemDesignJsonScope,DesignJsonItem> jsonItemMap;

        /**
         * 创建
         * @param jsonItemMap
         * @return
         */
        public static DesignJson create(Map<LowcodeModelItemDesignJsonScope,DesignJsonItem> jsonItemMap){
            DesignJson designJson = new DesignJson();
            designJson.setJsonItemMap(jsonItemMap);
            return designJson;
        }
    }

    @Data
    public static class DesignJsonItem{
        /**
         * 是否使用
         */
        private Boolean isUse;
        /**
         * 是否必填
         */
        private Boolean isRequired;
        /**
         * 是否模糊查询，仅限查询时使用
         */
        private Boolean isUseLike;
        /**
         * 是否翻译字典
         */
        private Boolean isTransDictId;

        public static DesignJsonItem create(Boolean isUse,
                                            Boolean isRequired,
                                            Boolean isUseLike,
                                            Boolean isTransDictId) {
            DesignJsonItem designJsonItem = new DesignJsonItem();
            designJsonItem.setIsUse(Optional.ofNullable(isUse).orElse(false));
            designJsonItem.setIsRequired(Optional.ofNullable(isRequired).orElse(false));
            designJsonItem.setIsUseLike(Optional.ofNullable(isUseLike).orElse(false));
            designJsonItem.setIsTransDictId(Optional.ofNullable(isTransDictId).orElse(false));

            return designJsonItem;
        }
    }


}
