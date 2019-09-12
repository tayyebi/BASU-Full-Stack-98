<?php
if (isset($_COOKIE['USERID'])) {
    unset($_COOKIE['USERID']); 
    setcookie('USERID', null, -1, '/'); 
}
header('Location: login.php');
exit;
?>