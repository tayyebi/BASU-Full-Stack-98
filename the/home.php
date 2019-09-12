<?php
include_once('internal/AController.php');
include_once('internal/db.php');

class HomeController extends AController
{
  function GET(){
    parent::GET();
    // parent::getRequest('Id')
    $sql = "SELECT * FROM `posts` INNER JOIN `users` ON `posts`.UserId = `users`.Id ORDER BY posts.`Submit` DESC LIMIT 30";
    $conn = (new Datab())->Open();
    $result = $conn->query($sql);
    $data = [];
    while ($row = $result->fetch_assoc()) {
        // $data[array_shift($row)] = $row;
        $data[] = $row;
    }
    parent::setData($data);
    parent::returnData();
  }
  function POST()
  {
    parent::POST();
    parent::setData("Hi");
    parent::returnData();
  }
}

$controller = new HomeController();