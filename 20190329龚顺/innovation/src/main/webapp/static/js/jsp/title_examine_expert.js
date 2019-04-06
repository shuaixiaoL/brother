

var vue = new Vue({
    el: '#app',
    data() {
        return {
            pageContentUrl: {
                selectProjectUserUrl: contextPath + '/rest/api/innovation/projectUser/progressapply',
                rejectConfirmUrl: contextPath + "/rest/api/innovation/projectUser/reject/",
                passConfirmUrl: contextPath +  "/rest/api/innovation/projectUser/passExamine/"
            },
            tableData: [],
            pageInfo: {},
            outerVisible: false,
            innerVisible: false,
            dialogVisible: false,
            passDialogVisible: false,//点击通过打开的模态框
            rejectFlag: false,
            tableDataRow: {},//选择中行的数据
            rejectTextarea: '',
            funds: 0//项目经费
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
        //驳回意见"确认"触发的事件
        rejectConfirm() {
            var self = this;
            //关闭模版框
            this.dialogVisible = false
            var jsjson = {};
            jsjson['projectUser'] = this.tableDataRow;
            jsjson['reject'] = this.rejectTextarea;
            jsjson['currentPage'] = this.pageInfo.pageNum;
            jsjson['currentSize'] = this.pageInfo.pageSize;

            axios.put(
                self.pageContentUrl.rejectConfirmUrl, jsjson)
                .then(function (response) {
                    console.log(response);//成功后调用
                    if (response.data.result == '0000') {
                        this.$message(response.data.msg);
                        this.selectProjectUser();//再次访问数据库
                    } else if (response.data.result == '9999') {
                    }
                }.bind(this))
                .catch(function (error) {
                    console.log(error);
                    this.$message('错误信息' + error);
                }.bind(this));
        },
        //打开通过模态框
        openPassExmine: function() {
            //打开模态框
            this.passDialogVisible = true;
        },
        //通过
        fundsConfirm: function() {
            var self = this;
            //关闭模版框
            this.outerVisible = false;
            this.passDialogVisible = false;
            var jsjson = {};
            jsjson['funds'] = this.funds;
            jsjson['projectUser'] = this.tableDataRow;
            jsjson['currentPage'] = this.pageInfo.pageNum;
            jsjson['currentSize'] = this.pageInfo.pageSize;

            axios.put(self.pageContentUrl.passConfirmUrl, jsjson)
                .then(function (response) {
                    console.log(response);//成功后调用
                    if (response.data.result == '0000') {
                        this.$message(response.data.msg);
                        this.selectProjectUser();
                    } else if (response.data.result == '9999') {
                    }
                }.bind(this))
                .catch(function (error) {
                    this.$message('错误信息' + error);
                }.bind(this));
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

        //这里可以弄执行下载操作，异步请求请规避超链接
        onClickHref: function () {
            window.open(contextPort + this.tableDataRow.attachdoc);
        },

        /////////////////////////////////////非直接事件调用函数///////////////////////////////////////
        //根据num查询项目数
        selectProjectUser: function() {
            var self = this;
            axios.get(
                self.pageContentUrl.selectProjectUserUrl, {
                    params: {
                        progressStatus: 1,
                        applyStatusList: '2,3,6',
                        currentPage: this.pageInfo.pageNum,
                        currentSize: this.pageInfo.pageSize
                    }
                })
                .then(function (response) {
                    console.log(response);//成功后调用
                    if (response.data.result == '0000') {
                        this.pageInfo = response.data.pi;
                        this.tableData = response.data.data;
                    } else if (response.data.result == '9999') {
                    }
                }.bind(this))
                .catch(function (error) {
                    console.log(error);
                }.bind(this));
        }
    },
    //在实例创建完成后被立即调用。
    created: function () {
        this.selectProjectUser();
    }
})


