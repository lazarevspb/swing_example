package common.tree;

// Swing пример использования стандартной модели дерева DefaultTreeModel
// и узлов DefaultMutableTreeNode
import javax.swing.*;
import javax.swing.tree.*;

import java.awt.GridLayout;

public class DefaultTreeModelTest extends JFrame
{
	private static final long serialVersionUID = 1L;

	final   String     ROOT  = "Корневая запись";
	// Массив листьев деревьев
	final   String[]   nodes = new String[]  {"Напитки", "Сладости"};
	final   String[][] leafs = new String[][]{{"Чай", "Кофе", "Коктейль", "Сок", "Морс", "Минералка"},
			                                  {"Пирожное", "Мороженое", "Зефир", "Халва"}};
	public DefaultTreeModelTest() {
		super("Пример использования DefaultTreeModel");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Создание древовидной структуры
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(ROOT);
		// Ветви первого уровня
		DefaultMutableTreeNode drink = new DefaultMutableTreeNode(nodes[0]);
		DefaultMutableTreeNode sweet = new DefaultMutableTreeNode(nodes[1]);
		// Добавление ветвей к корневой записи
		root.add(drink);
		root.add(sweet);
		// Добавление к корневой записи новой ветви
		root.add(new DefaultMutableTreeNode("Фрукты", true));
		// Добавление листьев
		for ( int i = 0; i < leafs[0].length; i++)
			drink.add(new DefaultMutableTreeNode(leafs[0][i], false));
		for ( int i = 0; i < leafs[1].length; i++)
			sweet.add(new DefaultMutableTreeNode(leafs[1][i], false));

		// Создание стандартной модели и дерево
		DefaultTreeModel treeModel1 = new DefaultTreeModel(root, true);
		JTree tree1 = new JTree(treeModel1);

		// Создание модели дерева, начиная с drink
		DefaultTreeModel treeModel2 = new DefaultTreeModel(drink);
		JTree tree2 = new JTree(treeModel2);
		// Размещение деревьев в интерфейсе
		JPanel contents = new JPanel(new GridLayout(1, 2));
		contents.add(new JScrollPane(tree1));
		contents.add(new JScrollPane(tree2));
		setContentPane(contents);
		// Открываем окно
		setSize(400, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new DefaultTreeModelTest();
	}
}
