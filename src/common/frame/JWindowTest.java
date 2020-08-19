package common.frame;

// Пример использования окна без рамки JWindow
import javax.swing.*;

import java.awt.*;

// Класс прорисовки изображения
class ImageDraw extends JComponent
{
	private static final long serialVersionUID = 1L;
	private Image capture;

	ImageDraw (Image capture) {
		this.capture = capture;
	}
	public void paintComponent(Graphics g) {
		// Прорисовка изображения
		g.drawImage(capture, 0, 0, this);
	}
}
public class JWindowTest extends JWindow
{
	private static final long serialVersionUID = 1L;
	// изображение "рабочего стола"
	private Image capture;
	// Размер окна 
	private int window_w = 300, window_h = 300;
	
	public JWindowTest() {
		super();
		// Определение положение окна на экране
		setLocation(200, 100);
		// Определение размера окна
		setSize (window_w, window_h);
		try {
			// "Вырезаем" часть изображения "рабочего стола"
			Robot robot = new Robot();
			capture = robot.createScreenCapture(
				     new Rectangle(5, 5, window_w, window_h));
		} catch (Exception ex) { ex.printStackTrace(); }
		// Добавляем в интерфейс изображение
		getContentPane().add(new ImageDraw(capture));
		// Открываем окно
		setVisible(true);
		try {
			// Заканчиваем работу через 10 сек
			Thread.currentThread();
			Thread.sleep(10000);
		} catch (Exception e) { }
			System.exit(0);
	}
	public static void main(String[] args) {
		new JWindowTest();
	}
}