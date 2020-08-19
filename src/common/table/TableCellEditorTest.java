package common.table;

// Применение редактора ячеек таблицы CellEditor

import java.awt.Component;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

// Редактор даты с использованием прокручивающегося списка JSpinner

class DateCellEditor extends AbstractCellEditor implements TableCellEditor
{
	private static final long serialVersionUID = 1L;
	// Редактор
	private JSpinner editor;
	// Конструктор
	public DateCellEditor() {
		// Настройка прокручивающегося списка
		SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
		editor = new JSpinner(model);
	}
	// Метод получения компонента для прорисовки
	public Component getTableCellEditorComponent(JTable table, Object value, 
			                               boolean isSelected, int row, int column) {
		// Изменение значения
		editor.setValue(value);
		return editor;
	}
	// Функция текущего значения в редакторе
	public Object getCellEditorValue() {
		return editor.getValue();
	}
}

public class TableCellEditorTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	// Название столбцов
	private String[] columns = { "Сотрудник"   , "Должность", "Дата рождения" };
	// Данные для таблицы
	private Object[][] data = {{ "Сергеев С.С.", "Менеджер"   , new Date()}, 
			                   { "Петров П.П." , "Программист", new Date()},
			                   { "Смирнов А.С" , "Водитель"   , new Date()}};
	public TableCellEditorTest() {
		super("Пример редактора ячеек CellEditor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Создание модели таблицы
		DefaultTableModel model = new DefaultTableModel(data, columns)
		{
			private static final long serialVersionUID = 1L;

			// Функция получения типа столбца
			public Class<?> getColumnClass(int column) {
				return data[0][column].getClass();
			}
		};
		
		// Создание таблицы
		JTable table = new JTable(model);
		// Определение редактора ячеек
		table.setDefaultEditor(Date.class, new DateCellEditor());
		// Раскрывающийся список
		JComboBox<String> combo = new JComboBox<String>(new String[] { "Менеджер", "Программист", "Водитель"});
		// Редактор ячейки с раскрывающимся списком
		DefaultCellEditor editor = new DefaultCellEditor(combo);
		// Определение редактора ячеек для колонки
		table.getColumnModel().getColumn(1).setCellEditor(editor);
		// Вывод окна на экран
		getContentPane().add(new JScrollPane(table));
		setSize(400, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new TableCellEditorTest();
	}
}
