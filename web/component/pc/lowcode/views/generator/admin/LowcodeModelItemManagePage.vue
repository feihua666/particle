<script setup name="LowcodeModelItemManagePage" lang="ts">
/**
 * 低代码模型项管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as lowcodeModelItemPageApi,
  remove as lowcodeModelItemRemoveApi,
  update as lowcodeModelItemUpdateApi
} from "../../../api/generator/admin/lowcodeModelItemAdminApi"
import {list as lowcodeModelListApi} from "../../../api/generator/admin/lowcodeModelAdminApi";

const tableRef = ref(null)
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 可通过路由传参
  lowcodeModelId:{
    type: String
  }
})

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [

    {
      field: {
        name: 'columnName'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '字段名称'
        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配'
        }
      }
    },
    {
      field: {
        name: 'propertyName'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '实体属性名称'
        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配'
        }
      }
    },
    {
      field: {
        name: 'lowcodeModelId',
        value: props.lowcodeModelId
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '模型',
        },
        compProps: {
          dataMethod: lowcodeModelListApi
        }
      }
    },
  ],
  tableColumns: [
    {
      label: "字段名称",
      prop: "columnName"
    },
    {
      label: "实体属性名称",
      prop: "propertyName"
    },
    {
      label: "字段类型",
      prop: "jdbcType"
    },
    {
      label: "实体属性类型",
      prop: "propertyType"
    },
    {
      label: "实体属性类型全路径",
      prop: "propertyFullType",
      showOverflowTooltip: true
    },
    {
      label: "全注释",
      prop: "commentFull",
      showOverflowTooltip: true
    },
    {
      label: "简洁注释",
      prop: "commentSimple",
      showOverflowTooltip: true
    },
    {
      label: "默认值",
      prop: "defaultValue"
    },
    {
      label: "是否唯一",
      prop: "isUnique",
    },
    {
      label: "是否必填",
      prop: "isRequired",
    },
    {
      label: "是否主键",
      prop: "isKey"
    },
    {
      label: "是否主键自增",
      prop: "isKeyIdentity"
    },
    {
      label: "是否关键字",
      prop: "isKeyWord"
    },
    {
      label: "长度",
      prop: "columnLength"
    },
    {
      label: "小数位长度",
      prop: "fractionLength"
    },
    {
      label: "是否外键",
      prop: "isForeignKey"
    },
    {
      label: "模型",
      prop: "lowcodeModelName"
    },
    {
      label: "设计数据",
      prop: "designJson",
      showOverflowTooltip: true

    },
    {
      label: "描述",
      prop: "remark"
    }
  ]

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:lowcodeModelItem:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doLowcodeModelItemPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return lowcodeModelItemPageApi({...reactiveData.form,...pageQuery})
}
const tablePaginationProps = {
  permission: submitAttrs.value.permission
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let idData = {id: row.id}
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:lowcodeModelItem:update',
      // 跳转到编辑
      route: {path: '/admin/lowcodeModelItemManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:lowcodeModelItem:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return lowcodeModelItemRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },

  ]
  return tableRowButtons
}
</script>
<template>
  <!-- 查询表单 -->
  <PtForm :form="reactiveData.form"
          :method="submitMethod"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :comps="reactiveData.formComps">
    <template #buttons>
      <PtButton permission="admin:web:lowcodeModelItem:create" route="/admin/lowcodeModelItemManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doLowcodeModelItemPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

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
<!-- 子级路由 -->
  <PtRouteViewPopover :level="3"></PtRouteViewPopover>
</template>


<style scoped>

</style>