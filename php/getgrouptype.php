<?php

include 'db.php';

header('Content-type: text/xml');

$type=$_GET["type"];

$us = mysqli_query($con, "select * from place where pType='$type'");
$rows_num = mysqli_num_rows($us);

// create a new XML document
$doc = new DomDocument('1.0', 'UTF-8');

// add root node
$root = $doc->createElement('talk');
$root = $doc->appendChild($root);
/*
pId, pName, pType, pIntroduce, pTel, pAddress, pTime, pIMG
*/
while($data=mysqli_fetch_row($us))
{
    $inner = $doc->createElement('item');
    $inner = $root->appendChild($inner);

    // add a child node for each field
    $child = $doc->createElement('id');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[0]");
    $value = $child->appendChild($value);

    $child = $doc->createElement('title');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[1]");
    $value = $child->appendChild($value);

    $child = $doc->createElement('content');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[3]");
    $value = $child->appendChild($value);
	
	$child = $doc->createElement('pic');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[7]");
    $value = $child->appendChild($value);

    $child = $doc->createElement('address');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[5]");
    $value = $child->appendChild($value);

    $child = $doc->createElement('pnum');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[6]");
    $value = $child->appendChild($value);

    $child = $doc->createElement('type');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[2]");
    $value = $child->appendChild($value);
	
    $child = $doc->createElement('user');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("user");
    $value = $child->appendChild($value);

    $child = $doc->createElement('area');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("area");
    $value = $child->appendChild($value);
    $value = $child->appendChild($value);
	
    $child = $doc->createElement('rdate');
    $child = $inner->appendChild($child);
    $value = $doc->createTextNode("$data[4]");
    $value = $child->appendChild($value);
    $value = $child->appendChild($value);
}

$xml_string = $doc->saveXML();
echo $xml_string;

mysqli_close($con);
?>

