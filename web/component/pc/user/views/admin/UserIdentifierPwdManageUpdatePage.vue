<script setup name="UserIdentifierPwdManageUpdatePage" lang="ts">
/**
 * 用户登录标识密码管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as userIdentifierPwdUpdateApi,
  detailForUpdate as detailForUpdateApi,
} from "../../api/admin/userIdentifierPwdAdminApi"

// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  userIdentifierPwdId: {
    type: String
  },
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.userIdentifierPwdId,
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
          name: 'password'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '密码',
          },
          compProps: {
            clearable: true,
            placeholder: '不修改密码请留空'
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
            type: "datetime",
            placeholder: '不填写永不过期'
          }
        }
      },
      {
        field: {
          name: 'isNeedUpdate',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '提示修改',
            required: true
          },
          compProps: {
            clearable: true,
            activeText: '提示',
            inactiveText: '不提示',
          }
        }
      },
      {
        field: {
          name: 'needUpdateMessage'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '提示修改消息内容',
            required: ({form})=> form.isNeedUpdate
          },
          compProps: {
            clearable: true,
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
  return userIdentifierPwdUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.userIdentifierPwdId})
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

          :layout="[[8,8]]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>