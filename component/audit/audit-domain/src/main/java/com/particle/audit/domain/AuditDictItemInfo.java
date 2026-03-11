package com.particle.audit.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 字典项信息，主要用来匹配一些字典项
 * </p>
 *
 * @author yangwei
 * @since 2025/5/9 11:03
 */
@Data
public class AuditDictItemInfo {

    @Schema(description = "字典id")
    private Long id;

    @Schema(description = "字典编码")
    private String code;

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典值")
    private String value;

    @Schema(description = "私有标识,模糊查询")
    private String privateFlag;

    @Schema(description = "私有标识备忘")
    private String privateFlagMemo;

    @Schema(description = "分组标识")
    private String groupFlag;

    @Schema(description = "分组标识备忘")
    private String groupFlagMemo;

    @Schema(description = "标签，多个以逗号分隔，用来区分字典项")
    private String tags;

    public static AuditDictItemInfo create(Long id,
                                           String code,
                                           String name,
                                           String value,
                                           String privateFlag,
                                           String privateFlagMemo,
                                           String groupFlag,
                                           String groupFlagMemo,
                                           String tags
                                           ) {
        AuditDictItemInfo dataDictItemInfo = new AuditDictItemInfo();
        dataDictItemInfo.setId(id);
        dataDictItemInfo.setCode(code);
        dataDictItemInfo.setName(name);
        dataDictItemInfo.setValue(value);
        dataDictItemInfo.setPrivateFlag(privateFlag);
        dataDictItemInfo.setPrivateFlagMemo(privateFlagMemo);
        dataDictItemInfo.setGroupFlag(groupFlag);
        dataDictItemInfo.setGroupFlagMemo(groupFlagMemo);
        dataDictItemInfo.setTags(tags);
        return dataDictItemInfo;
    }
}
