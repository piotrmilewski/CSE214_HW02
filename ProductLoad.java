public class ProductLoad{

    String name;
    double weight;
    double value;
    boolean isDangerous;

    public ProductLoad(){
	name = "";
	weight = 0.0;
	value = 0.0;
	isDangerous = false;
    }

    public ProductLoad(String newName, double newWeight, double newValue, boolean newdangerState){
	name = newName;
	weight = newWeight;
	value = newValue;
	isDangerous = newdangerState;
    }

    public String getName(){
	return name;
    }

    public String setName(String newName){
	String oldName = name;
	name = newName;
	return oldName;
    }

    public double getWeight(){
	return weight;
    }

    //set to throw exceptions for illegal arguments
    public double setWeight(double newWeight){
	double oldWeight = weight;
	weight = newWeight;
	return oldWeight;
    }

    public double getValue(){
	return value;
    }

    //set to throw exceptions for illegal arguments
    public double setValue(double newValue){
	double oldValue = value;
	value = newValue;
	return oldValue;
    }

    public boolean getDangerState(){
	return isDangerous;
    }

    public boolean setDangerState(boolean newDangerState){
	boolean oldDangerState = isDangerous;
	isDangerous = newDangerState;
	return oldDangerState;
    }
}
