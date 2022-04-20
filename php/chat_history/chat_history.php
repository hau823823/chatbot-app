<?php
#ini_set('display_errors', 'on');
#error_reporting(E_ALL);
$host = 'localhost';
$user = 'root';
$passwd = 'weallgoodgood9487';
$database = 'graproj';
$table = 'chathistory';

$connect = new mysqli($host, $user, $passwd, $database);

if ($connect->connect_error) {
    die("連線失敗: " . $connect->connect_error);
}

$uId = $_POST['uId'];
$umessage = $_POST['umessage'];
$rmessage = $_POST['rmessage'];
$sql = "INSERT INTO " . $table . " (uId, umessage, rmessage) VALUES ('" . $uId . "','" . $umessage . "','" . $rmessage . "')";
$query = mysqli_query($connect, $sql);

?>