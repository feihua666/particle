import {anyObj} from "../../../../../global/common/tools/ObjectTools";
import {getCurrentDataTime} from "../../../../../global/common/tools/DateTools";
const getDictEnumItems = (DictItems: any[])=>{
    let result = []

    DictItems.forEach(item =>{
        let template = `
	/**
	 * ${item.name}
	 */
	 ${item.value}
    `
        result.push(template)
    })
    return result.join(',')
}
export const genDictEnumJavaClass = ({className,author='generated',dictGroupData,dictItems=[]}:{className: string,dictGroupData: anyObj,dictItems: any[]}): string=>{
    let template = `
import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * ${dictGroupData.name} 字典项
 * </p>
 *
 * @author ${author}
 * @since ${getCurrentDataTime()}
 */
public enum ${className} implements IDictItem {
	${getDictEnumItems(dictItems)};

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.${dictGroupData.code}.groupCode();
	}

	/**
	 * ${dictGroupData.name} 字典组
	 */
	public enum Group implements IDictGroup {
		${dictGroupData.code};

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
    `

    return template
}