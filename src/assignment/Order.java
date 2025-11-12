package assignment;

public class Order {
    private int jumlah;
    private int totalHarga;
    private Barang barang;
    
    public Order(Barang barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
        hitungTotalHarga();
    }
    
    private void hitungTotalHarga() {
        this.totalHarga = barang.getHarga() * jumlah;
        
        // Jika voucher, tambahkan PPN
        if(barang instanceof Voucher) {
            Voucher v = (Voucher) barang;
            this.totalHarga = (int)(totalHarga + (totalHarga * v.getPpn()));
        }
    }
    
    public int getJumlah() {
        return jumlah;
    }
    
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
        hitungTotalHarga();
    }
    
    public int getTotalHarga() {
        return totalHarga;
    }
    
    public Barang getBarang() {
        return barang;
    }
    
    public void setBarang(Barang barang) {
        this.barang = barang;
        hitungTotalHarga();
    }

}