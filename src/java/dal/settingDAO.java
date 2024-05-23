/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Setting;

/**
 *
 * @author ACER
 */
public class settingDAO extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    public List<Setting> getAll() {
        List<Setting> list = new ArrayList<>();
        try {
            String strSelect = "SELECT s.id,type_id,value,s.name,`order`,"
                    + " status, description,t.name as type FROM setting as s join setting_type as t "
                    + "on type_id = t.id order by s.id";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting(rs.getInt("id"),
                        rs.getInt("type_id"), rs.getString("type"), rs.getInt("order"), rs.getString("name"),
                        rs.getInt("value"), rs.getString("status"), rs.getString("description")
                );
                list.add(setting);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Setting getById(int id) {
        settingDAO s = new settingDAO();
        List<Setting> list = s.getAll();
        for (Setting st : list) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    //Lấy ra setting type
    public List<String> getAllType() {
        List<String> types = new ArrayList<String>();
        try {
            String strSelect = "SELECT name from setting_type";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                String t = rs.getString("name");
                types.add(t);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return types;
    }

    //Search name
    public List<Setting> getAllByName(String n) {
        List<Setting> list = new ArrayList<>();
        try {
            String strSelect = "SELECT s.id,type_id,value,s.name,`order`,"
                    + " status, description,t.name as type FROM setting as s join setting_type as t "
                    + "on type_id = t.id where s.name like ?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, "%" + n + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting(rs.getInt("id"),
                        rs.getInt("type_id"), rs.getString("type"), rs.getInt("order"), rs.getString("name"),
                        rs.getInt("value"), rs.getString("status"), rs.getString("description")
                );
                list.add(setting);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //Lấy hết qua type
    public List<Setting> getAllByType(List<Setting> list, String type) {
        List<Setting> outputlist = new ArrayList<>();
        for (Setting s : list) {
            if (s.getType().equals(type)) {
                outputlist.add(s);
            }
        }
        return outputlist;
    }

    //Lấy hết qua status
    public List<Setting> getAllByStatus(List<Setting> list, String status) {
        List<Setting> outputlist = new ArrayList<>();
        for (Setting s : list) {
            if (s.getStatus().equals(status)) {
                outputlist.add(s);
            }
        }
        return outputlist;
    }

    //Lấy theo phân trang
    public List<Setting> getByPage(List<Setting> list, int page) {
        List<Setting> outputlist = new ArrayList<>();
        for (int i = (page - 1) * 3; i <= (page - 1) * 3 + 2; i++) {
            if (i >= list.size()) {
                return outputlist;
            }
            outputlist.add(list.get(i));
        }
        return outputlist;
    }
    //Lấy id từ tên type
    public int getTypeId(String n) {
        int id=0;
        try {
            String strSelect = "select id from setting_type where name = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1,n);
            rs = stm.executeQuery();
            while (rs.next()) id = rs.getInt("id");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    //Update setting 
    public void updateSetting(int id, Setting s) {
        settingDAO sDAO = new settingDAO();
        try {
            String strSelect = "UPDATE setting SET type_id = ?,value = ?,name = ?,`order` = ?,status = ?,description = ? WHERE id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, s.getTypeId());
            stm.setInt(2, s.getValue());
            stm.setString(3, s.getName());
            stm.setInt(4, s.getOrder());
            stm.setString(5, s.getStatus());
            stm.setString(6, s.getDescription());
            stm.setInt(7, id);
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //active 
    public void active(int id){
        try {
            String strSelect = "update setting set status = 'Active' where id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1,id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void inactive(int id){
        try {
            String strSelect = "update setting set status = 'Inactive' where id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1,id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
        
            // update product category
//    public void updateProductCategory(int id, Setting s) {
//        try {
//            String strSelect = "UPDATE product_category SET name = ?,status = ? WHERE id = ?";
//            stm = connection.prepareStatement(strSelect);
//            stm.setString(1, s.getName());
//            stm.setString(2, s.getStatus());
//            stm.setInt(3, id);
//            stm.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }

    public static void main(String[] args) {
        settingDAO s = new settingDAO();

        Setting st = new Setting(1, 1, 1, "SẢn Phẩm cho mèo", 1, "Active", "hehehe");
        s.updateSetting(1, st);
//        s.updateProductCategory(1, st);
        List<Setting> sList = s.getAll();
        for (Setting stt : sList) {
            System.out.println(stt.getName() + stt.getType());
        }
        System.out.println(s.getTypeId("Product Category"));
    }
}
