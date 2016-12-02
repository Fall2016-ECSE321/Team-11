<!DOCTYPE html>
<html>
	<head>
		<meta charset = "UTF-8">
		<title>Food Truck Management</title>
		<hr>
		<h1 style="text-align:left;float:left;">FTMS</h1> 
		<h2 style="text-align:right;float:right;">v1.0</h2> 
		<hr style="clear:both;"/>
		<style>
			.error {color: #FF0000;}
		</style>
	</head>
	<body>
		<?php
			require_once "model/Schedule.php";
			require_once "model/Employee.php";
			require_once 'model/StaffManager.php';
			require_once 'persistence/PersistenceFoodTruckManager.php';
			require_once "model/Equipment.php";
			require_once "model/MenuItem.php";
			require_once "model/Supply.php";
			require_once "model/OrderTracker.php";
			require_once "model/Menu.php";
			require_once "persistence/PersistenceMenuFTMS.php";
		
			session_start();
			
			//Retrieve data from model
			$pm = new PersistenceFoodTruckManager();
			$sm = $pm->loadDataFromStore();
			$pm2 = new PersistenceMenuFTMS();
			$mm = $pm2->loadDataFromStore();
			
			echo "<form action='register.php' method='post'>";
			
			echo "<p>Select Employee: <select name='employeespinner'>";
			foreach ($sm->getEmployees() as $employee) {
				echo "<option>" . $employee->getStaffName() . "</option>";
			}
			echo "</select><span class='error'>";
			if (isset($_SESSION['errorRegisterEmployee']) && !empty($_SESSION['errorRegisterEmployee'])){
				echo " * " . $_SESSION["errorRegisterEmployee"];
			}
			echo "</span></p>";
			
			echo "<p>Select Schedule: <select name='schedulespinner'>";
			foreach ($sm->getSchedules() as $schedule){
				echo "<option>" . $schedule->getWeekday() . "</option>";
			}
			echo "</select><span class='error'>";
			if (isset($_SESSION['errorRegisterSchedule']) && !empty($_SESSION['errorRegisterSchedule'])){
				echo " * " . $_SESSION["errorRegisterSchedule"];
			}
			echo "</span></p>";
			
			echo "<p><input type='submit' value='Register'/></p>";
			echo "</form>";
		?>
		<form action="addemployee.php" method="post">
			<p>Name: <input type="text" name="employee_name"/> Staff Role: <input type="text" name="employee_role"/> Hours? <input type="text" name="employee_hours"/>
			<span class="error">
			<?php 
			if (isset($_SESSION['errorEmployee']) && !empty($_SESSION['errorEmployee'])){
				echo " * " . $_SESSION["errorEmployee"];
			}
			?>
			</span></p>
			<p><input type="submit" value="Add Employee"/></p>
		</form>
		<form action="addschedule.php" method="post">
			<p>Weekday: <input type="text" name="weekday"/>
			<span class="error">
			<?php 
			if (isset($_SESSION['errorScheduleName']) && !empty($_SESSION['errorScheduleName'])){
				echo " * " . $_SESSION["errorScheduleName"];
			}
			?>
			</span></p>
			<p>Date: <input type="date" name="scheduledate" value="<?php echo date('Y-m-d'); ?>" />
			<span class="error">
			<?php 
			if (isset($_SESSION['errorScheduleDate']) && !empty($_SESSION['errorScheduleDate'])){
				echo " * " . $_SESSION["errorScheduleDate"];
			}
			?>
			</span></p>
			<p>Start Time: <input type="time" name="starttime" value="<?php echo date('H:i'); ?>" />
			<span class="error">
			<?php 
			if (isset($_SESSION['errorScheduleStartTime']) && !empty($_SESSION['errorScheduleStartTime'])){
				echo " * " . $_SESSION["errorScheduleStartTime"];
			}
			?>
			</span></p>
			<p>End Time: <input type="time" name="endtime" value="<?php echo date('H:i'); ?>" />
			<span class="error">
			<?php 
			if (isset($_SESSION['errorScheduleEndTime']) && !empty($_SESSION['errorScheduleEndTime'])){
				echo " * " . $_SESSION["errorScheduleEndTime"];
			}
			elseif (isset($_SESSION['errorEndTimeBefore']) && !empty($_SESSION['errorEndTimeBefore'])){
				echo " * " . $_SESSION["errorEndTimeBefore"];
			}
			?>
			</span></p>
			<p><input type="submit" value="Add Schedule"/></p>
		</form>
		<?php
		// This code places an order of desired menu items if possible with current supplies
		echo "<form action='order.php' method='post'>";
		echo "<p>Order? <select name='orderspinner'>";
		foreach ($mm->getMenuItems() as $MenuItem) {
			echo "<option>" . $MenuItem->getName() . "</option>";
		}
		echo "</select><span class='error'>";
		if (isset($_SESSION['errorOrder']) && !empty($_SESSION['errorOrder'])) {
			echo " * " . $_SESSION["errorOrder"];
		} if (isset($_SESSION['errorInsufficientSupplies']) && !empty($_SESSION['errorInsufficientSupplies'])) {
			echo " * " . $_SESSION["errorInsufficientSupplies"];
		}
		echo "</span></p>";
		echo "<p><input type='submit' value='Order' /></p>";
		echo "</form>";
		
		// This code creates supplies necessary for a previously added menu item
		echo "<form action='addsupplytomenuitem.php' method='post'>";
		echo "<p>Menu Item? <select name='menuitemspinner'>";
		foreach ($mm->getMenuItems() as $MenuItem) {
			echo "<option>" . $MenuItem->getName() . "</option>";
		}
		echo "</select><span class='error'>";
		if (isset($_SESSION['errorMenuItemBlank']) && !empty($_SESSION['errorMenuItemBlank'])) {
			echo " * " . $_SESSION["errorMenuItemBlank"];
		} echo "</span></p>";
		echo "<p>Supplies? <select name='supplyspinner'>";
		foreach ($mm->getSupplies() as $Supply) {
			echo "<option>" . $Supply->getName() . "</option>";
		}
		echo "</select><span class='error'>";		
		if (isset($_SESSION['errorSuppliesBlank']) && !empty($_SESSION['errorSuppliesBlank'])) {
			echo " * " . $_SESSION["errorSuppliesBlank"];
		} echo "</span></p>";
		echo "<p><input type='submit' value='Add Supplies to Menu Item' /></p>";
		echo "</form>";
		?>
		
		
		<form action="addequipment.php" method="post">
			<p>Equipment Name? <input type="text" name="equipment_name" /><span class="error">
			<?php echo "</select><span class='error'>";
						if (isset($_SESSION['errorEquipmentName']) && !empty($_SESSION['errorEquipmentName'])) {
							echo " * " . $_SESSION["errorEquipmentName"]; 
						} echo "</span></p>";?></span></p> 
						
			<p>Equipment Quantity? <input type="number" name="equipment_quantity" min="0" /><span class ="error">
				<?php echo "</select><span class='error'>";
						if (isset($_SESSION['errorEquipmentQuantity']) && !empty($_SESSION['errorEquipmentQuantity'])) {
							echo " * " . $_SESSION["errorEquipmentQuantity"];
						} echo "</span></p>";?></span></p>
				
			<p><input type="submit" value="Add Equipment" /></p>
		</form>
		
		
		<form action="addsupply.php" method="post">
			<p>Supply Name? <input type="text" name="supply_name" /><span class="error">
				<?php echo "</select><span class='error'>";
					if (isset($_SESSION['errorSupplyName']) && !empty($_SESSION['errorSupplyName'])) {
						echo " * " . $_SESSION["errorSupplyName"]; 
					} echo "</span></p>";?></span></p>
			<p>Supply Quantity? <input type="number" name="supply_quantity" min="0" /><span class="error">
				<?php echo "</select><span class='error'>";
					if (isset($_SESSION['errorSupplyQuantity']) && !empty($_SESSION['errorSupplyQuantity'])) {
						echo " * " . $_SESSION["errorSupplyQuantity"];
					} echo "</span></p>";?></span></p>
			<p><input type="submit" value="Add Supply" /></p>
		</form>
		
		
		<form action="addmenuitem.php" method="post">
			<p>Menu Item Name? <input type="text" name="item_name" /><span class="error">
			<?php echo "</select><span class='error'>";
				if (isset($_SESSION['errorItemName']) && !empty($_SESSION['errorItemName'])) {
					echo " * " . $_SESSION["errorItemName"]; 
				} echo "</span></p>";?></span></p>
			<p>Menu Item Popularity (if known)? <input type="number" name="item_popularity" min="0" /></p>
			<p>Menu Item Price? <input type="number" name="item_price" min="0" step="0.01" /><span class="error">
			<?php echo "</select><span class='error'>";
				if (isset($_SESSION['errorItemPrice']) && !empty($_SESSION['errorItemPrice'])) {
					echo " * " . $_SESSION["errorItemPrice"]; 
				} echo "</span></p>";?></span></p>
			<p><input type="submit" value="Add Menu Item" /></p>
		</form>
	</body>
</html>