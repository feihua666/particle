package ${injection.pkg};

import ${injection.createCommandExecutor.pkg}.${injection.createCommandExecutor.className};
import ${injection.deleteCommandExecutor.pkg}.${injection.deleteCommandExecutor.className};
import ${injection.updateCommandExecutor.pkg}.${injection.updateCommandExecutor.className};
import com.particle.common.client.dto.command.IdCommand;
import ${injection.updateCommand.pkg}.${injection.updateCommand.className};
import ${injection.applicationService.pkg}.${injection.applicationService.className};
import ${injection.createCommand.pkg}.${injection.createCommand.className};
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
@Transactional
@Service
@CatchAndLog
public class ${injection.className} extends AbstractBaseApplicationServiceImpl implements ${injection.applicationService.className} {

	private ${injection.createCommandExecutor.className} ${injection.createCommandExecutor.classNameVar};

	private ${injection.deleteCommandExecutor.className} ${injection.deleteCommandExecutor.classNameVar};

	private ${injection.updateCommandExecutor.className} ${injection.updateCommandExecutor.classNameVar};


	@Override
	public SingleResponse<${injection.vo.className}> create(${injection.createCommand.className} ${injection.createCommand.classNameVar}) {
		return ${injection.createCommandExecutor.classNameVar}.execute(${injection.createCommand.classNameVar});
	}

	@Override
	public SingleResponse<${injection.vo.className}> delete(IdCommand deleteCommand) {
		return ${injection.deleteCommandExecutor.classNameVar}.execute(deleteCommand);
	}

	@Override
	public SingleResponse<${injection.vo.className}> update(${injection.updateCommand.className} ${injection.updateCommand.classNameVar}) {
		return ${injection.updateCommandExecutor.classNameVar}.execute(${injection.updateCommand.classNameVar});
	}

	@Autowired
	public void set${injection.createCommandExecutor.className}(${injection.createCommandExecutor.className} ${injection.createCommandExecutor.classNameVar}) {
		this.${injection.createCommandExecutor.classNameVar} = ${injection.createCommandExecutor.classNameVar};
	}

	@Autowired
	public void set${injection.deleteCommandExecutor.className}(${injection.deleteCommandExecutor.className} ${injection.deleteCommandExecutor.classNameVar}) {
		this.${injection.deleteCommandExecutor.classNameVar} = ${injection.deleteCommandExecutor.classNameVar};
	}
	@Autowired
	public void set${injection.updateCommandExecutor.className}(${injection.updateCommandExecutor.className} ${injection.updateCommandExecutor.classNameVar}) {
		this.${injection.updateCommandExecutor.classNameVar} = ${injection.updateCommandExecutor.classNameVar};
	}

}
