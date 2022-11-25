/**
 * 将图片转换为Base64
 * @param url 可访问的url地址
 * @return {Promise}
 */
export function imageToDataUrl (url) {
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