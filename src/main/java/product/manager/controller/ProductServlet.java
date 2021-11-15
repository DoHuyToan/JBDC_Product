package product.manager.controller;

import product.manager.model.Product;
import product.manager.service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// THAY VALUE = urlPatterns, giá trị = products
@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {

    ProductService productService = new ProductService();

    // HIỂN THỊ FORM - DÙNG METHOD GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showNewFrom(request, response);
                break;
            case "edit":
                showEditFrom(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "find":
                find(request, response);
                break;
            default:
                showList(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                addProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
        }
    }


    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.remove(id);
        List<Product> products = productService.showAll();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditFrom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        request.setAttribute("pro",product);
        dispatcher.forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String producer = request.getParameter("producer");
        double price = Double.parseDouble(request.getParameter("price"));
        Product product = new Product(id, name, producer, price);
        productService.edit(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String producer = request.getParameter("producer");
        // GỌI TRANG DANH SÁCH
        List<Product> products;
        if(producer!=null){
            products = productService.findByProducer(producer);
        } else {
            products = productService.showAll();
        }
        // GÁN TÊN CHO OBJECT ĐỂ SỬ DỤNG ĐC TRONG VIEW (JSP)
        request.setAttribute("products", products);
        // DI CHUYỂN ĐẾN LINK VÀO VIEW
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(request, response);
    }

    private void find(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String find = request.getParameter("find");
        List<Product> products = productService.find(find);
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/find.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewFrom(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String producer = request.getParameter("producer");
        double price = Double.parseDouble(request.getParameter("price"));
        Product product = new Product(name, producer, price);
        productService.add(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(request, response);
    }

    // NHẤN NÚT THỰC HIỆN - METHOD POST





}
