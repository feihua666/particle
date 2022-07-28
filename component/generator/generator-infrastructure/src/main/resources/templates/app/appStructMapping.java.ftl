package ${injection.pkg};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import ${injection.vo.pkg}.${injection.vo.className};
import ${injection.domainObject.pkg}.${injection.domainObject.className};
import ${injection.idObject.pkg}.${injection.idObject.className};
import ${injection.entity.pkg}.${injection.entity.className};
import ${injection.pageQueryCommand.pkg}.${injection.pageQueryCommand.className};
import ${injection.queryListCommand.pkg}.${injection.queryListCommand.className};
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * ${injection.tableComment} app应用层数据实体映射转换
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ${injection.className}  implements IBaseQueryCommandMapStruct<${injection.entity.className}>{
	public static ${injection.className} instance = Mappers.getMapper( ${injection.className}.class );

	protected Long map(${injection.idObject.className} ${injection.idObject.classNameVar}){
		if (${injection.idObject.classNameVar} == null) {
			return null;
		}
		return ${injection.idObject.classNameVar}.getId();
	}
	/**
	 * ${injection.tableComment}领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link ${injection.className}#map(${injection.idObject.className})}
	 * @param ${injection.domainObject.classNameVar}
	 * @return
	 */
	public abstract ${injection.vo.className} to${injection.vo.className}(${injection.domainObject.className} ${injection.domainObject.classNameVar});


	/**
	 * 数据对象转视图对象
	 * @param ${injection.entity.classNameVar}
	 * @return
	 */
	public abstract ${injection.vo.className} ${injection.entity.classNameVar}To${injection.vo.className}(${injection.entity.className} ${injection.entity.classNameVar});

	/**
	 * 批量转换
	 * @param ${injection.entity.classNameVar}s
	 * @return
	 */
	public abstract List<${injection.vo.className}> ${injection.entity.classNameVar}sTo${injection.vo.className}s(List<${injection.entity.className}> ${injection.entity.classNameVar}s);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<${injection.vo.className}> infrastructurePageToPageResponse(Page<${injection.entity.className}> page) {
		return PageResponse.of(${injection.entity.classNameVar}sTo${injection.vo.className}s(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public ${injection.entity.className} queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof AreaPageQueryCommand) {
			return pageQueryCommandToDO((AreaPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof AreaQueryListCommand) {
			return queryListCommandToDO(((AreaQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract ${injection.entity.className} pageQueryCommandToDO(${injection.pageQueryCommand.className} ${injection.pageQueryCommand.classNameVar});

	public abstract ${injection.entity.className} queryListCommandToDO(${injection.queryListCommand.className} ${injection.queryListCommand.classNameVar});
}
