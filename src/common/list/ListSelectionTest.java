package common.list;

// Пример использования различных режимов выделения
import javax.swing.*;

public class ListSelectionTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private String[] data = { "Футбол", "Хоккей", "Баскетбол", "Биатлон", "Лыжи"};
	public ListSelectionTest()
	{
		super("Пример модели выделения");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Модель данных
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for (String string : data) 
			dlm.addElement(string);
		// Cписки для разных типов выделения
		JList<String> lst_single   = new JList<String>(dlm);
		JList<String> lst_interval = new JList<String>(dlm);
		JList<String> multiple     = new JList<String>(dlm);
		// Определение максимальной ширины списка
		lst_single.setPrototypeCellValue("Установленный размер");
		// Модели выделения
		lst_single  .setSelectionMode(ListSelectionModel.SINGLE_SELECTION           );
		lst_interval.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION  );
		multiple    .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // Выделение строки списков
		lst_single  .setSelectedIndex(2);
		lst_interval.setSelectionInterval(1, 1);
		lst_interval.addSelectionInterval(2, 3);
		// Список выделяемых строк
		int[] rows = {0, 2, 4};
		multiple .setSelectedIndices(rows); 
		// Добавляем компоненты в интерфейс
		JPanel contents = new JPanel();
		contents.add(new JScrollPane(lst_single  ));
		contents.add(new JScrollPane(lst_interval));
		contents.add(new JScrollPane(multiple    ));
		// Вывод окна
		setContentPane(contents);
		setSize(360, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new ListSelectionTest();
	}
}
