<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>职员管理</el-breadcrumb-item>
      <el-breadcrumb-item>员工列表</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-row :gutter="25">
        <el-col :span="9">
          <el-input
            placeholder="请输入职工姓名"
            v-model="queryInfo.query"
            clearable
            @clear="getEmployee()"
          >
            <el-button slot="append" icon="el-icon-search" @click="getEmployee()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加职工</el-button>
        </el-col>
      </el-row>
      <el-table :data="employeeList" border stripe>
        <el-table-column type="index"></el-table-column>
        <el-table-column label="姓名" prop="name"></el-table-column>
        <el-table-column label="入职时间" prop="addTime"></el-table-column>
        <el-table-column label="职位" prop="position"></el-table-column>
        <el-table-column label="店铺" prop="shopId"></el-table-column>
        <el-table-column label="状态" prop="deleteStatus">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.deleteStatus" @change="employeeStateChange(scope.row)"></el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180px">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click="showEditDialog(scope.row.id)"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeEmployeeById(scope.row.id)"></el-button>
            <!--<el-tooltip effect="dark" content="分配职位" placement="top" :enterable="false">
            <el-button type="warning" icon="el-icon-setting" size="mini"></el-button>
            </el-tooltip>-->
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
    <el-dialog title="添加员工" :visible.sync="addDialogVisible" width="50%" @close="addDialogClosed">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="addForm.name"></el-input>
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-input v-model="addForm.position"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addEmployee">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 修改对话框-->
    <el-dialog title="修改员工" :visible.sync="editDialogVisible" width="55%" @close="editDialogClosed">
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="70px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-input v-model="editForm.position"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editEmployeeInfo">确 定</el-button>
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
        pagesize: 2
      },
      employeeList: [],
      total: 0,
      addDialogVisible: false,
      addForm: {
        name: "",
        position: ""
      },
      editDialogVisible: false,
      addFormRules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 2,
            max: 10,
            message: "职工名在2~10个字符长度之间",
            trigger: "blur"
          }
        ],
        position: [{ required: true, message: "请输入职位", trigger: "blur" }]
      },
      editForm: {},
      editFormRules: {
        name: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 2,
            max: 10,
            message: "职工名在2~10个字符长度之间",
            trigger: "blur"
          }
        ],
        position: [{ required: true, message: "请输入职位", trigger: "blur" }]
      }
    };
  },
  created() {
    this.getEmployee();
  },
  methods: {
    async getEmployee() {
      const { data: res } = await this.$http.get("employees", {
        params: this.queryInfo
      });
      console.log(res);
      if (res.status !== "200")
        return this.$message.error("获取职员列表失败！");
      this.employeeList = res.employeeList;
      this.total = res.total;
    },
    handleSizeChange(newSize) {
      this.queryInfo.pagesize = newSize;
      this.getEmployee();
    },
    handleCurrentChange(newPage) {
      //console.log(newPage);
      this.queryInfo.pagenum = newPage
      this.getEmployee();
    },
    async employeeStateChange(employeeInfo) {
      const { data: res } = await this.$http.put(
        `employeeStatus?id=${employeeInfo.id}&status=${employeeInfo.deleteStatus}`
      );
      if (res.status !== "200") {
        employeeInfo.status = !employeeInfo.status;
        return this.$message.error("更新状态失败！");
      }
      this.$message.success("更新状态成功！");
    },
    addDialogClosed() {
      this.$refs.addFormRef.resetFields();
    },
    addEmployee() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return;
        const { data: res } = await this.$http.post(
          "addEmployee",
          this.addForm
        );
        if (res.status !== "200") {
          return this.$message.error("添加失败！");
        }
        this.$message.success("添加成功！");
        this.addDialogVisible = false;
        this.getEmployee();
      });
    },
    async showEditDialog(id) {
      const {data: res} = await this.$http.get('employeeById?id='+id)
      this.editForm = res.data
      this.editDialogVisible = true
    },
    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
    editEmployeeInfo() {
      this.$refs.editFormRef.validate(async valid =>{
        if(!valid) return
        const {data: res} = await this.$http.post('updateEmployee',{
          id: this.editForm.id,
          name: this.editForm.name, 
          position: this.editForm.position
          })
          if(res.status !== '200') return this.$message.error('修改失败！')
          this.editDialogVisible = false
          this.getEmployee()
          this.$message.success('修改员工信息成功！')
      })
    },
    async removeEmployeeById(id) {
       const confirResult = await this.$confirm('此操作将永久删除该员工, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).catch(err =>{
          return err
        })
        if(confirResult !== 'confirm') {
          return this.$message.info('已取消删除')
        }
        const {data: res} = await this.$http.post('deleteEmployee',{'id': id})
        if (res.status !== "200") {
          return this.$message.error("删除失败！");
        }
        this.$message.success("删除成功！");
        this.getEmployee();
    }
  }
};
</script>

<style lang="less" scoped>
</style>