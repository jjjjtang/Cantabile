<template>
  <div>
    <el-page-header
        style="padding-top: 5px; background: linear-gradient(to bottom, #cea7ec, #ea99c1);"
        :icon="ArrowLeft"
        :title="'返回'"
        @click="goBack"
    >
      <template #content>
        <div id="CoverContainer">
          <div id="AvatarContainer">
            <el-avatar :src="user.src" alt="" :size="50" style="margin-top: 3px"></el-avatar>
          </div>
          <span class="text-large font-600 mr-3">{{ user.username }}</span>
        </div>
      </template>
    </el-page-header>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import router from "@/router/index.js";
import { ArrowLeft } from "@element-plus/icons-vue";
import axios from "axios";

// 读取cookie函数
function getCookie(name) {
  const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
  if (match) return match[2];
  return null;
}

const user = ref({
  username: "Jiangie", // 默认名字
  src: "public/avatar/avatar.png", // 默认头像路径
});

const goBack = () => {
  router.go(-1);
};

onMounted(async () => {
  try {
    const jwt = getCookie("jwt1");
    if (!jwt) {
      console.warn("没有找到 jwt1 cookie");
      return;
    }
    // jwt中存的是userId还是token？假设存的是userId
    // 如果是token，这里需要解析token或者有其他接口获取userId
    // 这里先假设jwt就是userId
    const userId = jwt;

    const res = await axios.get(`http://localhost:8081/api/getUserById?userId=${userId}`);
    const data = res.data;
    // 根据后端返回数据动态赋值
    user.value.username = data.username || user.value.username;
    user.value.src = data.src || user.value.src;
  } catch (error) {
    console.error("获取用户信息失败:", error);
  }
});
</script>

<style scoped>
.text-large {
  font-size: 20px;
}
.font-600 {
  font-weight: 650;
}
#AvatarContainer {
  width: 50px;
  height: 55px;
  border-radius: 50%;
  margin-right: 10px;
}
#CoverContainer {
  display: flex;
  align-items: center;
  background-color: transparent; /* 保持透明，与 header 渐变融合 */
}
</style>
