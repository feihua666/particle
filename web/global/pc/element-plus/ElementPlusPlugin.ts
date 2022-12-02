import "./elementPlusCssOverride.css"
import BackendManagementLayout from './backend/BackendManagementLayout.vue'
import Button from './Button.vue'
import ButtonGroup from './ButtonGroup.vue'
import Cascader from './Cascader.vue'
import CheckboxGroup from './CheckboxGroup.vue'
import RadioGroup from './RadioGroup.vue'
import Select from './Select.vue'
import Upload from './Upload.vue'
import UploadAvatar from './UploadAvatar.vue'
import UploadSingleImage from './UploadSingleImage.vue'
import Input from './Input.vue'
import InputNumber from './InputNumber.vue'
import Switch from './Switch.vue'
import Autocomplete from './Autocomplete.vue'
import Checkbox from './Checkbox.vue'
import ColorPicker from './ColorPicker.vue'
import DatePicker from './DatePicker.vue'
import TimePicker from './TimePicker.vue'
import TimeSelect from './TimeSelect.vue'
import Menu from './Menu.vue'
import SubMenu from './SubMenu.vue'
import MenuItem from './MenuItem.vue'
import MenuItemGroup from './MenuItemGroup.vue'
import RouteViewDrawer from './RouteViewDrawer.vue'
import Pagination from './Pagination.vue'
import Table from './Table.vue'
import TableColumn from './TableColumn.vue'
import Carousel from './Carousel.vue'
import Image from './Image.vue'
import FormItemDetail from './FormItemDetail.vue'
import FormItem from './FormItem.vue'
import Form from './Form.vue'
import * as ElStyleTools from './ElStyleTools'

let prefix = 'Pt'
let map = {
    BackendManagementLayout,
    Button,
    ButtonGroup,
    Cascader,
    CheckboxGroup,
    RadioGroup,
    Select,
    Upload,
    UploadAvatar,
    UploadSingleImage,
    Input,
    InputNumber,
    Switch,
    Autocomplete,
    Checkbox,
    ColorPicker,
    DatePicker,
    TimePicker,
    TimeSelect,
    Menu,
    SubMenu,
    MenuItem,
    MenuItemGroup,
    RouteViewDrawer,
    Pagination,
    Table,
    TableColumn,
    Carousel,
    Image,
    FormItemDetail,
    FormItem,
    Form,
}
export default {
    install: function (app, options) {
        // 添加实例方法
        for (let mapKey in map) {
            app.component(prefix + mapKey,map[mapKey])
        }
        app.config.globalProperties.ptElStyleTools = ElStyleTools
    }
}