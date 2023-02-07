<script setup name="LowCodeSegmentTemplateManagePage" lang="ts">
/**
 * 低代码片段模板管理页面
 */
import {reactive, ref} from 'vue'
import {
  list as lowCodeSegmentTemplateListApi,
  page as lowCodeSegmentTemplatePageApi,
  remove as lowCodeSegmentTemplateRemoveApi
} from "../../../api/generator/admin/lowCodeSegmentTemplateAdminApi"
import {treeQueryComps} from "../../../../treeQueryComps";

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
        name: 'referenceSegmentTemplateId'
      },
      element: {
        comp: 'PtCascader',
        formItemProps: {
          label: '引用模板',
        },
        compProps: {
          dataMethod: lowCodeSegmentTemplateListApi,
          dataMethodResultHandleConvertToTree: true,
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
          dataMethod: lowCodeSegmentTemplateListApi,
          dataMethodResultHandleConvertToTree: true,
        }
      }
    },
    ...treeQueryComps
  ],
  tableColumns: [
    {
      label: "模板名称",
      prop: "name",
      width: 250,
      showOverflowTooltip: true
    },
    {
      label: "编码",
      prop: "code",
      showOverflowTooltip: true
    },

    {
      label: "名称模板",
      prop: "nameTemplate",
      showOverflowTooltip: true
    },
    {
      label: "内容模板",
      prop: "contentTemplate",
      showOverflowTooltip: true
    },
    {
      label: "引用模板",
      prop: "referenceSegmentTemplateName"
    },
    {
      label: "输出类型",
      prop: "outputType"
    },
    {
      label: "输出变量名",
      prop: "outputVariable"
    },
    {
      label: "共享变量名",
      prop: "shareVariables"
    },
    {
      label: "父级",
      prop: "parentName"
    },
    {
      label: "描述",
      prop: "remark"
    }
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:lowcodeSegmentTemplate:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doLowCodeSegmentTemplatePageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return lowCodeSegmentTemplatePageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:lowcodeSegmentTemplate:update',
      // 跳转到编辑
      route: {path: '/admin/lowCodeSegmentTemplateManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:lowcodeSegmentTemplate:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return lowCodeSegmentTemplateRemoveApi(idData).then(res => {
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
      <PtButton permission="admin:web:lowcodeSegmentTemplate:create" route="/admin/lowCodeSegmentTemplateManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doLowCodeSegmentTemplatePageApi"
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