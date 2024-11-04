<div class="url-card col-6  col-sm-6 col-md-4 col-xl-5a col-xxl-6a">
    <div class="url-body default">

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
            </div>
        </a>

    </div>
</div>
