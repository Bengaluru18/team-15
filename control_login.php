<?php

	$response=array();
	if($_SERVER['REQUEST_METHOD']=='GET')
	{
			if($_GET['password'] != null and $_GET['userid'] != null)
			{
			    
				$link=mysqli_connect("localhost","id5251226_shubham","shub2306","id5251226_codeforgood");
				$p=$_GET['password'];
				$u=$_GET['userid'];
				$qr="select * from ISAP_EMP where ename='$u' and password='$p';";
				$rs=mysqli_query($link,$qr);
				$o=mysqli_num_rows($rs);
				if($o>0)	
				{	
					$response['error']=false;
					$response['message']="Login Successfull";
					
				}
				else
				{
					$response['error']=true;
					$response['message']="Login Unsuccessfull";
					
				}
				
				
			} else{
			    $response['error']=true;
				$response['message']=$_GET['password'];
					   
			}
	}
	else
	{
		$response['error']=true;
		$response['message']="Invalid Request";
		
	}
	
	echo json_encode($response);
?>