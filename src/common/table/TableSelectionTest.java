package common.table;

// Пример выделения ячеек таблицы с использованием SelectionModel

import javax.swing.*;
import javax.swing.table.*;

public class TableSelectionTest extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	// Данные для таблиц
    private Object[][] array = new String[][] {{ "Сахар" , "кг", "1.5", "44" },
                                               { "Мука"  , "кг", "4.0", "32" },
                                               { "Молоко", "л" , "2.2", "45" }};
    // Заголовки столбцов
    private Object[] columnsHeader = new String[] {"Наименование", "Ед.измерения", "Количество"};
	public TableSelectionTest()
	{
		super("Пример использованием SelectionModel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Таблица с выделение по столбцам
		JTable tableColumn = new JTable(array, columnsHeader);
		// Блокирвание выделения по строкам
		tableColumn.setRowSelectionAllowed(false);
		// Модель столбцов
		TableColumnModel columnModel = tableColumn.getColumnModel();
		// Разрешение выделения столбцоа
		columnModel.setColumnSelectionAllowed(true);
		// Режим одиночного выделения
		columnModel.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Таблица с выделением по строкам
		JTable tableRow = new JTable(array, columnsHeader);
		// Режим выделения интервала по строкам
		tableRow.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		// Размещение таблиц в панели с блочным расположением
	     Box contents = new Box(BoxLayout.Y_AXIS);
	     contents.add(new JScrollPane(tableColumn));
	     contents.add(new JScrollPane(tableRow   ));
	     setContentPane(contents);

	     // Вывод окна на экран
	     setSize(480, 300);
	     setVisible(true);
	}
	public static void main(String[] args) {
		new TableSelectionTest();
	}
}
