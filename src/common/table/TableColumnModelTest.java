package common.table;



//Использование модели столбцов таблицы TableColumnModel

import java.awt.BorderLayout;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;

import java.util.Enumeration;

public class TableColumnModelTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	// Модель столбцов таблицы
	private TableColumnModel columnModel;
	// Данные для таблиц
    private Object[][] array = new String[][] {{ "Сахар" , "кг", "1.5", "44" },
                                               { "Мука"  , "кг", "4.0", "32" },
                                               { "Молоко", "л" , "2.2", "45" }};
    // Заголовки столбцов
    private Object[] columnsHeader = new String[] {"Наименование", "Ед.измерения", "Количество"};
    public TableColumnModelTest()
    {
	     super("Пример использования TableColumnModel");
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	     // Создание таблицы
	     final JTable table1 = new JTable(array, columnsHeader);
	     // Получаем стандартную модель
	     columnModel = table1.getColumnModel();
	     // Определение минимального и максимального размеров столбцов
	     Enumeration<TableColumn> e = columnModel.getColumns();
	     while ( e.hasMoreElements() ) {
	         TableColumn column = (TableColumn)e.nextElement();
	         column.setMinWidth(50);
	         column.setMaxWidth(200);
	     }
	     // Таблица с автонастройкой размера последней колонки
	     JTable table2 = new JTable(3, 5);
	     table2.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	     // Размещение таблиц в панели с блочным расположением
	     Box contents = new Box(BoxLayout.Y_AXIS);
	     contents.add(new JScrollPane(table1));
	     contents.add(new JScrollPane(table2));
	
	     // Кнопка добавления колонки в модель TableColumnModel
	     JButton add = new JButton("Добавить колонку");
	     // Слушатель обработки события
	     add.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	             // Добавление столбца к модели TableColumnModel
	             TableColumn сolumn = new TableColumn(3, 50);
	             сolumn.setHeaderValue("<html><b>Цена</b></html>");
	             columnModel.addColumn(сolumn);
	         }
	     });
	     // Кнопка перемещения колонки
	     JButton move = new JButton("Переместить колонку");
	     // Слушатель обработки события
	     move.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	             // Индекс первой колоки
	             int first = table1.getSelectedColumn();
	             // Индекс второй колонки
	             int last = (first == columnModel.getColumnCount()) ? first + 1 : 0;
	             // Перемещение столбцов
	             columnModel.moveColumn(first, last);
	         }
	     });
	     // Панель кнопок
	     JPanel pnlButtons = new JPanel();
	     pnlButtons.add(add);
	     pnlButtons.add(move);
	     // Слушатель событий модели столбцов таблицы
	     columnModel.addColumnModelListener(new TableColumnModelListener()
	     {
	         @Override
	         public void columnAdded(TableColumnModelEvent arg0) {
	             System.out.println ("TableColumnModelListener.columnAdded()");
	         }
	         @Override
	         public void columnMarginChanged(ChangeEvent arg0) {
	             System.out.println ("TableColumnModelListener.columnMarginChanged()");
	         }
	         @Override
	         public void columnMoved(TableColumnModelEvent arg0) {
	             System.out.println ("TableColumnModelListener.columnMoved()");
	         }
	         @Override
	         public void columnRemoved(TableColumnModelEvent arg0) {}
	         @Override
	         public void columnSelectionChanged(ListSelectionEvent arg0) {
	             System.out.println ("TableColumnModelListener.columnSelectionChanged()");
	         }
	     });
	
	     // Вывод окна на экран
	     getContentPane().add(contents);
	     getContentPane().add(pnlButtons, BorderLayout.SOUTH);
	     setSize(480, 300);
	     setVisible(true);
	}
    public static void main(String[] args) {
    	new TableColumnModelTest();
    }
}

