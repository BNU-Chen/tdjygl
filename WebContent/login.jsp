<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%String msg = request.getParameter("login");
			msg = msg == null ? "" : msg;
			System.out.println("login:" + msg);%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>集体建设用地流转信息发布与电子交易系统</title>
<style type="text/css">
html,body {
	height: 100%;
	width: 100%;
	margin: 0;
	padding: 0;
}

body {
	margin: 0;
	padding: 0;
	font-size: 9pt;
}

body{margin:0; padding:0; font-size:9pt;}
#login{margin:auto; width:1000px; height:600px; margin-top:60px;}
#top_left{width:1000px; height:89px; float:left;}
#top_left img {margin-left:0px;}

#center_left{width:752px; height:450px; background:url(images/login_09.png); float:left;}
#center_center{width:248px; height:89px; background:url(images/login_05.gif); float:left;}
#center_middle{width:248px; height:450px; float:left; background:url(images/login_13.gif) repeat-y;}
#center_right{width:36px; height:190px; float:right; background:url(images/login_11.gif);}

#down_left{width:1000px; height:50px; float:left; margin-top:15px;}
#down_center{width:248px; height:50px; background:url(images/login_16.gif); float:left;}
#inf{width:1000px; height:38px; background:url(images/login_18.png) no-repeat; }
.inf_text{font-family:Arial;display:block; width:100px; height:20px; font-size:16px; font-weight:bolder; color:#fff; margin-left:17px; margin-top:12px; float:left;}
.copyright{font-family:Arial;display:block; float:left; margin-left:17px; margin-top:15px;}

#user{ float:left;margin-left:30px; margin-top:30px; height:30px;font-family: arial, helvetica, verdana, sans-serif; font-size: 12px;}
#password{float:left;margin-left:30px; margin-top:20px; height:30px;font-family: arial, helvetica, verdana, sans-serif; font-size: 12px;}

#select{float:left;margin-left:30px; margin-top:20px; height:30px;font-family: arial, helvetica, verdana, sans-serif; font-size: 12px;width:200px}

.textinput{width:150px; height:25px; border:solid 2px #aca7a7; font-family: arial, helvetica, verdana, sans-serif; font-size: 12px;}
#rememberme{float:left;margin-left:10px; margin-top:20px; height:30px;font-size: 10px}
.checkbox{margin-left:30px;}
#btn{float:left;margin-left:30px; margin-top:20px;height:25px; margin-right:28px; text-align:center;}
#btn a{display:block; line-height:25px; background: url(images/bt_bg.gif); border: solid 1px #b6b6b6; width:65px; float:left; margin-left:15px; text-decoration:none; color:#000;}
.submitbtn{display:block; line-height:25px; background: url(images/bt_bg.gif); border: solid 1px #b6b6b6; width:65px; float:left; margin-left:15px; text-decoration:none; color:#000;}
</style>

</head>
<body>

<div id="login">
    <div id="top">
        <div id="top_left"><img src="images/login_03.png"/></div>
    </div>

    <div id="center">
        <div id="center_left"></div>
        <div id="center_middle">
            <form id = "loginform" method="POST">
                <div id="center_center"></div>
                <div id="user">用 户&nbsp;
                <input class="textinput" id="j_username" type="text" name="j_username" value="admin"/>
                </div>
                <div id="password">密 码&nbsp;
                <input class="textinput" type="password" id="j_password" name="j_password" value="admin"/>
                </div>
                <div id="select">
                <select id="login_select" style="width:180px" onchange="changeForm(this.value)">
                  <option value="0">管理系统</option>
                  <option value="1">买方系统</option>
                  <option value="2">卖方系统</option>
                </select>
                </div>
              <div id="rememberme">
                <input id="_spring_security_remember_me" name="_spring_security_remember_me" class="checkbox" type="checkbox">&nbsp;记住密码
                </div>
                
              <div id="btn">
                <a href="register.jsp">注册</a>
                <input  type="button" class = "submitbtn" onClick="MM_callJS()"  value="登录"style="height:25px">
               
                </div>
            </form>
        </div>
    </div>
    <div id="down_left">
        <div id="inf">
            <span class="inf_text">版权信息</span>
            <span class="copyright">Copyright &nbsp; &copy;
                2014 &nbsp;北京师范大学 &nbsp;&nbsp; 农村土地流转一站式透明交易系统 &nbsp; v1.0</span>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
function MM_callJS() { //v2.0
  var myform=document.forms[0];

  var myselect=document.getElementById("login_select");
  var myselectValue=myselect.value;
  if(myselectValue==1) {
	  myform.action='/tdjygl/j_spring_security_check'; 
	  
  }
 else if(myselectValue==2){
 myform.action='/tdjygl/j_spring_security_check';
 
 }
 else{
 myform.action='/tdjygl/j_spring_security_check';} 
  myform.submit();
  
}

function changeForm(val){
var mydivImg=document.getElementById("center_center");
var j_password=document.getElementById("j_password");
var j_username=document.getElementById("j_username");
var my_img_path="login_05.gif";
	if(val==1) {
		my_img_path="login_0501.gif";
		//alert(form.childNodes[1].value);
		 // form.childNodes[2].childNodes[0]value="oper";
		j_username.value="oper";
		j_password.value="oper";
	}
	else if(val==2){
		my_img_path="login_0502.gif";
		j_username.value="oper";
		j_password.value="oper";
		}
	mydivImg.style.background="url(images/"+my_img_path+")";	
}
</script>
</html>