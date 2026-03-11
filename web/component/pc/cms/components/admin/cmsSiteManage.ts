export const pageFormItems = [
    {
        field: {
            name: 'code',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点编码',
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
                label: '站点名称',

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

]
export const addPageFormItems = [
    {
        field: {
            name: 'code',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点编码',
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
                label: '站点名称',
                required: true,
                tips: '用于管理站点的名称，如果 站点标题 为空时会使用站点名称作为标题'
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
                label: '站点标题',
                tips: '标题一般用于显示在浏览器标签页，如果不设置，默认使用站点名称'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    {
        field: {
            name: 'domain',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点域名',
                required: true,
                tips: '用来匹配站点，如：http://www.example.com、https://example.com'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'dynamicDomain',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点外部域名',
                required: true,
                tips: '用来生成链接，如：http://www.example.com、https://example.com'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    {
        field: {
            name: 'dynamicDeployPath',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '部署路径',
                tips: '请以 / 开头，用于生成链接时url的一部分，一般将服务部署到如：nginx 等反向代理服务器时，配置的匹配路径'

            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'siteContextPath',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点访问上下文路径',
                tips: '请以 / 开头，用于生成链接时url的一部分，可以在同一个域名下根据站点上下文路径区分不同站点'

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
                label: '站点模板路径',
                required: true,
                tips: '相对于系统配置的根路径，如：类路径是相对于 classpath:/templates/cms/、文件系统绝对路径默认相对于 file:/opt/particle/templates/cms/,使用系统内置模板请填写 default，该模板在 classpath:/templates/cms/ 目录下'
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
                label: '站点首页模板',
                required: true,
                tips: '相对于站点模板路径，如：index.ftlh,使用系统内置模板请填写 index.ftlh'
            },
            compProps: {
                clearable: true,
            }
        }
    },

    {
        field: {
            name: 'template404Path',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点404模板路径',
                tips: '相对于站点模板路径，如：404,使用系统内置模板请填留空'
            },
            compProps: {
                clearable: true,
            }
        }
    },


    {
        field: {
            name: 'template404Index',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点404页面模板',
                tips: '相对于站点模板路径，如：index.ftlh,使用系统内置模板请填留空'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'template403Path',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点403模板路径',
                tips: '相对于站点模板路径，如：403,使用系统内置模板请填留空'
            },
            compProps: {
                clearable: true,
            }
        }
    },


    {
        field: {
            name: 'template403Index',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '站点403页面模板',
                tips: '相对于站点模板路径，如：index.ftlh,使用系统内置模板请填留空'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'templateChannelPath',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '栏目模板路径',
                tips: '相对于站点模板路径，如：channel'
            },
            compProps: {
                clearable: true,
            }
        }
    },


    {
        field: {
            name: 'templateChannelIndex',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '栏目页面模板',
                tips: '相对于栏目模板路径，如：index.ftlh'
            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'templateContentPath',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '内容模板路径',
                tips: '相对于站点模板路径，如：content'
            },
            compProps: {
                clearable: true,
            }
        }
    },


    {
        field: {
            name: 'templateContentIndex',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '内容页面模板',
                tips: '相对于内容模板路径，如：index.ftlh'
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
                label: '站点静态化页面存放路径',
                tips: '仅支持绝对路径,在页面静态化时静态页面存放路径'

            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'staticDomain',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '静态域名',
                tips: '站点静态化页面部署后可访问域名，如：http://static.example.com，在访问动态站点首页页面时可以优先重定向到该域名，也用于生成静态化页面时页面的链接'

            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'staticDeployPath',
        },
        element: {
            comp: 'el-input',
            formItemProps: {
                label: '静态部署路径',
                tips: '站点静态化页面部署后可访问的子目录，也用于生成静态化页面时页面的链接'

            },
            compProps: {
                clearable: true,
            }
        }
    },
    {
        field: {
            name: 'isPrimeSite',
            value: false,
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否主站点',
                required: true,
                tips: '在相同的域名下可以有多个站点（使用 站点访问上下文路径 区分），但只能有一个主站点'
            },
            compProps: {
            }
        }
    },
    {
        field: {
            name: 'isEnableBackendRecord',
            value: false,
        },
        element: {
            comp: 'el-switch',
            formItemProps: {
                label: '是否启用后端记录',
                required: true,
                tips: '在页面访问时记录访问日志，如果不开启建议前端调用接口更真实'
            },
            compProps: {
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
                tips: '这里指的是站点首页的初始页面访问量',

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

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

