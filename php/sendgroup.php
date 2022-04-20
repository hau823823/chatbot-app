<?php

include 'db.php';
/*
pId, pName, pType, pIntroduce, pTel, pAddress, pTime, pIMG
*/
$pName=$_GET["title"];
$pType=$_GET["type"];
$pIntroduce=$_GET["content"];
$pAddress=$_GET["address"];
$pTel=$_GET["phone"];
$pTime=$_GET["pTime"];
$pIMG=$_GET["pic"];

header('Content-type: text/xml');

mysqli_query("SET NAMES 'utf8'", $con);

// create a new XML document
	$doc = new DomDocument('1.0', 'UTF-8');

// add root node
	$root = $doc->createElement('login');
	$root = $doc->appendChild($root);
	date_default_timezone_set("Asia/Taipei");
	$rdate =date("Y-m-d H:i:s");
    $sql="INSERT INTO place(pName, pType, pIntroduce, pTel, pAddress, pTime, pIMG) VALUES ('$pName','$pType','$pIntroduce','$pTel', '$pAddress','$pTime','$pIMG')";
    if (!mysqli_query($con, $sql) == -1)
    {
		 $child = $doc->createElement('result');
		 $child = $root->appendChild($child);

		 $value = $doc->createTextNode('-1');
		 $value =  $child->appendChild($value);
    } 
	else
	{
	
		   // add a child node for each field
		$child = $doc->createElement('result');
		$child = $root->appendChild($child);

		$value = $doc->createTextNode('1');
		$value =  $child->appendChild($value);
	}



$xml_string = $doc->saveXML();
echo $xml_string;

mysqli_close($con);
?>

