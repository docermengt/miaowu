﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link href="../static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="../static/bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="../static/bootstrap/js/jquery-3.3.1.min.js"></script>
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>


    <link rel="stylesheet" type="text/css" href="../static/css/header.css">
    <link rel="stylesheet" type="text/css" href="../static/css/main.css">
    <link rel="stylesheet" type="text/css" href="../static/css/footer.css">
    <link rel="stylesheet" type="text/css" href="../static/css/buyTickets.css">
    <link rel="stylesheet" type="text/css" href="../static/css/movieDetail.css">

    <script src="../static/js/Api.js"></script>

    <script src="../static/layui/layui.js" charset="utf-8"></script>
<%--    <link rel="stylesheet" href="../static/layui/css/layui.css" media="all">--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/layui/2.9.2/css/layui.css" integrity="sha512-V8POzDh/+/NrceHV1dsdK9v6VWgQAtPaxYvQWGID2+PRoWJrjFiqlb26gE2PzdE8GIFoBvOOBtMH/SiAvj8uWQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>猫眼电影-详细</title>
</head>
<body>
<!-- ------------------------------------------------------------------- -->
<!-- 导航栏 -->
<jsp:include page="header.jsp"/>

<!-- 占位符 -->
<div style="margin-top: 80px;"></div>

<!-- 巨幕 -->
<div class="banner2">
    <div class="wrapper clearfix">
        <div class="celeInfo-left">
            <div class="avatar-shadow">
                <!-- 图片 -->
            </div>
        </div>

        <div class="celeInfo-right clearfix">
            <div class="movie-brief-container">
                <!-- 上 -->
            </div>
            <div class="action-buyBtn">
                <div class="action clearfix" data-val="{movieid:42964}">
                    <a class="wish " data-wish="false" onclick="wantSee()">
                        <div>
                            <i class="icon wish-icon"></i>
                            <span class="wish-msg" data-act="wish-click">想看</span>
                        </div>
                    </a>
                    <a class="score-btn " data-bid="b_rxxpcgwd" onclick="giveScore()">
                        <div>
                            <i class="icon score-btn-icon"></i>
                            <span class="score-btn-msg" data-act="comment-open-click">评分</span>
                        </div>
                    </a>
                </div>
            </div>

            <div class="movie-stats-container">
                <div class="movie-index">
                    <p class="movie-index-title">用户评分</p>
                    <div class="movie-index-content score normal-score">
                            <span class="index-left info-num ">
                                <!-- 评分 -->
                            </span>
                        <div class="index-right">
                            <div class="star-wrapper">
                                <div id="MovieScore"></div>
                            </div>
                            <span class="score-num">
                                    <!-- 评分数 -->
                                </span>
                        </div>
                    </div>
                </div>

                <div class="movie-index">
                    <p class="movie-index-title">累计票房</p>
                    <div class="movie-index-content box stonefont-num">
                        <!-- 票房数 -->
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<!-- 占位符 -->
<div style="margin-top: 50px;"></div>

<!-- 主体 -->
<div class="main">
    <div class="main-inner main-detail">
        <div class="main-content">
            <div class="tab-container">
                <div class="tab-title-container clearfix">
                    <div class="tab-title active" data-act="tab-desc-click">介绍</div>
                </div>

                <div class="tab-content-container">
                    <div class="tab-desc tab-content active" data-val="{tabtype:'desc'}">
                        <!-- 剧情简介 -->
                        <div class="module introduction">
                            <div class="mod-title">
                                <h3>剧情简介</h3>
                            </div>
                        </div>
                        <!-- 演职人员 -->
                        <div class="module staringPeople">
                            <div class="mod-title">
                                <h3>演职人员</h3>
                            </div>
                            <div class="mod-content">
                                <div class="celebrity-container clearfix">
                                </div>
                            </div>
                        </div>
                        <!-- 评论 -->
                        <div class="module">
                            <div class="mod-title">
                                <h3>热门短评</h3>
                            </div>

                            <div class="mod-content">
                                <div class="comment-list-container" data-hot="10">
                                    <ul class="view-comment">
                                    </ul>
                                </div>
                                <a class="comment-entry" data-act="comment-no-content-click" onclick="writeComment()">写短评</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 脚 -->
<jsp:include page="footer.jsp"/>

<!-- ------------------------------------------------------------------- -->
<script>
    var clientHeight = document.documentElement.clientHeight;
    var movie_id;
    var ScoreHtml;
    var user_id = JSON.parse(localStorage.getItem("user_json"));
    console.log(user_id)
    window.onload = function () {
        initHtml(); //HTML
        initBanner(); //巨幕
        intitActor(); //演职人员
        init_comment(); //评论
    }

    //初始化HTML
    function initHtml() {
        ScoreHtml =
            "<div style=\"text-align:center; margin:30px 0;\">" +
            "<div id=\"GiveScore\"></div>" +
            "<p style=\"color:#888;\">点击星星进行评分</p>" +
            "</div>"

        UpdateCommentHtml =
            "<h3 class=\"commenttitle\">评论信息</h3>" +
            "<div class=\"layui-form-item\">" +
            "<label class=\"layui-form-label usernametext\">用户帐号</label>" +
            "<div class=\"layui-input-block username\">" +
            "<input id=\"user_name\" type=\"text\" name=\"title\" lay-verify=\"title\" disabled=\"disabled\" style=\"cursor:not-allowed;\" autocomplete=\"off\" placeholder=\"username\" class=\"layui-input\">" +
            "</div>" +
            "</div>" +
            "<div class=\"layui-form-item\">" +
            "<label class=\"layui-form-label commenttimetext\">评论时间</label>" +
            "<div class=\"layui-input-block commenttime\">" +
            "<input id=\"comment_time\" type=\"text\" name=\"title\" lay-verify=\"title\" disabled=\"disabled\" style=\"cursor:not-allowed;\" autocomplete=\"off\" placeholder=\"commenttime\" class=\"layui-input\">" +
            "</div>" +
            "</div>" +
            "<div class=\"layui-form-item\">" +
            "<label class=\"layui-form-label commentcontenttext\">评论内容</label>" +
            "<div class=\"layui-input-block commentcontent\">" +
            "<textarea id=\"comment_content\" style=\"height:120px;\" placeholder=\"请输入评论内容\" autocomplete=\"off\" class=\"layui-textarea\" name=\"desc\" class=\"layui-input\"></textarea>" +
            "</div>" +
            "</div>"
        ;
        WriteCommentHtml =
            "<h3 class=\"commenttitle\">评论信息</h3>" +
            // "<div class=\"layui-form-item\">" +
            //     "<label class=\"layui-form-label usernametext\">用户帐号</label>" +
            //     "<div class=\"layui-input-block username\">" +
            //         "<input id=\"user_name_write\" type=\"text\" name=\"title\" lay-verify=\"title\" disabled=\"disabled\" style=\"cursor:not-allowed;\" autocomplete=\"off\" placeholder=\"username\" class=\"layui-input\">" +
            //     "</div>" +
            // "</div>" +
            "<div class=\"layui-form-item\">" +
            "<label class=\"layui-form-label commentcontenttext\">评论内容</label>" +
            "<div class=\"layui-input-block commentcontent\">" +
            "<textarea id=\"comment_content_write\" style=\"height:150px;\" placeholder=\"请输入评论内容\" autocomplete=\"off\" class=\"layui-textarea\" name=\"desc\" class=\"layui-input\"></textarea>" +
            "</div>" +
            "</div>"
        ;
    }

    //初始化巨幕
    function initBanner() {
        movie_id = getUrlParams('movie_id');
        var avatar = $(".avatar-shadow");
        var movieBriefContainer = $(".movie-brief-container");
        var infoNum = $(".info-num");
        var scoreNum = $(".score-num");
        var stonefontNum = $(".stonefont-num");
        var actionBuyBtn = $(".action-buyBtn");
        var Introduction = $(".introduction");
        var StonefontTemp;

        $.ajax({
            type: 'post',
            url: url + "/movielist/findMovieById",
            dataType: 'json',
            data: {
                movie_id: movie_id
            },
            success: function (obj) {
                StonefontTemp = obj.moviesbyid[0].movie_boxOffice;
                StonefontTemp += "万";
                avatar.append("<img class=\"avatar\" src=\"" + obj.moviesbyid[0].movie_picture + "\" alt=\"\">");
                movieBriefContainer.append(
                    "<h3 class=\"name\">" + obj.moviesbyid[0].movie_cn_name + "</h3>" +
                    "<div class=\"ename ellipsis\">" + obj.moviesbyid[0].movie_fg_name + "</div>" +
                    "<ul>" +
                    "<li class=\"ellipsis\">" + obj.moviesbyid[0].movie_type + "</li>" +
                    "<li class=\"ellipsis\">" + obj.moviesbyid[0].movie_duration + " / " + obj.moviesbyid[0].movie_country + "</li>" +
                    "<li class=\"ellipsis\">" + obj.moviesbyid[0].releaseDate + "</li>" +
                    "<ul>");
                infoNum.append("<span class=\"stonefont\">" + obj.moviesbyid[0].movie_score + "</span>");
                scoreNum.append("<span class=\"stonefont\">" + obj.moviesbyid[0].movie_commentCount + "</span>人评分");
                stonefontNum.append("<span class=\"stonefont\">" + StonefontTemp + "</span>");
                actionBuyBtn.append("<div class=\"moviedetail\"></div>");
                Introduction.append(
                    "<div class=\"mod-content\">" +
                    "<span class=\"dra\" style=\"font-size:16px;\">" + obj.moviesbyid[0].movie_detail + "</span>" +
                    "</div>"
                );
                layui.use('rate', function () {
                    var rate = layui.rate;
                    rate.render({
                        elem: '#MovieScore'
                        , value: (obj.moviesbyid[0].movie_score / 2)
                        , half: true
                        , readonly: true
                    })
                });
            }
        });
    }

    //评分
    function giveScore() {
        layui.use(['rate', 'layer'], function () {
            var layer = layui.layer;
            var rate = layui.rate;
            layer.open({
                type: 1
                , title: "电影评分"
                , closeBtn: false
                , area: '400px;'
                , shade: 0.8
                , offset: clientHeight / 5
                , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                , btn: ['确认评分', '取消']
                , yes: function () {
                    layer.alert('感谢你的评价,祝你生活愉快！', {icon: 0, offset: clientHeight / 5},
                        function () {
                            layer.closeAll();
                            location.reload();
                        }
                    );
                }
                , btnAlign: 'c'
                , moveType: 0 //拖拽模式，0或者1
                , content: ScoreHtml
                , success: function (layero) {
                    rate.render({
                        elem: '#GiveScore'
                        , value: 4.5
                        , half: true
                        , text: true
                        , setText: function (value) {
                            this.span.text(value * 2 + "分");
                        }
                    })
                }
            });
        });
    }

    //想看
    function wantSee() {
        layui.use(['rate', 'layer'], function () {
            var layer = layui.layer;
            var rate = layui.rate;
            layer.alert('感谢你的支持！', {icon: 0, offset: clientHeight / 5},
                function () {
                    layer.closeAll();
                    location.reload();
                }
            );
        });
    }

    //初始化演职人员
    function intitActor() {
        var celebrityContainer = $(".celebrity-container");
        var PosName;
        var StrActor;
        var StrRole;
        var PictureUrl;
        $.ajax({
            type: 'post',
            url: url + "/movielist/findMovieById",
            dataType: 'json',
            data: {
                movie_id: movie_id
            },
            success: function (obj) {
                console.log(obj);
                PictureUrl = "../static/images/actor/" + obj.moviesbyid[0].movie_cn_name + "/director/" + obj.moviesbyid[0].movie_director + ".jpg";
                // 导演
                celebrityContainer.prepend(
                    "<div class=\"celebrity-group\">" +
                    "<div class=\"celebrity-type\">" +
                    "导演" +
                    "</div>" +
                    "<ul class=\"celebrity-list clearfix\">" +
                    "<li class=\"celebrity\">" +
                    "<a class=\"portrait\">" +
                    "<img class=\"default-img\" alt=\"\" src=\"" + PictureUrl + "\">" +
                    "</a>" +
                    "<div>" +
                    "<a style=\"text-decoration: none;\" class=\"name\">" + obj.moviesbyid[0].movie_director + "</a>" +
                    "</div>" +
                    "</li>" +
                    "</ul>" +
                    "</div>" +
                    "<div style=\"width:40px; height:20px; float:left;\"></div>"
                );
                StrActor = obj.moviesbyid[0].movie_actor.split(',');
                for (var i = 0; i < StrActor.length; i++) {
                    StrRole = StrActor[i].split(':');
                    PictureUrl = "../static/images/actor/" + obj.moviesbyid[0].movie_cn_name + "/actor/" + StrRole[0] + ".jpg";
                    // 演员
                    PosName = "&nbsp";
                    if (i == 0) {
                        PosName = "演职人员";
                    }
                    celebrityContainer.append(
                        "<div class=\"celebrity-group\">" +
                        "<div class=\"celebrity-type\">" +
                        PosName +
                        "</div>" +
                        "<ul class=\"celebrity-list clearfix\">" +
                        "<li class=\"celebrity\">" +
                        "<a class=\"portrait\">" +
                        "<img class=\"default-img\" alt=\"\" src=\"" + PictureUrl + "\">" +
                        "</a>" +
                        "<div>" +
                        "<a style=\"text-decoration: none;\" class=\"name\">" + StrRole[0] + "&nbsp饰&nbsp" + StrRole[1] + "</a>" +
                        "</div>" +
                        "</li>" +
                        "</ul>" +
                        "</div>"
                    );
                }
            }
        });


    }

    //初始化评论
    function init_comment() {
        $.ajax({
            type: 'post',
            url: url + "/comment/selectComment",
            dataType: 'json',
            data: {
                movie_id: movie_id
            },
            success: function (obj) {

                for (let i = 0; i < obj.commentlist.length; i++) {
                    $(".view-comment").append(
                        ' <div class="layui-card">' +
                        '  <div class="layui-card-header" style="color: #e56666">' +'用户：'+ obj.commentlist[i].user_name + '</div>' +
                        '<div class="layui-card-body">' + obj.commentlist[i].comment_content + ' </div>' +
                        '<p  style="display: flex;justify-content: flex-end; color:#ef4238">'+'评论时间：'+new Date(obj.commentlist[i].comment_time).toLocaleString()+'</p>'+
                        '</div>'
                    )
                }


            }
        })
    }

    //写评论
    function writeComment() {
        //判断用户是否购买此电影
        var user_id =  JSON.parse(localStorage.getItem("user_json"));
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
    // 获取指定参数的值
        const movie_id = urlParams.get('movie_id');

        $.ajax({
            type: 'post',
            dataType:'json',
            url: url +"/schedule/selectByUserid",
            data:{
                user_id:user_id
            },
            success:function (obj){
                   var  flag = 0;
                for (let i = 0; i <obj.scheduleList.length  ; i++) {
                    if(movie_id ==obj.scheduleList[i].movie_id){
                        flag = 1;
                    }else {
                        flag = 0;
                    }
                }
                console.log(flag)
                  if(flag){
                     alertWitle()
                  }else {
                     alertMsg()
                  }
                  //弹出评论框
                function  alertWitle(){

                    layui.use(['laypage', 'layer', 'table'], function () {
                        var layer = layui.layer;
                        //写评论
                        layer.open({
                            type: 1
                            , title: "编写评论" //不显示标题栏
                            , closeBtn: false
                            , area: '430px;'
                            , shade: 0.8
                            , offset: clientHeight / 20
                            , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                            , btn: ['确认评价', '取消']
                            , yes: function () {
                                var comment_content = $('#comment_content_write').val();
                                if (comment_content == "") {
                                    layer.alert('评论内容不能空，评论失败！', {icon: 0, offset: clientHeight / 5},
                                        function () {
                                            layer.close(layer.index);
                                        }
                                    );
                                } else {
                                    if (comment_content.length > 150) {
                                        layer.alert('字数超过150个，评论失败！', {icon: 0, offset: clientHeight / 5},
                                            function () {
                                                layer.close(layer.index);
                                            }
                                        );
                                    } else {
                                        $.ajax({
                                            type: 'post',
                                            url: url + "/comment/addCommentByUser",
                                            dataType: 'json',
                                            data: {
                                                movie_id: movie_id,
                                                comment_content: comment_content,
                                                user_id: user_id
                                            },
                                            success: function (obj) {
                                                if (obj.code == 0) {
                                                    layer.alert('评价成功！', {icon: 0, offset: clientHeight / 5},
                                                        function () {
                                                            layer.closeAll();
                                                            location.reload();
                                                        }
                                                    );
                                                } else {
                                                    layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                                        function () {
                                                            layer.closeAll();
                                                        }
                                                    );
                                                }
                                            }
                                        });

                                    }
                                }
                            },
                            btn2: function(){
                                //按钮【按钮二】的回调
                                layer.closeAll()
                            }
                            , btnAlign: 'c movie-last'
                            , moveType: 0 //拖拽模式，0或者1
                            , content: WriteCommentHtml
                        });
                    });
                }
                  //弹出禁止评论框
                function alertMsg(){

                    layui.use(['layer'],function(){
                        var layer = layui.layer;
                        layer.alert('请先购买影票后再评论！', {icon: 0, offset: clientHeight / 5})
                    })
                }
                    }



        })


    }

    //删除评论
    function deleteConfirm(comment_id) {
        layui.use(['layer'], function () {
            var layer = layui.layer;
            layer.alert('确定要删除该条评论信息吗？', {icon: 0, offset: clientHeight / 5}
                , function () {
                    $.ajax({
                        type: 'post',
                        url: url + "/comment/deleteComemnt",
                        dataType: 'json',
                        data: {
                            comment_id: comment_id,
                        },
                        success: function (obj) {
                            if (obj.code == 0) {
                                layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                    function () {
                                        layer.closeAll();
                                        location.reload();
                                    }
                                );
                            } else {
                                layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                    function () {
                                        layer.closeAll();
                                    }
                                );
                            }
                        }
                    });
                }
            );
        });
    }

    //修改评论
    function updateConfirm(comment_id, user_name, comment_content, comment_time) {
        layui.use(['laypage', 'layer', 'table'], function () {
            var laypage = layui.laypage;
            var layer = layui.layer;
            var table = layui.table
            //修改评论
            layer.open({
                type: 1
                , title: "修改评论" //不显示标题栏
                , closeBtn: false
                , area: '430px;'
                , shade: 0.8
                , offset: clientHeight / 20
                , id: 'LAY_layuipro' //设定一个id，防止重复弹出
                , btn: ['确认修改', '取消']
                , yes: function () {
                    var comment_content = $('#comment_content').val();
                    if (comment_content == "") {
                        layer.alert('评论内容不能空，修改失败！', {icon: 0, offset: clientHeight / 5},
                            function () {
                                layer.close(layer.index);
                            }
                        );
                    } else {
                        console.log(comment_content.length);
                        if (comment_content.length > 150) {
                            layer.alert('字数超过150个，评论失败！', {icon: 0, offset: clientHeight / 5},
                                function () {
                                    layer.close(layer.index);
                                }
                            );
                        } else {
                            $.ajax({
                                type: 'post',
                                url: url + "/comment/updateComment",
                                dataType: 'json',
                                data: {
                                    comment_id: comment_id,
                                    comment_content: comment_content
                                },
                                success: function (obj) {
                                    if (obj.code == 0) {
                                        layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                            function () {
                                                layer.closeAll();
                                                location.reload();
                                            }
                                        );
                                    } else {
                                        layer.alert(obj.msg, {icon: 0, offset: clientHeight / 5},
                                            function () {
                                                layer.closeAll();
                                            }
                                        );
                                    }
                                },
                                error: function () {
                                    alert("network error!");
                                }
                            });
                        }
                    }
                }
                , btnAlign: 'c movie-last'
                , moveType: 0 //拖拽模式，0或者1
                , content: UpdateCommentHtml
                , success: function (layero) {
                    $('#user_name').val(user_name);
                    $('#comment_time').val(comment_time);
                    $('#comment_content').val(comment_content);
                }
            });
        });
    }

    //获取url参数
    function getUrlParams(name) { // 不传name返回所有值，否则返回对应值
        var url = window.location.search;
        if (url.indexOf('?') == 1) {
            return false;
        }
        url = url.substr(1);
        url = url.split('&');
        var name = name || '';
        var nameres;
        // 获取全部参数及其值
        for (var i = 0; i < url.length; i++) {
            var info = url[i].split('=');
            var obj = {};
            obj[info[0]] = decodeURI(info[1]);
            url[i] = obj;
        }
        // 如果传入一个参数名称，就匹配其值
        if (name) {
            for (var i = 0; i < url.length; i++) {
                for (const key in url[i]) {
                    if (key == name) {
                        nameres = url[i][key];
                    }
                }
            }
        } else {
            nameres = url;
        }
        // 返回结果
        return nameres;
    }

</script>
<!-- ------------------------------------------------------------------- -->

</body>
</html>