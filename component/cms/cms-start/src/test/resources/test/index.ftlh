<!DOCTYPE html>
<html lang="zh-CN">
<head>
    [#include "../include/global_meta.ftlh"/]

    <meta name="description" content="中国智库网 - 国务院发展研究中心主办">
    <meta name="author" content="国务院发展研究中心">

    <title>${site.name} - ${channel.title!channel.name}</title>

    [#include "../include/global_favicon.ftlh"/]

    <style>
        /* 栏目页面特有样式 */
        .category-header {
            background: linear-gradient(rgba(0, 51, 102, 0.9), rgba(0, 69, 138, 0.9)),
            url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" viewBox="0 0 100 100"><rect width="100" height="100" fill="%23003366"/><path d="M0,50 L100,50 M50,0 L50,100" stroke="%23ffffff" stroke-width="1" opacity="0.1"/></svg>');
            color: white;
            padding: 4rem 0;
            margin-bottom: 3rem;
        }

        .category-title {
            font-size: 2.5rem;
            font-weight: 700;
            margin-bottom: 1rem;
        }

        .category-description {
            font-size: 1.2rem;
            opacity: 0.9;
            max-width: 800px;
            margin: 0 auto;
        }

        /* 内容筛选器 */
        .content-filter {
            background-color: var(--light-gray);
            padding: 1.5rem;
            border-radius: 8px;
            margin-bottom: 2rem;
        }

        .filter-badge {
            background-color: white;
            border: 1px solid var(--medium-gray);
            border-radius: 20px;
            padding: 0.5rem 1.25rem;
            margin-right: 0.75rem;
            margin-bottom: 0.75rem;
            display: inline-block;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .filter-badge:hover, .filter-badge.active {
            background-color: var(--primary-blue);
            color: white;
            border-color: var(--primary-blue);
        }

        /* 内容卡片 */
        .content-card {
            border: 1px solid var(--medium-gray);
            border-radius: 8px;
            overflow: hidden;
            transition: all 0.3s ease;
            height: 100%;
            background-color: white;
        }

        .content-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.12);
            border-color: var(--primary-blue);
        }

        .card-category {
            position: absolute;
            top: 1rem;
            left: 1rem;
            background-color: var(--light-blue);
            color: var(--primary-blue);
            padding: 0.25rem 0.75rem;
            border-radius: 4px;
            font-size: 0.8rem;
            font-weight: 500;
            z-index: 1;
        }

        .card-date {
            font-size: 0.85rem;
            color: #6c757d;
        }

        .card-abstract {
            color: #555;
            font-size: 0.95rem;
            line-height: 1.6;
        }

        .card-footer-custom {
            background-color: var(--light-gray);
            border-top: 1px solid var(--medium-gray);
            padding: 1rem;
        }

        /* 分页器 */
        .custom-pagination .page-link {
            color: var(--primary-blue);
            border: 1px solid var(--medium-gray);
            margin: 0 0.25rem;
            border-radius: 4px;
        }

        .custom-pagination .page-link:hover {
            background-color: var(--light-blue);
            border-color: var(--primary-blue);
        }

        .custom-pagination .active .page-link {
            background-color: var(--primary-blue);
            border-color: var(--primary-blue);
            color: white;
        }

        /* 侧边栏 */
        .sidebar-widget {
            background-color: white;
            border: 1px solid var(--medium-gray);
            border-radius: 8px;
            padding: 1.5rem;
            margin-bottom: 1.5rem;
        }

        .widget-title {
            color: var(--primary-blue);
            border-bottom: 2px solid var(--accent-yellow);
            padding-bottom: 0.5rem;
            margin-bottom: 1rem;
            font-weight: 600;
        }

        .sidebar-list-item {
            padding: 0.75rem 0;
            border-bottom: 1px solid var(--light-gray);
        }

        .sidebar-list-item:last-child {
            border-bottom: none;
        }

        .sidebar-list-item a {
            color: #333;
            text-decoration: none;
        }

        .sidebar-list-item a:hover {
            color: var(--primary-blue);
        }

        /* 搜索框 */
        .custom-search-box {
            border-radius: 20px;
            border: 1px solid #ccc;
            padding: 0.4rem 1.5rem;
            font-size: 0.9rem;
        }

        .custom-btn-primary {
            background-color: var(--primary-blue);
            border-color: var(--primary-blue);
            color: white;
        }

        .custom-btn-primary:hover {
            background-color: var(--secondary-blue);
            border-color: var(--secondary-blue);
        }
    </style>
    <style>
        /* 列表卡片样式修正 */
        .content-card {
            border: 1px solid var(--medium-gray);
            border-radius: 8px;
            transition: all 0.3s ease;
            background-color: white;
            height: 100%;
            display: flex;
            flex-direction: column;
        }

        .content-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
            border-color: var(--primary-blue);
        }

        /* 分类标签样式修正 */
        .custom-category-badge {
            background-color: var(--light-blue);
            color: var(--primary-blue);
            padding: 0.3rem 0.8rem;
            border-radius: 4px;
            font-size: 0.8rem;
            font-weight: 500;
            display: inline-block;
        }

        /* 卡片标题优化 */
        .card-title {
            font-size: 1.05rem;
            line-height: 1.4;
            font-weight: 600;
            margin-bottom: 0.5rem;
        }

        .card-title a:hover {
            color: var(--primary-blue) !important;
        }

        /* 摘要文本优化 */
        .card-abstract {
            color: #555;
            font-size: 0.9rem;
            line-height: 1.6;
            margin-bottom: 1rem;
            /* 限制行数，避免过长 */
            display: -webkit-box;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            overflow: hidden;
        }

        /* 日期样式 */
        .card-date {
            font-size: 0.8rem;
            white-space: nowrap;
        }

        /* 卡片内边距优化 */
        .card-header {
            background-color: transparent;
        }

        .card-body {
            flex-grow: 1;
        }

        /* 响应式调整 */
        @media (max-width: 768px) {
            .card-title {
                font-size: 1rem;
            }

            .card-abstract {
                font-size: 0.85rem;
                -webkit-line-clamp: 2;
            }

            .row.g-4 {
                --bs-gutter-x: 1rem;
                --bs-gutter-y: 1rem;
            }
        }

        @media (max-width: 576px) {
            .col-md-6 {
                margin-bottom: 1rem;
            }

            .content-card {
                margin-bottom: 0;
            }
        }
    </style>
    [#include "../include/global_css.ftlh"/]
    [#include "../include/global_js_in_head.ftlh"/]

</head>
<body>
[#include "../include/global_top_bar.ftlh"/]
[#include "../include/global_navigation.ftlh"/]

<!-- 栏目头部 -->
<header class="category-header">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-8">
                <h1 class="category-title">${channel.name}</h1>
                <p class="category-description">
                    ${channel.profile!}
                </p>
            </div>
            <div class="col-lg-4 text-lg-end">
                <div class="mt-3 mt-lg-0">
                    <span class="badge bg-light text-primary fs-6 me-2">研究报告</span>
                    <span class="badge bg-light text-primary fs-6 me-2">学术论文</span>
                    <span class="badge bg-light text-primary fs-6">政策建议</span>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- 主要内容区域 -->
<main class="container">
    <div class="row">
        <!-- 左侧内容区域 -->
        <div class="col-lg-9">
            <!-- 内容筛选器 -->
            <!--<div class="content-filter">
                <h5 class="mb-3">筛选分类</h5>
                <div>
                    <span class="filter-badge active">全部</span>
                    <span class="filter-badge">宏观经济</span>
                    <span class="filter-badge">产业发展</span>
                    <span class="filter-badge">区域经济</span>
                    <span class="filter-badge">国际经济</span>
                    <span class="filter-badge">社会政策</span>
                    <span class="filter-badge">改革研究</span>
                    <span class="filter-badge">绿色经济</span>
                </div>
                <div class="mt-3 d-flex justify-content-between align-items-center">
                    <div class="text-muted small">

                    </div>
                    <div class="d-flex align-items-center">
                        <span class="me-2 small">排序：</span>
                        <select class="form-select form-select-sm w-auto">
                            <option>最新发布</option>
                            <option>最热浏览</option>
                            <option>按标题</option>
                        </select>
                    </div>
                </div>
            </div>-->
            <!-- 列表 -->
            <div class="row g-4 mb-5">
                [@cms_content_list siteId=site.id channelId=channel.id iteratorType="var" isPage="true" pageNo=page.pageNo pageSize=page.pageSize;item,index]
                    [#assign contentList = dataList]
                    [#assign contentPage = dataPage]
                [/@]
                [#if contentList?? && contentList?size gt 0]
                [#list contentList as item]
                    <div class="col-md-6 col-lg-6 col-xl-4">
                        <div class="content-card h-100">
                            <!-- 分类标签 - 修正为相对定位 -->
                            <div class="card-header pt-3 pb-2 px-3 border-0">
                                <div class="d-flex justify-content-between align-items-start">
                                    <h5 class="card-title mb-1 flex-grow-1">
                                        <a href="${item.indexUrl!}" class="text-decoration-none text-dark">
                                            ${item.title!}
                                        </a>
                                    </h5>
                                    <span class="card-date text-muted small ms-2">[#if item.publicAt??]${(item.publicAt)?substring(0, 10)}[/#if]</span>
                                </div>
                            </div>
                            <div class="card-body pt-0 pb-3 px-3">
                                <p class="card-abstract mb-3">
                                    ${item.profile!}
                                </p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <a href="${item.indexUrl!}" class="text-primary small text-decoration-none">
                                        查看详情 <i class="bi bi-arrow-right small"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="card-footer bg-transparent border-top pt-2 pb-2 px-3">
                                <div class="d-flex justify-content-between align-items-center small text-muted">
                                    <span>${item.author!}</span>
                                    <span><i class="bi bi-eye me-1"></i> ${item.pv}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                [/#list]
                [/#if]
            </div>
            <!-- 分页器 -->
            <nav aria-label="分页">
                <ul class="pagination justify-content-center custom-pagination">
                    [#if contentPage??]
                        [#if contentPage.hasPreviousPage]
                            <li class="page-item">
                                <a class="page-link" href="${cmsTool.appendPageParamsToUrl(page.currentFullUrl, contentPage.previousPageNo, contentPage.pageSize)}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        [#else]
                            <li class="page-item disabled">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        [/#if]

                        [#if contentPage.pageItems??]
                            [#list contentPage.pageItems as pageItem]
                                [#if pageItem.isEllipsis]
                                    <li class="page-item disabled">
                                        <span class="page-link">...</span>
                                    </li>
                                [#else]
                                    <li class="page-item [#if pageItem.isCurrent]active[/#if]">
                                        <a class="page-link" href="${cmsTool.appendPageParamsToUrl(page.currentFullUrl, pageItem.pageNo, contentPage.pageSize)}">${pageItem.pageNo}</a>
                                    </li>
                                [/#if]
                            [/#list]
                        [/#if]

                        [#if contentPage.hasNextPage]
                            <li class="page-item">
                                <a class="page-link" href="${cmsTool.appendPageParamsToUrl(page.currentFullUrl, contentPage.nextPageNo, contentPage.pageSize)}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        [#else]
                            <li class="page-item disabled">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        [/#if]
                    [/#if]
                </ul>
            </nav>
        </div>
        <!-- 右侧侧边栏 -->
        <div class="col-lg-3">
            <!-- 搜索框 -->
            <div class="sidebar-widget">
                <h5 class="widget-title">站内搜索</h5>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="关键词搜索">
                    <button class="btn custom-btn-primary" type="button">
                        <i class="bi bi-search"></i>
                    </button>
                </div>
            </div>
            <!-- 热门 -->
            <div class="sidebar-widget">
                <h5 class="widget-title">热门</h5>
                <div class="sidebar-list">
                    [@cms_content_list siteId=site.id isPage="true" pageSize=6;item,index]
                        <div class="sidebar-list-item">
                            <a href="${item.indexUrl!}" class="d-block">
                                <div class="fw-medium">${item.title!}</div>
                                <small class="text-muted">[#if item.publicAt??]${(item.publicAt)?substring(0, 10)}[/#if]</small>
                            </a>
                        </div>
                    [/@]
                </div>
            </div>
            <!-- 研究部门 -->
            <div class="sidebar-widget">
                <h5 class="widget-title">研究部门</h5>
                <div class="sidebar-list">
                    <div class="sidebar-list-item">
                        <a href="#" class="d-block">
                            <i class="bi bi-building me-2"></i>宏观经济研究部
                        </a>
                    </div>
                    <div class="sidebar-list-item">
                        <a href="#" class="d-block">
                            <i class="bi bi-lightbulb me-2"></i>创新发展研究部
                        </a>
                    </div>
                    <div class="sidebar-list-item">
                        <a href="#" class="d-block">
                            <i class="bi bi-globe me-2"></i>国际合作局
                        </a>
                    </div>
                    <div class="sidebar-list-item">
                        <a href="#" class="d-block">
                            <i class="bi bi-map me-2"></i>区域发展战略研究中心
                        </a>
                    </div>
                </div>
            </div>
            <!-- 下载专区 -->
<!--            <div class="sidebar-widget">
                <h5 class="widget-title">下载专区</h5>
                <div class="sidebar-list">
                    <div class="sidebar-list-item">
                        <a href="#" class="d-flex justify-content-between align-items-center">
                                <span>
                                    <i class="bi bi-file-pdf text-danger me-2"></i>
                                    2023研究报告合集
                                </span>
                            <small class="text-muted">PDF 15MB</small>
                        </a>
                    </div>
                    <div class="sidebar-list-item">
                        <a href="#" class="d-flex justify-content-between align-items-center">
                                <span>
                                    <i class="bi bi-file-excel text-success me-2"></i>
                                    宏观经济数据手册
                                </span>
                            <small class="text-muted">Excel 8MB</small>
                        </a>
                    </div>
                </div>
            </div>-->
        </div>
    </div>
</main>
[#include "../include/global_footer.ftlh"/]

[#include "../include/global_js_in_foot.ftlh"/]

<!-- 页面交互脚本 -->
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // 筛选标签点击事件
        const filterBadges = document.querySelectorAll('.filter-badge');
        filterBadges.forEach(badge => {
            badge.addEventListener('click', function() {
                // 移除所有active类
                filterBadges.forEach(b => b.classList.remove('active'));
                // 为当前点击的标签添加active类
                this.classList.add('active');

                // 这里可以添加筛选内容的逻辑
                console.log('筛选：' + this.textContent);
            });
        });
    });
</script>
</body>
</html>
