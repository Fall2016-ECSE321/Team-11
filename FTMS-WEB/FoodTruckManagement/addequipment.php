<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorEquipmentName"] = "";
$_SESSION["errorEquipmentQuantity"] = "";
$c = new Controller();

try {
	$equipment_name = NULL;
	if (isset($_POST['equipment_name'])) {
		$equipment_name = Inputvalidator::validate_input($_POST['equipment_name']);
	}
	$equipment_quantity = NULL;
	if (isset($_POST['equipment_quantity'])) {
		$equipment_quantity = $_POST['equipment_quantity'];
	}
	$c->createEquipment($equipment_name, $equipment_quantity);
	
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorEquipmentName"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorEquipmentQuantity"] = substr($error, 1);
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