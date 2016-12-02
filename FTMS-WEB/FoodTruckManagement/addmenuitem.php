<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorItemName"] = "";
$_SESSION["errorItemPrice"] = "";
$c = new Controller();

try {
	$item_name = NULL;
	if (isset($_POST['item_name'])) {
		$item_name = Inputvalidator::validate_input($_POST['item_name']);
	}
	$item_popularity = NULL;
	if (isset($_POST['item_popularity'])) {
		$item_popularity = $_POST['item_popularity'];
	} else {
		$item_popularity = 0;
	}
	$item_price = NULL;
	if (isset($_POST['item_price'])) {
		$item_price = $_POST['item_price'];
	}
	$c->createItem($item_name, $item_popularity, $item_price);
	
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorItemName"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorItemPrice"] = substr($error, 1);
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