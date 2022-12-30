<script setup name="AreaManageAddPage" lang="ts">
/**
 * 区域管理添加页面
 */
import {reactive ,ref} from 'vue'
import {create as areaCreateApi,list as areaListApi} from "../../api/admin/areaAdminApi"
import LocationGeoMapDialog from '../../compnents/LocationGeoMapDialog.vue'

const locationGeoMapDialogRef = ref(null)

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
            title: '编码全局唯一，用来唯一标识一个区域'
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
          name: 'nameSimple'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '简称'
          },
          compProps: {
            clearable: true
          }
        }
      },
      {
        field: {
          name: 'typeDictId'
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '类型',
            required: true
          },
          compProps: {
            clearable: true,
            // 字典查询
            dictParam: {groupCode: 'area_type'}
          }
        }
      },
      {
        field: {
          name: 'longitude'
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '经度'
          },
          compProps: {
            clearable: true,
            placeholder: '百度地图坐标'
          }
        }
      },
      {
        field: {
          name: 'latitude'
        },
        element: {
          comp: 'PtInput',
          formItemProps: {
            label: '纬度'
          },
          compProps: {
            clearable: true,
            placeholder: '百度地图坐标',
            appendComp: {
              comp: 'PtButton',
              compProps: {
                icon: 'Location',
                method: ()=> {
                  locationGeoMapDialogRef.value.open()
                }
              }
            }
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
            dataMethod: () => { return areaListApi({})},
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
  permission: 'admin:web:area:create',
})
// 提交按钮
const submitMethod = () => {
  return areaCreateApi
}
// 成功提示语
const submitMethodSuccess = () => {
  return '添加成功，请刷新数据查看'
}

const dialogSubmit = ({str,longitude,latitude})=>{
  reactiveData.form.longitude = longitude + ''
  reactiveData.form.latitude = latitude + ''
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
  <LocationGeoMapDialog ref="locationGeoMapDialogRef" :submit="dialogSubmit" :point="[reactiveData.form.longitude,reactiveData.form.latitude]"></LocationGeoMapDialog>
</template>


<style scoped>

</style>