import ClipboardJS from 'clipboard'

/**
 * 复制文本到剪贴板
 * @param text
 * @param successCallback
 * @param errorCallback
 */
export function copyToClipboard(text: string, successCallback?: (e) => void, errorCallback?: (e) => void) {
    let triggerElement = document.createElement('textarea');
    triggerElement.setAttribute('data-clipboard-text', text);
    document.body.appendChild(triggerElement);
    // 创建 clipboard.js 实例
    const clipboard = new ClipboardJS(triggerElement);

    // 尝试复制文本
    clipboard.on('success', function(e) {
        e.clearSelection();
        successCallback(e)
        clipboard.destroy(); // 复制完成后销毁实例
        // 从 DOM 中移除
        document.body.removeChild(triggerElement);
    });
    clipboard.on('error', function(e) {
        errorCallback(e)
        clipboard.destroy(); // 发生错误时销毁实例

        // 从 DOM 中移除
        document.body.removeChild(triggerElement);
    });

    // 触发点击事件以开始复制过程
    triggerElement.click();
}

/**
 * 复制文本到剪贴板 promise
 * @param text
 */
export function copy(text: string){
    return new Promise((resolve, reject) => {
        copyToClipboard(text, (e) => {
            resolve(e)
        }, (e) => {
            reject(e)
        })
    })
}
