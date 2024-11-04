<script type='text/javascript' id='popper-js-extra'>
    var theme = {
        "ajaxurl": "",
        "useAjaxurl": false,
        "addico": "https://nav.baidu.cn/wp-content/themes/onenav/images/add.png",
        "order": "asc",
        "formpostion": "top",
        "defaultclass": "io-grey-mode",
        "isCustomize": "1",
        "icourl": "",
        "icopng": ".png",
        "urlformat": "1",
        "customizemax": "10",
        "newWindow": "0",
        "lazyload": "1",
        "minNav": "1", // 侧栏菜单初始状态设置 1=最小化
        "loading": "1",
        "hotWords": "baidu",
        "classColumns": " col-sm-6 col-md-4 col-xl-5a col-xxl-6a ",
        "apikey": "TWpBeU1UVTNOekk1TWpVMEIvZ1M2bFVIQllUMmxsV1dZelkxQTVPVzB3UW04eldGQmxhM3BNWW14bVNtWk4="
    };
</script>
<script type='text/javascript' src='${frontFinalAbsoluteSubContextPath!""}/assets/js/jquery.ui.touch-punch.min-0.2.2.js' id='jqueryui-touch-js'></script>
<script type='text/javascript' src='${frontFinalAbsoluteSubContextPath!""}/assets/js/clipboard.min-5.6.2.js' id='clipboard-js'></script>
<script type='text/javascript' src='${frontFinalAbsoluteSubContextPath!""}/assets/js/tooltip-extend.js' id='iplaycode-nav-js'></script>
<script type='text/javascript' src='${frontFinalAbsoluteSubContextPath!""}/assets/js/popper.min.js' id='popper-js'></script>
<script type='text/javascript' src='${frontFinalAbsoluteSubContextPath!""}/assets/js/bootstrap.min-4.3.1.js' id='bootstrap-js'></script>
<script type='text/javascript' src='${frontFinalAbsoluteSubContextPath!""}/assets/js/theia-sticky-sidebar-1.5.0.js' id='sidebar-js'></script>
<script type='text/javascript' src='${frontFinalAbsoluteSubContextPath!""}/assets/js/lazyload.min-12.4.0.js' id='lazyload-js'></script>
<script type='text/javascript' src='${frontFinalAbsoluteSubContextPath!""}/assets/js/fancybox.min-3.5.7.js' id='lightbox-js-js'></script>
<script type='text/javascript' src='${frontFinalAbsoluteSubContextPath!""}/assets/js/app-mini.js' id='app-mini-js'></script>

<script type="text/javascript">
    $(document).ready(function(){
        var siteWelcome = $('#loading');
        siteWelcome.addClass('close');
        setTimeout(function() {
            siteWelcome.remove();
        }, 600);
    });
</script>
<script>
    $(document).ready(function(){
        setTimeout(function () {
            if ($('a.smooth[href="' + window.location.hash + '"]')[0]) {
                $('a.smooth[href="' + window.location.hash + '"]').click();
            }else if (window.location.hash != '') {
                $("html, body").animate({
                    scrollTop: $(window.location.hash).offset().top - 90
                }, {
                    duration: 500,
                    easing: "swing"
                });
            }
        }, 300);
        $(document).on('click','a.smooth',function(ev) {
            if($('#sidebar').hasClass('show') && !$(this).hasClass('change-href')){
                $('#sidebar').modal('toggle');
            }
            var self = this;
            var selfHref = $(self).attr("href");
            var selfHrefReal = selfHref.substring(selfHref.indexOf('#'))
            if(selfHrefReal.substring(0, 1) == "#"){
                $("html, body").animate({
                    scrollTop: $(selfHrefReal).offset().top - 90
                }, {
                    duration: 500,
                    easing: "swing"
                });
            }
            if($(this).hasClass('go-search-btn')){
                $('#search-text').focus();
            }
            if(!$(this).hasClass('change-href')){
                var menu =  $("a"+selfHrefReal);
                menu.click();
                toTarget(menu.parent().parent(),true,true);
            }
        });
        $(document).on('click','a.tab-noajax',function(ev) {
            var url = $(this).data('link');
            if(url)
                $(this).parents('.d-flex.flex-fill.flex-tab').children('.btn-move.tab-move').show().attr('href', url);
            else
                $(this).parents('.d-flex.flex-fill.flex-tab').children('.btn-move.tab-move').hide();
        });

    });
</script>

<script>

    (function(){
        if(document.cookie.replace(/(?:(?:^|.*;\s*)night\s*\=\s*([^;]*).*$)|^.*$/, "$1") === ''){
            if(new Date().getHours() > 22 || new Date().getHours() < 6){
                document.body.classList.remove('io-black-mode');
                document.body.classList.add('io-grey-mode');
                document.cookie = "night=1;path=/";
                console.log('夜间模式开启');
            }else{
                document.body.classList.remove('night');
                document.cookie = "night=0;path=/";
                console.log('夜间模式关闭');
            }
        }else{
            var night = document.cookie.replace(/(?:(?:^|.*;\s*)night\s*\=\s*([^;]*).*$)|^.*$/, "$1") || '0';
            if(night == '0'){
                document.body.classList.remove('night');
            }else if(night == '1'){
                document.body.classList.add('night');
            }
        }
    })();

    $("#search-bg").css("background", "linear-gradient(#e2c4c4, #f9f9f9)");
    function switchNightMode(){
        var night = document.cookie.replace(/(?:(?:^|.*;\s*)night\s*\=\s*([^;]*).*$)|^.*$/, "$1") || '0';
        if(night == '0'){
            $("#search-bg").css("background", "linear-gradient(#4f4040, #1b1d1f)");
            document.body.classList.remove('io-grey-mode');
            document.body.classList.add('io-black-mode');
            document.cookie = "night=1;path=/"
            console.log(' 1');
            $(".switch-dark-mode").attr("data-original-title","日间模式");
            $(".mode-ico").removeClass("icon-night");
            $(".mode-ico").addClass("icon-light");
        }else{
            $("#search-bg").css("background", "linear-gradient(#e2c4c4, #f9f9f9)");

            document.body.classList.remove('io-black-mode');
            document.body.classList.add('io-grey-mode');
            document.cookie = "night=0;path=/"
            console.log(' 2');
            $(".switch-dark-mode").attr("data-original-title","夜间模式");
            $(".mode-ico").removeClass("icon-light");
            $(".mode-ico").addClass("icon-night");
        }
    }
</script>


<#--<script>
    var newsContainer = document.getElementById('news-container');
    var newsItems = document.getElementsByClassName('news-item');
    var currentItem = 0;

    setInterval(function() {

        newsItems[currentItem].classList.remove('show');
        newsItems[currentItem].style.transform = 'translateY(-20px)';

        currentItem = (currentItem + 1) % newsItems.length;
        newsItems[currentItem].style.transform = 'translateY(' + (newsContainer.offsetHeight - 20) + 'px)';
        setTimeout(function() {
            newsItems[currentItem].classList.add('show');
        }, 500);
    }, 8000);
</script>-->
