<script setup name="RoleManageAddPage" lang="ts">
/**
 * 角色管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as roleCreateApi,list as roleListApi} from "../../api/admin/roleAdminApi"


// 属性
const reactiveData = reactive({
  // 表单初始查询第一页
  form: {},
  // 表单数据对象
  formData: {},
})
// 表单项
const formComps = ref(
    [
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '编码',
            required: true,
            title: '编码全局唯一，用来唯一标识一个角色'
          },
          compProps: {
            clearable: true,
            placeholder: '编码唯一如：110',
          }
        }
      },
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',
            required: true,
          },
          compProps: {
            clearable: true,
            placeholder: '如：管理员',
          }
        }
      },
      {
        field: {
          name: 'isDisabled',
          value: false
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否禁用'
          },
          compProps: {
            activeText: '禁用',
            inactiveText: '启用',
          }
        }
      },
      {
        field: {
          name: 'disabledReason'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '禁用原因',
            required: ({form}) => form.isDisabled == true

          },
          compProps: {
            clearable: true,
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
            dataMethod: () => { return roleListApi({})},
            dataMethodResultHandleConvertToTree: true,
          }
        }
      },
      {
        field: {
          name: 'seq',
          value: 10
        },
        element: {
          comp: 'el-input-number',
          formItemProps: {
            label: '排序'
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'remark'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '描述'
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
  buttonText: '确认添加',
  permission: 'admin:web:role:create',
})
// 提交按钮
const submitMethod = () => {
  return roleCreateApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
}

</script>
<template>
  <!-- 添加表单 -->
  <PtForm :form="reactiveData.form"
          :formData="reactiveData.formData"
          labelWidth="80"
          :method="submitMethod()"
          :methodSuccess="submitMethodSuccess"
          defaultButtonsShow="submit,reset"
          :submitAttrs="submitAttrs"
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>