/**
 * 将图片转换为Base64
 * @param url 可访问的url地址
 * @return {Promise}
 */
export function imageToDataUrl (url:string):Promise<string> {
    return new Promise((resolve, reject) => {
        let canvas = document.createElement('canvas')
        let ctx = canvas.getContext('2d')
        let img = new Image()
        img.onload = function () {
            canvas.height = img.height
            canvas.width = img.width
            ctx.drawImage(img, 0, 0)
            let dataURL = canvas.toDataURL('image/png')
            resolve(dataURL)
            canvas = null
        }
        img.src = url
    })
}

/**
 * 将base64转换为文件
 * @param dataUrl base64图片文件
 * @return {File}
 */
export function dataUrltoFile (dataUrl) {
    let arr = dataUrl.split(',')
    let mime = arr[0].match(/:(.*?);/)[1]
    let bstr = atob(arr[1])
    let n = bstr.length
    let u8arr = new Uint8Array(n)
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
    }
    let fileExt = mime.replace('image/', '')
    return new File([u8arr], 'file_' + Date.parse(new Date()) + '.' + fileExt, {type: mime})
}

/**
 * 将 base64转为blob
 * @param dataUrl
 * @return {Blob}
 */
export function dataUrltoBlob (dataUrl) {
    let arr = dataUrl.split(',')
    let mime = arr[0].match(/:(.*?);/)[1]
    let bstr = atob(arr[1])
    let n = bstr.length
    let u8arr = new Uint8Array(n)
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
    }
    return new Blob([u8arr], {type: mime})
}

/**
 * blob to base64
 * @param blob
 * @param callback
 */
export function  blobToDataURl(blob, callback) {
    let reader = new FileReader();
    reader.onload = function (e) {
        callback(e.target.result);
    };
    reader.readAsDataURL(blob);
}

/**
 * 下载文件,根据地址下载
 * @param url url地址,需要以http开头
 * @param fileName 下载的文件名,需要带后缀扩展名
 */
export function downloadFileByUrl(url, fileName) {
    const link = document.createElement('a');
    link.href = url;
    link.style.display = 'none'
    link.setAttribute('target', '_blank')
    if (fileName) {
        link.setAttribute('download', fileName); // 指定下载文件名
    }
    document.body.appendChild(link);
    link.click();
    // 清理工作
    document.body.removeChild(link);
}
/**
 * 下载文件，根据数据流下载
 * @param data 二进制数据
 * @param contentType 二进行文件内容类型
 * @param fileName 下载的文件名,需要带后缀扩展名
 */
export function downloadFileByData(data,contentType, fileName) {
    // 创建一个blob URL
    const url = window.URL.createObjectURL(new Blob([data]));
    downloadFileByUrl(url, fileName)
    window.URL.revokeObjectURL(url);
}

/**
 * 提取下载文件响应头中的文件名
 * @param contentDisposition
 */
function extractFilenameFromContentDisposition(contentDisposition) {
    // 假设contentDisposition是一个包含Content-Disposition响应头的字符串
    // 例如："attachment; filename*=UTF-8''%E4%BD%A0%E5%A5%BD%E5%90%97.txt" 或 "attachment; filename=\"你好吗.txt\""

    // 首先，尝试处理RFC 5987编码的文件名（可能包含%编码）
    const rfc5987Pattern = /filename\*=([^;]*)/i;
    const rfc5987Match = contentDisposition.match(rfc5987Pattern);
    if (rfc5987Match) {
        // 解析RFC 5987编码
        const [, encodingAndValue] = rfc5987Match;
        const [encoding, value] = encodingAndValue.split("''");
        // 注意：这里的decodeURIComponent可能不完全适用于所有编码，特别是非UTF-8的情况
        // 但在这个例子中，我们假设编码是UTF-8（如示例所示）
        return value
    }

    // 如果不是RFC 5987编码，则尝试处理简单的引号包围的文件名
    const filenamePattern = /filename=\"([^\"]*)\"/i;
    const filenameMatch = contentDisposition.match(filenamePattern);
    if (filenameMatch) {
        // 返回文件名（这里可能不需要解码，除非服务器错误地使用了URL编码）
        return filenameMatch[1];
    }

    // 如果没有找到文件名，则返回null或其他适当的值
    return null;
}
/**
 * 从响应头中提取文件名
 * @param res axios响应对象
 */
export function extractFileName(res) {
    let fileName = null
    let headers = res.headers;
    if (headers['content-disposition']) {
        fileName = extractFilenameFromContentDisposition(headers['content-disposition'])
    }
    return fileName
}

/**
 * 从响应头中提取文件类型
 * @param res axios响应对象
 */
export function extractContentType(res) {
    let contentType = null
    let headers = res.headers;
    if (headers['content-type']) {
        contentType = headers['content-type']
    }
    return contentType
}