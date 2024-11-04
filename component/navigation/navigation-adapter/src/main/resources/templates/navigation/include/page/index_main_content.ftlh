<div class="main-content flex-fill">
    <#include "index_header_banner.ftlh"/>
    <#include "index_header_search.ftlh"/>


    <div id="content" class="content-site customize-site">
        <#-- 先从 Map 中取出 List -->
        <#list rootCategoryDOS as item>
        <#assign childrenCategoryDOS=notRootCategoryParentIdGroupByMap?api.get(item.id)![]/>
        <#assign hasChildren=childrenCategoryDOS?? && childrenCategoryDOS?has_content/>
        <div class="d-flex flex-fill">
            <h4 class="text-gray text-lg mb-4">
                <i class="fas ${item.icon!""} fa-lg fa-lg icon-fw icon-lg mr-2" id="c${item.id?string("0")}"></i>
                ${item.name}
            </h4>
        </div>
        <#-- 根类下面的站点 -->
        <#assign siteDOS=categoryIdGroupBySiteMap?api.get(item.id)![]/>
        <#assign hasSiteDOS=siteDOS?? && siteDOS?has_content/>
            <#if hasSiteDOS>
                <div class="row">
                    <#list siteDOS as siteItem>
                        <#assign siteItem = siteItem/>
                        <#include "url_card.ftlh"/>
                    </#list>
                </div>
            </#if>

        <#-- 二级类别 -->
        <#if hasChildren>
        <div class="d-flex flex-fill flex-tab mb-4">
            <div class="overflow-x-auto">
                <div class="slider_menu mini_tab ajax-list-home" slidertab="sliderTab" data-id="16">
                    <ul class="nav nav-pills menu">
                        <#list childrenCategoryDOS as childItem>
                        <li class="pagenumber nav-item">
                            <a href="" id="c${childItem.id?string("0")}" data-id="${childItem.id?string("0")}" data-pid="${childItem.parentId?string("0")}" class="nav-link <#if childItem_index == 0>active</#if>">
                                <#if childItem.icon??>
                                <i class="fas ${childItem.icon!""} fa-lg icon-fw icon-lg mr-2"></i>
                                </#if>
                            <span>${childItem.name}</span>
                            </a>
                        </li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>

        <#list childrenCategoryDOS as childItem>
            <#assign siteDOS=categoryIdGroupBySiteMap?api.get(childItem.id)![]/>
            <#assign hasSiteDOS=siteDOS?? && siteDOS?has_content/>
            <div class="tab-content">
                <div class="tab-pane <#if childItem_index == 0>active</#if>">
                    <div class="row ajax-${childItem.id?string("0")} rc-${childItem.parentId?string("0")}">
                        <#list siteDOS as siteItem>
                            <#assign siteItem = siteItem/>
                            <#include "url_card.ftlh"/>
                        </#list>
                    </div>
                </div>
            </div>
        </#list>
        </#if>
        </#list>



        <h4 class="text-gray text-lg mb-4">
            <i class="iconfont icon-book-mark-line icon-lg mr-2" id="friendlink"></i>友情链接
        </h4>
        <div class="friendlink text-xs card">
            <div class="card-body">

                <#list navigationFriendshipLinkDOS as item>
                <a href="${item.url!""}" title="${item.name!""}" target="_blank">${item.name!""}</a>
                </#list>

            </div>
        </div>
    </div>

    <#include "../global_footer.ftlh"/>
</div>
