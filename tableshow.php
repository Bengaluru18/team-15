
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<?php
	session_start();
	$server     = "localhost";
	$username   = "id5251226_shubham";
	$password   = "shub2306";
	$db         = "id5251226_codeforgood";


		$conn = mysqli_connect( $server, $username, $password, $db );


	if (!$conn) {
    die( "Connection failed: " . mysqli_connect_error() );
	}
	if($_SESSION['loggedInUser']="State Monitor")
	{
		$qr="select f.region, b.b_id,b.sch_start,b.sch_end,f.f_name,e.eq_name from booking as b,equipment as e,farmer as f where b.f_id=f.f_id and b.eq_id=e.eq_id"
		$rs=mysqli_query($link,$qr);
		$row=mysqli_fetch_row($rs)
		echo "<table class='table table-striped table-bordered table-hover'><tr><th>Region</th>
        <th>Booking ID</th>
        <th>Booking Date</th>
        <th>End Date</th>
        <th>Farmer name</th>
        <th>Equipment</th>
        "
		while($row)
			echo "<tr><td>".$row[0]."</td><td>".$row[1]."</td><td>".$row[2]."</td><td>".$row[3]."</td><td>".$row[4]."</td><td>".$row[4]."</td></tr>"
	}
	
?>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>


