package Tutor;

public class UpcastingDemo {
    public static void main(String[] args) {
        Pekerja pekerja = new Pekerja();
        pekerja.tampilkanPesan();

        pekerja = new CEO();
        pekerja.tampilkanPesan();

        Karyawan karyawan = new Karyawan();
        pekerja = (Pekerja)karyawan;
        pekerja.tampilkanPesan();

    }
}