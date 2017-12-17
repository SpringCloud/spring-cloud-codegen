<template>
  <div class="wrapper wrapper-content animated fadeInRight">
      <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
          <div class="ibox float-e-margins">
            <div class="ibox-content">
              <form method="POST" class="form-horizontal" action="/v1/downloadResponse" enctype="text/plain">
                <div class="module-item" v-for="(module,number) in modules" :key="number">
                  <div class="form-group" v-if="module.type == 'MIX_GROUP'" v-for="(item,index) in module.entityList">
                    <label v-if="item.type != 'RADIO' && item.type != 'CHECKBOX'" class="col-sm-2 control-label">{{ item.label }}</label>
                    <div class="col-sm-8" v-if="item.type != 'RADIO' && item.type != 'CHECKBOX'">
                      <input v-if="item.type == 'TEXTFIELD'" class="form-control" :name="item.key" :value="item.value" required>
                      <span v-if="item.highlightable && item.type == 'TEXTFIELD'" class="must-need">*</span>
                      <span v-if="item.note != null && item.type == 'TEXTFIELD'" class="info-tip">
                        <el-tooltip class="item" effect="dark" placement="right">
                          <div slot="content" class="content-box">{{ item.note }}</div>
                          <el-button class="el-icon-question"></el-button>
                        </el-tooltip>
                      </span>
                      <el-select v-if="item.type == 'COMBOBOX'" v-model="modules[number].entityList[index].value" :name="item.key" :select2Style="select2Style" :placeholder="item.label">
                        <el-option v-if="item.type == 'COMBOBOX'" v-for="option in item.options" :key="number" :label="option" :value="option"></el-option>
                      </el-select>
                    </div>
                  </div>
                  <div class="form-group" v-else-if="module.type == 'RADIO_GROUP' && index == 1">
                    <label class="col-sm-2 control-label" v-if="index == 1">{{ module.label }}</label>
                    <div class="col-sm-10">
                      <div v-for="(radio,rnum) in module.entityList" class="col-sm-2" v-if="index == 1">
                        <el-radio  :label="radio.key" v-model="item.value" :name="module.key" >{{ radio.label }}</el-radio>
                      </div>
                    </div>
                  </div>
                  <div class="form-group" v-else-if="module.type == 'CHECKBOX_GROUP' && index == 1">
                    <label class="col-sm-2 control-label" v-if="index == 1" style="margin-top: -7px;">{{ module.label }}</label>
                    <div class="col-sm-10" v-if="index == 1">
                        <div v-for="(chkbox,cnum) in module.entityList" :class="'col-sm-2'">
                          <el-checkbox  :label="modules[number].entityList[cnum].value" :name="chkbox.key" style="color:#666">{{ chkbox.label }}</el-checkbox>
                          <span v-if="chkbox.defaultable" class="recommend">（推荐）</span>
                        </div>
                    </div>
                  </div>
                  <hr>
                </div>
                <div class="hr-line-dashed"></div>
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
  export default {
    components: {
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
      }
    },
    created () {
      this.fetchData()
    },
    methods: {
      fetchData: async function () {
        this.getInitJq('getMetaData');
      },

      getInitJq : function (url) {
        var vue = this;
        return $.ajax({
          type: 'GET',
          url: '/v1/' + url,
          dataType: 'json',
          success: function (data) {
            vue.builtModules(data);
          }
        });
      },
      builtModules: function (data) {
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
        }
      },
      chkEntityList: function (list) {
        if (list[0].type == 'CHECKBOX') {
          for (var i in list) {
            list[i].value = true;
          }
        }
        return list;
      },
      refresh: function () {
        window.location.reload();
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
