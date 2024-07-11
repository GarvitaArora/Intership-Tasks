import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double deposit(double amount) {
        balance += amount;
        return balance;
    }

    public double withdraw(double amount) {
        if (amount > balance) {
            JOptionPane.showMessageDialog(null, "Insufficient funds");
            return balance;
        }
        balance -= amount;
        return balance;
    }

    public double getBalance() {
        return balance;
    }
}

class ATMGUI {
    private BankAccount account;

    public ATMGUI(BankAccount account) {
        this.account = account;

        JFrame frame = new JFrame("ATM Interface");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("ATM Operations");
        label.setBounds(150, 10, 120, 30);
        frame.add(label);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(140, 50, 120, 30);
        frame.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(140, 90, 120, 30);
        frame.add(depositButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.setBounds(140, 130, 120, 30);
        frame.add(balanceButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(140, 170, 120, 30);
        frame.add(exitButton);

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter the amount to withdraw:");
                if (amountStr != null) {
                    try {
                        double amount = Double.parseDouble(amountStr);
                        double updatedBalance = account.withdraw(amount);
                        JOptionPane.showMessageDialog(frame, "Withdrawal successful. New balance: " + updatedBalance);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount entered.");
                    }
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountStr = JOptionPane.showInputDialog("Enter the amount to deposit:");
                if (amountStr != null) {
                    try {
                        double amount = Double.parseDouble(amountStr);
                        double updatedBalance = account.deposit(amount);
                        JOptionPane.showMessageDialog(frame, "Deposit successful. New balance: " + updatedBalance);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid amount entered.");
                    }
                }
            }
        });

        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double balance = account.getBalance();
                JOptionPane.showMessageDialog(frame, "Your current balance: " + balance);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);
        new ATMGUI(account);
    }
}
