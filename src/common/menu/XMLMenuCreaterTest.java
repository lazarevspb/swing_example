package common.menu;

/**
 * Класс тестирования модуля загрузки меню из файла XML
 */

import javax.swing.*;
import java.awt.event.*;

public class XMLMenuCreaterTest extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	public XMLMenuCreaterTest()
	{
		super("XML Menu Loader");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			// открываем файл XML с описанием меню
			XMLReader xmlReader = new XMLReader ("menu.xml", "UTF8");
			// Загружаем меню
			XMLMenuCreater menuCreater = new XMLMenuCreater(xmlReader.getInputSource());
			menuCreater.parse();
			// Подключение строки меню
			setJMenuBar(menuCreater.getMenuBar("mainMenu"));
			// Подключение слушателя
			menuCreater.addActionListener("exit", new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}});
		} catch (Exception e) {
			e.printStackTrace();
		}
		// выводим окно на экран
		setSize(300, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new XMLMenuCreaterTest();
	}
}