<script setup name="LowcodeSegmentGenManagePage" lang="ts">
/**
 * 低代码片段生成管理页面
 */
import {reactive, ref} from 'vue'
import {
  list as lowcodeSegmentGenListApi,
  page as lowcodeSegmentGenPageApi, reloadLowcodeModelJson,
  remove as lowcodeSegmentGenRemoveApi
} from "../../../api/generator/admin/lowcodeSegmentGenAdminApi"
import {list as lowcodeSegmentTemplateListApi} from "../../../api/generator/admin/lowcodeSegmentTemplateAdminApi";
import {list as lowcodeModelListApi} from "../../../api/generator/admin/lowcodeModelAdminApi";

const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: [
    {
      field: {
        name: 'name'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '生成名称'
        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配'
        }
      }
    },
    {
      field: {
        name: 'lowcodeSegmentTemplateId'
      },
      element: {
        comp: 'PtCascader',
        formItemProps: {
          label: '片段模板',
        },
        compProps: {
          dataMethod: lowcodeSegmentTemplateListApi,
          dataMethodResultHandleConvertToTree: true,
        }
      }
    },
    {
      field: {
        name: 'lowcodeModelId'
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '低代码模型',
        },
        compProps: {
          dataMethod: lowcodeModelListApi,
        }
      }
    },
    {
      field: {
        name: 'generateTypeDictId'
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '生成类型',
        },
        compProps: {
          // 字典查询
          dictParam: {groupCode: 'lowcode_segment_gen_type'},
        }
      }
    },
    {
      field: {
        name: 'refrenceSegmentGenId'
      },
      element: {
        comp: 'PtSelect',
        formItemProps: {
          label: '引用生成',
        },
        compProps: {
          dataMethod: lowcodeSegmentGenListApi,
        }
      }
    },
  ],
  tableColumns: [
    {
      label: "生成名称",
      prop: "name",
    },
    {
      label: "片段模板名称",
      prop: "lowcodeSegmentTemplateName",
    },

    {
      label: "模型名称",
      prop: "lowcodeModelName",
    },
    {
      label: "模型数据",
      prop: "lowcodeModelJson",
      showOverflowTooltip: true
    },
    {
      label: "全局数据",
      prop: "globalJson",
      showOverflowTooltip: true
    },
    {
      label: "是否生成",
      prop: "isGenerated"
    },
    {
      label: "生成类型",
      prop: "generateTypeDictName"
    },
    {
      label: "引用生成名称",
      prop: "refrenceSegmentGenName"
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
  permission: 'admin:web:lowcodeSegmentGen:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doLowcodeSegmentGenPageApi = ({pageQuery}: {param: object,pageQuery: {pageNo: number,pageSize: number}}) => {
  return lowcodeSegmentGenPageApi({...reactiveData.form,...pageQuery})
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
      permission: 'admin:web:lowcodeSegmentGen:update',
      // 跳转到编辑
      route: {path: '/admin/lowcodeSegmentGenManageUpdate',query: idData}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:lowcodeSegmentGen:delete',
      methodConfirmText: `确定要删除 ${row.name} 吗？`,
      // 删除操作
      method(){
        return lowcodeSegmentGenRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },
    {
      txt: '重新加载模型json数据',
      text: true,
      position: 'more',
      permission: 'admin:web:lowcodeSegmentGen:reloadLowcodeModelJson',
      methodConfirmText: `这将会覆盖当前的模型json数据，确定要重新加载 ${row.name} 吗？`,
      // 删除操作
      method(){
        return reloadLowcodeModelJson(idData).then(res => {
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
      <PtButton permission="admin:web:lowcodeSegmentGen:create" route="/admin/lowcodeSegmentGenManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doLowcodeSegmentGenPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})"  :dropdownTriggerButtonOptions="{  text: true,buttonText: '更多'}">
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