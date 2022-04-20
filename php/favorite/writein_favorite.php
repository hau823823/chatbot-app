<?php
#ini_set('display_errors', 'on');
#error_reporting(E_ALL);
$host = 'localhost';
$user = 'root';
$passwd = 'weallgoodgood9487';
$database = 'graproj';
$table = 'favorite';

$connect = new mysqli($host, $user, $passwd, $database);

if ($connect->connect_error) {
    die("連線失敗: " . $connect->connect_error);
}

$uId = $_POST['uId'];
$pId = $_POST['pId'];
$sql = "INSERT INTO " . $table . " (uId, pId) VALUES ('" . $uId . "','" . $pId. "')";
$query = mysqli_query($connect, $sql);

?>