<script setup name="AreaManageUpdatePage" lang="ts">
/**
 * 低代码数据源管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as lowCodeDataSourceUpdateApi,
  detailForUpdate as detailForUpdateApi,
  list as lowCodeDataSourceListApi
} from "../../../api/generator/admin/lowCodeDataSourceAdminApi"



// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  lowCodeDataSourceId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.lowCodeDataSourceId,
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
  buttonText: '确认修改',
  permission: 'admin:web:lowcodeDatasource:update',
})
// 提交按钮
const submitMethod = () => {
  return lowCodeDataSourceUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.lowCodeDataSourceId})
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
          :buttonsTeleportProps="$route.meta.formButtonsTeleportProps"
          inline
          :layout="[2,1,1]"
          :comps="formComps">
  </PtForm>

</template>


<style scoped>

</style>