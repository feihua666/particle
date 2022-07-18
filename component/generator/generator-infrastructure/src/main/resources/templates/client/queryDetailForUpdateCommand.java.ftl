package ${injection.pkg};

<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * ${injection.tableComment} 通用更新时查询详情指令对象
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@ApiModel(value="${injection.tableComment} 通用更新时查询详情指令对象")
public class ${injection.className} extends AbstractBaseCommand {

    @NotNull
	@ApiModelProperty(value = "id",notes = "")
	private Long id;

}
