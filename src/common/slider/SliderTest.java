package common.slider;

// Пример использования ползунков

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.util.Dictionary;
import java.util.Hashtable;

public class SliderTest extends JFrame 
{
	private static final long serialVersionUID = 1L;

	private JLabel label;
	
	public SliderTest()
	{
		super("Пример использования ползунков JSlider");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Таблица с надписями ползунка
		Dictionary <Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(new Integer(0), new JLabel("<html><font color=red size=3>0"));
		labels.put(new Integer(60), new JLabel("<html><font color=gray size=3>30"));
		labels.put(new Integer(120), new JLabel("<html><font color=blue size=4>60"));
		labels.put(new Integer(180), new JLabel(new ImageIcon("images/star.png")));
		
		// Создание модели ползунков
		BoundedRangeModel model = new DefaultBoundedRangeModel(80, 0, 0, 200);

		// Создание ползунков
		JSlider slider1 = new JSlider(model);
		JSlider slider2 = new JSlider(model);
		JSlider slider3 = new JSlider(0, 80, 20);
		JSlider slider4 = new JSlider(model);

		// Настройка внешнего вида ползунков
		slider2.setOrientation(JSlider.VERTICAL);
		slider2.setMajorTickSpacing(50);
		slider2.setMinorTickSpacing(10);
		slider2.setPaintTicks(true);
		
		slider3.setPaintLabels(true);
		slider3.setMajorTickSpacing(10);
		
		slider4.setLabelTable(labels);
		slider4.setPaintLabels(true);

		label = new JLabel(getPercent(slider1.getValue()));
		// присоединяем слушателя
		slider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// меняем надпись
				int value = ((JSlider)e.getSource()).getValue();
				label.setText(getPercent(value));
			}
		});
		// Размещение ползунков в интерфейсе
		JPanel contents = new JPanel();
		contents.add(slider1);
		contents.add(slider2);
		contents.add(slider3);
		contents.add(slider4);
		getContentPane().add(contents);
		getContentPane().add(label, BorderLayout.SOUTH);
		// Вывод окна на экран
		setSize(500, 300);
		setVisible(true);
	}
	private String getPercent(int value)
	{
		return "Размер : " + (int)value/2 + "%";
	}
	public static void main(String[] args) {
		new SliderTest();
	}
}
