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

$uId = $_POST['uId'];
$uId = mysqli_real_escape_string($connect, stripslashes(htmlspecialchars($uId)));
$schname = $_POST['schname'];
$schname = mysqli_real_escape_string($connect, stripslashes(htmlspecialchars($schname)));

$sql = "SELECT sId, schname, place FROM tripplace WHERE uId LIKE '$uId' AND schname LIKE '$schname' ORDER BY sId";
$query = mysqli_query($connect, $sql);

$response = array();

while ($row = mysqli_fetch_assoc($query)) {
	array_push($response, 
	array(
		'sId' =>$row['sId'],
		'schname' =>$row['schname'],
		'place' =>$row['place'])
	);
}

echo json_encode($response,JSON_UNESCAPED_UNICODE);
?>