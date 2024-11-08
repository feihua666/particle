<div class="url-card col-6  col-sm-6 col-md-4 col-xl-5a col-xxl-6a">
    <div class="url-body max">

        <a href="${frontFinalAbsoluteSubContextPath!""}${frontDetailPath!""}/${siteItem.id?string("0")}.html" target="_blank"
           class="card no-c mb-4" data-toggle="tooltip" data-placement="bottom" data-original-title="${siteItem.title!""}">

            <div class="card-body">
                <div class="url-content d-flex align-items-center">
                    <div class="url-img mr-2 d-flex align-items-center justify-content-center">
                        <img class="lazy" src="${siteItem.logoUrl!""}" data-src="${siteItem.logoUrl!""}"
                             onerror="javascript:this.src='${frontFinalAbsoluteSubContextPath!""}/assets/images/default-logo.png'" alt="${siteItem.name!""}">
                    </div>
                    <div class="url-info flex-fill">
                        <div class="text-sm overflowClip_1">
                            <strong>${siteItem.name!""}</strong>
                        </div>
                        <p class="overflowClip_1 m-0 text-muted text-xs"><#if siteItem.title??>${siteItem.title!""}</br></#if>${siteItem.content!""}</p>
                    </div>
                </div>
                <div class="url-goto-after mt-2">
                </div>
            </div>
        </a>

        <#assign navigationSiteTagDOS=siteIdGroupBySiteTagMap?api.get(siteItem.id)![]/>
        <#assign hasSiteTag=navigationSiteTagDOS?? && navigationSiteTagDOS?has_content/>
        <div class="url-goto px-3 pb-1">
            <div class="d-flex align-items-center" style="white-space:nowrap">
                <div class="tga text-xs py-1">
                    <span class="mr-1">
                        <#list navigationSiteTagDOS as siteTagItem>
                            <a rel="tag">${siteTagItem.name!""}</a>
                        </#list>
                    </span>
                </div>
                <a href="${siteItem.url!""}" target="_blank" rel="external nofollow noopener" title="" class="togo text-center text-muted is-views" data-toggle="tooltip" data-placement="right" data-original-title="直达">
                    <i class="iconfont icon-goto"></i>
                </a>
            </div>
        </div>
    </div>

</div>
