<?php
#ini_set('display_errors', 'on');
#error_reporting(E_ALL);
$host = 'localhost';
$user = 'root';
$passwd = 'weallgoodgood9487';
$database = 'graproj';

$uId = '15';
$personid = '1';

$connect = new mysqli($host, $user, $passwd, $database);

if ($connect->connect_error) {
    die("連線失敗: " . $connect->connect_error);
}


//呼叫query方法(SQL語法)
$memberData = $connect->query("SELECT pId FROM plan WHERE uId = $uId AND personid = $personid ");

$response = array();
//有資料筆數大於0時才執行
if ($memberData->num_rows > 0) {
//讀取剛才取回的資料
    while ($row = $memberData->fetch_assoc()) {

        //print_r($row[pId]);

        $Data = mysqli_query($connect, "SELECT pName FROM place WHERE pId = $row[pId]");
        while ($row = mysqli_fetch_assoc($Data)) {
			array_push($response, 
			array(
				'pName' =>$row['pName'],
				)
			);
		}

    }
}
echo json_encode($response,JSON_UNESCAPED_UNICODE);

?>