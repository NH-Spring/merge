<template>
    <div class="login_container">
        <div class="login_box">

            <el-form ref="loginFromRef" :model="loginForm" :rules="loginFormRules" label-width="65px" class="login_form">
              <el-form-item label="账号" prop="username">
                <el-input v-model="loginForm.username" prefix-icon="iconfont icon-user"></el-input>
              </el-form-item>


              <el-form-item class="btns">
                <el-button type="primary" @click="login">提交</el-button>
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
                username: ''
            },

            loginFormRules: {
                username: [
                  { required: true, message: '请输入找回账号', trigger: 'blur' },
                  { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        resetLoginForm() {
            this.$refs.loginFromRef.resetFields();
        },
        login() {
            this.$refs.loginFromRef.validate(async valid => {
              if (!valid) return;
              const result = await this.$http.post('/findPas',this.loginForm);
              console.log(result);
              if (result.data.status !== '200') return this.$message.error('邮件发送失败！');
              this.$message.success('邮件发送成功！');
              this.$router.push("/code");
            })
        }
    }
}
</script>
<style lang="less" scoped>
.login_container {
    background-color:darkcyan;
    height: 100%;
}

.login_box  {
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