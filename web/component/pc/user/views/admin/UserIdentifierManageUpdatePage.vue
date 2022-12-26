<script setup name="UserIdentifierManageUpdatePage" lang="ts">
/**
 * 用户登录标识管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as userIdentifierUpdateApi,
  detailForUpdate as detailForUpdateApi,
} from "../../api/admin/userIdentifierAdminApi"
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  identifierId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.identifierId,
    userId: '',
    version: 1
  },
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'identifier'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '登录标识',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'identityTypeDictId'
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '账号类型',
            required: true
          },
          compProps: {
            clearable: true,
            // 字典查询
            dictParam: {groupCode: 'user_account_type'},
          }
        }
      },
      {
        field: {
          name: 'isLock',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否锁定',
            required: true
          },
          compProps: {
            clearable: true,
            activeText: '锁定',
            inactiveText: '正常',
          }
        }
      },
      {
        field: {
          name: 'lockReason'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '锁定原因',
            required: ({form}) => form.isLock == true
          },
          compProps: {
            clearable: true,
          }
        }
      },


      {
        field: {
          name: 'isExpired',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否过期',
            required: true
          },
          compProps: {
            clearable: true,
            activeText: '已过期',
            inactiveText: '正常',
          }
        }
      },
      {
        field: {
          name: 'expiredReason'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '过期原因',
            required: ({form}) => form.isExpired == true
          },
          compProps: {
            clearable: true,
            placeholder: '如：手动过期|系统自动达到过期时间'
          }
        }
      },
      {
        field: {
          name: 'expireAt',
        },
        element: {
          comp: 'PtDatePicker',
          formItemProps: {
            label: '过期时间'
          },
          compProps:  {
            clearable: true,
            type: "datetime"
          }
        }
      },
      {
        field: {
          name: 'groupFlag'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '分组标识'
          },
          compProps: {
            clearable: true,
          }
        }
      },

    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改'
})
// 提交按钮
const submitMethod = () => {
  return userIdentifierUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.identifierId})
}
// 成功提示语
const submitMethodSuccess = () => {
  return '修改成功，请刷新数据查看'
}
</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :dataMethod="dataMethod"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          inline
          :layout="[1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>