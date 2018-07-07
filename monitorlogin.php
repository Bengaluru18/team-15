<?php
	
	if( isset( $_POST['login'] ) ) {
	    
    
    // build a function to validate data
    function validateFormData( $formData ) {
        $formData = trim( stripslashes( htmlspecialchars( $formData ) ) );
        return $formData;
    }
	    $formUser = validateFormData( $_POST['username'] );
		$formPass = validateFormData( $_POST['password'] );
		//	echo "pass1:".$formPass;
		$formadmin = validateFormData( $_POST['admin'] );
		
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
	
		$query = "select * from ISAP_EMP where ename='$formUser' and role='$formadmin';";
		$rs=mysqli_query($conn,$query);
			
				  while( $row = mysqli_fetch_assoc($rs) ) {
                        $hashedPass = $row['password'];
                        }	
				 if(  $formPass ==$hashedPass  ) {
            
        
                echo"login successfull";
            //header( "Location: clients.php" );
        } 
				else
				{
					echo "login unsuccessfull";
					
				}
}
?>