<script setup>

import TableComponent from "@/components/profile/TableComponent.vue";
</script>

<template>
  <div id="SongListContainer">
    <div id="BtnContainer">
      <div id="playBtn">
        <svg viewBox="0 0 24 24" id="playIcon">
          <path d="M8 5v14l11-7z" />
        </svg>
      </div>
    </div>

    <div id="SongContainer">
      <table-component></table-component>
    </div>
  </div>
</template>

<style scoped>
#SongListContainer {
  width: 675px;
  height: 700px;

}

#BtnContainer {
  width: 675px;
  height: 100px;

}

#SongContainer {
  width: 675px;
  height: 600px;

}
#playBtn{
  width: 60px;
  height: 60px;
  border-radius: 100%;
  background-color: #ea62a5;
  margin-left: 30px;
  margin-top: 20px;
}
#playBtn:hover{
  background-color: #f682bb;
  box-shadow: 0 0 3px 3px rgba(215, 144, 180, 0.65);
  transition: all 0.5s;
  cursor: pointer;
}
#playIcon{
  width: 38px;
  height: 38px;
  margin-left: 12px;
  margin-top: 12px;
  fill: #2a2a2a;
}
</style>