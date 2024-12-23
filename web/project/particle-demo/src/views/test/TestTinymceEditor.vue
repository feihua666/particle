<script setup name="TestTinymceEditor" lang="ts">
import {ref, watch} from "vue";

const editorValue = ref('<p th:each="subjectInfo:${subjectInfo}"><div th:src="${subjectInfo.networkTopUrl}" src="assets/img/default.png">sdsd</div></p>')

watch(()=> editorValue.value,(val)=>{
  console.log('editorValue',val)
})

const init={
  valid_elements: '*[th:*]',
  // extended_valid_elements:  '*[th:*]',
}

</script>
<template>
  <PtTinymceEditor v-model="editorValue" outputFormat="html" :init="init"></PtTinymceEditor>

<!--  <Editor v-model="editorValue" api-key="no-api-key"-->
<!--          :init="{-->
<!--        plugins: 'lists link image table code help wordcount'-->
<!--      }"></Editor>-->
</template>


<style scoped>

</style>
