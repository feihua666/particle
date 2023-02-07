<script setup name="TestAceEditor" lang="ts">

import {ref} from "vue";

import 'ace-builds/src-min-noconflict/theme-chaos.js'
import 'ace-builds/src-min-noconflict/theme-chrome.js'
import 'ace-builds/src-min-noconflict/theme-cloud9_day.js'
import 'ace-builds/src-min-noconflict/theme-cloud9_night.js'
import 'ace-builds/src-min-noconflict/theme-cloud9_night_low_color.js'
import 'ace-builds/src-min-noconflict/theme-clouds.js'
import 'ace-builds/src-min-noconflict/theme-clouds_midnight.js'
import 'ace-builds/src-min-noconflict/theme-cobalt.js'
import 'ace-builds/src-min-noconflict/theme-crimson_editor.js'
import 'ace-builds/src-min-noconflict/theme-dawn.js'
import 'ace-builds/src-min-noconflict/theme-dracula.js'
import 'ace-builds/src-min-noconflict/theme-dreamweaver.js'
import 'ace-builds/src-min-noconflict/theme-eclipse.js'
import 'ace-builds/src-min-noconflict/theme-github.js'
import 'ace-builds/src-min-noconflict/theme-gob.js'
import 'ace-builds/src-min-noconflict/theme-gruvbox.js'
import 'ace-builds/src-min-noconflict/theme-gruvbox_dark_hard.js'
import 'ace-builds/src-min-noconflict/theme-gruvbox_light_hard.js'
import 'ace-builds/src-min-noconflict/theme-idle_fingers.js'
import 'ace-builds/src-min-noconflict/theme-iplastic.js'
import 'ace-builds/src-min-noconflict/theme-katzenmilch.js'
import 'ace-builds/src-min-noconflict/theme-kr_theme.js'
import 'ace-builds/src-min-noconflict/theme-kuroir.js'
import 'ace-builds/src-min-noconflict/theme-merbivore.js'
import 'ace-builds/src-min-noconflict/theme-merbivore_soft.js'
import 'ace-builds/src-min-noconflict/theme-mono_industrial.js'
import 'ace-builds/src-min-noconflict/theme-monokai.js'
import 'ace-builds/src-min-noconflict/theme-nord_dark.js'
import 'ace-builds/src-min-noconflict/theme-one_dark.js'
import 'ace-builds/src-min-noconflict/theme-pastel_on_dark.js'
import 'ace-builds/src-min-noconflict/theme-solarized_dark.js'
import 'ace-builds/src-min-noconflict/theme-solarized_light.js'
import 'ace-builds/src-min-noconflict/theme-sqlserver.js'
import 'ace-builds/src-min-noconflict/theme-terminal.js'
import 'ace-builds/src-min-noconflict/theme-textmate.js'
import 'ace-builds/src-min-noconflict/theme-tomorrow.js'
import 'ace-builds/src-min-noconflict/theme-tomorrow_night.js'
import 'ace-builds/src-min-noconflict/theme-tomorrow_night_blue.js'
import 'ace-builds/src-min-noconflict/theme-tomorrow_night_bright.js'
import 'ace-builds/src-min-noconflict/theme-tomorrow_night_eighties.js'
import 'ace-builds/src-min-noconflict/theme-twilight.js'
import 'ace-builds/src-min-noconflict/theme-vibrant_ink.js'
import 'ace-builds/src-min-noconflict/theme-xcode.js'


const modelValue = ref('')
const theme = ref('')
const themeOptions = [
  'ace/theme/chaos',
  'ace/theme/chrome',
  'ace/theme/cloud9_day',
  'ace/theme/cloud9_night',
  'ace/theme/cloud9_night_low_color',
  'ace/theme/clouds',
  'ace/theme/clouds_midnight',
  'ace/theme/cobalt',
  'ace/theme/crimson_editor',
  'ace/theme/dawn',
  'ace/theme/dracula',
  'ace/theme/dreamweaver',
  'ace/theme/eclipse',
  'ace/theme/github',
  'ace/theme/gob',
  'ace/theme/gruvbox',
  'ace/theme/gruvbox_dark_hard',
  'ace/theme/gruvbox_light_hard',
  'ace/theme/idle_fingers',
  'ace/theme/iplastic',
  'ace/theme/katzenmilch',
  'ace/theme/kr_theme',
  'ace/theme/kuroir',
  'ace/theme/merbivore',
  'ace/theme/merbivore_soft',
  'ace/theme/mono_industrial',
  'ace/theme/monokai',
  'ace/theme/nord_dark',
  'ace/theme/one_dark',
  'ace/theme/pastel_on_dark',
  'ace/theme/solarized_dark',
  'ace/theme/solarized_light',
  'ace/theme/sqlserver',
  'ace/theme/terminal',
  'ace/theme/textmate',
  'ace/theme/tomorrow',
  'ace/theme/tomorrow_night',
  'ace/theme/tomorrow_night_blue',
  'ace/theme/tomorrow_night_bright',
  'ace/theme/tomorrow_night_eighties',
  'ace/theme/twilight',
  'ace/theme/vibrant_ink',
  'ace/theme/xcode'
].map((item)=>({id: item,name:item}))
</script>
<template>

  <PtRadioGroup v-model="theme" :options="themeOptions"></PtRadioGroup>
  <PtAceEditor v-model="modelValue"  :theme="theme" mode="ace/mode/json"></PtAceEditor>
</template>


<style scoped>

</style>