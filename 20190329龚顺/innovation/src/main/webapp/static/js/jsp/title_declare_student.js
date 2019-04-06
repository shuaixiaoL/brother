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
                compareNum: '',
                teacherNum: '',
                memberOneNum: '',
                memberTwoNum: '',
                descInfo: '',
                attachdoc:'',
                progressStatus: 1,
                applyStatus: 1
            },
            rules: {
                name: [
                    {required: true, message: '请输入活动名称', trigger: 'blur'},
                    {min: 0, max: 40, message: '长度在40个字符', trigger: 'blur'}
                ],
                compareNum: [
                    {required: true, message: '请选择活动区域', trigger: 'blur'},
                    {min: 0, max: 40, message: '长度在40个字符', trigger: 'blur'}
                ],
                teacherNum: [
                    {required: true, message: '请选择活动区域', trigger: 'change'},
                    {min: 0, max: 40, message: '长度在40个字符', trigger: 'blur'}
                ],
                memberOneNum: [
                    {required: true, message: '请选择活动区域', trigger: 'change'},
                    {min: 0, max: 40, message: '长度在40个字符', trigger: 'blur'}
                ],
                memberTwoNum: [
                    {required: true, message: '请选择活动区域', trigger: 'change'},
                    {min: 0, max: 40, message: '长度在40个字符', trigger: 'blur'}
                ],
                descInfo: [
                    {required: true, message: '请选择活动区域', trigger: 'change'},
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
                    bashPath + '/rest/api/innovation/projectUser/',
                    this.titleForm
                )
                    .then(function (response) {
                        console.log(response);//成功后调用
                        if (response.data.result == '0000') {
                            this.$message(response.data.msg);
                            this.$refs[formName].resetFields();
                        } else if (response.data.result == '9999') {
                            this.$message(response.data.msg + '有问题');
                        }
                    }.bind(this))
                    .catch(function (error) {
                        console.log(error);
                        this.$message('错误信息' + error);
                    }.bind(this));
            }

        },
        //重置按钮
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        //判断input框checkTeacherNum教师编号
        checkNum(numId) {
            console.log(numId)
            var numTemp = this.titleForm[numId.split('_')[1]];

            if (numTemp) {
               //判断成员编号，如果不是7或12，就不发请求
                if (numTemp.length == 7 || numTemp.length == 12) {//存在
                    var levelTemp = numId.split('_')[1];//获得选择框确定用户身份
                    var level = 1;
                    if(levelTemp.search('teacher') != -1) {
                        level = 2;
                    } else if(levelTemp.search('examine') != -1) {
                        level = 3;
                    } else {
                        level = 1;
                    }
                    axios.get(
                        bashPath + '/rest/api/innovation/user/numlevel/', {
                            params: {
                                num: this.titleForm[numId.split('_')[1]],
                                level:level
                            }
                        }
                    )
                        .then(function (response) {
                            console.log(response);//成功后调用
                            let data = response.data;
                            if (data.result == '0000') {
                                $("#" + numId + "").css({"color": "green"})
                                $("#" + numId + "").html("真实姓名:" + data.data.realName);
                                this.titleFormFlag = true;
                            } else if (response.data.result == '9999') {
                                $("#" + numId + "").css({"color": "red"})
                                $("#" + numId + "").html(data.msg);
                            }
                        }.bind(this))
                        .catch(function (error) {
                            console.log(error);//成功后调用
                        }.bind(this));
                } else {
                    $("#" + numId + "").css({"color": "red"})
                    $("#" + numId + "").html("不存在");
                }
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


