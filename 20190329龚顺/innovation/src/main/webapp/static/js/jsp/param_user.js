

var vue = new Vue({
    el: '#app',
    data() {
        return {
            titleFormFlag : false,//自定一个表单是否可以提交，默认不可以
            formInline:{
               /* region:0*/
            },
            tableData: [],
            pageInfo: {},
            outerVisible: false,
            innerVisible: false,
            dialogVisible: false,
            rejectFlag: false,
            tableDataRow: {},//选择中行的数据
            rejectTextarea: '',
        }
    },
    methods: {
        dateFormatter: function (row, column, cellValue, index) {
            var date = row[column.property];
            if (date == undefined) {
                return "";
            }
            return moment(date).format("YYYY-MM-DD HH:mm:ss");
        },
        sexFormatter: function (row, column, cellValue, index) {
            var sex = row[column.property];
            if(sex == 1) {
                return "男";
            } else if (sex == 2) {
                return "女";
            } else {
                return "";
            }
        },
        levelFormatter: function (row, column, cellValue, index) {
            var sex = row[column.property];
            if(sex == 1) {
                return "学生";
            } else if (sex == 2) {
                return "老师";
            } else if (sex == 3) {
                return "专家";
            } else if (sex == 4) {
                return "管理员";
            } else {
                return "";
            }
        },
        handleDetailed(index, row) {
            this.outerVisible = true;//打开模版框
            //获得本行数据,双向绑定
            var data = this.tableData;
            this.tableDataRow = data[index];
        },
        handleDelete(index, row) {
            //打开模版框
            this.dialogVisible = true;
            //获得本行数据,双向绑定
            var data = this.tableData;
            this.tableDataRow = data[index];
            console.log(this.tableDataRow);
        },
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {
                });
        },
        //关于分页
        handleSizeChange:function(val){
            console.log("每页"+val+"条");
            this.pageInfo.pageSize = val;
            this.getUsers();
        },
        handleCurrentChange:function(val){
            console.log("当前页:"+val);
            this.pageInfo.pageNum = val;
            this.getUsers();
        },

        //申请组长、成员一、成员二
        declareDetailed: function (index, row, userLevel) {
            //赋值给选中行数据
            var data = this.tableData;
            this.tableDataRow = data[index];
            console.log(userLevel);
            var jsjson = {};
            jsjson['numSession'] = numSession;
            jsjson['levelSession'] = levelSession;
            jsjson['userLevel'] = userLevel;
            jsjson['projectUser'] = this.tableDataRow;

            axios.post(
                bashPath + '/rest/api/innovation/projectUser/userLevel',
                jsjson
            )
                .then(function (response) {
                    console.log(response);//成功后调用
                    if (response.data.result == '0000') {
                        this.$message(response.data.msg);
                        this.getUsers();
                    } else if (response.data.result == '9999') {
                        this.$message(response.data.msg + '有问题');
                    }
                }.bind(this))
                .catch(function (error) {
                    console.log(error);
                    this.$message('错误信息' + error);
                }.bind(this));
        },

        onSubmitByEx(formName) {
            this.titleFormFlag = true;//这个一般用于表单验证的

            if (this.titleFormFlag) {
                this.getUsers();
            }

        },
        onSubmit(formName) {
            this.titleFormFlag = true;//这个一般用于表单验证的
            var jsjson = {};
            jsjson['numSession'] = numSession;
            jsjson['projectUser'] = this.tableDataRow;

            if (this.titleFormFlag) {
                axios.put(
                    bashPath + '/rest/api/innovation/projectUser/form',
                    jsjson
                )
                    .then(function (response) {
                        console.log(response);//成功后调用
                        if (response.data.result == '0000') {
                            this.$message(response.data.msg);
                            //关闭模版框
                            this.dialogVisible = false;
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
            //这里有点小特殊，并不所有数据需要重置，只重置descInfo，就行了
            // this.$refs[formName].resetFields();
            this.tableDataRow.descInfo = '';
        },

        /////////////////////////////////////非直接事件调用函数///////////////////////////////////////
        getUsers: function () {
            axios.get(
                contextPath + '/rest/api/innovation/user/',
                {
                    params:{
                        level: this.formInline.region,
                        currentPage: this.pageInfo.pageNum,
                        currentSize: this.pageInfo.pageSize
                    }
                }
            )
                .then(function (response) {
                        this.pageInfo = response.data.userPageInfo;
                        this.tableData = response.data.userPageInfo.list;

                }.bind(this))
                .catch(function (error) {
                    console.log(error);
                }.bind(this));
        },
    },
    //在实例创建完成后被立即调用。老师审核通过
    created: function () {
        //首页分页查询
        this.getUsers();
    }
})


