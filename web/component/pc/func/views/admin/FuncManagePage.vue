<script setup name="FuncManagePage" lang="ts">
/**
 * 功能菜单管理页面
 */
import {reactive ,ref} from 'vue'
import {page as funcPageApi} from "../../api/admin/funcAdminApi"
import {list as funcGroupListApi} from "../../api/admin/funcGroupAdminApi"

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
        name: 'funcGroupId'
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '功能分组'
        },
        compProps: {
          clearable: true,
          dataMethod: funcGroupListApi
        }
      }
    }
  ],
  tableOptions: [],
  tableColumns: [
    {
      prop: 'code',
      label: '编码'
    },
    {
      prop: 'name',
      label: '名称'
    },
    {
      prop: 'icon',
      label: '图标',
      // elementPlus 图标
      columnView: 'elIcon'
    },
    {
      prop: 'isDisabled',
      label: '是否禁用'
    },
    {
      prop: 'url',
      label: '路由'
    },
    {
      prop: 'typeDictName',
      label: '类型'
    },
    {
      prop: 'seq',
      label: '排序'
    },
    {
      prop: 'remark',
      label: '描述'
    }
  ]
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
  return funcPageApi({...reactiveData.form,...pageQuery})
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
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef" :dataMethod="doFuncPageApi" @dataMethodDataLoading="(loading) => submitAttrs.loading=loading" :columns="reactiveData.tableColumns">

  </PtTable>

</template>


<style scoped>

</style>