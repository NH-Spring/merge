import Vue from 'vue'
import { Button, Card, Tooltip } from 'element-ui'
import { Form, FormItem } from 'element-ui'
import { Input } from 'element-ui'
import { Message, container, Main, Header, Aside, Menu,
Submenu, MenuItemGroup, MenuItem, breadcrumb, BreadcrumbItem, 
Row, Col, Table, TableColumn, Switch, Pagination, Dialog, MessageBox, 
Tag,Tree, Select, Option, Cascader,Alert,Steps,Step,Tabs,TabPane,Upload
} from "element-ui"

Vue.use(Button)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(container)
Vue.use(Main)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItemGroup)
Vue.use(MenuItem)
Vue.use(breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Card)
Vue.use(Row)
Vue.use(Col)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Switch)
Vue.use(Tooltip)
Vue.use(Pagination)
Vue.use(Dialog)
Vue.use(Tag)
Vue.use(Tree)
Vue.use(Select)
Vue.use(Option)
Vue.use(Cascader)
Vue.use(Alert)
Vue.use(Steps)
Vue.use(Step)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Upload)
Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm