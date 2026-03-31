![obodc](docs/en-US/images/odc-head.png)

中文 | [English](./README.md)

# obodc

`obodc` 是这个 fork 的新主线，主仓库位于 [Minorli/obodc](https://github.com/Minorli/obodc)。
它基于公开的 upstream 代码线启动，但后续会沿着 fork 自己的路线演进，重点解决大规模 OceanBase 场景下的性能、
稳定性、交互和产品体验问题。

## 当前状态

- 当前 fork 版本：`0.1.0-SNAPSHOT`
- 默认分支：`main`
- 上游基线：公开 upstream `v4.3.4_bp2`
- 当前重点：
  - 加固海量元数据场景下的浏览与同步链路
  - 清理继承自上游的品牌和发布痕迹
  - 为后续大规模 UI/UE 改造做准备

## obodc 当前关注的能力

- 适配拥有数百个数据源、超大对象规模的 OceanBase 环境
- 降低启动成本和 MetaDB 压力
- 防止元数据浏览把浏览器回包打爆
- 把仓库和产品外层入口清理成 fork 自有品牌

## 仓库结构

```text
apps/           可运行产品和主入口模块的规范位置
packages/       共享后端模块和本地库的规范位置
extensions/     插件、starter、module 的规范位置
tests/          集成测试和仓库级测试资产的规范位置
tools/          开发与打包脚本的规范位置
build/          构建配置与打包资产的规范位置
third_party/    vendored source 和本地二进制依赖的规范位置
client/         前端 submodule 位置，由 apps/web-client 统一引用
distribution/   指向 build/packaging 和 build/output 的兼容别名
script/         指向 tools/scripts 的兼容别名
server/         指向已迁移后端模块的兼容别名
openspec/       fork 自己维护的规格与变更提案
```

## 快速开始

### 拉取代码

```bash
git clone git@github.com:Minorli/obodc.git
cd obodc
```

### 构建本地依赖

```bash
tools/scripts/build_libs.sh
```

### 构建后端

```bash
./mvnw clean package -DskipTests
```

### 本地启动

先设置 MetaDB 环境变量，再启动服务：

```bash
export DATABASE_HOST=127.0.0.1
export DATABASE_PORT=2881
export DATABASE_NAME=odc_metadb
export DATABASE_USERNAME=odc@test
export DATABASE_PASSWORD='your_password'
export ODC_SERVER_PORT=8989

tools/scripts/nohup-start-odc.sh
```

## 打包与发布

`0.1.0-SNAPSHOT` 仍然是 fork 启动阶段，目前还没有发布 fork 自有的官方镜像和安装包。
当前阶段请以源码构建为主。

- 打包说明：[build/packaging/README.md](build/packaging/README.md)
- 后端开发指南：[docs/zh-CN/DEVELOPER_GUIDE.md](docs/zh-CN/DEVELOPER_GUIDE.md)
- 贡献指南：[docs/zh-CN/CONTRIBUTION.md](docs/zh-CN/CONTRIBUTION.md)
- 上游跟踪策略：[docs/UPSTREAM_TRACKING.md](docs/UPSTREAM_TRACKING.md)

## 路线

fork 第一阶段的目标是：

1. 稳住大规模元数据浏览和同步链路。
2. 重做品牌、界面和交互入口。
3. 面向 OceanBase 重型团队构建更强的 SQL 与运维工作流。
4. 继续选择性合并上游有价值修复，同时沿 fork 的产品方向演进。

## 参与贡献

后续 issue、discussion、pull request 默认都以 fork 仓库为准：

- Issues: [Minorli/obodc/issues](https://github.com/Minorli/obodc/issues)
- Discussions: [Minorli/obodc/discussions](https://github.com/Minorli/obodc/discussions)
- Pull requests: [Minorli/obodc/pulls](https://github.com/Minorli/obodc/pulls)

提交代码前请先阅读：

- [docs/zh-CN/CONTRIBUTION.md](docs/zh-CN/CONTRIBUTION.md)
- [docs/zh-CN/DEVELOPER_GUIDE.md](docs/zh-CN/DEVELOPER_GUIDE.md)

## 继承历史说明

本仓库保留了上游 ODC 的历史发布记录作为参考。fork 自有版本从 `0.1.0-SNAPSHOT` 开始。
更早的 CHANGELOG 条目都属于继承自 upstream 的历史，不代表这些版本由 `obodc` 发布。

## 许可证

本仓库继续使用 [Apache-2.0](https://www.apache.org/licenses/LICENSE-2.0) 许可证。
