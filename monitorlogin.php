<?php
	session_start();
	if( isset( $_POST['login'] ) ) {
	     $hashedPass="";
    
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
            
					$_SESSION['loggedInUser'] = $formadmin;
                    
                if(	$_SESSION['loggedInUser']=="Project Monitor")
                {
                    header( "Location: HTML/page-analytic-project.html" );
                }
                else
                {
                    
                    header( "Location: HTML/page-analytic-state.html" );
                }
        } 
				else
				{
					
					 header( "Location: page-login-failed.html" );
					
				}
}
?>