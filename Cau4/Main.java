
import java.util.*;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 50 / 100;
    }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return hocPhi * 70 / 100;
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3000000L);
    }
}

abstract class SinhVien {
    protected String maSV;
    protected int soTinChi;
    protected MienGiam mienGiam;

    public SinhVien(String maSV, int soTinChi) {
        this.maSV = maSV;
        this.soTinChi = soTinChi;
        this.mienGiam = null;
    }

    public abstract long hocPhi();

    public abstract String getMaHe();

    public String getMaSV() {
        return maSV;
    }

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public long tienGiam() {
        if (mienGiam == null) {
            return 0;
        }
        return mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - tienGiam();
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

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        ArrayList<SinhVien> danhSach = new ArrayList<>();
        HashMap<String, SinhVien> timKiem = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");

            String maHe = data[0];
            String maSV = data[1];
            int soTinChi = Integer.parseInt(data[2]);

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
                    break;
            }

            danhSach.add(sv);
            timKiem.put(maSV, sv);
        }

        int m = Integer.parseInt(sc.nextLine());

        ArrayList<String> loi = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] data = sc.nextLine().trim().split("\\s+");

            String maSV = data[0];
            String maChinhSach = data[1];

            SinhVien sv = timKiem.get(maSV);

            if (sv == null) {
                loi.add("NOT FOUND " + maSV);
                continue;
            }

            switch (maChinhSach) {
                case "CS":
                    sv.setMienGiam(new ConChinhSach());
                    break;
                case "HN":
                    sv.setMienGiam(new HoNgheo());
                    break;
                case "HB":
                    sv.setMienGiam(new HocBong());
                    break;
            }
        }

        for (String dong : loi) {
            System.out.println(dong);
        }

        for (SinhVien sv : danhSach) {
            System.out.println(
                sv.getMaSV() + " " +
                sv.getMaHe() + " " +
                sv.hocPhi() + " " +
                sv.tienGiam() + " " +
                sv.phaiNop()
            );
        }

        String[] cacHe = {"CQ", "CLC", "LT"};

        for (String maHe : cacHe) {
            int soLuong = 0;
            long tongPhaiNop = 0;

            for (SinhVien sv : danhSach) {
                if (sv.getMaHe().equals(maHe)) {
                    soLuong++;
                    tongPhaiNop += sv.phaiNop();
                }
            }

            if (soLuong > 0) {
                System.out.println(
                    maHe + " " + soLuong + " " + tongPhaiNop
                );
            }
        }

        sc.close();
    }
}
