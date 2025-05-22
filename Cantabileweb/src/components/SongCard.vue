<script setup>

</script>

<template>
  <div class="card2">
    <div class="album-container">
      <img src="/Album/blacpinkAlbum.png" alt="Example Album 2" />
    </div>
    <div class="albumtext-container">
      SECOND ALBUM<br />- ARTIST 2
    </div>
  </div>
</template>

<style scoped>
.card2 {
  width: 240px;
  height: 260px;
  background-color: #f5eff2;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 16px;
  margin-left: 0px;
  box-sizing: border-box;
}
.card2:hover {
  background-color: #f8e0ee;
  transition: all 0.2s ease-in-out;
  cursor: pointer;
}
.album-container img {
  width: 200px;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
}
.albumtext-container {
  font-size: 12px;
  color: #010b21;
  font-weight: bold;
  text-align: center;
}
</style>