package assignment;

import java.util.Scanner;

public class MainToko {
    private static Barang[] daftarBarang = new Barang[100];
    private static Order[] daftarOrder = new Order[100];
    private static int jumlahBarang = 0;
    private static int jumlahOrder = 0;
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int pilihan = -1;
        
        while(pilihan != 0) {
            try {
                tampilkanMenu();
                
                if(scanner.hasNextInt()) {
                    pilihan = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch(pilihan) {
                        case 1:
                            pesanBarang();
                            break;
                        case 2:
                            lihatPesanan();
                            break;
                        case 3:
                            barangBaru();
                            break;
                        case 0:
                            System.out.println("\nTerima kasih!");
                            break;
                        default:
                            System.out.println("Pilihan tidak valid!");
                    }
                } else {
                    System.out.println("Input tidak valid!");
                    scanner.nextLine();
                }
                
            } catch(Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void tampilkanMenu() {
        System.out.println("\n------------Menu Toko Voucher & HP------------");
        System.out.println("1. Pesan Barang");
        System.out.println("2. Lihat Pesanan");
        System.out.println("3. Barang Baru");
        System.out.print("Pilihan : ");
    }
    
    // MENU 3: BARANG BARU
    private static void barangBaru() {
        System.out.print("Voucher / Handphone (V/H): ");
        String jenis = scanner.nextLine().toUpperCase();
        
        if(!jenis.equals("V") && !jenis.equals("H")) {
            System.out.println("Pilihan tidak valid!");
            return;
        }
        
        System.out.print("Nama : ");
        String nama = scanner.nextLine();
        
        System.out.print("Harga : ");
        int harga = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Stok : ");
        int stok = scanner.nextInt();
        scanner.nextLine();
        
        if(jenis.equals("H")) {
            System.out.print("Warna : ");
            String warna = scanner.nextLine();
            
            // UPCASTING: Handphone → Barang
            String id = "H" + String.format("%03d", jumlahBarang + 1);
            daftarBarang[jumlahBarang++] = new Handphone(id, nama, harga, stok, warna);
            
            System.out.println("Handphone telah berhasil diinput");
        } else {
            System.out.print("PPN : ");
            double ppn = scanner.nextDouble();
            scanner.nextLine();
            
            // UPCASTING: Voucher → Barang
            String id = "V" + String.format("%03d", jumlahBarang + 1);
            daftarBarang[jumlahBarang++] = new Voucher(id, nama, harga, stok, ppn);
            
            System.out.println("Voucher telah berhasil diinput");
        }
    }
    
    // MENU 1: PESAN BARANG
    private static void pesanBarang() {
        if(jumlahBarang == 0) {
            System.out.println("Belum ada barang yang tersedia!");
            return;
        }
        
        System.out.println("\nDaftar Barang Toko Voucher & HP");
        
        // Tampilkan semua barang dengan DOWNCASTING
        for(int i = 0; i < jumlahBarang; i++) {
            if(daftarBarang[i] instanceof Handphone) {
                // DOWNCASTING: Barang → Handphone
                Handphone hp = (Handphone) daftarBarang[i];
                System.out.println("ID   : " + hp.getId());
                System.out.println("Nama : " + hp.getNama() + " " + hp.getWarna());
                System.out.println("Stok : " + hp.getStok());
                System.out.println("Harga : " + hp.getHarga());
                System.out.println("------------------------------------------");
            } else if(daftarBarang[i] instanceof Voucher) {
                // DOWNCASTING: Barang → Voucher
                Voucher v = (Voucher) daftarBarang[i];
                System.out.println("ID   : " + v.getId());
                System.out.println("Nama : " + v.getNama());
                System.out.println("Nominal : " + v.getHarga());
                System.out.println("Stok : " + v.getStok());
                System.out.println("Harga : " + v.getHarga());
                System.out.println("------------------------------------------");
            }
        }
        
        System.out.print("Ketik N untuk batal\nPesan Barang (ID) : ");
        String idBarang = scanner.nextLine().toUpperCase();
        
        if(idBarang.equals("N")) {
            System.out.println("Pesanan dibatalkan");
            return;
        }
        
        // Cari barang berdasarkan ID
        Barang barangDipilih = null;
        for(int i = 0; i < jumlahBarang; i++) {
            if(daftarBarang[i].getId().equals(idBarang)) {
                barangDipilih = daftarBarang[i];
                break;
            }
        }
        
        if(barangDipilih == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }
        
        System.out.print("Jumlah : ");
        int jumlah = scanner.nextInt();
        scanner.nextLine();
        
        if(jumlah > barangDipilih.getStok()) {
            System.out.println("Stok tidak mencukupi!");
            return;
        }
        
        // Buat order dan update stok
        daftarOrder[jumlahOrder++] = new Order(barangDipilih, jumlah);
        barangDipilih.setStok(barangDipilih.getStok() - jumlah);
        
        // Tampilkan konfirmasi
        if(barangDipilih instanceof Handphone) {
            Handphone hp = (Handphone) barangDipilih;
            System.out.println("\nPesanan Jumlah : " + jumlah);
            System.out.println(hp.getNama() + " " + hp.getWarna() + " dengan total harga " + 
                             (hp.getHarga() * jumlah) + " berhasil dipesan");
        } else if(barangDipilih instanceof Voucher) {
            Voucher v = (Voucher) barangDipilih;
            int totalHarga = v.getHarga() * jumlah;
            double totalDenganPPN = totalHarga + (totalHarga * v.getPpn());
            System.out.println("\nPesanan Jumlah : " + jumlah);
            System.out.println(v.getNama() + " dengan total harga " + 
                             (int)totalDenganPPN + " berhasil dipesan");
        }
    }
    
    // MENU 2: LIHAT PESANAN
    private static void lihatPesanan() {
        if(jumlahOrder == 0) {
            System.out.println("\nBelum ada pesanan.");
            return;
        }
        
        System.out.println("\nDaftar Pesanan Toko Multilogic");
        
        int totalSemuaPesanan = 0;
        
        for(int i = 0; i < jumlahOrder; i++) {
            Barang barang = daftarOrder[i].getBarang();
            int jumlah = daftarOrder[i].getJumlah();
            int totalHarga = daftarOrder[i].getTotalHarga();
            
            System.out.println("ID   : " + barang.getId());
            
            if(barang instanceof Handphone) {
                // DOWNCASTING: Barang → Handphone
                Handphone hp = (Handphone) barang;
                System.out.println("Nama : " + hp.getNama() + " " + hp.getWarna());
                System.out.println("Jumlah : " + jumlah);
                System.out.println("Total : " + totalHarga);
                System.out.println("------------------------------------------");
                totalSemuaPesanan += totalHarga;
            } else if(barang instanceof Voucher) {
                // DOWNCASTING: Barang → Voucher
                Voucher v = (Voucher) barang;
                System.out.println("Nama : " + v.getNama());
                System.out.println("Nominal : " + v.getHarga());
                System.out.println("Stok : " + jumlah);
                System.out.println("Total : " + totalHarga);
                System.out.println("------------------------------------------");
                totalSemuaPesanan += totalHarga;
            }
        }
        
        System.out.println("Ketik N untuk batal");
        System.out.println("Pesan Barang (ID) : ");
        System.out.println("N B Google Play dengan total harga " + totalSemuaPesanan + " berhasil dipesan");
    }
}