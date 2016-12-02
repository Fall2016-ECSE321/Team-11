ch<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorMenuItemBlank"] = "";
$_SESSION["errorSuppliesBlank"] = "";
$c = new Controller();

try {
	$item = NULL;
	if (isset($_POST['menuitemspinner'])) {
		$item = $_POST['menuitemspinner'];
	}
	$supplies = NULL;
	if (isset($_POST['supplyspinner'])) {
		$supplies = $_POST['supplyspinner'];
	}
	$c->createSuppliesToMenuItem($item, $supplies);
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorMenuItemBlank"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorSuppliesBlank"] = substr($error, 1);
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