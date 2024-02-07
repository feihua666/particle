// data 为数据源接口返回结果对象，是一个map

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.util.StrUtil
import com.particle.global.tool.obj.NullObj;
import cn.hutool.core.util.ObjectUtil;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;

def removeDomain = 'https://oss.quanweidu.com'
def result = data

if(result == null){
// 强制使用自定义对象返回
    result = NullObj.NULL
}else{
    def resultTemp = result as Map
    def resultNew = [:]
    resultTemp.forEach { key, value ->
        // 出错设置为空
        if (value == null || !StrUtil.equals("000000", value.ret_code)) {
            resultNew.put(key,null)
        }else {
            def newValue = null
            if ("basic".equals(key)) {
                newValue = BeanUtil.getProperty(value, 'result')
            }else {
                newValue = BeanUtil.getProperty(value, 'result.items')
            }
            //去掉域名
            if (!ObjectUtil.isNull(newValue)) {
                if (newValue instanceof Map) {
                    def newValueTemp = [:]
                    newValue.each { key1, value1 ->
                        newValueTemp.put(key1, value1)
                        if (value1 instanceof String) {
                            if (StrUtil.startWith(value1,removeDomain)) {
                                newValueTemp.put(key1, value1.substring(removeDomain.length()))
                            }
                        }
                    }
                    newValue = newValueTemp
                }
                else if (newValue instanceof List) {
                    def newValueTemp = []
                    for (final def item in newValue) {
                        if (item instanceof Map) {
                            def newItemValueTemp = [:]
                            item.each { key1, value1 ->
                                newItemValueTemp.put(key1, value1)
                                if (value1 instanceof String) {
                                    if (StrUtil.startWith(value1,removeDomain)) {
                                        newItemValueTemp.put(key1, value1.substring(removeDomain.length()))
                                    }
                                }
                            }
                            newValueTemp.add(newItemValueTemp)
                        }
                    }
                    newValue = newValueTemp
                }
            }
            resultNew.put(key, newValue)
        }
    }
    result = resultNew
}
// 如果为空强制使用自定义对象返回
if(ObjectUtil.isNull(result)){
    result = SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND)
}

result;

