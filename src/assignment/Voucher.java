package assignment;

public class Voucher extends Barang {
    private double ppn;
    
    public Voucher(String id, String nama, int harga, int stok, double ppn) {
        super(id, nama, harga, stok);
        this.ppn = ppn;
    }
    
    public double getPpn() {
        return ppn;
    }
    
    public void setPpn(double ppn) {
        this.ppn = ppn;
    }
}