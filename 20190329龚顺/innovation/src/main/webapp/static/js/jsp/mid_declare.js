

var vue = new Vue({
    el: '#app',
    data() {
        return {
            pageContentUrl: {
                selectProjectUserUrl: contextPath +  "/rest/api/innovation/projectUser/progressapply/",
                midSubmitUrl: contextPath +  "/rest/api/innovation/projectUser/form/",
            },
            importFileUrl: contextPath + '/rest/api/innovation/project/upload/',
            titleFormFlag : false,//自定一个表单是否可以提交，默认不可以
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
            return moment(date).format("YYYY-MM-DD");
        },
        handleDetailed(index, row) {
            this.outerVisible = true;//打开模版框
            //获得本行数据,双向绑定
            var data = this.tableData;
            this.tableDataRow = data[index];
            if(data[index]['sqTime']) {
                this.tableDataRow.sqTime = moment(data[index]['sqTime']).format("YYYY-MM-DD");
            }
        },
        handleDelete(index, row) {
            //打开模版框
            this.dialogVisible = true;
            //获得本行数据,双向绑定
            var data = this.tableData;
            this.tableDataRow = data[index];
            if(data[index]['sqTime']) {
                this.tableDataRow.sqTime = moment(data[index]['sqTime']).format("YYYY-MM-DD");
            }
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
            this.selectProjectUser();
        },
        handleCurrentChange:function(val){
            console.log("当前页:"+val);
            this.pageInfo.pageNum = val;
            this.selectProjectUser();
        },

        onSubmit(formName) {
            this.titleFormFlag = true;//这个一般用于表单验证的

            if (this.titleFormFlag) {
                axios.put(
                    this.pageContentUrl.midSubmitUrl,
                    this.tableDataRow
                )
                    .then(function (response) {
                        console.log(response);//成功后调用
                        if (response.data.result == '0000') {
                            this.$message(response.data.msg);
                            //关闭模版框
                            this.dialogVisible = false;
                            this.selectProjectUser();
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

        handleSuccess: function(res,file,fileList){
            var self = this;
            if(res.result == '0000'){
                self.$message({
                    message: '上传成功！',
                    type: 'success'
                });
                self.tableDataRow.attachdoc = res.data;
            } else if(res.result == '9999') {
                self.$alert('', '上传失败', {
                    confirmButtonText: '确定'
                });
            }
        },
        //这里可以弄执行下载操作，异步请求请规避超链接
        onClickHref: function () {
            window.open(contextPort + this.tableDataRow.attachdoc);
        },

        /////////////////////////////////////非直接事件调用函数///////////////////////////////////////
        //根据num查询项目数
        selectProjectUser: function() {
            axios.get(
                this.pageContentUrl.selectProjectUserUrl, {
                    params: {
                        progressStatus : 2,
                        applyStatusList: '1,2,3,4,5,6',
                        currentPage: this.pageInfo.pageNum,
                        currentSize: this.pageInfo.pageSize
                    }
                })
                .then(function (response) {
                    console.log(response);//成功后调用

                    if (response.data.result == '0000') {
                        // this.tableData = response.data.data;
                        this.pageInfo = response.data.pi;
                        this.tableData = response.data.data;
                        //为防止万一，再刷新一下
                    } else if (response.data.result == '9999') {
                    }
                }.bind(this))
                .catch(function (error) {
                    console.log(error);
                }.bind(this));
        },
    },
    //在实例创建完成后被立即调用。老师审核通过
    created: function () {
        this.selectProjectUser();
    }
})


