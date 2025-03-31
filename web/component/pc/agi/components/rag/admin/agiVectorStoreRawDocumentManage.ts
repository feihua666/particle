import {ElMessage} from 'element-plus'
import {nextTick} from "vue"
import {getItems} from "../../../../dict/api/front/dictFrontApi";

/**
 * key和后端类对应：com.particle.global.ai.enums.AIDocumentType
 */
const documentTypeExtensionMapping = {
  word: ['.doc', '.docx'], // Word 文档
  excel: ['.xls', '.xlsx'], // Excel 文档
  pdf: ['.pdf'], // PDF 文档
  html: ['.html', '.htm'], // HTML 文档
  xml: ['.xml'], // XML 文档
  markdown: ['.md', '.markdown'], // Markdown 文档
  txt: ['.txt'], // 纯文本文件
  json: ['.json'], // JSON 文件
  ppt: ['.ppt', '.pptx'], // PowerPoint 文档
}
const getDocumentTypeByExtension = (extension) => {
  return Object.keys(documentTypeExtensionMapping).find(key => documentTypeExtensionMapping[key].includes(extension))
}
let alert = (message,type='success')=>{
  ElMessage({
    showClose: true,
    message: message,
    type: type,
    showIcon: true,
    grouping: true
  })
}
export const pageFormItems = [
      {
        field: {
          name: 'name',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '名称',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'fileName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '文件名称',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配',
          }
        }
      },
      {
        field: {
          name: 'typeDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '类型',
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: 'agi_raw_document_type'}
          }
        }
      },

]
export const addPageFormItems = [
  {
    field: {
      name: 'emptyUrls',
      value: [],
      valueChange: ({form,formData,newValue}) =>{
        nextTick(()=>{
          if (newValue && newValue.length > 0 && newValue[0].response && newValue[0].response.absoluteHttpUrl) {
            form.url = newValue[0].response.absoluteHttpUrl
            form.fileName = newValue[0].response.originFileName
            form.name = newValue[0].response.originFileName
            let extension = newValue[0].response.extension
            let documentType = getDocumentTypeByExtension(extension)
            if (documentType) {
              getItems({groupCode: 'agi_raw_document_type'}).then(res => {
                form.typeDictId = res.data.data.find(item => item.value === documentType).id
              })
            }
          }else{
            form.url = null
            form.fileName = null
            form.name = null
            form.typeDictId = null
          }
        })
      }
    },
    element: {
      comp: 'PtUpload',
      formItemProps: {
        label: '上传文件',
        required: ({form}) => !!!form.url,
        displayBlock: true,
      },
      compProps: {
        drag: true,
        autoUpload: true,
        limit: 1,
        dragTip: '将文件拖到此处或<em>点击上传</em>',
        tipTxt: '只能上传一个文件，上传后，下方的表单将会自动填充',
        onExceed: (files,uploadFiles)=>{
          alert('最多只能选择一个文件,如要重新选择，请先删除之前的文件','error')
        }
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
            tips: '一个标识文档的名称，方便维护'
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'fileName',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '文件名称',
            tips: '上传文件时必填，存储上传文件的原始文件名称，带扩展名'
          },
          compProps: {
            clearable: true,
          }
        }
      },


  {
    field: {
      name: 'typeDictId',
    },
    element: {
      comp: 'PtDictFrontSelect',
      formItemProps: {
        label: '类型',
        required: true,
        tips: '请严格按上传的文件对应的类型填写，否则可能导致解析错误'
      },
      compProps: {
        // 字典查询
        dictParam: {groupCode: 'agi_raw_document_type'}
      }
    }
  },


      {
        field: {
          name: 'url',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '地址',
            tips: '上传文件后自动生成的地址，也可以手动填写一个供下载文件的地址'
          },
          compProps: {
            clearable: true,
          }
        }
      },

      {
        field: {
          name: 'remark',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '描述',
            tips: '备注说明性文字，方便维护'
          },
          compProps: {
            clearable: true,
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

