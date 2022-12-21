<script setup name="FuncGroupManageUpdatePage" lang="ts">
/**
 * 功能菜单管理更新页面
 */
import {reactive, ref} from 'vue'
import {
  update as funcGroupUpdateApi,
  detailForUpdate as detailForUpdateApi,
} from "../../api/admin/funcGroupAdminApi"
// 声明属性
// 只要声名了属性 attrs 中就不会有该属性了
const props = defineProps({
  // 加载数据初始化参数,路由传参
  funcGroupId: {
    type: String
  }
})
// 属性
const reactiveData = reactive({
  // 表单
  form: {
    id: props.funcGroupId,
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
            title: '编码全局唯一，用来唯一标识一个功能项'
          },
          compProps: {
            clearable: true,
            disabled: true,
            disabledReason: '编码不能修改'
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
  buttonText: '确认修改'
})
// 提交按钮
const submitMethod = () => {
  return funcGroupUpdateApi
}
// 初始化加载更新的数据
const dataMethod = () => {
  return detailForUpdateApi({id: props.funcGroupId})
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
          :comps="formComps">
  </PtForm>
</template>


<style scoped>

</style>