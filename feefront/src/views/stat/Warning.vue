<template>
  <div>
    <div>
    <h2>收入管理待处理列表</h2>
  </div>
    <el-table :data="pageData" style="width: 100%; margin: 0 auto; border-radius: 2px; border-bottom: 0px;"
      :header-cell-style="{ 'text-align': 'center' }" :cell-style="{ 'text-align': 'center' }" max-height="550px" border>
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
      <el-table-column prop="receipt" label="已收金额" width="150">
      </el-table-column>
      <el-table-column prop="classsize" label="班级人数" width="150">
      </el-table-column>
      <el-table-column prop="paysize" label="缴费人数" width="150">
      </el-table-column>

      <el-table-column prop="status" label="状态" width="150"
        :filters="[{ text: '待处理', value: 0 }, { text: '进行中', value: 1 }]" :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status === 0" type="info" disable-transitions>待处理</el-tag>
          <el-tag v-else-if="scope.row.status === 1" type="primary" disable-transitions>进行中</el-tag>
        </template>
      </el-table-column>


      <el-table-column label="备注描述" width="120" type="expand">
        <template slot-scope="scope">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="备注描述内容：" style="margin-left: 30px;">
              <span>{{ scope.row.descr }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
    </el-table>

    <el-row :gutter="20">
      <el-col :span="4">
      </el-col>
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage"
            :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="pageTotal" class="pagination">
          </el-pagination>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content bg-purple">
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {



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


  },
  mounted() {

  },
  watch: {

  },
  created() {
    setTimeout(() => {
      this.fetchData();
    }, 1000);

  },
  computed: {

  },
  filters: {

  }
}
