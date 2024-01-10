public class TrainEx {

  String honkSound = "choo-choo";

  public void honk()	{
	System.out.println(honkSound);
  }
	public static void main(String[] args )	{
		new Vehicle().honk();
		new Train().honk();

		Train   aTrain   = new Train();
		Vehicle aVehicle = (Train)aTrain;
		Vehicle aVehicleOtherReference = aVehicle;
		System.out.println(aTrain + "	" +aTrain);
		System.out.println(aVehicle + "	" +aVehicle);
		aVehicle.honk();
		System.out.println("aVehicle.soManyWheels() = " + aVehicle.soManyWheels());
		aVehicle.honk();
		aVehicle.setSound("ring ring");
		aTrain.setSound("ring ring");
		aVehicle.honk();
		aTrain.honk();

		// aVehicle is still of type Vehicle, so it doesn't know what lies in the realm of the train
		// this is why the line below throws an error
//		aVehicle.onlyAtrainCanDoThis();
		// Nonetheless, this works! The reason being an object of type Vehicle can access methods in Vehicle
		aVehicle.onlyAvehicleCanDoThis();
		// Train, since it is the child of Vehicle can access both methods.
		aTrain.onlyAvehicleCanDoThis();
		aTrain.onlyAtrainCanDoThis();

		aVehicle.wheels = 0;
		System.out.println("aVehicle.soManyWheels() = " + aVehicle.soManyWheels());
		System.out.println("aTrain.soManyWheels() = " + aTrain.soManyWheels());
		System.out.println("aVehicleOtherReference.soManyWheels() = " + aVehicleOtherReference.soManyWheels());
		System.out.println("aVehicle.wheels = " + aVehicle.wheels);
	}
}

