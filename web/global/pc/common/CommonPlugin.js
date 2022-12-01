import '../../common/global.css'
import BaiduMap from './BaiduMap.vue'
import Frame from './Frame.vue'
// import PtTinymceEditor from './tinymceEditor/PtTinymceEditor.vue'
import SlideVerify from './slideVerify/SlideVerify.vue'
import Logo from './Logo.vue'
import LoginTemplate from './template/login/LoginTemplate.vue'
import EntryTemplate from './template/EntryTemplate.vue'
let prefix = "Pt"
let map = {
    BaiduMap,
    Frame,
    SlideVerify,
    Logo,
    LoginTemplate,
    EntryTemplate,
}
export default {
    install: function (app, options) {
        for (let mapKey in map) {
            app.component(prefix + mapKey,map[mapKey])
        }
    }
}