<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <el-row>
        <el-col :span="6">
          <el-form-item label="名称：" :label-width="formLabelWidth" prop="title">
            <el-input v-model="ruleForm.title" placeholder="请输入名称" clearable style="width: 220px;"></el-input>
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
          <el-form-item label="备注描述：" :label-width="formLabelWidth" prop="descr">
            <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" placeholder="请输入内容" clearable
              v-model="ruleForm.descr" show-word-limit maxlength="200" style="width: 220px;">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="缴费状态：" :label-width="formLabelWidth" prop="status">
            <el-select v-model="ruleForm.status" disabled style="width: 220px;">
              <el-option label="待处理" :value="0"></el-option>
              <el-option label="进行中" :value="1"></el-option>
              <el-option label="已完成" :value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="缴费类型：" :label-width="formLabelWidth" prop="state">
            <el-select v-model="ruleForm.state"  style="width: 220px;">
              <el-option label="预支收入" :value="0"></el-option>
              <el-option label="预存收入" :value="1"></el-option>
            </el-select>
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
// import { SumReceipt } from "@/utils/port";
// import { SumPayout } from "@/utils/port";
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
        status: 0,
        state:0,
        descr: "",
      },
      // a: 0,
      // b: 0,
      rules: {

        //表单验证规则
        //名称
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
    //重置内容
    resetForm(formName) {
      this.$refs[formName].resetFields();
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
                message: "新增班费收入成功，您可以继续添加",
                type: "success",
              });
              this.$refs.ruleForm.resetFields()
              // 新增成功后切换到支出管理标签页
              this.handleTabClick({ label: '支出管理' });
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
    // 切换标签页的方法
    handleTabClick(tab) {
      if (tab.label === '支出管理') {
        this.activeTab = 'tab1';
      }
    },
  },
  // created() {
  //   // 获取班费余额
  //   SumReceipt()
  //     .then((resultA) => {
  //       // 将总收入赋值给a
  //       this.a = resultA;
  //       return SumPayout();
  //     })
  //     .then((resultB) => {
  //       // 将总支出赋值给 b
  //       this.b = resultB;
  //       // 计算余额并赋值给 surplus 
  //       this.ruleForm.surplus = this.a - this.b;
  //     })
  // }

};
</script>

<style></style>