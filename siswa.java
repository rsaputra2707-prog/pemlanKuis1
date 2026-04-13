import java.util.*;

abstract class Vehicle {
    protected String kode;
    protected String nama;
    protected int harga;
    protected boolean tersedia;

    public Vehicle(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.tersedia = true;
    }

    public String getKode() {
        return kode;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean status) {
        this.tersedia = status;
    }

    public abstract String getTipe();

    public int hitungBiaya(int hari, boolean promo) {
        int total = harga * hari;

        if (promo) {
            if (getTipe().equals("CAR")) total -= 20000;
            else total -= 10000;
        }

        return Math.max(total, 0);
    }

    public void detail() {
        String status = tersedia ? "TERSEDIA" : "DISEWA";
        System.out.println(kode + " | " + getTipe() + " | " + nama +
                " | harga: " + harga + " | status: " + status);
    }
}

class Car extends Vehicle {
    public Car(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    public String getTipe() {
        return "CAR";
    }
}

class Bike extends Vehicle {
    public Bike(String kode, String nama, int harga) {
        super(kode, nama, harga);
    }

    public String getTipe() {
        return "BIKE";
    }
}

public class siswa {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.nextLine();

        HashMap<String, Vehicle> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String input = scan.nextLine();
            String[] parts = input.split(" ");

            switch (parts[0]) {
                case "ADD":
                    String tipe = parts[1];
                    String kode = parts[2];
                    String nama = parts[3];
                    int harga = Integer.parseInt(parts[4]);

                    if (map.containsKey(kode)) {
                        System.out.println("Kendaraan sudah terdaftar");
                        break;
                    }

                    if (tipe.equals("CAR")) {
                        map.put(kode, new Car(kode, nama, harga));
                    } else {
                        map.put(kode, new Bike(kode, nama, harga));
                    }

                    System.out.println(tipe + " " + kode + " berhasil ditambahkan");
                    break;

                case "RENT":
                    kode = parts[1];
                    int hari = Integer.parseInt(parts[2]);
                    boolean promo = parts.length == 4;

                    if (!map.containsKey(kode)) {
                        System.out.println("Kendaraan tidak ditemukan");
                        break;
                    }

                    Vehicle v = map.get(kode);

                    if (!v.isTersedia()) {
                        System.out.println("Kendaraan sedang disewa");
                        break;
                    }

                    int total = v.hitungBiaya(hari, promo);
                    v.setTersedia(false);

                    System.out.println("Total sewa " + kode + ": " + total);
                    break;

                case "RETURN":
                    kode = parts[1];

                    if (!map.containsKey(kode)) {
                        System.out.println("Kendaraan tidak ditemukan");
                        break;
                    }

                    v = map.get(kode);

                    if (v.isTersedia()) {
                        System.out.println("Kendaraan belum disewa");
                        break;
                    }

                    v.setTersedia(true);
                    System.out.println(kode + " berhasil dikembalikan");
                    break;

                case "DETAIL":
                    kode = parts[1];

                    if (!map.containsKey(kode)) {
                        System.out.println("Kendaraan tidak ditemukan");
                        break;
                    }

                    map.get(kode).detail();
                    break;

                case "COUNT":
                    System.out.println("Total kendaraan: " + map.size());
                    break;
            }
        }
    }
}