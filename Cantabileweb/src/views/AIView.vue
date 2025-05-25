<script setup>
import {ref} from 'vue'
import axios from 'axios'

// 聊天记录数组
const messages = ref([])
// 用户输入框内容
const inputText = ref('')

// 发送消息
const sendMessage = async () => {
  if (inputText.value.trim() === '') return

  // 先把用户自己的消息加进去
  messages.value.push({text: inputText.value, from: 'user'})
  const userInput = inputText.value // 保存一份用户输入
  inputText.value = '' // 清空输入框s

  // 显示 "AI正在思考..." 临时消息
  messages.value.push({text: '小猫梨梨思考中...', from: 'ai-loading'})

  try {
    const response = await axios({
      method: 'post',
      url: 'https://api.chatanywhere.tech/v1/chat/completions',
      headers: {
        'Authorization': 'Bearer sk-iEtCCppj0GXYG10Ih77GDthiFhGrCiqay7u5hjleRHlYNLbV', // <-- 注意替换成你自己的key！
        'Content-Type': 'application/json'
      },
      data: {
        model: 'gpt-4o',
        messages: [
          {role: 'system', content: '你是小猫梨梨，你要为用户提供音乐相关的建议。每次回答问题之前，说“喵喵喵~，亲爱的主人，小猫梨梨很高兴为您服务，梨梨认为”，每次结束，说“主人有什么需要尽管呼叫小梨”。你在对话中应当使用萌萌的词汇，表现对主人的喜爱，表现天真烂漫的性格。每句话带有一个喵~'},
          {role: 'user', content: userInput}
        ]
      }
    })

    // 删除"思考中"这条临时消息
    messages.value.pop()

    // 把AI回复显示出来
    const aiReply = response.data.choices[0].message.content
    messages.value.push({text: aiReply, from: 'ai'})
  } catch (error) {
    console.error(error)
    messages.value.pop()
    messages.value.push({text: '出错了，请稍后再试！', from: 'ai'})
  }
}
</script>

<template>
  <div id="Container">
    <div id="container">
      <div id="chatbox">
        <div v-for="(msg, index) in messages" :key="index" :class="['message', msg.from]">
          {{ msg.text }}
        </div>
      </div>
    </div>

    <div id="inputer">
      <div id="TextContainer">
        <input
            v-model="inputText"
            type="text"
            placeholder="请输入内容..."
            id="textInput"
            @keyup.enter="sendMessage"
        />
      </div>
      <div id="ButtonContainer">
        <button id="sendButton" @click="sendMessage">发送</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
#container {
  width: 650px;
  height: 500px;
  margin-left: 40px;
  border: 1px solid #ccc;
  border-radius: 10px;
  overflow-y: auto;
  background-color: #f9f9f9;
  padding: 15px;
}

#chatbox {
  display: flex;
  flex-direction: column;
}

.message {
  padding: 10px;
  margin-bottom: 10px;
  max-width: 70%;
  border-radius: 8px;
  word-wrap: break-word;
  font-size: 16px;
}

.message.user {
  background-color: #a7bae3;
  align-self: flex-end;
}

.message.ai {
  background-color: #e9caea;
  align-self: flex-start;
}

.message.ai-loading {
  background-color: #eae2e2;
  align-self: center;
  font-style: italic;
  color: gray;
}

#inputer {
  width: 650px;
  height: 80px;
  margin-left: 40px;
  margin-top: 10px;
  display: flex;
  align-items: center;
}

#TextContainer {
  width: 400px;
  margin-left: 20px;
}

#ButtonContainer {
  width: 200px;
  margin-left: 20px;
}

#textInput {
  width: 100%;
  height: 40px;
  font-size: 16px;
  padding: 0 10px;
  border-radius: 8px;
  border: 1px solid #ccc;

}

#sendButton {
  width: 100%;
  height: 44px;
  background-color: #efb1f3;
  color: white;
  font-size: 16px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s;
  margin-left:30px ;
}

#sendButton:hover {
  background-color: #ec95f1;
}
</style>
