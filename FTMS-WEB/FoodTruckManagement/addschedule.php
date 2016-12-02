<?php
require_once 'controller/Controller.php';
session_start();
$_SESSION["errorScheduleName"] = "";
$_SESSION["errorScheduleDate"] = "";
$_SESSION["errorScheduleStartTime"] = "";
$_SESSION["errorScheduleEndTime"] = "";
$_SESSION["errorEndTimeBefore"] = "";
$c = new Controller();
try{
	$c->createSchedule($_POST['weekday'], $_POST['scheduledate'], $_POST['starttime'], $_POST['endtime']);
	
} 
catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorScheduleName"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorScheduleDate"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "3"){
			$_SESSION["errorScheduleStartTime"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "4"){
			$_SESSION["errorScheduleEndTime"] = substr($error, 1);
		}
		
		if (substr($error, 0, 1) == "4") {
			$_SESSION["errorEndTimeBefore"] = substr($error, 1);
		}
	}
}
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="refresh" content="0; url=/FoodTruckManagement/" />
</head>
</html>
