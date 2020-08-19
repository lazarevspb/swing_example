package common.table;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.Icon;

public class TableCellTest extends JFrame
{
	private static final long serialVersionUID = 1L;

	// Значки
	private Icon manIcon   = new ImageIcon("images/man.png"), 
			     womanIcon = new ImageIcon("images/woman.png");
	// Названия столбцов
	private String[] columnNames = {"Роль", "Образ", "Актер"};
	// Данные таблицы
	private Object[][] data = {{ new ImageTextCell("Остап Бендер" , manIcon),  
		                         "<html><b>Великий комбинатор", 
		                         new ImageTextCell("Арчил Гомиашвили") },
                               { new ImageTextCell("Киса Воробьянинов", manIcon), 
		                         "<html><i>Предводитель дворянства", 
		                         new ImageTextCell("Сергей Филиппов")},
	                         { new ImageTextCell("Мадам Грицацуева", womanIcon), 
		                         "<html><font color=\"red\">Знойная женщина", 
		                         new ImageTextCell("Наталья Крачковская")}};	
	public TableCellTest()
	{
		super("Пример использования TableCellRenderer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Создание таблицы на основе модели данных
		JTable table = new JTable(new DataModel(data, columnNames));
		// Регистрация объекта для прорисовки данных
		table.setDefaultRenderer(ImageTextCell.class, new ImageTextCellRenderer());
		// Вывод окна на экран
		getContentPane().add(new JScrollPane(table));
		setSize(500, 300);
		setVisible(true);
	}
	
	// Объект, включающий значок и текст
	class ImageTextCell
	{
		public Icon   icon;
		public String text;
		// Конструкторы
		public ImageTextCell(String text)
		{
			this (text, null);
		}
		public ImageTextCell(String text, Icon icon)
		{
			this.icon = icon;
			this.text = text;
		}
	}

	// Класс отображения объекта в ячейке таблицы
	class ImageTextCellRenderer extends DefaultTableCellRenderer 
	{
		private static final long serialVersionUID = 1L;

		// Метод получения компонента для прорисовки
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		                                               boolean hasFocus, int row, int column)
		{
			// Объект прорисовки
			ImageTextCell objectCell = (ImageTextCell)value;
			// Надпись от базового класса
			JLabel label = (JLabel)super.getTableCellRendererComponent(table, 
					                        objectCell.text, isSelected, hasFocus, row, column);
			// Размещение значка
			label.setIcon(objectCell.icon);
			// Выравнивание
			if (column == 2)
				label.setHorizontalAlignment(JLabel.RIGHT);
			else
				label.setHorizontalAlignment(JLabel.LEFT);
			return label;
		}
	}
	// Модель данных таблицы
	class DataModel extends AbstractTableModel 
	{
		private static final long serialVersionUID = 1L;
		// Названия столбцов
		private String[] columnNames = null;
		// Данные таблицы
		private Object[][] data = null;
		DataModel(Object[][] data, String[] columnNames)
		{
			this.data        = data;
			this.columnNames = columnNames;
		}
		// Количество столбцов
		public int getColumnCount() {
			return columnNames.length;
		}
		// Названия столбцов
		public String getColumnName(int column) {
			return columnNames[column];
		}
		// Количество строк
		public int getRowCount() {
			return data.length;
		}
		// Тип данных столбца
		public Class<?> getColumnClass(int column) {
			return data[0][column].getClass();
		}
		// Значение ячейки
		public Object getValueAt(int row, int column) {
			return data[row][column];
		}
	}
	public static void main(String[] args) {
		new TableCellTest();
	}
}
