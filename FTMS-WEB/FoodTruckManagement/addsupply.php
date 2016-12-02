<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorSupplyName"] = "";
$_SESSION["errorSupplyQuantity"] = "";
$c = new Controller();

try {
	$supply_name = NULL;
	if (isset($_POST['supply_name'])) {
		$supply_name = Inputvalidator::validate_input($_POST['supply_name']);
	}
	$supply_quantity = NULL;
	if (isset($_POST['supply_quantity'])) {
		$supply_quantity = $_POST['supply_quantity'];
	}
	$c->createSupply($supply_name, $supply_quantity);
	
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorSupplyName"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorSupplyQuantity"] = substr($error, 1);
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