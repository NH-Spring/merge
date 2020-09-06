<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>菜单展示</el-breadcrumb-item>
      <el-breadcrumb-item>菜单列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="25">
        <el-col :span="7">
          <el-input placeholder="请输入搜索内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <!--<el-button type="primary" @click="goAddPage">添加</el-button>-->
        </el-col>
      </el-row>
      <el-table :data="menusList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="菜品名称" prop="menusName" width="300px"></el-table-column>
        <el-table-column label="菜品价格" prop="menusPrice" width="170px"></el-table-column>
        <el-table-column label="图片" width="280px">
          <template slot-scope="scope">
            <img :src="scope.row.menusPicture" min-width="70" height="70" />
          </template>
        </el-table-column>
        <el-table-column label="菜品描述" prop="introduce" width="170px"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 5
      },
      menusList: [],
      total: 0
    };
  },
  created() {
    this.getMenus();
  },
  methods: {
    async getMenus() {
      const { data: res } = await this.$http.get("getMenus", {
        params: this.queryInfo
      });
      console.log(res);
      if (res.status !== "200") {
        return this.$message.error("获取失败");
      }
      this.$message.success("获取成功！");
      this.menusList = res.menusList;
      //this.total = res.total
    },
    goAddPage() {
      this.$router.push("addPage");
    }
  }
};
</script>
<style lang="less" scoped>
</style>