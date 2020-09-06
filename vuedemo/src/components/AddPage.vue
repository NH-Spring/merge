<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜单添加</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-alert title="添加菜单信息" type="warning" center show-icon :closable="false"></el-alert>
      <el-steps :active="activeNum - 0" finish-status="success" align-center>
        <el-step title="基本信息"></el-step>
        <el-step title="商品图片"></el-step>
        <el-step title="商品内容"></el-step>
        <el-step title="完成"></el-step>
      </el-steps>
      <el-form
        :model="addForm"
        :rules="addFormRules"
        ref="addFormRef"
        label-width="110px"
        label-position="top"
      >
        <el-tabs :tab-position="'left'" v-model="activeNum" :before-leave="beforeTab">
          <el-tab-pane label="基本信息" name="1">
            <el-form-item label="菜品名称" prop="menusName">
              <el-input v-model="addForm.menusName"></el-input>
            </el-form-item>
            <el-form-item label="菜品价格" prop="menusPrice">
              <el-input v-model="addForm.menusPrice" type="number"></el-input>
            </el-form-item>
            <el-form-item label="菜品分类" prop="menusClassIds">
              <el-cascader
                expand-trigger="hover"
                :options="menuClassList"
                :props="menusProps"
                v-model="addForm.menusClassIds"
                @change="handlChange"
              ></el-cascader>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="商品图片" name="2">
            <!-- action 后台API地址-->
            <el-upload
              action="http://localhost:8081/upload"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              list-type="picture"
              :headers="headerObject"
              :on-success="handleSucc"
              :limit="1"
            >
              <el-button size="small" type="primary">点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
          </el-tab-pane>
          <el-tab-pane label="商品内容" name="3">
            <quill-editor v-model="addForm.introduce"></quill-editor>
            <button type="danger" style="margin-top: 10px" @click="addM">添加菜品</button>
          </el-tab-pane>
        </el-tabs>
      </el-form>
    </el-card>
    <el-dialog title="图片预览" :visible.sync="preView" width="45%">
      <img :src="prePath" class="imgStyle" alt />
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      preView: false,
      prePath: "",
      activeNum: "1",
      menuClassList: [],
      headerObject: {
        Authorization: window.sessionStorage.getItem("token")
      },
      menusProps: {
        label: "menuClassName",
        value: "id",
        template: "order"
      },
      addForm: {
        menusName: "",
        menusPrice: 0,
        menusClassIds: [],
        menusClassId: 0,
        menusPicture: "",
        introduce: "",
        
      },
      addFormRules: {
        menusName: [
          { required: true, message: "请输入菜品名", trigger: "blur" }
        ],
        menusPrice: [
          { required: true, message: "请输入价格", trigger: "blur" }
        ],
        menusClassIds: [
          { required: true, message: "请选择分类", trigger: "blur" }
        ]
      }
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
    handlChange() {
      if (this.addForm.menusClassIds.length !== 2) {
        this.addForm.menusClassIds = [];
      }
      if (this.addForm.menusClassIds.length === 2) {
        this.addForm.menusClassId = this.addForm.menusClassIds[
          this.addForm.menusClassIds.length - 1
        ];
      }
    },
    beforeTab(activeName, oldActiveName) {
      if (oldActiveName === "1" && this.addForm.menusClassIds.length !== 2) {
        this.$message.error("请将基本信息填写完整！");
        return false;
      }
    },
    handleSucc(response) {
      const picInfo = response;
      this.addForm.menusPicture = picInfo.path;
      console.log(response);
      
    },
    handlePreview(file) {
      this.prePath = file.response.path;
      this.addForm.menusPicture = file.response.path
      this.preView = true;
    },
    handleRemove(file) {},
    addM() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) {
          return this.$message.error("请填写必要的表单项！");
        }
        const { data: res } = await this.$http.post("addmenus", this.addForm);
        if (res.status !== "200") {
          return this.$message.error("添加失败");
        }
        this.$message.success("添加成功");
        this.$router.push('menus')
      });
    }
  }
};
</script>
<style lang="less" scoped>
.imgStyle {
  width: 100%;
}
</style>