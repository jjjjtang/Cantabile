<script setup>

import SongListComponent from "@/components/profile/SongListComponent.vue";
import TableComponent from "@/components/profile/TableComponent.vue";
</script>

<template>
  <div id="LikeSongsContainer">

    <div style="display: flex">
      <div id="LikeSongsTitleContent">
        <svg viewBox="0 0 24 24" id="Love">
          <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5
             2 5.42 4.42 3 7.5 3c1.74 0 3.41 0.81 4.5 2.09
             C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5
             c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/>
        </svg>
      </div>


      <div>
        <div id="playlist">
          Playlist
        </div>

        <div id="TextContent">
          <strong>Liked Songs</strong>
        </div>

        <div id="jiangie">
          <strong>Jiangie</strong>
        </div>

      </div>
    </div>

    <song-list-component></song-list-component>





  </div>

</template>

<style scoped>
#LikeSongsContainer {
  width: 1000px;
  height: 700px;
  background: linear-gradient(to bottom, #ea99c1,#e5bfe7,#f2f2f2);
  margin: 0;
  padding: 0;
}

#LikeSongsTitleContent {
  width: 180px;
  height: 180px;
  background: linear-gradient(135deg, rgba(231, 118, 173, 0.91),rgba(255, 182, 210, 0.9), #fbc3fd);
  margin-left: 30px;
  margin-top: 60px;
  box-shadow: 0 0 3px 3px rgba(215, 144, 180, 0.65);
  border-radius: 15px;
}
#Love{
  width: 70px;
  height: 70px;
  margin-left: 60px;
  margin-top: 60px;
  fill: white;
}
#TextContent {
  margin-left: 20px;
  color: #f2f2f2;
  font-size: 60px;
}
#playlist{
  margin-left: 20px;
  margin-top: 70px;
  color: #f2f2f2;
  font-size: 18px;
}
#jiangie{
  margin-left: 22px;
  color: #f2f2f2;
  margin-top: 15px;
  font-size: 15px;
}



</style>