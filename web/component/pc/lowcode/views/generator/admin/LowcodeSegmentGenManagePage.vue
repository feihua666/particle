<script setup name="LowcodeSegmentGenManagePage" lang="ts">
/**
 * 低代码片段生成管理页面
 */
import {reactive, ref} from 'vue'
import {
  page as lowcodeSegmentGenPageApi,
  reloadLowcodeModelJson,
  remove as lowcodeSegmentGenRemoveApi
} from "../../../api/generator/admin/lowcodeSegmentGenAdminApi"
import {exist} from "../../../../../../global/common/tools/ArrayTools";
import {pageFormItems} from "../../../components/admin/lowcodeSegmentGenManage";

const tableRef = ref(null)

// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {
  },
  formComps: pageFormItems,
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
      label: "全局数据(渲染后有值)",
      prop: "globalJson",
      showOverflowTooltip: true
    },
    {
      label: "扩展数据(渲染后有值)",
      prop: "extJson",
      showOverflowTooltip: true
    },
    {
      label: "输出绝对位置(渲染后有值)",
      prop: "outputFileParentAbsoluteDir",
      showOverflowTooltip: true
    },
    {
      label: "java包key(渲染后有值)",
      prop: "javaPackageKeys",
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
      prop: "remark",
      showOverflowTooltip: true
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
  let idDataWithRefrenceSegmentGenId = {id: row.id,refrenceSegmentGenId: row.refrenceSegmentGenId,isGenerated: row.isGenerated}

  let tableRowButtons: Array<any> = [
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
      position: 'more',
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
  const moduleExist = exist(row.generateTypeDictValue,['backend_module','frontend_module','backend_and_frontend_module'])

  moduleExist && tableRowButtons.push(
      {
        txt: '模块设计和渲染',
        text: true,
        position: 'more',
        permission: 'admin:web:lowcodeSegmentGen:renderGen',
        // 跳转到设计和渲染
        route: {path: '/admin/lowcodeSegmentGenModuleDesignAndGenerate',query: idDataWithRefrenceSegmentGenId}
      },
  )
  const modelServiceExist = exist(row.generateTypeDictValue,['backend_model_service','frontend_model_service','backend_and_frontend_model_service'])
  modelServiceExist && tableRowButtons.push(
      {
        txt: '模型服务设计和渲染',
        text: true,
        position: 'more',
        beforeMethod: ()=> {
          if(row.lowcodeModelId > 0){
            return true
          }
          return '模型服务设计与渲染需要设置模型'
        },
        permission: 'admin:web:lowcodeSegmentGen:renderGen',
        // 跳转到设计和渲染
        route: {path: '/admin/lowcodeSegmentGenModelServiceDesignAndGenerate',query: idDataWithRefrenceSegmentGenId}
      },
  )
  !moduleExist && !modelServiceExist && tableRowButtons.push(
      {
        txt: '通用设计和渲染',
        text: true,
        position: 'more',
        permission: 'admin:web:lowcodeSegmentGen:renderGen',
        // 跳转到设计和渲染
        route: {path: '/admin/lowcodeSegmentGenGenericDesignAndGenerate',query: idDataWithRefrenceSegmentGenId}
      },
  )
  let globalJsonStr = row.globalJson
  let globalJson = JSON.parse(globalJsonStr) || {}
  let lowcodeModelJsonStr = row.lowcodeModelJson
  let lowcodeModelJson = JSON.parse(lowcodeModelJsonStr) || {}

  let code = globalJson.moduleNamePackage + "_manage"
  let name = row.name.split('_')[0] + "管理"
  // pc或平板后台管理为1，如果数据有变更请修改
  let funcGroupId = '1'
  // 菜单=21，页面=22，按钮=1605497232817737729
  let typeDictId = ''
  let componentOf = globalJson.moduleName
  tableRowButtons.push(
      {
        txt: '添加根功能菜单',
        text: true,
        position: 'more',
        disabled: !row.isGenerated,
        disabledReason: !row.isGenerated ? '生成后才可以添加根功能菜单' : undefined,
        permission: 'admin:web:func:create',
        // 跳转到菜单添加页面
        route: {path: '/admin/lowcodeSegmentGen_funcManageAdd',query: {
            code,
            name,
            funcGroupId,
            typeDictId: '21',
            componentOf
          }}
      },
  )
  let pageCode = lowcodeModelJson.model?.nameEn + '_manage_page'
  tableRowButtons.push(
      {
        txt: '添加功能菜单页面',
        text: true,
        position: 'more',
        disabled: !row.isGenerated,
        disabledReason: !row.isGenerated ? '生成后才可以添加功能菜单页面' : undefined,
        permission: 'admin:web:func:create',
        // 跳转到菜单添加页面
        route: {path: '/admin/lowcodeSegmentGen_funcManageAdd',query: {
            code: pageCode,
            name,
            funcGroupId,
            typeDictId: '22',
            url: `/admin/${lowcodeModelJson.model?.nameEnEntityVar}ManagePage`,
            componentOf,
          }}
      },
  ),  tableRowButtons.push(
      {
        txt: '添加功能菜单页面CRUD',
        text: true,
        position: 'more',
        disabled: !row.isGenerated,
        disabledReason: !row.isGenerated ? '生成后才可以添加功能菜单页面CRUD' : undefined,
        permission: 'admin:web:func:create',
        // 跳转到菜单添加页面
        route: {path: '/admin/lowcodeSegmentGen_funcManageCrudAdd',query: {
            codePrefix: lowcodeModelJson.model?.nameEn + '_manage_page' + '_',
            namePrefix: name,
            permissionPrefix: `admin:web:${lowcodeModelJson.model?.nameEnEntityVar}:`,
            funcGroupId,
            // 按钮=1605497232817737729
            typeDictId: '1605497232817737729',
            componentOf,
            createExtPermission: `admin${lowcodeModelJson.model?.nameEnEntity}ManageAdd`,
            updateExtPermission: `admin${lowcodeModelJson.model?.nameEnEntity}ManageUpdate`,
            queryPageExtPermission: `admin${lowcodeModelJson.model?.nameEnEntity}ManagePage`,
            isTree: lowcodeModelJson.model?.tableTypeDictValue == 'TREE'
          }}
      },
  )
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

           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="180">
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
