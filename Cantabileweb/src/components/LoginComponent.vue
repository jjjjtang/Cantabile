<script setup>
import { ref } from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";

const dialogVisible = ref(false); // 控制对话框显示的状态
const loginForm = ref({
  username: '',
  password: '',
  rememberMe: false // 是否记住密码
});
const formRef = ref(null); // 引用表单实例
function checkJwt1CookieExists() {
    console.log('checkJwt1CookieExists');
    const jwt1 = document.cookie.split(';').find(c => c.trim().startsWith('jwt1='));
    if (jwt1) {
        console.log('jwt1 cookie exists');
        ElMessage.error('无需登录，请先退出登录');
        return false;
    } else {
        console.log('jwt1 cookie does not exist');
        dialogVisible.value = true;
        return true;
    }
}
// 表单验证规则
const loginFormRules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { pattern: /^[a-zA-Z]{1,10}$/, message: '用户名只能包含英文字母，且长度为1-10字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { pattern: /^[a-zA-Z0-9]{1,20}$/, message: '密码只能包含英文字母和数字，且长度为1-20字符', trigger: 'blur' }
  ]
};

const submitLoginForm = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      console.log('登录信息正确，可以提交服务器:', loginForm.value);
      const loginInfo = {
        username: loginForm.value.username,
        password: loginForm.value.password
      };
      console.log(loginInfo);
      test(loginInfo);
      loginForm.value.username = '';
      loginForm.value.password = '';
      dialogVisible.value = false;
    } else {
      console.log('表单验证未通过');
      return false;
    }
  });
};

const handleCancel = () => {
  // 重置表单字段和清除验证结果
  formRef.value.resetFields();
  // 关闭对话框
  dialogVisible.value = false;
};

const handleClose = () => {
  // 关闭窗口时也重置表单字段和清除验证结果
  formRef.value.resetFields();
  dialogVisible.value = false;
};

const test = (loginInfo) => {
  axios.post('http://localhost:8081/api/login', loginInfo)
      .then(res => {
        console.log(res);
        const jwt = res.data;
        console.log(jwt);
        document.cookie = "jwt1=" + jwt;

        if (res.data === "-1") {
          ElMessage.error('登录失败，请检查用户名或密码是否正确(╥﹏╥)');
        }
        else {
          ElMessage({
            message: '登录成功٩(๑^o^๑)۶',
            type: 'success',
          })
          window.location.reload();
        }
      })
      .catch(err => {
        console.error(err);
      });
}
</script>

<template>
  <el-button class="button" color="#e2c8ca" :dark="true" plain link @click="dialogVisible = checkJwt1CookieExists()">
    登录
  </el-button>

  <div id="Container">
    <el-dialog
        v-model="dialogVisible"
        title="登录"
        width="30%"
        :before-close="handleClose"

    >
      <el-form ref="formRef" :model="loginForm" :rules="loginFormRules" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-checkbox v-model="loginForm.rememberMe">记住密码</el-checkbox>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="handleCancel">取消</el-button>
          <el-button type="primary" @click="submitLoginForm">登录</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<style scoped>
.button {
  padding: 2px;
}
.forget {
  margin-left: 20px;
}



</style>