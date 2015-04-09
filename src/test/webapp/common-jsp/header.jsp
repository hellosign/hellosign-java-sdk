<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>${param.title} | HelloSign</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<link rel="stylesheet" href="/css/style.css" />

<link rel="shortcut icon" href="/favicon.ico?v=2" />
<link rel="apple-touch-icon"
    href="/apple-touch-icon-precomposed.png?v=2" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

    <div class="container">

        <!-- Static navbar -->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed"
                        data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                        aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">hellosign-java-sdk Demos</a>
                </div>
                <div id="navbar"
                    class="navbar-collapse collapse nav nav-pills pull-right">
                    <ul class="nav navbar-nav">
                        <li class="dropdown"><a href="#" class="dropdown-toggle"
                            data-toggle="dropdown" role="button" aria-expanded="false">Demos
                                <span class="caret"></span>
                        </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="/embeddedSigningDemo.jsp">Signing</a></li>
                                <li><a href="/embeddedRequestingDemo.jsp">Requesting</a></li>
                                <li><a href="/embeddedTemplateDemo.jsp">Template Creation</a></li>
                                <li><a href="/embeddedTemplateEditDemo.jsp">Editing a Template</a></li>
                                <li><a href="/embeddedTemplateSigningDemo.jsp">Signing with a Template</a></li>
                                <li><a href="/embeddedTemplateRequestingDemo.jsp">Requesting with a Template</a></li>
                                <li><a href="/oauthDemo.jsp">OAuth 2.0 Demo</a></li>
                            </ul></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle"
                            data-toggle="dropdown" role="button" aria-expanded="false">Docs
                                <span class="caret"></span>
                        </a>
                            <ul class="dropdown-menu" role="menu">
	                            <li><a href="/javadoc">JavaDoc</a></li>
	                            <li><a href="https://www.hellosign.com/api/documentation"
	                               target="_blank">API Doc</a></li>
                            </ul></li>
                        <li><a
                            href="https://www.github.com/hellofax/hellosign-java-sdk"
                            target="_blank">GitHub</a></li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
            <!--/.container-fluid -->
        </nav>
    </div>
    <div class="container">
        <div class="clearfix">
            <div class="jumbotron">
                <h2>${param.title}</h2>
                <p class="lead">${param.description}</p>
                <div id="demoContainer">
                    <div class="error-message">${param.errorMessage}</div>
                    <form method="post" enctype="multipart/form-data">