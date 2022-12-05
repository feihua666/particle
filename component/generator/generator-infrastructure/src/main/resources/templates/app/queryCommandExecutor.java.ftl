package ${injection.pkg};

import ${injection.appStructMapping.pkg}.${injection.appStructMapping.className};
import ${injection.queryListCommand.pkg}.${injection.queryListCommand.className};
import ${injection.vo.pkg}.${injection.vo.className};
import ${injection.entity.pkg}.${injection.entity.className};
import ${injection.service.pkg}.${injection.service.className};
import ${injection.pageQueryCommand.pkg}.${injection.pageQueryCommand.className};
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * ${injection.tableComment} 列表查询指令执行器
 * </p>
 * @author ${author}
 * @since ${date}
 */
@Component
@Validated
public class ${injection.className}  extends AbstractBaseQueryExecutor {

	private ${injection.service.className} ${injection.service.classNameVar};

	/**
	 * 执行 ${injection.tableComment} 列表查询指令
	 * @param ${injection.queryListCommand.classNameVar}
	 * @return
	 */
	public MultiResponse<${injection.vo.className}> execute(@Valid ${injection.queryListCommand.className} ${injection.queryListCommand.classNameVar}) {
		List<${injection.entity.className}> ${injection.entity.classNameVar} = ${injection.service.classNameVar}.list(${injection.queryListCommand.classNameVar});
		List<${injection.vo.className}> ${injection.vo.classNameVar}s = ${injection.appStructMapping.className}.instance.${injection.entity.classNameVar}sTo${injection.vo.className}s(${injection.entity.classNameVar});
		return MultiResponse.of(${injection.vo.classNameVar}s);
	}
	/**
	 * 执行 ${injection.tableComment} 分页查询指令
	 * @param ${injection.pageQueryCommand.classNameVar}
	 * @return
	 */
	public PageResponse<${injection.vo.className}> execute(@Valid ${injection.pageQueryCommand.className} ${injection.pageQueryCommand.classNameVar}) {
		Page<${injection.entity.className}> page = ${injection.service.classNameVar}.listPage(${injection.pageQueryCommand.classNameVar});
		return ${injection.appStructMapping.className}.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 ${injection.tableComment} 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<${injection.vo.className}> executeDetail(IdCommand detailCommand) {
		${injection.entity.className} byId = ${injection.service.classNameVar}.getById(detailCommand.getId());
		${injection.vo.className} ${injection.vo.classNameVar} = ${injection.appStructMapping.className}.instance.${injection.entity.classNameVar}To${injection.vo.className}(byId);
		return SingleResponse.of(${injection.vo.classNameVar});
	}
	/**
	 * 执行 ${injection.tableComment} 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<${injection.vo.className}> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		${injection.entity.className} byId = ${injection.service.classNameVar}.getById(detailForUpdateCommand.getId());
		${injection.vo.className} ${injection.vo.classNameVar} = ${injection.appStructMapping.className}.instance.${injection.entity.classNameVar}To${injection.vo.className}(byId);
		return SingleResponse.of(${injection.vo.classNameVar});
	}

	@Autowired
	public void set${injection.service.className}(${injection.service.className} ${injection.service.classNameVar}) {
		this.${injection.service.classNameVar} = ${injection.service.classNameVar};
	}
}
