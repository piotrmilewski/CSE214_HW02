public class TrainCar{

    double length;
    double weight;
    ProductLoad load;

    public TrainCar(){
	length = 0.0;
	weight = 0.0;
	load = null;
    }

    public TrainCar(double newLength, double newWeight){
	length = newLength;
	weight = newWeight;
	load = null;
    }
    
    public TrainCar(double newLength, double newWeight, ProductLoad newLoad){
	length = newLength;
	weight = newWeight;
	load = newLoad;
    }

    public double getLength(){
	return length;
    }

    public double getWeight(){
	return weight;
    }

    public ProductLoad getLoad(){
	return load;
    }

    public ProductLoad setLoad(ProductLoad newLoad){
	ProductLoad oldLoad = load;
	load = newLoad;
	return oldLoad;
    }

    public boolean isEmpty(){
	return (load == null);
    }
}
