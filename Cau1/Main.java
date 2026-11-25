import java.util.Scanner;
import java.util.Locale;

class SinhVien {
    private String maSV;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String maSV, String hoTen, String ngaySinh) {
        this.maSV = maSV;
        this.hoTen = chuanHoaTen(hoTen);
        this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
    }

    private String chuanHoaTen(String ten) {
        ten = ten.trim().replaceAll("\\s+", " ");
        String[] words = ten.toLowerCase(Locale.ROOT).split(" ");

        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.append(Character.toUpperCase(word.charAt(0)));
            result.append(word.substring(1));
            result.append(" ");
        }

        return result.toString().trim();
    }

    private String chuanHoaNgaySinh(String ngaySinh) {
        String[] parts = ngaySinh.split("/");

        int ngay = Integer.parseInt(parts[0]);
        int thang = Integer.parseInt(parts[1]);
        int nam = Integer.parseInt(parts[2]);

        return String.format("%02d/%02d/%04d", ngay, thang, nam);
    }

    public void hienThi() {
        System.out.println(maSV + " " + hoTen + " " + ngaySinh);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        SinhVien[] danhSach = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            String maSV = sc.nextLine();
            String hoTen = sc.nextLine();
            String ngaySinh = sc.nextLine();

            danhSach[i] = new SinhVien(maSV, hoTen, ngaySinh);
        }

        for (SinhVien sv : danhSach) {
            sv.hienThi();
        }

        sc.close();
    }
}
