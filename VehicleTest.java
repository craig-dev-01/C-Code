/* Craig 
 * 
 * 
 * 
 * 
 * Description: This program establishes 7 classes 1 parent "Vehicle" class, 4 child classes "Bus", "Train", "Car", and "Motorcycle". 
 * Additionally, this program establishes "RepairShop" Class and includes one test class. The test class opens and reads "Vehicles.txt",
 * Loads an array, parses the information in the array to an array of type Vehicle, and then displays all of the information. Additionally, 
 * This program creates a RepairShop object, loads the object's inventory of vehicles and displays the contents. 
 * 
 */
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
public class VehicleTest {

	public static void main(String[] args) throws IOException{
		
		//Create file object using Vehicles.txt
		File file = new File("Vehicles.txt");
		
		//Create Scanner reader
		Scanner inputFile = new Scanner(file);
		
		//Variable for the number of vehicles in Vehicles.txt
		int numVehicles = inputFile.nextInt();
		
		//2D array to hold type and name before making object array:
		String[][] strArray = new String[numVehicles][2];
		
		//1D Vehicle object array:
		Vehicle[] vehicleArray = new Vehicle[numVehicles];
		
		//loop that reads items in file and loads strArray with vehicle type and vehicle name:
		for(int i = 0; i < strArray.length; i++) {
			String type = inputFile.next();
			String name = inputFile.nextLine();
			name = name.trim();
			
			for(int j = 0; j < strArray[0].length; j++) {
				if(j == 0) {
					strArray[i][j] = type;
				}
				else {
					strArray[i][j] = name;
				}
			}
		}

		//load Vehicle array from strArray:
		
		//String objects for comparisons and instantiating vehicle objects:
		String bus = "bus";
		String train = "train";
		String car = "car";
		String motorcycle = "motorcycle";
		
		for(int i = 0; i < strArray.length; i++) {
			
			if(bus.equals(strArray[i][0])){//test for and make Bus object
				
				vehicleArray[i] = new Bus(strArray[i][1]);
			}
			else if(train.equals(strArray[i][0])){//test for and make Train object
				vehicleArray[i] = new Train(strArray[i][1]);
			}
			else if(car.equals(strArray[i][0])){//test for and make car object
				vehicleArray[i] = new Car(strArray[i][1]);
			}
			else{//Make motorcycle object
				vehicleArray[i] = new Motorcycle(strArray[i][1]);
			}
						
		}
		
		//Display vehicle objects in table:
		printVehicles(vehicleArray);
		
		//Create repair shop 
		RepairShop repairShop = new RepairShop();
		
		//fill repair shop
		repairShop.fillRepairShop(vehicleArray);
		
		//display vehicles in repair shop
		repairShop.printRepairShopDetails();
		

	}//main
	
	//This method prints the vehicle array in a formatted manner
	public static void printVehicles(Vehicle[] vehArray) {
		
		String header = """
				----------------------------------------------------------------------------
				Type			Name			Sound
				----------------------------------------------------------------------------""";
		System.out.println(header);
		
		//iterate through vehicle array and print Type, name, and sound
		for(int i = 0; i < vehArray.length; i++) {
			
			System.out.printf("%-22s%-25s%s\n", vehArray[i].getType(), vehArray[i].getName(), vehArray[i].sound());
			
		}
	}
}//printVehicles

/*Vehicle class has type and name attributes and has functionality to return default sound. 
 * All child classes override sound and return a more specific sound
 */
class Vehicle{
	private String type;
	private String name;
	
	public Vehicle(String type, String name) {
		this.type = type;
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String sound() {
		return "sound";
	}
}//Vehicle

//Bus class has no attributes it uses parent class attributes
class Bus extends Vehicle{
	
	public Bus(String name) {
		//call parent constructor
		super("Bus", name);
			
	}
	@Override
	public String sound() {
		return "Rum-rum-rum-rummm";
	}
}//Bus

//Car Class has no attributes it uses parent class attributes
class Car extends Vehicle{
	
	public Car(String name) {
		super("Car", name);
	}
	
	@Override
	public String sound() {
		return "Vroom-vroom-vroommm";
	}
}//Car

//Motorcycle Class has no attributes it uses parent class attributes
class Motorcycle extends Vehicle{
	
	public Motorcycle(String name) {
		super("Motorcycle", name);
	}
	
	@Override
	public String sound() {
		return "Bom-bom-bom";
	}
	
}//Train Class has no attributes it uses parent class attributes
class Train extends Vehicle{
	
	public Train(String name) {
		
		super("Train", name);
	}
	
	@Override
	public String sound() {
		return "Chooga-chooga-chooga";
	}
}//Train

/* RepairShop has attributes to track number of cars and motorcycles in the garage
 * Only cars and motorcycles are repaired in the shop
 * The vehicle objects are stored in a static array of length 100
 * The class has functionality to take cars and motorcycles out a Vehicle array and load into the shop array
 * The class has a function to display total vehicles in the shop 
 */
class RepairShop{
	private static int numCars;
	private static int numMotorcycles;
	private static Vehicle[] vehiclesToRepair = new Vehicle[100];
	
	public RepairShop() {
	}
	
	//This method fills the repair shop array object with cars and motorcycles from the main method's
	//vehicle array.
	public void fillRepairShop (Vehicle[] vehiclesArray) {
		
		//load only cars and motorcycles from array and increment numCars and numMotorcycles each time
		int vehiclesToRepairIndex = 0;
		for(int i = 0; i < vehiclesArray.length ; i++) {
			if(vehiclesArray[i] instanceof Car) {
				numCars++;
				vehiclesToRepair[vehiclesToRepairIndex] = vehiclesArray[i];
				vehiclesToRepairIndex++;
				
			}
			else if(vehiclesArray[i] instanceof Motorcycle) {
				numMotorcycles++;
				vehiclesToRepair[vehiclesToRepairIndex] = vehiclesArray[i];
				vehiclesToRepairIndex++;
			}
		}
	}
	
	//this method prints the vehicles in the repair shop
	public void printRepairShopDetails() {
		
		String header = """
				--------------------------------------------
				Car and Motorcycle Repair Shop
				--------------------------------------------""";
		//print header and number of cars and motorcycles:
		System.out.printf(header + "\n" + "Number of cars: \t%d\nNumber of Motorcycles: \t%d\n", numCars, numMotorcycles);
		
		//loop through array and print vehicles
		for(int i = 0; i < vehiclesToRepair.length ; i++) {
			if(vehiclesToRepair[i] == null) {//break loop if element is null
				break;
			}
			//print type and name in formatted manner
			System.out.printf("%-20s---%s\n",vehiclesToRepair[i].getType(), vehiclesToRepair[i].getName());
		}
	}
}//RepairShop

