package ca.mcgill.ecse321.FTMS;
import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.FTMS.controller.TestFTMSController;
import ca.mcgill.ecse321.FTMS.persistence.TestPersistence;
/**
 * Class to test all units
 * @author Group 11
 *
 */
@RunWith(Suite.class)
@SuiteClasses({TestFTMSController.class, TestPersistence.class})
public class AllTests {

}
