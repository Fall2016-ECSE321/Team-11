<?php
require_once 'controller/Controller.php';
session_start();
$_SESSION["errorRegisterEmployee"] = "";
$_SESSION["errorRegisterSchedule"] = "";
$c = new Controller();
try {
	$employee = NULL;
	if (isset($_POST['employeespinner'])) {
		$employee = $_POST['employeespinner'];
	}
	$schedule = NULL;
	if (isset($_POST['schedulespinner'])) {
		$schedule = $_POST['schedulespinner'];
	}
	$c->register($employee, $schedule);
	
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorRegisterEmployee"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorRegisterSchedule"] = substr($error, 1);
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