# spring-cloud-codegen-ui

> ui source for codegen

**demo**: [http://start.springcloud.cn](http://start.springcloud.cn)

# To start

This is a project template for [vue-cli](https://github.com/vuejs/vue-cli)

``` bash
# install dependencies
npm install
```

### serve with hot reload at localhost:7777
```shell
# by api: http://127.0.0.1:3333
npm run dev
```
配置Hosts:
127.0.0.1 localhost

### serve with hot reload at localhost:8888   
```shell
# by api: http://codegen.smartscity.com/api/v1/swagger-ui.html
npm run remote
```

### serve with hot reload at localhost:9999   
```shell 
# by api: http://codegen.smartscity.com/api/v2/swagger-ui.html
#edit v1 to v2
vim src/views/nav1/Form.vue
  replaceAll /v1/ to /v2/
npm run v2
```


### build for production with minification
```shell
npm run build

```

