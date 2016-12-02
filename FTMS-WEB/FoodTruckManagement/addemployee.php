<?php
require_once 'controller/Controller.php';

session_start();

$c = new Controller();

try{
	$c->createEmployee($_POST['employee_name'], $_POST['employee_role'], $_POST['employee_hours']);
	$_SESSION["errorEmployee"] = "";
} catch (Exception $e){
	$_SESSION["errorEmployee"] = $e->getMessage();
}
?>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="refresh" content="0; url=/FoodTruckManagement/" />
	</head>
</html>