<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/login1.png" alt />
      </div>

      <el-form
        ref="loginFromRef"
        :model="loginForm"
        :rules="loginFormRules"
        label-width="55px"
        class="login_form"
      >
        <el-form-item label="账 户" prop="username">
          <el-input v-model="loginForm.username" prefix-icon="iconfont icon-user"></el-input>
        </el-form-item>

        <el-form-item label="密 码" prop="password">
          <el-input
            v-model="loginForm.password"
            prefix-icon="iconfont icon-3702mima"
            type="password"
          ></el-input>
        </el-form-item>

        <el-form-item class="btns">
          <el-button type="danger" @click="findpas">找回密码</el-button>
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="infro" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: {
        username: "",
        password: ""
      },

      loginFormRules: {
        username: [
          { required: true, message: "请输入登录账号", trigger: "blur" },
          { min: 6, max: 12, message: "长度在 6 到 12 个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入登录密码", trigger: "blur" },
          { min: 6, max: 16, message: "长度在 6 到 16 个字符", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    resetLoginForm() {
      this.$refs.loginFromRef.resetFields();
    },
    login() {
      this.$refs.loginFromRef.validate(async valid => {
        if (!valid) return;
        const result = await this.$http.post("/login", this.loginForm);
        console.log(result);
        if (result.data.status !== "200")
          return this.$message.error("登录失败！");
        this.$message.success("登录成功！");
        window.sessionStorage.setItem("token", result.data.token);
        this.$router.push("/home");
      });
    },
    findpas() {
        this.$router.push("/findPas");
    }
  }
};
</script>

<style lang="less" scoped>
.login_container {
  background-color: darkcyan;
  height: 100%;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: white;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: white;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: white;
    }
  }
}

.btns {
  display: flex;
  justify-content: flex-end;
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}
</style>