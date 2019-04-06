var commonData = {
    //1.页面

    //2.数据库配置表
    params: [],//所有数据配置信息
    paramsFirst: [],


}
var commomUtils = {

}
var commomMain = {
    //访问数据字段
    getSysParam: function () {
        axios.get(
            contextPath + '/rest/api/innovation/params/'
        )
            .then(function (response) {
                var data = response.data;
                commonData.params = data;

            }.bind(this))
            .catch(function (error) {
                console.log(error);
            }.bind(this));
    }
}

var initCommon = function () {
    commomMain.getSysParam();
}
