package View;


import Model.Interface.SinhVienModel;
import Model.SinhVien;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListSinhVien extends  JFrame {
    private JPanel rootpanel;
    private JTable tableSinhVien;
    private JTextField id;
    private JPanel date;
    private JButton pic;
    private JButton delete;
    private JButton insert;
    private JButton update;
    private JButton frist;
    private JButton last;
    private JButton fri;
    private JButton las;
    private JTextField mark;
    private JTextField name;
    private JLabel picture;
    private JTextField idSearch;
    private JPanel bodypanel;
    private SinhVienModel model;
    private Component component;
    int idSearchSv;
    float marks=0;
    Calendar cld=Calendar.getInstance();

    JDateChooser datechoser=new JDateChooser(cld.getTime());

    int pos = 0;
    String path = null;

    public void Showtext(int index){
        id.setText(Integer.toString(model.getAllSinhVien().get(index).getID()));
        name.setText(model.getAllSinhVien().get(index).getFullName());
        mark.setText(Float.toString(model.getAllSinhVien().get(index).getMark()));
        path =model.getAllSinhVien().get(index).getImage();
        Image images  =  new ImageIcon(model.getAllSinhVien().get(index).getImage()).getImage();
        ImageIcon icon = new ImageIcon(images);
        picture.setIcon(icon);
        picture.setIcon(resize(null, images));
        Date dates= null;
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        dates =new Date(model.getAllSinhVien().get(index).getDate());
        simple.format(dates);

        cld.setTime(dates);
        datechoser=new JDateChooser(cld.getTime());
        datechoser.setDateFormatString("dd/MM/yyyy");
        date.removeAll();
        date.add(datechoser);

    }

    void clear(){

        name.setText("");
        mark.setText("");
        path = null;
    }
    public String getTfFullName() {
        return name.getText();
    }
    public Float getID(int ids){
        return Float.valueOf(id.getText());
    }
    public Float getMark(float marks){
        return Float.valueOf(mark.getText());
    }
    public String getPath(){
        return path;
    }


    public ImageIcon resize(String pa, Image pic1){
        ImageIcon icon = null;
        Image img = null;
        if (pa != null){
            icon = new ImageIcon(pa);
            img = icon.getImage().getScaledInstance(picture.getWidth(),picture.getHeight(),Image.SCALE_SMOOTH);
        }
        else{
            icon = new ImageIcon(pic1);
            img = icon.getImage().getScaledInstance(picture.getWidth(),picture.getHeight(),Image.SCALE_SMOOTH);
        }

        ImageIcon ico = new ImageIcon(img);
        return ico;
    }

    public ListSinhVien(SinhVienModel model){
        this.model = model;
        date.add(datechoser);
        datechoser.setDateFormatString("dd/MM/yyyy");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,600));
        setContentPane(rootpanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        SinhVienTableModel modelTable = new SinhVienTableModel(model.getAllSinhVien());
        model.RegisterObserver(modelTable);
        tableSinhVien.setModel(modelTable);





        tableSinhVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int dex = tableSinhVien.getSelectedRow();
                Showtext(dex);
            }
        });

        pic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","png","gif","jpg","jpeg","ico","bmp");
                fileChooser.addChoosableFileFilter(filter);
                int selet = fileChooser.showSaveDialog(null);
                if (selet == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    path = file.getAbsolutePath();
                    picture.setIcon(resize(path,null));

                }
                else {
                    if (selet == JFileChooser.CANCEL_OPTION){
                        JOptionPane.showMessageDialog(null,"No Image");
                    }
                }
            }
        });
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!name.getText().isEmpty() && !mark.getText().isEmpty()){
                    String Fullname = name.getText();
                    long dates = datechoser.getDate().getTime();

                    String link = path;
                  SinhVien newSinhvien = new SinhVien(Fullname,marks,dates,link);
                  SinhVienModel addnew = new Model.SinhVienModel();
                  addnew.AddNewSinhVien(newSinhvien);
                    model.NotifySinhVien();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Do not leave blank except for the ID !");
                }
                clear();
            }

        });


        mark.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try{
                    marks = (float) Double.parseDouble(mark.getText());
                    if(marks <=0  || marks >=10){
                        JOptionPane.showMessageDialog(null," mark incorrect!");
                        mark.setText("");
                    }


                }
                catch (Exception es){
                    mark.setText("");
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!name.getText().isEmpty() && !mark.getText().isEmpty() && !id.getText().isEmpty()){
                    Integer Id = Integer.valueOf(id.getText());
                    String Fullname = name.getText();
                    long dates =datechoser.getDate().getTime();
                    String link = path;
                    SinhVien upSinhvien = new SinhVien(Id,Fullname,marks,dates,link);
                    SinhVienModel updateSV= new Model.SinhVienModel();
                    updateSV.UpdateSV(upSinhvien);
                    model.NotifySinhVien();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Not be empty !");
                }
                clear();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int index = tableSinhVien.getSelectedRow();

                if (index != -1){
                    int id = (int) tableSinhVien.getValueAt(index,0);
                    SinhVienModel deleteSV= new Model.SinhVienModel();
                    deleteSV.DeleteInfoSV(id);
                    model.NotifySinhVien();
                }
                Integer Id = Integer.valueOf(id.getText());
                SinhVienModel deleteSV= new Model.SinhVienModel();
                deleteSV.DeleteInfoSV(Id);
                model.NotifySinhVien();
            }
        });


        frist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pos = 0;
                Showtext(pos);
            }
        });
        last.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pos = 0;
                pos = model.getAllSinhVien().size()-1;
                Showtext(pos);
            }
        });
        fri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                pos--;
                if(pos <= model.getAllSinhVien().size() && pos>=0){
                    Showtext(pos);

                }
                else {
                    pos =0;
                }

            }
        });
        las.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pos++;
                if(pos < model.getAllSinhVien().size()){
                    Showtext(pos);


                }
                else if(pos == model.getAllSinhVien().size()){
                    pos = model.getAllSinhVien().size() -1;
                }

            }
        });

        idSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableSinhVien.getModel());
                String text = idSearch.getText();
                if (text.trim().length() == 0) {

                    rowSorter.setRowFilter(null);
                } else {

                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
                tableSinhVien.setRowSorter(rowSorter);
            }
        });
    }



    public Model.SinhVien getById(int id) {
        Model.SinhVienModel model = new Model.SinhVienModel();
        return  model.getSinhVienByID(id);
    }

    public JPanel getRootpanel() {
        return rootpanel;
    }


}
