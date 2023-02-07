<script setup name="LowCodeDataSourceManageAddPage" lang="ts">
/**
 * 低代码数据源管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as lowCodeDataSourceCreateApi,list as lowCodeDataSourceListApi} from "../../../api/generator/admin/lowCodeDataSourceAdminApi"


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
            title: '编码全局唯一，用来唯一标识一个数据源'
          },
          compProps: {
            clearable: true,
            placeholder: '编码唯一如：110',
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
            label: '名称',
            required: true
          },
          compProps: {
            clearable: true,
          }
        }
      },
      {
        field: {
          name: 'driverClassName'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '驱动类名',
            required: true,
            displayBlock: true,
            tips: 'com.mysql.cj.jdbc.Driver'
          },
          compProps: {
            clearable: true,
            placeholder: '如：com.mysql.cj.jdbc.Driver'
          }
        }
      },
      {
        field: {
          name: 'url'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '地址',
            required: true,
            tips: '例：jdbc:mysql://localhost/particle_test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8',
            displayBlock: true
          },
          compProps: {
            clearable: true,
            placeholder: '如：jdbc:mysql://localhost/particle_test',
          }
        }
      },
      {
        field: {
          name: 'username'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '用户',
            required: true
          },
          compProps: {
            clearable: true,
            placeholder: '如：root'
          }
        }
      },
      {
        field: {
          name: 'password'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '密码',
            required: true
          },
          compProps: {
            clearable: true,
            placeholder: '如：xxxx'
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
  permission: 'admin:web:lowcodeDatasource:create',
})
// 提交按钮
const submitMethod = () => {
  return lowCodeDataSourceCreateApi
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
          :layout="[2,1,1]"
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>