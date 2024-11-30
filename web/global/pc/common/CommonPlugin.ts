import '../../common/global.css'
import BaiduMap from './map/BaiduMap.vue'
import LocationGeoMap from './map/LocationGeoMap.vue'
import Frame from './Frame.vue'
import RouteView from './RouteView.vue'
import AceEditor from './aceEditor/AceEditor.vue'
import TinymceEditor from './tinymceEditor/TinymceEditor.vue'
import SlideVerify from './slideVerify/SlideVerify.vue'
import Logo from './Logo.vue'
import LoginTemplate from './template/login/LoginTemplate.vue'
import LoginPageTemplate from './template/login/LoginPageTemplate.vue'
import EntryTemplate from './template/EntryTemplate.vue'
let prefix = "Pt"
let map = {
    BaiduMap,
    LocationGeoMap,
    Frame,
    SlideVerify,
    Logo,
    LoginTemplate,
    LoginPageTemplate,
    EntryTemplate,
    RouteView,
    AceEditor,
    TinymceEditor,
}
export default {
    install: function (app, options) {
        for (let mapKey in map) {
            app.component(prefix + mapKey,map[mapKey])
        }
    }
}
