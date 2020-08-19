package common.frame;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JFrameWindowListener extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static int    counter = 0; // счетчик
	private static JLabel label   = null;
	private static String TEMPL   = "Закрытие окна (попыток %d)";
	public JFrameWindowListener ()
	{
		// Создание окна с заголовком
        JFrame frame = new JFrame("JFrameWindowListener");
		// Не закрывать окно по нажатию на кнопку с крестиком 
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Подключение слушателя окна
        frame.addWindowListener(new WindowListener() {
 
            public void windowActivated  (WindowEvent event) {}
            public void windowClosed     (WindowEvent event) {}
            public void windowDeactivated(WindowEvent event) {}
            public void windowDeiconified(WindowEvent event) {}
            public void windowIconified  (WindowEvent event) {}
            public void windowOpened     (WindowEvent event) {}
            // Метод обработки события "Закрытие окна"
            public void windowClosing (WindowEvent event) {
            	if (++counter == 3) {
            		event.getWindow().setVisible(false);
            		System.exit(0);
            	} else
            		label.setText(String.format(TEMPL, counter));
            }
        }); 
        label = new JLabel(String.format(TEMPL, counter));
        frame.getContentPane().add(label);

        frame.setPreferredSize(new Dimension(250, 80));
        frame.pack();
        frame.setVisible(true);
	}
    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new JFrameWindowListener();
    }
}
