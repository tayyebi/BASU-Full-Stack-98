<?php
class Datab
{
	function Open(){
        $servername = "localhost";
        $username = "root";
        $password = "123";
        $dbname = "kuku";

        // Create connection
        $mconn = new mysqli($servername, $username, $password, $dbname);
        // Check connection
        if ($mconn->connect_error) {
            die("Connection failed: " . $mconn->connect_error);
        }
        return $mconn;
	}
}

$conn = (new Datab())->Open();