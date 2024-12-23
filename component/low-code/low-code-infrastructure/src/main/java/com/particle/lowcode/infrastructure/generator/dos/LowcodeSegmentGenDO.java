package com.particle.lowcode.infrastructure.generator.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;

/**
 * <p>
 * 低代码生成表
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Data
@TableName("component_lowcode_segment_gen")
public class LowcodeSegmentGenDO extends BaseDO {
    /**
    * 生成名称，一般用于显示标识
    */
    private String name;
    /**
    * 低代码片段模板id
    */
    private Long lowcodeSegmentTemplateId;
    /**
    * 低代码模型id
    */
    private Long lowcodeModelId;
    /**
    * 低代码模型数据包括模型项数据
    */
    private String lowcodeModelJson;
    /**
    * 全局可用变量json数据
    */
    private String globalJson;
    /**
     * 扩展可用变量json数据
     */
    private String extJson;
    /**
     * 输出文件的父目录绝对路径
     */
    private String outputFileParentAbsoluteDir;
    /**
     * java包名的key，根据key可以自动将对应的值转为后缀javaPath路径
     */
    private String javaPackageKeys;
    /**
    * 是否已生成
    */
    private Boolean isGenerated;
    /**
    * 生成类型字典id
    */
    private Long generateTypeDictId;
    /**
    * 引用生成id，主要用来获取引用的相关变量
    */
    private Long refrenceSegmentGenId;
    /**
    * 描述,注意事项等
    */
    private String remark;

}
