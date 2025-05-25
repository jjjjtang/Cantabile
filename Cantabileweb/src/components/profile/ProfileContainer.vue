<script setup>
import { ref, onMounted } from "vue";
import axios from "axios";

// 读取cookie函数
function getCookie(name) {
  const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
  if (match) return match[2];
  return null;
}

const user = ref({
  username: "默认昵称",
  email: "默认邮箱@example.com",
});

onMounted(async () => {
  try {
    const jwt = getCookie("jwt1");
    if (!jwt) {
      console.warn("没有找到 jwt1 cookie");
      return;
    }
    const userId = jwt;
    const res = await axios.get(`http://localhost:8081/api/getUserById?userId=${userId}`);
    const data = res.data;
    user.value.username = data.username || user.value.username;
    user.value.email = data.email || user.value.email;
  } catch (error) {
    console.error("获取用户信息失败:", error);
  }
});

function onClickUsername() {
  alert(`点击了昵称: ${user.value.username}`);
}

function onClickEmail() {
  alert(`点击了邮箱: ${user.value.email}`);
}
</script>

<template>
  <div id="ProfileContainer">
    <button class="ContentContainer btn" @click="onClickUsername">
      <span class="label-text">昵称：</span><span>{{ user.username }}</span>
    </button>

    <button class="ContentContainer btn" @click="onClickEmail">
      <span class="label-text">邮箱：</span><span>{{ user.email }}</span>
    </button>

    <div class="ContentContainer">
      我喜欢的(99+)
    </div>

    <div class="ContentContainer">
      创建歌单(4)
    </div>

    <div class="ContentContainer">
      常听歌手(34)
    </div>
  </div>
</template>

<style scoped>
#ProfileContainer {
  width: 550px;
  height: 700px;
  background: linear-gradient(to bottom, #ea99c1, #e5bfe7, #f2f2f2);
}

.ContentContainer {
  width: 80%;
  height: 60px;
  background-color: rgba(246, 131, 187, 0.72);
  margin-top: 30px;
  margin-left: 30px;
  border-radius: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #2f2f2f;
  font-size: 20px;
  transition: background-color 0.3s ease;
}

.ContentContainer:hover {
  background-color: rgba(231, 100, 163, 0.9);
}

button.ContentContainer.btn {
  border: none;
  cursor: pointer;
  outline: none;
  background-color: rgba(246, 131, 187, 0.72);
  text-align: center;
  padding: 0; /* 避免按钮默认内边距影响布局 */
}

button.ContentContainer.btn:hover {
  background-color: rgba(231, 100, 163, 0.9);
}

.label-text {
  font-size: 14px;
  margin-right: 6px;
  color: #2f2f2f;
}
</style>
