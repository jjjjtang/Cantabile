<script setup>
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import router from "@/router/index.js";
import axios from "axios";

// 读取cookie函数
function getCookie(name) {
  const match = document.cookie.match(new RegExp('(^| )' + name + '=([^;]+)'));
  if (match) return match[2];
  return null;
}

const dialogVisible = ref(false);
const articleContent = ref("");

const user = ref({
  username: "默认用户名",
  src: "/avatar/avatar.png",
});

const Back = () => {
  router.go(-1);
};

const handlePublish = () => {
  if (!articleContent.value) {
    ElMessage.warning("请输入文章内容");
    return;
  }
  ElMessage.success("文章发布成功！");
  dialogVisible.value = false;
  articleContent.value = "";
};

onMounted(async () => {
  try {
    const jwt = getCookie("jwt1");
    if (!jwt) {
      console.warn("未找到 jwt1 cookie");
      return;
    }
    const userId = jwt;
    const res = await axios.get(
        `http://localhost:8081/api/getUserById?userId=${userId}`
    );
    const data = res.data;
    if (data) {
      user.value.username = data.username || user.value.username;
      user.value.src = data.src || user.value.src;
    }
  } catch (error) {
    console.error("获取用户信息失败:", error);
  }
});
</script>

<template>
  <div class="header-container">
    <!-- 返回按钮 -->
    <div class="back-btn" @click="Back">
      <svg
          class="icon"
          viewBox="0 0 1024 1024"
          width="20"
          height="20"
          xmlns="http://www.w3.org/2000/svg"
      >
        <path
            d="M723.413333 895.701333c-9.6 0-19.328-3.2-27.434666-9.6l-423.765334-340.906666a43.733333 43.733333 0 0 1 0-68.096l421.973334-339.114667a43.690667 43.690667 0 0 1 54.698666 68.096L369.28 511.146667l381.525333 306.901333a43.690667 43.690667 0 0 1-27.392 77.696z"
            fill="#ff80b3"
        ></path>
      </svg>
      <span>返回</span>
    </div>

    <!-- 标题 -->
    <div class="title">全部文章</div>

    <!-- 发布按钮 + 头像 + 用户名 -->
    <div class="right-area">
      <div class="add-btn" @click="dialogVisible = true">+ 发布</div>
      <div class="avatar">
        <img :src="user.src" alt="avatar" />
      </div>
      <div class="username">{{ user.username }}</div>
    </div>
  </div>

  <!-- 发布弹窗 -->
  <el-dialog
      v-model="dialogVisible"
      title="发布新文章"
      width="500px"
      align-center
      :before-close="() => (dialogVisible = false)"
  >
    <div class="dialog-body">
      <el-input
          v-model="articleContent"
          type="textarea"
          :rows="10"
          placeholder="请输入文章内容"
          class="input-content"
      />
    </div>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handlePublish">发布</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.header-container {
  width: 100%;
  padding: 12px 20px;
  background-color: #fff0f5;
  border-bottom: 2px dashed #ffb6c1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-radius: 0 0 16px 16px;
  box-shadow: 0 3px 8px rgba(255, 182, 193, 0.25);
  font-family: "Comic Sans MS", cursive, sans-serif;
}

.back-btn {
  display: flex;
  align-items: center;
  color: #ff4d88;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  font-size: 14px;
}

.back-btn:hover {
  transform: translateX(-2px);
  color: #e60073;
}

.back-btn svg {
  margin-right: 5px;
}

.title {
  font-size: 20px;
  font-weight: bold;
  color: #cc2b5e;
  text-align: center;
  flex-grow: 1;
  margin-left: 10px;
}

.right-area {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-right: 15px;
}

.add-btn {
  background-color: #ff85a2;
  color: white;
  padding: 6px 12px;
  border-radius: 16px;
  box-shadow: 0 2px 4px rgba(255, 105, 135, 0.3);
  font-size: 13px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-right: 6px;
}

.add-btn:hover {
  background-color: #ff5e8e;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  overflow: hidden;
  border: 2px solid #ffa3c4;
  box-shadow: 0 0 4px #ffc6da;
}

.avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.username {
  font-size: 16px;
  font-weight: 600;
  color: #cc2b5e;
  margin-left: 8px;
  font-family: "Comic Sans MS", cursive, sans-serif;
}

:deep(.el-dialog) {
  border-radius: 16px;
  font-family: "Comic Sans MS", cursive, sans-serif;
  background-color: #fff0f7;
}

.dialog-body {
  display: flex;
  flex-direction: column;
  gap: 16px;
  padding: 10px 0;
}

.input-content {
  font-family: inherit;
}

.dialog-footer {
  text-align: right;
}
</style>
