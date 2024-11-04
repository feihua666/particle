<div id="sidebar" class="sticky sidebar-nav fade animate-nav" style="width: 200px">

    <div class="modal-dialog h-100 sidebar-nav-inner">
        <div class="sidebar-logo border-bottom border-color">

            <div class="logo overflow-hidden">
                <a href="" class="logo-expanded">
                    <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/logo.png" height="40" class="logo-light"
                         alt="particle| ai工具网址导航,ai最新产品">
                    <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/logo.png" height="40" class="logo-dark d-none"
                         alt="particle| ai工具网址导航,ai最新产品">
                </a>
                <a href="" class="logo-collapsed">
                    <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/logo.png" height="40" class="logo-light"
                         alt="particle| ai工具网址导航,ai最新产品">
                    <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/logo.png" height="40" class="logo-dark d-none"
                         alt="particle| ai工具网址导航,ai最新产品">
                </a>
            </div>

        </div>
        <div class="sidebar-menu flex-fill">
            <div class="sidebar-scroll">
                <div class="sidebar-menu-inner">
                    <ul>
                        <#-- 先从 Map 中取出 List -->
                    <#list rootCategoryDOS as item>
                        <#assign childrenCategoryDOS=notRootCategoryParentIdGroupByMap?api.get(item.id)![]/>
                        <#assign hasChildren=childrenCategoryDOS?? && childrenCategoryDOS?has_content/>
                        <li class="sidebar-item">
                            <a href="${frontIndexAbsolutePath!""}#c${item.id?string("0")}" class="smooth change-href">
                                <i class="fas ${item.icon!""} fa-lg icon-fw icon-lg mr-2"></i>
                                <span>${item.name}</span>
                                <#if hasChildren>
                                <i class="iconfont icon-arrow-r-m sidebar-more text-sm"></i>
                                </#if>
                            </a>
                            <#if hasChildren>
                            <ul>
                                <#list childrenCategoryDOS as childItem>
                                <li>
                                    <a href="#c${childItem.id?string("0")}" class="smooth">
                                        <#if childItem.icon??>
                                        <i class="fas ${childItem.icon!""} fa-lg icon-fw icon-lg mr-2"></i>
                                        </#if>
                                        <span>${childItem.name}</span>
                                    </a>
                                </li>
                                </#list>
                            </ul>
                            </#if>
                        </li>
                    </#list>
                    </ul>
                </div>
            </div>
        </div>
<#--        <div class="border-top py-2 border-color">
            <div class="flex-bottom">
                <ul>
                    <li id="menu-item-212"
                        class="menu-item menu-item-type-custom menu-item-object-custom menu-item-212 sidebar-item">
                        <a href="#friendlink" class="smooth">
                            <i class="fab fa-staylinked icon-fw icon-lg mr-2"></i>
                            <span>友情链接</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>-->
    </div>
</div>
