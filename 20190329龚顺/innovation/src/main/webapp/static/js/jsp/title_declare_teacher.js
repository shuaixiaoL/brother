new Vue({
    el: '#app',
    data() {
        return {
            importFileUrl: contextPath + '/rest/api/innovation/project/upload/',
            filePath:'#',
            fileName:'',
            titleFormFlag : false,//自定一个表单是否可以提交，默认不可以
            titleForm: {
                name: '',
                teacherNum: '',
                descInfo: '',
                progressStatus: 1,
                applyStatus: 2
            },
            rules: {
                name: [
                    {required: true, message: '请输入题目名称', trigger: 'change'},
                    {min: 0, max: 40, message: '长度在40个字符', trigger: 'blur'}
                ],
                teacherNum: [
                    {required: true, message: '请选择教师工号', trigger: 'change'},
                    {min: 0, max: 40, message: '长度在40个字符', trigger: 'blur'}
                ],
                descInfo: [
                    {required: true, message: '请详细填写', trigger: 'change'},
                    {min: 0, max: 40, message: '长度在40个字符', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        onSubmit(formName) {
            console.log('增加!');
            console.log(this.titleForm);
            if (this.titleFormFlag) {
                axios.post(
                    bashPath + '/rest/api/innovation/projectUser',
                    this.titleForm
                )
                    .then(function (response) {
                        console.log(response);//成功后调用
                        this.$message('提交成功');
                        this.$refs[formName].resetFields();
                    }.bind(this))
                    .catch(function (error) {
                        console.log(error);
                        this.$message('有错');
                    }.bind(this));
            }

        },
        //重置按钮
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        //判断input框checkTeacherNum教师编号
        checkTeacherNum(numId) {
            console.log(numId)
            console.log('判断老师编号!' + this.titleForm.teacherNum);
            if (this.titleForm.teacherNum) {
                axios.get(
                    bashPath + '/rest/api/innovation/user/numlevel/', {
                        params: {
                            num: this.titleForm.teacherNum,
                            level:2
                        }
                    }
                )
                    .then(function (response) {
                        console.log(response);//成功后调用
                        if (response.data.result == '0000') {
                            $("#"+ numId +"").css({"color" : "blue"})
                            $("#"+ numId +"").html("教师真实姓名:" + response.data.data.realName);
                            this.titleFormFlag = true;
                        } else if (response.data.result == '9999') {
                            $("#"+ numId +"").css({"color" : "red"})
                            $("#"+ numId +"").html("该教师工号不存在");
                        }
                    }.bind(this))
                    .catch(function (error) {
                        console.log(error);//成功后调用
                    }.bind(this));
            }
        },
        handleSuccess: function(res,file,fileList){
            var self = this;
            if(res.result == '0000'){
                self.$message({
                    message: '上传成功！',
                    type: 'success'
                });
                self.filePath = res.data;
                self.fileName = file.name;
                self.titleForm.attachdoc = res.data;
            } else if(res.result == '9999') {
                self.$alert('', '上传失败', {
                    confirmButtonText: '确定'
                });

            }

        },
        //这里可以弄执行下载操作，异步请求请规避超链接
        onClickHref: function () {
            window.open(this.filePath);
        }
    }
})


