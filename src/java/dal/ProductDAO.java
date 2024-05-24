package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDAO extends DBContext {

    private PreparedStatement stm;
    private ResultSet rs;
    private List<Product> productList = new ArrayList<>();

    private static Product setProduct(ResultSet rs) {
        Product p = new Product();
        try {
            p.setId(rs.getInt("id"));
            p.setQuantity(rs.getInt("quantity"));
            p.setTitle(rs.getString("title"));
            p.setStatus(rs.getString("status"));
            p.setThumbnail(rs.getString("thumbnail"));
            p.setCategoryId(rs.getInt("category_id"));
            p.setImportPrice(rs.getDouble("import_price"));
            p.setListPrice(rs.getDouble("list_price"));
            p.setCreatedDate(rs.getDate("created_date"));
            p.setFeatured(rs.getBoolean("is_featured"));
        } catch (SQLException e) {
        }
        return p;
    }

    public List<Product> getAll() {
        String sql = "SELECT * FROM product";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product p = setProduct(rs);
                productList.add(p);
            }
            return productList;
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Product> getFeatured() {
        productList.clear();
        String sql = "SELECT * FROM product WHERE featured = 1 LIMIT 5";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Product p = setProduct(rs);
                productList.add(p);
            }
            return productList;
        } catch (SQLException e) {
        }
        return productList;
    }

    public static void main(String[] args) {
        ProductDAO p = new ProductDAO();
        for (Product pr : p.getFeatured()) {
            System.out.println(pr.getTitle());
        }
    }
}
