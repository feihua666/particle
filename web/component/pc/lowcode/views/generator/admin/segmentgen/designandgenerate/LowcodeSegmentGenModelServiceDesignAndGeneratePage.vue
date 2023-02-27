<script setup name="LowcodeSegmentGenModelServiceDesignAndGeneratePage" lang="ts">
/**
 * 低代码片段生成设计和渲染页面
 */
import {reactive ,ref} from 'vue'
import {detail as segmentGenDetailApi, renderGen} from "../../../../../api/generator/admin/lowcodeSegmentGenAdminApi.ts"
import {cloneObj, hasOwnProps} from "../../../../../../../../global/common/tools/ObjectTools";

import {ElMessage} from 'element-plus'
import {upperFirst} from "../../../../../../../../global/common/tools/StringTools";
const alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  lowcodeSegmentGenId: {
    type: String
  },
  // 引用生成id，用来加载引用的渲染数据
  refrenceSegmentGenId:{
    type: String
  },
})

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
    lowcodeSegmentGenId: props.lowcodeSegmentGenId,
    javaPackageKeys: ['moduleNamePackage','parentPackage']
  },
  renderResultForm: {
    templateContentResult: '',
    templateNameContentResult: '',
    templateNameContentResultFile: '',
  },
  // 文件系统分隔符
  fileSeparater: '/',
  // 表单数据对象
  formData: {},

  defaultParentPackage: 'com.particle'
})
const setGlobalKeyValue = (form,key,value) => {

  let globalJson = {}
  try {
    globalJson = JSON.parse(form.global)
  }catch (e){
  }
  globalJson[key] = value
  form.global = (JSON.stringify(globalJson,(key,value)=>value,'  '))
}
// 表单项
const formComps = ref(
    [
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
      {
        field: {
          name: 'moduleName',
          valueChange:({form,formData,newValue})=>{
            setGlobalKeyValue(form,'moduleName',newValue)
            if(newValue){
              form.moduleNamePackage = newValue.toLowerCase().replace('-','').replace('_','')
              form.moduleNameEntity = upperFirst(form.moduleNamePackage)
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
          compProps: {
            clearable: true,

            slots: {
              append: {
                is: 'PtButton',
                attrs: {
                  icon: 'Search',
                  beforeMethod:()=>{
                    if(props.refrenceSegmentGenId > 0){
                      return true
                    }
                    return '未引用生成数据'
                  },
                  method:()=> {
                    return segmentGenDetailApi({id: props.refrenceSegmentGenId}).then(res => {
                      let data = res.data.data
                      loadRefrenceSegmentGen(data)
                      return  Promise.resolve(res)
                    })
                  },
                  title: '加载引用生成数据'
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
          value: reactiveData.defaultParentPackage,
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
      {
        field: {
          name: 'outputFileParentAbsoluteDir',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '输出文件的父目录绝对路径',
            tips: '如：/user/yw/test 或 C:\\yw\\test',
            required: true
          },
          compProps: {
            clearable: true,

          }
        }
      },
      {
        field: {
          name: 'global',
          value: JSON.stringify({parentPackage: reactiveData.defaultParentPackage})
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
)
const renderResultFormComps = ref(
    [
      {
        field: {
          name: 'templateNameContentResult'
        },
        element: {
          comp: 'PtAceEditor',
          formItemProps: {
            label: '名称渲染结果文本',
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
          name: 'templateContentResult'
        },
        element: {
          comp: 'PtAceEditor',
          formItemProps: {
            label: '渲染结果文本',
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
          name: 'templateNameContentResultFile'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称渲染结果文件句柄',
          },
          compProps: {
            clearable: true,

          }
        }
      },
    ]
)
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '开始渲染',
  permission: 'admin:web:lowcodeSegmentGen:renderGen',
})
// 提交按钮
const submitMethod = (form) => {
  let formTemp = cloneObj(form)
  if (formTemp.global) {
    formTemp.global = JSON.parse(formTemp.global)
  }
  if (formTemp.ext) {
    formTemp.ext = JSON.parse(formTemp.ext)
  }
  return renderGen(formTemp)
}
const renderGenResultDialogVisible = ref(false)

// 成功提示语
const submitMethodSuccess = (res) => {
  renderGenResultDialogVisible.value = true

  reactiveData.renderResultForm.templateContentResult = res.data.data.templateContentResult
  reactiveData.renderResultForm.templateNameContentResult = res.data.data.templateNameContentResult
  reactiveData.renderResultForm.templateNameContentResultFile = res.data.data.templateNameContentResultFile

  // 这里没有返回数据，直接实现并且延迟，确保在弹窗后再提示，否则可能提示框被dialog弹窗遮挡
  setTimeout(()=>{alert('渲染成功') },300)
}

/**
 * 引用生成中的渲染数据
 * @param data
 */
const loadRefrenceSegmentGen = (data) =>{


  // 字段数据
  reactiveData.form.outputFileParentAbsoluteDir = data.outputFileParentAbsoluteDir
  reactiveData.form.ext = (data.extJson)
  let globalJson = JSON.parse(data.globalJson)
  reactiveData.form.packageModule && (globalJson.packageModule = reactiveData.form.packageModule)
  reactiveData.form.global = JSON.stringify(globalJson,null,'  ')


  // json数据
  globalJson = JSON.parse(data.globalJson)
  for (let globalJsonKey in globalJson) {
    if(hasOwnProps(reactiveData.form,globalJsonKey)){
      reactiveData.form[globalJsonKey] = globalJson[globalJsonKey]
    }
  }

  // javaPackageKeys 数据
  let javaPackageKeys = reactiveData.form.javaPackageKeys || []

  if(data.javaPackageKeys){
    data.javaPackageKeys.split(',').forEach(item => {
      if (!javaPackageKeys.some(it => it == item)) {
        javaPackageKeys.push(item)
      }
    })
  }
  reactiveData.form.javaPackageKeys = javaPackageKeys
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="100"
          :method="submitMethod"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"

          inline
          label-position="top"
          :layout="[1,3,2,1,1,1,1]"
          :comps="formComps">
  </PtForm>

  <el-dialog v-model="renderGenResultDialogVisible" width="70%" title="查看渲染结果数据" append-to-body destroy-on-close>
    <PtForm :form="reactiveData.renderResultForm"
            labelWidth="100"
            defaultButtonsShow=""
            label-position="top"
            :layout="[2,1]"
            :comps="renderResultFormComps">
    </PtForm>
  </el-dialog>
</template>


<style scoped>

</style>