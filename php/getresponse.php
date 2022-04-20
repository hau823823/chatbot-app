<?php
$host = 'localhost';
//改成你登入phpmyadmin帳號
$user = 'kuo';
//改成你登入phpmyadmin密碼
$passwd = 'graproj';

$database = 'graproj';
//實例化mysqli(資料庫路徑, 登入帳號, 登入密碼, 資料庫)
$connect = new mysqli($host, $user, $passwd, $database);

$input = $_SERVER['QUERY_STRING'];

if ($connect->connect_error) {
    die("連線失敗: " . $connect->connect_error);


}

$str_sec = explode("%20",$input);

//設定連線編碼，防止中文字亂碼
$connect->query("SET NAMES 'utf8'");

$selectSql = "SELECT * FROM response WHERE id = $str_sec[0] ";
//呼叫query方法(SQL語法)
$memberData = $connect->query($selectSql);
//有資料筆數大於0時才執行
if ($memberData->num_rows > 0) {
//讀取剛才取回的資料
    while ($row = $memberData->fetch_assoc()) {
        print_r($row[cn_answer]);
    }
}

$selectSql = "SELECT * FROM place WHERE pId = $str_sec[1] ";
//呼叫query方法(SQL語法)
$memberData = $connect->query($selectSql);
//有資料筆數大於0時才執行
if ($memberData->num_rows > 0) {
//讀取剛才取回的資料
    while ($row = $memberData->fetch_assoc()) {
        print_r($row[pName]);
    }
}
?>