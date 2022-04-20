<?php
#ini_set('display_errors', 'on');
#error_reporting(E_ALL);
$host = 'localhost';
$user = 'root';
#$passwd = 'rootroot';
$passwd = 'weallgoodgood9487';
$database = 'graproj';

$uId = '15';
$personid = '1';
$connect = new mysqli($host, $user, $passwd, $database);

if ($connect->connect_error) {
    die("連線失敗: " . $connect->connect_error);
}

$place_id = "SELECT pId FROM plan WHERE uId = $uId AND personid = $personid";


$sql="SELECT pName FROM place WHERE pId = $place_id";
$query = mysqli_query($connect,$sql);
//$place = mysqli_query($connect, "SELECT pName FROM place WHERE pId = $query");
$response = array();

while ($row = mysqli_fetch_assoc($query)) {
	array_push($response, 
	array(
		'pName' =>$row['pName'],
		)
	);
}


echo json_encode($response,JSON_UNESCAPED_UNICODE); 

?>
