<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜单分类</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row>
        <el-col>
          <el-button @click="showAddDia" type="primary">添加分类</el-button>
        </el-col>
      </el-row>
      <tree-table
        class="treeaTable"
        :data="menuClassList"
        :columns="columns"
        :selection-type="false"
        :expand-type="false"
        show-index
        border
      >
        <template slot="isok" slot-scope="scope">
          <i class="el-icon-success" v-if="scope.row.deleteStatus === true" style="color: green" />
          <i class="el-icon-error" v-else style="color: red" />
        </template>
        <template slot="order" slot-scope="scope">
          <el-tag size="mini" v-if="scope.row.level === 1">一级</el-tag>
          <el-tag type="warning" size="mini" v-else>二级</el-tag>
        </template>
        <template slot="opt" slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            size="mini"
            @click="showEditDialog(scope.row)"
          >编辑</el-button>
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            @click="removeMenuClass(scope.row.id)"
          >删除</el-button>
        </template>
      </tree-table>
      <!--
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="querInfo.pagenum"
        :page-sizes="[1, 2, 3, 4]"
        :page-size="querInfo.pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
      -->
    </el-card>
    <!-- 修改对话框-->
    <el-dialog title="修改员工" :visible.sync="editDialogVisible" width="55%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="分类名" prop="menuClassName">
          <el-input v-model="editForm.menuClassName"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editMenuClassInfo">确 定</el-button>
      </span>
    </el-dialog>
    <!--添加对话框 -->
    <el-dialog title="添加分类" :visible.sync="addDialogVisible" width="55%" @close="addDialogClose">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="80px">
        <el-form-item label="分类名称" prop="menuClassName">
          <el-input v-model="addForm.menuClassName"></el-input>
        </el-form-item>
        <el-form-item label="父级分类">
          <el-cascader
            expand-trigger="hover"
            :options="parentList"
            :props="cascaderProps"
            v-model="selectKeys"
            @change="parentChange"
            clearable
          ></el-cascader>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addMenuClass">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      //querInfo: {
      // type: 2,
      // pagenum: 1,
      // pagesize: 3
      // },
      menuClassList: [],
      total: 0,
      addDialogVisible: false,
      editDialogVisible: false,
      addForm: {
        menuClassName: "",
        pid: 0,
        level: 1
      },
      selectKeys: [],
      cascaderProps: {
        value: "id",
        label: "menuClassName",
        children: "children"
      },
      parentList: [],
      editForm: {},
      addFormRules: {
        menuClassName: [
          { required: true, message: "请输入分类名", trigger: "blur" }
        ]
      },
      editFormRules: {
        menuClassName: [
          { required: true, message: "请输入分类名", trigger: "blur" }
        ]
      },
      columns: [
        {
          label: "分类名称",
          prop: "menuClassName"
        },
        {
          label: "是否有效",
          type: "template",
          template: "isok"
        },
        {
          label: "排序",
          type: "template",
          template: "order"
        },
        {
          label: "操作",
          type: "template",
          template: "opt"
        }
      ]
    };
  },
  created() {
    this.getmenuClassList();
  },
  methods: {
    async getmenuClassList() {
      const { data: res } = await this.$http.get("getmenuClassList");
      if (res.status !== "200") {
        return this.$message.error("获取失败");
      }
      this.menuClassList = res.menuClassList;
      this.total = res.total;
    },
    handleSizeChange(newSize) {
      this.querInfo.pagesize = newSize;
      this.getmenuClassList();
    },
    handleCurrentChange(newPage) {
      this.querInfo.pagenum = newPage;
      this.getmenuClassList();
    },
    showAddDia() {
      this.getparent();
      this.addDialogVisible = true;
    },
    async getparent() {
      const { data: res } = await this.$http.get("getparent");
      if (res.status !== "200") {
        return this.$message.error("获取失败");
      }
      this.parentList = res.data;
    },
    parentChange() {
      if (this.selectKeys.length > 0) {
        this.addForm.pid = this.selectKeys[this.selectKeys.length - 1];
        this.addForm.level = this.selectKeys.length + 1;
        return;
      } else {
        this.addForm.pid = 0;
        this.addForm.level = 1;
      }
    },
    addMenuClass() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return;
        const { data: res } = await this.$http.post(
          "addMenuClass",
          this.addForm
        );
        if (res.status !== "200") {
          return this.$message.error("获取失败");
        }
        this.$message.success("添加成功！");
        this.getmenuClassList();
        this.addDialogVisible = false;
      });
    },
    addDialogClose() {
      this.$refs.addFormRef.resetFields();
      this.selectKeys = [];
      this.addForm.pid = 0;
      this.addForm.level = 1;
    },
    async removeMenuClass(id) {
      const confirResult = await this.$confirm(
        "此操作将永久删除该分类, 是否继续?",
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
      const { data: res } = await this.$http.post("deleteMenuClass", {
        id: id
      });
      if (res.status !== "200") {
        return this.$message.error("删除失败！");
      }
      this.$message.success("删除成功！");
      this.getmenuClassList();
    },
    async showEditDialog(menuClass) {
      //const { data: res } = await this.$http.get("employeeById?id=" + id);
      this.editForm = menuClass;
      this.editDialogVisible = true;
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    editMenuClassInfo() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return;
        const { data: res } = await this.$http.post("updateMenuClass", {
          id: this.editForm.id,
          menuClassName: this.editForm.menuClassName,
        });
        if (res.status !== "200") return this.$message.error("修改失败！");
        this.editDialogVisible = false;
        this.getmenuClassList();
        this.$message.success("修改员工信息成功！");
      });
    }
  }
};
</script>
<style lang="less" scoped>
.treeaTable {
  margin-top: 16px;
}
</style>