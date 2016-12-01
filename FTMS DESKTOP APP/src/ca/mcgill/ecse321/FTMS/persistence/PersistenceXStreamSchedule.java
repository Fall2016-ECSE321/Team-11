package ca.mcgill.ecse321.FTMS.persistence;

import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import com.thoughtworks.xstream.XStream;
/**
 * This class loads the OrderFTMS XML file
 * @author Group 11
 */
public class PersistenceXStreamSchedule {

	private static XStream xstream = new XStream();
	private static String filename = "Schedule.xml";
	/**
	 * This method saves the object into the XMl
	 * @param obj
	 * @return true if the file can be written
	 */
	
	public static boolean saveToXMLwithXStream(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj); // save our xml file
	
		try {
				FileWriter writer = new FileWriter(filename);
				writer.write(xml);
				writer.close();
				return true;
				} catch (IOException e) {
					e.printStackTrace(); 
					return false; 
				}
	}
	
	/**
	 * This method loads the xml file
	 * @return true if file can be read
	 */
	public static Object loadFromXMLwithXStream() {
		xstream.setMode(XStream.ID_REFERENCES);
		try {
				FileReader fileReader = new FileReader(filename); // load our xml file
				return xstream.fromXML(fileReader);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	public static void setAlias(String xmlTagName, Class<?> className) {
		xstream.alias(xmlTagName, className);
	}
	public static void setFilename(String fn) {
		filename = fn;
	}
}
