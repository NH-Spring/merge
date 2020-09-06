<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>数据统计</el-breadcrumb-item>
      <el-breadcrumb-item>统计表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <div id="main" style="width: 600px;height: 400px;"></div>
    </el-card>
  </div>
</template>

<script>
import echarts from "echarts";

export default {
  data() {
    return {};
  },
  created() {},
  async mounted() {
    var myChart = echarts.init(document.getElementById("main"));
    const { data: res } = await this.$http.get('getEcha')
    if (res.status !== '200') {
      return this.$message.error('获取折线图数据失败！')
    }

    var option = {
      title: {
        text: "店铺新增长趋势"
      },
      xAxis: {
        type: "category",
        data: res.time
      },
      yAxis: {
        type: "value"
      },
      series: [
        {
          data: res.num,
          type: "line"
        }
      ]
    };
    myChart.setOption(option);
  },
  methods: {}
};
</script>
<style lang="less" scoped>
</style>