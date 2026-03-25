import {
    useCascaderCmsChannelCompItem,
    useCascaderCmsContentCategoryCompItem,
    useSelectCmsSiteCompItem
} from "../cmsCompItem";

export const pageFormItems = [
    useSelectCmsSiteCompItem({}),
    useCascaderCmsChannelCompItem({ fieldName: 'cmsChannelId', label: '栏目' }),
    useCascaderCmsContentCategoryCompItem({ fieldName: 'cmsContentCategoryId', label: '内容分类' }),
    {
        field: {
            name: 'title',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '标题',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'author',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '作者',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },
    {
        field: {
            name: 'original',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '来源',

            },
            compProps: {
                clearable: true,
                placeholder: '左前缀匹配'
            }
        }
    },

    {
        field: {
            name: 'auditStatusDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '审核状态',

            },
            compProps: {
                // 字典查询
                dictParam: { groupCode: 'cms_content_audit_status' }
            }
        }
    },

    {
        field: {
            name: 'contentTypeDictId',
        },
        element: {
            comp: 'PtDictFrontSelect',
            formItemProps: {
                label: '内容类型',

            },
            compProps: {
                // 字典查询
                dictParam: { groupCode: 'cms_content_type' }
            }
        }
    },

]
export const useAddPageFormItems = ({ isForAdd = true }) => {
    return [

        useSelectCmsSiteCompItem({ required: true }),
        useCascaderCmsChannelCompItem({ fieldName: 'cmsChannelId', label: '栏目' }),
        useCascaderCmsContentCategoryCompItem({ fieldName: 'cmsContentCategoryId', label: '内容分类' }),

        {
            field: {
                name: 'title',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '标题',
                    required: true,
                },
                compProps: {
                    clearable: true,
                }
            }
        },


        {
            field: {
                name: 'author',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '作者',
                    required: true,
                    tips: '作者名称，如：张三、李四等'
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'author_profile',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '作者介绍',
                },
                compProps: {
                    clearable: true,
                }
            }
        },


        {
            field: {
                name: 'original',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '来源',
                    tips: '文章来源，如：原创、头条、CSDN等',
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'originalUrl',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '原文链接',
                    tips: '如果是转载文章，请填写原文链接，以http或https开头',
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'originalPublicAt',
            },
            element: {
                comp: 'PtDatePicker',
                formItemProps: {
                    label: '原文发布时间',
                    tips: '如果是转载文章，希望显示原文发布时间，可以填写',
                },
                compProps: {
                    clearable: true,
                    type: "datetime"
                }
            }
        },




        {
            field: {
                name: 'contentTypeDictId',
            },
            element: {
                comp: 'PtDictFrontSelect',
                formItemProps: {
                    label: '内容类型',
                    required: true,
                },
                compProps: {
                    // 字典查询
                    dictParam: { groupCode: 'cms_content_type' }
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
                    tips: '图片地址主要是在列表中展示'

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
                    tips: '图片描述可能主要是在内容详情中展示，如：说明图片来源'
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
                    tips: '图片地址主要是在列表中展示'
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
                    tips: '图片描述可能主要是在内容详情中展示，如：说明图片来源'
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
                    tips: '图片地址主要是在列表中展示'
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
                    tips: '图片描述可能主要是在内容详情中展示，如：说明图片来源'
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
                    label: '内容模板路径',
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
                    label: '内容模板',
                    tips: '相对于内容模板路径，如：index.ftlh,使用站点配置模板请留空'
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
                    label: '内容静态化页面存放路径',
                    tips: '相对于栏目静态化页面存放路径'

                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'isAlsoAsChannel',
                value: false
            },
            element: {
                comp: 'el-switch',
                formItemProps: {
                    label: '作为栏目',
                    tips: '如果勾选，该内容也会作为栏目展示,适用于将内容挂载到栏目下点击跳转该内容详情页场景'
                },
                compProps: {
                    clearable: true,
                    activeText: '是',
                    inactiveText: '否',
                }
            }
        },
        {
            field: {
                name: 'alsoAsChannelSeq',
                value: 1
            },
            element: {
                comp: 'el-input-number',
                formItemProps: {
                    label: '作为栏目使用时的排序'
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'isShowInList',
                value: true
            },
            element: {
                comp: 'el-switch',
                formItemProps: {
                    label: '列表展示',
                    tips: '适用于将内容挂载到栏目下点击跳转该内容详情页场景，并不在列表中展示该内容'
                },
                compProps: {
                    clearable: true,
                    activeText: '是',
                    inactiveText: '否',
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

        {
            field: {
                name: 'profile',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '简介',
                    tips: '填写一些简单介绍，主要是在列表中展示',
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
                name: 'summary',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '摘要',
                    tips: '文章摘要，主要是在详情中展示',
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
                name: 'keywords',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '关键词',
                    tips: '逗号分隔,如：关键词1,关键词2,关键词3',
                    displayBlock: true,
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'tags',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '标签',
                    tips: '逗号分隔,如：标签1,标签2,标签3',
                    displayBlock: true,
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
        {
            field: {
                name: 'initPv',
                value: 0,
            },
            element: {
                comp: 'el-input-number',
                formItemProps: {
                    label: '初始页面访问量',
                    tips: '内容详情页的初始页面访问量',
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'wordCount',
                value: 0,
            },
            element: {
                comp: 'el-input-number',
                formItemProps: {
                    label: '文章字数',
                    tips: '文章内容的字数，一般为中文单字 + 英文单词数量',
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'imageTableCount',
                value: 0,
            },
            element: {
                comp: 'el-input-number',
                formItemProps: {
                    label: '图表数量',
                    tips: '文章内容中的图片 + 图片表格数量',
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'citationCount',
                value: 0,
            },
            element: {
                comp: 'el-input-number',
                formItemProps: {
                    label: '引用数量',
                    tips: '文章内容正文标注的引用来源数量，如作者姓氏和年份',
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'referenceCount',
                value: 0,
            },
            element: {
                comp: 'el-input-number',
                formItemProps: {
                    label: '参考文献数量',
                    tips: '文末列出的引用列表数量，如书名、期刊名、页码',
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        {
            field: {
                name: 'reading_duration',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '阅读时长',
                    tips: '文章阅读时长，如：约5分钟、小于10分钟'
                },
                compProps: {
                    clearable: true,
                }
            }
        },
        ...(isForAdd ? [{
            field: {
                name: 'contentArticle',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '文章内容',
                    tips: '文本内容，主要是在内容详情中展示，如果需要添加更丰富的内容，请在多媒体管理中修改',
                    displayBlock: true,

                },
                compProps: {
                    clearable: true,
                    type: 'textarea',
                    rows: 10,
                }
            }
        },
            {
                field: {
                    name: 'isUseArticleAnalyzer',
                    value: false
                },
                element: {
                    comp: 'el-switch',
                    formItemProps: {
                        label: '内容字数统计',
                        tips: '启用后填写无效，适用字段文章字数、图表数量、引用数量、参考文献数量'
                    },
                    compProps: {
                        clearable: true,
                        activeText: '是',
                        inactiveText: '否',
                    }
                }
            }] : [])
    ]
}

// 更新和添加一致
export const useUpdatePageFormItems = useAddPageFormItems

export const useAuditPageFormItems = () => {
    return [
        {
            field: {
                name: 'isPassAudit',
                value: 'true'
            },
            element: {
                comp: 'PtRadioGroup',
                formItemProps: {
                    label: '是否通过审核',
                    required: true,
                },
                compProps: {
                    buttonView: true,
                    options: [
                        {
                            id: 'true',
                            name: '审核通过'
                        },
                        {
                            id: 'false',
                            name: '审核不通过'
                        },
                    ]
                }
            }
        },


        {
            field: {
                name: 'comment',
            },
            element: {
                comp: 'el-input',
                formItemProps: {
                    label: '审核意见',
                    required: true,
                    tips: '填写审核意见，如：同意或不同意请修改...等'
                },
                compProps: {
                    clearable: true,
                    type: 'textarea',
                    rows: 4,
                }
            }
        },

    ]
}
