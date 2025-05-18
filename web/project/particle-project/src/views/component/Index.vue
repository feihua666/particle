<script setup name="index"  lang="ts">
import {onMounted, reactive, ref, watch} from 'vue'

const reactiveData = reactive({
  // loading状态
  cascaderModelValue: ['consistency'],
  checkboxGroupModelValue: ['consistency'],
  checkboxModelValue: true,
  radioGroupModelValue: 'consistency',
  selectModelValue: 'consistency',
  selectGroupModelValue: ['chengdu'],
  selectRemoteModelValue: '',
  uploadModelValue: [
    {
      name: 'element-plus-logo.svg',
      url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
    },
    {
      name: 'element-plus-logo2.svg',
      url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
    },
  ],
  uploadAvatarModelValue: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
  uploadSingleImageModelValue: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
  inputModelValue: 'input',
  inputNumberModelValue: 2,
  switchModelValue: true,
  autocompleteModelValue: '',
  colorPickerModelValue: '',
  datePickerModelValue: '',
  timeSelectModelValue: '11:20',
  treeModelValue: [9],
  treeSyncModelValue: [9],
  treeSyncInitModelValue: [9],
  treeDefaultCheckedKeys: [1],
  cascaderOptions: [
    {
      id: 'guide',
      name: 'Guide',
      children: [
        {
          id: 'disciplines',
          name: 'Disciplines',
          children: [
            {
              id: 'consistency',
              name: 'Consistency',
            },
            {
              id: 'feedback',
              name: 'Feedback',
            },
            {
              id: 'efficiency',
              name: 'Efficiency',
            },
            {
              id: 'controllability',
              name: 'Controllability',
            },
          ],
        },
        {
          id: 'navigation',
          name: 'Navigation',
          children: [
            {
              id: 'side nav',
              name: 'Side Navigation',
            },
            {
              id: 'top nav',
              name: 'Top Navigation',
            },
          ],
        },
      ],
    },
    {
      id: 'component',
      name: 'Component',
      children: [
        {
          id: 'basic',
          name: 'Basic',
          children: [
            {
              id: 'layout',
              name: 'Layout',
            },
            {
              id: 'color',
              name: 'Color',
            },
            {
              id: 'typography',
              name: 'Typography',
            },
            {
              id: 'icon',
              name: 'Icon',
            },
            {
              id: 'button',
              name: 'Button',
            },
          ],
        },
        {
          id: 'form',
          name: 'Form',
          children: [
            {
              id: 'radio',
              name: 'Radio',
            },
            {
              id: 'checkbox',
              name: 'Checkbox',
            },
            {
              id: 'input',
              name: 'Input',
            },
            {
              id: 'input-number',
              name: 'InputNumber',
            },
            {
              id: 'select',
              name: 'Select',
            },
            {
              id: 'cascader',
              name: 'Cascader',
            },
            {
              id: 'switch',
              name: 'Switch',
            },
            {
              id: 'slider',
              name: 'Slider',
            },
            {
              id: 'time-picker',
              name: 'TimePicker',
            },
            {
              id: 'date-picker',
              name: 'DatePicker',
            },
            {
              id: 'datetime-picker',
              name: 'DateTimePicker',
            },
            {
              id: 'upload',
              name: 'Upload',
            },
            {
              id: 'rate',
              name: 'Rate',
            },
            {
              id: 'form',
              name: 'Form',
            },
          ],
        },
        {
          id: 'data',
          name: 'Data',
          children: [
            {
              id: 'table',
              name: 'Table',
            },
            {
              id: 'tag',
              name: 'Tag',
            },
            {
              id: 'progress',
              name: 'Progress',
            },
            {
              id: 'tree',
              name: 'Tree',
            },
            {
              id: 'pagination',
              name: 'Pagination',
            },
            {
              id: 'badge',
              name: 'Badge',
            },
          ],
        },
        {
          id: 'notice',
          name: 'Notice',
          children: [
            {
              id: 'alert',
              name: 'Alert',
            },
            {
              id: 'loading',
              name: 'Loading',
            },
            {
              id: 'message',
              name: 'Message',
            },
            {
              id: 'message-box',
              name: 'MessageBox',
            },
            {
              id: 'notification',
              name: 'Notification',
            },
          ],
        },
        {
          id: 'navigation',
          name: 'Navigation',
          children: [
            {
              id: 'menu',
              name: 'Menu',
            },
            {
              id: 'tabs',
              name: 'Tabs',
            },
            {
              id: 'breadcrumb',
              name: 'Breadcrumb',
            },
            {
              id: 'dropdown',
              name: 'Dropdown',
            },
            {
              id: 'steps',
              name: 'Steps',
            },
          ],
        },
        {
          id: 'others',
          name: 'Others',
          children: [
            {
              id: 'dialog',
              name: 'Dialog',
            },
            {
              id: 'tooltip',
              name: 'Tooltip',
            },
            {
              id: 'popover',
              name: 'Popover',
            },
            {
              id: 'card',
              name: 'Card',
            },
            {
              id: 'carousel',
              name: 'Carousel',
            },
            {
              id: 'collapse',
              name: 'Collapse',
            },
          ],
        },
      ],
    },
    {
      id: 'resource',
      name: 'Resource',
      children: [
        {
          id: 'axure',
          name: 'Axure Components',
        },
        {
          id: 'sketch',
          name: 'Sketch Templates',
        },
        {
          id: 'docs',
          name: 'Design Documentation',
        },
      ],
    },
  ],
  checkboxGroupOptions: [
    {
      id: 'consistency',
      name: 'Consistency',
    },
    {
      id: 'feedback',
      name: 'Feedback',
    },
    {
      id: 'efficiency',
      name: 'Efficiency',
    },
    {
      id: 'controllability',
      name: 'Controllability',
    },
  ],
  radioGroupOptions: [
    {
      id: 'consistency',
      name: 'Consistency',
    },
    {
      id: 'feedback',
      name: 'Feedback',
    },
    {
      id: 'efficiency',
      name: 'Efficiency',
    },
    {
      id: 'controllability',
      name: 'Controllability',
    },
  ],
  selectOptions: [
    {
      id: 'consistency',
      name: 'Consistency',
    },
    {
      id: 'feedback',
      name: 'Feedback',
    },
    {
      id: 'efficiency',
      name: 'Efficiency',
    },
    {
      id: 'controllability',
      name: 'Controllability',
    },
  ],
  selectGroupOptions: [
    {
      name: 'popular cities',
      options: [
        {
          id: 'shanghai',
          name: 'Shanghai',
        },
        {
          id: 'beijing',
          name: 'Beijing',
        },
      ],
    },
    {
      name: 'City name',
      options: [
        {
          id: 'chengdu',
          name: 'Chengdu',
        },
        {
          id: 'shenzhen',
          name: 'Shenzhen',
        },
        {
          id: 'guangzhou',
          name: 'Guangzhou',
        },
        {
          id: 'dalian',
          name: 'Dalian',
        },
      ],
    },
  ],
  menuOptions: [
    {
      id: '1',
      name: '一级菜单1',
      type: 'menu',
      children: [
        {
          id: '1-1',
          name: '二级菜单1-1',
          type: 'menu',
          children: [
            {
              id: '1-1-1',
              name: '三级菜单1-1-1',
              type: 'page'
            },
            {
              id: '1-1-2',
              name: '三级菜单1-1-2',
              type: 'page'
            },
            {
              id: '1-1-3',
              name: '三级菜单1-1-3',
              type: 'page'
            },
            {
              id: '1-1-4',
              name: '三级菜单1-1-4',
              type: 'page'
            },
          ],
        },
        {
          id: '1-2',
          name: '二级菜单1-2',
          children: [
            {
              id: '1-2-1',
              name: '三级菜单1-2-1',
              type: 'page'
            },
            {
              id: '1-2-2',
              name: '三级菜单1-2-2',
              type: 'page'
            },
          ],
        },
      ],
    },
    {
      id: '2',
      name: '一级菜单2',
      type: 'menu',
      children: [
        {
          id: '2-1',
          name: '二级菜单2-1',
          type: 'menu',
          children: [
            {
              id: '2-1-1',
              name: '三级菜单2-1-1',
              type: 'page',
            },
            {
              id: '2-1-2',
              name: '三级菜单2-1-2',
              type: 'page',
            },
            {
              id: '2-1-3',
              name: '三级菜单2-1-3',
              type: 'page',
            },
            {
              id: '2-1-4',
              name: '三级菜单2-1-4',
              type: 'page',
            },
            {
              id: '2-1-5',
              name: '三级菜单2-1-5',
              type: 'page',
            },
          ],
        },
        {
          id: '2-2',
          name: '二级菜单2-2',
          type: 'menu',
          children: [
            {
              id: '2-2-1',
              name: '三级菜单2-2-1',
              type: 'page',
            },
            {
              id: '2-2-2',
              name: '三级菜单2-2-2',
              type: 'page',
            },
            {
              id: '2-2-3',
              name: '三级菜单2-2-3',
              type: 'page',
            },
            {
              id: '2-2-4',
              name: '三级菜单2-2-4',
              type: 'page',
            },
            {
              id: '2-2-5',
              name: '三级菜单2-2-5',
              type: 'page',
            },
            {
              id: '2-2-6',
              name: '三级菜单2-2-6',
              type: 'page',
            },
            {
              id: '2-2-7',
              name: '三级菜单2-2-7',
              type: 'page',
            },
            {
              id: '2-2-8',
              name: '三级菜单2-2-8',
              type: 'page',
            },
            {
              id: '2-2-9',
              name: '三级菜单2-2-9',
              type: 'page',
            },
            {
              id: '2-2-10',
              name: '三级菜单2-2-10',
              type: 'page',
            },
            {
              id: '2-2-11',
              name: '三级菜单2-2-11',
              type: 'page',
            },
            {
              id: '2-2-12',
              name: '三级菜单2-2-12',
              type: 'page',
            },
            {
              id: '2-2-13',
              name: '三级菜单2-2-13',
              type: 'page',
            },
            {
              id: '2-2-14',
              name: '三级菜单2-2-14',
              type: 'page',
            },
          ],
        },
        {
          id: '2-3',
          name: '二级菜单2-3',
          type: 'menu',
          children: [
            {
              id: '2-3-1',
              name: '三级菜单2-3-1',
              type: 'page',
            },
            {
              id: '2-3-2',
              name: '三级菜单2-3-2',
              type: 'page',
            },
            {
              id: '2-3-3',
              name: '三级菜单2-3-3',
              type: 'page',
            },
            {
              id: '2-3-4',
              name: '三级菜单2-3-4',
              type: 'page',
            },
            {
              id: '2-3-5',
              name: '三级菜单2-3-5',
              type: 'page',
            },
            {
              id: '2-3-5',
              name: '三级菜单2-3-5',
              type: 'page',
            },
          ],
        },
        {
          id: '2-4',
          name: '二级菜单2-4',
          type: 'menu',
          children: [
            {
              id: '2-4-1',
              name: '三级菜单2-4-1',
              type: 'page',
            },
            {
              id: '2-4-2',
              name: '三级菜单2-4-2',
              type: 'page',
            },
            {
              id: '2-4-3',
              name: '三级菜单2-4-3',
              type: 'page',
            },
            {
              id: '2-4-4',
              name: '三级菜单2-4-4',
              type: 'page',
            },
            {
              id: '2-4-5',
              name: '三级菜单2-4-5',
              type: 'page',
            },
          ],
        },
        {
          id: '2-5',
          name: '二级菜单2-5',
          type: 'menu',
          children: [
            {
              id: '2-5-1',
              name: '三级菜单2-5-1',
              type: 'page',
            },
            {
              id: '2-5-2',
              name: '三级菜单2-5-2',
              type: 'page',
            },
            {
              id: '2-5-3',
              name: '三级菜单2-5-3',
              type: 'page',
            },
            {
              id: '2-5-4',
              name: '三级菜单2-5-4',
              type: 'page',
            },
            {
              id: '2-5-5',
              name: '三级菜单2-5-5',
              type: 'page',
            },
          ],
        },
        {
          id: '2-6',
          name: '二级菜单2-6',
          type: 'menu',
          children: [
            {
              id: '2-6-1',
              name: '三级菜单2-6-1',
              type: 'page',
            },
            {
              id: '2-6-2',
              name: '三级菜单2-6-2',
              type: 'page',
            },
            {
              id: '2-6-3',
              name: '三级菜单2-6-3',
              type: 'page',
            },
            {
              id: '2-6-4',
              name: '三级菜单2-6-4',
              type: 'page',
            },
            {
              id: '2-6-5',
              name: '三级菜单2-6-5',
              type: 'page',
            },
            {
              id: '2-6-6',
              name: '三级菜单2-6-6',
              type: 'page',
            },
          ],
        },
      ],
    },
    {
      id: '3',
      name: '一级菜单3',
      icon: 'location',
      type: 'menu',
      children: [
        {
          id: '3-1',
          name: '二级菜单3-1',
          type: 'page',
        },
        {
          id: '3-2',
          name: '二级菜单3-2',
          type: 'page',
        },
        {
          id: '3-3',
          icon: 'location',
          name: '二级菜单3-3',
          type: 'page',
        },
      ],
    },
  ],
  carouselOptions: [
    {
      name: 'name',
      value: 'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg'
    },
    {
      name: 'name',
      value: 'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg'
    },
    {
      name: 'name',
      value: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg'
    }
  ],
  tableOptions: [
    {
      id: '12987122',
      name: 'Tom',
      amount1: '234',
      amount2: '3.2',
      amount3: 10,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
    },
    {
      id: '12987123',
      name: 'Tom',
      amount1: '165',
      amount2: '4.43',
      amount3: 12,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
    },
    {
      id: '12987124',
      name: 'Tom',
      amount1: '324',
      amount2: '1.9',
      amount3: 9,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
    },
    {
      id: '12987125',
      name: 'Tom',
      amount1: '621',
      amount2: '2.2',
      amount3: 17,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
    },
    {
      id: '12987126',
      name: 'Tom',
      amount1: '539',
      amount2: '4.1',
      amount3: 15,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg1',
    },
  ],
  tableColumns: [
    {
      prop: 'id',
      label: 'id',
    },
    {
      prop: 'image',
      label: '图片',
      columnView: 'image'
    },
    {
      prop: 'name',
      label: '姓名',
    },
    {
      prop: 'amount1',
      label: '金额',
      nestColumns:[
        {
          prop: 'amount2',
          label: '金额2',
        },{
          prop: 'amount3',
          label: '金额3',
        }
      ]
    },
  ],
  tablePageOptions: [
    {
      id: '12987122',
      name: 'Tom',
      amount1: '234',
      amount2: '3.2',
      amount3: 10,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
    },
    {
      id: '12987123',
      name: 'Tom',
      amount1: '165',
      amount2: '4.43',
      amount3: 12,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
    },
    {
      id: '12987124',
      name: 'Tom',
      amount1: '324',
      amount2: '1.9',
      amount3: 9,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
    },
    {
      id: '12987125',
      name: 'Tom',
      amount1: '621',
      amount2: '2.2',
      amount3: 17,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
    },
    {
      id: '12987126',
      name: 'Tom',
      amount1: '539',
      amount2: '4.1',
      amount3: 15,
      image: 'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg1',
    },
  ],
  formItemDetailForm: {
    input: 'input',
    elInput: 'input'
  },
  formComps: [
    {
      field: {
        name: 'input',
        value: 'input'
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '输入框',
          required: true,
          prop: 'input'
        }
      }
    },
    {
      field: {
        name: 'cascaderModelValue',
        value: 'consistency'
      },
      element: {
        comp: 'pt-cascader',
        compProps: {
          dataMethod: ptCascaderHttpApi,
          dataMethodResultHandle: directDataMethodResultHandle,
        },
        formItemProps: {
          label: '级联选择',
          required: true,
        }
      }
    },
    {
      field: {
        name: 'checkboxGroupModelValue',
        value: ['consistency']
      },
      element: {
        comp: 'pt-checkbox-group',
        compProps: {
          dataMethod: ptCheckboxGroupHttpApi,
          dataMethodResultHandle: directDataMethodResultHandle,
        },
        formItemProps: {
          label: '复选框组',
          required: true,
        }
      }
    },
    {
      field: {
        name: 'checkboxModelValue',
        value: false
      },
      element: {
        comp: 'pt-checkbox',
        formItemProps: {
          label: '复选框',
          required: true,
        }
      }
    },
  ],
  treeOptions: [
    {
      id: 1,
      label: 'Level one 1',
      children: [
        {
          id: 4,
          label: 'Level two 1-1',
          children: [
            {
              id: 9,
              label: 'Level three 1-1-1',
            },
            {
              id: 10,
              label: 'Level three 1-1-2',
            },
          ],
        },
      ],
    },
    {
      id: 2,
      label: 'Level one 2',
      children: [
        {
          id: 5,
          label: 'Level two 2-1',
        },
        {
          id: 6,
          label: 'Level two 2-2',
        },
      ],
    },
    {
      id: 3,
      label: 'Level one 3',
      children: [
        {
          id: 7,
          label: 'Level two 3-1',
        },
        {
          id: 8,
          label: 'Level two 3-2',
        },
      ],
    },
  ],
})
watch(
() => reactiveData.cascaderModelValue,
    (val) => {
      console.log('cascaderModelValue',val)
    }
)
watch(
    () => reactiveData.checkboxModelValue,
    (val) => {
      console.log('checkboxModelValue',val)
    }
)
watch(
    () => reactiveData.checkboxGroupModelValue,
    (val) => {
      console.log('checkboxGroupModelValue',val)
    }
)
watch(
    () => reactiveData.radioGroupModelValue,
    (val) => {
      console.log('radioGroupModelValue',val)
    }
)
watch(
    () => reactiveData.selectModelValue,
    (val) => {
      console.log('selectModelValue',val)
    }
)
watch(
    () => reactiveData.selectGroupModelValue,
    (val) => {
      console.log('selectGroupModelValue',val)
    }
)
watch(
    () => reactiveData.uploadModelValue,
    (val) => {
      console.log('uploadModelValue',val)
    }
)
watch(
    () => reactiveData.inputModelValue,
    (val) => {
      console.log('inputModelValue',val)
    }
)
watch(
    () => reactiveData.colorPickerModelValue,
    (val) => {
      console.log('colorPickerModelValue',val)
    }
)
watch(
    () => reactiveData.datePickerModelValue,
    (val) => {
      console.log('datePickerModelValue',val)
    }
)
watch(
    () => reactiveData.timeSelectModelValue,
    (val) => {
      console.log('timeSelectModelValue',val)
    }
)
watch(
    () => reactiveData.treeModelValue,
    (val) => {
      console.log('treeModelValue',val)
    }
)
watch(
    () => reactiveData.treeSyncModelValue,
    (val) => {
      console.log('treeSyncModelValue',val)
    }
)
watch(
    () => reactiveData.treeSyncInitModelValue,
    (val) => {
      console.log('treeSyncInitModelValue',val)
    }
)
// 挂载
onMounted(() => {

})

// 方法

function ptButtonHttpApi(){
  return new Promise((resolve, reject) => {
    setTimeout(()=>{
      resolve(22222)
    },1000)
  })
}
const ptButtonHttpApiResult = (p)=>{
  p.then(res => {
    console.log(res)
  }).catch(error => {
    console.error(error)
  }).finally(()=>{
    console.log('2222333')
  })
}
function ptCascaderHttpApi(){
  return new Promise((resolve, reject) => {
    setTimeout(()=>{
      resolve(reactiveData.cascaderOptions)
    },2000)
  })
}
function directDataMethodResultHandle({success}){
  return success
}
function ptCheckboxGroupHttpApi(){
  return new Promise((resolve, reject) => {
    setTimeout(()=>{
      resolve(reactiveData.checkboxGroupOptions)
    },1011)
  })
}
function ptRadioGroupHttpApi(){
  return new Promise((resolve, reject) => {
    setTimeout(()=>{
      resolve(reactiveData.radioGroupOptions)
    },1100)
  })
}
function ptSelectHttpApi(){
  return new Promise((resolve, reject) => {
    setTimeout(()=>{
      resolve(reactiveData.selectOptions)
    },1110)
  })
}
function ptSelectGroupHttpApi(){
  return new Promise((resolve, reject) => {
    setTimeout(()=>{
      resolve(reactiveData.selectGroupOptions)
    },1100)
  })
}

/* remote */
function ptSelectRemoteHttpApi(){
  return new Promise((resolve, reject) => {
    setTimeout(()=>{
      resolve(reactiveData.selectOptions)
    },1100)
  })
}
const selectRemoteLoading = ref(false)
const selectRemoteOptions = ref([])
const selectRemoteMethod = (query) => {
  if (query) {
    selectRemoteLoading.value = true
    setTimeout(() => {
      selectRemoteLoading.value = false
      selectRemoteOptions.value = reactiveData.selectOptions.filter((item) => {
        return item.name.toLowerCase().includes(query.toLowerCase())
      })
    }, 2000)
  } else {
    selectRemoteOptions.value = []
  }
}
const selectRemoteMethodPromise = (query) => {

  return new Promise((resolve, reject) => {
    if (query) {
      setTimeout(() => {
        let r = reactiveData.selectOptions.filter((item) => {
          return item.name.toLowerCase().includes(query.toLowerCase())
        })
        resolve(r)
      }, 2000)
    }else {
      resolve([])
    }

  })
}
const tablePageMethodPromise = (query = {pageNo:1,pageSize: 2}) => {

  return new Promise((resolve, reject) => {

    if (query) {
      setTimeout(() => {
        let r = {
          pageNo: query.pageNo,
          pageSize: 2,
          totalCount: reactiveData.tablePageOptions.length,
          data: []
        }
        let index = (r.pageNo -1) * r.pageSize
        for (let i = index; i < index + r.pageSize; i++) {
          if(r.totalCount -1 >= i){
            r.data.push(reactiveData.tablePageOptions[i])
          }
        }
        resolve({data: r})
      }, 2000)
    }else {
      resolve([])
    }

  })
}
const formMethodPromise = () => {

  return new Promise((resolve, reject) => {

    setTimeout(() => {
      let r = {
        id: '123',
        name: 'name',
        gender: 'f',
        age: 20
      }
      resolve({data: r})
    }, 2000)

  })
}
const menuSelect = (index,indexPath) => {
  console.log(index,indexPath)
}
//treeOptions
const treeRemoteMethodPromise = (query) => {

  return new Promise((resolve, reject) => {
    setTimeout(() => {
      let data = reactiveData.treeOptions
      resolve({data})
    }, 2000)

  })
}
const treeRemoteMethodInitPromise = (query) => {

  return new Promise((resolve, reject) => {
    setTimeout(() => {
      let data = [10,9]
      resolve({data})
    }, 1000)

  })
}
const treeChange = function (a){
  console.log('treeChange',a)
}
const treeCheck = function (a,b){
  console.log('treeCheck',a,b)
}
const treeCheckChange = function (){
  console.log('treeCheckChange',arguments)

}
const treeCurrentChange = function (){
  console.log('treeCurrentChange',arguments)

}
</script>

<template>
<PtBackendManagementLayout>
  <template #headerAside>
    <PtLogo></PtLogo>
  </template>


  <div>
    <div>link 效果,图标可根据文字自动识别 指定 permission属性 会自动判断权限</div>
    <PtButton method-confirm-text="确定要查询吗" view="link" permission="ss" noPermissionView="alert" type="primary" :method="ptButtonHttpApi" @method-result="ptButtonHttpApiResult">查询</PtButton>
  </div>

  <div>
    <div>按钮 效果,图标可根据文字自动识别 指定 confirm-text 属性确认提示语，弹窗确认框</div>
    <PtButton method-confirm-text="确定要重置吗" view="button"  type="primary" :method="ptButtonHttpApi" @method-result="ptButtonHttpApiResult">重置</PtButton>
  </div>


  <div>
    <div>PtCascader</div>
    <PtCascader v-model="reactiveData.cascaderModelValue" :props="{multiple: true}" filterable noPermissionView="alert" :dataMethod="ptCascaderHttpApi" :dataMethodResultHandle="directDataMethodResultHandle">
    </PtCascader>
  </div>

  <div>
    <div>PtCheckbox</div>
    <PtCheckbox v-model="reactiveData.checkboxModelValue"  noPermissionView="alert">
    </PtCheckbox>
  </div>
<div>
  <div>PtCheckboxGroup</div>
  <PtCheckboxGroup v-model="reactiveData.checkboxGroupModelValue" :min="1" :max="2"  permission="ss" noPermissionView="alert" :dataMethod="ptCheckboxGroupHttpApi" :dataMethodResultHandle="directDataMethodResultHandle">
  </PtCheckboxGroup>
</div>

  <div>
    <div>PtRadioGroup</div>
    <PtRadioGroup v-model="reactiveData.radioGroupModelValue" noPermissionView="alert" :dataMethod="ptRadioGroupHttpApi" :dataMethodResultHandle="directDataMethodResultHandle">
    </PtRadioGroup>
  </div>

  <div>
    <div>PtSelect 基本使用</div>
    <PtSelect v-model="reactiveData.selectModelValue" permission="ss"  noPermissionView="alert" :dataMethod="ptSelectHttpApi" :dataMethodResultHandle="directDataMethodResultHandle">
    </PtSelect>
  </div>


  <div>
    <div>PtSelect 多选</div>
    <PtSelect v-model="reactiveData.selectGroupModelValue"  multiple noPermissionView="alert" :dataMethod="ptSelectGroupHttpApi" :dataMethodResultHandle="directDataMethodResultHandle">
  </PtSelect>
  </div>

  <div>
    <div>PtSelect 远程搜索 原始用法,尝试输入 e</div>
    <PtSelect v-model="reactiveData.selectRemoteModelValue" :options="selectRemoteOptions" :loading="selectRemoteLoading" remote filterable :remoteMethod="selectRemoteMethod" noPermissionView="alert" :dataMethod="ptSelectRemoteHttpApi" :dataMethodResultHandle="directDataMethodResultHandle">
    </PtSelect>
  </div>
  <div>
    <div>PtSelect 远程搜索，扩展后,尝试输入 e</div>
    <PtSelect v-model="reactiveData.selectRemoteModelValue" :options="selectRemoteOptions" :loading="selectRemoteLoading" remote filterable :remoteMethod="selectRemoteMethodPromise" :remoteMethodResultHandle="directDataMethodResultHandle" noPermissionView="alert" :dataMethod="ptSelectRemoteHttpApi" :dataMethodResultHandle="directDataMethodResultHandle">
    </PtSelect>
  </div>
  <div>
    <div>PtUpload 基础上传功能</div>
    <PtUpload v-model="reactiveData.uploadModelValue"  noPermissionView="alert" :autoUpload="true" action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15">
    </PtUpload>
  </div>
  <div>
    <div>PtUpload 基础上传功能，手动</div>
    <PtUpload v-model="reactiveData.uploadModelValue"  noPermissionView="alert" :autoUpload="false" action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15">
    </PtUpload>
  </div>
  <div>
    <div>PtUpload 卡片</div>
    <PtUpload v-model="reactiveData.uploadModelValue"  noPermissionView="alert" :autoUpload="true" list-type="picture-card" action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15">
    </PtUpload>
  </div>
  <div>
    <div>PtUploadAvatar 上传头像</div>
    <PtUploadAvatar v-model="reactiveData.uploadAvatarModelValue"  noPermissionView="alert" action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15">
    </PtUploadAvatar>
  </div>
  <div>
    <div>PtUploadSingleImage 上传单个图片</div>
    <PtUploadSingleImage v-model="reactiveData.uploadSingleImageModelValue"  noPermissionView="alert" action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15">
    </PtUploadSingleImage>
  </div>
  <div>
    <div>PtInput 输入框</div>
    <PtInput v-model="reactiveData.inputModelValue" permission="ss" >
      <template #prepend>Http://</template>
    </PtInput>
  </div>

  <div>
    <div>PtInputNumber 输入框</div>
    <PtInputNumber v-model="reactiveData.inputNumberModelValue" permission="ss" noPermissionView="alert">
    </PtInputNumber>
  </div>


  <div>
    <div>PtSwitch 开关</div>
    <PtSwitch v-model="reactiveData.switchModelValue"  permission="ss" noPermissionView="alert" methodConfirmText="确认要切换吗">
    </PtSwitch>
  </div>

  <div>
    <div>PtAutocomplete 自动补全输入框</div>
    <PtAutocomplete v-model="reactiveData.autocompleteModelValue"  noPermissionView="alert">
    </PtAutocomplete>
  </div>

  <div>
    <div>PtColorPicker 颜色选择器</div>
    <PtColorPicker v-model="reactiveData.colorPickerModelValue" permission="ss"  noPermissionView="alert">
    </PtColorPicker>
  </div>

  <div>
    <div>PtDatePicker 日期选择器</div>
    <PtDatePicker v-model="reactiveData.datePickerModelValue"  noPermissionView="alert">
    </PtDatePicker>
  </div>

  <div>
    <div>PtDatePicker 日期时间选择器</div>
    <PtDatePicker v-model="reactiveData.datePickerModelValue" type="datetime"  noPermissionView="alert">
    </PtDatePicker>
  </div>

  <div>
    <div>PtTimePicker 时间选择器</div>
    <PtTimePicker v-model="reactiveData.datePickerModelValue"  noPermissionView="alert">
    </PtTimePicker>
  </div>

  <div>
    <div>PtTimeSelect 时间选择器</div>
    <PtTimeSelect v-model="reactiveData.timeSelectModelValue" permission="ss" noPermissionView="alert">
    </PtTimeSelect>
  </div>
  <div>
    <div>PtMenu 菜单</div>
    <PtMenu :options="reactiveData.menuOptions" @select="menuSelect">
    </PtMenu>
  </div>

  <div>
    <div>PtCarousel 跑马灯</div>
    <PtCarousel :options="reactiveData.carouselOptions">
    </PtCarousel>
  </div>
  <div>
    <div>PtImage 基本图片，默认</div>
    <PtImage style="width: 300px;height:300px;" src="https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg">
    </PtImage>
  </div>
  <div>
    <div>PtImage 图片，全屏预览</div>
    <PtImage style="width: 300px;height:300px;" previewView="default" src="https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg">
    </PtImage>
  </div>
  <div>
    <div>PtImage 图片，跑马灯预览</div>
    <PtImage style="width: 300px;height:300px;" previewView="dialog" src="https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg">
    </PtImage>
  </div>
  <div>
    <div>PtTable 表格，支持自定义表头组合</div>
    <PtTable :options="reactiveData.tableOptions" :columns="reactiveData.tableColumns">
    </PtTable>
  </div>
  <div>
    <div>PtTable 表格，分页</div>
    <PtTable :dataMethod="tablePageMethodPromise" :columns="reactiveData.tableColumns">
    </PtTable>
  </div>

  <div>
    <div>PtFormItemDetail 表单项详情，名字容易误解，</div>
    <PtFormItemDetail :form="reactiveData.formItemDetailForm" :formData="{}" prop="input" comp="txt">
    </PtFormItemDetail>
    <PtFormItemDetail :form="reactiveData.formItemDetailForm"  :formData="{}" prop="input" placeholder="xxxx" comp="el-input">
    </PtFormItemDetail>
    <input/>
  </div>

  <div>
    <div>PtForm 表单</div>
    <PtForm :layout="2"  :comps="reactiveData.formComps">
    </PtForm>
  </div>

  <div>
    <div>PtTree 树</div>
    <el-button @click="reactiveData.treeDefaultCheckedKeys = [2]">选中2</el-button>
    <PtTree show-checkbox v-model="reactiveData.treeModelValue"
            :defaultCheckedKeys="reactiveData.treeDefaultCheckedKeys"
            enableFilter
            @change="treeChange"
            @check="treeCheck"
            permission="sss"
            @treeCheckChange="treeCheckChange"
            @treeCurrentChange="treeCurrentChange"
            :data="reactiveData.treeOptions" :props="{label: 'label'}">
    </PtTree>
  </div>
  <div>
    <div>PtTree 异步加载数据</div>
    <PtTree show-checkbox v-model="reactiveData.treeSyncModelValue"

            @change="treeChange"
            @check="treeCheck"
            @treeCheckChange="treeCheckChange"
            @treeCurrentChange="treeCurrentChange"
            :dataMethod="treeRemoteMethodPromise"
            :props="{label: 'label'}">
    </PtTree>
  </div>
  <div>
    <div>PtTree 异步加载数据，并异步加载选中数据</div>
    <PtTree show-checkbox v-model="reactiveData.treeSyncInitModelValue"

            @change="treeChange"
            @check="treeCheck"
            @treeCheckChange="treeCheckChange"
            @treeCurrentChange="treeCurrentChange"
            :dataMethod="treeRemoteMethodPromise"
            :dataInitMethod="treeRemoteMethodInitPromise"
            :props="{label: 'label'}">
    </PtTree>
  </div>
  <div>
    <div>百度地图</div>
<!--  一定要设置一个高度  -->
    <PtBaiduMap style="height:500px;">
    </PtBaiduMap>
  </div>
  <div>
    <div>百度地图地址解析</div>
    <!--  一定要设置一个高度  -->
    <PtLocationGeoMap>
    </PtLocationGeoMap>
  </div>
  <div>
    <div>PtSecretText 敏感信息 </div>
    <!--  一定要设置一个高度  -->
    <PtSecretText modelValue="sdsdsdsfsd" :permission="()=>false" :permissions="['www']">
    </PtSecretText>
  </div>
</PtBackendManagementLayout>

</template>
<style scoped>

</style>
