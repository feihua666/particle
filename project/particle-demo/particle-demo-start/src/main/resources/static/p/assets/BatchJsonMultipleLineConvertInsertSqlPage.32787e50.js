import{d as p,a as C,r as c,b as _,o as D,c as v,e as B,f as E,F as S,_ as P}from"./index.49d89bd8.js";const I=p({name:"BatchJsonMultipleLineConvertInsertSqlPage"}),j=p({...I,setup(A){const l=C({form:{},formData:{}}),f=c([{field:{name:"jsonStrLines"},element:{comp:"el-input",formItemProps:{label:"json\u591A\u884C\u6587\u672C",required:!0,tips:"\u6CE8\u610F\uFF1A\u6BCF\u4E00\u884C\u5E94\u8BE5\u662F\u4E00\u4E2A\u5B8C\u6574\u7684json\u5B57\u7B26\u4E32"},compProps:{clearable:!0,rows:10,type:"textarea"}}},{field:{name:"handleLogical",value:"convertInsertSql"},element:{comp:"PtRadioGroup",formItemProps:{label:"\u5904\u7406\u903B\u8F91",required:!0},compProps:{options:[{id:"convertInsertSql",name:"\u8F6C\u6362\u4E3Ainsert sql"}]}}},{field:{name:"tableName",value:"t_test"},element:{comp:"el-input",formItemProps:{label:"\u8868\u540D",required:!0,tips:"\u8868\u540D\uFF0C\u8BF7\u786E\u4FDD\u8868\u540D\u6B63\u786E\uFF0C\u5426\u5219\u53EF\u80FD\u63D2\u5165\u5931\u8D25"},compProps:{clearable:!0}}},{field:{name:"text"},element:{comp:"el-input",formItemProps:{label:"\u7ED3\u679C"},compProps:{clearable:!0,readonly:!0,rows:10,type:"textarea"}}}]),d=c({buttonText:"\u786E\u8BA4\u8F6C\u6362"}),F=u=>{b[u.handleLogical](u)},b={convertInsertSql:u=>{let o=u.jsonStrLines.split(`
`),e="";for(let s=0;s<o.length;s++){let r=[],n=[],a=o[s];if(!a)continue;let i=JSON.parse(a);for(let m in i){r.push(m);let t=i[m];typeof t=="string"&&(t=t.replaceAll("'","\\'"),t="'"+t+"'"),t==null&&(t="null"),n.push(t)}e+="INSERT INTO "+u.tableName,e+=" (",e+=r.join(", "),e+=") ",e+=" VALUES (",e+=n.join(", "),e+=");",e+=`
`}u.text=e}},h=()=>"\u63D0\u53D6\u6210\u529F";return(u,o)=>{const e=_("PtForm");return D(),v(S,null,[B(" \u6DFB\u52A0\u8868\u5355 "),E(e,{form:l.form,formData:l.formData,labelWidth:"80",method:F,methodSuccess:h,defaultButtonsShow:"submit,reset",submitAttrs:d.value,layout:1,"label-position":"top",comps:f.value},null,8,["form","formData","submitAttrs","comps"])],2112)}}}),q=P(j,[["__file","/Users/yw/fh/git-source/particle/web/component/pc/tools/views/front/BatchJsonMultipleLineConvertInsertSqlPage.vue"]]);export{q as default};