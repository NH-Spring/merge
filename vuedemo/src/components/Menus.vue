<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜单列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="25">
        <el-col :span="7">
          <el-input placeholder="请输入搜索内容" v-model="queryInfo.query" clearable @clear="getMenus()">
            <el-button slot="append" icon="el-icon-search" @click="getMenus()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="goAddPage">添加</el-button>
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
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-edit"
              @click="showEditDialog(scope.row.id)"
            ></el-button>
            <el-button
              type="danger"
              size="mini"
              icon="el-icon-delete"
              @click="removeMenus(scope.row.id)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 修改对话框-->
    <el-dialog title="修改菜单" :visible.sync="editDialogVisible" width="55%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="菜名" prop="menusName">
          <el-input v-model="editForm.menusName"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="menusPrice">
          <el-input v-model="editForm.menusPrice"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editmenusInfo">确 定</el-button>
      </span>
    </el-dialog>
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
      editForm: {},
      editFormRules: {
        menusName: [
          { required: true, message: "请输入菜名", trigger: "blur" }
        ],
        menusPrice: [{ required: true, message: "请输入价格", trigger: "blur" }]
      },
      editDialogVisible: false,
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
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    async removeMenus(id) {
      const confirResult = await this.$confirm(
        "此操作将永久删除, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      ).catch(err => {
        return err;
      });
      if (confirResult !== "confirm") {
        return this.$message.info("已取消删除");
      }
      const { data: res } = await this.$http.post("deletemenu", { id: id });
      if (res.status !== "200") {
        return this.$message.error("删除失败！");
      }
      this.$message.success("删除成功！");
      this.getMenus();
    },
    async showEditDialog(id) {
      const { data: res } = await this.$http.get("menusByid?id=" + id);
      this.editForm = res.data;
      this.editDialogVisible = true;
    },
        editmenusInfo() {
      this.$refs.editFormRef.validate(async valid =>{
        if(!valid) return
        const {data: res} = await this.$http.post('updatemenus',{
          id: this.editForm.id,
          menusName: this.editForm.menusName, 
          menusPrice: this.editForm.menusPrice
          })
          if(res.status !== '200') return this.$message.error('修改失败！')
          this.editDialogVisible = false
          this.getMenus()
          this.$message.success('修改信息成功！')
      })
    }
  }
};
</script>
<style lang="less" scoped>
</style>