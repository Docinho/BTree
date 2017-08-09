package adt.btree;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import adt.btree.BTree;
import adt.btree.BTreeImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class StudentTestBTree {

	protected BTree<Integer> tree1;
	private BTree<Integer> bt;

	@Before
	public void setUp() throws Exception {
		tree1 = new BTreeImpl<Integer>(4);
		bt = new BTreeImpl<Integer>(3);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(tree1.isEmpty());
	}

	@Test
	public void testHeight() {
		assertEquals(-1, tree1.height());
		tree1.insert(2);
		assertEquals(0, tree1.height());
	}

	@Test
	public void testDepthLeftOrder() {
		tree1.insert(2);
		tree1.insert(4);
		tree1.insert(6);
		tree1.insert(8);
		try {
			assertEquals("[[6], [2, 4], [8]]", Arrays.toString(tree1.depthLeftOrder()));
		} catch (AssertionError e) {
			assertEquals("[[4], [2], [6, 8]]", Arrays.toString(tree1.depthLeftOrder()));
		}
	}

	@Test
	public void testSize() {
		assertEquals(0, tree1.size());
		tree1.insert(18);
		assertEquals(1, tree1.size());
	}

	@Test
	public void testInsert() {
		bt.insert(1);
		bt.insert(2);
		assertEquals(0, bt.height());
		bt.insert(3);
		assertEquals(1, bt.height());
		assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] { 2 });
		assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] { 1 });
		assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] { 3 });

		bt.insert(4);
		assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] { 2 });
		assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] { 1 });
		assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] { 3, 4 });

		bt.insert(5);
		assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] { 2, 4 });
		assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] { 1 });
		assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] { 3 });
		assertArrayEquals(bt.getRoot().getChildren().get(2).getElements().toArray(), new Integer[] { 5 });

		bt.insert(6);
		assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] { 2, 4 });
		assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] { 1 });
		assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] { 3 });
		assertArrayEquals(bt.getRoot().getChildren().get(2).getElements().toArray(), new Integer[] { 5, 6 });

		bt.insert(7);
		assertArrayEquals(bt.getRoot().getElements().toArray(), new Integer[] { 4 });
		assertArrayEquals(bt.getRoot().getChildren().get(0).getElements().toArray(), new Integer[] { 2 });
		assertArrayEquals(bt.getRoot().getChildren().get(0).getChildren().get(0).getElements().toArray(),
				new Integer[] { 1 });
		assertArrayEquals(bt.getRoot().getChildren().get(0).getChildren().get(1).getElements().toArray(),
				new Integer[] { 3 });

		assertArrayEquals(bt.getRoot().getChildren().get(1).getElements().toArray(), new Integer[] { 6 });
		assertArrayEquals(bt.getRoot().getChildren().get(1).getChildren().get(0).getElements().toArray(),
				new Integer[] { 5 });
		assertArrayEquals(bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray(),
				new Integer[] { 7 });

	}

	@Test
	public void testInsert2() {

		bt.insert(13);
		assertArrayEquals(new Integer[] { 13 }, bt.getRoot().getElements().toArray());
		bt.insert(9);
		assertArrayEquals(new Integer[] { 9, 13 }, bt.getRoot().getElements().toArray());
		bt.insert(5);
		assertArrayEquals(new Integer[] { 9 }, bt.getRoot().getElements().toArray());
		assertArrayEquals(new Integer[] { 5 }, bt.getRoot().getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 13 }, bt.getRoot().getChildren().get(1).getElements().toArray());

		assertEquals(bt.size(), 3);
		assertEquals(bt.height(), 1);

		bt.insert(12);
		assertArrayEquals(new Integer[] { 12, 13 }, bt.getRoot().getChildren().get(1).getElements().toArray());
		bt.insert(99);
		assertArrayEquals(new Integer[] { 9, 13 }, bt.getRoot().getElements().toArray());
		assertArrayEquals(new Integer[] { 5 }, bt.getRoot().getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 12 }, bt.getRoot().getChildren().get(1).getElements().toArray());
		assertArrayEquals(new Integer[] { 99 }, bt.getRoot().getChildren().get(2).getElements().toArray());
		bt.insert(-1);
		assertArrayEquals(new Integer[] { -1, 5 }, bt.getRoot().getChildren().get(0).getElements().toArray());

		assertEquals(bt.size(), 6);
		assertEquals(bt.height(), 1);

		bt.insert(122);
		bt.insert(3);

		assertEquals(bt.size(), 8);
		assertEquals(bt.height(), 2);
		assertArrayEquals(new Integer[] { 9 }, bt.getRoot().getElements().toArray());
		assertArrayEquals(new Integer[] { 3 }, bt.getRoot().getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 13 }, bt.getRoot().getChildren().get(1).getElements().toArray());
		assertArrayEquals(new Integer[] { -1 },
				bt.getRoot().getChildren().get(0).getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 5 },
				bt.getRoot().getChildren().get(0).getChildren().get(1).getElements().toArray());
		assertArrayEquals(new Integer[] { 12 },
				bt.getRoot().getChildren().get(1).getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 99, 122 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray());

		bt.insert(76);
		assertArrayEquals(new Integer[] { 13, 99 }, bt.getRoot().getChildren().get(1).getElements().toArray());
		assertArrayEquals(new Integer[] { 12 },
				bt.getRoot().getChildren().get(1).getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 76 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray());
		assertArrayEquals(new Integer[] { 122 },
				bt.getRoot().getChildren().get(1).getChildren().get(2).getElements().toArray());
		bt.insert(77);

		assertEquals(bt.size(), 10);
		assertEquals(bt.height(), 2);

		bt.insert(78);
		bt.insert(79);
		assertArrayEquals(new Integer[] { 9, 77 }, bt.getRoot().getElements().toArray());
		// noh a esquerda da raiz
		assertArrayEquals(new Integer[] { 3 }, bt.getRoot().getChildren().get(0).getElements().toArray());

		// noh a esquerda do noh a esquerda da raizhttps://www.google.com.br/search?client=ubuntu&channel=fs&q=cro&ie=utf-8&oe=utf-8&gws_rd=cr&ei=T26LWYLUD4K9wAT7zLDoAw
		assertArrayEquals(new Integer[] { -1 },
				bt.getRoot().getChildren().get(0).getChildren().get(0).getElements().toArray());
		// noh a direita do noh a esquerda da raiz
		assertArrayEquals(new Integer[] { 5 },
				bt.getRoot().getChildren().get(0).getChildren().get(1).getElements().toArray());

		// filho do meio da raiz
		assertArrayEquals(new Integer[] { 13 }, bt.getRoot().getChildren().get(1).getElements().toArray());
		// filho a esquerda do filho do meio da raiz
		assertArrayEquals(new Integer[] { 12 },
				bt.getRoot().getChildren().get(1).getChildren().get(0).getElements().toArray());
		// filho a direita do filho do meio da raiz
		assertArrayEquals(new Integer[] { 76 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray());

		// filho a direita da raiz
		assertArrayEquals(new Integer[] { 99 }, bt.getRoot().getChildren().get(2).getElements().toArray());
		// filho a esquerda do filho a direita da raiz
		assertArrayEquals(new Integer[] { 78, 79 },
				bt.getRoot().getChildren().get(2).getChildren().get(0).getElements().toArray());
		// filho a direita do filho a direita da raiz
		assertArrayEquals(new Integer[] { 122 },
				bt.getRoot().getChildren().get(2).getChildren().get(1).getElements().toArray());

		assertEquals(bt.size(), 12);
		assertEquals(bt.height(), 2);

		bt.insert(58);
		// filho a direita do filho do meio da raiz
		assertArrayEquals(new Integer[] { 58, 76 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray());
		bt.insert(70);
		// filho do meio da raiz
		assertArrayEquals(new Integer[] { 13, 70 }, bt.getRoot().getChildren().get(1).getElements().toArray());
		// filho a esquerda do filho do meio da raiz
		assertArrayEquals(new Integer[] { 12 },
				bt.getRoot().getChildren().get(1).getChildren().get(0).getElements().toArray());
		// filho do meio do filho do meio da raiz
		assertArrayEquals(new Integer[] { 58 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray());
		// filho a direita do filho do meio da raiz
		assertArrayEquals(new Integer[] { 76 },
				bt.getRoot().getChildren().get(1).getChildren().get(2).getElements().toArray());

		assertEquals(bt.size(), 14);
		assertEquals(bt.height(), 2);

		bt.insert(59);
		// filho do meio do filho do meio da raiz
		assertArrayEquals(new Integer[] { 58, 59 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray());
		bt.insert(133);
		// filho a direita do filho a direita da raiz
		assertArrayEquals(new Integer[] { 122, 133 },
				bt.getRoot().getChildren().get(2).getChildren().get(1).getElements().toArray());
		bt.insert(140);
		// filho a direita da raiz
		assertArrayEquals(new Integer[] { 99, 133 }, bt.getRoot().getChildren().get(2).getElements().toArray());
		// filho a esquerda do filho a direita da raiz
		assertArrayEquals(new Integer[] { 78, 79 },
				bt.getRoot().getChildren().get(2).getChildren().get(0).getElements().toArray());
		// filho do meio do filho a direita da raiz
		assertArrayEquals(new Integer[] { 122 },
				bt.getRoot().getChildren().get(2).getChildren().get(1).getElements().toArray());
		// filho a direita do filho a direita da raiz
		assertArrayEquals(new Integer[] { 140 },
				bt.getRoot().getChildren().get(2).getChildren().get(2).getElements().toArray());

		assertEquals(bt.size(), 17);
		assertEquals(bt.height(), 2);

		bt.insert(141);
		bt.insert(142);

		// raiz
		assertArrayEquals(new Integer[] { 77 }, bt.getRoot().getElements().toArray());
		// noh a esquerda da raiz
		assertArrayEquals(new Integer[] { 9 }, bt.getRoot().getChildren().get(0).getElements().toArray());
		// noh a esquerda do noh a esquerda da raiz
		assertArrayEquals(new Integer[] { 3 },
				bt.getRoot().getChildren().get(0).getChildren().get(0).getElements().toArray());
		// noh a direita do noh a esquerda da raiz
		assertArrayEquals(new Integer[] { 13, 70 },
				bt.getRoot().getChildren().get(0).getChildren().get(1).getElements().toArray());

		// filho a direita da raiz
		assertArrayEquals(new Integer[] { 133 }, bt.getRoot().getChildren().get(1).getElements().toArray());
		// filho a esquerda do filho a direita da raiz
		assertArrayEquals(new Integer[] { 99 },
				bt.getRoot().getChildren().get(1).getChildren().get(0).getElements().toArray());
		// filho a direita do filho a direita da raiz
		assertArrayEquals(new Integer[] { 141 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray());

		bt.insert(143);
		bt.insert(60);
		// noh a esquerda da raiz
		assertArrayEquals(new Integer[] { 9, 59 }, bt.getRoot().getChildren().get(0).getElements().toArray());
		// noh a esquerda do noh a esquerda da raiz
		assertArrayEquals(new Integer[] { 3 },
				bt.getRoot().getChildren().get(0).getChildren().get(0).getElements().toArray());
		// noh do meio do noh a esquerda da raiz
		assertArrayEquals(new Integer[] { 13 },
				bt.getRoot().getChildren().get(0).getChildren().get(1).getElements().toArray());
		assertArrayEquals(new Integer[] { 12 },
				bt.getRoot().getChildren().get(0).getChildren().get(1).getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 58 },
				bt.getRoot().getChildren().get(0).getChildren().get(1).getChildren().get(1).getElements().toArray());
		// noh a direita do noh a esquerda da raiz
		assertArrayEquals(new Integer[] { 70 },
				bt.getRoot().getChildren().get(0).getChildren().get(2).getElements().toArray());
		assertArrayEquals(new Integer[] { 60 },
				bt.getRoot().getChildren().get(0).getChildren().get(2).getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 76 },
				bt.getRoot().getChildren().get(0).getChildren().get(2).getChildren().get(1).getElements().toArray());
		assertEquals(bt.size(), 21);
		assertEquals(3, bt.height());

		bt.insert(145);
		// noh a direita do noh a direita da raiz
		assertArrayEquals(new Integer[] { 141, 143 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray());
		assertArrayEquals(new Integer[] { 140 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 142 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getChildren().get(1).getElements().toArray());
		assertArrayEquals(new Integer[] { 145 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getChildren().get(2).getElements().toArray());
		bt.insert(146);
		bt.insert(147);
		// noh a direita da raiz
		assertArrayEquals(new Integer[] { 133, 143 }, bt.getRoot().getChildren().get(1).getElements().toArray());
		// noh do meio do noh a direita da raiz
		assertArrayEquals(new Integer[] { 141 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getElements().toArray());
		assertArrayEquals(new Integer[] { 140 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 142 },
				bt.getRoot().getChildren().get(1).getChildren().get(1).getChildren().get(1).getElements().toArray());
		// noh a direita do noh a direita da raiz
		assertArrayEquals(new Integer[] { 146 },
				bt.getRoot().getChildren().get(1).getChildren().get(2).getElements().toArray());
		assertArrayEquals(new Integer[] { 145 },
				bt.getRoot().getChildren().get(1).getChildren().get(2).getChildren().get(0).getElements().toArray());
		assertArrayEquals(new Integer[] { 147 },
				bt.getRoot().getChildren().get(1).getChildren().get(2).getChildren().get(1).getElements().toArray());

		bt.insert(148);
		assertArrayEquals(new Integer[] { 147, 148 },
				bt.getRoot().getChildren().get(1).getChildren().get(2).getChildren().get(1).getElements().toArray());

		assertEquals(bt.size(), 25);
		assertEquals(bt.height(), 3);

	}

}
