<script setup name="UserManagePage" lang="ts">
/**
 * 用户管理页面
 */
import {reactive, ref} from 'vue'
import {page as userPageApi, remove as userRemoveApi} from "../../api/admin/userAdminApi"
import { useRoute } from 'vue-router'
const route = useRoute()
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
          label: '姓名'
        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配'
        }
      }
    },
    {
      field: {
        name: 'nickname'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '昵称'
        },
        compProps: {
          clearable: true,
          placeholder: '左前缀匹配'
        }
      }
    },
    {
      field: {
        name: 'serialNo'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '编号'
        },
        compProps: {
          clearable: true,
        }
      }
    },
    {
      field: {
        name: 'genderDictId'
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '性别',
        },
        compProps: {
          clearable: true,
          // 字典查询
          dictParam: {groupCode: 'gender'}
        }
      }
    },
    {
      field: {
        name: 'categoryDictId'
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '分类',
        },
        compProps: {
          clearable: true,
          // 字典查询
          dictParam: {groupCode: 'user_category'}
        }
      }
    },
    {
      field: {
        name: 'sourceFromDictId'
      },
      element: {
        comp: 'PtDictFrontSelect',
        formItemProps: {
          label: '来源',
        },
        compProps: {
          clearable: true,
          // 字典查询
          dictParam: {groupCode: 'user_source_from'}
        }
      }
    },
  ],
  tableColumns: [
    {
      prop: 'nickname',
      label: '昵称',
      showOverflowTooltip: true
    },
    {
      prop: 'name',
      label: '姓名',
    },
    {
      prop: 'avatar',
      label: '头像',
      columnView: 'image'
    },
    {
      prop: 'serialNo',
      label: '编号'
    },
    {
      prop: 'genderDictName',
      label: '性别'
    },
    {
      prop: 'isVirtual',
      label: '虚拟用户',
      width: 70,
      formatter: (row, column, cellValue, index) => {
        return cellValue ? '虚拟用户' : '真实用户'
      }
    },
    {
      prop: 'isLock',
      label: '是否锁定',
      width: 70,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '锁定' : '正常'
        if(cellValue && row.lockReason){
          r = r + `(${row.lockReason})`
        }
        return r
      },
      showOverflowTooltip: true
    },
    {
      prop: 'categoryDictName',
      label: '分类'
    },
    {
      prop: 'groupFlag',
      label: '分组标识'
    },
    {
      prop: 'sourceFromDictName',
      label: '来源',
    },
    {
      prop: 'isExpired',
      label: '是否过期',
      width: 70,
      formatter: (row, column, cellValue, index) => {
        let r = cellValue ? '已过期' : '正常'
        if(cellValue && row.expiredReason){
          r = r + `(${row.expiredReason})`
        }
        return r
      }
    },
    {
      prop: 'expireAt',
      label: '过期时间',
      width: 80,
    },
  ],

})

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '查询',
  loading: false,
  permission: 'admin:web:user:pageQuery'
})
// 查询按钮
const submitMethod = ():void => {
  tableRef.value.refreshData()
}
// 分页数据查询
const doUserPageApi = (pageQuery: {pageNo: number,pageSize: number}) => {
  return userPageApi({...reactiveData.form,...pageQuery})
}
// 表格操作按钮
const getTableRowButtons = ({row, column, $index}) => {
  if($index < 0){
    return []
  }
  let idData = {id: row.id}
  let routeQuery = {userId: row.id,nickname: row.nickname}
  let tableRowButtons = [
    {
      txt: '编辑',
      text: true,
      permission: 'admin:web:user:update',
      // 跳转到编辑
      route: {path: '/admin/userManageUpdate',query: routeQuery}
    },
    {
      txt: '删除',
      text: true,
      permission: 'admin:web:user:delete',
      methodConfirmText: `删除用户将可能造成不可预知的后果，一般不建议删除，可以将用户锁定处理，如果是新用户可以放心删除。确定要删除 ${row.nickname} 吗？`,
      // 删除操作
      method(){
        return userRemoveApi(idData).then(res => {
          // 删除成功后刷新一下表格
          submitMethod()
          return Promise.resolve(res)
        })
      }
    },

    {
      txt: '重置密码',
      text: true,
      position: 'more',
      permission: 'admin:web:userIdentifierPwd:user:resetPassword',
      methodConfirmText: `此操作会影响用户所有账号密码，确定要重置密码吗？`,
      // 跳转到编辑
      route: {path: '/admin/userPwdManageResetPassword',query: routeQuery},
    },
    {
      txt: '添加登录标识',
      text: true,
      permission: 'admin:web:userIdentifier:create',
      position: 'more',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierManageAddFixed',query: routeQuery},
    },
    {
      txt: '登录标识',
      text: true,
      permission: 'admin:web:userIdentifier:pageQuery',
      position: 'more',
      icon: 'UserFilled',
      type: 'primary',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierManagePage',query: routeQuery},
    },
    {
      txt: '用户密码',
      text: true,
      permission: 'admin:web:userIdentifierPwd:pageQuery',
      position: 'more',
      icon: 'View',
      type: 'primary',
      // 跳转到编辑
      route: {path: '/admin/userIdentifierPwdManagePage',query: routeQuery},
    },
  ]
  return tableRowButtons
}
const dropdownTriggerButtonOptions = {
  text: true,
  buttonText: '更多',
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
      <PtButton permission="admin:web:user:create" route="/admin/userManageAdd">添加</PtButton>
    </template>
  </PtForm>
<!-- 指定 dataMethod，默认加载数据 -->
  <PtTable ref="tableRef"
           default-expand-all
           :dataMethod="doUserPageApi"
           @dataMethodDataLoading="(loading) => submitAttrs.loading=loading"
           :dataMethodResultHandleConvertToTree="true"
           :columns="reactiveData.tableColumns">

    <!--  操作按钮  -->
    <template #defaultAppend>
      <el-table-column label="操作" width="220">
        <template #default="{row, column, $index}">
          <PtButtonGroup :options="getTableRowButtons({row, column, $index})" :dropdownTriggerButtonOptions="dropdownTriggerButtonOptions">
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