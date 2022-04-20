<?php
    include("db.php");       
    $id = $_GET['id'];
       
    header('Content-type: text/xml');

// create a new XML document
	$doc = new DomDocument('1.0', 'UTF-8');

// add root node
	$root = $doc->createElement('login');
	$root = $doc->appendChild($root);
	date_default_timezone_set("Asia/Taipei");
    
    $sql = "Delete FROM place where pId = '$id'";
    
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
