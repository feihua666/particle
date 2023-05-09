package com.particle.oplog.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class OpLogVO extends AbstractBaseIdTreeVO {

    @ApiModelProperty("操作名称")
    private String name;
    
    @ApiModelProperty("模块对应的字典id")
    private Long moduleDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "moduleDictId",mapValueField = "name")
    @ApiModelProperty("模块对应的字典id对应字典名称")
    private String moduleDictName;
        
    @ApiModelProperty("模块")
    private String module;
    
    @ApiModelProperty("类型对应的字典id")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @ApiModelProperty("类型对应的字典id对应字典名称")
    private String typeDictName;
        
    @ApiModelProperty("类型")
    private String type;
    
    @ApiModelProperty("操作用户id")
    private Long userId;
    
    @ApiModelProperty("操作用户姓名")
    private String userName;
    
    @ApiModelProperty("操作用户昵称")
    private String userNickname;
    
    @ApiModelProperty("操作用户头像")
    private String userAvatar;
    
    @ApiModelProperty("请求地址")
    private String url;
    
    @ApiModelProperty("请求ip")
    private String ip;
    
    @ApiModelProperty("主数据id")
    private Long mainDataId;
    
    @ApiModelProperty("主数据表名")
    private String mainDataTable;
    
    @ApiModelProperty("主数据载体")
    private String mainDataEntity;
    
    @ApiModelProperty("操作时间")
    private LocalDateTime operateAt;
        
    @ApiModelProperty("描述")
    private String remark;
    


}
