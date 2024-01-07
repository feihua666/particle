<script setup name="OutParamTransConfig" lang="ts">
import {onMounted, reactive, ref,nextTick} from 'vue'
import { clone} from "../../../../../../../global/common/tools/ObjectTools";
import {paramType} from "../dataQueryDatasourceApiManage";
import { v4 as uuidv4 } from 'uuid';
import {ElMessage} from 'element-plus'
import {underlineToHump, upperFirst} from "../../../../../../../global/common/tools/StringTools";

let alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}
const selfFormRef = ref(null)
/**
 * 翻译配置项
 * 和后端 {@link com.particle.global.light.share.trans.anno.TransItem} 基本一致
 */
interface TransItem{
  // 类型,翻译实现支持的类型
  type: string,
  // 需要翻译的字段名称
  byFieldName: string,
  // 需要被翻译的字段名称
  forFieldName:string,
  // 当翻译结果是一个对象时，可以使用该字段取对象的一个属性值
  mapValueField: string,
  // 如果是集合是否转为字符串拼接，仅支持字符串字段
  isJoin: boolean,
  // 当翻译结果是一个集合时，可以使用的分隔符
  mapJoinSeparator: string
  // 当值存在时不翻译
  isNotTransWhenExist: boolean,
  // 因为该翻译配置仅支持以字典配置为数据，所以这里添加加两个字段以提示字典数据，和翻译结果对应的key字段为唯一标识
  // 使用的结果key作为唯一标识
  mapKeyField: string,
  // 字典组编码
  dicGroupCode: string
}
// 字典配置对象
// props.initJsonStr 需要符合该类型
interface InitJson{
  transItems: TransItem[]
}


const tableRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 初始化数据
  initJsonStr: {
    type: String
  },

})
// 属性
const reactiveData = reactive({
  initJson: {transItems: []},
  // 表单初始查询第一页
  form: {
    id: ''
  },
  tableColumns: [
    {
      prop: 'type',
      label: '翻译类型',
    },
    {
      prop: 'byFieldName',
      label: '翻译字段名',
    },
    {
      prop: 'forFieldName',
      label: '被翻译字段名',
    },
    {
      prop: 'mapValueField',
      label: '映射字段名',
    },
    {
      prop: 'isNotTransWhenExist',
      label: '是否覆盖翻译目标值',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '当值存在时不覆盖' : '当值存在时覆盖'
      }
    },
    {
      prop: 'mapKeyField',
      label: '映射key字段名',
    },
    {
      prop: 'dicGroupCode',
      label: '字典组编码',
    },
  ],


})
const formComps = [
  {
    field: {
      name: 'type',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '翻译类型',
        tips: '类型,翻译实现支持的类型,不了解不要填写，默认使用字典配置翻译，如果需要其它翻译才能填写,数据源接口填写无效为避免引起相互调用暂不开放'
      },
      compProps: {
        clearable: true,
        placeholder: '如：user',
      }
    }
  },
  {
    field: {
      name: 'byFieldName',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '翻译字段名',
        tips: '需要翻译的字段名称',
        required: true,
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'forFieldName',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '被翻译字段名',
        tips: '需要被翻译的字段名称',
        required: true,
      },
      compProps: {
        clearable: true,
      }
    }
  },

  {
    field: {
      name: 'mapValueField',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '映射字段名',
        required: true,
        tips: '一般需要填写，不填写默认为name,当翻译结果是一个对象时，可以使用该字段取对象的一个属性值'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'isNotTransWhenExist',
      value: true
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否覆盖翻译目标值',
        tips: '当翻译结果是一个对象时，可以使用该字段取对象的一个属性值'
      },
      compProps: {
        activeText: '当值存在时不覆盖',
        inactiveText: '当值存在时覆盖',
      }
    }
  },
  {
    field: {
      name: 'mapKeyField',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '映射key字段名',
        required: true,
        tips: '一般需要填写，不填写在使用字典配置时默认为value,使用的结果key作为唯一标识'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'dicGroupCode',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '字典组编码',
        required: ({form})=> !form.type,
        tips: '不填写类型时必填，在字典配置中的字典组编码，翻译结果将取该字典组下的字典项作为翻译结果'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'datasourceApiCode',
      valueChange: ({form,formData,newValue})=>{
        if(newValue){
          form.type = "trans_datasourceapi_by_code:" + newValue + ":" + form.mapKeyField
        }else {
          form.type = ''
        }
      }
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '数据源接口编码',
        tips: '如果使用数据源接口作为翻译数据来源，可以借此辅助输入类型'
      },
      compProps: {
        clearable: true,
      }
    }
  },
]
onMounted(()=>{
  // 挂载后初始化数据
  if(props.initJsonStr){
    reactiveData.initJson.transItems = JSON.parse(props.initJsonStr).transItems
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '保存翻译配置',
})
// 添加字典配置按钮
const submitMethod = ():void => {
  let isAdd = !reactiveData.form.id

  if (isAdd) {
    add()
  }else {
    update()
  }

}

const add = ()=>{

  let obj = {
    // 生成一个唯一id
    id: uuidv4()
  }
  formFillItem(reactiveData.form, obj)
  let container = reactiveData.initJson.transItems
  container.push(obj)
}
const update = ()=>{
  let item = getById(reactiveData.form.id,reactiveData.initJson.transItems)
  formFillItem(reactiveData.form, item)
}
const formFillItem = (form,item)=>{
  item.type = form.type
  item.byFieldName = form.byFieldName
  item.forFieldName = form.forFieldName
  item.mapValueField = form.mapValueField
  item.isNotTransWhenExist = form.isNotTransWhenExist
  item.mapKeyField = form.mapKeyField
  item.dicGroupCode = form.dicGroupCode
}
const getById = (id,array=[])=>{
  for (let i = 0; i < array.length; i++) {
    let item = array[i]
    if(item.id == id){
      return item
    }
  }
  return null
}
const removeById = (id,array=[])=>{
  for (let i = 0; i < array.length; i++) {
    let item = array[i]
    if(item.id == id){
      array.splice(i,1)
      return
    }
  }
}

// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let tableRowButtons: any[] = [
    {
      txt: '修改',
      text: true,
      // 删除操作
      method(){
        let item = row
        resetForm()
        nextTick(()=>{

          reactiveData.form.id = item.id
          // 反向填充
          formFillItem(item,reactiveData.form)
        })
      }
    },
    {
      txt: '删除',
      text: true,
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        removeById(row.id,reactiveData.initJson.transItems)
      }
    }
  ]

  return tableRowButtons
}

const resetForm = ()=> {
  selfFormRef.value?.resetForm()
}
// 暴露方法
defineExpose({
  getInitJson: () => reactiveData.initJson
})
</script>
<template>
  <!-- 添加表单 -->
  <PtForm ref="selfFormRef" :form="reactiveData.form" class="pt-wdith-100-pc"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="formComps">
  </PtForm>

  <PtTable ref="tableRef"
           :options="reactiveData.initJson.transItems"
           :columns="reactiveData.tableColumns" default-expand-all>

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})">
          </PtButtonGroup>
        </template>
      </el-table-column>
    </template>
  </PtTable>
</template>


<style scoped>

</style>