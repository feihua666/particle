<script setup name="DictManagePage" lang="ts">
/**
 * 字典管理页面
 */
import {reactive ,ref} from 'vue'
import {list as dictListApi, page as dictPageApi, remove as dictRemoveApi} from "../../api/admin/dictAdminApi"

const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    {
      field: {
        name: 'code'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '编码'
        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配'
        }
      }
    },
    {
      field: {
        name: 'name'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '名称'
        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配'
        }
      }
    },
    {
      field: {
        name: 'parentId'
      },
      element: {
        comp: 'PtCascader',
        formItemProps: {
          label: '父级',
        },
        compProps: {
          clearable: true,
          // 加载数据
          dataMethod: () => { return dictListApi({})},
          dataMethodResultHandleConvertToTree: true,
        }
      }
    },
  ],
  tableColumns: [
    {
      prop: 'name',
      label: '名称',
      width: 150,
      showOverflowTooltip: true
    },
    {
      prop: 'code',
      label: '编码'
    },
    {
      prop: 'value',
      label: '字典值'
    },
    {
      prop: 'valueUnit',
      label: '字典值单位'
    },
    {
      prop: 'parentName',
      label: '父级'
    },

    {
      prop: 'isDisabled',
      label: '是否系统',
      width: 100,
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '系统字典' : '自定义字典'
      }
    },
    {
      prop: 'isPublic',
      label: '是否公共',
      width: 100,
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '公共字典' : '私有字典'
      }
    },
    {
      prop: 'isGroup',
      label: '是否字典组',
      width: 100,
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '字典组' : '字典项'
      }
    },
    {
      prop: 'isDisabled',
      label: '是否禁用',
      width: 100,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '禁用' : '启用'
        if(cellValue && row.disabledReason){
          r = r + `(${row.disabledReason})`
        }
        return r
      }
    },
    {
      prop: 'privateFlag',
      label: '私有标识',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue
        if(cellValue && row.privateFlagMemo){
          r = r + `(${row.privateFlagMemo})`
        }
        return r
      }
    },
    {
      prop: 'groupFlag',
      label: '分组标识',
      formatter: (row, column, cellValue, index) => {
        let r = cellValue
        if(cellValue && row.groupFlagMemo){
          r = r + `(${row.groupFlagMemo})`
        }
        return r
      }
    },
    {
      prop: 'tags',
      label: '标签'
    },
    {
      prop: 'seq',
      label: '排序',
      width: 50,
    },
    {
      prop: 'remark',
      label: '描述'
    }
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doFuncPageApi = (pageQuery: {pageNo: number,pageSize: number}) => {
  return dictPageApi({...reactiveData.form,...pageQuery})
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
      // 跳转到编辑
      route: {path: '/admin/dictManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return dictRemoveApi({id: row.id}).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    }
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
      <PtButton route="/admin/dictManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doFuncPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
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