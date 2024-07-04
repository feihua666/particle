import {underlineToHump, upperFirst} from "../../../../../../../global/common/tools/StringTools";
import {detail as segmentGenDetailApi} from "../../../../api/generator/admin/lowcodeSegmentGenAdminApi";
import {hasOwnProps} from "../../../../../../../global/common/tools/ObjectTools";

export const defaultParentPackage = 'com.particle'
const setGlobalKeyValue = (form,key,value) => {

    let globalJson = {}
    try {
        globalJson = JSON.parse(form.global)
    }catch (e){
    }
    globalJson[key] = value
    form.global = (JSON.stringify(globalJson,(key,value)=>value,'  '))
}

/**
 * 引用生成中的渲染数据
 * @param data
 */
const loadRefrenceSegmentGen = (data,form) =>{


    // 字段数据
    form.outputFileParentAbsoluteDir = data.outputFileParentAbsoluteDir
    form.ext = (data.extJson)
    let globalJson = JSON.parse(data.globalJson)
    form.packageModule && (globalJson.packageModule = form.packageModule)
    form.global = JSON.stringify(globalJson,null,'  ')


    // json数据
    globalJson = JSON.parse(data.globalJson)
    for (let globalJsonKey in globalJson) {
        if(hasOwnProps(form,globalJsonKey)){
            form[globalJsonKey] = globalJson[globalJsonKey]
        }
    }

    // javaPackageKeys 数据
    let javaPackageKeys = form.javaPackageKeys || []

    if(data.javaPackageKeys){
        data.javaPackageKeys.split(',').forEach(item => {
            if (!javaPackageKeys.some(it => it == item)) {
                javaPackageKeys.push(item)
            }
        })
    }
    form.javaPackageKeys = javaPackageKeys
}
export const genericFormComps = [
    {
        field: {
            name: 'javaPackageKeys',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: 'java包键名',
                tips: '仅支持 global和ext键表转换，用于将形如：com.particle的包名转换为路径 com/particle,多个以逗号分隔',
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'outputFileParentAbsoluteDir',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '输出文件的父目录绝对路径',
                tips: '一般为组件的上一级目录，如：/user/yw/test 或 C:\\yw\\test。引用的模板有输出文件或目录时需要填写',
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'global',
            value: JSON.stringify({parentPackage: defaultParentPackage})
        },
        element: {
            comp: 'PtAceEditor',
            formItemProps: {
                label: '全局渲染数据',
            },
            compProps: {
                clearable: true,
                mode: "ace/mode/json",
                class: 'pt-width-100-pc'
            }
        }
    },
    {
        field: {
            name: 'ext',
            value: '{}'
        },
        element: {
            comp: 'PtAceEditor',
            formItemProps: {
                label: '扩展渲染数据',
            },
            compProps: {
                clearable: true,
                mode: "ace/mode/json",
                class: 'pt-width-100-pc'

            }
        }
    },

]

export const useModuleFormComps = ({props}) => {
    return [
        {
            field: {
                name: 'moduleName',
                valueChange:({form,formData,newValue})=>{
                    setGlobalKeyValue(form,'moduleName',newValue)
                    if(newValue){
                        let newValueTemp = underlineToHump(newValue.toLowerCase(),['-'])

                        form.moduleNamePackage = newValueTemp.toLowerCase()
                        form.moduleNameEntity = upperFirst(newValueTemp)
                    }else {
                        form.moduleNamePackage = ''
                        form.moduleNameEntity = ''
                    }
                }
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '模块名称',
                    tips: '如：user 或 particle-demo，请不要使用大写',
                    labelTips: '该值会自动放到全局变量中',
                    required: true
                },
                compProps: ({form}) => {
                    return {
                        clearable: true,

                        slots: {
                            append: {
                                is: 'PtButton',
                                attrs: {
                                    icon: 'Search',
                                    beforeMethod:()=>{
                                        if(props.refrenceSegmentGenId > 0 || props.isGenerated){
                                            return true
                                        }
                                        return '未引用生成数据或尚未生成，请先引用生成数据或生成'
                                    },
                                    method:()=> {
                                        console.log(props.isGenerated)
                                        return segmentGenDetailApi({id: (props.isGenerated ? props.lowcodeSegmentGenId : props.refrenceSegmentGenId)}).then(res => {
                                            let data = res.data.data
                                            loadRefrenceSegmentGen(data,form)
                                            return  Promise.resolve(res)
                                        })
                                    },
                                    title: '加载引用生成数据,如果已经生成则加载上次生成的填写数据'
                                }
                            }
                        }
                    }
                }
            }
        },
        {
            field: {
                name: 'author',
                valueChange:({form,formData,newValue})=>{
                    setGlobalKeyValue(form,'author',newValue)
                }
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '作者',
                    tips: '如：yw',
                    labelTips: '该值会自动放到全局变量中',
                    required: true
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'moduleNamePackage',
                valueChange:({form,formData,newValue})=>{
                    setGlobalKeyValue(form,'moduleNamePackage',newValue)
                }
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '模块名称对应的包名',
                    tips: '如：user 或 particledemo，请不要使用大写',
                    labelTips: '该值会自动放到全局变量中',
                    required: true
                },
                compProps: {
                    clearable: true,

                }
            }
        },
        {
            field: {
                name: 'parentPackage',
                value: defaultParentPackage,
                valueChange:({form,formData,newValue})=>{
                    setGlobalKeyValue(form,'parentPackage',newValue)
                }
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '父包',
                    tips: '如：com.particle，请不要使用大写',
                    labelTips: '该值会自动放到全局变量中',
                    required: true
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'moduleNameEntity',
                valueChange:({form,formData,newValue})=>{
                    setGlobalKeyValue(form,'moduleNameEntity',newValue)
                }
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '模块名称实体化',
                    tips: '保证首字母大写且符合java文件命名',
                    labelTips: '该值会自动放到全局变量中',
                    required: true
                },
                compProps: {
                    clearable: true,
                }
            }
        },

        {
            field: {
                name: 'emptyPtDivider',
            },
            element: {
                comp: 'PtDivider',
                formItemProps: {
                    label: '',
                },
                compProps: {
                    content: '以上为辅助输入，最终以下面json为提交数据',
                    class: 'pt-width-100-pc'
                }
            }
        },
        ...genericFormComps

    ]
}

export const useModelFormComps = ({props}) => {
    return [
        {
            field: {
                name: 'packageModule',
                valueChange:({form,formData,newValue})=>{
                    setGlobalKeyValue(form,'packageModule',newValue)
                }
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '子包名称',
                    tips: '如：user ，请全部使用小写不要使用分隔符，符合java 包命令规则',
                    labelTips: '该值会自动放到全局变量中',
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        ...useModuleFormComps({props})
    ]
}