<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath+3/0%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body onload="init()">
    This is my JSP page. <br>
    <script>
    	(function(window){
    		
			function CreateXMLHttp(){
					var xmlhttp = false;
			        try{
			            xmlhttp = new XMLHttpRequest();  //尝试创建 XMLHttpRequest 对象，除 IE 外的浏览器都支持这个方法。
			        }
			        catch (e){
			            try{
			                xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");  //使用较新版本的 IE 创建 IE 兼容的对象（Msxml2.XMLHTTP）
			            }
			            catch (e){
			                try{
			                  xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //使用较老版本的 IE 创建 IE 兼容的对象（Microsoft.XMLHTTP）。
			                }
			                catch (failed){
			                      xmlhttp = false;  //如果失败则保证 request 的值仍然为 false。
			                }
			            }
			        }
			        return xmlhttp;
			}
    		window.ajax=function(url,fn) {
    			var xmlhttp = CreateXMLHttp();
				sendRequest();
				
				function getResult(){
				  if (xmlhttp.readyState == 4 && xmlhttp.status == 200){ //完成请求正确返回
				    //在这里填写具体的提取返回数据，并处理
				    if(fn){
				    	fn(xmlhttp.responseText)
				    }
				  }else{
				  	console.log(xmlhttp);
				  }
				}

				function sendRequest(){
				  if(xmlhttp){
				     xmlhttp.open("GET",url, true); 
				     xmlhttp.onreadystatechange = getResult; 
				     xmlhttp.send(null); 
				  } 
				} 

    		}
    		
    		
    	})(window);
    	if(!Array.prototype.forEach){
    		Array.prototype.forEach=function(fn){
    			if(fn){
    				for(var i in this){
    					fn(this[i],i,this);
    				}
    			}
    		}
    	}
    </script>
    <script>
    	
    	
    	function init(){
    		var url = "<%=path%>/servlet/AddressServlet?op=getProvinces";
    		ajax(url,function(data){
    			if(data){
	    			try{
						setProvinces(eval(data));
	                }
	                catch (e){
						console.log(e);
	                }
    			}
    		});
    	}
    	
    	function setProvinces(provinces){
			var sel_province = document.getElementById('sel_province');
			sel_province.length=0;     		
    		provinces.forEach(function(province){
    			var op = new Option(province.name,province.code);
    			sel_province.add(op);
    		});
    	}
    	
    	function setCitys(citys){
			var sel_city = document.getElementById('sel_city');
			sel_city.length=0;     		
    		citys.forEach(function(city){
    			var op = new Option(city.name,city.code);
    			sel_city.add(op);
    		});
    	}
    	
    	function setAreas(areas){
			var sel_area = document.getElementById('sel_area');
			sel_area.length=0;     		
    		areas.forEach(function(area){
    			var op = new Option(area.name,area.code);
    			sel_area.add(op);
    		});
    	}
    	
    	function changeProvince(province_code){
    		var url = "<%=path%>/servlet/AddressServlet?op=getCitys&code="+province_code;
    		ajax(url,function(data){
    			if(data){
	    			try{
						setCitys(eval(data));
	                }
	                catch (e){
						console.log(e);
	                }
    			}
    		});
    	}
    	
    	function changeCity(city_code){
    		var url = "<%=path%>/servlet/AddressServlet?op=getAreas&code="+city_code;
    		ajax(url,function(data){
    			if(data){
	    			try{
						setAreas(eval(data));
	                }
	                catch (e){
						console.log(e);
	                }
    			}
    		});
    		
    	}
    	
    	function changeArea(){
    		var sel_province = document.getElementById('sel_province');
    		var sel_city = document.getElementById('sel_city');
    		var sel_area = document.getElementById('sel_area');
    		
    		function getSelectText(sel){
    			return sel.options[sel.selectedIndex].text;
    		}
    		
    		var result = getSelectText(sel_province)+" --> "+getSelectText(sel_city)+" --> "+getSelectText(sel_area);
    		
    		show(result);
    	}
    	
    	function show(msg){
    		var result_box = document.getElementById('result');
    		result_box.innerHTML=msg;
    	}
    </script>
    
    省：<select name="province" id="sel_province" onchange="changeProvince(this.value)" ><option>请选择省份</option></select>&nbsp;&nbsp;&nbsp;
    市：<select name="city" id="sel_city" onchange="changeCity(this.value)"><option>请选择省份</option></select>&nbsp;&nbsp;&nbsp;
    区：<select name="area" id="sel_area" onchange="changeArea()" ><option>请选择省份</option></select><br>
    <h1 id="result"></h1>
    <a href="<%=path %>/user.action"><h1>进入用户列表</h1></a>
  </body>
</html>
