<template>
  <div class="home">
    <div class="elTable">
      <el-tabs type="border-card" @tab-click="handleTabClick">
        <el-tab-pane label="收入管理" mame="tab1">
          <el-table :data="pageData" style="width: 100%; margin: 0 auto; border-radius: 2px; border-bottom: 0px;"
            :header-cell-style="{ 'text-align': 'center' }" :cell-style="{ 'text-align': 'center' }" max-height="550px"
            border>
            <el-table-column fixed type="index" width="50" label="序号">
            </el-table-column>
            <el-table-column prop="id" label="编号" width="80">
            </el-table-column>
            <el-table-column prop="title" label="项目名称" width="150">
            </el-table-column>
            <el-table-column prop="origindate" label="创建时间" width="180">
            </el-table-column>
            <el-table-column prop="budget" label="预收金额" width="150">
            </el-table-column>
            <el-table-column prop="receipt" label="已收金额" width="120">
            </el-table-column>
            <el-table-column prop="classsize" label="班级人数" width="120">
            </el-table-column>
            <el-table-column prop="paysize" label="缴费人数" width="120">
            </el-table-column>
            <!-- 备注描述列 -->
            <el-table-column label="备注描述" width="120" type="expand">
              <template slot-scope="scope">
                <!-- 备注描述的显示内容 -->
                <el-form label-position="left" inline class="demo-table-expand">
                  <el-form-item label="备注描述内容：" style="margin-left: 30px;">
                    <span>{{ scope.row.descr }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>

            <!-- 搜索、编辑、删除 -->
            <el-table-column fixed="right" width="195">
              <template slot="header" slot-scope="scope">
                <el-input v-model="title" size="mini" class="search_input" placeholder="输入项目名称搜索" shadow="always">
                  <template slot="append">
                    <el-button type="primary" size="mini" class="search_button" @click="search1" plain>搜索</el-button>
                  </template>
                </el-input>
              </template>
              <template slot-scope="scope">
                <el-button size="mini" type="warning" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <!-- 分页 -->
          <el-row :gutter="20">
            <el-col :span="4">
              <!-- <div class="grid-content bg-purple" style="padding-left:5px;padding-top: 8px;"> <el-button type="danger"
                  size="mini">批量删除</el-button></div> -->
            </el-col>
            <el-col :span="16">
              <div class="grid-content bg-purple">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                  :current-page="currentPage" :page-sizes="[10, 20, 50, 100]" :page-size="pageSize"
                  layout="total, sizes, prev, pager, next, jumper" :total="pageTotal" class="pagination">
                </el-pagination>
              </div>
            </el-col>
            <el-col :span="4">
              <div class="grid-content bg-purple">
              </div>
            </el-col>
          </el-row>

        </el-tab-pane>


        <!-- 新增功能 -->
        <el-tab-pane label="新增操作" name="tab2">
          <IncomeAdd></IncomeAdd>
        </el-tab-pane>
      </el-tabs>
      <div>
        <el-dialog title="编辑班费" :visible.sync="postedit" width="45%">
          <el-form :model="formedit" :rules="rules" ref="formedit" style="width: 100%;">
            <el-row>
              <el-col :span="12">
                <el-form-item label="编号：" :label-width="formLabelWidth" prop="id">
                  <el-input v-model="formedit.id" clearable disabled style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="项目名称" :label-width="formLabelWidth" prop="title">
                  <el-input v-model="formedit.title" placeholder="请输入修改的项目名称" clearable style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="创建时间:" :label-width="formLabelWidth" prop="origindate">
                  <el-input v-model="formedit.origindate" disabled clearable style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="备注描述:" :label-width="formLabelWidth" prop="descr">
                  <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4 }" placeholder="请输入内容" clearable
                    v-model="formedit.descr" show-word-limit maxlength="200">
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="预收金额:" :label-width="formLabelWidth" prop="budget">
                  <el-input v-model="formedit.budget" clearable style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="已收金额:" :label-width="formLabelWidth" prop="receipt">
                  <el-input v-model="formedit.receipt" clearable style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="班级人数:" :label-width="formLabelWidth" prop="classsize">
                  <el-input v-model="formedit.classsize" disabled clearable style="width: 220px;"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="缴费人数:" :label-width="formLabelWidth" prop="paysize">
                  <el-input v-model="formedit.paysize" clearable style="width: 220px;"></el-input>
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
import { IncomeAll } from '@/utils/port';
import { searchTitle } from "@/utils/port";
import { IncomeEdit } from "@/utils/port";
import { IncomeDel } from "@/utils/port";
import IncomeAdd from '@/views/income/IncomeAdd.vue';
export default {
  components: { IncomeAdd },
  data() {
    return {
      activeTab: 'tab1',
      //编辑弹窗
      //宽度设置
      formLabelWidth: "100px",

      //编辑弹窗
      postedit: false,

      //搜索框
      title: "",


      //编辑表对应数据
      formedit: {
        title: "",
        budget: "",
        receipt: "",
        paysize: "",
        descr: ""
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
        //已缴班费
        receipt: [
          {
            required: true,
            pattern: /^(([1-9]\d{0,4})|50000)(\.\d{1,2})?$/,
            message: "请输入合法的金额，最大为50000，输入整数",
            trigger: "change"
          },
          {
            validator: (rule, value, callback) => {
              const budgetValue = this.formedit.budget;
              if (parseFloat(value) > parseFloat(budgetValue)) {
                callback(new Error("已缴班费不能大于预收班费"));
              } else {
                callback();
              }
            },

          }
        ],
        //缴费人数
        paysize: [{
          required: true,
          pattern: /^(0|[1-9]|[12]\d|30)$/,
          message: "请输入大于等于0且小于等于30的整数的人数",
          trigger: "change"
        }],


      },


      //分页 
      // 总的数据
      tableData: [],
      // 分页的数据
      pageData: [],
      // 总数据量 默认为 0
      pageTotal: 0,
      // 当前页 默认是第一页
      currentPage: 1,
      // 每页大小 默认每页10条数据
      pageSize: 10,

    };

  },
  created() {
    //分页
    this.fetchData();
  },
  mounted() {
  },
  methods: {

    // 改变每页大小的回调
    handleSizeChange(val) {
      this.pageSize = val;
      this.pageData = this.queryByPage();
    },
    // 改变当前页的回调
    handleCurrentChange(val) {
      this.currentPage = val;
      this.pageData = this.queryByPage();
    },
    // 获取全部数据
    async fetchData() {
      // 请求后端数据
      const data = await IncomeAll();
      this.tableData = data;
      this.pageTotal = data.length;
      this.pageData = this.queryByPage();
    },
    // 实现分页的方法
    queryByPage() {
      // 起始位置 = (当前页 - 1) x 每页的大小
      const start = (this.currentPage - 1) * this.pageSize;
      // 结束位置 = 当前页 x 每页的大小
      const end = this.currentPage * this.pageSize;
      return this.tableData.slice(start, end);
    },


    //信息提示
    handleTabClick(tab) {
      Message({
        message: `您切换到了${tab.label}`,
        type: 'success'
      });

      this.$notify.warning({
        title: '注意，新增操作',
        message: '编号和创建时间数据库自动生成，无需手动添加',
        showClose: false
      });

    },

    //模糊查询
    search1() {
      let params = { title: this.title };
      searchTitle(params).then(res => {
        this.pageData = res;
        this.pageTotal = res.length;
      });
    },


    //编辑摁钮
    handleEdit(data) {
      this.postedit = true;
      for (const key in data) {
        this.formedit[key] = data[key];
      }
    },
    //编辑班费
    async edit() {
      this.$refs.formedit.validate((val) => {
        if (val) {
          IncomeEdit(this.formedit).then(res => {
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

    refresh() {
      location.reload();//刷新页面
    },

    //删除班费
    //删除按钮
    async handleDelete(row) {
      try {
        // 弹框（默认取消在左确定在右)
        await this.$confirm('是否确认删除', '提示', {
          confirmButtonText: '确定',
          concelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          IncomeDel(row).then(res => {
            if (!res) {
              this.$message({
                message: "删除失败",
                type: "error",
              });
            } else {
              this.refresh();
              this.$message({
                message: "删除成功",
                type: "success",
              });
              this.refresh();//刷新页面
            }
          })
        })
      } catch (err) { }
    }

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


/deep/.el-tabs {
  .el-tabs__item {
    font-size: 18px;
  }
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




//解决table设置fixed缩小显示不全问题

/deep/.el-table__header-wrapper,
.el-table__body-wrapper,
.el-table__footer-wrapper {
  overflow: visible;
}

/deep/.el-table__body-wrapper {
  overflow-x: visible !important;
}

/* 这个是为了解决前面样式覆盖之后伪类带出来的竖线 */
/deep/.el-table::after {
  position: relative;
}

/deep/.el-table__fixed-right {
  height: 100% !important;
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