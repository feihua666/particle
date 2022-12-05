package ${injection.pkg};

import ${injection.queryCommandExecutor.pkg}.${injection.queryCommandExecutor.className};
import ${injection.updateCommand.pkg}.${injection.updateCommand.className};
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import ${injection.pageQueryCommand.pkg}.${injection.pageQueryCommand.className};
import ${injection.representationApplicationService.pkg}.${injection.representationApplicationService.className};
import ${injection.createCommand.pkg}.${injection.createCommand.className};
import ${injection.queryListCommand.pkg}.${injection.queryListCommand.className};
import ${injection.vo.pkg}.${injection.vo.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * ${injection.tableComment} 门面服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@CatchAndLog
public class ${injection.className} extends AbstractBaseApplicationServiceImpl implements ${injection.representationApplicationService.className} {

	private ${injection.queryCommandExecutor.className} ${injection.queryCommandExecutor.classNameVar};

	@Override
	public SingleResponse<${injection.vo.className}> queryDetail(IdCommand detailCommand) {
		return ${injection.queryCommandExecutor.classNameVar}.executeDetail(detailCommand);
	}

	@Override
	public SingleResponse<${injection.vo.className}> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
		return ${injection.queryCommandExecutor.classNameVar}.executeDetailForUpdate(detailForUpdateCommand);
	}

	@Override
	public PageResponse<${injection.vo.className}> pageQuery(${injection.pageQueryCommand.className} ${injection.pageQueryCommand.classNameVar}) {
		return ${injection.queryCommandExecutor.classNameVar}.execute(${injection.pageQueryCommand.classNameVar});
	}

	@Override
	public MultiResponse<${injection.vo.className}> queryList(${injection.queryListCommand.className} ${injection.queryListCommand.classNameVar}) {
		return ${injection.queryCommandExecutor.classNameVar}.execute(${injection.queryListCommand.classNameVar});
	}

	@Autowired
	public void set${injection.queryCommandExecutor.className}(${injection.queryCommandExecutor.className} ${injection.queryCommandExecutor.classNameVar}) {
		this.${injection.queryCommandExecutor.classNameVar} = ${injection.queryCommandExecutor.classNameVar};
	}
}
