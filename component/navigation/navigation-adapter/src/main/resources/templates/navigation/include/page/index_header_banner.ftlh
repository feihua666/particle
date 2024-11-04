<div class="big-header-banner">
    <div id="header" class="page-header sticky">
        <div class="navbar navbar-expand-md">
            <div class="container-fluid p-0">

                <div class="collapse navbar-collapse order-2 order-md-1">
                    <div class="header-mini-btn">
                        <label>
                            <input id="mini-button" type="checkbox">
                            <svg viewbox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                                <path class="line--1" d="M0 40h62c18 0 18-20-17 5L31 55"></path>
                                <path class="line--2" d="M0 50h80"></path>
                                <path class="line--3" d="M0 60h62c18 0 18 20-17-5L31 45"></path>
                            </svg>
                        </label>

                    </div>

                    <ul class="navbar-nav site-menu" style="margin-right: 16px;">

                        <li >
                            <a href="${frontIndexAbsolutePath!""}">
                                <i class="fa fa-home fa-lg mr-2"></i>
                                <span>首页</span>
                            </a>
                            <ul class="sub-menu">

                            </ul>
                        </li>

                    </ul>


                </div>

                <ul class="nav navbar-menu text-xs order-1 order-md-2">


                    <li class="nav-item mr-3 mr-lg-0 d-none d-lg-block">
                        <script>
                            fetch('https://v1.hitokoto.cn')
                                .then(response => response.json())
                                .then(data => {
                                    const hitokoto = document.getElementById('hitokoto_text')
                                    hitokoto.href = 'https://hitokoto.cn/?uuid=' + data.uuid
                                    hitokoto.innerText = data.hitokoto
                                })
                                .catch(console.error)
                        </script>
                        <div id="hitokoto"><a href="#" target="_blank" id="hitokoto_text">疏影横斜水清浅，暗香浮动月黄昏。</a></div>
                    </li>


                    <li class="nav-search ml-3 ml-md-4">
                        <a href="javascript:" data-toggle="modal" data-target="#search-modal"><i
                                    class="iconfont icon-search icon-2x"></i></a>
                    </li>
                    <li class="nav-item d-md-none mobile-menu ml-3 ml-md-4">
                        <a href="javascript:" id="sidebar-switch" data-toggle="modal"
                           data-target="#sidebar"><i class="iconfont icon-classification icon-2x"></i></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="placeholder" style="height:74px"></div>
</div>
