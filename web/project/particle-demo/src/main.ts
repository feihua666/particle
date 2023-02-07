import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { v4 as uuidv4 } from 'uuid';
import ace from "ace-builds"
import "ace-builds/src-min-noconflict/theme-eclipse"
import 'ace-builds/src-min-noconflict/mode-javascript'
import 'ace-builds/src-min-noconflict/mode-json'
import 'ace-builds/src-min-noconflict/mode-css'
import 'ace-builds/src-min-noconflict/mode-html'


import App from './App.vue'
import router from './router'

// import './assets/main.css'

// ElementPlus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
// 这里得引入一下，否则在 axiosRequest中报错
import axios from 'axios'
// axios
import axiosInstance from '../../../global/pc/common/axios/axiosRequest'

/****************************** 自定义 开始 *********************************/
// 自定义 element plus 组件
import ElementPlusPlugin from  '../../../global/pc/element-plus/ElementPlusPlugin'
import CommonPlugin from  '../../../global/pc/common/CommonPlugin'
import ComponentPlugin from  '../../../component/pc/ComponentPlugin'

/****************************** 自定义 结束 *********************************/
async function start() {
    const app = createApp(App)

    app.use(createPinia())
    app.use(router)

    // ElementPlus，在引入 Element Plus 时，可以传入一个包含 size 和 zIndex 属性的全局配置对象。 size 用于设置表单组件的默认尺寸，zIndex 用于设置弹出组件的层级，zIndex 的默认值为 2000。
    app.use(ElementPlus, {size: 'small', zIndex: 3000,locale: zhCn})
    for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
        app.component(key, component)
    }

    // axios 挂载全局
    app.config.globalProperties.axios = axiosInstance

    // 自定义
    app.use(ElementPlusPlugin, {})
    app.use(CommonPlugin, {})
    app.use(ComponentPlugin, {})


    app.mount('#app')
}
start()