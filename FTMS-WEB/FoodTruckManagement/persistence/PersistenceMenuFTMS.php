<?php
class PersistenceMenuFTMS {
	private $filename;
	function __construct($filename = 'datamenu.txt') {
		$this->filename = $filename;
	}

	function loadDataFromStore() {
		if (file_exists($this->filename)) {
			$str = file_get_contents($this->filename);
			$mm = unserialize($str);
		} else {
			$mm = Menu::getInstance();
		}
		return $mm;
	}

	function writeDataToStore($mm) {
		$str = serialize($mm);
		file_put_contents($this->filename, $str);
	}
}
?>