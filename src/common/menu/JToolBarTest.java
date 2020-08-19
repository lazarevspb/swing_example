package common.menu;

// Класс создания комбинированных панелей инструментов
import javax.swing.*;

import java.awt.event.*;

public class JToolBarTest extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public JToolBarTest()
	{
		super("Пример использования JToolBar");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Создание панелей инструментов
		JToolBar tbCommon = new JToolBar();
		tbCommon.add(new OpenAction());
		tbCommon.add(new ExitAction());
		tbCommon.addSeparator();
		tbCommon.add(new JButton("Стиль"));
		// Блокируем возможность перетаскивания панели
		tbCommon.setFloatable(false);
		
		String[] schools = new String[] {"Школа", "Институт", "Академия"};
		JToolBar tbEducation = new JToolBar();
		tbEducation.add(new JButton("Образование"));
		tbEducation.add(new JComboBox<String> (schools));
		// Блокируем возможность перетаскивания панели
		tbEducation.setFloatable(false);
		// Блокируем возможность эффекта интерактивности - при наведении
		// мыши кнопка выделяется
		tbEducation.setRollover(false);
		
		JToolBar tbStyle = new JToolBar();
		tbStyle.add(new JButton("Обычный"));
		tbStyle.add(new JButton("Полужирный"));
		tbStyle.add(new JButton("Подчеркнутый"));
		
		// Выравнивание содержимого
		tbEducation.add(Box.createGlue());

		// Добавление двух панелей инструментов
		BoxLayoutUtils blUtils = new BoxLayoutUtils();
		// Панель с горизонтальным расположением компонентов
		JPanel first = blUtils.createHorizontalPanel();
		first.add(tbCommon);
		first.add(Box.createHorizontalStrut(5));
		first.add(tbEducation);
		// Панель с вертикальным расположением компонентов
		JPanel all = blUtils.createVerticalPanel();
		all.add(first);
		all.add(tbStyle);
		// Выравнивание по горизонтали
		blUtils.setGroupAlignmentX(new JComponent[] { first, tbStyle }, 
		                        JComponent.LEFT_ALIGNMENT);
		// Размещение в верхней части окна
		getContentPane().add(all, "North");
		// Выводим окно на экран
		setSize(400, 300);
		setVisible(true);
	}
	//-----------------------------------------------------------------------------
	// Команда для кнопки "Открытия"
	class OpenAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public OpenAction() {
			// Настройка иконок
			putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/open.png"));
		}
		// Обработка действия
		public void actionPerformed(ActionEvent e) {
			// ничего не делаем
		}
	}
	//-----------------------------------------------------------------------------
	// Команда для кнопки "Выхода"
	class ExitAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public ExitAction() {
			// Настройка иконок
			putValue(AbstractAction.SMALL_ICON, new ImageIcon("images/exit.png"));
		}
		// Обработка действия
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	//-----------------------------------------------------------------------------
	/**
	 * Класс выравнивания компонентов в контейнере
	 */
	class BoxLayoutUtils 
	{
		// Выравнивание компонентов по оси X
		public void setGroupAlignmentX(JComponent[] cs, float alignment) {
			for (int i=0; i<cs.length; i++) {
				cs[i].setAlignmentX(alignment);
			}
		}
		// Выравнивание компонентов по оси Y
		public void setGroupAlignmentY(JComponent[] cs, float alignment) {
			for (int i=0; i<cs.length; i++) {
				cs[i].setAlignmentY(alignment);
				}
		}
		// Создание панели с установленным вертикальным блочным расположением
		public JPanel createVerticalPanel() {
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
			return p;
		}
		// Создание панели с установленным горизонтальным блочным расположением
		public JPanel createHorizontalPanel() {
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
			return p;
		}
	}
	//-----------------------------------------------------------------------------
	public static void main(String[] args) {
		new JToolBarTest();
	}
}
