/**
 * The <code>TrainCarNode</code> class contains a node with references to the nodes before 
 * and after the current node.
 *
 *
 * @author Piotr Milewski
 *    email: piotr.milewski@stonybrook.edu
 *    Stony Brook ID: 112291666
 **/

public class TrainCarNode{

    TrainCar currNode;
    TrainCarNode nextNode;
    TrainCarNode prevNode;

    public TrainCarNode(){
	currNode = null;
	nextNode = null;
	prevNode = null;
    }

    public TrainCarNode(TrainCar newNode){
	currNode = newNode;
	nextNode = null;
	prevNode = null;
    }

    public TrainCarNode(TrainCar newNode, TrainCarNode nextNode, TrainCarNode prevNode){
	currNode = newNode;
	nextNode = nextNode;
	prevNode = prevNode;
    }

    public TrainCar getCurrNode(){
	return currNode;
    }

    public TrainCar setCurrNode(TrainCar newNode){
	TrainCar oldCurrNode = newNode;
	currNode = newNode;
	return oldCurrNode;
    }
    
    public TrainCarNode getNextNode(){
	return nextNode;
    }

    public TrainCarNode setNextNode(TrainCarNode newNode){
	TrainCarNode oldNextNode = nextNode;
	nextNode = newNode;
	return oldNextNode;
    }

    public TrainCarNode getPrevNode(){
	return prevNode;
    }
    
    public TrainCarNode setPrevNode(TrainCarNode newNode){
	TrainCarNode oldPrevNode = prevNode;
	prevNode = newNode;
	return oldPrevNode;
    }
    
    public String toString(){
	String output = "";
	output += "\nCurrent Node: " + currNode;
	output += "\nNext Node: " + nextNode;
	output += "\nPrev Node: " + prevNode + "\n";
	return output;
    }
}
