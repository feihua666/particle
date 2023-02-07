/**
 * 动态创建引入外部脚本
 * @param url
 */
export function loadScript(url){
    var script = document.createElement('script')
    script.type = 'text/javascript'
    script.src = url
    document.body.appendChild(script)
    script.remove()
}

/**
 * 动态创建页面内脚本
 * @param code
 */
export function loadScriptCode(code){
    var codeNode = document.createTextNode(code)
    var script = document.createElement('script')
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
export function loadStyle(url){
    var link = document.createElement('link')
    link.rel = 'stylesheet'
    link.type = 'text/css'
    link.href = url
    var head = document.getElementsByTagName('head')[0]
    head.appendChild(link)
}

/**
 * 动态创建页面内样式
 * @param css
 */
export function loadStyleString(css){
    var style = document.createElement('style')
    style.type = 'text/css'
    try{
        style.appendChild(document.createTextNode(css))
    }catch(ex){
        // 也是为了兼容IE，估计以后也用不上了
        style.styleSheet.cssText = css
    }
    var head = document.getElementsByTagName('head')[0]
    head.appendChild(style)
    // 内部创建的样式，也同样可以使用head.removeChild 进行删除
}