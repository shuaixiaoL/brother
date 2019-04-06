(function ($) {
    $.fn.sidebarMenu = function (options) {
        options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
        var target = $(this);
        target.addClass('nav');
        target.addClass('nav-list');
        if (options.data) {
            init(target, options.data);
        }
        else {
            if (!options.url) return;
            $.getJSON(options.url, options.param, function (data) {
                init(target, data);
            });
        }
        var url = window.location.pathname;
        //menu = target.find("[href='" + url + "']");
        //menu.parent().addClass('active');
        //menu.parent().parentsUntil('.nav-list', 'li').addClass('active').addClass('open');
        function init(target, data) {
            $.each(data, function (i, item) {
                var li = $('<li id="li_' + item.id + '"></li>');
                var a = $('<a></a>');
                var icon = $('<i></i>');
                // icon.addClass('glyphicon');
                icon.addClass(item.icon);
                var text = $('<span></span>');
                text.addClass('menu-text').text(item.text);
                a.append(icon);
                a.append(text);
                if (item.menus&&item.menus.length>0) {
                    a.attr('href', '#');
                    a.addClass('dropdown-toggle');
                    var arrow = $('<b></b>');
                    arrow.addClass('arrow').addClass('icon-angle-down');
                    a.append(arrow);
                    li.append(a);
                    var menus = $('<ul></ul>');
                    menus.addClass('submenu');
                    init(menus, item.menus);//当有子menu目录时，递归调用
                    li.append(menus);
                }
                else {
                    var href = 'javascript:addTabs({id:\'' + item.id + '\',title: \'' + item.text + '\',close: true,url: \'' + item.url + '\',icon:\''+ item.icon +'\'});';
                    a.attr('href', href);
                    //if (item.istab)
                    //    a.attr('href', href);
                    //else {
                    //    a.attr('href', item.url);
                    //    a.attr('title', item.text);
                    //    a.attr('target', '_blank')
                    //}
                    li.append(a);
                }
                target.append(li);
            });
        }
    }

    $.fn.sidebarMenu.defaults = {
        url: null,
        param: null,
        data: null
    };
})(jQuery);

$(function () {

        $.ajax({
            url: contextPath + '/rest/getPermission',
            type: 'post',
            timeout: 20000,
            data:'json',
            success: function (result) {
               if(result) {
                   $('#menu').sidebarMenu({
                       data:result
                   });
               }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("访问后台发生错误:" + XMLHttpRequest.status)
            }
        });
        $('#sidebar').height($(window).height() - 80);

    // $('#menu').sidebarMenu({ url: "/api/Api/GetMenuByUser/", param: { strUser: 'admin' } });

});