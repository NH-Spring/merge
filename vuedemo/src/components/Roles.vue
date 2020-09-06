<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>权限管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row>
        <el-col>
          <el-button type="danger" @click="addDialogVisible = true">添加角色</el-button>
        </el-col>
      </el-row>
      <el-table :data="roleList" border stripe>
        <el-table-column type="expand">
          <template slot-scope="scope">
            <el-row
              :class="['bdbottom', i1 === 0 ? 'bdtop' : '', 'vcenter']"
              v-for="(item1, i1) in scope.row.childrenRigths"
              :key="item1.id"
            >
              <el-col :span="5">
                <el-tag>{{item1.authName}}</el-tag>
                <i class="el-icon-caret-right"></i>
              </el-col>
              <el-col :span="19">
                <el-row
                  :class="[i2 === 0 ? '' : 'bdtop', 'vcenter']"
                  v-for="(item2, i2) in item1.childrenRigths"
                  :key="item2.id"
                >
                  <el-col :span="6">
                    <el-tag type="success">{{item2.authName}}</el-tag>
                    <i class="el-icon-caret-right"></i>
                  </el-col>
                  <el-col :span="18">
                    <el-tag
                      type="warning"
                      v-for="(item3, i3) in item2.childrenRigths"
                      :key="item3.id"
                      closable
                      @close="removeRightById(scope.row, item3.id)"
                    >{{item3.authName}}</el-tag>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
            <!--<pre> {{scope.row}}</pre>-->
          </template>
        </el-table-column>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="角色名称" prop="roleName"></el-table-column>
        <el-table-column label="角色描述" prop="roleDesc"></el-table-column>
        <el-table-column label="操作" width="310px">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="showEditDialog(scope.row.id)"
            >编辑</el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="removeRightsById(scope.row.id)"
            >删除</el-button>
            <el-button
              type="warning"
              icon="el-icon-setting"
              size="mini"
              @click="showSetRightsDialog(scope.row)"
            >分配权限</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog
      title="分配权限"
      :visible.sync="setRightsDialogVisible"
      width="55%"
      @close="setRightsDialogClosed"
    >
      <el-tree
        :data="rightsList"
        :props="treeProps"
        show-checkbox
        node-key="id"
        default-expand-all
        :default-checked-keys="defkeys"
        ref="treeRef"
      ></el-tree>
      <span slot="footer" class="dialog-footer">
        <el-button @click="setRightsDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="allotRights">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改对话框-->
    <el-dialog title="修改角色" :visible.sync="editDialogVisible" width="55%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="95px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="editForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input v-model="editForm.roleDesc"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editRoleInfo">确 定</el-button>
      </span>
    </el-dialog>
    <!--添加对话框 -->
    <el-dialog title="添加员工" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="95px">
        <el-form-item label="角色名字" prop="roleName">
          <el-input v-model="addForm.roleName"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" prop="roleDesc">
          <el-input v-model="addForm.roleDesc"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRoleRights">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      roleList: [],
      setRightsDialogVisible: false,
      rightsList: [],
      treeProps: {
        label: "authName",
        children: "childrenRigths"
      },
      defkeys: [],
      roleId: "",
      editForm: {},
      addForm: {
        roleName: "",
        roleDesc: ""
      },
      editDialogVisible: false,
      addDialogVisible: false,
      editFormRules: {
        roleName: [
          { required: true, message: "请输入角色名", trigger: "blur" }
        ],
        roleDesc: [
          { required: true, message: "请输入角色描述", trigger: "blur" }
        ]
      },
      addFormRules: {
        roleName: [
          { required: true, message: "请输入角色名", trigger: "blur" }
        ],
        roleDesc: [
          { required: true, message: "请输入角色描述", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getRolesList();
  },
  methods: {
    async getRolesList() {
      const { data: res } = await this.$http.get("roles");
      if (res.status !== "200") {
        return this.$message.error("获取失败！");
      }

      this.roleList = res.data;
      console.log(this.roleList);
    },
    async removeRightById(role, rightsId) {
      const confirmResult = await this.$confirm(
        "此操作将永久删除文件，是否继续？",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      ).catch(err => err);
      if (confirmResult !== "confirm") {
        return this.$message.info("已取消删除！");
      }
      const { data: res } = await this.$http.post("deleteRole", {
        roleId: role.id,
        rightsId: rightsId
      });
      if (res.status !== "200") {
        return this.$message.error("删除失败！");
      }
      role.childrenRigths = res.data;
    },
    async showSetRightsDialog(role) {
      this.roleId = role.id;
      const { data: res } = await this.$http.get("rightsTree");
      if (res.status !== "200") {
        return this.$message.error("获取失败！");
      }
      this.rightsList = res.rightsList;
      //console.log(role)
      this.getLeafKeys(role, this.defkeys);
      this.setRightsDialogVisible = true;
    },
    getLeafKeys(node, arr) {
      if (!node.childrenRigths) {
        return arr.push(node.id);
      }

      node.childrenRigths.forEach(item => this.getLeafKeys(item, arr));
    },
    setRightsDialogClosed() {
      this.defkeys = [];
    },
    async allotRights() {
      const keys = [
        ...this.$refs.treeRef.getCheckedKeys(),
        ...this.$refs.treeRef.getHalfCheckedKeys()
      ];
      const idStr = keys.join(",");
      const { data: res } = await this.$http.post("roleAdd", {
        rids: idStr,
        roleId: this.roleId
      });
      if (res.status !== "200") {
        return this.$message.error("分配权限失败！");
      }
      this.$message.success("分配权限成功！");
      this.getRolesList();
      this.setRightsDialogVisible = false;
    },
    async removeRightsById(id) {
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
      const { data: res } = await this.$http.post("deleteRoleAll", { id: id });
      if (res.status !== "200") {
        return this.$message.error("删除失败！");
      }
      this.$message.success("删除成功！");
      this.getRolesList();
    },
    async showEditDialog(id) {
      const { data: res } = await this.$http.get("findRoleById?id=" + id);
      this.editForm = res.data;
      this.editDialogVisible = true;
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields();
    },
    editRoleInfo() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return;
        const { data: res } = await this.$http.post("updateRole", {
          id: this.editForm.id,
          roleName: this.editForm.roleName,
          roleDesc: this.editForm.roleDesc
        });
        if (res.status !== "200") return this.$message.error("修改失败！");
        this.editDialogVisible = false;
        this.getRolesList();
        this.$message.success("修改角色信息成功！");
      });
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    addRoleRights() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return;
        const { data: res } = await this.$http.post("addRole", this.addForm);
        if (res.status !== "200") {
          return this.$message.error("添加失败！");
        }
        this.$message.success("添加成功！");
        this.addDialogVisible = false;
        this.getRolesList();
      });
    }
  }
};
</script>
<style lang="less" scoped>
.el-tag {
  margin: 6px;
}
.bdtop {
  border-bottom: 1px solid #eee;
}
.vcenter {
  display: flex;
  align-items: center;
}
</style>