<?php
include_once('internal/db.php');

if (!isset($_POST['send']) and !isset($_DELETE)) // Note: Delete is ot defined yet so it's always false
{
    header('HTTP/1.0 405 Method Not Allowed');
    exit;
}

$sql = "INSERT INTO `posts` (`Text`, `UserId`) VALUES ('" . $_POST["Text"] . "', " . $_POST["UserId"] . ")";

if ($conn->query($sql) === TRUE) {
    if (!isset($_SERVER['PHP_AUTH_USER']))
        header('Location: message.php?☑');
    else
        echo "true";
} else {
    if (!isset($_SERVER['PHP_AUTH_USER']))
        header('Location: message.php?☒');
    else
        echo "false";
}

$conn->close();
?>