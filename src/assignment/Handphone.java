package assignment;

public class Handphone extends Barang {
    private String warna;
    
    public Handphone(String id, String nama, int harga, int stok, String warna) {
        super(id, nama, harga, stok);
        this.warna = warna;
    }
    
    public String getWarna() {
        return warna;
    }
    
    public void setWarna(String warna) {
        this.warna = warna;
    }
}