import { createApp } from 'vue'
import App from './App.vue'
import Varlet, { StyleProvider, Themes } from '@varlet/ui'
import '@varlet/touch-emulator'
import '@varlet/ui/es/style'

// 创建 host
const host = document.createElement('div')
host.className = 'particle'
document.body.appendChild(host)

// shadow root
const shadow = host.attachShadow({ mode: 'open' })


// app 容器
const appRoot = document.createElement('div')
shadow.appendChild(appRoot)

// theme
const customTheme = {
    ...Themes.md3Light
}
StyleProvider(customTheme)
// mount
createApp(App)
    .use(Varlet)
    .mount(appRoot)
