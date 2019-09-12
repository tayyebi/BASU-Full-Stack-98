<?php
include_once('internal/db.php');

if (isset($_SERVER['PHP_AUTH_USER']))
{
    $sql = "SELECT Id FROM `users` WHERE `Username` = '" . $_SERVER["PHP_AUTH_USER"] . "' AND `Password` = '" . $_SERVER["PHP_AUTH_PW"] . "'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        // output data of each row
        if ($row = $result->fetch_assoc()) {
            $_COOKIE['USERID'] = $row['Id'];
        }
    }
}

if (!isset($_COOKIE['USERID'])) // or basic auth for api
{
  header('HTTP/1.0 403 Forbidden');
  exit;
}
?>