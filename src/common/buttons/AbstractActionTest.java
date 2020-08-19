package common.buttons;

// Использование Action при создании кнопки

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AbstractActionTest extends JFrame 
{
    private static final long serialVersionUID = 1L;

    private  final  String  BUTTON_NAME = "button1"; 

    public AbstractActionTest()
    {
        super("Пример использования Action");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Панель содержимого
        Container container = getContentPane();
        // Устанавливаем менеджер последовательного расположения
        container.setLayout(new FlowLayout());
        // Создание кнопок, выполняющих одно действие
        Action action = new SimpleAction();
        JButton button1 = new JButton(action);
        button1.setName(BUTTON_NAME);
        button1.setText("First button");
        button1.setMnemonic('F');
        JButton button2 = new JButton(action);
        button2.setName("button2");
        button2.setText("Second button");
        button2.setMnemonic('S');
        container.add(button1);
        container.add(button2);
        // выводим окно на экран
        setSize(320, 100);
        setVisible(true);
    }
    // Внутренний класс
    class SimpleAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        SimpleAction() {
                // Параметры команды
                // putValue(NAME, "Класс Action!");
                putValue(SHORT_DESCRIPTION, "Это подсказка");
                // putValue(MNEMONIC_KEY, new Integer('A'));
        }
        // Обработка события нажатия на кнопку
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            System.out.println("Нажатие на кнопку <" + btn.getName() + ">");
            // можно выключить команду, не зная, к каким компонентам она присоединена
            if (btn.getName().equalsIgnoreCase(BUTTON_NAME)) {
                btn.setEnabled(false);
                // Изменение надписи
                // putValue(NAME, "Disable button");
                btn.setText("Disable button");
            }
        }
    };
    public static void main(String[] args) {
        new AbstractActionTest();
    }
}