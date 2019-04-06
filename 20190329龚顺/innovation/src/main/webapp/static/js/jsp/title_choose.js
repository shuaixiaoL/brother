

var vue = new Vue({
    el: '#app',
    data() {
        return {
            pageContentUrl: {
                selectTitleChooseUrl: contextPath +  "/rest/api/innovation/projectUser/titleChoose/",
                declareDetailedUrl: contextPath + '/rest/api/innovation/projectUser/userLevel',
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
            this.selectTitleChoose();
        },
        handleCurrentChange:function(val){
            console.log("当前页:"+val);
            this.pageInfo.pageNum = val;
            this.selectTitleChoose();
        },

        //这里可以弄执行下载操作，异步请求请规避超链接
        onClickHref: function () {
            window.open(contextPort + this.tableDataRow.attachdoc);
        },

        //申请组长、成员一、成员二
        declareDetailed: function (index, row, userLevel) {
            //赋值给选中行数据
            var data = this.tableData;
            this.tableDataRow = data[index];
            console.log(userLevel);
            var jsjson = {};
            jsjson['levelSession'] = levelSession;
            jsjson['userLevel'] = userLevel;
            jsjson['projectUser'] = this.tableDataRow;

            axios.post(
                this.pageContentUrl.declareDetailedUrl,
                jsjson
            )
                .then(function (response) {
                    console.log(response);//成功后调用
                    if (response.data.result == '0000') {
                        this.$message(response.data.msg);
                        this.selectTitleChoose();
                    } else if (response.data.result == '9999') {
                        this.$message(response.data.msg + '有问题');
                    }
                }.bind(this))
                .catch(function (error) {
                    console.log(error);
                    this.$message('错误信息' + error);
                }.bind(this));
        },

        /////////////////////////////////////非直接事件调用函数///////////////////////////////////////
        //根据num查询项目数
        selectTitleChoose: function () {
            axios.get(
                this.pageContentUrl.selectTitleChooseUrl,{
                    params:{
                        progressStatus: 1,
                        applyStatusList: '2',
                        currentPage: this.pageInfo.pageNum,
                        currentSize: this.pageInfo.pageSize
                    }
                }
            )
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
        },
    },
    //在实例创建完成后被立即调用。老师审核通过
    created: function () {
        this.selectTitleChoose();
    }
})


