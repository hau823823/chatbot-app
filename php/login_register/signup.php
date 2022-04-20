<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['email'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("users", $_POST['username'], $_POST['password'], $_POST['email'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed(Username may  be used)";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
