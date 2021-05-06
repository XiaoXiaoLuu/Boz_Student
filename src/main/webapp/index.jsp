<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BoZ--week1</title>
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/main.css" type="text/css" />
    <base href="${pageContext.request.contextPath}/" />
</head>

<body>
<div class="main">
    <div class="top">
        <div class="">
            <h2>学生数据管理页面</h2>
        </div>
    </div>

    <div class="list2">
        <div class="list2_item">
        <button id="addUser"> 添加 </button>
        <span class="tip-error"></span>
    </div>
        <table class="listcontent" id="userList" width="30%" cellspacing="1px">
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>出生日期</th>
            <th>备注</th>
            <th>平均分</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${studentVOList}" var="student" varStatus="status">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.birthday}</td>
                <td>${student.description}</td>
                <td>${student.avgScore}</td>
                <td>
                    <a href="javascript:void(0)" class="span_update">修改</a>
                    <a href="del?studentId=${student.id}">移除</a>
                </td>
            </tr>
        </c:forEach>
    </table>

        <hr style="height:1px;border:none;border-top:1px solid #ccc;"/>
        <!-- 分页文字信息，其中分页信息都封装在pageInfo中 -->
        <c:if test="${clauseVO.total != null && clauseVO.currentCount != null}">
            <fmt:parseNumber var="lastPage"  integerOnly="true" value="${clauseVO.total / clauseVO.currentCount + 1}" />
        </c:if>
        <div>
            当前第：${clauseVO.currentPage}页，总共：${lastPage}页，总共：${clauseVO.total}条记录
        </div>
        <!-- 分页条 -->
        <div class="lis2_item">
            <nav aria-label="Page navigation">
                <ul>
                    <li>
                        <a href="list?currentPage=1&currentCount=${clauseVO.currentCount}&total=${clauseVO.total}"
                           rel="external nofollow">首页</a>
                    </li>
                    <c:if test="${clauseVO.currentPage > 1}">
                        <li>
                            <a href="list?currentPage=${clauseVO.currentPage - 1}&currentCount=${clauseVO.currentCount}&total=${clauseVO.total}" rel="external nofollow" aria-label="Previous">
                                <span aria-hidden="true">«</span>
                            </a>
                        </li>
                    </c:if>

                    <%--                    <c:forEach items="${pageMyArticleInfo.navigatepageNums }" var="page_Num">--%>
                    <%--                        <c:if test="${page_Num == pageMyArticleInfo.pageNum }">--%>
                    <%--                            <li>--%>
                    <%--                                <a rel="external nofollow">${ page_Num}</a>--%>
                    <%--                            </li>--%>
                    <%--                        </c:if>--%>
                    <%--                        <c:if test="${page_Num != pageMyArticleInfo.pageNum }">--%>
                    <%--                            <li>--%>
                    <%--                                <a href="list?pn=${ page_Num}&ps=5&name=${user.name}"--%>
                    <%--                                   rel="external nofollow">${ page_Num}</a>--%>
                    <%--                            </li>--%>
                    <%--                        </c:if>--%>
                    <%--                    </c:forEach>--%>

                    <c:if test="${clauseVO.currentPage < clauseVO.total / clauseVO.currentCount }">
                        <li>
                            <a href="list?currentPage=${clauseVO.currentPage + 1}&currentCount=${clauseVO.currentCount}&total=${clauseVO.total}" rel="external nofollow" aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <a href="list?currentPage=${lastPage}&currentCount=${clauseVO.currentCount}&total=${clauseVO.total}"
                           rel="external nofollow">末页</a>
                    </li>
            </ul>
            </nav>
        </div>
    </div>
</div>

<div class="list_add_model">
    <label id="list_add" style="position: absolute;top:2px;left: 87%;font-size: 25px;">x</label>
    <div id="model_add">
        <form action="add" method="post">
            <ul>
                <li>
                    <label>姓名：</label>
                    <input type="text" name="studentnameAdd" id="studentnameAdd" class="studentname" required autofocus >
                    <span class="tip-error"></span>
                </li>
                <li>
                    <label>出生日期：</label>
                    <input type="date" name="birthdayAdd" id="birthdayAdd" class="birthday" required >
                    <span class="tip-error"></span>
                </li>
                <li>
                    <label>备注：</label>
                    <input type="text" name="descriptionAdd" id="descriptionAdd" class="description" required >
                    <span class="tip-error"></span>
                </li>
                <li>
                    <label>平均分：</label>
                    <input type="number" name="avgScoreAdd" id="avgScoreAdd" class="avgScore" required >
                    <span class="tip-error"></span>
                </li>
                <li>
                    <button id="submit_register">提交</button>
                    <c:if test="${errMsg != null && errMsg != ''}">
                        <span class="tip-error">${errMsg}</span>
                    </c:if>

                </li>
            </ul>
        </form>
    </div>
</div>

<div class="list_edit_model">
    <label id="list_edit" style="position: absolute;top:2px;left: 87%;font-size: 25px;">x</label>
    <div id="model_edit">
        <form action="edit" method="post">
            <ul>
                <li>
                    <label>姓名：</label>
                    <input type="text" id="studentid" hidden>
                    <input type="text" name="studentname" id="studentname" class="studentname" required autofocus >
                    <span class="tip-error"></span>
                </li>
                <li>
                    <label>出生日期：</label>
                    <input type="date" name="birthday" id="birthday" class="birthday" required >
                    <span class="tip-error"></span>
                </li>
                <li>
                    <label>备注：</label>
                    <input type="text" name="description" id="description" class="description" required >
                    <span class="tip-error"></span>
                </li>
                <li>
                    <label>平均分：</label>
                    <input type="number" name="avgScore" id="avgScore" class="avgScore" required >
                    <span class="tip-error"></span>
                </li>
                <li>
                    <button id="submit_register">提交</button>
                    <c:if test="${errMsg != null && errMsg != ''}">
                        <span class="tip-error">${errMsg}</span>
                    </c:if>

                </li>
            </ul>
        </form>
    </div>
</div>

<div id="backGround"></div>


<script type="text/javascript"
        src="https://cdn.staticfile.org/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/utils.js"></script>

<script type="text/javascript">
    var currentPage  = 1;
    var allPages = 1;
    var total = 0;

    $(function () {
        addUser();
        onUser();
        closeModel();
    });

    function addUser(){
        $('#addUser').click(function() {
            $("#studentnameAdd").val("");
            $("#birthdayAdd").val("");
            $("#descriptionAdd").val("");
            $("#avgScoreAdd").val("");
            
            $(".list_add_model").slideDown(300);
            $("#backGround").show();
        });
    }

    function onUser() {
        $(".listcontent").on('click', '.span_update', function () {
            let var1 = $(this).parents('tr').children('td').eq(0).text();
            let var2 = $(this).parents('tr').children('td').eq(1).text();
            let var3 = $(this).parents('tr').children('td').eq(2).text();
            let var4 = $(this).parents('tr').children('td').eq(3).text();
            let var5 = $(this).parents('tr').children('td').eq(4).text();

            $("#studentid").val("");
            $("#studentid").val(var1);

            $("#studentname").val("");
            $("#studentname").val(var2);

            $("#birthday").val("");
            $("#birthday").val(var3);

            $("#description").val("");
            $("#description").val(var4);

            $("#avgScore").val("");
            $("#avgScore").val(var5);

            console.log(var1);
            $(".list_edit_model").slideDown(300);
            $("#backGround").show();
        });

    }

    function closeModel() {
        $("#list_add").click(function(){
            $(".list_add_model").slideUp(300);
            $("#backGround").hide();
        });

        $("#list_edit").click(function(){
            $(".list_edit_model").slideUp(300);
            $("#backGround").hide();
        });
    }
</script>


</body>
</html>
