<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorOrder"] = "";
$_SESSION["errorInsufficientSupplies"] = "";
$c = new Controller();

try {
	$menuItem = NULL;
	if (isset($_POST['orderspinner'])) {
		$menuItem = $_POST['orderspinner'];
	}
	$c->createOrder($menuItem);
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorOrder"] = $e->getMessage();
		}
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorInsufficientSupplies"] = $e->getMessage();
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