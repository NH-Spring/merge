<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>职员管理</el-breadcrumb-item>
      <el-breadcrumb-item>管理员列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-row :gutter="25">
        <el-col :span="9">
          <el-input placeholder="请输入管理员账号" v-model="queryInfo.query" clearable @clear="getAdmin()">
            <el-button slot="append" icon="el-icon-search" @click="getAdmin()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="getshop()">添加管理员</el-button>
        </el-col>
      </el-row>
      <el-table :data="adminList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="账号" prop="userName"></el-table-column>
        <el-table-column label="添加时间" prop="addTime"></el-table-column>
        <el-table-column label="密码" prop="password"></el-table-column>
        <el-table-column label="邮箱" prop="email"></el-table-column>
        <el-table-column label="状态" prop="deleteStatus">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.deleteStatus" @change="adminStateChange(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.id)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeAdminById(scope.row.id)"
            ></el-button>
            <el-tooltip effect="dark" content="分配角色" placement="top" :enterable="false">
              <el-button
                type="warning"
                icon="el-icon-setting"
                size="mini"
                @click="setRole(scope.row)"
              ></el-button>
            </el-tooltip>
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
    <el-dialog title="添加管理者" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="账号" prop="userName">
          <el-input v-model="addForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="addForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
        <el-form-item label="所属店铺" prop="shopId">
          <el-select v-model="addForm.shopId" placeholder="请选择">
            <el-option
              v-for="item in shopList"
              :key="item.id"
              :label="item.shopName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addAdmin">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改对话框-->
    <el-dialog title="修改信息" :visible.sync="editDialogVisible" width="55%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="账号" prop="userName">
          <el-input v-model="editForm.userName" disabled></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="editForm.password"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editAdminInfo">确 定</el-button>
      </span>
    </el-dialog>
    <!--角色-->
    <el-dialog
      title="分配角色"
      :visible.sync="setRoleDialogVisible"
      width="50%"
      @close="setRoleDialogClosed"
    >
      <div>
        <p>当前的用户：{{adminInfo.userName}}</p>
        <p>当前的角色: {{roleName}}</p>
        <p>
          分配新角色：
          <el-select v-model="selectedRoleId" placeholder="请选择">
            <el-option
              v-for="item in rolesList"
              :key="item.id"
              :label="item.roleName"
              :value="item.id"
            ></el-option>
          </el-select>
        </p>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="setRoleDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    var checkEmail = (rule, value, cb) => {
      // 验证邮箱的正则表达式
      const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;

      if (regEmail.test(value)) {
        // 合法的邮箱
        return cb();
      }

      cb(new Error("请输入合法的邮箱"));
    };
    return {
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 2
      },
      setRoleDialogVisible: false,
      adminList: [],
      total: 0,
      addDialogVisible: false,
      addForm: {
        name: "",
        password: "",
        email: "",
        shopId: 0
      },
      roleName: "",
      editDialogVisible: false,
      addFormRules: {
        userName: [
          { required: true, message: "请输入账号", trigger: "blur" },
          {
            min: 6,
            max: 10,
            message: "账号在6~10个字符长度之间",
            trigger: "blur"
          }
        ],
        password: [
          { required: true, message: "请输密码", trigger: "blur" },
          {
            min: 6,
            max: 10,
            message: "密码在6~10个字符长度之间",
            trigger: "blur"
          }
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          { validator: checkEmail, trigger: "blur" }
        ]
      },
      editForm: {},
      adminInfo: {},
      rolesList: [],
      shopList: [],
      selectedRoleId: "",
      editFormRules: {
        userName: [
          { required: true, message: "请输入账号", trigger: "blur" },
          {
            min: 6,
            max: 10,
            message: "账号在6~10个字符长度之间",
            trigger: "blur"
          }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 6,
            max: 10,
            message: "密码在6~10个字符长度之间",
            trigger: "blur"
          }
        ],
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          { validator: checkEmail, trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getAdmin();
  },
  methods: {
    async getAdmin() {
      const { data: res } = await this.$http.get("admins", {
        params: this.queryInfo
      });
      console.log(res);
      if (res.status !== "200") return this.$message.error("获取列表失败！");
      this.adminList = res.adminList;
      this.total = res.total;
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize;
      this.getAdmin();
    },
    handleCurrentChange(newPage) {
      //console.log(newPage);
      this.queryInfo.pagenum = newPage;
      this.getAdmin();
    },
    async adminStateChange(adminInfo) {
      const { data: res } = await this.$http.put(
        `adminStatus?id=${adminInfo.id}&status=${adminInfo.deleteStatus}`
      );
      if (res.status !== "200") {
        adminInfo.status = !adminInfo.status;
        return this.$message.error("更新状态失败！");
      }
      this.$message.success("更新状态成功！");
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    async addAdmin() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return;
        const { data: res } = await this.$http.post("addAdmin", this.addForm);
        if (res.status !== "200") {
          return this.$message.error("添加失败！");
        }
        this.$message.success("添加成功！");
        this.addDialogVisible = false;
        this.getAdmin();
      });
    },
    async showEditDialog(id) {
      const { data: res } = await this.$http.get("adminById?id=" + id);
      this.editForm = res.data;
      this.editDialogVisible = true;
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    editAdminInfo() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return;
        const { data: res } = await this.$http.post("updateAdmin", {
          id: this.editForm.id,
          name: this.editForm.name,
          password: this.editForm.password,
          email: this.editForm.email
        });
        if (res.status !== "200") return this.$message.error("修改失败！");
        this.editDialogVisible = false;
        this.getAdmin();
        this.$message.success("修改信息成功！");
      });
    },
    async removeAdminById(id) {
      const confirResult = await this.$confirm(
        "此操作将永久删除该账号, 是否继续?",
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
      const { data: res } = await this.$http.post("deleteAdmin", { id: id });
      if (res.status !== "200") {
        return this.$message.error("删除失败！");
      }
      this.$message.success("删除成功！");
      this.getAdmin();
    },
    async setRole(adminInfo) {
      this.adminInfo = adminInfo;
      const { data: res } = await this.$http.get("roles");
      if (res.status !== "200") {
        return this.$message.error("获取角色列表失败！");
      }
      const { data: rest } = await this.$http.get(
        "findRoleById?id=" + adminInfo.roleId
      );
      this.rolesList = res.data;
      this.roleName = rest.data.roleName;
      this.setRoleDialogVisible = true;
    },
    async getshop() {
      
      const { data: res } = await this.$http.get("shop");
      if (res.status !== "200") {
        return this.$message.error("获取角色列表失败！");
      }
      this.shopList = res.shopList;

      this.addDialogVisible = true
    },
    async saveRoleInfo() {
      if (!this.selectedRoleId) {
        return this.$message.error("请选择要分配的角色！");
      }
      const { data: res } = await this.$http.post("/updateAdminRoleId", {
        id: this.adminInfo.id,
        rid: this.selectedRoleId
      });
      if (res.status !== "200") {
        return this.$message.error("更新失败！");
      }
      this.$message.success("更新角色成功");
      this.getAdmin();
      this.setRoleDialogVisible = false;
    },
    setRoleDialogClosed() {
      this.selectedRoleId = "";
      this.adminInfo = {};
    }
  }
};
</script>

<style lang="less" scoped>
</style>