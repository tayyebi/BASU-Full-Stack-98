<?php
if (isset($_GET['☑'])){
    header('Location: index.php');
    exit;
}
?>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="static/favicon.ico">

    <title>You have a message</title>

    <!-- Bootstrap core CSS -->
    <link href="static/lib/bootstrap/min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="static/css/message.css" rel="stylesheet">
  </head>

  <body class="text-center">

    <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
      <header class="masthead mb-auto">
        <div class="inner">
          <h3 class="masthead-brand">KuKu</h3>
          <nav class="nav nav-masthead justify-content-center">
            <a class="nav-link active" href="index.php">Home</a>
            <a class="nav-link" href="static/login.php">Login</a>
            <a class="nav-link" href="http://forum.kukuapp.ir">FAQ</a>
          </nav>
        </div>
      </header>

      <main role="main" class="inner cover">
        <h1 class="cover-heading">There was something wrong</h1>
        <p class="lead">
            That's all we know! =))))))))
        </p>
        <p class="lead">
          <a href="index.php" class="btn btn-lg btn-secondary">Back Home</a>
        </p>
      </main>

      <footer class="mastfoot mt-auto">
        <div class="inner">
          <p>Kuku</p>
        </div>
      </footer>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="static/lib/jquery/slim.min.js" ></script>
    <script>window.jQuery || document.write('<script src="static/lib/jquery/slim.min.js"><\/script>')</script>
    <script src="static/lib/popper/min.js"></script>
    <script src="static/lib/bootstrap/min.js"></script>
  </body>
</html>
