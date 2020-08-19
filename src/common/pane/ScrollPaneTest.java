package common.pane;

// Использование панелей прокрутки JScrollPane
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ScrollPaneTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	private  JLabelGrid label;
	public ScrollPaneTest()
	{
		super("Пример использования JScrollPane");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Создание панели прокрутки
		label = new JLabelGrid();
		JScrollPane scrollPane = new JScrollPane(label, 
				                                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		                                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// Устанавливаем заголовки
		scrollPane.setColumnHeaderView(new XHeader());
		scrollPane.setRowHeaderView(new YHeader());
		// Определение свойств панели прокрутки
		scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.yellow));
		scrollPane.setWheelScrollingEnabled(true);
		// Вывод окна на экран
		getContentPane().add(scrollPane);
		setSize(450, 300);
		setVisible(true);
	}

	class JLabelGrid extends JLabel implements Scrollable
	{
		private static final long serialVersionUID = 1L;
		
		private int CELL_SIZE  = 10;  // размер ячейки сетки
		private int CELL_COUNT = 50;  // количество ячеек сетки
		
		public JLabelGrid()
		{
			// Изображение волков
			setIcon(new ImageIcon("images/wolf.jpg"));
		}
		// Предпочтительный размер компонента
		public Dimension getPreferredSize() {
			return new Dimension(CELL_SIZE*CELL_COUNT, CELL_SIZE*CELL_COUNT);
		}
		// Прорисовка компонента
		public void paintComponent(Graphics g) {
			// Вызов метода базового класса
			super.paintComponent(g);
			for (int x = 0; x < CELL_COUNT; x++) {
				for (int y = 0; y < CELL_COUNT; y++) {
					// Прорисовывем ячейку
					g.setColor(Color.black);
					g.drawRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);
				}
			}
		}
		// Предпочтительный размер области прокрутки
		public Dimension getPreferredScrollableViewportSize() {
			return getPreferredSize();
		}
		// Приращение при прокрутке на один элемент
		public int getScrollableUnitIncrement(Rectangle visible, int or, int dir) {
			return CELL_SIZE;
		}
		// Приращение при прокрутке "блоком"
		public int getScrollableBlockIncrement(Rectangle visible, int or, int dir) {
			return CELL_SIZE*10;
		}
		// Контроль размера области прокрутки
		public boolean getScrollableTracksViewportWidth() {
			return false;
		}
		public boolean getScrollableTracksViewportHeight() {
			return false;
		}
	}
	// Заголовок по оси X
	class XHeader extends JPanel
	{
		private static final long serialVersionUID = 1L;
		// Размер заголовка
		public Dimension getPreferredSize() {
			return new Dimension(label.getPreferredSize().width, 20);
		}
		// Прорисовываем линейку
		public void paintComponent(Graphics g) {
			int width = getWidth();
			for (int i=0; i<width; i+=50) {
				g.drawString("" + i, i, 15);
			}
		}
	}
	// Заголовок по оси Y
	class YHeader extends JPanel
	{
		private static final long serialVersionUID = 1L;
		// Размер заголовка
		public Dimension getPreferredSize() {
			return new Dimension(20, label.getPreferredSize().height);
		}
		// Прорисовываем линейку
		public void paintComponent(Graphics g) {
			int height = getHeight();
			for (int i=0; i<height; i+=50) {
				g.drawString("" + i, 0, i);
			}
		}
	}
	public static void main(String[] args)
	{
		new ScrollPaneTest();
	}
}
