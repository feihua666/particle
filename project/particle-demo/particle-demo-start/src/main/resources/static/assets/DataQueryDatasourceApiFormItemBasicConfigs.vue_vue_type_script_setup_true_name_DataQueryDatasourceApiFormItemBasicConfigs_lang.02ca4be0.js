import{d as m,a as C,ad as g,r as s,b as B,o as d,h as f,c as R,e as D,w as E,W as b,F as O,i as y,a6 as _}from"./index.bd091b8a.js";const N=m({name:"JdbcApiBasicConfig"}),k=m({...N,props:{initJsonStr:{type:String},onSubmit:{type:Function,default:()=>({})}},setup(a,{expose:p}){const t=a,o=C({form:{}}),l=[{field:{name:"dataType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u6570\u636E\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"dataquery_datasource_api_jdbc_basic_config_data_type"},props:{value:"value"}}}},{field:{name:"isSearchCount",value:!0},element:{comp:"el-switch",formItemProps:{label:"\u662F\u5426\u67E5\u8BE2\u603B\u6570",tips:"\u4EC5\u5728\u5206\u9875\u65F6\u6709\u6548"},compProps:{activeText:"\u67E5\u8BE2\u603B\u6570",inactiveText:"\u4E0D\u67E5\u8BE2\u603B\u6570"}}},{field:{name:"sqlTemplateType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"sql\u6A21\u677F\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"dataquery_datasource_api_jdbc_basic_config_sql_template_type"},props:{value:"value"}}}},{field:{name:"sqlTemplate"},element:{comp:"el-input",formItemProps:{label:"sql\u6A21\u677F\u5185\u5BB9",required:!0,tips:`1. enjoy\u6A21\u677F \u6700\u7EC8\u4F1A\u6E32\u67D3\u4E3Asql\u8BED\u53E5\uFF0C \u53D6\u503C\u793A\u4F8B where name = '#(data.name)' data\u4E3A\u8BF7\u6C42\u53C2\u6570\u53E5\u67C4\uFF0C<br/>2. mybatisScript\u6A21\u677F \u6700\u7EC8\u4F1A\u6E32\u67D3\u4E3Asql\u8BED\u53E5\uFF0C \u9700\u8981\u4EE5&lt;script&gt;\u5F00\u5934\uFF0C\u6CE8\u610F\u524D\u9762\u4E0D\u80FD\u6709\u7A7A\u683C \u53D6\u503C\u793A\u4F8B&lt;script&gt;&lt;if test="data.data.name != null"&gt;where name = #{data.data.name}&lt;/if&gt;&lt;/script&gt; \u6CE8\u610F\uFF1Adata.data(\u4E24\u4E2Adata)\u4E3A\u8BF7\u6C42\u53C2\u6570\u53E5\u67C4<br/>3. raw\u6A21\u5F0F \u8BF7\u76F4\u63A5\u5199sql\uFF0C\u4E0D\u5177\u5907\u53D8\u91CF\u6E32\u67D3\u80FD\u529B<br/>4. groovy\u811A\u672C\u652F\u6301\uFF0C\u5177\u6709\u7F16\u7A0B\u7684\u80FD\u529B\u6700\u7EC8\u7ED3\u679C\u5C06\u4F7F\u7528\u8BE5\u811A\u672C\u7684\u8FD4\u56DE\u503C\u76F4\u63A5\u8FD4\u56DE\u6570\u636E,\u6CE8\u610F\uFF1A\u5982\u679C\u8FD4\u56DE\u6570\u636E\u7C7B\u578B\u4E3A\u5B57\u7B26\u4E32\u5219\u505A\u4E3Asql\u76F4\u63A5\u4F7F\u7528\uFF0C\u5426\u5219\u76F4\u63A5\u505A\u4E3A\u8FD4\u56DE\u6570\u636E\uFF0C\u5185\u7F6E\u53E5\u67C4\u4E3A jdbcService \u548C data\uFF0CjdbcService \u4E3A\u67E5\u8BE2\u6570\u636E\u670D\u52A1\uFF0Cdata\u4E3A\u6301\u6709\u8BF7\u6C42\u53C2\u6570\u53E5\u67C4`},compProps:{type:"textarea",rows:15,clearable:!0}}}];g(()=>{if(t.initJsonStr){let u=JSON.parse(t.initJsonStr);for(let e in u)o.form[e]=u[e]}});const i=s({buttonText:"\u786E\u8BA4"}),F=()=>{t.onSubmit()};return p({form:o.form}),(u,e)=>{const c=B("PtForm");return d(),f(c,{ref:"selfFormRef",form:o.form,class:"pt-wdith-100-pc",method:F,defaultButtonsShow:"submit,reset",submitAttrs:i.value,layout:1,comps:l},null,8,["form","submitAttrs"])}}}),$=m({name:"HttpApiBasicConfig"}),U=m({...$,props:{initJsonStr:{type:String},onSubmit:{type:Function,default:()=>({})}},setup(a,{expose:p}){const t=a,o=C({form:{}}),l=[{field:{name:"requestMethod"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u8BF7\u6C42\u65B9\u6CD5",required:!0},compProps:{dictParam:{groupCode:"dataquery_datasource_api_http_basic_config_request_method"},props:{value:"value"}}}},{field:{name:"requestContentType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u8BF7\u6C42\u5185\u5BB9\u7C7B\u578B",required:!0,tips:"get\u8BF7\u6C42\u53EF\u4EE5\u8BF7\u6C42\u6CA1\u6709\u4F7F\u7528\u4E00\uFF0C\u968F\u4FBF\u9009\u4E00\u4E2A"},compProps:{dictParam:{groupCode:"dataquery_datasource_api_http_basic_config_content_type"},props:{value:"value"}}}},{field:{name:"responseContentType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u54CD\u5E94\u5185\u5BB9\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"dataquery_datasource_api_http_basic_config_content_type"},props:{value:"value"}}}},{field:{name:"requestUrlRenderType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u8BF7\u6C42\u5730\u5740\u6E32\u67D3\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"dataquery_datasource_api_http_basic_config_request_url_render_type"},props:{value:"value"}}}},{field:{name:"requestUrlTemplate"},element:{comp:"el-input",formItemProps:{label:"\u8BF7\u6C42\u5730\u5740",required:!0,tips:"\u5FC5\u987B\u8FD4\u56DE\u5730\u5740\u5B57\u7B26\u4E32\uFF0Cdata\u4E3A\u8BF7\u6C42\u53C2\u6570\u53E5\u67C4\uFF0CqueryString \u4E3A\u67E5\u8BE2\u53C2\u6570\u53E5\u67C4,username\u548Cpassword\u4E3A\u7528\u6237\u540D\u548C\u5BC6\u7801\u6765\u6E90\u4E3A\u6570\u636E\u6E90\u914D\u7F6E"},compProps:{clearable:!0,placeholder:"\u5982\uFF1A/getUserList"}}}];g(()=>{if(t.initJsonStr){let u=JSON.parse(t.initJsonStr);for(let e in u)o.form[e]=u[e]}});const i=s({buttonText:"\u786E\u8BA4"}),F=()=>{t.onSubmit()};return p({form:o.form}),(u,e)=>{const c=B("PtForm");return d(),f(c,{ref:"selfFormRef",form:o.form,class:"pt-wdith-100-pc",method:F,defaultButtonsShow:"submit,reset",submitAttrs:i.value,layout:[3,1],comps:l},null,8,["form","submitAttrs"])}}}),M=m({name:"Neo4jApiBasicConfig"}),H=m({...M,props:{initJsonStr:{type:String},onSubmit:{type:Function,default:()=>({})}},setup(a,{expose:p}){const t=a,o=C({form:{}}),l=[{field:{name:"dataType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u6570\u636E\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"dataquery_datasource_api_neo4j_basic_config_data_type"},props:{value:"value"}}}},{field:{name:"cqlTemplateType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"cql\u6A21\u677F\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"dataquery_datasource_api_neo4j_basic_config_cql_template_type"},props:{value:"value"}}}},{field:{name:"cqlTemplate"},element:{comp:"el-input",formItemProps:{label:"cql\u6A21\u677F\u5185\u5BB9",required:!0,tips:"1. enjoy\u6A21\u677F \u6700\u7EC8\u4F1A\u6E32\u67D3\u4E3Acql\u8BED\u53E5\uFF0C \u53D6\u503C\u793A\u4F8B where name = '#(data.name)' data\u4E3A\u8BF7\u6C42\u53C2\u6570\u53E5\u67C4\uFF0C<br/>2. raw\u6A21\u5F0F \u8BF7\u76F4\u63A5\u5199cql\uFF0C\u4E0D\u5177\u5907\u53D8\u91CF\u6E32\u67D3\u80FD\u529B<br/>3. groovy\u811A\u672C\u652F\u6301\uFF0C\u5177\u6709\u7F16\u7A0B\u7684\u80FD\u529B\u6700\u7EC8\u7ED3\u679C\u5C06\u4F7F\u7528\u8BE5\u811A\u672C\u7684\u8FD4\u56DE\u503C\u76F4\u63A5\u8FD4\u56DE\u6570\u636E,\u6CE8\u610F\uFF1A\u5982\u679C\u8FD4\u56DE\u6570\u636E\u7C7B\u578B\u4E3A\u5B57\u7B26\u4E32\u5219\u505A\u4E3Acql\u76F4\u63A5\u4F7F\u7528\uFF0C\u5426\u5219\u76F4\u63A5\u505A\u4E3A\u8FD4\u56DE\u6570\u636E\uFF0C\u5185\u7F6E\u53E5\u67C4\u6709\u4E09\u4E2A\u4E3A driver\u3001neo4jClient\u3001neo4jTemplate \u548C data\uFF0Cdriver\u3001neo4jClient\u3001neo4jTemplate\u4E3A\u67E5\u8BE2\u6570\u636E\u670D\u52A1\uFF0Cdata\u4E3A\u6301\u6709\u8BF7\u6C42\u53C2\u6570\u53E5\u67C4"},compProps:{type:"textarea",rows:15,clearable:!0}}},{field:{name:"cqlCountTemplate"},element:{comp:"el-input",formItemProps:{label:"cql\u603B\u6570\u6A21\u677F\u5185\u5BB9",required:({form:u,formData:e})=>e.dataType&&e.dataType.value=="page",tips:"\u8BE5\u6A21\u677F\u4EC5\u5728\u5206\u9875\u65F6\u4F7F\u7528\uFF0C\u7528\u6765\u7EDF\u8BA1\u67E5\u8BE2\u6570\u636E\u7684\u603B\u6570\uFF0C\u987B\u548C[cql\u6A21\u677F\u5185\u5BB9]\u67E5\u8BE2\u6761\u4EF6\u4FDD\u6301\u4E00\u81F4,\u9700\u8981\u8FD4\u56DE\u4E00\u4E2A\u7EDF\u8BA1\u6570\u91CF\uFF0C\u5982\uFF1Amatch(n) return count(*)"},compProps:{type:"textarea",rows:15,clearable:!0}}}];g(()=>{if(t.initJsonStr){let u=JSON.parse(t.initJsonStr);for(let e in u)o.form[e]=u[e]}});const i=s({buttonText:"\u786E\u8BA4"}),F=()=>{t.onSubmit()};return p({form:o.form}),(u,e)=>{const c=B("PtForm");return d(),f(c,{ref:"selfFormRef",form:o.form,class:"pt-wdith-100-pc",method:F,defaultButtonsShow:"submit,reset",submitAttrs:i.value,layout:1,comps:l},null,8,["form","submitAttrs"])}}}),K=m({name:"EsApiBasicConfig"}),L=m({...K,props:{initJsonStr:{type:String},onSubmit:{type:Function,default:()=>({})}},setup(a,{expose:p}){const t=a,o=C({form:{}}),l=[{field:{name:"dataType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"\u6570\u636E\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"dataquery_datasource_api_es_basic_config_data_type"},props:{value:"value"}}}},{field:{name:"dslTemplateType"},element:{comp:"PtDictFrontSelect",formItemProps:{label:"dsl\u6A21\u677F\u7C7B\u578B",required:!0},compProps:{dictParam:{groupCode:"dataquery_datasource_api_es_basic_config_dsl_template_type"},props:{value:"value"}}}},{field:{name:"indexNames"},element:{comp:"el-input",formItemProps:{label:"\u7D22\u5F15\u540D\u79F0",tips:"\u591A\u4E2A\u4EE5\u9017\u53F7\u5206\u9694"},compProps:{clearable:!0}}},{field:{name:"dslTemplate"},element:{comp:"el-input",formItemProps:{label:"dsl\u6A21\u677F\u5185\u5BB9",required:!0,labelTips:'\u652F\u6301\u4E0Equery\u540C\u7EA7\u6307\u5B9A \u5982\uFF1AindexNames: "xxx", \u7684\u7D22\u5F15\u540D\u79F0\uFF0C\u5982\u679C\u4E0A\u9762\u7D22\u5F15\u540D\u79F0\u5B57\u6BB5\u6307\u5B9A\u4E86\u7D22\u5F15\u540D\u79F0\uFF0C\u5219\u8BE5\u8BBE\u7F6E\u4E0D\u751F\u6548\uFF0C\u5EFA\u8BAE\u4F7F\u7528\u4E0A\u9762\u7684\u7D22\u5F15\u540D\u79F0\u5B57\u6BB5',tips:'1. enjoy\u6A21\u677F \u6700\u7EC8\u4F1A\u6E32\u67D3\u4E3Adsl\u8BED\u53E5\uFF0C \u53D6\u503C\u793A\u4F8B {"query":{"term":{"title":"#(data.name)"}}} data\u4E3A\u8BF7\u6C42\u53C2\u6570\u53E5\u67C4\uFF0C<br/>2. raw\u6A21\u5F0F \u8BF7\u76F4\u63A5\u5199dsl\uFF0C\u4E0D\u5177\u5907\u53D8\u91CF\u6E32\u67D3\u80FD\u529B<br/>3. groovy\u811A\u672C\u652F\u6301\uFF0C\u5177\u6709\u7F16\u7A0B\u7684\u80FD\u529B\u6700\u7EC8\u7ED3\u679C\u5C06\u4F7F\u7528\u8BE5\u811A\u672C\u7684\u8FD4\u56DE\u503C\u76F4\u63A5\u8FD4\u56DE\u6570\u636E,\u6CE8\u610F\uFF1A\u5982\u679C\u8FD4\u56DE\u6570\u636E\u7C7B\u578B\u4E3A\u5B57\u7B26\u4E32\u5219\u505A\u4E3Adsl\u76F4\u63A5\u4F7F\u7528\uFF0C\u5426\u5219\u76F4\u63A5\u505A\u4E3A\u8FD4\u56DE\u6570\u636E\uFF0C\u5185\u7F6E\u53E5\u67C4\u6709\u4E09\u4E2A\u4E3A elasticsearchRestTemplate\u3001restHighLevelClient\u3001restClient \u548C data\uFF0CelasticsearchRestTemplate\u3001restHighLevelClient\u3001restClient\u4E3A\u67E5\u8BE2\u6570\u636E\u670D\u52A1\uFF0Cdata\u4E3A\u6301\u6709\u8BF7\u6C42\u53C2\u6570\u53E5\u67C4\u6CE8\u610F\uFF1A\u5206\u9875\u67E5\u8BE2\u4E0D\u652F\u6301es\u7684from\u548Csize\u5C5E\u6027\u67E5\u8BE2\uFF0C\u8BBE\u7F6E\u540E\u8BF7\u6C42\u5206\u9875\u53C2\u6570\u65E0\u6548\uFF0C\u8BF7\u6839\u636E\u5206\u9875\u53C2\u6570\u4F20\u53C2\u4F7F\u7528\uFF0C\u5355\u6761\u548C\u591A\u6761\u652F\u6301'},compProps:{type:"textarea",rows:15,clearable:!0,placeholder:`{
    "query": {
        "match": {
            "title": "\u6D4B\u8BD5es0"
        }
    },
    "sort": {
            "id": {
                "order": "desc"
            }
        }
}`}}},{field:{name:"dslCountTemplate"},element:{comp:"el-input",formItemProps:{label:"dsl\u603B\u6570\u6A21\u677F\u5185\u5BB9",tips:"\u8BE5\u6A21\u677F\u4EC5\u5728\u5206\u9875\u65F6\u4F7F\u7528\uFF0C\u7528\u6765\u7EDF\u8BA1\u67E5\u8BE2\u6570\u636E\u7684\u603B\u6570\uFF0C\u987B\u548C[dsl\u6A21\u677F\u5185\u5BB9]\u67E5\u8BE2\u6761\u4EF6\u4FDD\u6301\u4E00\u81F4\uFF0C\u6CE8\u610Fes\u5728\u67E5\u8BE2\u65F6\u4F1A\u8FD4\u56DE\u603B\u6570\uFF0C\u4E0D\u5EFA\u8BAE\u8BBE\u7F6E\u8BE5\u9879"},compProps:{type:"textarea",rows:15,clearable:!0}}}];g(()=>{if(t.initJsonStr){let u=JSON.parse(t.initJsonStr);for(let e in u)o.form[e]=u[e]}});const i=s({buttonText:"\u786E\u8BA4"}),F=()=>{t.onSubmit()};return p({form:o.form}),(u,e)=>{const c=B("PtForm");return d(),f(c,{ref:"selfFormRef",form:o.form,class:"pt-wdith-100-pc",method:F,defaultButtonsShow:"submit,reset",submitAttrs:i.value,layout:1,comps:l},null,8,["form","submitAttrs"])}}}),z=y("div",{id:"dataqueryDatasourceApiJdbcBasicConfigDialogFooter"},null,-1),Q=y("div",{id:"dataqueryDatasourceApiHttpBasicConfigDialogFooter"},null,-1),W=y("div",{id:"dataqueryDatasourceApiNeo4jBasicConfigDialogFooter"},null,-1),G=y("div",{id:"dataqueryDatasourceApiEsBasicConfigDialogFooter"},null,-1),X=m({name:"DataQueryDatasourceApiFormItemBasicConfigs"}),Z=m({...X,props:{form:{type:Object,required:!0},formData:{type:Object,required:!0}},setup(a,{expose:p}){const t=a,o=s(null),l=s(null),i=s(null),F=s(null),u=C({jdbc:{dialogVisible:!1},http:{dialogVisible:!1},neo4j:{dialogVisible:!1},es:{dialogVisible:!1}}),e=h=>JSON.stringify(h),c=()=>{t.form.configJson=e(o.value.form),u.jdbc.dialogVisible=!1},J=()=>{t.form.configJson=e(l.value.form),u.http.dialogVisible=!1},j=()=>{t.form.configJson=e(i.value.form),u.neo4j.dialogVisible=!1},T=()=>{t.form.configJson=e(F.value.form),u.es.dialogVisible=!1},v=s(!1),V=()=>{_(()=>{v.value=!0})},P=s(!1),w=()=>{_(()=>{P.value=!0})},q=s(!1),x=()=>{_(()=>{q.value=!0})},S=s(!1),I=()=>{_(()=>{S.value=!0})};return p({reactiveData:u}),(h,r)=>{const A=B("el-dialog");return d(),R(O,null,[D(A,{modelValue:u.jdbc.dialogVisible,"onUpdate:modelValue":r[0]||(r[0]=n=>u.jdbc.dialogVisible=n),width:"70%",title:"jdbc\u914D\u7F6EJson",onOpen:V,onClosed:r[1]||(r[1]=n=>v.value=!1),"append-to-body":"","destroy-on-close":""},{footer:E(()=>[z]),default:E(()=>[v.value?(d(),f(k,{key:0,ref_key:"jdbcConfigRef",ref:o,initJsonStr:a.form.configJson,onSubmit:c,buttonsTeleportProps:{disabled:!1,to:"#dataqueryDatasourceApiJdbcBasicConfigDialogFooter"}},null,8,["initJsonStr"])):b("",!0)]),_:1},8,["modelValue"]),D(A,{modelValue:u.http.dialogVisible,"onUpdate:modelValue":r[2]||(r[2]=n=>u.http.dialogVisible=n),width:"70%",title:"http\u914D\u7F6EJson",onOpen:w,onClosed:r[3]||(r[3]=n=>P.value=!1),"append-to-body":"","destroy-on-close":""},{footer:E(()=>[Q]),default:E(()=>[P.value?(d(),f(U,{key:0,ref_key:"httpConfigRef",ref:l,initJsonStr:a.form.configJson,onSubmit:J,buttonsTeleportProps:{disabled:!1,to:"#dataqueryDatasourceApiHttpBasicConfigDialogFooter"}},null,8,["initJsonStr"])):b("",!0)]),_:1},8,["modelValue"]),D(A,{modelValue:u.neo4j.dialogVisible,"onUpdate:modelValue":r[4]||(r[4]=n=>u.neo4j.dialogVisible=n),width:"70%",title:"neo4j\u914D\u7F6EJson",onOpen:x,onClosed:r[5]||(r[5]=n=>q.value=!1),"append-to-body":"","destroy-on-close":""},{footer:E(()=>[W]),default:E(()=>[q.value?(d(),f(H,{key:0,ref_key:"neo4jConfigRef",ref:i,initJsonStr:a.form.configJson,onSubmit:j,buttonsTeleportProps:{disabled:!1,to:"#dataqueryDatasourceApiNeo4jBasicConfigDialogFooter"}},null,8,["initJsonStr"])):b("",!0)]),_:1},8,["modelValue"]),D(A,{modelValue:u.es.dialogVisible,"onUpdate:modelValue":r[6]||(r[6]=n=>u.es.dialogVisible=n),width:"70%",title:"es\u914D\u7F6EJson",onOpen:I,onClosed:r[7]||(r[7]=n=>S.value=!1),"append-to-body":"","destroy-on-close":""},{footer:E(()=>[G]),default:E(()=>[S.value?(d(),f(L,{key:0,ref_key:"esConfigRef",ref:F,initJsonStr:a.form.configJson,onSubmit:T,buttonsTeleportProps:{disabled:!1,to:"#dataqueryDatasourceApiEsBasicConfigDialogFooter"}},null,8,["initJsonStr"])):b("",!0)]),_:1},8,["modelValue"])],64)}}});export{Z as _};