package com.particle.dataquery.client.provider.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 数据查询供应商 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Data
@Schema
public class DataQueryProviderQueryListCommand extends AbstractBaseQueryCommand {



    @Like
        @Schema(description = "供应商名称,左前缀匹配")
    private String name;


    @Schema(description = "是否禁用")
    private Boolean isDisabled;


    @Schema(description = "姓名")
    private String userName;


    @Schema(description = "邮箱")
    private String email;


    @Schema(description = "手机号")
    private String mobile;


    @Schema(description = "描述")
    private String remark;









}
