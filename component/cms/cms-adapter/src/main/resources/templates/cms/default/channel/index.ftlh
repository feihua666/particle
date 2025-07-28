<!DOCTYPE html>
<html lang="zh-CN">
<head>
    [#include "../include/global_meta.ftlh"/]

    <meta name="description" content="">
    <meta name="author" content="">

    <title>${site.name}</title>

    [#include "../include/global_favicon.ftlh"/]

    <!-- Custom styles for this template -->
    <style>

    </style>

    [#include "../include/global_css.ftlh"/]
    [#include "../include/global_js_in_head.ftlh"/]

</head>
<body>
[#include "../include/global_navigation.ftlh"/]
<div class="main-wrapper  container" style="margin-top:60px;">
    [#include "../include/global_breadcrumb.ftlh"/]
    <div class="row">
        <!-- 图库 -->
        <div class="col-sm-12">
            [@cms_content_list siteId=site.id channelId=channel.id   iteratorType="var"]

            [#assign newsList = contentList]
            [#assign title = "内容列表"]
            <!-- rowNum为12的约数 -->
            [#assign rowNum = 4]
            [#include "../include/widget/video_list.ftlh"/]
            [/@]
        </div>
    </div>
</div>

[#include "../include/global_footer.ftlh"/]

[#include "../include/global_js_in_foot.ftlh"/]

<!-- Custom javascript for this template -->
[#assign excuType="scrolled"]
[#include "../include/global_navigation_js.ftlh"/]
<script>

</script>
</body>
</html>
