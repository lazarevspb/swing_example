package common.text;

// Браузер на основе редактора JEditorPane

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class EditorPaneTest extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private  JEditorPane  editor ;  // наш редактор
	private  JTextField   address; 	// текстовое поле с адресом
	private  final String unavailable = "Адрес недоступен";
	
	public EditorPaneTest()
	{
		super("Пример с JEditorPane");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Создаие пользовательского интерфейса
		createGUI();
		// Вывод окна на экран
		setSize(500, 400);
		setVisible(true);
	}
	/**
	 * Процедура создания интерфейса
	 */
	private void createGUI()
	{
		// Панель с адресной строкой
		JPanel addressPanel = new JPanel();
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		addressPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		// Поле URL адреса
		address = new JTextField("http://mail.ru", 35);
		// Слушатель окончания ввода
		address.addActionListener(new NewAddressAction());
		addressPanel.add(new JLabel("Адрес:"));
		addressPanel.add(address);
		try {
			// Создание редактора
			editor = new JEditorPane(address.getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, unavailable);
		}
		editor.setContentType("text/html");
		editor.setEditable(false);
		// Поддержка ссылок
		editor.addHyperlinkListener(new HyperlinkL());
		// Размещение в форме
		getContentPane().add(addressPanel, "North");
		getContentPane().add(new JScrollPane(editor));
	}
	// Слушатель, получающий уведомления о вводе нового адреса
	class NewAddressAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Переход по адресу
			String newAddress = address.getText();
			try {
				editor.setPage(newAddress);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(EditorPaneTest.this, unavailable);
			}
		}
	}
	// Слушатель, обеспечивающий поддержку ссылок
	class HyperlinkL implements HyperlinkListener {
		public void hyperlinkUpdate(HyperlinkEvent he) {
			// Проверка типа события
			if ( he.getEventType() != HyperlinkEvent.EventType.ACTIVATED ) 
				return;
			// Переходим по адресу
			try {
				editor.setPage(he.getURL());
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(EditorPaneTest.this, unavailable);
			}
		}
	}
	public static void main(String[] args) {
		new EditorPaneTest();
	}
}
