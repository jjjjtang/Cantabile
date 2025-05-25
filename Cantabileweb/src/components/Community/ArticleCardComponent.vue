<script setup>
defineProps({
  post: {
    type: Object,
    required: true
  }
})
function formatTime(timeStr) {
  const date = new Date(timeStr);
  return `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()} ${date.getHours()}:${date.getMinutes()}`;
}
</script>

<template>
  <div class="card">
    <div class="header">
      <div class="avatar">
        <img :src="post.src" alt="avatar" />
      </div>
      <div class="user-info">
        <div class="name">{{post.username}}</div>
        <div class="time">{{ formatTime(post.createTime) }}</div>
      </div>
      <div class="like">
        💗 {{ post.like }}
      </div>
    </div>

    <div class="content">
      {{ post.content }}
    </div>
  </div>
</template>

<style scoped>
/* 样式保持不变 */
.card {
  width: 90%;
  max-width: 1100px;
  margin-left: 60px;
  background-color: #ffe6f1;
  border-radius: 16px;
  box-shadow: 1px 1px 3px 8px rgba(255, 192, 203, 0.5);
  padding: 20px;
  transition: all 0.3s ease;
  font-family: "Comic Sans MS", cursive, sans-serif;
  margin-bottom: 35px;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 20px rgba(255, 105, 180, 0.4);
}

.header {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.avatar {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  overflow: hidden;
  border: 2px solid #ffaad4;
  box-shadow: 0 0 5px #ffc6da;
}

.avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.user-info {
  margin-left: 15px;
  flex-grow: 1;
}

.name {
  font-size: 18px;
  font-weight: bold;
  color: #ff4d88;
}

.time {
  font-size: 12px;
  color: #b06f82;
  margin-top: 4px;
}

.like {
  font-size: 16px;
  color: rgba(255, 105, 180, 0.85);
  font-weight: bold;
  background-color: #fff0f5;
  padding: 5px 10px;
  border-radius: 20px;
  box-shadow: 0 0 4px #ffc0cb;
}

.content {
  font-size: 16px;
  color: #663366;
  line-height: 1.6;
  background-color: #fffafc;
  padding: 15px;
  border-radius: 12px;
  border: 1px dashed #ffa3c4;
}
</style>
