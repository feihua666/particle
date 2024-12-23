/**
 * 菜单 相关,主要用于组件封装
 */
import {computed} from 'vue'

export interface MenuProps{
    // 指定选项的对象类型的某个属性值
    type?: string,
    // 指定选项的索引的某个属性值
    index?: string,
    // 指定选项的索引的某个属性值,index 不存在时使用
    backIndex?: string,
    // 指定选项的图标的某个属性值
    icon?: string,
    // 指定选项的图标的某个属性值
    // 菜单名称
    name?: string,
    // 指定选项的叶子节点的标志位为选项对象的某个属性值
    children?: string,
}
/**
 * 菜单 属性，封装组件时用
 */
export const menuProps = {
    // 自定义属性,用来取数据对应的值
    props: {
        type: Object,
        default: (): MenuProps => ({})
    }
}
// 返回是否有菜单 ，及菜单 其它常用属性
export const menuConfig = ({props}) => {
    // propsOptions
    const propsOptions = computed(() => {
        let defaultProps: MenuProps = {
            // 指定选项的对象类型的某个属性值
            type: 'type',
            // 指定选项的索引的某个属性值
            index: 'id',
            // 指定选项的索引的某个属性值，index 对应值不存在时使用
            backIndex: 'id',
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
    // 是否一个菜单
    const isMenu = (menuItem) => {
        return menuItem.isMenu || menuItem[propsOptions.value.type] == 'menu'
    }
    // 是否一个页面，一个页面需要配置路由
    const isPage = (menuItem) => {
        return menuItem.isPage || menuItem[propsOptions.value.type] == 'page'
    }
    // 是否一个分组，element plus 支持的展示方式
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

