<script setup name="UserManageUpdatePage" lang="ts">
/**
 * 用户管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as userUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as userListApi
} from "../../api/admin/userAdminApi"
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  userId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.userId,
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
          name: 'avatar'
        },
        element: {
          comp: 'PtUploadAvatar',
          formItemProps: {
            label: '头像'
          },
          compProps: {
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
            label: '昵称',
            required: true,
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
            label: '姓名'
          },
          compProps: {
            clearable: true,
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
            placeholder: '编号全局唯一'
          }
        }
      },

      {
        field: {
          name: 'isVirtual',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '虚拟用户'
          },
          compProps: {
            activeText: '虚拟用户',
            inactiveText: '真实用户',
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
            required: ({form})=> !form.isVirtual,
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
          name: 'isLock',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否锁定'
          },
          compProps: {
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
          name: 'groupFlag',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '分组标识'
          },
          compProps:  {
            clearable: true,
            placeholder: '就是一个字符串'
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
            required:true,
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
            required:true,
          },
          compProps: {
            clearable: true,
            // 字典查询
            dictParam: {groupCode: 'user_source_from'}
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
            label: '是否过期'
          },
          compProps: {
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
    ]
)

// 提交按钮属性
const submitAttrs = ref({
  buttonText: '确认修改',
  permission: 'admin:web:user:update',
})
// 提交按钮
const submitMethod = () => {
  return userUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.userId})
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