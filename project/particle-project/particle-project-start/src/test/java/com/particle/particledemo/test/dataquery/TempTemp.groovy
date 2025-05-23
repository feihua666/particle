import cn.hutool.core.collection.CollectionUtil
import cn.hutool.core.util.NumberUtil
import cn.hutool.core.util.StrUtil
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyIprPatentAllWrapExWarehouseCommandExecutor
import com.particle.data.app.company.executor.warehousewrap.DataCompanyAnnualReportAllWrapWarehouseCommandExecutor
import com.particle.data.app.company.executor.warehousewrap.DataCompanyIprPatentAllWrapWarehouseCommandExecutor
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportExWarehouseQueryCommand
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentExWarehouseQueryCommand
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAnnualReportAllExWarehouseVO
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentAllExWarehouseVO
import com.particle.dataquery.app.dataapi.executor.representation.DataQueryDataApiDataApiQueryCommandExecutor
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand
import com.particle.global.dto.response.PageResponse
import com.particle.global.tool.spring.SpringContextHolder

import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * 主要思路
 * 1. 先拉取数据
 * 2. 将拉取的数据入库
 * 3. 从本地出库并计算
 */
def param = data
def dataQueryDataApiDataApiQueryCommandExecutor = SpringContextHolder.getBean(DataQueryDataApiDataApiQueryCommandExecutor.class) as DataQueryDataApiDataApiQueryCommandExecutor
def dataCompanyIprPatentAllWrapExWarehouseCommandExecutor = SpringContextHolder.getBean(DataCompanyIprPatentAllWrapExWarehouseCommandExecutor.class) as DataCompanyIprPatentAllWrapExWarehouseCommandExecutor
def dataCompanyIprPatentAllWrapWarehouseCommandExecutor = SpringContextHolder.getBean(DataCompanyIprPatentAllWrapWarehouseCommandExecutor.class) as DataCompanyIprPatentAllWrapWarehouseCommandExecutor
def dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor = SpringContextHolder.getBean(DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor.class) as DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor
def dataCompanyAnnualReportAllWrapWarehouseCommandExecutor = SpringContextHolder.getBean(DataCompanyAnnualReportAllWrapWarehouseCommandExecutor.class) as DataCompanyAnnualReportAllWrapWarehouseCommandExecutor
def helperMap = [
        datasourceApi: datasourceApi,
        dataQueryDataApiDataApiQueryCommandExecutor: dataQueryDataApiDataApiQueryCommandExecutor,
        dataCompanyIprPatentAllWrapExWarehouseCommandExecutor: dataCompanyIprPatentAllWrapExWarehouseCommandExecutor,
        dataCompanyIprPatentAllWrapWarehouseCommandExecutor: dataCompanyIprPatentAllWrapWarehouseCommandExecutor,
        dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor: dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor,
        dataCompanyAnnualReportAllWrapWarehouseCommandExecutor: dataCompanyAnnualReportAllWrapWarehouseCommandExecutor,
        patentPage: 1,
        annualReport: 30,
]
def initResult = getInitResult()
def initResultCalculateMap = getInitResultCalculateMap()
pullAndWarehouse(param, helperMap)
exWarehouseAndCalculate(param,initResult,initResultCalculateMap, helperMap)
return initResult
/**
 * 拉取数据入库
 * @param param
 */
def pullAndWarehouse(param, Map helperMap) {
    // 总拉取50页
    def patentPage = helperMap.patentPage
    // 获取专利数据并入库
    for (int i = 1; i <= patentPage; i++) {
        def command = new DataQueryDataApiQueryCommand()
        command.setUrl("/iprPatentAll")
        command.setParam([name: param.name,pageNo: i,pageSize: 10,isApplicant: true,isInvent: true])
        def response = (helperMap.dataQueryDataApiDataApiQueryCommandExecutor as DataQueryDataApiDataApiQueryCommandExecutor).dataApiQuery(command) as PageResponse<DataCompanyIprPatentAllExWarehouseVO>
        if (CollectionUtil.isNotEmpty(response.getData())) {
            (helperMap.dataCompanyIprPatentAllWrapWarehouseCommandExecutor as DataCompanyIprPatentAllWrapWarehouseCommandExecutor).warehouse(response)
        }else{
            break
        }
    }
    // 获取年报数据并入库
    def annualReport = helperMap.annualReport
    for (int i = 1; i <= annualReport; i++) {
        def command = new DataQueryDataApiQueryCommand()
        command.setUrl("/annualReportAll")
        command.setParam([name: param.name,pageNo: i,pageSize: 10])
        def response = (helperMap.dataQueryDataApiDataApiQueryCommandExecutor as DataQueryDataApiDataApiQueryCommandExecutor).dataApiQuery(command) as PageResponse<DataCompanyAnnualReportAllExWarehouseVO>
        if (CollectionUtil.isNotEmpty(response.getData())) {
            (helperMap.dataCompanyAnnualReportAllWrapWarehouseCommandExecutor as DataCompanyAnnualReportAllWrapWarehouseCommandExecutor).warehouse(response)
        }else{
            break
        }
    }

}
/**
 * 出库并计算
 * @param param
 * @param helperMap
 */
def exWarehouseAndCalculate(param,initResult,Map initResultCalculateMap, Map helperMap) {
    def dataCompanyExWarehouseQueryCommand = DataCompanyExWarehouseQueryCommand.create(param.name, null)
    // 这些key依赖年报数据，参数不同，单独处理
    def annualReportKeys = ["rDPerProportionT","rDPerProportionOneT","totalNumberPerT","totalNumberPerOneT"]
    // 专利相关计算
    def patentPage = helperMap.patentPage
    for (int i = 1; i <= patentPage; i++) {
        def dataCompanyIprPatentExWarehouseQueryCommand = new DataCompanyIprPatentExWarehouseQueryCommand()
        dataCompanyIprPatentExWarehouseQueryCommand.setIsApplicant(true)
        dataCompanyIprPatentExWarehouseQueryCommand.setIsInvent(true)
        dataCompanyIprPatentExWarehouseQueryCommand.setPageNo(i)
        dataCompanyIprPatentExWarehouseQueryCommand.setPageSize(10)
        def response = (helperMap.dataCompanyIprPatentAllWrapExWarehouseCommandExecutor as DataCompanyIprPatentAllWrapExWarehouseCommandExecutor).exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyIprPatentExWarehouseQueryCommand) as PageResponse<DataCompanyIprPatentAllExWarehouseVO>
        if (CollectionUtil.isNotEmpty(response.getData())) {
            for (final def vo in response.getData()) {
                for (final def key in initResultCalculateMap.keySet()) {
                    // 排除掉依赖年报的数据
                    if (!annualReportKeys.contains(key)) {
                        initResultCalculateMap.get(key)(vo,initResult,param)
                    }
                }
            }
        }else{
            break
        }
    }
    // 使用年报数据计算
    def dataCompanyAnnualReportExWarehouseQueryCommand = DataCompanyAnnualReportExWarehouseQueryCommand.create(null)
    def response = (helperMap.dataCompanyAnnualReportAllWrapExWarehouseCommandExecutor as DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor).exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyAnnualReportExWarehouseQueryCommand) as PageResponse<DataCompanyAnnualReportAllExWarehouseVO>
    def data = response.getData()
    if (CollectionUtil.isNotEmpty(data)) {
        for (final def key in initResultCalculateMap.keySet()) {
            // 只计算依赖年报的数据
            if (annualReportKeys.contains(key)) {
                initResultCalculateMap.get(key)(data,initResult,param)
            }
        }
    }
}

/**
 * 初始化结果
 */
def getInitResult(){
    def initResult = [
            // 产学研专利合作数量
            totalIndustry: 0,
            // 产学研机构合作数量
            numberMechanism: 0,
            // 审中专利数量
            totalExamination: 0,
            // 无效专利数量
            totalInvalid: 0,
            // 专利引证次数
            numberQuote: 0,
            // 专利被引次数
            numberCited: 0,
            // 转移专利数量
            numberTransfer: 0,
            // 累计专利申请量
            applyQuantity: 0,
            // 发明专利申请量
            applyQuantityfm: 0,
            // 实用新型专利申请量
            applyQuantitysy: 0,
            // 外观设计专利申请量
            applyQuantitywg: 0,
            // 授权专利申请总量
            applyQuantitysq: 0,
            // 发明专利授权量
            authorizationfm: 0,
            // 实用新型专利授权量
            authorizationsy: 0,
            // 外观设计专利授权量
            authorizationwg: 0,
            // 有效专利总量
            totalEffective: 0,
            // 有效发明专利量
            totalEffectivefm: 0,
            // 有效实用新型专利量
            totalEffectivesy: 0,
            // 有效外观设计专利量
            totalEffectivewg: 0,
            // T-1年研发人员数量
            numberRDPerOneT: 0,
            // T-1年人员数量
            totalNumberPerOneT: 0,
            // T年研发人员数量
            numberRDPerT: 0,
            // T年人员数量
            totalNumberPerT: 0,
            // T年研发人员占比
            rDPerProportionT: 0,
            // T-1年研发人员占比
            rDPerProportionOneT: 0,
            // T年研发人员增长率
            rDPerGrowth: 0,
            // PCT专利申请数量
            applyQuantityPCT: 0,
            // 申请专利国家数
            patentCountry: 0,
            // 同族专利数量
            patentFamily: 0,
            // 专利平均剩余有效期（年）
            termAVG: 0,
            // 剩余有效期在超过 10 年的发明专利数量（个）
            fMterm10: 0,
            // 剩余有效期在 5-10 年间的发明专利数量（个）
            fMterm5_10: 0,
            // 剩余有效期在 5 年以内的发明专利数量（个）
            fMterm5: 0,
            // 剩余有效期在超过 10 年的实用新型专利数量（个）
            sYterm10: 0,
            // 剩余有效期在 5-10 年间的实用新型专利数量（个）
            sYterm5_10: 0,
            // 剩余有效期在 5 年以内的实用新型专利数量（个）
            sYterm5: 0,
            // 剩余有效期在超过 10 年的外观设计专利数量（个）
            wGterm10: 0,
            // 剩余有效期在 5-10 年间的外观设计专利数量（个）
            wGterm5_10: 0,
            // 剩余有效期在 5 年以内的外观设计专利数量（个）
            wGterm5: 0
    ] as LinkedHashMap

    return initResult
}
/**
 * 计算
 * @return
 */
def getInitResultCalculateMap(){
    def helpMap = [:]
    def initResultCalculateMap = [
            // 产学研专利合作数量
            totalIndustry: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def parties = vo.getParties()
                def find = parties.find { it.isApplicant }
                def partyName =  find.getPartyName()
                if (find != null && StrUtil.containsAny(partyName, "研究所","大学","实验室","学院")) {
                    initResult["totalIndustry"] = initResult["totalIndustry"] + 1
                }
            },
            // 产学研机构合作数量
            numberMechanism: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def parties = vo.getParties()
                def find = parties.find { it.isApplicant }
                def partyName =  find.getPartyName()
                if (find != null && StrUtil.containsAny(partyName, "研究所","大学","实验室","学院")
                        && !StrUtil.equals(param.name, partyName)) {
                    def numberMechanismHelpValueSet = helpMap.computeIfAbsent("numberMechanism", { new HashSet<>() })
                    numberMechanismHelpValueSet.add(partyName)
                    initResult["numberMechanism"] = numberMechanismHelpValueSet.size()
                }
            },
            // 审中专利数量
            totalExamination: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 在审 字典id 1922901744187117570
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901744187117570L)) {
                    initResult["totalExamination"] = initResult["totalExamination"] + 1
                }
            },
            // 无效专利数量
            totalInvalid: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 无效 字典id 1922901361075195906
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901361075195906L)) {
                    initResult["totalInvalid"] = initResult["totalInvalid"] + 1
                }
            },
            // 专利引证次数
            numberQuote: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def statistic = vo.getStatistic()
                def quoteNum = statistic.getQuoteNum() == null ? 0 : statistic.getQuoteNum()
                initResult["numberQuote"] = initResult["numberQuote"] + quoteNum
            },
            // 专利被引次数
            numberCited: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def statistic = vo.getStatistic()
                def citedNum = statistic.getCitedNum() == null ? 0 : statistic.getCitedNum()
                initResult["numberCited"] = initResult["numberCited"] + citedNum
            },
            // 转移专利数量
            numberTransfer: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def statistic = vo.getStatistic()
                def transferNum = statistic.getTransferNum() == null ? 0 : statistic.getTransferNum()
                initResult["numberTransfer"] = initResult["numberTransfer"] + transferNum
            },
            // 累计专利申请量
            applyQuantity: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                initResult["applyQuantity"] = initResult["applyQuantity"] + 1
            },
            // 发明专利申请量
            applyQuantityfm: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 发明 字典id 1907664719599116290
                if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                    initResult["applyQuantityfm"] = initResult["applyQuantityfm"] + 1
                }
            },
            // 实用新型专利申请量
            applyQuantitysy: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 实用新型 字典id 1907664719485870081
                if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                    initResult["applyQuantitysy"] = initResult["applyQuantitysy"] + 1
                }
            },
            // 外观设计专利申请量
            applyQuantitywg: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 外观设计 字典id 1907664719255183361
                if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                    initResult["applyQuantitywg"] = initResult["applyQuantitywg"] + 1
                }
            },
            // 授权专利申请总量
            applyQuantitysq: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def parties = vo.getParties()
                def find = parties.find { it.isApplicant }
                def partyName =  find.getPartyName()
                if (find != null && StrUtil.equals(param.name, partyName)) {
                    def legalStatuses = vo.getLegalStatuses()
                    if (CollectionUtil.isNotEmpty(legalStatuses)) {
                        def legalStatus = legalStatuses.find({StrUtil.containsAny(it.getLegalStatusDetailCn(), "授权")})
                        if (legalStatus != null) {
                            initResult["applyQuantitysq"] = initResult["applyQuantitysq"] + 1
                        }
                    }
                }
            },
            // 发明专利授权量
            authorizationfm: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 发明 字典id 1907664719599116290
                if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                    def parties = vo.getParties()
                    def find = parties.find { it.isApplicant }
                    def partyName =  find.getPartyName()
                    if (find != null && StrUtil.equals(param.name, partyName)) {
                        def legalStatuses = vo.getLegalStatuses()
                        if (CollectionUtil.isNotEmpty(legalStatuses)) {
                            def legalStatus = legalStatuses.find({StrUtil.containsAny(it.getLegalStatusDetailCn(), "授权")})
                            if (legalStatus != null) {
                                initResult["authorizationfm"] = initResult["authorizationfm"] + 1
                            }
                        }
                    }
                }

            },
            // 实用新型专利授权量
            authorizationsy: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 实用新型 字典id 1907664719485870081
                if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                    def parties = vo.getParties()
                    def find = parties.find { it.isApplicant }
                    def partyName =  find.getPartyName()
                    if (find != null && StrUtil.equals(param.name, partyName)) {
                        def legalStatuses = vo.getLegalStatuses()
                        if (CollectionUtil.isNotEmpty(legalStatuses)) {
                            def legalStatus = legalStatuses.find({StrUtil.containsAny(it.getLegalStatusDetailCn(), "授权")})
                            if (legalStatus != null) {
                                initResult["authorizationsy"] = initResult["authorizationsy"] + 1
                            }
                        }
                    }
                }
            },
            // 外观设计专利授权量
            authorizationwg: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 外观设计 字典id 1907664719255183361
                if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                    def parties = vo.getParties()
                    def find = parties.find { it.isApplicant }
                    def partyName =  find.getPartyName()
                    if (find != null && StrUtil.equals(param.name, partyName)) {
                        def legalStatuses = vo.getLegalStatuses()
                        if (CollectionUtil.isNotEmpty(legalStatuses)) {
                            def legalStatus = legalStatuses.find({StrUtil.containsAny(it.getLegalStatusDetailCn(), "授权")})
                            if (legalStatus != null) {
                                initResult["authorizationwg"] = initResult["authorizationwg"] + 1
                            }
                        }
                    }
                }
            },
            // 有效专利总量
            totalEffective: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                if (vo.basic.getIsCurrentValid() != null && vo.basic.getIsCurrentValid()) {
                    initResult["totalEffective"] = initResult["totalEffective"] + 1
                }
            },
            // 有效发明专利量
            totalEffectivefm: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                if (vo.basic.getIsCurrentValid() != null && vo.basic.getIsCurrentValid()) {
                    // 发明 字典id 1907664719599116290
                    if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                        initResult["totalEffectivefm"] = initResult["totalEffectivefm"] + 1
                    }
                }
            },
            // 有效实用新型专利量
            totalEffectivesy: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                if (vo.basic.getIsCurrentValid() != null && vo.basic.getIsCurrentValid()) {
                    // 实用新型 字典id 1907664719485870081
                    if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                        initResult["totalEffectivesy"] = initResult["totalEffectivesy"] + 1
                    }
                }
            },
            // 有效外观设计专利量
            totalEffectivewg: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                if (vo.basic.getIsCurrentValid() != null && vo.basic.getIsCurrentValid()) {
                    // 外观设计 字典id 1907664719255183361
                    if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                        initResult["totalEffectivewg"] = initResult["totalEffectivewg"] + 1
                    }
                }
            },
            // T-1年研发人员数量
            numberRDPerOneT: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def applyDate = vo.getBasic().getApplyDate()
                if (applyDate != null && applyDate.getYear() == (LocalDate.now().getYear() - 2)) {
                    // 有效 字典id 1922901306708627458
                    if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                        def find = vo.getParties().find({ it.isInvent })
                        if (find != null) {
                            def partyName =  find.getPartyName()
                            for (final def pn in partyName.split(";")) {
                                def numberRDPerOneTHelpValueSet = helpMap.computeIfAbsent("numberRDPerOneT", { new HashSet<>() })
                                numberRDPerOneTHelpValueSet.add(pn)
                                initResult["numberRDPerOneT"] = numberRDPerOneTHelpValueSet.size()
                            }
                        }
                    }
                }
            },
            // T-1年人员数量
            totalNumberPerOneT: {List<DataCompanyAnnualReportAllExWarehouseVO> vos,Map initResult,param ->
                if (CollectionUtil.isNotEmpty(vos)) {
                    def dataSize = vos.size()
                    if (dataSize > 1) {
                        def vo = vos.get(1)
                        def security = vo.getSocialSecurity()
                        if (security != null) {
                            def totalNumberPerOneT = NumberUtil.max(
                                    NumberUtil.nullToZero(security.getEndowmentInsurancePeopleNum()),
                                    NumberUtil.nullToZero(security.getUnemploymentInsurancePeopleNum()),
                                    NumberUtil.nullToZero(security.getMedicalInsurancePeopleNum()),
                            )
                            initResult["totalNumberPerOneT"] = totalNumberPerOneT
                        }

                    }
                }
            },
            // T年研发人员数量
            numberRDPerT: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def applyDate = vo.getBasic().getApplyDate()
                if (applyDate != null && applyDate.getYear() == (LocalDate.now().getYear() - 1)) {
                    // 有效 字典id 1922901306708627458
                    if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                        def find = vo.getParties().find({ it.isInvent })
                        if (find != null) {
                            def partyName =  find.getPartyName()
                            for (final def pn in partyName.split(";")) {
                                def numberRDPerTHelpValueSet = helpMap.computeIfAbsent("numberRDPerT", { new HashSet<>() })
                                numberRDPerTHelpValueSet.add(pn)
                                initResult["numberRDPerT"] = numberRDPerTHelpValueSet.size()
                            }
                        }
                    }
                }
            },
            // T年人员数量
            totalNumberPerT: {List<DataCompanyAnnualReportAllExWarehouseVO> vos,Map initResult,param ->
                if (CollectionUtil.isNotEmpty(vos)) {
                    def dataSize = vos.size()
                    if (dataSize > 0) {
                        def vo = vos.get(0)
                        def security = vo.getSocialSecurity()
                        if (security != null) {
                            def totalNumberPerT = NumberUtil.max(
                                    NumberUtil.nullToZero(security.getEndowmentInsurancePeopleNum()),
                                    NumberUtil.nullToZero(security.getUnemploymentInsurancePeopleNum()),
                                    NumberUtil.nullToZero(security.getMedicalInsurancePeopleNum()),
                            )
                            initResult["totalNumberPerT"] = totalNumberPerT
                        }

                    }
                }
            },
            // T年研发人员占比
            rDPerProportionT: {anyParam,Map initResult,param ->
                def totalNumberPerT = initResult["totalNumberPerT"]
                if (totalNumberPerT == 0) {
                    initResult["rDPerProportionT"] = 1
                }
                initResult["rDPerProportionT"] = NumberUtil.round(initResult["numberRDPerT"] / totalNumberPerT,2)
            },
            // T-1年研发人员占比
            rDPerProportionOneT: {anyParam,Map initResult,param ->
                def totalNumberPerOneT = initResult["totalNumberPerOneT"]
                if (totalNumberPerOneT == 0) {
                    initResult["rDPerProportionOneT"] = 1
                }
                initResult["rDPerProportionOneT"] = NumberUtil.round(initResult["numberRDPerOneT"] / totalNumberPerOneT,2)
            },
            // T年研发人员增长率
            rDPerGrowth: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def numberRDPerT = initResult["numberRDPerT"]
                def numberRDPerOneT = initResult["numberRDPerOneT"]
                if (numberRDPerOneT == 0) {
                    initResult["rDPerGrowth"] = 1
                }else{
                    initResult["rDPerGrowth"] = NumberUtil.round((numberRDPerT - numberRDPerOneT) / numberRDPerOneT /100,2)
                }

            },
            // PCT专利申请数量
            applyQuantityPCT: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                if (vo.getBasic().getIsPct() != null && vo.getBasic().getIsPct()) {
                    initResult["applyQuantityPCT"] = initResult["applyQuantityPCT"] + 1
                }
            },
            // 申请专利国家数
            patentCountry: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def applyNo = vo.getBasic().getApplyNo()
                if (applyNo != null) {
                    def country = applyNo.substring(0, 2)
                    def countryHelpValueSet = helpMap.computeIfAbsent("patentCountry", { new HashSet<>() })
                    countryHelpValueSet.add(country)
                    initResult["patentCountry"] = countryHelpValueSet.size()
                }
            },
            // 同族专利数量
            patentFamily: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                def statistic = vo.getStatistic()
                def familyNum = statistic.getFamilyNum() == null ? 0 : statistic.getFamilyNum()
                initResult["patentFamily"] = initResult["patentFamily"] + familyNum
            },
            // 专利平均剩余有效期（年）
            termAVG: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def termAVGTotalNum = helpMap.computeIfAbsent("termAVGTotalNum", { 0 })
                    termAVGTotalNum = termAVGTotalNum + 1
                    helpMap["termAVGTotalNum"] = termAVGTotalNum

                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            def termAVGTotal = helpMap.computeIfAbsent("termAVGTotal", { 0.00 })
                            termAVGTotal = termAVGTotal + result
                            helpMap["termAVGTotal"] = termAVGTotal
                        }


                        if (termAVGTotalNum > 0) {
                            def termAVGTotal = helpMap.computeIfAbsent("termAVGTotal", { 0.00 })
                            def termAVG = termAVGTotal / termAVGTotalNum
                            initResult["termAVG"] = NumberUtil.round(termAVG, 2).doubleValue()
                        }

                    }
                }
            },
            // 剩余有效期在超过 10 年的发明专利数量（个）
            fMterm10: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                //                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                //                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            if (result > 10) {
                                initResult["fMterm10"] = initResult["fMterm10"] + 1
                            }
                        }
                    }
                }
            },
            // 剩余有效期在 5-10 年间的发明专利数量（个）
            fMterm5_10: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                //                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                //                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            if (result > 5 && result <= 10) {
                                initResult["fMterm5_10"] = initResult["fMterm5_10"] + 1
                            }
                        }
                    }
                }
            },
            // 剩余有效期在 5 年以内的发明专利数量（个）
            fMterm5: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                //                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                //                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            if (result <= 5) {
                                initResult["fMterm5"] = initResult["fMterm5"] + 1
                            }
                        }
                    }
                }
            },
            // 剩余有效期在超过 10 年的实用新型专利数量（个）
            sYterm10: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                //                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                //                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            if (result > 10) {
                                initResult["sYterm10"] = initResult["sYterm10"] + 1
                            }
                        }
                    }
                }
            },
            // 剩余有效期在 5-10 年间的实用新型专利数量（个）
            sYterm5_10: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                //                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                //                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            if (result  > 5 && result <= 10) {
                                initResult["sYterm5_10"] = initResult["sYterm5_10"] + 1
                            }
                        }
                    }
                }
            },
            // 剩余有效期在 5 年以内的实用新型专利数量（个）
            sYterm5: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                //                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                //                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            if (result <= 5) {
                                initResult["sYterm5"] = initResult["sYterm5"] + 1
                            }
                        }
                    }
                }
            },
            // 剩余有效期在超过 10 年的外观设计专利数量（个）
            wGterm10: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                //                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                //                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            if (result > 10) {
                                initResult["wGterm10"] = initResult["wGterm10"] + 1
                            }
                        }
                    }
                }
            },
            // 剩余有效期在 5-10 年间的外观设计专利数量（个）
            wGterm5_10: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                //                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                //                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            if (result > 5 && result <= 10) {
                                initResult["wGterm5_10"] = initResult["wGterm5_10"] + 1
                            }
                        }
                    }
                }
            },
            // 剩余有效期在 5 年以内的外观设计专利数量（个）
            wGterm5: {DataCompanyIprPatentAllExWarehouseVO vo,Map initResult,param ->
                // 有效 字典id 1922901306708627458
                if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                    def applyDate = vo.getBasic().getApplyDate()
                    if (applyDate != null) {
                        def now = LocalDate.now()
                        def termDays = ChronoUnit.DAYS.between(applyDate, now)
                        def termYears = termDays / 365
                        def result = null
                        // 有效 字典id 1922901306708627458
                        if (Objects.equals(vo.basic.getCurrentRightStatusDictId(),1922901306708627458L)) {
                            // 发明 字典id 1907664719599116290
                            if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719599116290L)) {
                                //                                result = 20.00 - termYears;
                            }
                            // 实用新型 字典id 1907664719485870081
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719485870081L)) {
                                //                                result = 10.00 - termYears;
                            }
                            // 外观设计 字典id 1907664719255183361
                            else if (Objects.equals(vo.basic.getPatentTypeDictId(),1907664719255183361L)) {
                                result = 15.00 - termYears;
                            }
                        }
                        if (result != null) {
                            if (result <= 5) {
                                initResult["wGterm5"] = initResult["wGterm5"] + 1
                            }
                        }
                    }
                }
            }
    ] as LinkedHashMap

    return initResultCalculateMap
}
