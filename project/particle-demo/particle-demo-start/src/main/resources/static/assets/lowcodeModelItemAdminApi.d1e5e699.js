import{g as t}from"./index.6cdd7c8b.js";let a="/admin/web/lowcode-model-item";const o=e=>t.post(a+"/create",e),s=e=>t.delete(a+"/delete",{data:e}),d=e=>t.put(a+"/update",e),p=e=>t.get(a+"/detail-for-update",{params:e}),n=e=>t.get(a+"/page",{params:e});export{o as c,p as d,n as p,s as r,d as u};