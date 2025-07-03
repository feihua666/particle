import {useRemoteSelectCmsContentCompItem, useSelectCmsSiteCompItem} from "../cmsSiteCompItem";

export const pageFormItems = [
  useSelectCmsSiteCompItem({}),
  useRemoteSelectCmsContentCompItem({props:{},required: false}),
      {
        field: {
          name: 'mediaType',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '媒体类型',

          },
          compProps: {
            clearable: true,
          }
        }
      },
]
export const useAddPageFormItems = ({props})=> {
  return [

    useSelectCmsSiteCompItem({required: true}),
    useRemoteSelectCmsContentCompItem({props,required: true}),

    {
      field: {
        name: 'content',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '文本内容',
          required: true,
          displayBlock: true,
        },
        compProps: {
          clearable: true,
          type: 'textarea',
          rows: 20,
        }
      }
    },


    {
      field: {
        name: 'imageUrl',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '图片地址',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'imageDescription',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '图片描述',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'imageUrl1',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '图片地址1',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'imageDescription1',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '图片描述1',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'imageUrl2',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '图片地址2',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'imageDescription2',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '图片描述2',

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
          tips: '文件名称,一般用于下载场景，选填',

        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'fileUrl',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '文件地址',
          tips: '文件地址和文件名称对象,一般用于下载场景，提供下载链接，选填',
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'fileSize',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '文件大小',
          tips: '单位字节，文件大小,一般用于下载场景，提供下载文件大小，选填',
        },
        compProps: {
        }
      }
    },


    {
      field: {
        name: 'fileSizeStr',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '文件大小字符串',
          tips: '对单位字节的可读性字符串，如：20MB、3G等',
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'mediaType',
      },
      element: {
        comp: 'el-input',
        formItemProps: {
          label: '媒体类型',
          tips: '对内容的一个标识，用户自定义，如：pdf、word、视频、压缩文件等',
        },
        compProps: {
          clearable: true,
        }
      }
    },


    {
      field: {
        name: 'seq',
        value: 1000
      },
      element: {
        comp: 'el-input-number',
        formItemProps: {
          label: '排序',
          tips: '针对一个内容，多个媒体内容时，按该值从小到大排序',
        },
        compProps: {
          clearable: true,
        }
      }
    },


  ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

