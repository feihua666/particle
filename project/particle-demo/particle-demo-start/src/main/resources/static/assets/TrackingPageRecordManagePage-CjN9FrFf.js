import{i as f,d as T,r as u,a as C,b as n,o as x,c as I,e as p,w as b,F as y}from"./index-BGwvwH0Y.js";let g="/admin/web/tracking_page_record";const A=o=>f.delete(g+"/delete",{data:o}),R=o=>f.get(g+"/page",{params:o}),B=[{field:{name:"userNickname"},element:{comp:"el-input",formItemProps:{label:"用户昵称"},compProps:{clearable:!0,placeholder:"左前缀匹配"}}},{field:{name:"sessionMd5"},element:{comp:"el-input",formItemProps:{label:"会话标识md5"},compProps:{clearable:!0}}},{field:{name:"trackingPageCode"},element:{comp:"el-input",formItemProps:{label:"页面编码"},compProps:{clearable:!0,placeholder:"左前缀匹配"}}},{field:{name:"actionType"},element:{comp:"el-input",formItemProps:{label:"行为类型"},compProps:{clearable:!0}}},{field:{name:"actionAtStart"},element:{comp:"PtDatePicker",formItemProps:{label:"行为开始时间"},compProps:{type:"datetime"}}},{field:{name:"actionAtEnd"},element:{comp:"PtDatePicker",formItemProps:{label:"行为结束时间"},compProps:{type:"datetime"}}},{field:{name:"traceId"},element:{comp:"el-input",formItemProps:{label:"追踪id"},compProps:{clearable:!0}}}],M=T({__name:"TrackingPageRecordManagePage",props:{trackingPageCode:{type:String}},setup(o){const c=u(null),r=C({form:{trackingPageCode:o.trackingPageCode},formComps:B,tableColumns:[{prop:"userNickname",label:"用户昵称"},{prop:"userAvatar",label:"用户头像",columnView:"image"},{prop:"isUserTrigger",label:"用户触发",formatter:(e,a,t,s)=>t?"是":"否"},{prop:"session",label:"会话标识",showOverflowTooltip:!0},{prop:"sessionMd5",label:"会话标识md5",showOverflowTooltip:!0},{prop:"imei",label:"设备串号"},{prop:"deviceId",label:"设备id"},{prop:"deviceName",label:"设备名称"},{prop:"trackingPageCode",label:"页面编码"},{prop:"preTrackingPageCode",label:"前驱页面编码"},{prop:"operatingSystem",label:"操作系统及版本"},{prop:"appVersion",label:"客户端版本"},{prop:"actionType",label:"行为类型"},{prop:"actionAt",label:"行为产生时间"},{prop:"actionResult",label:"行为值"},{prop:"actionOnX",label:"行为位置 x"},{prop:"actionOnY",label:"行为位置 y"},{prop:"netType",label:"网络类型"},{prop:"longitude",label:"位置经度"},{prop:"latitude",label:"位置纬度"},{prop:"screenHeight",label:"屏幕高度"},{prop:"screenWidth",label:"屏幕宽度"},{prop:"entryAt",label:"进入页面时间"},{prop:"leaveAt",label:"离开页面时间"},{prop:"duration",label:"页面停留时长"},{prop:"extInfoJson",label:"额外数据",showOverflowTooltip:!0},{prop:"traceId",label:"追踪id",showOverflowTooltip:!0},{prop:"frontTraceId",label:"前端追踪id",showOverflowTooltip:!0},{prop:"remark",label:"描述"}]}),i=u({buttonText:"查询",loading:!1,permission:"admin:web:trackingPageRecord:pageQuery"}),d=()=>{c.value.refreshData()},P=({pageQuery:e})=>R({...r.form,...e}),h={permission:i.value.permission},k=({row:e,column:a,$index:t})=>t<0?[]:(e.id,[{txt:"删除",text:!0,permission:"admin:web:trackingPageRecord:delete",methodConfirmText:`确定要删除 ${e.name} 吗？`,method(){return A({id:e.id}).then(l=>(d(),Promise.resolve(l)))}}]);return(e,a)=>{const t=n("PtForm"),s=n("PtButtonGroup"),l=n("el-table-column"),w=n("PtTable");return x(),I(y,null,[p(t,{form:r.form,method:d,defaultButtonsShow:"submit,reset",submitAttrs:i.value,inline:"",labelWidth:"120",comps:r.formComps},null,8,["form","submitAttrs","comps"]),p(w,{ref_key:"tableRef",ref:c,"default-expand-all":"",dataMethod:P,onDataMethodDataLoading:a[0]||(a[0]=m=>i.value.loading=m),paginationProps:h,columns:r.tableColumns},{defaultAppend:b(()=>[p(l,{label:"操作",width:"180"},{default:b(({row:m,column:_,$index:v})=>[p(s,{options:k({row:m,column:_,$index:v})},null,8,["options"])]),_:1})]),_:1},8,["columns"])],64)}}});export{M as default};