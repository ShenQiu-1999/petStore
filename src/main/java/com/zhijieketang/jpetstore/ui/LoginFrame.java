package com.zhijieketang.jpetstore.ui;

import com.zhijieketang.jpetstore.dao.AccountDao;
import com.zhijieketang.jpetstore.dao.mysql.AccountDaoImp;
import com.zhijieketang.jpetstore.domain.Account;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends MyFrame {

    private final JTextField txtAccountId;
    private final JPasswordField txtPassword;

    public LoginFrame() {
        super("用户登录", 400, 230);
        //设置布局管理为绝对布局
        getContentPane().setLayout(null);

        JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        label1.setBounds(51, 33, 83, 30);
        label1.setText("账号：");
        label1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        getContentPane().add(label1);


        txtAccountId = new JTextField(10);
        txtAccountId.setText("");
        txtAccountId.setBounds(158, 33, 157, 30);
        txtAccountId.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        getContentPane().add(txtAccountId);

        JLabel jLabel2 = new JLabel();
        jLabel2.setText("密码：");
        jLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setBounds(51, 85, 83, 30);
        getContentPane().add(jLabel2);

        txtPassword = new JPasswordField(10);
        txtPassword.setText("");
        txtPassword.setBounds(158, 85, 157, 30);
        getContentPane().add(txtPassword);

        JButton btnOk = new JButton();
        btnOk.setText("确定");
        btnOk.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnOk.setBounds(61, 140, 100, 30);
        getContentPane().add(btnOk);

        JButton btnCancel = new JButton();
        btnCancel.setText("取消");
        btnCancel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        btnCancel.setBounds(255, 140, 100, 30);
        getContentPane().add(btnCancel);

        btnOk.addActionListener(e -> {
            AccountDao accountDao = new AccountDaoImp();
            Account account = accountDao.findById(txtAccountId.getText());

            String passwordText = new String(txtPassword.getPassword());
            if (account != null && passwordText.equals(account.getPassword())) {
                System.out.println("登录成功.");
                ProductListFrame form = new ProductListFrame();
                form.setVisible(true);
                setVisible(false);
                MainApp.account = account;//将用户信息保存到静态变量中
            } else {
                JLabel label = new JLabel("您输入的账号或密码有误，请重新输入");
                label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                JOptionPane.showMessageDialog(null, label, "登录失败", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.getRootPane().setDefaultButton(btnOk);//设置回车的默认按钮操作 设置回车确认

        btnCancel.addActionListener(e -> {
            System.exit(0);
        });
    }
}
