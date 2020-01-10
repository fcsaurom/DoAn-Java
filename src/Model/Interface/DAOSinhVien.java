package Model.Interface;

import Model.SinhVien;

import java.util.List;

public interface DAOSinhVien {
    void Insert(SinhVien sinhVien);
    SinhVien getSinhVienById(int id);
    List<SinhVien> getAllSinhVien();
    void Update(SinhVien sinhVien);
    void Delete(int id);
}
