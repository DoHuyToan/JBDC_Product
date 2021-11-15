package product.manager.service.product;

import product.manager.config.ConnectionSingleton;
import product.manager.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProdcutService{
    // TẠO ĐỐI TƯỢNG connection: CUNG CẤP CÁC METHOD GIAO TIẾP VỚI DATABASE
    private Connection connection = ConnectionSingleton.getConnection();

    @Override
    public List<Product> showAll() {
        List<Product> products = new ArrayList<>();
        // TẠO ĐỐI TƯỢNG ĐỂ GỬI CÂU LỆNH SQL ĐẾN DATABASE ĐỂ LẤY DỮ LIỆU
        try {
            PreparedStatement ps = connection.prepareStatement("select * from products order by name ;");
            System.out.println(ps);

            // ResultSet: ĐẠI DIỆN CHO TẬP CÁC BẢN GHI SAU KHI TRUY VẤN QUERY
            // executeQuery: TRẢ VỀ 1 ĐỐI TƯỢNG ResultSet CHỨA 1 LIST RECORD THỎA MÃN CÂU LỆNH SELECT
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String producer = rs.getString("producer");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, producer, price));
            }
        } catch (SQLException e){
            printSQLException(e);
        }
        return products;
    }

    @Override
    public void add(Product product) {
        try {
            // TẠO ĐỐI TƯỢNG ĐỂ GỬI CÂU LỆNH SQL ĐẾN DATABASE
            PreparedStatement ps = connection.prepareStatement("insert into products(name , producer , price) value (?, ?, ?)");
            // GÁN GIÁ TRỊ CHO LẦN LƯỢT TỪNG THAM SỐ (?) SAU KHI THỰC HIỆN CÂU LỆNH QUERY Ở TRÊN
            ps.setString(1, product.getName());
            ps.setString(2, product.getProducer());
            ps.setDouble(3, product.getPrice());
            // METHOD NÀY TRẢ VỀ SỐ MẪU TIN BỊ ẢNH HƯỞNG BỞI CÂU SQL, SỐ LƯỢNG PHẦN TỬ ĐÃ THAY ĐỔI
            ps.executeUpdate();
        } catch (SQLException e){
            printSQLException(e);
        }
    }

    @Override
    public boolean edit(Product product) {
        boolean rowEdit = false;
        try {
            // TẠO ĐỐI TƯỢNG ĐỂ GỬI CÂU LỆNH SQL ĐẾN DATABASE
            PreparedStatement ps = connection.prepareStatement("update products set name =?, producer =?, price=? where id=?");
            ps.setString(1, product.getName());
            ps.setString(2, product.getProducer());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getId());

            // executeUpdate: NẾU CÓ ÍT NHẤT 1 PHẦN TỬ TÌM ĐC CẦN SỬA THÌ TRUE
            rowEdit = ps.executeUpdate()>0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return rowEdit;
    }

    @Override
    public boolean remove(int id) {
        boolean rowRemove = false;
        try {
            // TẠO ĐỐI TƯỢNG ĐỂ GỬI CÂU LỆNH SQL ĐẾN DATABASE
            PreparedStatement ps = connection.prepareStatement("delete from products where id=?");
            ps.setInt(1, id);

            // executeUpdate: nếu thực hiện đc 1 câu lệnh trả về int = 1 => rowEdit = true
            rowRemove = ps.executeUpdate() >0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return rowRemove;
    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try {
            // TẠO ĐỐI TƯỢNG ĐỂ GỬI CÂU LỆNH SQL ĐẾN DATABASE
            PreparedStatement ps = connection.prepareStatement("select id, name , procedure , price from products where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String producer = rs.getString("producer");
                double price = rs.getDouble("price");
                product = new Product(id, name, producer, price);
            }
        } catch (SQLException e){
            printSQLException(e);
        }
        return product;
    }

    public List<Product> findByProducer(String producer){
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select  * from products where producer =?");
            ps.setString(1, producer);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, producer, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> find(String find){
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from products where name like '%'+ ? + '%'");
            ps.setString(1, find);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String producer = rs.getString("producer");
                double price = rs.getDouble("price");
                products.add(new Product(id, name, producer, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }



    // TẠO METHOD BÁO CÁO LỖI: Message
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex){
            if(e instanceof SQLException){
                // err: HIỆN CHỮ ĐỎ, out: HIỆN CHỮ THƯỜNG
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
