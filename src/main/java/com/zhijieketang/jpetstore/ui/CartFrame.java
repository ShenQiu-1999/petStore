package com.zhijieketang.jpetstore.ui;

import com.zhijieketang.jpetstore.dao.OrderDao;
import com.zhijieketang.jpetstore.dao.OrderDetailDao;
import com.zhijieketang.jpetstore.dao.ProductDao;
import com.zhijieketang.jpetstore.dao.mysql.OrderDaoImp;
import com.zhijieketang.jpetstore.dao.mysql.OrderDetailImp;
import com.zhijieketang.jpetstore.dao.mysql.ProductDaoImp;
import com.zhijieketang.jpetstore.domain.Order;
import com.zhijieketang.jpetstore.domain.OrderDetail;
import com.zhijieketang.jpetstore.domain.Product;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Date;
import java.util.Map;
import java.util.Set;

//商品购物车窗口
public class CartFrame extends MyFrame {

    private JTable table;

    //购物车数据
    private Object[][] data = null;

    private ProductDao dao = new ProductDaoImp();

    //商品id 商品数量
    private Map<String, Integer> cart;

    private ProductListFrame productListFrame;

    public CartFrame(Map<String, Integer> cart, ProductListFrame productListFrame) {
        super("商品购物车", 1000, 700);
        this.cart = cart;
        this.productListFrame = productListFrame;

        JPanel topPanel = new JPanel();
        FlowLayout fl_topPanel = (FlowLayout) topPanel.getLayout();
        fl_topPanel.setVgap(10);
        fl_topPanel.setHgap(20);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        JButton btnReturn = new JButton("返回商品列表");
        btnReturn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        topPanel.add(btnReturn);

        JButton btnSubmit = new JButton("提交订单");
        btnSubmit.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        topPanel.add(btnSubmit);

        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(getTable());

        //订单提交事件监听器
        btnSubmit.addActionListener(e -> {
            generateOrders();
            JLabel label = new JLabel("订单已生成，等待付款");
            label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            if (JOptionPane.showConfirmDialog(this, label, "信息", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                //付款
                System.exit(0);
            } else {
                System.exit(0);
            }
        });

        //返回商品列表事件监听器
        btnReturn.addActionListener(e -> {
            //更新购物车
            for (Object[] item : data) {
                String productid = (String) item[0];
                Integer quantity = (Integer) item[3];
                cart.put(productid, quantity);
            }
            this.productListFrame.setVisible(true);
            setVisible(false);
        });
    }

    //初始化左侧面板中的表格控件
    private JTable getTable() {
        //准备表中数据
        data = new Object[cart.size()][5];
        Set<String> keys = this.cart.keySet();
        int index = 0;
        for (String productid : keys) {
            Product p = dao.findById(productid);
            data[index][0] = p.getProductid();
            data[index][1] = p.getCname();
            data[index][2] = p.getUnitcost();
            data[index][3] = cart.get(productid);

            double amount = (double) data[index][2] * (int) data[index][3];
            data[index][4] = amount;
            index++;
        }
        //创建表格数据模型
        TableModel model = new CartTableModel(data);

        if (table == null) {
            table = new JTable(model);
            table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 16));
            table.setRowHeight(51);
            table.setRowSelectionAllowed(false);
        } else
            table.setModel(model);
        return table;
    }

    private void generateOrders() {
        OrderDao orderDao = new OrderDaoImp();
        OrderDetailDao orderDetailDao = new OrderDetailImp();

        Order order = new Order();
        order.setUserid(MainApp.account.getUserid());
        //0待付款
        Date now = new Date();
        long orderId = now.getTime();
        order.setOrderid(orderId);
        order.setOrderdate(now);
        order.setAmount(getOrderTotalAmount());

        //创建订单 放入数据库
        orderDao.create(order);

        for (int i = 0; i < data.length; i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderid(orderId);
            orderDetail.setProductid((String) data[i][0]);
            orderDetail.setQuantity((int) data[i][3]);
            orderDetail.setUnitcost((double) data[i][2]);
            //创建订单明细
            orderDetailDao.create(orderDetail);
        }
    }

    //计算订单应付总金额
    private double getOrderTotalAmount() {
        double totalAmount = 0.0;
        for (int i = 0; i < data.length; i++) {
            totalAmount += (Double) data[i][4];
        }
        return totalAmount;
    }

}
