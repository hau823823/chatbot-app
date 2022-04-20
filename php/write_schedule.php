<?php
#ini_set('display_errors', 'on');
#error_reporting(E_ALL);
$host = 'localhost';
$user = 'root';
$passwd = 'weallgoodgood9487';
$database = 'graproj';
$table = 'schedule';

$connect = new mysqli($host, $user, $passwd, $database);

if ($connect->connect_error) {
    die("連線失敗: " . $connect->connect_error);
}

$uId = $_POST['uId'];
$schname = $_POST['schname'];
$sql = "INSERT INTO " . $table . " (uId, schname) VALUES ('" . $uId . "','" . $schname. "')";
$query = mysqli_query($connect, $sql);

?>