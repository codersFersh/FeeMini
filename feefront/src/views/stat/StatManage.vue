<template>
  <div>
    <div style="margin-left: 250px; margin-top: 50px;">
      <div>
        <h2 style="font-weight:100px;font-size: 50px; font-family:KaiTi">班费收支明细</h2>
      </div>
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value1" :title="title1">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: blue"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value2" :title="title2">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: red"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value3" :title="title3">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color:chartreuse"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>

      </el-row>
    </div>

    <div style="margin-left: 250px; margin-top: 50px;">
      <el-row :gutter="20">
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value4" :title="title4">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: blue"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="sizeCss">
            <el-statistic group-separator="," :precision="2" decimal-separator="." :value="value5" :title="title5">
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: red"></i>
              </template>
            </el-statistic>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-dialog title="未完成的缴费信息列表" :visible.sync="warning" width="80%">
        <el-table :data="pageData" style="width: 100%; margin: 0 auto; border-radius: 2px; border-bottom: 0px;"
          :header-cell-style="{ 'text-align': 'center' }" :cell-style="{ 'text-align': 'center' }" max-height="550px"
          border>
          <el-table-column fixed type="index" width="50" label="序号">
          </el-table-column>
          <el-table-column prop="id" label="编号" width="80">
          </el-table-column>
          <el-table-column prop="title" label="名称" width="150">
          </el-table-column>
          <el-table-column prop="origindate" label="创建时间" width="180">
          </el-table-column>
          <el-table-column prop="budget" label="项目金额" width="150">
          </el-table-column>
          <el-table-column prop="receipt" label="已收金额" width="120">
          </el-table-column>
          <el-table-column prop="classsize" label="班级人数" width="120">
          </el-table-column>
          <el-table-column prop="paysize" label="缴费人数" width="120">
          </el-table-column>

          <el-table-column prop="status" label="状态" width="100"
            :filters="[{ text: '待处理', value: 0 }, { text: '进行中', value: 1 }]"
            :filter-method="filterTag" filter-placement="bottom-end">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status === 0" type="info" disable-transitions>待处理</el-tag>
              <el-tag v-else-if="scope.row.status === 1" type="primary" disable-transitions>进行中</el-tag>
            </template>
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
        </el-table>

        <!-- 分页 -->
        <el-row :gutter="20">
          <el-col :span="4">
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
      </el-dialog>
    </div>
  </div>
</template>

<script>

import { SumBudget } from "@/utils/port";
import { SumPayout } from "@/utils/port";
import { SumReceipt } from "@/utils/port";
import { WarnStatus } from "@/utils/port";

export default {
  data() {
    return {
      like: true,
      value1: "",
      value2: "",
      value3: "",
      value4: "",
      value5: "",
      title1: '班费总收入',
      title2: '班费总支出',
      title3: '班费余额',
      title4: '预收班费',
      title5: '未缴费金额',

      //弹窗
      warning: false,

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
    }
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
      const data = await WarnStatus();
      this.tableData = data;
      this.pageTotal = data.length;
      this.pageData = this.queryByPage();
    },
    // 实现分页的方法
    //slice() 方法返回一个新的数组对象，
    //这一对象是一个由 begin 和 end 决定的原数组的浅拷贝
    //（包括 begin，不包括end）。原始数组不会被改变。
    //当展示第1页并每页10条数据时：应当截取索引0-9的数据，
    //即tableData.slice(0, 10) => currentPage = 1;pageSize = 10。
    queryByPage() {
      // 起始位置 = (当前页 - 1) x 每页的大小
      const start = (this.currentPage - 1) * this.pageSize;
      // 结束位置 = 当前页 x 每页的大小
      const end = this.currentPage * this.pageSize;
      return this.tableData.slice(start, end);
    },


    filterTag(value, row) {
      return row.status === value;
    },

  },
  mounted() {


  },
  watch: {

  },
  created() {
    // this.tableData()
    SumReceipt().then(res => {
      this.value1 = res
      SumPayout().then(res => {
        this.value3 = this.value1 - res
      })
    })
    SumPayout().then(res => {
      this.value2 = res
    })

    SumBudget().then(res => {
      this.value4 = res
      SumReceipt().then(res => {
        this.value5 = this.value4 - res
      })
    })

    this.warning = true;
    setTimeout(() => {
      this.fetchData();
      
    }, 500);

  },
  computed: {

  },
  filters: {

  }
}
</script>

<style scoped lang='scss'>
.el-statistic .head {
  margin-bottom: 4px;
  color: #606266;
  font-size: 40px !important;
}

.el-statistic .con .number {
  font-size: 40px !important;
  padding: 4px;
}

.sizeCss {
  font-size: 30px;
  background-color: cornsilk;
}
</style>