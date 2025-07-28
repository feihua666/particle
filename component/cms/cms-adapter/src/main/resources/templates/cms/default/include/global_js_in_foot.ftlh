[#-- bootstrap.min.js 是 Bootstrap 的核心 JavaScript 文件，不包含 Popper.js。 --]
[#-- bootstrap.bundle.min.js Bootstrap 的“捆绑版”文件，它包含了 Bootstrap 的核心 JavaScript 代码以及 Popper.js。 --]
<script src="${site.staticResourcePath!}/cms/assets/dist/js/js/bootstrap.bundle.min.js"></script>

<!-- 页面图片宽度自适应脚本 -->
<script>
    var defaultImageClass = '.img-self-adaption'
    function ImgselfAdaption(targetSelector,imageSelector) {
        var _imgSelector = 'img' + defaultImageClass;
        if(imageSelector){
            _imgSelector = imageSelector;
        }
        var $target = $(targetSelector);
        var $imgs = $(_imgSelector);
        $imgs.each(function (i) {
           var $img = $(this);
           if(!$img.data('styleWidth')){
               $img.data('styleWidth',$img.css('width'))
           }
           else if(!$img.data('attrWidth')){
               $img.data('attrWidth',$img.attr('width'))
           }
            imgNaturalDimensions($img[0],function (obj) {

                if(obj.width > $target.width()){
                    $img.width($target.width())
                }else{

                    if($img.data('styleWidth')){
                        $img.css('width',$img.data('styleWidth'))
                    }else if($img.data('attrWidth')){
                        $img.attr('width',$img.attr('width'))
                    }else{
                        $img.css('width','auto')
                    }
                }
            })

        });
    }
    function doImgselfAdaption(targetSelector,imageSelector) {
        ImgselfAdaption(targetSelector,imageSelector);
        $(window).resize(function () {
            ImgselfAdaption(targetSelector,imageSelector);
        })
    }

    function imgNaturalDimensions(imgElement, callback) {
        var nWidth, nHeight;
        if (!imgElement.naturalWidth) { // 现代浏览器
            nWidth = imgElement.naturalWidth;
            nHeight = imgElement.naturalHeight;
            callback({width: nWidth, height:nHeight});
            nWidth = null;
            nHeight = null;
        } else { // IE6/7/8
            var nImg = new Image();
            nImg.src = imgElement.src;
            if(nImg.complete) { // 图片已经存在于浏览器缓存
                nWidth = nImg.width;
                nHeight = nImg.height;
                callback({width: nWidth, height:nHeight});
                nWidth = null;
                nHeight = null;
            }else{
                nImg.onload = function() {
                    nWidth = nImg.width;
                    nHeight = nImg.height;
                    callback({width: nWidth, height:nHeight});
                    nWidth = null;
                    nHeight = null;
                }
            }

        }
    }
</script>
<!-- 页面访问量埋点 开始-->

<!-- 站点首页 -->
[#if !channel??]
<script>
$.ajax({
    url:'/cms/front/site/${site.id}/count',
    method:'PUT'
})

</script>
[/#if]
<!-- 栏目 -->
[#if !content?? && channel??]
<script>
    $.ajax({
        url:'/cms/front/channel/${channel.id}/count',
        method:'PUT'
    })
</script>
[/#if]
<!-- 内容首页 -->
[#if content??]
<script>
    $.ajax({
        url:'/cms/front/content/${content.id}/count',
        method:'PUT'
    })
</script>
[/#if]
<!-- 页面访问量埋点 结束-->
