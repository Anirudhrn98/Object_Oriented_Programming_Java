class Vehicle {

    String honkSound = "vehicle honk";
    int wheels       = 4;

    public void setSound (String honkSound)    {
        this.honkSound = honkSound;
    }
    public String toString ()    {
        return "v";
    }
    public void setSoManyWheels (int wheels)    {
        this.wheels = wheels;
    }
    public int soManyWheels ()	{
        return wheels;
    }
    public void honk()	{
        System.out.println(honkSound);
    }

}


class Car extends Vehicle {

    String honkSound = "honk";
    int wheels       = 4;

    public void setSound (String honkSound)    {
        this.honkSound = honkSound;
    }
    public int soManyWheels ()    {
        return wheels;
    }
    public void onlyACarCanDoThis()	{
        System.out.println("onlyACarCanDoThis");
    }
    public void honk()	{
        System.out.println(honkSound);
    }
    public static void main(String[] args )	{
        new Vehicle().honk();
    }
}


public class PoliceCar extends Car {  // Car extends Vehicle

    String honkSound = "tatue tata";
    int wheels       = 4;

    public void setSound (String honkSound)    {
        this.honkSound = honkSound;
    }
    public int soManyWheels ()    {
        return wheels;
    }
    public void onlyACarPoliceCanDoThis()	{
        System.out.println("onlyACarPoliceCanDoThis");
    }
    public void honk()	{
        System.out.println(honkSound);
    }
    public void onlyACarCanDoThis()	{
        System.out.println("onlyApoliceCarCanDoThis");
    }


    public static void main(String[] args )	{

        PoliceCar aPoliceCar = new PoliceCar();
        Car aCar = aPoliceCar;
        Vehicle aVehicle = aCar;

        aCar.honk();
        aPoliceCar.onlyACarCanDoThis();
        aCar.onlyACarCanDoThis();
//        aVehicle.onlyACarCanDoThis();
    }
}

