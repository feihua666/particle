<template>
    <div :style="{height:height,width:width}">
    </div>
</template>

<script>
    // 官方示例 http://lbsyun.baidu.com/jsdemo.htm#a1_2
    export default {
        name: 'PtBaiduMap',
        props: {
            width: {
                type: String,
                default: '100%'
            },
            height: {
                type: String,
                default: '300px'
            },
            initZoom: {
                type: Number,
                default: 14
            },
            // 地图初始坐标
            // 类型为数组[116.404, 39.915]
            initPoint: {
                type: Array,
                default: function () {
                    return [116.404, 39.915]
                }
            },
            // 地图点击事件
            mClick: {
                type: Function,
                default: function () {

                }
            },
            // 是否组件加载完毕自动初始化地图
            initAuto: {
                type: Boolean,
                default: true
            },
            // 百度申请的key
            ak: {
                type: String,
                default: '69a5a1e7260cd2cd298c33666e436530'
            }
        },
        data() {
            return {
                map: null
            }
        },
        mounted() {
            this.bMapScript(this.ak).then( (BMap)=> {
                if (this.initAuto === true) {
                    this.init(BMap)
                } else {
                    this.$emit('ready',this.map)
                }
            })
        },
        methods: {
            init(BMap) {
                if (this.map) {
                    return
                }
                this.map = new BMap.Map(this.$el)
                let point = this.newPoint(this.initPoint[0], this.initPoint[1])
                this.centerAndZoom(point, this.initZoom)
                let top_left_control = new BMap.ScaleControl({anchor: window.BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
                let top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
                this.map.addControl(top_left_control);
                this.map.addControl(top_left_navigation);
                this.map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
                this.map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
                this.bindClick()
                this.$emit('ready', this.map)
            },
            bMapScript(ak) {
                // 感谢vue-baidu-map项目
                if (!window.BMap) {
                    window.BMap = {}
                    window.BMap._preloader = new Promise((resolve, reject) => {
                        window._initBaiduMap = function () {
                            resolve(window.BMap)
                            window.document.body.removeChild($script)
                            window.BMap._preloader = null
                            window._initBaiduMap = null
                        }
                        const $script = document.createElement('script')
                        window.document.body.appendChild($script)
                        $script.src = `https://api.map.baidu.com/api?v=2.0&ak=${ak}&callback=_initBaiduMap`
                    })
                    return window.BMap._preloader
                } else if (!window.BMap._preloader) {
                    return Promise.resolve(window.BMap)
                } else {
                    return window.BMap._preloader
                }
            },
            getMapIns() {
                return this.map
            },
            // 地图初始化完成后调用
            bindClick() {
                this.map.addEventListener('click', (e) => {
                    this.mClick(e)
                    this.$emit('mClick', e)
                })
            },
            centerAndZoom(point, zoom) {
                this.map.centerAndZoom(point, zoom ? zoom : this.map.getZoom());
            },
            // 新建一个点
            newPoint(longitude, latitude) {
                let point = new window.BMap.Point(longitude, latitude)
                return point
            },
            // 新建一个geo定位器
            newGeocoder() {
                let geo = new window.BMap.Geocoder()
                return geo
            },
            // 新建一个标注
            addMarker(point) {
                let marker = new window.BMap.Marker(point)
                this.map.addOverlay(marker);
            },
            getPoint(address, callback) {
                // 创建地址解析器实例
                let myGeo = this.newGeocoder()
                // 将地址解析结果显示在地图上,并调整地图视野
                myGeo.getPoint(address, (point) => {
                    callback(point)
                })
            },
            getAddress(point, callback) {
                // 创建地址解析器实例
                let myGeo = this.newGeocoder()
                myGeo.getLocation(point, (rs) => {
                    let addComp = rs.addressComponents
                    let addr = []
                    addr.push(addComp.province)
                    addr.push(addComp.city)
                    addr.push(addComp.street)
                    addr.push(addComp.streetNumber)
                    callback(addr, rs)
                })
            },
            // 地址解析并标注
            addressMarker(address) {
                this.getPoint(address, (point) => {
                    if (point) {
                        this.centerAndZoom(point)
                        this.addMarker(point)
                    } else {
                        this.$message.error("您选择地址没有解析到结果!");
                    }
                })
            },
            clearOverlays() {
                this.map.clearOverlays()
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
