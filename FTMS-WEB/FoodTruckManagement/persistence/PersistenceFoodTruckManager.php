<?php
class PersistenceFoodTruckManager {
	private $filename;

	function __construct($filename = 'data.txt') {
		$this->filename = $filename;
	}
	function loadDataFromStore() {
		if (file_exists($this->filename)) {
			$str = file_get_contents($this->filename);
			$sm= unserialize($str);
		} else {
			$sm = StaffManager::getInstance();
		}
		return $sm;
	}
	function writeDataToStore($sm) {
		$str = serialize($sm);
		file_put_contents($this->filename, $str);
	}
}
?>