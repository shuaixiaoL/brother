

var vue = new Vue({
    el: '#app',
    data() {
        return {
            tableData: [],
            pageInfo: {},
            outerVisible: false,
            innerVisible: false,
            dialogVisible: false,
            rejectFlag: false,
            tableDataRow: {},//选择中行的数据
            rejectTextarea: ''
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
            //关闭模版框
            this.dialogVisible = false
            var jsjson = {};
            jsjson['numSession'] = numSession;
            jsjson['levelSession'] = levelSession;
            jsjson['projectUser'] = this.tableDataRow;
            jsjson['reject'] = this.rejectTextarea;
            jsjson['currentPage'] = this.pageInfo.pageNum;
            jsjson['currentSize'] = this.pageInfo.pageSize;

            axios.put(
                bashPath + "/rest/api/innovation/projectUser/reject/", jsjson)
                .then(function (response) {
                    console.log(response);//成功后调用
                    if (response.data.result == '0000') {
                        this.$message(response.data.msg);
                        this.selectProjectUserByNumProgressApply();
                        // this.tableData = response.data.data;
                    } else if (response.data.result == '9999') {
                    }
                }.bind(this))
                .catch(function (error) {
                    console.log(error);
                    this.$message('错误信息' + error);
                }.bind(this));
        },
        //通过
        passExamine() {
            //关闭模版框
            this.outerVisible = false
            var jsjson = {};
            jsjson['numSession'] = numSession;
            jsjson['levelSession'] = levelSession;
            jsjson['projectUser'] = this.tableDataRow;
            jsjson['currentPage'] = this.pageInfo.pageNum;
            jsjson['currentSize'] = this.pageInfo.pageSize;

            axios.put(
                bashPath + "/rest/api/innovation/projectUser/passExamine/", jsjson)
                .then(function (response) {
                    console.log(response);//成功后调用
                    if (response.data.result == '0000') {
                        this.$message(response.data.msg);
                        this.selectProjectUserByNumProgressApply();
                    } else if (response.data.result == '9999') {
                    }
                }.bind(this))
                .catch(function (error) {
                    console.log(error);
                    this.$message('错误信息' + error);
                }.bind(this));
        },
        //关于分页
        handleSizeChange:function(val){
            console.log("每页"+val+"条");
            this.pageInfo.pageSize = val;
            this.selectProjectUserByNum();
        },
        handleCurrentChange:function(val){
            console.log("当前页:"+val);
            this.pageInfo.pageNum = val;
            this.selectProjectUserByNum();
        },

        /////////////////////////////////////非直接事件调用函数///////////////////////////////////////
        //根据num查询项目数
        selectProjectUserByNum: function() {
            axios.get(
                bashPath + '/rest/api/innovation/projectUser/numprogress/', {
                    params: {
                        numSession: numSession,
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
        selectProjectUserByNumProgressApply:function () {

            axios.get(
                bashPath + '/rest/api/innovation/projectUser/numprogressapply/', {
                    params: {
                        numSession: numSession,
                        progressStatus: 4,
                        applyStatusList: '1,3,6'
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
        this.selectProjectUserByNumProgressApply();
    }
})


