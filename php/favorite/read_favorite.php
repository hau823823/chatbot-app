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

$sql = "SELECT pId, pName, pIntroduce, pTel, pAddress, pTime, pIMG FROM place WHERE 	pId in (select pId from favorite where uId like '$uId')";
$query = mysqli_query($connect, $sql);

$response = array();

while ($row = mysqli_fetch_assoc($query)) {
	array_push($response, 
	array(
		'uId'=> $uId,
		'pId' =>$row['pId'],
		'pName' =>$row['pName'],
		'pIntroduce' =>$row['pIntroduce'],
		'pTel' =>$row['pTel'],
		'pAddress' =>$row['pAddress'],
		'pTime' =>$row['pTime'],
		'pIMG' =>$row['pIMG'])
	);
}

echo json_encode($response,JSON_UNESCAPED_UNICODE);
?>