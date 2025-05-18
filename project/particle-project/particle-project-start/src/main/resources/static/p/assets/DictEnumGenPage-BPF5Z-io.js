import{l as g}from"./dictAdminApi-zHqxBvI8.js";import{L as I,d as C,a as D,r as l,y as v,M as _,b as c,o as $,c as P,e as i,F as M,B as S,N as T}from"./index-COCB7OXO.js";const w=s=>{let o=[];return s.forEach(e=>{let m=`
	/**
	 * ${e.name}
	 */
	 ${e.value}
    `;o.push(m)}),o.join(",")},x=({className:s,author:o="generated",dictGroupData:e,dictItems:m=[]})=>`
import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * ${e.name} 字典项
 * </p>
 *
 * @author ${o}
 * @since ${I()}
 */
public enum ${s} implements IDictItem {
	${w(m)};

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.${e.code}.groupCode();
	}

	/**
	 * ${e.name} 字典组
	 */
	public enum Group implements IDictGroup {
		${e.code};

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
    `,F=C({__name:"DictEnumGenPage",setup(s){const{proxy:o}=S(),e=D({form:{},formData:{},result:""}),m=l([{field:{name:"dictId",valueChange({form:t,formData:r}){setTimeout(()=>{var a;t.className=v(_((a=r.dictId)==null?void 0:a.code,["-"]))},400)}},element:{comp:"PtCascader",formItemProps:{label:"选择一个字典组",required:!0},compProps:{clearable:!0,dataMethod:()=>g({}),dataMethodResultHandleConvertToTree:!0}}},{field:{name:"className"},element:{comp:"el-input",formItemProps:{label:"类名称",required:!0},compProps:{clearable:!0}}},{field:{name:"author",value:"yw"},element:{comp:"el-input",formItemProps:{label:"作者",required:!0},compProps:{clearable:!0}}}]),n=l({buttonText:"确认生成",beforeMethod:()=>{var t;return e.form.dictId&&!((t=e.formData.dictId)!=null&&t.isGroup)?(d("仅支持选择字典组"),!1):!0}}),p=t=>{T({groupCode:e.formData.dictId.code}).then(r=>{e.result=f(e.form.className,e.form.author,r.data.data)})},d=t=>{o.$message({showClose:!0,message:t,type:"error",showIcon:!0,grouping:!0})},f=(t,r,a)=>x({className:t,author:r,dictGroupData:e.formData.dictId,dictItems:a}),h=()=>"生成成功";return(t,r)=>{const a=c("PtForm"),u=c("el-input");return $(),P(M,null,[i(a,{form:e.form,formData:e.formData,labelWidth:"120",method:p,methodSuccess:h,defaultButtonsShow:"submit,reset",submitAttrs:n.value,inline:"",comps:m.value},null,8,["form","formData","submitAttrs","comps"]),i(u,{modelValue:e.result,"onUpdate:modelValue":r[0]||(r[0]=b=>e.result=b),type:"textarea",rows:"20",placeholder:"生成结果这里显示"},null,8,["modelValue"])],64)}}});export{F as default};
