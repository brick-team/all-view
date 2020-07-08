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
1. Always check out `master` branch and submit pull request it.
2. Before submitting, please execute the following command to perform formatter. 
    `mvn googleformatter:format`
3. If you want fix a bug , you can check out `master` branch and this name is `issues-numb`



## Source Code Style
- this project adopts [google-java-style](https://google.github.io/styleguide/javaguide.html)