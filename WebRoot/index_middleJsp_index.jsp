<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
response.setStatus(301);
response.setHeader( "Location",path+"/userInitDataAction!getUserDataBySession.action" );
response.setHeader( "Connection", "close" );
%>
