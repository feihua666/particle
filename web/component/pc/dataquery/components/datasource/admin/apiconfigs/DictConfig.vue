<script setup name="DictConfig" lang="ts">
import {nextTick, onMounted, reactive, ref} from 'vue'
import {v4 as uuidv4} from 'uuid';
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
 * 字典配置项
 */
interface DictItem{
  // 字典名，文本时可能没有
  id: [string,number],
  // 字典名，文本时可能没有
  name: string,
  // 字典描述
  value:string,
  // 单位
  unit?: string,
  // 是否字典组
  isGroup: boolean,
  // 子字典，一般对象还有下级对象
  children: DictItem[]
}
// 字典配置对象
// props.initJsonStr 需要符合该类型
interface InitJson{
  dictItems: DictItem[]
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
  initJson: {dictItems: []},
// 表单初始查询第一页
  form: {
    id: ''
  },
  tableColumns: [
    {
      prop: 'id',
      label: '主键',
    },
    {
      prop: 'name',
      label: '字典名称',
    },
    {
      prop: 'value',
      label: '字典值/编码',
    },
    {
      prop: 'isGroup',
      label: '是否字典组',
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '字典组' : '字典项'
      }
    },
  ],


})
const formComps = [
  {
    field: {
      name: 'id',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '主键',
        tips: '不填写自动生成'
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'isGroup',
      value: false
    },
    element: {
      comp: 'el-switch',
      formItemProps: {
        label: '是否字典组',
        required: true,
      },
      compProps: {
        activeText: '字典组',
        inactiveText: '字典项',
      }
    }
  },
  {
    field: {
      name: 'name',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '字典名',
        required: true,
      },
      compProps: {
        clearable: true,
      }
    }
  },
  {
    field: {
      name: 'value',
    },
    element: {
      comp: 'el-input',
      formItemProps: {
        label: '字典值/编码',
        required: true,
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
        options: reactiveData.initJson.dictItems
      }
    }
  },
]
onMounted(()=>{
  // 挂载后初始化数据
  if(props.initJsonStr){
    reactiveData.initJson.dictItems.push(...(JSON.parse(props.initJsonStr).dictItems))
  }
})
// 提交按钮属性
const submitAttrs = ref({
  buttonText: '保存字典配置',
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

  if(reactiveData.form.isGroup == false && !reactiveData.form.parentId){
    alert('字典项能只添加到字典组下面,你可能需要先添加一个字典组','error')
    return
  }
  if(reactiveData.form.isGroup == true && reactiveData.form.parentId){
    alert('字典组下只能添加字典项','error')
    return
  }

  let obj = {
    // 生成一个唯一id
    id: reactiveData.form.id || uuidv4(),
    parentId: reactiveData.form.parentId,
    children: []
  }
  formFillItem(reactiveData.form, obj)

  let container = reactiveData.initJson.dictItems
  if(obj.parentId){
    let parent = getById(obj.parentId,reactiveData.initJson.dictItems)
    container = parent.children
  }
  container.push(obj)
}
const update = ()=>{
  let item = getById(reactiveData.form.id,reactiveData.initJson.dictItems)
  formFillItem(reactiveData.form, item)
}
const formFillItem = (form,item)=>{
  item.name = form.name
  item.value = form.value
  item.isGroup = form.isGroup
  item.unit = form.unit
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
          formFillItem(item,reactiveData.form)
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
        removeById(row.id,reactiveData.initJson.dictItems)
      }
    }
  ]
  if(row.isGroup == true){
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
           :options="reactiveData.initJson.dictItems"
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
