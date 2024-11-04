<!DOCTYPE html>
<html lang="zh-CN">
<head>
    [#include "include/global_meta.ftlh"/]
    <meta name="theme-color" content="#f9f9f9" />
    <title>particle| ai工具网址导航,ai最新产品</title>
    [#include "include/global_favicon.ftlh"/]
    <meta name="keywords" content="particle chatgpt,AI,导航，AI导航,AI聊天,AI文本生成,AI绘画,AI编程,AI电商" />
    <meta name="description" content="particle 网址导航 | 免费chatgpt 汇集各类先进的人工智能产品，旨在帮助用户更快速地了解和使用这些产品,轻松地浏览不同领域的AI产品，包括语音识别、图像处理、自然语言处理。" />

    <meta property="og:type" content="article">
    <meta property="og:url" content="${contextPathSimple!""}/nav${frontDetailPath!""}/${siteDO.id?string("0")}.html">
    <meta property="og:title" content="${siteDO.name} | particle| ai工具网址导航,ai最新产品">
    <meta property="og:description" content="开源机器学习框架">
    <meta property="og:image" content="${frontFinalAbsoluteSubContextPath!""}/assets/images/logo.png">
    <meta property="og:site_name" content="AI导航站">
    <meta name="robots" content="max-image-preview:large">

    [#include "include/global_css.ftlh"/]

    [#include "include/global_js_in_head.ftlh"/]

</head>
<body class="io-grey-mode">
    <div class="page-container">
        [#include "include/page/sidebar.ftlh"/]
        [#include "include/page/detail_main_content.ftlh"/]
    </div>

    [#include "include/page/search_modal.ftlh"/]

    [#include "include/global_js_in_foot.ftlh"/]
</body>
</html>


