<template>
  <ModelThree v-if="computedModelType == 'json' " v-bind="$attrs"></ModelThree>
  <ModelObj v-else-if="computedModelType == 'obj' " v-bind="$attrs"></ModelObj>
  <ModelFbx v-else-if="computedModelType == 'fbx' " v-bind="$attrs"></ModelFbx>
  <ModelStl v-else-if="computedModelType == 'stl' " v-bind="$attrs"></ModelStl>
  <ModelCollada v-else-if="computedModelType == 'dae' " v-bind="$attrs"></ModelCollada>
  <ModelPly v-else-if="computedModelType == 'ply' " v-bind="$attrs"></ModelPly>
  <ModelGltf v-else-if="computedModelType == 'gltf' " v-bind="$attrs"></ModelGltf>
  <div v-else>不支持的模型类型 {{computedModelType}}</div>
</template>

<script>
/**
 * 3d 模型加载
 * 兼容 vue-3d-model 全部属性和事件
 * 依赖：
 * npm install vue-3d-model --save
 * 参考：
 * https://github.com/hujiulong/vue-3d-model
 * https://mrdoob.github.io/model-tag/
 * 示例：
 * https://hujiulong.github.io/vue-3d-model/#/demo-obj
 */
import { ModelThree ,ModelObj,ModelFbx,ModelStl,ModelCollada,ModelPly,ModelGltf} from 'vue-3d-model'
// 支持的模型类型
let supportModelType = ['json','obj','fbx','stl','dae','ply','gltf']
export default {
  name: "ThreeModel",
  components: {
    ModelThree ,ModelObj,ModelFbx,ModelStl,ModelCollada,ModelPly,ModelGltf
  },
  props: {
    /**
     * 模型类型，可选，如果定的src没有后缀，请指定该值
     * 支持的类型 参见 supportModelType
     */
    modelType: {
      type: String,
    }
  },
  computed:{
    // 萨斯噶类型智能判断
    computedModelType(){
      let r = null
      if(this.modelType){
        r = this.modelType
      }

      if(this.$attrs.src){
        for (let i = 0; i < supportModelType.length; i++) {
          if(this.$attrs.src.lastIndexOf('.'+ supportModelType[i]) > 0){
            r = supportModelType[i]
            break
          }
        }
      }
      return r
    }
  },
  watch: {

  }
}
</script>
