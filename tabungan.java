import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
abstract class Student {
    private String nama;
    private int saldo;
    public Student(String nama) {
        this.nama = nama;
        this.saldo = 0;
    }
    public String getNama() {
        return nama;
    }
    public int getSaldo() {
        return saldo;
    }
    protected void tambahSaldo(int jumlah) {
        saldo += jumlah;
    }
    protected void kurangSaldo(int jumlah) {
        saldo -= jumlah;
    }
    public void save(int jumlah) {
        tambahSaldo(jumlah);
        System.out.println("Saldo " + getNama() + ": " + getSaldo());
    }
    public abstract void take(int jumlah);
    public abstract String getTipe();
}


class Reguler extends Student {
    public Reguler(String nama) {
        super(nama);
    }
    @Override
    public void take(int jumlah) {
        if (getSaldo() < jumlah) {
            System.out.println("Saldo " + getNama() + " tidak cukup");
        } else {
            kurangSaldo(jumlah);
            System.out.println("Saldo " + getNama() + ": " + getSaldo());
        }
    }
    @Override
    public String getTipe() {
        return "REGULER";
    }
}


class Beasiswa extends Student {
    public Beasiswa(String nama) {
        super(nama);
    }
    @Override
    public void take(int jumlah) {
        int bayar = jumlah - 1000;
        if (bayar < 0) bayar = 0;

        if (getSaldo() < bayar) {
            System.out.println("Saldo " + getNama() + " tidak cukup");
        } else {
            kurangSaldo(bayar);
            System.out.println("Saldo " + getNama() + ": " + getSaldo());
        }
    }
    @Override
    public String getTipe() {
        return "BEASISWA";
    }
}


public class tabungan {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.nextLine();
        Map<String, Student> akun = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String input = scan.nextLine();
            String[] cmd = input.split(" ");
            if (cmd[0].equals("CREATE")) {
                String tipe = cmd[1];
                String nama = cmd[2];
                if (akun.containsKey(nama)) {
                    System.out.println("Akun sudah terdaftar");
                } else {
                    if (tipe.equals("REGULER")) {
                        akun.put(nama, new Reguler(nama));
                    } else {
                        akun.put(nama, new Beasiswa(nama));
                    }
                    System.out.println(tipe + " " + nama + " berhasil dibuat");
                }
            } else if (cmd[0].equals("SAVE")) {
                String nama = cmd[1];
                int jumlah = Integer.parseInt(cmd[2]);
                if (!akun.containsKey(nama)) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    akun.get(nama).save(jumlah);
                }
            } else if (cmd[0].equals("TAKE")) {
                String nama = cmd[1];
                int jumlah = Integer.parseInt(cmd[2]);
                if (!akun.containsKey(nama)) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    akun.get(nama).take(jumlah);
                }
            } else if (cmd[0].equals("CHECK")) {
                String nama = cmd[1];
                if (!akun.containsKey(nama)) {
                    System.out.println("Akun tidak ditemukan");
                } else {
                    Student s = akun.get(nama);
                    System.out.println(s.getNama() + " | " + s.getTipe() + " | saldo: " + s.getSaldo());
                }
            }
        }
        scan.close();
    }
}