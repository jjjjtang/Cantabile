<script setup>
import PersonalComponent from '@/components/profile/PersonalComponent.vue'
import ContentComponent from '@/components/profile/ContentComponent.vue'
</script>


<template>
  <el-container id="CoverContainer"style="display: flex; flex-direction: column;">
    <PersonalComponent></PersonalComponent>
    <ContentComponent></ContentComponent>

  </el-container>
</template>

<style scoped>
#CoverContainer {
  width: 100%;
  height: 1000px;
  margin: 0;
}
</style>