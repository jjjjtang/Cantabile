<script setup>
import ProfileContainer from "@/components/profile/ProfileContainer.vue"
import LikeSongsComponent from "@/components/profile/LikeSongsComponent.vue"
import LikeComponent from "@/components/profile/LikeComponent.vue";
import MusicPlayerTwo from "@/components/MusicPlayerTwo.vue";
</script>

<template>
  <div id="CoverContainer">
    <profile-container></profile-container>
    <LikeSongsComponent></LikeSongsComponent>
    <LikeComponent></LikeComponent>
    <music-player-two></music-player-two>
  </div>
</template>

<style scoped>
#CoverContainer {
  height: 700px;
  background-color: #f2f2f2;
  display: flex;
}



</style>