<script setup name="OutParamDocConfig" lang="ts">
import {onMounted, reactive, ref,nextTick} from 'vue'
import { clone} from "../../../../../../../global/common/tools/ObjectTools";
import {paramType} from "../dataQueryDatasourceApiManage";
import { v4 as uuidv4 } from 'uuid';

const selfFormRef = ref(null)
/**
 * 入参文档项
 */
interface OutParamDoc{
  // 参数名，文本时可能没有
  name?: string,
  // 参数描述
  description?:string,
  // 参数类型，同后端字典
  type: string,
  // 字典标识
  dictFlag?: string,
  // 子参数，一般对象还有下级对象
  children: OutParamDoc[]
}
// 入参文档对象
// props.initJsonStr 需要符合该类型
interface InitJson{
  outParamDocs: OutParamDoc[]
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
  initJson: {outParamDocs: []},
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
        sceneDataHander(data){
          if(data){
            let r = []
            data.forEach(item =>{
              let itemTemp = clone(item)
              if(item.value == paramType.none){
                itemTemp.isDisabled = true
                itemTemp.disabledReason = `这里不需要该选项，因为参数必须有类型`
                r.push(itemTemp)
              }else {
                r.push(itemTemp)
              }
            })
            return r
          }
          return data
        }
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
        options: reactiveData.initJson.outParamDocs
      }
    }
  },
]
onMounted(()=>{
  // 挂载后初始化数据
  if(props.initJsonStr){
    reactiveData.initJson.outParamDocs = JSON.parse(props.initJsonStr).outParamDocs
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '保存出参文档',
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
  let obj = {
    // 生成一个唯一id
    id: uuidv4(),
    name: reactiveData.form.name,
    description: reactiveData.form.description,
    type: reactiveData.form.type,
    dictFlag: reactiveData.form.dictFlag,
    parentId: reactiveData.form.parentId,
    children: []
  }
  let contener = reactiveData.initJson.outParamDocs
  if(obj.parentId){
    let parent = getById(obj.parentId,reactiveData.initJson.outParamDocs)
    contener = parent.children
  }
  contener.push(obj)
}
const update = ()=>{
  let item = getById(reactiveData.form.id,reactiveData.initJson.outParamDocs)
  item.name = reactiveData.form.name
  item.description = reactiveData.form.description
  item.type = reactiveData.form.type
  item.dictFlag = reactiveData.form.dictFlag
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
        })
      }
    },
    {
      txt: '删除',
      text: true,
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        removeById(row.id,reactiveData.initJson.outParamDocs)
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
           :options="reactiveData.initJson.outParamDocs"
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