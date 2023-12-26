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



    filterTag(value, row) {
      return row.status === value;
    },

    sum() {
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
    },


    async checkAndOpen() {
    try {
      const warnStatusData = await WarnStatus();
      if (warnStatusData.length > 0) {
        this.open(warnStatusData);
      }
    } catch (error) {
      console.error(error);
    }
  },
  // map 用于在数组的每个元素上调用的函数，
  //并将结果组成一个新数组的方法。这个新数组将包含每个元素经过函数处理后的结果

  open(dataList) {
    // 使用 map 来遍历数据列表
  const messages = dataList.map(data => `编号： ${data.id}，名称： ${data.title}`).join('<br>');
  this.$notify.warning({
    title: '注意！有缴费记录未完成，请及时处理：',
    dangerouslyUseHTMLString: true,
    message: `<br>${messages}`,
    showClose: false,
  });
},

  },
  mounted() {

    this.checkAndOpen();

  },
  watch: {

  },
  created() {
    setTimeout(() => {
      this.sum();
    }, 1000);

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