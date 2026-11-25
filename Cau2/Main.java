
import java.util.Scanner;

abstract class SinhVien {
    protected String maSV;
    protected int soTinChi;

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();

    public String getMaSV() {
        return maSV;
    }
}

class SinhVienChinhQuy extends SinhVien {
    public SinhVienChinhQuy(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L;
    }
}

class SinhVienChatLuongCao extends SinhVien {
    public SinhVienChatLuongCao(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L * 3 / 2;
    }
}

class SinhVienLienThong extends SinhVien {
    public SinhVienLienThong(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L + 2000000L;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        SinhVien[] danhSach = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");

            String maHe = data[0];
            String maSV = data[1];
            int soTinChi = Integer.parseInt(data[2]);

            switch (maHe) {
                case "CQ":
                    danhSach[i] = new SinhVienChinhQuy(maSV, soTinChi);
                    break;
                case "CLC":
                    danhSach[i] = new SinhVienChatLuongCao(maSV, soTinChi);
                    break;
                case "LT":
                    danhSach[i] = new SinhVienLienThong(maSV, soTinChi);
                    break;
            }
        }

        for (SinhVien sv : danhSach) {
            System.out.println(sv.getMaSV() + " " +
                    (sv instanceof SinhVienChinhQuy ? "CQ" :
                    sv instanceof SinhVienChatLuongCao ? "CLC" : "LT")
                    + " " + sv.hocPhi());
        }

        sc.close();
    }
}
