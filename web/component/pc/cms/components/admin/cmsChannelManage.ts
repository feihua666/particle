import {
    useCascaderCmsChannelCompItem,
    useRemoteSelectCmsContentCompItem,
    useSelectCmsSiteCompItem
} from "../cmsCompItem";
import {treeQueryComps} from "../../../treeQueryComps";

export const pageFormItems = [
    useSelectCmsSiteCompItem({}),
      {
        field: {
          name: 'code',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '栏目编码',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
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
            label: '栏目名称',

          },
          compProps: {
            clearable: true,
            placeholder: '左前缀匹配'
          }
        }
      },
    {
        field: {
            name: 'title',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点标题',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    useCascaderCmsChannelCompItem({}),
    ...treeQueryComps
]
export const useAddPageFormItems = ({props}) => {
    return [

        useSelectCmsSiteCompItem({required: true}),

        {
            field: {
                name: 'code',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '栏目编码',
                    tips: '编码唯一，用来唯一标识站点，注意需要保持url命令规则'

                },
                compProps: {
                    clearable: true,
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
                    label: '栏目名称',
                    required: true,
                    tips: '用于管理栏目的名称，如果 栏目标题 为空时会使用栏目名称作为标题'
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'title',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '栏目标题',
                    tips: '标题一般用于显示在浏览器标签页，如果不设置，默认使用栏目名称'
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'channelContextPath',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '栏目访问上下文路径',
                    tips: '请以 / 开头，用于生成链接时url的一部分，默认为 /channel，注意：不要使用全数字，会有冲突'

                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'templatePath',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '栏目模板路径',
                    tips: '相对于站点模板路径，该路径需在站点模板路径下,使用站点配置模板请留空'
                },
                compProps: {
                    clearable: true,
                }
            }
        },


        {
            field: {
                name: 'templateIndex',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '栏目模板',
                    tips: '相对于栏目模板路径，如：index.ftlh,使用站点配置模板请留空'
                },
                compProps: {
                    clearable: true,
                }
            }
        },


        {
            field: {
                name: 'staticSavePath',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '栏目静态化页面存放路径',
                    tips: '相对于站点静态化页面存放路径'

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
                    label: '排序'
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        useCascaderCmsChannelCompItem({}),
        {
            field: {
                name: 'profile',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '简介',
                    tips: '填写一些简单介绍，简介可以模板中使用',
                    displayBlock: true,

                },
                compProps: {
                    clearable: true,
                    type: 'textarea',
                    rows: 3,
                }
            }
        },
        {
            field: {
                name: 'initPv',
                value: 0,
            },
            element: {
                comp: 'el-input-number',
                formItemProps: {
                    label: '初始页面访问量',
                    tips: '这里指的是栏目首页的初始页面访问量',

                },
                compProps: {
                    clearable: true,
                }
            }
        },
        useRemoteSelectCmsContentCompItem({props,
            fieldName: 'relatedCmsContentId',
            propCmsContentIdFieldName: 'relatedCmsContentId',
            propCmsContentTitleFieldName: 'relatedCmsContentTitle',
            label: '关联内容',
            tips:'根据内容标题搜索，选择一个内容关联，适用于点击栏目跳转该内容详情页场景',
            required: false
        }
        ),
        {
            field: {
                name: 'customUrl',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '自定义url',
                    tips: '适用于点击栏目跳转该内容详情页场景或自定义跳转场景'

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
                    label: '备注',
                    tips: '写点什么，备注不可以在模板中使用，没有设置这个变量'

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

