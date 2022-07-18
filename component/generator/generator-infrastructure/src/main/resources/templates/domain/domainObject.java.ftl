package ${injection.pkg};

<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * ${injection.tableComment} 领域模型
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
@Entity
public class ${injection.className} extends AggreateRoot {

	private ${injection.idObject.className} id;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    /**
     * ${field.comment}
     */
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->


	/**
	 * 创建${injection.tableComment}领域模型对象
	 * @return ${injection.tableComment}领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static ${injection.className} create(){
		return DomainFactory.create(${injection.className}.class);
	}
}
