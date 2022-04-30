package com.zhijieketang.jpetstore.ui;

import javax.swing.table.AbstractTableModel;

//购物车 表格模型
public class CartTableModel extends AbstractTableModel {

    //表格列名
    private String[] columnNames = {"商品编号", "商品名", "商品单价", "数量", "商品应付金额"};

    //保存表格中的数据
    private Object[][] data = null;

    public CartTableModel(Object[][] data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }

    //修改购物车商品数量 计算总金额
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex != 3)
            return;
        try {
            int quantity = Integer.parseInt((String) aValue);
            if (quantity < 0)
                return;
            data[rowIndex][3] = quantity;
            double unitcost = (double) data[rowIndex][2];
            double totalPrice = unitcost * quantity;
            data[rowIndex][4] = totalPrice;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
