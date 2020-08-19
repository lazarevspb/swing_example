package common.list;

// Пример использования раскрывающихся списков

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class ComboBoxTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	// Массив элементов списка
	public String[] elements = new String[] {"Офис", "Склад", "Гараж", "Производство", "Столовая"};
	
	private JComboBox<String> cbFirst;
	private DefaultComboBoxModel<String> cbModel;
	
	public ComboBoxTest() {
		super("Пример JComboBox");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Модель данных списка
		cbModel = new DefaultComboBoxModel<String>();
		for (int i = 0; i < elements.length; i++) 
			cbModel.addElement((String)elements[i]);
		// Данные для 2-го списка
		Vector<String> data = new Vector<String>();
		for (int i = 0; i < 10; i++) 
			data.add(String.format("#%d элемент", i));
		// 1-й раскрывающийся список
		cbFirst = new JComboBox<String>(cbModel);
		// Меняем элемент Гараж на Автопарк
		cbModel.setSelectedItem("Гараж");
		int idx = cbModel.getIndexOf(cbModel.getSelectedItem()); 
		cbModel.removeElementAt(idx);
		cbModel.insertElementAt("Автопарк", idx);
		cbModel.setSelectedItem("Автопарк");
		// Определяем размер списка
		cbFirst.setPrototypeDisplayValue("Максимальный размер");
		// 2-й раскрывающийся список
		JComboBox<String> cbSecond = new JComboBox<String>(data);
		/*
		 *  Определение свойств списка - редактируемый, количество элементов 
		 *  в раскрывающемся окне
		 */
		cbSecond.setEditable(true);
		cbSecond.setMaximumRowCount(5);
		// Кнопка добавления элемента в модель данных
		JButton btnAdd = new JButton("Добавить");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Выбираем позицию предпоследнего элемента
				int pos = cbModel.getSize() - 1;
				cbModel.insertElementAt("~ Добавленная строка ~", pos);
			}
		});
		
		// Размещение компонентов в интерфейсе и вывод окна
		JPanel contents = new JPanel();
		contents.add  (cbSecond);
		contents.add  (cbFirst );
		contents.add  (btnAdd  );
		setContentPane(contents);
		setSize(450, 180);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ComboBoxTest();
	}
}

