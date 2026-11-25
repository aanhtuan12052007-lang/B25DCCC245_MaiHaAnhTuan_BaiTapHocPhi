import java.util.*;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

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

    public int getSoTinChi() {
        return soTinChi;
    }

    public abstract String getMaHe();
}

class SinhVienChinhQuy extends SinhVien {
    public SinhVienChinhQuy(String maSV, int soTinChi) {
        super(maSV, soTinChi);
    }

    @Override
    public long hocPhi() {
        return soTinChi * 450000L;
    }

    @Override
    public String getMaHe() {
        return "CQ";
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

    @Override
    public String getMaHe() {
        return "CLC";
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

    @Override
    public String getMaHe() {
        return "LT";
    }
}

class QuanLyHocPhi {
    private ArrayList<SinhVien> danhSach = new ArrayList<>();
    private ArrayList<String> danhSachLoi = new ArrayList<>();
    private HashSet<String> maSVHopLe = new HashSet<>();

    public void themSinhVien(String maHe, String maSV, String tinChi)
            throws DuLieuKhongHopLeException {

        if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
            throw new DuLieuKhongHopLeException("Ma he khong hop le");
        }

        int soTinChi;

        try {
            soTinChi = Integer.parseInt(tinChi);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("So tin chi khong hop le");
        }

        if (soTinChi < 1 || soTinChi > 30) {
            throw new DuLieuKhongHopLeException("So tin chi khong hop le");
        }

        if (maSVHopLe.contains(maSV)) {
            throw new DuLieuKhongHopLeException("Ma sinh vien bi trung");
        }

        SinhVien sv;

        switch (maHe) {
            case "CQ":
                sv = new SinhVienChinhQuy(maSV, soTinChi);
                break;
            case "CLC":
                sv = new SinhVienChatLuongCao(maSV, soTinChi);
                break;
            default:
                sv = new SinhVienLienThong(maSV, soTinChi);
        }

        danhSach.add(sv);
        maSVHopLe.add(maSV);
    }

    public void themLoi(String maSV) {
        danhSachLoi.add(maSV);
    }

    public void hienThi() {
        for (String maSV : danhSachLoi) {
            System.out.println("INVALID " + maSV);
        }

        danhSach.sort((a, b) -> {
            if (a.hocPhi() != b.hocPhi()) {
                return Long.compare(b.hocPhi(), a.hocPhi());
            }
            return a.getMaSV().compareTo(b.getMaSV());
        });

        long tong = 0;

        for (SinhVien sv : danhSach) {
            System.out.println(sv.getMaSV() + " " + sv.getMaHe() + " " + sv.hocPhi());
            tong += sv.hocPhi();
        }

        System.out.println("TONG " + tong);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        QuanLyHocPhi ql = new QuanLyHocPhi();

        for (int i = 0; i < n; i++) {
            String dong = sc.nextLine().trim();
            String[] data = dong.split("\\s+");

            String maHe = data[0];
            String maSV = data[1];
            String tinChi = data[2];

            try {
                ql.themSinhVien(maHe, maSV, tinChi);
            } catch (DuLieuKhongHopLeException e) {
                ql.themLoi(maSV);
            }
        }

        ql.hienThi();

        sc.close();
    }
}
