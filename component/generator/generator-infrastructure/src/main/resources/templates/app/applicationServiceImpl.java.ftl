package ${injection.pkg};

import ${injection.createCommandExecutor.pkg}.${injection.createCommandExecutor.className};
import ${injection.deleteCommandExecutor.pkg}.${injection.deleteCommandExecutor.className};
import ${injection.updateCommandExecutor.pkg}.${injection.updateCommandExecutor.className};
import ${injection.queryCommandExecutor.pkg}.${injection.queryCommandExecutor.className};
import ${injection.deleteCommand.pkg}.${injection.deleteCommand.className};
import ${injection.updateCommand.pkg}.${injection.updateCommand.className};
import ${injection.queryDetailCommand.pkg}.${injection.queryDetailCommand.className};
import ${injection.queryDetailForUpdateCommand.pkg}.${injection.queryDetailForUpdateCommand.className};
import ${injection.pageQueryCommand.pkg}.${injection.pageQueryCommand.className};
import ${injection.applicationService.pkg}.${injection.applicationService.className};
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
public class ${injection.className} extends AbstractBaseApplicationServiceImpl implements ${injection.applicationService.className} {

	private ${injection.createCommandExecutor.className} ${injection.createCommandExecutor.classNameVar};

	private ${injection.deleteCommandExecutor.className} ${injection.deleteCommandExecutor.classNameVar};

	private ${injection.updateCommandExecutor.className} ${injection.updateCommandExecutor.classNameVar};

	private ${injection.queryCommandExecutor.className} ${injection.queryCommandExecutor.classNameVar};

	@Override
	public SingleResponse<${injection.vo.className}> create(${injection.createCommand.className} ${injection.createCommand.classNameVar}) {
		return ${injection.createCommandExecutor.classNameVar}.execute(${injection.createCommand.classNameVar});
	}

	@Override
	public SingleResponse<${injection.vo.className}> delete(${injection.deleteCommand.className} ${injection.deleteCommand.classNameVar}) {
		return ${injection.deleteCommandExecutor.classNameVar}.execute(${injection.deleteCommand.classNameVar});
	}

	@Override
	public SingleResponse<${injection.vo.className}> update(${injection.updateCommand.className} ${injection.updateCommand.classNameVar}) {
		return ${injection.updateCommandExecutor.classNameVar}.execute(${injection.updateCommand.classNameVar});
	}

	@Override
	public SingleResponse<${injection.vo.className}> queryDetail(${injection.queryDetailCommand.className} ${injection.queryDetailCommand.classNameVar}) {
		return ${injection.queryCommandExecutor.classNameVar}.execute(${injection.queryDetailCommand.classNameVar});
	}

	@Override
	public SingleResponse<${injection.vo.className}> queryDetailForUpdate(${injection.queryDetailForUpdateCommand.className} ${injection.queryDetailForUpdateCommand.classNameVar}) {
		return ${injection.queryCommandExecutor.classNameVar}.execute(${injection.queryDetailForUpdateCommand.classNameVar});
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
	@Autowired
	public void set${injection.queryCommandExecutor.className}(${injection.queryCommandExecutor.className} ${injection.queryCommandExecutor.classNameVar}) {
		this.${injection.queryCommandExecutor.classNameVar} = ${injection.queryCommandExecutor.classNameVar};
	}
}
