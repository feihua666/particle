import{h as e}from"./index.49d89bd8.js";let a="/admin/web/openplatform_openapi_record_app_month_bill";const o=t=>e.delete(a+"/delete",{data:t}),r=t=>e.put(a+"/update",t),s=t=>e.get(a+"/detail-for-update",{params:t}),n=t=>e.get(a+"/page",{params:t}),i=t=>e.post(a+"/lastMonthStatistic",t),l=t=>e.post(a+"/thisMonthStatistic",t);export{s as d,i as l,n as p,o as r,l as t,r as u};