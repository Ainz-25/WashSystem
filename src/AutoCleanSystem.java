/**
 * Record pembungkus identitas objek/kendaraan yang dicuci.
 * @param identifier nomor plat atau nama pemilik
 * @param category jenis item (Mobil SUV, Motor Matic, Helm Fullface)
 * @param brand merk item
 */
record ItemInfo(String identifier, String category, String brand) {}

abstract class WashService {
    public static final double TAX_RATE = 0.10;
    public static final double MEMBER_DISCOUNT = 0.15;

    private ItemInfo itemInfo;
    private double price;
    private boolean isMember;

    public WashService(ItemInfo itemInfo, double price, boolean isMember) {
        this.itemInfo = itemInfo;
        this.price = price;
        this.isMember = isMember;
    }

    public ItemInfo getItemInfo() {
        return itemInfo;
    }

    public double getPrice() {
        return price;
    }

    public boolean isMember() {
        return isMember;
    }

    public double calculateDiscount() {
        return isMember ? price * MEMBER_DISCOUNT : 0.0;
    }

    public double calculateTax() {
        return (price - calculateDiscount()) * TAX_RATE;
    }

    public double calculateFinalPrice() {
        return (price - calculateDiscount()) + calculateTax();
    }

    public void printInvoice() {
        System.out.println("=== NOTA JASA PENCUCIAN ===");
        System.out.println("Identitas   : " + itemInfo.identifier());
        System.out.println("Kategori    : " + itemInfo.category());
        System.out.println("Brand       : " + itemInfo.brand());
        System.out.println("Biaya Awal  : Rp" + getPrice());
        System.out.println("Diskon      : Rp" + calculateDiscount());
        System.out.println("Pajak (10%) : Rp" + calculateTax());
        System.out.println("Total Bayar : Rp" + calculateFinalPrice());
        System.out.println("===========================");
    }
}

class CarWash extends WashService {
    public CarWash(ItemInfo itemInfo, double price, boolean isMember) {
        super(itemInfo, price, isMember);
    }
}

class MotorcycleWash extends WashService {
    public MotorcycleWash(ItemInfo itemInfo, double price, boolean isMember) {
        super(itemInfo, price, isMember);
    }
}



public class AutoCleanSystem {
    public static void main(String[] args) {
        ItemInfo car = new ItemInfo("N 1234 AB", "Mobil SUV", "Fortuner");
        CarWash order1 = new CarWash(car, 60000.0, true);

        ItemInfo motor = new ItemInfo("N 5678 CD", "Motor Matic", "Vario");
        MotorcycleWash order2 = new MotorcycleWash(motor, 25000.0, false);

        order1.printInvoice();
        System.out.println();
        order2.printInvoice();

    }
}