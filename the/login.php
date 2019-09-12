<?php

include_once('internal/db.php');

$INVALID = false;
if (isset($_POST['login']))
{
  $sql = "SELECT Id FROM `users` WHERE `Username` = '" . $_POST["username"] . "' AND `Password` = '" . $_POST["password"] . "'";
  $result = $conn->query($sql);

  if ($result->num_rows > 0) {
      // output data of each row
      if ($row = $result->fetch_assoc()) {
        setcookie("USERID", $row['Id'], time() + (86400 * 30), "/"); // 86400 = 1 day
        header('Location: message.php?☑');
        exit;
      }
  } else {
      $INVALID = true;
  }
  $conn->close();
}

include_once('internal/header.php');
?>

    <form class="form-signin" method="post">

      <?php
      if ($INVALID) {
      ?>
      <div class="alert alert-danger" role="alert">
        Login failed
      </div>
      <?php
      }
      ?>

      <img class="mb-4" src="static/content/logo.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <label for="inputUsername" class="sr-only">Username</label>
      <input name="username" type="text" id="inputUsername" class="form-control" placeholder="Username" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Keep me in your mind
        </label>
      </div>
      <input name="login" class="btn btn-lg btn-primary btn-block" type="submit" value="Sign in" />
      <a class="btn btn-lg btn-block" href="register.php">New to KuKu?</a>
      <p class="mt-5 mb-3 text-muted">&copy; 2019</p>
    </form>
<?php
include_once('internal/footer.php');
?>