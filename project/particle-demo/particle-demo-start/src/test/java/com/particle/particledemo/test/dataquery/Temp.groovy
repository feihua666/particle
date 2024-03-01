import cn.hutool.core.net.url.UrlQuery;
import cn.hutool.core.util.StrUtil
def pageNo = 1
def pageSize = 10
if(data.pageNo != null){
    pageNo = data.pageNo
}
if(data.pageSize != null){
    pageSize = data.pageSize
}
// 转换一下参数
def queryStringMap = [name: data.name,code: data.uscc,page_num: pageNo,page_size: pageSize]
def queryString = UrlQuery.of(queryStringMap).toString()
def result = datasourceApi.invoke('ent_tmlist',null,queryString)


public static void replaceDomainMap(Object oMap){
    def removeDomain = 'https://oss.quanweidu.com'
    if (oMap instanceof Map) {

        oMap.entrySet().each { entry ->
            if (entry.getValue() instanceof String) {
                if (StrUtil.startWith(entry.getValue(),removeDomain)) {
                    entry.setValue(entry.getValue().substring(removeDomain.length()))
                }
            }else if (entry.getValue() instanceof List) {
                replaceDomainList(entry.getValue())
            }else if (entry.getValue() instanceof Map) {
                replaceDomainMap(entry.getValue())
            }
        }
    }
}

public static void replaceDomainList(Object oList) {
    if (oList instanceof List) {
        for (final def item in oList) {
            replaceDomainMap(item)
        }
    }
}

public static void replaceDomain(Object o) {
    o instanceof List ? replaceDomainList(o) : replaceDomainMap(o)
}

replaceDomain(result)

result;