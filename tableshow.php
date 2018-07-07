<?php

	$server     = "localhost";
	$username   = "id5251226_shubham";
	$password   = "shub2306";
	$db         = "id5251226_codeforgood";

// Create a connection
		$conn = mysqli_connect( $server, $username, $password, $db );

// Check connection
	if (!$conn) {
    die( "Connection failed: " . mysqli_connect_error() );
	}
	else
		echo "successfull";

?>



