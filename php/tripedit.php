<?php
#ini_set('display_errors', 'on');
#error_reporting(E_ALL);
$host = 'localhost';
$user = 'root';
$passwd = 'weallgoodgood9487';
$database = 'graproj';

$uId = '15';

$connect = new mysqli($host, $user, $passwd, $database);

if ($connect->connect_error) {
    die("連線失敗: " . $connect->connect_error);
}


$query = mysqli_query($connect, "SELECT place FROM tripplace WHERE uId = $uId");

$response = array();

while ($row = mysqli_fetch_assoc($query)) {
	array_push($response, 
	array(
		'place' =>$row['place'],
		)
	);
}


echo json_encode($response,JSON_UNESCAPED_UNICODE); 

?>