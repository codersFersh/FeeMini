<template>
  <div class="login_container">
    <!-- 这里是用户登录 -->
    <div class="login_box">
      <!--头像区域-->
      <div class="avatar_box">
        <img src="@/assets/logo.png" alt="">
      </div>
      <!--表单-->
      <el-form ref="loginForms" :model="loginForm" :rules="loginFormRules" label-width="0px" class="login_form"
        @keyup.enter.native="loginn">
        <!-- <el-switch v-model="value1" class="switchStyle" active-text="管理" inactive-text="用户" active-color="#409eff"
          @click.native="open1" inactive-color="#DCDFE6">
        </el-switch> -->
        <!--用户名-->
        <el-form-item prop="account">
          <el-input v-model="loginForm.account" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!--密码-->
        <el-form-item prop="password">
          <el-input type="password" show-password v-model="loginForm.password" prefix-icon="el-icon-lock"
            placeholder="请输入密码"></el-input>
        </el-form-item>
        <!--按钮-->
        <el-form-item>
          <el-row>
            <el-col :span="15">
              <el-button type="text" style="font-size:10px;">修改密码</el-button>
            </el-col>
            <el-col :span="9">
              <el-button type="primary" @click="loginn" :disabled="!canSubmit">登录</el-button>
              <el-button type="info" @click="resetLoginForm">重置</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import { LoginUser } from '@/utils/port';
export default {

  data() {
    return { // 表单数据
      loginForm: {
        account: '',
        password: '',
      },
      value1: true,
      // 表单验证
      loginFormRules: {
        account: [
          { required: true, message: '请输入登录名称', trigger: 'blur' },
          { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ], // 验证用户名

        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          { min: 6, max: 18, message: '长度在6 到 18 个字符', trigger: 'blur' }
        ]// 验证密码
      },
    }
  },
  computed: {
    canSubmit() {
      const { account, password } = this.loginForm
      return Boolean(account && password)
    }
  },
  methods: {
    //重置按钮
    resetLoginForm() {
      this.$refs.loginForms.resetFields()
      this.$message({
        type: 'success',
        message: "内容已重置"
      });
    },
    loginn() {
      this.$refs.loginForms.validate((valid) => {
        if (valid) {
          const user = {
            account: this.loginForm.account,
            password: this.loginForm.password
          };
          LoginUser(user)
            .then((res) => {
              // 使用可选链操作符避免 undefined 错误
              const token = res?.token;

              if (token) {
                window.localStorage.setItem("token", token);
                this.$message({
                  message: "登录成功",
                  type: "success",
                });
                this.$router.push("/Home/StatManage");
              } else {
                // 处理没有 token 的情况
                this.$message({
                  message: "登录失败，未获取到有效的 token",
                  type: "error",
                });
              }
            })
            .catch((error) => {
              console.log(error);
              if (error.status === 401) {
                // 处理 401 错误，未授权
                this.$message({
                  message: "登录失败，请检查用户名和密码是否正确",
                  type: "error",
                });
              } else {
                // 处理其他错误
                this.$message({
                  message: "登录请求失败：" + error,
                  type: "error",
                });
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },


  }
}



</script>

<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
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
    background-color: #fff;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
      box-shadow: 0 0 10px #eee;
    }
  }
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;

  input.el-input__inner {
    border-radius: 30px;
    border-color: transparent;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  }

}
</style>