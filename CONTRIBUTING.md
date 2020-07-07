# Contributing to the t-view-parent

First off, thank you for taking the time to contribute! 👍 🎉
## How to Contribute

### Feature 
- If you want to propose new features, create issues and use this label:[Feature](https://github.com/t-view/all-view/labels/Feature)

### Bug
- If you find a bug , hope you create issues and use this label:[bug](https://github.com/t-view/all-view/labels/bug) 
#### example
- api bug

```markdown
- api: `/user/login`
- params: ``
- error message: 
```

- function bug

```markdown
- function name: `function name full name`
- params: ``
```

- maybe you want coding , you can create concrete JUnit testing. 

### Submit a Pull Request

If you want to submit a PR, you need to do the following
(如果你需要提交一个PR你需要做如下操作)
1. Always check out `master` branch and submit pull request against it.
(总是从master检出分支,并针对它提交PR)
2. Before submitting, please execute the following command to perform formatter. 
    `mvn googleformatter:format`
(在提交代码之前请执行如下命令)
3. If you want fix a bug , you can check out `master` branch and this name is `issues-numb`
   (如果你想要修复一个bug,你需要检出master分支并命名 issues 的数字编号,进行开发)



## Source Code Style
- this project adopts [google-java-style](https://google.github.io/styleguide/javaguide.html)