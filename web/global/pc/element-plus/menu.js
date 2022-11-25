/**
 * 菜单 相关,主要用于组件封装
 */
import { computed} from 'vue'

/**
 * 菜单 属性，封装组件时用
 */
export const menuProps = {
    // 自定义属性
    props: {
        type: Object,
        default: () => ({})
    }
}
// 返回是否有菜单 ，及菜单 其它常用属性
export const menuConfig = ({props}) => {
    // propsOptions
    const propsOptions = computed(() => {
        let defaultProps = {
            // 指定选项的对象类型的某个属性值
            type: 'type',
            // 指定选项的索引的某个属性值
            index: 'id',
            // 指定选项的图标的某个属性值
            icon: 'icon',
            // 指定选项的图标的某个属性值
            // 菜单名称
            name: 'name',
            // 指定选项的叶子节点的标志位为选项对象的某个属性值
            children: 'children',
        }
        return Object.assign(defaultProps, props.props)
    })
    const isMenu = (menuItem) => {
        return menuItem.isMenu || menuItem[propsOptions.value.type] == 'menu'
    }
    const isPage = (menuItem) => {
        return menuItem.isPage || menuItem[propsOptions.value.type] == 'page'
    }
    const isGroup = (menuItem) => {
        return menuItem.isGroup || menuItem[propsOptions.value.type] == 'group'
    }
    return {
        propsOptions,
        isMenu,
        isPage,
        isGroup,
    }
}

