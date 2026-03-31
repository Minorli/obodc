# 参与 obodc

本仓库是 fork 自有主线，主仓库地址为 [Minorli/obodc](https://github.com/Minorli/obodc)。
如果你要提 issue、参与讨论或提交代码，请默认以 fork 仓库为准。

## 从哪里开始

- Issues: [Minorli/obodc/issues](https://github.com/Minorli/obodc/issues)
- Discussions: [Minorli/obodc/discussions](https://github.com/Minorli/obodc/discussions)
- Pull requests: [Minorli/obodc/pulls](https://github.com/Minorli/obodc/pulls)
- 开发指南：[DEVELOPER_GUIDE.md](./DEVELOPER_GUIDE.md)

如果你的问题是关于 upstream 跟踪、兼容性判断、或从上游仓库选择性合并，请在 issue 或 PR 中明确写出背景。

## 行为准则

本项目遵循仓库内的 [行为准则](../../CODE_OF_CONDUCT-zh-CN.md)。
如果遇到不可接受的行为，请通过 GitHub issue 或 discussion 联系维护者。

## 仓库结构

```text
apps/           可运行产品和入口模块的规范位置
packages/       共享后端模块和本地库的规范位置
extensions/     插件、starter、module 的规范位置
tests/          集成测试和仓库级测试资产的规范位置
tools/          开发与打包脚本的规范位置
build/          构建配置与打包资产的规范位置
third_party/    vendored source 和本地二进制依赖的规范位置
client/         vendored 前端工作区的兼容别名
distribution/   指向 build/packaging 和 build/output 的兼容别名
script/         指向 tools/scripts 的兼容别名
server/         指向已迁移后端模块的兼容别名
openspec/       fork 自己维护的规格与变更提案
```

## 提交 PR 之前

- 先确认这个改动应该落在 fork，而不是直接回 upstream。
- 先创建或关联 issue。
- 说明本次改动属于哪一类：
  - fork 自己的产品演进
  - 从 upstream 回捞的修复
  - 在 fork 分叉过程中发现的缺陷修复
- 提交评审前先跑相关构建和测试。
- 保持 commit 可读、边界清晰。

## PR 标题格式

使用 conventional commit 风格：

```text
<type>[optional scope]: <description>
```

示例：

```text
feat(metadata): cap oversized metadata browse payloads
fix(sql): avoid blocking metadata refresh on request path
docs(branding): rebrand root surfaces to obodc
```

## 缺陷模板

```markdown
[问题概述]

[环境信息]

- obodc 版本：
- 数据库版本：
- 操作系统：
- 部署方式：

[复现步骤]

[期望结果]

[实际结果]

[相关日志 / 截图]
```

## 评审预期

- 尽量提交边界清晰的 PR，不要把无关改动混在一起。
- 行为变化要写清楚运行风险。
- 如果和 upstream 历史有关，要显式标注。
- 用户可见行为变化要同步更新文档。

## 继承历史说明

仓库里保留了 upstream ODC 的历史记录，但新的贡献流程、命名和主线节奏都以 fork 为准。
