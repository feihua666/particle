package ${injection.pkg};

<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.domain.id.Id;

/**
 * <p>
 * ${injection.tableComment} 领域模型id
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public class ${injection.className} extends Id {

	public ${injection.className}(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 ${injection.tableComment} 领域模型id
	 * @param id
	 * @return
	 */
	public static ${injection.className} of(Long id){
		return new ${injection.className}(id);
	}
}
