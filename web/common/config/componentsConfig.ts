const compsStr: string = import.meta.env.VITE_COMPS
const comps: Array<string> = compsStr.split(',')

/**
 * 判断是否启用组件
 * @param componentName
 */
export const componentEnabled = (componentName: string): boolean => {
    let exist = comps.indexOf(componentName) >=0 || comps.indexOf('all') >=0
    return exist
}