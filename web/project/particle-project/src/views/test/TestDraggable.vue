<script setup name="TestDraggable" lang="ts">

import {ref} from "vue";
import draggable from 'vuedraggable'

const drag = ref(false)
const myArray = ref([
  {
    "name": "John 1",
    "id": 0
  },
  {
    "name": "Joao 2",
    "id": 1
  },
  {
    "name": "Jean 3",
    "id": 2
  }
])
</script>
<template>
  <draggable
      v-model="myArray"
      group="people"
      @start="drag=true"
      @end="drag=false"
      item-key="id">
    <template #item="{element}">
      <div>{{element.name}}</div>
    </template>
  </draggable>
</template>


<style scoped>

</style>
