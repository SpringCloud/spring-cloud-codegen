<template>
  <!-- <div class="gray-bg "> -->
    <div class="SW_footer">
      <div class="ft layout clearfix hidden-xs">
        <div class="slogan"><a href="/"></a></div>
        <div class="ft_item">
          <h4 class="a1">关于我们</h4>
          <ul>
            <li><a href="http://springcloud.cn/?/article/1" target="_blank" rel="nofollow">本站介绍</a></li>
            <li><a href="" target="_blank" rel="nofollow">联系我们</a></li>
            <li><a href="" target="_blank" rel="nofollow">用户协议</a></li>
            <!--<li><a href="http://springcloud.cn/?/feed/">内容订阅</a></li>-->
          </ul>
        </div>
        <div class="ft_item">
          <h4 class="a2">站内导航</h4>
          <ul>
            <li><a href="http://docs.springcloud.cn">社区文档</a></li>
            <li><a href="http://springcloud.cn">社区主站</a></li>
            <li><a href="http://xujin.org">许进博客</a></li>
          </ul>
        </div>
        <div class="ft_item">
          <h4 class="a3">友情链接</h4>
          <ul>
            <li><a href="" target="_blank">社区支持</a></li>
            <li><a href="http://www.itdks.com/" target="_blank">IT大咖说</a></li>
          </ul>
        </div>
        <div class="ft_item">
          <h4 class="a4">常用链接</h4>
          <ul>
            <li><a href="http://xujin.org" target="_blank">许进沉思录</a></li>
            <li><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=2508203324&amp;site=qq&amp;menu=yes"><img border="0" src="http://springcloud.cn/static/css/fox/img/ft_qq.png" alt="联系站长" title="联系站长"></a></li>
          </ul>
        </div>
        <div class="ft_item hidden-xs hidden-sm">
          <h4 class="a5">社区公众号</h4>
          <a href="#" target="_blank"><img src="http://springcloud.cn/static/css/fox/img/gzh.jpg" width="100" height="100" alt="手机访问"></a>
        </div>
      </div>
      <div class="cp"> Copyright (c) 2017, All Rights Reserved Spring Cloud中国社区 版权所有 备案号：滇ICP备14007546号-3
      </div>
      <div class="clearfix hidden-xs hidden-sm" id="trans_news">
        <ul id="contain">
          <li>商标声明:Spring是Pivotal Software,Inc.在美国以及其他国家的商标。</li>

        </ul>
      </div>
    </div>
  <!-- </div> -->
</template>

<script>
  export default {
    components: {},
    name: 'Footer',

    data: function() {
      return{
        select2Style: {//select2-style样式
          width: '100%'
        },
        modules: [],
      }
    },
    created () {

    },
    methods: {
    },
    mounted: function() {

    }
  }
</script>
<style scoped>
  /* (公用)页尾 */

  .SW_footer {
    width: 100%;
    /* height: 100%; */
    /*margin:0 -20px;*/
    /*margin-top: 40px;*/
    /* position: fixed;
    left:0;
    bottom: 0; */
    background-color: #444;
    color: #999;
    font-size: 12px;
    z-index: 100000;
    border-top: 4px solid #6db33f;
  }

  .SW_footer .layout {
    /*width: 100%;*/
    margin: 0 auto;
  }

  .SW_footer .h1_slogan {
    height: 36px;
    line-height: 36px;
    background: #3B3B3B;
    font-size: 14px;
    color: #ccc;
  }

  .SW_footer .h1_slogan span {
    display: block;
    height: 40px;
    width: 960px;
    margin: 0 auto;
    letter-spacing: 2px;
  }

  .ft {
    position: relative;
    padding: 40px 0 30px 0;
    width: 960px;
  }

  .ft .slogan {
    float: left;
    width: 260px;
    height: 61px;
    margin-top: 0px;
  }

  .ft .slogan a {
    display: inline-block;
    width: 230px;
    height: 84px;
    background: url(../assets/sw-footer-sprite1.png) no-repeat 0 0;
  }

  .ft .ft_item {
    float: left;
    width: 140px;
    line-height: 22px;
  }

  .ft .ft_item a {
    color: #999;
    padding: 2px 10px 2px 5px;
  }

  .ft .ft_item a:hover {
    background: #666;
    color: #222;
    border-radius: 10px 0;
  }

  .ft h4 {
    height: 25px;
    width: 80px;
    text-indent: -9999px;
    margin-bottom: 10px;
    /*background: url(../../assets/sw-footer-sprite.png) no-repeat;*/
  }

  .ft .a1 {
    background-position: 0 -75px;
  }

  .ft .a2 {
    background-position: -97px -75px;
  }

  .ft .a3 {
    background-position: -198px -75px;
  }

  .ft .a4 {
    background-position: 0 -105px;
  }

  .ft .a5 {
    background-position: -97px -105px;
  }

  .SW_footer .cp {
    width: 100%;
    max-width: 850px;
    margin: 0 auto;
    padding-bottom: 20px;
    text-align: center;
  }

  .SW_footer .beian {
    width: 100%;
    max-width: 850px;
    margin: 0 auto;
    padding-bottom: 20px;
    text-align: center;
  }

  .sw-mn {
    color: #bbb;
    background-color: #2c2c2c;
    padding: 0 10px;
    position: fixed;
    margin-top: -30px;
    width: 100%;
    z-index: 9999;
  }

  .sw-mn,
  .sw-mn-bd,
  .sw-mn-l,
  .sw-mn-r {
    height: 30px;
  }

  .sw-mn li {
    float: left;
    height: 30px;
    line-height: 30px;
  }

  .sw-mn li em,
  .sw-mn li span {
    color: #fff;
    padding: 0 5px;
    margin-left: 3px;
    border-radius: 3px;
  }

  .sw-mn li em {
    border: 1px solid #bbb;
  }

  .sw-mn li img {
    width: 30px;
    height: 30px;
    vertical-align: top;
  }

  .sw-mn a,
  .sw-mn a:visited {
    display: inline-block;
    float: left;
    padding: 0 12px;
    color: #bbb;
    text-decoration: none;
  }

  .sw-mn a:hover,
  .sw-mn a.active {
    color: #fff;
    background-color: #1d1d1d;
    text-decoration: none;
  }

  .cp a {
    color: #999;
    text-decoration: none;
  }

  a {
    cursor: pointer;
    color: #393A44;
  }

  a:hover {
    text-decoration: none;
    color: #6D89BB;
  }

  a:focus {
    outline: none !important;
  }

  #trans_news {}

  #contain {
    font-size: 14px;
    overflow: hidden;
    list-style: none;
    height: 30px;
    margin: 0px auto;
    color: #999;
    text-shadow: 1px 1px 0 #111;
    text-align: center;
    background: #333;
    border-top: 1px solid #222;
  }

  #contain li {
    height: 30px;
    line-height: 30px;
    white-space: nowrap;
    overflow: hidden;
    letter-spacing: 2px;
  }
</style>
