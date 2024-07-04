<script setup name="InParamDocConfig" lang="ts">
import {onMounted, reactive, ref,nextTick} from 'vue'
import { clone} from "../../../../../../../global/common/tools/ObjectTools";
import {paramType} from "../dataQueryDatasourceApiManage";
import { v4 as uuidv4 } from 'uuid';
import {ElMessage} from 'element-plus'

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
 * 入参文档项
 */
interface InParamDoc{
  // 参数名，文本时可能没有
  name?: string,
  // 参数描述
  description?:string,
  // 是否必填
  isRequired: boolean,
  // 参数类型，同后端字典
  type: string,
  // 字典标识
  dictFlag?: string,
  // 子参数，一般对象还有下级对象
  children: InParamDoc[]
}
// 入参文档对象
// props.initJsonStr 需要符合该类型
interface InitJson{
  inParamDocs: InParamDoc[]
}


const tableRef = ref(null)

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 初始化数据
  initJsonStr: {
    type: String
  },
  // 入参根节点类型，在第一次没有数据时默认添加根节点类型
  rootInParamType: {
    type: String,
    default: paramType.object
  }
})
// 属性
const reactiveData = reactive({
  initJson: {inParamDocs: []},
// 表单初始查询第一页
  form: {
    id: ''
  },
  tableColumns: [
    {
      prop: 'name',
      label: '参数名称',
    },
    {
      prop: 'description',
      label: '参数说明',
    },
    {
      prop: 'type',
      label: '类型',
    },
    {
      prop: 'isRequired',
      label: '是否必填',
    },
    {
      prop: 'dictFlag',
      label: '字典标识',
    },
  ],


})
const formComps = [
  {
    field: {
      name: 'name',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '参数名',
        required: true,
        tips: '请填写字段名'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'description',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '参数说明',
        required: true,
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'isRequired',
      value: false
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否必填',
        required: true,
      },
      compProps: {
        activeText: '必填',
        inactiveText: '选填',
      }
    }
  },
  {
    field: {
      name: 'type',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '类型',
        required: true
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'dataquery_datasource_api_param_type'},
        defaultValueItem: (item) => item.value == props.rootInParamType,
        // 选取value为选重值
        props: {value: 'value'},
      }
    }
  },
  {
    field: {
      name: 'dictFlag',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '字典标识',
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'parentId',
    },
    element: {
      comp: 'PtCascader',
      formItemProps: {
        label: '父级',
      },
      compProps: {
        disabled: true,
        clearable: true,
        options: reactiveData.initJson.inParamDocs
      }
    }
  },
]
onMounted(()=>{
  // 挂载后初始化数据
  if(props.initJsonStr){
    reactiveData.initJson.inParamDocs = JSON.parse(props.initJsonStr).inParamDocs
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '保存入参文档',
})
// 添加入参文档按钮
const submitMethod = ():void => {
  let isAdd = !reactiveData.form.id

  if (isAdd) {
    add()
  }else {
    update()
  }

}

const add = ()=>{

  // 根节点只能添加一条
  if(!reactiveData.form.parentId && reactiveData.initJson.inParamDocs.length > 0){

    alert('只能添加一个根参数','error')
    return
  }

  let obj = {
    // 生成一个唯一id
    id: uuidv4(),
    parentId: reactiveData.form.parentId,
    children: []
  }
  formFillItem(reactiveData.form, obj)

  let contener = reactiveData.initJson.inParamDocs
  if(obj.parentId){
    let parent = getById(obj.parentId,reactiveData.initJson.inParamDocs)
    contener = parent.children
  }
  contener.push(obj)
}
const update = ()=>{
  let item = getById(reactiveData.form.id,reactiveData.initJson.inParamDocs)
  formFillItem(reactiveData.form, item)
}
const formFillItem = (form,item)=>{
  item.name = form.name
  item.description = form.description
  item.type = form.type
  item.dictFlag = form.dictFlag
  item.isRequired = form.isRequired
}
const getById = (id,array=[])=>{
  for (let i = 0; i < array.length; i++) {
    let item = array[i]
    if(item.id == id){
      return item
    }
    return getById(id,item.children)
  }
}
const removeById = (id,array=[])=>{
  for (let i = 0; i < array.length; i++) {
    let item = array[i]
    if(item.id == id){
      array.splice(i,1)
      return
    }
    removeById(id,item.children)
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
          reactiveData.form.name = item.name
          reactiveData.form.description = item.description
          reactiveData.form.type = item.type
          reactiveData.form.dictFlag = item.dictFlag
          reactiveData.form.parentId = item.parentId
          reactiveData.form.isRequired = item.isRequired
        })
      }
    },
    {
      txt: '删除',
      text: true,
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        removeById(row.id,reactiveData.initJson.inParamDocs)
      }
    }
  ]
  if(row.type == paramType.object || row.type == paramType.array){
    tableRowButtons.push(
        {
          txt: '添加子级',
          text: true,
          // 删除操作
          method(){
            resetForm()
            nextTick(()=>{
              reactiveData.form.parentId = row.id
            })
          }
        }
    )
  }
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
           :options="reactiveData.initJson.inParamDocs"
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