<!DOCTYPE html>
<html lang="zh-CN">
<head>
    [#include "../include/global_meta.ftlh"/]

    <meta name="description" content="">
    <meta name="author" content="">

    <title>${site.name} - ${content.title!}</title>

    [#include "../include/global_favicon.ftlh"/]


    [#include "../include/global_css.ftlh"/]
    <link href="${site.staticResourcePath!}/css/widget.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <style>

    </style>
    [#include "../include/global_js_in_head.ftlh"/]

</head>
<body>
[#include "../include/global_navigation.ftlh"/]

<div class="main-wrapper  container" style="margin-top:60px;">
    [#include "../include/global_breadcrumb.ftlh"/]
    <!-- 内容详情 -->
    <div class="col-sm-9 content-content">
        <h2>${content.title!}</h2>
        <h4>
            <small>更新时间： ${content.updateAt!?datetime }</small>
            &nbsp;
            &nbsp;
            &nbsp;
            &nbsp;
            <small>作者： ${content.author! }</small>
            &nbsp;
            &nbsp;
            &nbsp;
            &nbsp;
            <small>浏览量： ${content.pv! }</small>
        </h4>
        <hr/>
        <div class="row" style="height: 800px;overflow-y: auto;border: 1px solid #c3b9b4cc;">

        [#if content.libraryImages??]
        [#list content.libraryImages as li]
        <br/>
        <img src="${li.imageUrl!}" style="width:100%;">
        <br/>

        [/#list]
        [/#if]
        </div>
    </div>
</div>

[#include "../include/global_footer.ftlh"/]

[#include "../include/global_js_in_foot.ftlh"/]

<!-- Custom javascript for this template -->
[#assign excuType="scrolled"]
[#include "../include/global_navigation_js.ftlh"/]
<script>
    doImgselfAdaption('.content-content','.content-content img')
</script>
</body>
</html>
