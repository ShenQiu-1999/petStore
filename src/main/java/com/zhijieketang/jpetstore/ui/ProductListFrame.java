package com.zhijieketang.jpetstore.ui;

import com.zhijieketang.jpetstore.dao.ProductDao;
import com.zhijieketang.jpetstore.dao.mysql.ProductDaoImp;
import com.zhijieketang.jpetstore.domain.Product;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProductListFrame extends MyFrame {

    private JTable table;
    private JLabel lblImage, lblListprice, lblDescn, lblUnitcost;
    //商品列表集合
    private List<Product> products = null;
    private final ProductDao dao = new ProductDaoImp();
    //购物车 key：商品id value：商品数量
    private Map<String, Integer> cart = new HashMap<>();
    //商品索引
    private int selectedRow = -1;


    public ProductListFrame() {
        super("商品列表", 1000, 700);
        products = dao.findAll();
        //添加顶部搜索面板
        getContentPane().add(getSearchPanel(), BorderLayout.NORTH);

        //创建分栏面板
        JSplitPane splitPane = new JSplitPane();
        //设置指定分隔条位置，从窗格的左边到分隔条的左边
        splitPane.setDividerLocation(600);
        //设置左侧面板
        splitPane.setLeftComponent(getLeftPanel());
        //设置右侧面板
        splitPane.setRightComponent(getRightPanel());
        //分栏添加到内容面板
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    //初始化搜索面板
    private JPanel getSearchPanel() {
        JPanel searchPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) searchPanel.getLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(40);

        JLabel lbl = new JLabel("选择商品类别：;");
        lbl.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        String[] categorys = {"所有类别", "鱼类", "犬类", "爬行类", "猫类", "鸟类"};
        JComboBox<String> comboBox = new JComboBox<>(categorys);
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        searchPanel.add(comboBox);

        JButton btnGo = new JButton("查询");
        btnGo.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        searchPanel.add(btnGo);

        JButton btnReset = new JButton("重置");
        btnReset.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        searchPanel.add(btnReset);

        //注册查询按钮的监听器
        btnGo.addActionListener(e -> {
            //所选择的类别
            String category = (String) comboBox.getSelectedItem();
            if ("所有类别".equals(category))
                products = dao.findAll();
            else
                products = dao.findByCategory(category);
            TableModel model = new ProductTableModel(products);
            table.setModel(model);
        });

        //注册重置按钮的事件监听器
        btnReset.addActionListener(e -> {
            products = dao.findAll();
            TableModel model = new ProductTableModel(products);
            table.setModel(model);
        });
        return searchPanel;
    }

    //初始化右侧面板
    private JPanel getRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridLayout(2, 1, 0, 0));

        lblImage = new JLabel();
        rightPanel.add(lblImage);
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel detailPanel = new JPanel();
        detailPanel.setBackground(Color.WHITE);
        rightPanel.add(detailPanel);
        detailPanel.setLayout(new GridLayout(8, 1, 0, 5));

        JSeparator separator_1 = new JSeparator();
        detailPanel.add(separator_1);

        lblListprice = new JLabel();
        detailPanel.add(lblListprice);
        lblListprice.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        lblUnitcost = new JLabel();
        detailPanel.add(lblUnitcost);
        lblUnitcost.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        lblDescn = new JLabel();
        detailPanel.add(lblDescn);
        lblDescn.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        JSeparator separator_2 = new JSeparator();
        detailPanel.add(separator_2);

        JButton btnAdd = new JButton("添加到购物车");
        detailPanel.add(btnAdd);
        btnAdd.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        //占位
        JLabel lbl = new JLabel();
        detailPanel.add(lbl);

        JButton btnCheck = new JButton("查看购物车");
        detailPanel.add(btnCheck);
        btnCheck.setFont(new Font("微软雅黑", Font.PLAIN, 15));

        //添加购物车按钮的事件监听器
        btnAdd.addActionListener(e -> {
            if (selectedRow < 0)
                return;
            Product selectProduct = products.get(selectedRow);
            String productid = selectProduct.getProductid();

            if (cart.containsKey(productid)) {
                //购物车已有该商品 获得商品数量
                Integer quantity = cart.get(productid);
                cart.put(productid, ++quantity);
            } else {
                cart.put(productid, 1);
            }
            System.out.println(cart);
        });

        //查看购物车事件监听器
        btnCheck.addActionListener(e -> {
            CartFrame cartFrame = new CartFrame(cart, this);
            cartFrame.setVisible(true);
            setVisible(false);
        });
        return rightPanel;
    }

    //初始化左侧面板
    private JScrollPane getLeftPanel() {
        JScrollPane leftScrollPane = new JScrollPane();
        leftScrollPane.setViewportView(getTable());
        return leftScrollPane;
    }

    private JTable getTable() {
        TableModel model = new ProductTableModel(this.products);
        if (table == null) {
            table = new JTable(model);
            table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            table.getTableHeader().setFont((new Font("微软雅黑", Font.BOLD, 16)));
            table.setRowHeight(51);
            table.setRowSelectionAllowed(true);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            ListSelectionModel rowSelectionModel = table.getSelectionModel();

            rowSelectionModel.addListSelectionListener(e -> {
                if (e.getValueIsAdjusting())
                    return;
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                selectedRow = lsm.getMinSelectionIndex();
                if (selectedRow < 0)
                    return;
                //更新右侧面板
                Product p = products.get(selectedRow);
                String petImage = String.format("/images/%s", p.getImage());
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(ProductListFrame.class.getResource(petImage)));
                lblImage.setIcon(icon);

                String descn = p.getDescn();
                lblDescn.setText("商品描述:" + descn);

                double listprice = p.getListprice();
                String slistprice = String.format("商品市场:%.2f", listprice);
                lblListprice.setText(slistprice);

                double unitcost = p.getUnitcost();
                String slblUnitcost = String.format("商品单价:%.2f", unitcost);
                lblUnitcost.setText(slblUnitcost);
            });
        } else
            table.setModel(model);
        return table;
    }

}
