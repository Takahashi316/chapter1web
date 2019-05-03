package org.smart4j.chapter1;

import org.smart4j.chapter1.model.Customer;
import org.smart4j.chapter1.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author chen
 * @date 2019/5/2
 * @description
 */
@WebServlet("/customer")
public class HelloServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init(){
        customerService=new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentTime = dateFormat.format(new Date());
//        req.setAttribute("currentTime",currentTime);
//        req.getRequestDispatcher("WEB-INF/jsp/hello.jsp").forward(req,resp);

        List<Customer> customers=customerService.getCustomerList();
        req.setAttribute("customerList",customers);
        req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req,resp);

    }
}
