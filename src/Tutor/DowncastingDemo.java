package Tutor;

public class DowncastingDemo {
    public static void main(String[] args) {
        Pekerja pekerja = new CEO();
        pekerja.tampilkanPesan();
        
        CEO ceo = (CEO) pekerja;
        ceo.tampilkanIdentitas();
        ceo.canyaPendapatan();
    }
}