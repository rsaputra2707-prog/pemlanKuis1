import java.util.*;
public class moo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String nama;
        while (true) {
            nama = scan.nextLine();
            if (nama.matches("[a-zA-Z]+")) break;
            System.out.println("Mooo! Nama sapi harus pakai huruf, bukan angka atau simbol!");
        }
        int berat;
        while (true) {
            try {
                berat = Integer.parseInt(scan.nextLine());
                if (berat >= 1) break;
            } catch (Exception e) {}
            System.out.println("Sapi astral? Masukkan berat yang valid dulu, bestie!");
        }
        String layanan;
        while (true) {
            layanan = scan.nextLine();
            if (layanan.equals("spa") || layanan.equals("potong_kuku") || layanan.equals("grooming")) break;
            System.out.println("Pilih spa, potong_kuku, atau grooming! Sapi kamu mau dirawat apa, sih?");
        }
        String kelas;
        while (true) {
            kelas = scan.nextLine();
            if (kelas.equals("reguler") || kelas.equals("vip")) break;
            System.out.println("Pilih reguler atau vip! Sapi kamu mau treatment sultan atau biasa aja?");
        }
        double hargaPerKg = 0;
        if (layanan.equals("spa")) hargaPerKg = 8000;
        else if (layanan.equals("potong_kuku")) hargaPerKg = 6000;
        else hargaPerKg = 10000;
        double biayaDasar = berat * hargaPerKg;
        double diskon = (berat > 30) ? 0.1 * biayaDasar : 0;
        double tambahanVIP = kelas.equals("vip") ? 0.2 * biayaDasar : 0;
        double subtotal = biayaDasar - diskon + tambahanVIP;
        double pajak = 0.08 * subtotal;
        double total = subtotal + pajak;
        if (nama.equals("Moo") || nama.equals("Mooo") || nama.equals("Moooo")) {
            total = 0;
        }
        System.out.println("============= NOTA KLINIK SAPI =============");
        System.out.println("Nama Sapi: " + nama);
        System.out.println("Berat: " + berat + " kg");
        System.out.println("Jenis Layanan: " + layanan);
        System.out.println("Kelas: " + kelas);
        System.out.println("Biaya Dasar: Rp " + biayaDasar);
        System.out.println("Diskon: Rp " + diskon);
        System.out.println("Biaya Tambahan VIP: Rp " + tambahanVIP);
        System.out.println("Subtotal: Rp " + subtotal);
        System.out.println("Pajak: Rp " + pajak);
        System.out.println("Total Biaya: Rp " + total);
        System.out.println("============================================");
        if (total == 0) {
            System.out.println("Terima kasih, " + nama + " ! Sapi spesial memang beda perlakuan~");
        } else {
            System.out.println("Terima kasih, " + nama + " ! Semoga sapinya makin glow up.");
        }
    }
}