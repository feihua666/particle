/**
 * 动态创建引入外部脚本
 * @param url
 */
export function loadScript(url: string){
    let script = document.createElement('script')
    script.type = 'text/javascript'
    script.src = url
    document.body.appendChild(script)
    script.remove()
}

/**
 * 动态创建页面内脚本
 * @param code
 */
export function loadScriptCode(code: string){
    let codeNode = document.createTextNode(code)
    let script = document.createElement('script')
    script.type = 'text/javascript'
    try{
        script.appendChild(codeNode)
    }catch(ex){
        // 兼容IE的写法，以后估计用不上了
        script.text = code
    }
    document.body.appendChild(script)
    script.remove()
}

/**
 * 动态引入外部样式
 * @param url
 */
export function loadStyle(url: string){
    let link = document.createElement('link')
    link.rel = 'stylesheet'
    link.type = 'text/css'
    link.href = url
    let head = document.getElementsByTagName('head')[0]
    head.appendChild(link)
}

/**
 * 动态创建页面内样式
 * @param css
 */
export function loadStyleString(css: string){
    let style = document.createElement('style')
    style.appendChild(document.createTextNode(css))
    let head = document.getElementsByTagName('head')[0]
    head.appendChild(style)
}
