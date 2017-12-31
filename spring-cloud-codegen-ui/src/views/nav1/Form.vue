<template>
  <div class="wrapper wrapper-content animated fadeInRight">
      <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
          <div class="ibox float-e-margins">
            <div class="ibox-content">
              <form method="POST" id="genform" class="form-horizontal" action="/api/v2/downloadResponse" enctype="text/plain">
                <div class="module-item" v-for="(module,number) in modules" :key="number">
                  <transition name="fade">
                    <!--TODO 此处样式需要修改一下-->
                    <!--class="hr-line-dashed"-->
                    <div v-if="hrShow[module.key]">{{ module.label }}</div>
                  </transition>

                  <transition name="fade" v-if="module.type == 'MIX_GROUP'" v-for="(item,index) in module.entityList">
                    <div class="form-group">
                      <label v-if="item.type != 'RADIO' && item.type != 'CHECKBOX'" class="col-sm-2 control-label">{{ item.label }}</label>
                      <div class="col-sm-8" v-if="item.type != 'RADIO' && item.type != 'CHECKBOX'">
                        <input v-if="item.type == 'TEXTFIELD'" class="form-control" :name="item.key" :value="item.value" required>
                        <span v-if="item.highlightable && item.type == 'TEXTFIELD'" class="must-need">*</span>
                        <span v-if="item.note != '' && item.note != null && item.type == 'TEXTFIELD'" class="info-tip">
                          <el-tooltip class="item" effect="dark" placement="right">
                            <div slot="content" class="content-box">{{ item.note }}</div>
                            <el-button class="el-icon-question"></el-button>
                          </el-tooltip>
                        </span>
                        <el-select v-if="item.type == 'COMBOBOX'" v-model="modules[number].entityList[index].value" :name="item.key" :select2Style="select2Style" :placeholder="item.label">
                          <el-option v-if="item.type == 'COMBOBOX'" v-for="option in item.options" :key="number" :label="option" :value="option"></el-option>
                        </el-select>
                      </div>
                      <div class="col-sm-10" v-if="item.type == 'CHECKBOX'">
                        <label class="col-sm-2 control-label" style="margin-top: -7px;">{{ item.label }}</label>
                        <div  :class="'col-sm-2'">
                          <el-checkbox  :label="!item.value" :name="item.key" style="color:#666"></el-checkbox>
                          <span v-if="!item.defaultable" class="recommend">（推荐）</span>
                        </div>
                      </div>
                    </div>
                  </transition>
                  <transition name="fade" v-else-if="module.type == 'COMBOBOX_GROUP' && index == 1">
                    <div class="form-group">
                      <h3 align="center" class="col-sm-11">Generate a
                        <span class="good-span">
                          <el-select v-model="modules[number].entityList[0].value" :name="module.entityList[0].key">
                            <el-option v-for="project in module.entityList[0].options"  :label="project" :value="project"></el-option>
                          </el-select>
                        </span>
                        with
                        <span class="good-span">
                          <el-select v-model="modules[number].entityList[1].value" :name="module.entityList[1].key">
                            <el-option v-for="project2 in module.entityList[1].options"  :label="project2" :value="project2"></el-option>
                          </el-select>
                        </span>
                        and Spring Boot
                        <span class="good-span">
                          <el-select v-model="modules[number].entityList[2].value" :name="module.entityList[2].key">
                            <el-option v-for="project3 in module.entityList[2].options"  :label="project3" :value="project3"></el-option>
                          </el-select>
                        </span>
                      </h3>
                    </div>
                  </transition>
                  <transition name="fade" v-else-if="module.type == 'RADIO_GROUP' && index == 1 && module.key == 'sc-alone' && showScAlone">
                    <div class="form-group">
                      <label class="col-sm-2 control-label" v-if="index == 1">{{ module.label }}</label>
                      <div class="col-sm-10">
                        <div v-for="(radio,rnum) in module.entityList" class="col-sm-2" v-if="index == 1">
                          <el-radio  :label="radio.key" v-model="scType" :name="module.key">{{ radio.label }}</el-radio>
                        </div>
                      </div>
                    </div>
                  </transition>
                  <transition name="fade" v-else-if="module.type == 'RADIO_GROUP' && index == 1 && module.key == 'sc-alone-radio' && showScAloneRadio">
                    <div class="form-group">
                      <label class="col-sm-2 control-label" v-if="index == 1">{{ module.label }}</label>
                      <div class="col-sm-10">
                        <div v-for="(radio,rnum) in module.entityList" class="col-sm-2" v-if="index == 1">
                          <el-radio  :label="radio.key" v-model="item.value" :name="module.key">{{ radio.label }}</el-radio>
                        </div>
                      </div>
                    </div>
                  </transition>
                  <transition name="fade" v-else-if="module.type == 'RADIO_GROUP' && index == 1 && module.key != 'sc-alone' && module.key != 'sc-alone-radio' ">
                    <div class="form-group" >
                      <label class="col-sm-2 control-label" v-if="index == 1">{{ module.label }}</label>
                      <div class="col-sm-10">
                        <div v-for="(radio,rnum) in module.entityList" class="col-sm-2" v-if="index == 1">
                          <el-radio  :label="radio.key" v-model="applicationType" :name="module.key">{{ radio.label }}</el-radio>
                        </div>
                      </div>
                    </div>
                  </transition>
                  <transition name="fade" v-else-if="module.type == 'CHECKBOX_GROUP' && index == 1 && module.key == 'sc-group-checkBox' && showScGroupCheckBox">
                    <div class="form-group">
                      <label class="col-sm-2 control-label" v-if="index == 1" style="margin-top: -7px;">{{ module.label }}</label>
                      <div class="col-sm-10" v-if="index == 1">
                        <div v-for="(chkbox,cnum) in module.entityList" :class="'col-sm-2'">
                          <el-checkbox  :label="modules[number].entityList[cnum].value" :name="chkbox.key" style="color:#666">{{ chkbox.label }}</el-checkbox>
                          <span v-if="chkbox.defaultable" class="recommend">（推荐）</span>
                        </div>
                      </div>
                    </div>
                  </transition>
                  <transition name="fade" v-else-if="module.type == 'CHECKBOX_GROUP' && index ==1 && module.key == 'basic-framework' && showBasicFramework">
                    <div class="form-group">
                      <label class="col-sm-2 control-label" v-if="index == 1" style="margin-top: -7px;">{{ module.label }}</label>
                      <div class="col-sm-10" v-if="index == 1">
                        <div v-for="(chkbox,cnum) in module.entityList" :class="'col-sm-2'">
                          <el-checkbox  :label="modules[number].entityList[cnum].value" :name="chkbox.key" style="color:#666">{{ chkbox.label }}</el-checkbox>
                          <span v-if="chkbox.defaultable" class="recommend">（推荐）</span>
                        </div>
                      </div>
                    </div>
                  </transition>
                  <transition name="fade" v-else-if="module.type == 'CHECKBOX_GROUP' && index == 1 && module.key != 'sc-group-checkBox' && module.key != 'basic-framework'">
                    <div class="form-group">
                      <label class="col-sm-2 control-label" v-if="index == 1" style="margin-top: -7px;">{{ module.label }}</label>
                      <div class="col-sm-10" v-if="index == 1">
                        <div v-for="(chkbox,cnum) in module.entityList" :class="'col-sm-2'">
                          <el-checkbox  :label="modules[number].entityList[cnum].value" :name="chkbox.key" style="color:#666">{{ chkbox.label }}</el-checkbox>
                          <span v-if="chkbox.defaultable" class="recommend">（推荐）</span>
                        </div>
                      </div>
                    </div>
                  </transition>


                </div>
                <div class="form-group" style="text-align:center;">
                  <div class="col-sm-12">
                    <a class="btn btn-default" v-on:click="refresh()">重置</a>
                    <button style="background-color: #6db33f;border-color: #1ab394;color: #FFFFFF;" class="btn" type="submit">生成工程</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>

  import axios from 'axios'
  import $ from '@/../static/js/jquery'
  import ElSwitch from "../../../node_modules/element-ui/packages/switch/src/component.vue";
  import ElRadio from "../../../node_modules/element-ui/packages/radio/src/radio.vue";
  import N3Radio from "../../../node_modules/N3-components/src/Radio/n3Radio.vue";
  import ElCheckboxGroup from "../../../node_modules/element-ui/packages/checkbox/src/checkbox-group.vue";
  import ElCheckbox from "../../../node_modules/element-ui/packages/checkbox/src/checkbox.vue";
  import ElOption from "../../../node_modules/element-ui/packages/select/src/option.vue";
  export default {
    components: {
      ElOption,
      ElCheckbox,
      ElCheckboxGroup,
      N3Radio,
      ElRadio,
      ElSwitch},
    name: 'survey',

    data: function() {
      return{
        select2Style: {//select2-style样式
          width: '100%'
        },
        modules: [],
        applicationType: '', //应用类型
        scType: '',//springcloud组件类型
        rqEntity: {
          'springcloud': 0,
          'alone': 0
        },
        hrShow: {},
        showScAlone: false,
        showScAloneRadio: false,
        showScGroupCheckBox: false,
        showBasicFramework: false,
      }
    },
    created () {
      this.fetchData()
    },
    watch: {
      applicationType: function (choice) {
        if (choice == 'springcloud') {
          //显示springCloud
          this.showScAlone = true;
          this.hrShow['sc-alone'] = true;

          this.showScAloneRadio = false;
          this.hrShow['sc-alone-radio'] = false;

          this.showScGroupCheckBox = false;
          this.hrShow['sc-group-checkBox'] = false;

          this.showBasicFramework = false;
          this.hrShow['basic-framework'] = false;
        } else if(choice == 'springboot') {
          //显示基础框架
          this.showScAlone = false;
          this.hrShow['sc-alone'] = false;

          this.showScAloneRadio = false;
          this.hrShow['sc-alone-radio'] = false;

          this.showScGroupCheckBox = false;
          this.hrShow['sc-group-checkBox'] = false;

          this.showBasicFramework = true;
          this.hrShow['basic-framework'] = true;
        } else {
          //隐藏springCloud
          this.showScAlone = false;
          this.hrShow['sc-alone'] = false;

          this.showScAloneRadio = false;
          this.hrShow['sc-alone-radio'] = false;

          this.showScGroupCheckBox = false;
          this.hrShow['sc-group-checkBox'] = false;

          this.showBasicFramework = false;
          this.hrShow['basic-framework'] = false;
        }
      },
      scType: function (choice) {
        if (choice == 'alone') {
          //独立组件
          this.showScAloneRadio = true;
          this.hrShow['sc-alone-radio'] = true;

          this.showScGroupCheckBox = false;
          this.hrShow['sc-group-checkBox'] = false;

          this.showBasicFramework = false;
          this.hrShow['basic-framework'] = false;
        } else {
          //组合组件
          this.showScAloneRadio = false;
          this.hrShow['sc-alone-radio'] = false;

          this.showScGroupCheckBox = true;
          this.hrShow['sc-group-checkBox'] = true;

          this.showBasicFramework = true;
          this.hrShow['basic-framework'] = true;
        }
      }
    },
    methods: {

      fetchData: async function () {
        this.getInitJq('getMetaData');
      },

      getInitJq : function (url) {
        var vue = this;
        return $.ajax({
          type: 'GET',
          url: '/api/v2/' + url,
          dataType: 'json',
          success: function (data) {
            vue.builtModules(data);
            vue.hideHr();
          }
        });
      },
      builtModules: function (data) {
        this.modules = [];
        var i = 0;
        for (i in data) {
          var tmp = {};
          tmp.key = data[i].key;
          tmp.label = data[i].label;
          tmp.description = data[i].description;
          tmp.column = data[i].column;
          tmp.type = data[i].type;
          tmp.entityList = this.chkEntityList(data[i].entityList);
          tmp.values = [];
          this.modules.push(tmp);
          this.hrShow[tmp.key] = true;
        }
      },
      hideHr: function () {
        this.hrShow['sc-alone'] = false;
        this.hrShow['sc-alone-radio'] = false;
        this.hrShow['sc-group-checkBox'] = false;
        this.hrShow['basic-framework'] = false;
      },
      chkEntityList: function (list) {
        if (list[0].type == 'CHECKBOX') {
          for (var i in list) {
            list[i].value = true;
          }
        }
        if (list[0].type == 'COMBOBOX') {
          for (var i in list) {
            if (list[i].value == null) {
              list[i].value = list[i].options[0];
            }
          }
        }
        return list;
      },
      refresh: function () {
//        document.getElementById("genform").reset();
//        window.location.reload();
        this.getInitJq('getMetaData');
      }

    },
    mounted: function() {

    }
  }
</script>
<style scoped>
  /*.gray-bg {*/
    /*margin-bottom: 300px;*/
  /*}*/
  .hr-line-dashed{border-top:1px dashed #e7eaec;color:#fff;background-color:#fff;height:1px;margin:20px 0}
  .good-span {
    display: inline-block;
    width: 100px;
  }
  .info-tip button {
    font-size: 18px;
    color: #6DB345;
    position: absolute;
    top: -6px;
    right: -32px;
    border:none;
    width:32px;
    height:37px;
  }

  .content-box {
    max-width:300px !important;
  }

  .info-tip button:focus, .info-tip button:hover {
    background-color: white ! important;
  }
  .recommend {
    color:#1AB387;
    padding-left: 15px;
    font-size: 8pt;
  }
  .must-need{
    color:red;
    font-size:15pt;
    display: block;
    position: absolute;
    top:7px;
    right:0px;
  }
  .el-radio{
    color:#666;
    font-size:12px;
    margin-top:7px;
  }
  .style{
    margin-left: 20px;
    top: 7px;
    position: relative;
  }
  .col-sm-4{
    padding-left: 0px;
  }
   div {
     display: block;
   }

  a {
    text-decoration: none;
  }

  ul,
  ol {
    margin: 0;
    padding: 0;
    list-style: none;
  }

</style>
