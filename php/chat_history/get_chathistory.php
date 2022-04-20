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

$check = $_POST['uId'];

$sql = "SELECT umessage, rmessage FROM chathistory WHERE uId LIKE '$check'";
$query = mysqli_query($connect, $sql);

$response = array();

while ($row = mysqli_fetch_assoc($query)) {
	array_push($response, 
	array(
		'umessage' =>$row['umessage'],
		'rmessage' =>$row['rmessage'])
	);
}

echo json_encode($response,JSON_UNESCAPED_UNICODE);
?>