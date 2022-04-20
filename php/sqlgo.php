<?php

    // connect mysql
    $pdo = new PDO("mysql:host=localhost;dbname=graproj;charset=utf8", "root", "weallgoodgood9487");
    session_start();
    $uId = "15";
    $sId = "1";
    // useful function
    function All($sql) {
        global $pdo;
        return $pdo-> query($sql)-> fetchAll();
    }
    // type = https://140.117.71.51/sqlgo.php?func=[your action]&place=[your place]
    switch($_GET["func"]){
        case "sel":
            // $p = $_GET["place"];
            // echo $p;"
            $sql = All("SELECT place FROM tripplace WHERE uId = '$uId' AND sId = '$sId'");
            foreach(All($sql) as $row) {
                echo $row["place"]."\n";
              }
        break;
        case "edit":
            $p = $_GET["place"];
            // $sql = "UPDATE t SET p= newvalue WHERE condition ";
            //echswql($connect,$sql);
        break;
        // 加號
        case "add":
            $p = $_GET["place"];
            // $sql = "INSERT "value" ";
            //echswql($connect,$sql);
        break;
        case "del":
            $p = $_GET["place"];
            // $sql = "DELETE FROM tWHERE condition;";
            //echswql($connect,$sql);
        break;
    }

    function echswql($connect,$sql){
        $res = $connect -> query($sql);
        echo json_encode($res,JSON_UNESCAPED_UNICODE); 
        if ($res->num_rows > 0) {
            // output data of each row
            while($row = $result->fetch_assoc()) {
              echo "id: " . $row["sId"]. "<br>";
              
            }
          } else {
            echo "0 results";
          }

        // echo json_encode($response,JSON_UNESCAPED_UNICODE); 
    }
?>