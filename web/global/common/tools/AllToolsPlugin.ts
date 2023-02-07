import * as ArrayTools from './ArrayTools'
import * as CommonTools from './CommonTools'
import * as DocumentTools from './DocumentTools'
import * as FileTools from './FileTools'
import * as FunctionTools from './FunctionTools'
import * as MathTools from './MathTools'
import * as ObjectTools from './ObjectTools'
import * as RegTools from './RegTools'
import * as StorageTools from './StorageTools'
import * as StringTools from './StringTools'
import * as StyleTools from './StyleTools'
import * as TimerExcTools from './TimerExcTools'
import * as UrlTools from './UrlTools'
import * as DateTools from './DateTools'
import * as PromiseTools from './PromiseTools'
import CompAdapter from '../CompAdapter.vue'
let prefix = "Pt"
let map = {
    CompAdapter,
}
export default {
    install: function (app, options) {
        // 添加实例方法
        app.config.globalProperties.ptArrayTools = ArrayTools
        app.config.globalProperties.ptCommonTools = CommonTools
        app.config.globalProperties.ptDocumentTools = DocumentTools
        app.config.globalProperties.ptFileTools = FileTools
        app.config.globalProperties.ptFunctionTools = FunctionTools
        app.config.globalProperties.ptMathTools = MathTools
        app.config.globalProperties.ptObjectTools = ObjectTools
        app.config.globalProperties.ptRegTools = RegTools
        app.config.globalProperties.ptStorageTools = StorageTools
        app.config.globalProperties.ptStringTools = StringTools
        app.config.globalProperties.ptStyleTools = StyleTools
        app.config.globalProperties.ptTimerExcTools = TimerExcTools
        app.config.globalProperties.ptUrlTools = UrlTools
        app.config.globalProperties.ptDateTools = DateTools
        app.config.globalProperties.ptPromiseTools = PromiseTools
        for (let mapKey in map) {
            app.component(prefix + mapKey,map[mapKey])
        }
    }
}