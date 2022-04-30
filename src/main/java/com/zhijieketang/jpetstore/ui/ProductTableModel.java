package com.zhijieketang.jpetstore.ui;

import com.zhijieketang.jpetstore.domain.Product;

import javax.swing.table.AbstractTableModel;
import java.util.List;

//商品列表 表格模型
public class ProductTableModel extends AbstractTableModel {

    private final String[] columnNames =
            {"商品编号", "商品类别", "商品中文名", "商品英文名"};

    //表格中的数据内容保存在List集合
    private List<Product> data = null;

    public ProductTableModel(List<Product> data) {
        this.data = data;
    }

    //行
    @Override
    public int getRowCount() {
        return data.size();
    }

    //列
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //每一行就是一个Product商品对象
        Product p = data.get(rowIndex);
        Object goods;
        switch (columnIndex) {
            case 0:
                goods = p.getProductid();
                break;
            case 1:
                goods = p.getCategory();
                break;
            case 2:
                goods = p.getCname();
                break;
            default:
                goods = p.getEname();
        }
        return goods;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
}
