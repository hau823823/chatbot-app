<?php
#ini_set('display_errors', 'on');
#error_reporting(E_ALL);
$host = 'localhost';
$user = 'root';
$passwd = 'weallgoodgood9487';
$database = 'graproj';
$connect = new mysqli($host, $user, $passwd, $database);

if ($connect->connect_error) {
    die("連線失敗: " . $connect->connect_error);
}

$sId = $_POST['sId'];
$uId = $_POST['uId'];
$schname = $_POST['schname'];
$place = $_POST['place'];

$sql = "DELETE FROM tripplace WHERE sId LIKE '$sId' AND uId LIKE '$uId' AND schname LIKE '$schname' AND place LIKE '$place'";
$query = mysqli_query($connect, $sql);

?>