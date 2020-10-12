import java.util.Objects;

public class MovingHomesDemo {
    private static Address myAddress = new Address("Beukelsweg", 7, "Rotterdam");

    public static void main(String[] args) throws InterruptedException {
        Thread postalService = new Thread(() -> {
            Address currentAddress = myAddress;
            while (currentAddress.equals(myAddress)) {
                System.out.println("Deliver mail to current address: " + currentAddress);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Customer has moved; forward mail to new address: " + myAddress);
        });

        postalService.start();

        //Thread.sleep(1000);
        myAddress = new Address("Kruisboog", 42, "Veenendaal");
        System.out.println("Wij zijn verhuisd naar " + myAddress);
    }
}

class Address {
    private String street;
    private int houseNumber;
    private String city;

    public Address(String street, int houseNumber, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return houseNumber == address.houseNumber &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, city);
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s", street, houseNumber, city);
    }
}