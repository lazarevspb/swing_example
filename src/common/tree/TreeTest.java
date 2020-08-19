package common.tree;

// Использование JTree, TreeModel
import java.util.*;

import javax.swing.*;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeModel;
import javax.swing.event.TreeModelListener;

public class TreeTest extends JFrame 
{
	private static final long serialVersionUID = 1L;
	final   String     root  = "Корневая запись";
	final   String[]   nodes = new String[]  {"Напитки", "Сладости"};
	final   String[][] leafs = new String[][]{{"Чай", "Кофе", "Пиво", "Минералка"},
			                                  {"Пирожное", "Мороженое", "Зефир", "Халва"}};
	// создание дерева на основе массива
	Object[] data = new Object[] { nodes[0], nodes[1], 
                    new String[] { leafs[0][0], leafs[0][1], leafs[0][2] }};
	public TreeTest() {
		super("Пример использования JTree");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Данные для дерева на основе вектора
		Vector<String> vector = new Vector<String>();
		for (int i = 0; i < leafs[0].length; i++) {
			 vector.add(leafs[0][i]);
		}
		// Данные для дерева на основе таблицы
		Hashtable<String, String> table = new Hashtable<String, String>();
		for (int i = 0; i < nodes.length; i++) {
			table.put(nodes[i], String.valueOf(i));
		}
		// Создание деревьев
		JTree tree1 = new JTree(data);
		JTree tree2 = new JTree(vector);
		JTree tree3 = new JTree(table);
		JTree tree4 = new JTree(new SimpleModel());
		// Включение отображения корневой записи дерева
		tree3.setRootVisible(true);
		// Размещение деревьев в панели содержимого
		JPanel contents = new JPanel();
		contents.add(tree1);
		contents.add(tree2);
		contents.add(tree3);
		JScrollPane scrollPane = new JScrollPane(tree4, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contents.add(scrollPane);
		setContentPane(contents);
		// Вывод окна на экран
		setSize(400, 300);
		setVisible(true);
	}
	// Модель данных дерева
	class SimpleModel implements TreeModel
	{
		// Список потомков корневой записи
		private ArrayList<String> rootList = new ArrayList<String>();
		// Дочерние узлы первого уровня
		private ArrayList<String>[] tnodes;
		
		@SuppressWarnings("unchecked")
		public SimpleModel()
		{
			// Заполнение списков данными
			tnodes = (ArrayList<String>[]) new ArrayList<?>[nodes.length];
			for (int i = 0; i < nodes.length; i++) {
				rootList.add(nodes[i]);
				tnodes[i] = new ArrayList<String>();
				for (int j = 0; j < leafs[i].length; j++) {
					tnodes[i].add(leafs[i][j]);
				}
			}
		}
		// Функция получения корневого узла дерева
		@Override
		public Object getRoot() {
			return root;
		}
		// Функция получения потомка корневого узла
		private final int getRootChild(Object node)
		{
			int idx = -1;
			for (int i = 0; i < rootList.size(); i++){
				if (rootList.get(i) == node) {
					idx = i;
					break;
				}
			}
			return idx;
		}
		// Функция получения количество потомков узла
		@Override
		public int getChildCount(Object node)
		{
			int idx = getRootChild(node);
			if ( node == root )
				return rootList.size();
			else if ( node == rootList.get(idx))
				return tnodes[idx].size(); 
			return 0;
		}
		// Функция получения потомка узла по порядковому номеру
		@Override
		public Object getChild(Object node, int index)
		{
			int idx = getRootChild(node);
			if ( node == root )
				return rootList.get(index);
			else if ( node == rootList.get(idx))
				return tnodes[idx].get(index); 
			return null;
		}
		// Функция получения порядкового номера потомка
		@Override
		public int getIndexOfChild(Object node, Object child)
		{
			int idx = getRootChild(node);
			if ( node == root )
				return rootList.indexOf(child);
			else if ( node == rootList.get(idx))
				return tnodes[idx].indexOf(child);
			return 0;
		}
		// Функция определения, является ли узел листом
		@Override
		public boolean isLeaf(Object node)
		{
			int idx = getRootChild(node);
			if ((idx >= 0) && tnodes[idx].contains(node))
				return true;
			else
				return false;
		}
		// Функция вызывается при изменении значения некоторого узла
		@Override
		public void valueForPathChanged(TreePath path, Object value) {}
		// Метод присоединения слушателя
		@Override
		public void addTreeModelListener(TreeModelListener tml) {}
		// Методы удаления слушателя
		@Override
		public void removeTreeModelListener(TreeModelListener tml) {}
	}
	public static void main(String[] args) {
		new TreeTest();
	}
}
