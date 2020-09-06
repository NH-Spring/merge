<template>
  <el-container class="home-container">
    <el-header>
        <div>
        <img src="../assets/login1.png" alt="" height="60px">
        <span>连锁餐饮管理系统</span>
        </div>
        <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <div class="toggle-button" @click="toggleCollapse()">|||</div>
          <el-menu background-color="#333750" text-color="#fff"
            active-text-color="#409eff" :unique-opened="true" :collapse="isCollapse" 
            :collapse-transition="false" router :default-active="activePath">
      <el-submenu :index="item.path" v-for="item in menuLeftlist" :key="item.path">

        <template slot="title">
          <i :class="iconsObj[item.path]"></i>
          <span>{{item.authName}}</span>
        </template>

        <el-menu-item :index="'/' + sub.path" v-for="sub in item.menusLeftChildren" :key="sub.path" 
        @click="saveNavState('/' + sub.path)">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>{{sub.authName}}</span>
          </template>
        </el-menu-item>
      </el-submenu>
      
    </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>  
</template>

<script>
export default {
    data() {
      return {
      menuLeftlist: [],
      iconsObj: {
        employee: 'iconfont icon-user',
        shop: 'iconfont icon-shangpin',
        power: 'iconfont icon-tijikongjian',
        menu: 'el-icon-s-order',
        statistics : 'iconfont icon-baobiao'
      },
      isCollapse: false,
      activePath: ''
      }
    },
    created () {
      this.getMenuList()
      this.activePath = window.sessionStorage.getItem('activePath')
    },
    methods: {
        logout() {
            window.sessionStorage.clear();
            this.$router.push("/login");
        },
        async getMenuList() {
            const {data: res} = await this.$http.get('menusLeft')
            if(res.status !== '200') return this,$message.error("请求失败！")
            this.menuLeftlist = res.menusLeftlist
            console.log(res)
        },
        toggleCollapse() {
            this.isCollapse =! this.isCollapse
        },
        saveNavState(activePath) {
        window.sessionStorage.setItem('activePath', activePath)
        this.activePath = activePath
      }
    }

}
</script>
<style lang="less" scoped>
.home-container {
    height: 100%;
}
.el-header {
    background-color: #373341;
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    align-items: center;
    color: #fff;
    font-size: 20px;
    > div {
        display: flex;
        align-items: center;
        span {
        margin-left: 15px;
    }
    }
}
.el-aside {
    background-color: #333750;
    .el-menu {
      border-right: none;
    }
}
.el-main {
    background-color: #eaedf1;
}
.iconfont {
  margin-right: 10px;
}
.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 23px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>
