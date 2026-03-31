# obodc 后端开发指南

本文说明 fork 主线 `obodc` 的后端开发方式。默认以本仓库为准：

- 仓库地址：[https://github.com/Minorli/obodc](https://github.com/Minorli/obodc)
- 默认分支：`main`
- 当前版本线：`0.1.0-SNAPSHOT`

## 1. 拉取代码

```bash
git clone git@github.com:Minorli/obodc.git
cd obodc
```

## 2. 开发工具链

- JDK 8
- 仓库根目录自带的 Maven Wrapper
- 只有涉及前端时才需要 Node.js
- Java 开发推荐使用 IntelliJ IDEA

## 3. 构建依赖

项目仍然依赖少量存放在 `third_party/binaries/` 目录下、需要本地安装的二进制依赖，先执行：

```bash
tools/scripts/build_libs.sh
```

如果需要本地构建前端资源，再执行：

```bash
tools/scripts/init_node_env.sh
tools/scripts/update_submodule.sh
tools/scripts/build_sqlconsole.sh
```

## 4. 后端构建

```bash
./mvnw clean package
```

更快的日常循环可以直接针对后端模块：

```bash
./mvnw -pl server/odc-service -am test
```

## 5. 本地启动

启动前先设置 MetaDB 连接信息：

```bash
export DATABASE_HOST=127.0.0.1
export DATABASE_PORT=2881
export DATABASE_NAME=odc_metadb
export DATABASE_USERNAME=odc@test
export DATABASE_PASSWORD='your_password'
export ODC_SERVER_PORT=8989
```

启动后端：

```bash
tools/scripts/nohup-start-odc.sh
```

## 6. 测试

部分测试依赖真实数据库。请把本地测试密钥放在版本库之外。

- 需要时可在仓库根目录创建 `local-unit-test.properties`
- 解密密钥放在本地环境变量或 `.env`
- 不要提交明文密码或本地密钥

常用命令：

```bash
./mvnw test
./mvnw -pl server/odc-service -am -Dtest=SomeTest test
```

## 7. 代码格式与 IDE

- 仓库格式化规则现在位于 `build/config/code-style/`
- `.idea/codeStyle` 可以作为 IntelliJ IDEA 的默认基线
- 如果 IDE 需要，请安装 Lombok 支持

## 8. Fork 开发流程

这个 fork 会选择性吸收 upstream 的修复，但新的产品工作默认先落在 fork。

- upstream 跟踪策略见 [docs/UPSTREAM_TRACKING.md](../../docs/UPSTREAM_TRACKING.md)
- 重要产品或架构变更放进 `openspec/`
- 运行时兼容性相关的内部标识暂时保持不变，后续再单独迁移

## 9. 打包

打包说明见 [build/packaging/README.md](../../build/packaging/README.md)。
当前 `0.1.0-SNAPSHOT` 阶段以源码构建产物为主。
