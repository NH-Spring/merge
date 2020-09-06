<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>店铺管理</el-breadcrumb-item>
      <el-breadcrumb-item>店铺列表</el-breadcrumb-item>
    </el-breadcrumb>
 <el-card class="box-card">
      <el-row :gutter="25">
        <el-col :span="9">
          <el-input
            placeholder="请输入店铺名称"
            v-model="queryInfo.query"
            clearable
            @clear="getShopList()"
          >
            <el-button slot="append" icon="el-icon-search" @click="getShopList()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加店铺</el-button>
        </el-col>
      </el-row>
      <el-table :data="shopList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="店铺名称" prop="shopName"></el-table-column>
        <el-table-column label="添加时间" prop="addTime"></el-table-column>
        <el-table-column label="位置" prop="address"></el-table-column>
        <el-table-column label="状态" prop="deleteStatus">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.deleteStatus" @change="shopStateChange(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeShopById(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="queryInfo.pagenum"
        :page-sizes="[1, 2, 3, 4]"
        :page-size="queryInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </el-card>
    <!--添加对话框 -->
    <el-dialog title="添加店铺" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px">
        <el-form-item label="店铺名称" prop="shopName">
          <el-input v-model="addForm.shopName"></el-input>
        </el-form-item>
        <el-form-item label="店铺地址" prop="address">
          <el-cascader :options="cityData" v-model="addForm.address"></el-cascader>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addShop">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改对话框-->
    <el-dialog title="修改店铺" :visible.sync="editDialogVisible" width="55%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="80px">
        <el-form-item label="店铺名称" prop="shopName">
          <el-input v-model="editForm.shopName"></el-input>
        </el-form-item>
        <el-form-item label="店铺地址" prop="address">
          <el-input v-model="editForm.address"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editShopInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import cityData from './citydata.js'

export default {
  data() {
    return {
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 2
      },
      shopList: [],
      total: 0,
      addDialogVisible: false,
      addForm: {
        shopName: "",
        address: []
      },
      editDialogVisible: false,
      addFormRules: {
        shopName: [
          { required: true, message: "请输入店铺名", trigger: "blur" }
        ],
        address: [{ required: true, message: "请输入店铺地址", trigger: "blur" }]
      },
      editForm: {},
      cityData,
      editFormRules: {
        shopName: [
          { required: true, message: "请输入店铺名", trigger: "blur" }
      
        ],
        address: [{ required: true, message: "请输入店铺地址", trigger: "blur" }]
      }
    };
  },
  created() {
    this.getShopList();
  },
  methods: {
    async getShopList() {
      const { data: res } = await this.$http.get("shops", {
        params: this.queryInfo
      });

      if (res.status !== "200")
        return this.$message.error("获取列表失败！");
      this.shopList = res.shopList;
      this.total = res.total;
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize;
      this.getShopList();
    },
    handleCurrentChange(newPage) {
      this.queryInfo.pagenum = newPage
      this.getShopList();
    },
    async shopStateChange(shopInfo) {
      const { data: res } = await this.$http.put(
        `shopStatus?id=${shopInfo.id}&status=${shopInfo.deleteStatus}`
      );
      if (res.status !== "200") {
        shopInfo.status = !shopInfo.status;
        return this.$message.error("更新状态失败！");
      }
      this.$message.success("更新状态成功！");
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    addShop() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return;
        const { data: res } = await this.$http.post(
          "addShop",
          this.addForm
        );
        if (res.status !== "200") {
          return this.$message.error("添加失败！");
        }
        this.$message.success("添加成功！");
        this.addDialogVisible = false;
        this.getShopList();
      });
    },
    async showEditDialog(id) {
      const {data: res} = await this.$http.get('shopById?id='+id)
      this.editForm = res.data
      this.editDialogVisible = true
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    editShopInfo() {
      this.$refs.editFormRef.validate(async valid =>{
        if(!valid) return
        const {data: res} = await this.$http.post('updateShop',{
          id: this.editForm.id,
          ShopName: this.editForm.shopName, 
          address: this.editForm.address
          })
          if(res.status !== '200') return this.$message.error('修改失败！')
          this.editDialogVisible = false
          this.getShopList()
          this.$message.success('修改信息成功！')
      })
    },
    async removeShopById(id) {
       const confirResult = await this.$confirm('此操作将永久删除该信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err =>{
          return err
        })
        if(confirResult !== 'confirm') {
          return this.$message.info('已取消删除')
        }
        const {data: res} = await this.$http.post('deleteShop',{'id': id})
        if (res.status !== "200") {
          return this.$message.error("删除失败！");
        }
        this.$message.success("删除成功！");
        this.getShopList();
    }
  }
};
</script>

<style lang="less" scoped>
.el-cascader {
    width: 100%;
    height: 40px;
}
</style>