<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-row>
        <el-col :span="6">
          <el-form-item label="项目名称：" :label-width="formLabelWidth" prop="title">
            <el-input v-model="ruleForm.title" placeholder="请输入项目名称" clearable style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="预收金额：" :label-width="formLabelWidth" prop="budget">
            <el-input v-model="ruleForm.budget" placeholder="请输入预收金额" clearable style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="缴费人数：" :label-width="formLabelWidth" prop="classsize">
            <el-input v-model="ruleForm.classsize" placeholder="请输入班级人数" disabled style="width: 220px;"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="备注描述:" :label-width="formLabelWidth" prop="descr">
            <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" placeholder="请输入内容" clearable
              v-model="ruleForm.descr" show-word-limit maxlength="200" style="width: 220px;">
            </el-input>
          </el-form-item>
        </el-col>       
      </el-row>
      <div>
        <el-form-item id="buttonItem">
          <el-button type="primary" @click="clickAdd">提交</el-button>
          <el-button @click="resetForm('ruleForm')" type="warning">重置</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
</template>

<script>
import { IncomeAdd } from "@/utils/port";
export default {
  data() {

    return {
      roleInfoList: '',
      gradeInfoList: '',
      formLabelWidth: "120px",
      ruleForm: {
        title: "",
        budget: "",
        classsize: "30",
        descr: "",
      },
      rules: {

        //表单验证规则
        //项目名称
        title: [
          { required: true, message: "请填写此次班级收取班费的目的", trigger: "blur" },
          {
            pattern: /^[\u4e00-\u9fa5]{0,}$/,
            min: 2,
            max: 10,
            message: "格式错误请检查，2到10中文个字符",
            trigger: "blur",
          },
        ],
        //预收班费
        budget: [
          {
            required: true,
            pattern: /^(([1-9]\d{0,4})|50000)(\.\d{1,2})?$/,
            message: "请输入合法的金额，最大为50000，输入整数",
            trigger: "change"
          }
        ],

      },

    };
  },

  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    open1() {
      this.$notify({
        title: '用户添加',
        message: '该生学号已自动生成于数据库',
        type: 'success'
      })
    },

    refresh() {
      location.reload();
    },

    // 点击提交按钮触发的方法
    async clickAdd() {
      this.$refs.ruleForm.validate(async (val) => {
        if (val) {
            // 调用接口方法，传入表单数据
            IncomeAdd(this.ruleForm).then(res => {
              // 处理响应结果
              console.log(res)
              if (res) {
                this.$message({
                  message: "新增班费收入成功",
                  type: "success",
                });
                this.$refs.ruleForm.resetFields()
                this.open1()
                this.refresh()
              } else {
                this.$message({
                  message: "新增班费收入失败",
                  type: "error",
                });
              }
            })         
        }
      })
    },
  },

};
</script>

<style>

</style>