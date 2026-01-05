import AllToolsPlugin from "./tools/AllToolsPlugin.ts"
import G6Graph from "./g6/G6Graph.vue";

let prefix = "Pt"
let map = {
    G6Graph,
}
export default {
    install: function (app, options) {
        AllToolsPlugin.install(app,options)
        for (let mapKey in map) {
            app.component(prefix + mapKey,map[mapKey])
        }
    }
}
