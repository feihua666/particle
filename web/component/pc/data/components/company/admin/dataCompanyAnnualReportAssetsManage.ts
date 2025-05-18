export const pageFormItems = [
      {
        field: {
          name: 'companyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业表ID',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'companyAnnualReportId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业年报表ID',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'year',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '年报年度',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'totalAssets',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '资产总额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'totalAssetsCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '资产总额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'totalOwnerEquity',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所有者权益合计（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'totalOwnerEquityCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '所有者权益合计币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'totalSales',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '销售总额(营业总收入)（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'totalSalesCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '销售总额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'totalProfit',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '利润总额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'totalProfitCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '利润总额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'primeBusProfit',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主营业务收入（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'primeBusProfitCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '主营业务收入币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'retainedProfit',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '净利润（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'retainedProfitCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '净利润币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'totalTax',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '纳税总额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'totalTaxCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '纳税总额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'totalLiability',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '负债总额（万元）',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'totalLiabilityCurrencyDictId',
        },
        element: {
          comp: 'PtDictFrontSelect',
          formItemProps: {
            label: '负债总额币种',
            
          },
          compProps: {
            // 字典查询
            dictParam: {groupCode: '这里填写字典组编码'}
          }
        }
      },
      {
        field: {
          name: 'isTotalAssetsPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否资产总额公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isTotalOwnerEquityPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否所有者权益合计公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isTotalSalesPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否销售总额公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isTotalProfitPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否利润总额公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isPrimeBusProfitPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否主营业务收入公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isRetainedProfitPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否净利润公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isTotalTaxPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否纳税总额公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'isTotalLiabilityPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否负债总额公示',
            
          },
          compProps: {
          }
        }
      },
      {
        field: {
          name: 'latestHandleAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后处理时间',
            
          },
          compProps: {
          }
        }
      },
]
export const addPageFormItems = [




      {
        field: {
          name: 'companyId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业表ID',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'companyAnnualReportId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '企业年报表ID',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'year',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '年报年度',
            required: true,
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalAssets',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '资产总额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalAssetsCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '资产总额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalOwnerEquity',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所有者权益合计（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalOwnerEquityCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '所有者权益合计币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalSales',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '销售总额(营业总收入)（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalSalesCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '销售总额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalProfit',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '利润总额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalProfitCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '利润总额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'primeBusProfit',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主营业务收入（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'primeBusProfitCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '主营业务收入币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'retainedProfit',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '净利润（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'retainedProfitCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '净利润币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalTax',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '纳税总额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalTaxCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '纳税总额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalLiability',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '负债总额（万元）',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'totalLiabilityCurrencyDictId',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '负债总额币种',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isTotalAssetsPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否资产总额公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isTotalOwnerEquityPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否所有者权益合计公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isTotalSalesPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否销售总额公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isTotalProfitPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否利润总额公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isPrimeBusProfitPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否主营业务收入公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isRetainedProfitPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否净利润公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isTotalTaxPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否纳税总额公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'isTotalLiabilityPublic',
        },
        element: {
          comp: 'el-switch',
          formItemProps: {
            label: '是否负债总额公示',
            
          },
          compProps: {
          }
        }
      },


      {
        field: {
          name: 'latestHandleAt',
        },
        element: {
          comp: 'el-input',
          formItemProps: {
            label: '最后处理时间',
            
          },
          compProps: {
          }
        }
      },












]

// 更新和添加一致
export const updatePageFormItems = addPageFormItems

