# Contributing to the t-view-parent

首先，感谢您抽出时间来做贡献! 👍 🎉
## 如何贡献

### 功能 
- 如果你想添加一个新的功能请使用这个 [Feature](https://github.com/t-view/all-view/labels/Feature) 标签创建 issues

### Bug
- 如果你发现了一个bug请使用这个 [bug](https://github.com/t-view/all-view/labels/bug) 标签创建 issues

#### bug 登记
- 接口错误

```markdown
- api接口: `/user/login`
- 参数: ``
- 报错信息: 
```

- 函数错误 

```markdown
- 函数名称: `函数名称全路径`
- 参数: ``
```

- 如果你想编码,可以创建具体的测试方法

### 提交 Pull Request

如果你需要提交一个PR你需要做如下操作
1. 总是从 `master` 检出分支,并针对它提交PR
2. 在提交代码之前请执行如下命令, 对代码进行格式化 `mvn googleformatter:format`
3. 如果你想要修复一个bug,你需要检出master分支并命名 issues 的数字编号,进行开发

## Source Code Style
- 本项目采用 [google-java-style](https://google.github.io/styleguide/javaguide.html) 作为代码样式