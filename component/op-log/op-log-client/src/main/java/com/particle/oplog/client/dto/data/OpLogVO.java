package com.particle.oplog.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
import java.time.LocalDateTime;
/**
 * <p>
 * 操作日志 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Data
@Schema
public class OpLogVO extends AbstractBaseIdTreeVO {

    @Schema(description = "操作名称")
    private String name;
    
    @Schema(description = "模块对应的字典id")
    private Long moduleDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "moduleDictId",mapValueField = "name")
    @Schema(description = "模块对应的字典id对应字典名称")
    private String moduleDictName;
        
    @Schema(description = "模块")
    private String module;
    
    @Schema(description = "类型对应的字典id")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "类型对应的字典id对应字典名称")
    private String typeDictName;
        
    @Schema(description = "类型")
    private String type;
    
    @Schema(description = "操作用户id")
    private Long userId;
    
    @Schema(description = "操作用户姓名")
    private String userName;
    
    @Schema(description = "操作用户昵称")
    private String userNickname;
    
    @Schema(description = "操作用户头像")
    private String userAvatar;
    
    @Schema(description = "请求地址")
    private String url;
    
    @Schema(description = "请求ip")
    private String ip;
    
    @Schema(description = "主数据id")
    private Long mainDataId;
    
    @Schema(description = "主数据表名")
    private String mainDataTable;
    
    @Schema(description = "主数据载体")
    private String mainDataEntity;
    
    @Schema(description = "操作时间")
    private LocalDateTime operateAt;
        
    @Schema(description = "描述")
    private String remark;
    


}
