package View;

import Model.SinhVien;
import Model.Interface.SinhVienTabelObserver;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SinhVienTableModel extends AbstractTableModel implements SinhVienTabelObserver {
    private   List<SinhVien> sinhVienList ;

    public SinhVienTableModel(List<SinhVien> sinhVienList){
        this.sinhVienList = sinhVienList;
    }
    public SinhVienTableModel(){

    }
    private static final String [] COLUMN_NAME = {"ID","Name","Mark","Time"};
    private static final int ID = 0;
    private static final int FULL_NAME = 1;
    private static final int MARK = 2;
    private static final int DATE = 3;
    @Override
    public int getRowCount() {
        return sinhVienList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAME.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAME[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SinhVien sinhVien = sinhVienList.get(rowIndex);
        if (columnIndex == ID){
            return sinhVien.getID();
        }
        else if(columnIndex == FULL_NAME){
            return sinhVien.getFullName();
        }
        else if(columnIndex == MARK){
            return sinhVien.getMark();
        }
        else if(columnIndex == DATE){
            return sinhVien.getDate();
        }
        return null;
    }

    @Override
    public void updateobserver(List<SinhVien> list) {
        sinhVienList.clear();
        sinhVienList.addAll(list);
        fireTableDataChanged();
    }
}
