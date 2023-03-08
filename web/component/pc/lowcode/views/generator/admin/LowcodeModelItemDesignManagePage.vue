<script setup name="LowcodeModelItemDesignManagePage" lang="ts">
/**
 * 低代码模型设计页面
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
const getSimpleBooleanColumnView = (scope,prop,propName)=>{
  return {
    is: 'PtSwitch',
    modelValue: scope.row[prop],
    // methodConfirmText: `确定要修改字段名称为 ${scope.row.columnName} ${propName}吗？`,
    method: (value)=> {
      scope.row[prop] = value;
      return lowcodeModelItemUpdateApi(scope.row).then(res => {
        // 修改成功，处理数据版本，否则会报数据版本错误，省一次数据刷新
        scope.row.version++
        return Promise.resolve(res)
      })
    },
    methodSuccess: '修改成功'
  }
}
// 注意该方法会成倍的调用
const getBooleanColumnView = (scope,designJsonScope,prop,propName,effectDesignJsonScopes=[])=>{
  if(!scope.row.designJsonObj){
    scope.row.designJsonObj = JSON.parse(scope.row.designJson)
  }
  let designJson = scope.row.designJsonObj
  let jsonItemMap = designJson.jsonItemMap
  let designJsonScopeValue = jsonItemMap[designJsonScope]
  return {
    is: 'PtSwitch',
    modelValue: designJsonScopeValue[prop],
    // methodConfirmText: `确定要修改字段名称为 ${scope.row.columnName} ${propName}吗？`,
    method: (value)=> {
      designJsonScopeValue[prop] = value
      if(effectDesignJsonScopes){
        effectDesignJsonScopes.forEach(item =>{
          jsonItemMap[item][prop] = value
        })
      }

      scope.row.designJson = JSON.stringify(designJson)
      return lowcodeModelItemUpdateApi(scope.row).then(res => {
        // 修改成功，处理数据版本，否则会报数据版本错误，省一次数据刷新
        scope.row.version++
        return Promise.resolve(res)
      })
    },
    methodSuccess: '修改成功'
  }
}
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
      prop: "columnName",
      fixed: true
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
      label: '响应实体',
      nestColumns:[
        {
          label: '是否使用',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'VO','isUse','是否使用',['UPDATE'])
          }
        },{
          label: '是否翻译字典',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'VO','isTransDictId','是否翻译字典')
          }
        }
      ]
    },
    {
      label: '添加',
      nestColumns:[
        {
          label: '是否使用',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'CREATE','isUse','是否使用',['UPDATE'])
          }
        },{
          label: '是否必填',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'CREATE','isRequired','是否必填',['UPDATE'])
          }
        }
      ]
    },
    {
      label: '修改(联动添加)',
      nestColumns:[
        {
          label: '是否使用',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'UPDATE','isUse','是否使用')
          }
        },{
          label: '是否必填',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'UPDATE','isRequired','是否必填')
          }
        }
      ]
    },
    {
      label: '列表查询',
      nestColumns:[
        {
          label: '是否使用',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'QUERY_LIST','isUse','是否使用',['QUERY_PAGE'])
          }
        },{
          label: '是否必填',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'QUERY_LIST','isRequired','是否必填',['QUERY_PAGE'])
          }
        },{
          label: '左模糊查询',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'QUERY_LIST','isUseLike','左模糊查询',['QUERY_PAGE'])
          }
        }
      ]
    },
    {
      label: '分页列表查询(联动列表查询)',
      nestColumns:[
        {
          label: '是否使用',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'QUERY_PAGE','isUse','是否使用')
          }
        },{
          label: '是否必填',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'QUERY_PAGE','isRequired','是否必填')
          }
        },{
          label: '左模糊查询',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'QUERY_PAGE','isUseLike','左模糊查询')
          }
        }
      ]
    },
    {
      label: '数据实体',
      nestColumns:[
        {
          label: '是否使用',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'DO','isUse','是否使用',['DOMAIN'])
          }
        }
      ]
    },
    {
      label: '领域模型(联动数据实体)',
      nestColumns:[
        {
          label: '是否使用',
          columnView: (scope)=>{
            return getBooleanColumnView(scope,'DOMAIN','isUse','是否使用')
          }
        }
      ]
    },
    {
      label: "默认值",
      prop: "defaultValue"
    },
    {
      label: "是否唯一",
      prop: "isUnique",
      columnView: (scope) =>{
        return getSimpleBooleanColumnView(scope,'isUnique','是否唯一')
      }

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
      prop: "isForeignKey",
      columnView: (scope) =>{
        return getSimpleBooleanColumnView(scope,'isForeignKey','是否外键')
      }
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
const styleColmunIndex = [7,8, 11,12, 16,17,18, 20]
const cellStyle=({ row, column, rowIndex, columnIndex })=>{
  return styleColmunIndex.indexOf(columnIndex) >= 0 ? {background: '#F2F3F5'} : {}
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
  <PtTable ref="tableRef"
           default-expand-all
           :cellStyle="cellStyle"
           :dataMethod="doLowcodeModelItemPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :paginationProps="tablePaginationProps"
           :columns="reactiveData.tableColumns">

  </PtTable>
<!-- 子级路由 -->
  <PtRouteViewPopover :level="3"></PtRouteViewPopover>
</template>


<style scoped>

</style>