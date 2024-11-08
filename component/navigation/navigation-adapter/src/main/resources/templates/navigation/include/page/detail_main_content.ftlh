<div class="main-content flex-fill">
    <#include "index_header_banner.ftlh"/>


    <div id="content" class="container my-4 my-md-5">
        <div class="row site-content py-4 py-md-5 mb-xl-5 mb-0 mx-xxl-n5">
            <div class="background-fx">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-01.svg" class="shape-01">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-02.svg" class="shape-02">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-03.svg" class="shape-03">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-04.svg" class="shape-04">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-05.svg" class="shape-05">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-06.svg" class="shape-06">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-07.svg" class="shape-07">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-08.svg" class="shape-08">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-09.svg" class="shape-09">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-10.svg" class="shape-10">
                <img src="${frontFinalAbsoluteSubContextPath!""}/assets/images/fx/shape-11.svg" class="shape-11">
            </div>                <!-- 网址信息 -->
            <div class="col-12 col-sm-5 col-md-4 col-lg-3">
                <div class="siteico">
                    <div class="blur blur-layer"
                         style="background: transparent url(${siteDO.logoUrl!""}) no-repeat center center;-webkit-background-size: cover;-moz-background-size: cover;-o-background-size: cover;background-size: cover;animation: rotate 30s linear infinite;"></div>
                    <img class="img-cover" src="${siteDO.logoUrl!""}" alt="${siteDO.name!""}" title="${siteDO.name!""}">
                </div>
            </div>
            <div class="col mt-4 mt-sm-0">
                <div class="site-body text-sm">
                    <#list currentSiteCategoryDOS![] as item>
                        <a class="btn-cat custom_btn-d mb-2" href="${frontFinalAbsoluteSubContextPath!""}#c${item.id?string("0")}">${item.name!""}</a>
                    </#list>
                    <#assign navigationSiteTagDOS=siteIdGroupBySiteTagMap?api.get(siteDO.id)![]/>
                    <#assign hasSiteTag=navigationSiteTagDOS?? && navigationSiteTagDOS?has_content/>
                    <#list navigationSiteTagDOS as siteTagItem>
                        <span class="tags">
                            <a rel="tag">${siteTagItem.name!""}</a>
                        </span>
                    </#list>
                    <div class="site-name h3 my-3">${siteDO.name!""}</div>
                    <div class="mt-2">
                        <p class="mb-2">${siteDO.title!""}</p>



                        <div class="site-go mt-3">
                           <span class="site-go-url">
                                <a style="margin-right: 10px;" href="${siteDO.url!""}"
                                   title="${siteDO.name!""}" target="_blank" class="btn btn-arrow">
                                    <span>链接直达<i class="iconfont icon-arrow-r-m"></i></span>
                                </a>
                           </span>
                            <a href="javascript:" class="btn btn-arrow qr-img" data-toggle="tooltip"
                               data-placement="bottom" title="" data-html="true"
                               data-original-title="<img src='https://api.qrserver.com/v1/create-qr-code/?size=200×200&amp;margin=10&amp;data=${siteDO.url!""}' width='150'>">
                                <span>手机查看<i class="iconfont icon-qr-sweep"></i></span>
                            </a>
                        </div>


                    </div>

                </div>
            </div>
            <!-- 网址信息 end -->
        </div>
        <div class="panel site-content card transparent">
            <div class="card-body p-0">
                <div class="apd-bg">
                </div>
                <div class="panel-body my-4 ">
                    ${siteDO.content!""}
                </div>
            </div>
        </div>
        <#assign hasRelatedSiteDOS=currentSiteRelatedSiteDOS?? && currentSiteRelatedSiteDOS?has_content/>
        <#if hasRelatedSiteDOS>
            <h4 class="text-gray text-lg my-4"><i class="site-tag iconfont icon-tag icon-lg mr-1"></i>相关导航</h4>
            <div class="row mb-n4 customize-site">
                <#list currentSiteRelatedSiteDOS![] as siteItem>
                    <#assign siteItem = siteItem/>
                    <#include "url_card.ftlh"/>
                </#list>
            </div>
        </#if>

    </div>

    <#include "../global_footer.ftlh"/>
</div>
