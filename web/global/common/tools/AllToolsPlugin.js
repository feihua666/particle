import * as ArrayTools from './ArrayTools.js'
import * as CommonTools from './CommonTools.js'
import * as DynamicFormTools from './DynamicFormTools.js'
import * as FileTools from './FileTools.js'
import * as FunctionTools from './FunctionTools.js'
import * as MathTools from './MathTools.js'
import * as ObjectTools from './ObjectTools.js'
import * as RegTools from './RegTools.js'
import * as StorageTools from './StorageTools.js'
import * as StringTools from './StringTools.js'
import * as StyleTools from './StyleTools.js'
import * as TimerExcTools from './TimerExcTools.js'
import * as UrlTools from './UrlTools.js'
import * as DateTools from './DateTools.js'
import * as PromiseTools from './PromiseTools.jss'

export default {
    install: function (app, options) {
        // 添加实例方法
        app.config.globalProperties.ptArrayTools = ArrayTools
        app.config.globalProperties.ptCommonTools = CommonTools
        app.config.globalProperties.ptDynamicFormTools = DynamicFormTools
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

    }
}