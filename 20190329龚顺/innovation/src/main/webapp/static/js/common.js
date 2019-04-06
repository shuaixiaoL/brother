var commonMain = {
    //js数组分组
    groupJs: function (arr) {
        var map = {},
            dest = [];
        for (var i = 0; i < arr.length; i++) {
            var ai = arr[i];
            if (!map[ai.line_code]) {
                dest.push({
                    line_code: ai.line_code,
                    line_name: ai.line_name,
                    kind: ai.kind,
                    data: [ai]
                });
                map[ai.line_code] = ai;
            } else {
                for (var j = 0; j < dest.length; j++) {
                    var dj = dest[j];
                    if (dj.line_code == ai.line_code) {
                        dj.data.push(ai);
                        break;
                    }
                }
            }
        }
        return dest;
    },

    //js拆分数组
    splitArr: function (chartArr) {
        var allData = []; //用来装处理完的数组
        var currData = []; //子数组用来存分割完的数据
        //循环需要处理的数组
        for (var i = 0; i < chartArr.length; i++) {
            //将chartArr[i]添加到子数组
            currData.push(chartArr[i]);
            //在这里求4的余数,如果i不等于0,且可以整除 或者考虑到不满4个或等于4个的情况就要加上  i等于当前数组长度-1的时候
            if ((i != 0 && (i + 1) % 17 == 0) || i == chartArr.length - 1) {
                if (i < chartArr.length - 1) {//这里做个小变动，取后面的第一条加入数组
                    currData.push(chartArr[i + 1]);
                }
                //把currData加到allData里
                allData.push(currData);
                //在这里清空currData
                currData = [];
            }
        }
        ;
        return allData;
    },

    //获得数组差（arr1为大数组）
    getArrDif: function (arr1, arr2) {
        for (var i = arr1.length - 1; i >= 0; i--) {
            a = arr1[i];
            for (var j = arr2.length - 1; j >= 0; j--) {
                b = arr2[j];
                if (a == b) {
                    arr1.splice(i, 1);
                    arr2.splice(j, 1);
                    break;
                }
            }
        }
        return arr1;//得到差
    },
    //数组求差（类似二维数组，双重循环）,目前单条件,得第一位的
    getSubtract: function (unionArr, subsetArr, condition) {
        var new_tmp = [];
        for (var i = 0; i < unionArr.length; i++) {
            var flag = true;
            for (var j = 0; j < subsetArr.length; j++) {
                if (unionArr[i][condition] == subsetArr[j][condition]) {
                    flag = false;
                }
            }
            if (flag) {
                new_tmp.push(unionArr[i]);
            }
        }
        return new_tmp;
    },
    //4.在对象数组中，抽取一列，组成数据
    getArrFromObjArr(ObjArr, key) {
        let arr = [];
        for (let i = 0; i < ObjArr.length; i++) {
            arr.push(ObjArr[i][key]);
        }
        return arr;
    },
    //更具条件进行选择 item{ key, value }
    getObjArrById: function (ObjArr, items) {
        let ObjArr2 = [];
        for (let i = 0; i < ObjArr.length; i++) {
            if(items.value.indexOf(ObjArr[i][items.key]) + 1) {
                ObjArr2.push(ObjArr[i]);
            }
        }
        return ObjArr2;
    }


}
