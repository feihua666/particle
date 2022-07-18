package ${injection.pkg};

import ${injection.domainObject.pkg}.${injection.domainObject.className};
import ${injection.idObject.pkg}.${injection.idObject.className};
<#list injection.imports as im>
import ${im};
</#list>
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * ${injection.tableComment} 防腐层
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${injection.className} extends IBaseGateway {


	/**
	 * 根据 id 获取 ${injection.tableComment} 领域对象
	 * @param ${injection.idObject.classNameVar}
	 * @return 必须包括全部可用属性
	 */
	${injection.domainObject.className} getById(${injection.idObject.className} ${injection.idObject.classNameVar});

	/**
	 * 保存 ${injection.tableComment} 领域对象
	 * 如果不存在应该新增,新增时需要将参数areaId设置成功，如果存在应该更新
	 * @param ${injection.domainObject.classNameVar}  ${injection.tableComment} 领域对象
	 * @return true=保存成功，false=保存失败，注意只要持久化了应该为 {@code true}
	 *         注意在有些 orm 或数据库下数据库中已经存在，更新时影响结果为0，但应该返回 {@code true}
	 */
	boolean save(${injection.domainObject.className} ${injection.domainObject.classNameVar});


	/**
	 * 删除 ${injection.tableComment} 领域对象
	 * @param ${injection.idObject.classNameVar}
	 * @return
	 */
	boolean delete(${injection.idObject.className} ${injection.idObject.classNameVar});
}
