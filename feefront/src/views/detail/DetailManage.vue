<template>
  <div class="home">
    <div style="max-width: 100px;left: 0;">
      <el-button type="info" @click="flip()">返回</el-button>
    </div>
    <div class="elTable">
      <el-tabs type="border-card">
        <el-tab-pane label="收入详细管理" mame="tab1">
          <el-table :data="pageData" style="width: 100%; margin: 0 auto; border-radius: 2px; border-bottom: 0px;"
            :header-cell-style="{ 'text-align': 'center' }" :cell-style="{ 'text-align': 'center' }" max-height="550px"
            border>
            <el-table-column prop="id" label="编号" width="80">
            </el-table-column>
            <el-table-column prop="incomeid" label="缴费编号" width="80">
            </el-table-column>
            <el-table-column prop="entry" label="名称" width="120">
            </el-table-column>
            <el-table-column label="已缴费名单">
              <el-table-column prop="cashpay" label="现金缴费" width="200">
              </el-table-column>
              <el-table-column prop="wechat" label="微信缴费" width="200">
              </el-table-column>
              <el-table-column prop="alipay" label="支付宝缴费" width="200">
              </el-table-column>
            </el-table-column>
            <el-table-column prop="unpaid" label="未缴费名单" width="410">
            </el-table-column>

            <!-- 搜索、编辑、删除 -->
            <el-table-column fixed="right" width="120">
              <template slot-scope="scope">
                <el-button size="mini" type="warning" @click="HandleEdit(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>


      <div>
        <el-dialog title="编辑缴费详细" :visible.sync="postedit" width="60%">
          <el-form :model="formedit" :rules="rules" ref="formedit" style="width: 100%;">
            <el-row>
              <el-col :span="8">
                <el-form-item label="编号：" :label-width="formLabelWidth" prop="id">
                  <el-input v-model="formedit.id" clearable disabled style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="缴费编号：" :label-width="formLabelWidth" prop="incomeid">
                  <el-input v-model="formedit.incomeid" disabled placeholder="请输入修改的名称" clearable
                    style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="名称:" :label-width="formLabelWidth" prop="entry">
                  <el-input v-model="formedit.entry" disabled clearable style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="现金缴费:" :label-width="formLabelWidth" prop="cashpay">
                  <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" placeholder="请输入内容" clearable
                    v-model="formedit.cashpay" show-word-limit>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="微信缴费:" :label-width="formLabelWidth" prop="wechat">
                  <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" placeholder="请输入内容" clearable
                    v-model="formedit.wechat" show-word-limit>
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="支付宝缴费:" :label-width="formLabelWidth" prop="alipay">
                  <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" placeholder="请输入内容" clearable
                    v-model="formedit.alipay" show-word-limit>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="未缴费名单:" :label-width="formLabelWidth" prop="unpaid">
                  <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" placeholder="请输入内容" clearable
                    v-model="formedit.unpaid" show-word-limit>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>

          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="postedit = false">取 消</el-button>
            <el-button native-type="submit" @click="edit()">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
  </div>
</template>


<script>
import { Message } from 'element-ui';
import { DetailsByIncomeId } from '@/utils/port';
import { DetailsEdit } from "@/utils/port";
export default {
  data() {
    return {
      activeTab: 'tab1',
      //编辑弹窗
      //宽度设置
      formLabelWidth: "100px",

      //编辑弹窗
      postedit: false,

      pageData: [],

      //编辑表对应数据
      formedit: {
        cashpay: "",
        wechat: "",
        alipay: "",
        unpaid: ""
      },
      rules: {

      }
    }



  },
  created() {
    this.IncomeId = this.$route.query.IncomeId;
    setTimeout(() => {
      this.fetchData();
    }, 500);
  },
  mounted() {
  },
  methods: {

    flip() {
      this.$router.push({ path: "/Home/IncomeManage" })
    },

    async fetchData() {
      let incomeid = this.IncomeId
      console.log(incomeid);
      const data = await DetailsByIncomeId({ incomeid })
      this.pageData = data;
    },



    //编辑摁钮
    HandleEdit(data) {
      this.postedit = true;
      for (const key in data) {
        this.formedit[key] = data[key];
      }
    },
    //编辑班费
    async edit() {
      this.$refs.formedit.validate((val) => {
        console.log(val);
        console.log(this.$refs.formedit);
        if (val) {
          DetailsEdit(this.formedit).then(res => {
            console.log(res)
            if (res) {
              this.$message({
                message: "编辑成功",
                type: "success",
              });
            }
            else {
              this.$message({
                message: "编辑失败",
                type: "error",
              });
            }
            (this.postedit = false), this.fetchData();
          });
        }
      });
    },
  },
}




</script>

<style lang="less" scoped>
.home {
  border-radius: 5px;
  background-color: lightgray;
}

.elTable {
  width: 99%;
  margin: 0 auto;
}

.line {
  top: 0px;
}

.search_input {
  font-weight: bold;
  border-width: 2px;
  border-style: solid;
  border-color: antiquewhite;
  background-size: auto;
  left: 0;
}



.search_button {
  background-color: #409EFF;
}


// 时间选择器
.date_picker {
  // width: 50px !important;
  font-weight: bold;
  border-width: 2px;
  border-style: solid;
  border-color: antiquewhite;
  background-size: auto;
  left: 0;
}

//分页样式
.pagination {
  text-align: center;
  padding-top: 7px;
}



el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
}
</style>