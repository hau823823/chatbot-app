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

$loginitem = $_POST['loginitem'];
$loginitem = mysqli_real_escape_string($connect, stripslashes(htmlspecialchars($loginitem)));

$sql = "SELECT 	uId, email FROM users WHERE username LIKE '$loginitem'";
$query = mysqli_query($connect, $sql);

$response = array();

while ($row = mysqli_fetch_assoc($query)) {
	array_push($response, 
	array(
		'uId' =>$row['uId'],
		'email' =>$row['email'])
	);
}

echo json_encode($response,JSON_UNESCAPED_UNICODE);
?>