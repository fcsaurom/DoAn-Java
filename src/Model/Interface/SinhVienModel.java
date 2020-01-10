package Model.Interface;

import Model.SinhVien;

import java.util.List;

public interface SinhVienModel {
        List<SinhVien> getAllSinhVien();
        SinhVien getSinhVienByID(int id);
        void AddNewSinhVien(SinhVien sinhVien);
        void DeleteInfoSV(int id);
        void UpdateSV(SinhVien sinhVien);
        void NotifySinhVien();
        void  RegisterObserver(SinhVienTabelObserver tabelObserver);
        void UnRegisterObserver(SinhVienTabelObserver tabelObserver);

}
