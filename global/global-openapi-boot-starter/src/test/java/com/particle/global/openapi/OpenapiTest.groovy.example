import cn.hutool.core.lang.UUID
import cn.hutool.crypto.digest.DigestUtil
import com.particle.global.tool.http.HttpClientTool

def clientId = '1753440086675959808'
def clientClientSecret = 'e32e54cc6015423ba01d7e69fe295030'


def timestamp = System.currentTimeMillis() + ''
def nonce = UUID.randomUUID().toString()
def urlPrefix = "http://localhost:8080"
def body = "{\n" +
        "\"name\": \"北京百度网讯科技有限公司\",\n" +
        "\"uscc\": \"91110000802100433B\"\n" +
        "}"

def signStr = "clientId=${clientId}timestamp=${timestamp}nonce=${nonce}${body}${clientClientSecret}"

def signature = DigestUtil.sha256Hex(signStr)

def extConfig = HttpClientTool.ExtConfig.create()
        .addHeader("clientId", clientId)
        .addHeader("timestamp", timestamp)
        .addHeader("nonce", nonce)
        .addHeader("signature", signature)
def start = System.currentTimeMillis()
//def json = HttpClientTool.postJson(urlPrefix + "/openapi/dq/ent/basic_address", body, extConfig)
def json = HttpClientTool.postJson(urlPrefix + "/openapi/dq/ent/annual_report", body, extConfig)

//def json = HttpClientTool.postJson(urlPrefix + "/openapi/dq/ent/deep", body, extConfig)
println(json)
println(System.currentTimeMillis() - start)
