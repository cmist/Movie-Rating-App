<!DOCTYPE HTML>
<html lang='de' dir='ltr'>
<head>
	<meta charset="utf-8" />
	<title>Movie rating - ${pagetitle}</title>
	<link type="text/css" href="css/style.css" rel="stylesheet" media="screen" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	<script>
  		$(function() {
  			$("#datepicker1").datepicker();
		});
  	</script>
</head>
<body>
<div id="wrapper">
	<div id="logo">Movie Rating App<br>Software Engineering Example</div>
    <ul id="navigation">
    	<li><a href="index" title="Index">View Homesite</a></li>
	<#if navtype == "unregisteredUser">
    	<li><a href="UnregisteredUser?page=register" title="register user in the app">UnregisteredUser</a></li>	
	<#elseif navtype == "registeredUser">
		<li><a href="RegisteredUser?page=MovieOverview" title="show movie overview of all movies in the app">MovieOverview</a></li>
		<li><a href="RegisteredUser?page=AddMovie" title="add a new movie to the database">Add Movie</a></li>
	<#else>
    	<li><a href="UnregisteredUser" title="UnregisteredUser GUI">UnregisteredUser</a></li>
		<li><a href="RegisteredUser?page=MovieOverview" title="RegisteredUser GUI">RegisteredUser</a></li>
	</#if>
    </ul>
	<div id="content">