import {createApp} from "vue";

/**
 * 渲染模板
 * @param jsonObjStr
 * @param template
 * @param data
 */
export const renderTemplate = (jsonObj: Record<any, any> | Array<any>, template: string,data: Record<string, any> = {}): string | null => {

    const div = document.createElement('div')
    const app = createApp({
        template: `<div>${template}</div>`,
        setup() {
            return { data: jsonObj, ...data }
        }
    })
    app.mount(div)
    let result = div.innerHTML
    app.unmount()
    result = result.replace(/^<div>/, '').replace(/<\/div>$/, '')
    return result
}
