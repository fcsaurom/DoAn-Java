package Model;

import Model.Interface.SinhVienTabelObserver;

import java.util.ArrayList;
import java.util.List;

public class SinhVienModel implements Model.Interface.SinhVienModel {
    private List<SinhVienTabelObserver> tableObserver =new ArrayList<>();;
    @Override
    public List<SinhVien> getAllSinhVien() {
        DataModel dataModel = new DataModel();
        return  dataModel.getAllSinhVien();
    }

    @Override
    public SinhVien getSinhVienByID(int id) {
        DataModel dataModel = new DataModel();
        dataModel.getSinhVienById(id);
        return dataModel.getSinhVienById(id);
    }

    @Override
    public void AddNewSinhVien(SinhVien sinhVien) {
        DataModel dataModel = new DataModel();
        dataModel.Insert(sinhVien);
    }

    @Override
    public void DeleteInfoSV(int id) {
        DataModel dataModel = new DataModel();
        dataModel.Delete(id);
    }

    @Override
    public void UpdateSV(SinhVien sinhVien) {
        DataModel dataModel = new DataModel();
        dataModel.Update(sinhVien);


    }

    @Override
    public void NotifySinhVien() {
        for (SinhVienTabelObserver tableObserver: tableObserver){
            tableObserver.updateobserver( getAllSinhVien());
        }
    }

    @Override
    public void RegisterObserver(SinhVienTabelObserver tabelObserver) {
        if(!tableObserver.contains(tabelObserver)){
            tableObserver.add(tabelObserver);
        }
    }

    @Override
    public void UnRegisterObserver(SinhVienTabelObserver tabelObserver) {
        if(tableObserver.contains(tabelObserver)){
            tableObserver.remove(tabelObserver);
        }
    }
}
