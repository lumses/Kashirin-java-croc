package ru.croccode.hypernull.bot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

import ru.croccode.hypernull.domain.MatchMode;
import ru.croccode.hypernull.geometry.Offset;
import ru.croccode.hypernull.geometry.Point;
import ru.croccode.hypernull.geometry.Size;
import ru.croccode.hypernull.io.SocketSession;
import ru.croccode.hypernull.message.Hello;
import ru.croccode.hypernull.message.MatchOver;
import ru.croccode.hypernull.message.MatchStarted;
import ru.croccode.hypernull.message.Move;
import ru.croccode.hypernull.message.Register;
import ru.croccode.hypernull.message.Update;

public class StarterBot implements Bot {

	private static final Random rnd = new Random(System.currentTimeMillis());

	private final MatchMode mode;

	private Offset moveOffset;

	private int moveCounter = 0;

	private int id,x,y;

	private Size map_size;

	private int[][] map;

	private Point coord_of_bot;

	public StarterBot(MatchMode mode) {
		this.mode = mode;
	}

	@Override
	public Register onHello(Hello hello) {
		Register register = new Register();
		register.setMode(mode);
		register.setBotName("lumses_stupid_bot");
		return register;
	}

	// поиск в ширину
	private class Section {
		int x, y, distance;    // расстояние
		Section previous;  // родительская ячейка

		Section(int x, int y, int distance, Section previous) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.previous = previous;
		}

		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}

	// BFS
	public LinkedList<Section> shortWay(int[][] matrix, Point start, Point end) {

		/* написал просто это условие для безопасности, для случая
		если все свободные клетки будут заспавнены монетами(не проверял его)
		если вдруг случится случай, что монета заспавнится на препятствие
		или бот заспавнится */
		if (matrix[start.x()][start.y()] == 0 || matrix[end.x()][end.y()] == 0) {
			return null;
		}

		Section[][] sections = new Section[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != 0) {
					sections[i][j] = new Section(i, j, Integer.MAX_VALUE, null);
				}
			}
		}

		LinkedList<Section> queue = new LinkedList<>();
		Section src = sections[start.x()][start.y()];
		src.distance = 0;
		queue.add(src);
		Section destination = null;
		Section temp;
		while ((temp = queue.poll()) != null) {
			// если нашли монету
			if (temp.x == end.x() && temp.y == end.y()) {
				destination = temp;
				break;
			}
			// вверх
			visit(sections, queue, temp.x - 1, temp.y, temp);
			// вниз
			visit(sections, queue, temp.x + 1, temp.y, temp);
			// влево
			visit(sections, queue, temp.x, temp.y - 1, temp);
			// вправо
			visit(sections, queue, temp.x, temp.y + 1, temp);
		}

		// если есть путь -- строим его
		if (destination == null) {
			return null;
		} else {
			LinkedList<Section> way = new LinkedList<>();
			temp = destination;
			do {
				way.addFirst(temp);
			} while ((temp = temp.previous) != null);
			return way;
		}
	}

	// обновление, смотрели ли мы клетку или нет
	private void visit(Section[][] sections, LinkedList<Section> queue, int x, int y, Section parent) {
		if (x < 0 || x >= sections.length || y < 0 || y >= sections[0].length || sections[x][y] == null) {
			return;
		}
		// обновляем дистанцию и предыдущий член
		int dist = parent.distance + 1;
		Section temp = sections[x][y];
		if (dist < temp.distance) {
			temp.distance = dist;
			temp.previous = parent;
			queue.add(temp);
		}
	}

	@Override
	public void onMatchStarted(MatchStarted matchStarted) {
		map_size = matchStarted.getMapSize();
		id = matchStarted.getYourId();
		x = matchStarted.getMapSize().width();
		y = matchStarted.getMapSize().height();
		map = new int[x][y];
	}

	@Override
	public Move onUpdate(Update update) throws IndexOutOfBoundsException{
		coord_of_bot = update.getBots().get(id);

		// заполняю карту единицами(далее блоки будут заполняться 0)
		// 1 будет означать, что на эту координату можно переместиться
		for (int i =0 ;i< x;i++){
			for (int j = 0;j<y;j++){
				map[i][j] = 1;
			}
		}

		/* Добавляю в ArrayList координаты вокруг бота(8шт)
		Делаю это для того, чтобы бот рандомно ходил на свободные клетки
		пока в его поле зрения нет монет
		для того, чтобы не было простоя)) */
		ArrayList<Point> a = new ArrayList<>();
		Point b1 = new Point(coord_of_bot.x()-1,coord_of_bot.y());
		Point b2 = new Point(coord_of_bot.x()-1,coord_of_bot.y()-1);
		Point b3 = new Point(coord_of_bot.x()-1,coord_of_bot.y()+1);
		Point b4 = new Point(coord_of_bot.x(),coord_of_bot.y()+1);
		//Point b5 = new Point(coord_of_bot.x(),coord_of_bot.y());
		Point b6 = new Point(coord_of_bot.x(),coord_of_bot.y()+-1);
		Point b7 = new Point(coord_of_bot.x()+1,coord_of_bot.y()+1);
		Point b8 = new Point(coord_of_bot.x()+1,coord_of_bot.y());
		Point b9 = new Point(coord_of_bot.x()+1,coord_of_bot.y()-1);
		a.add(b1);
		a.add(b2);
		a.add(b3);
		a.add(b4);
		//a.add(b5);
		a.add(b6);
		a.add(b7);
		a.add(b8);
		a.add(b9);
		/*	System.out.println("До:");
		for (int i =0 ; i < a.size();i++) {
			System.out.print(a.get(i)+" ");
		}
		System.out.println(); */


		/* заполняю карту препятствиями
		в ArrayList a оставляю только те координаты, которые свободны */
		Set<Point> blocks = update.getBlocks();
		for (Point block: blocks) {
			map[block.x()][block.y()] = 0;
			ArrayList c = new ArrayList<>();
			for (int i =0; i< a.size();i++) {
				if (a.get(i).x() == block.x() && a.get(i).y() == block.y()) {
					c.add(i);
				}
			}
			if (a.size()!=0 && c.size()!=0) a.remove(c.get(0));
		/*	System.out.println("После:");
			for (int i =0 ; i < a.size();i++) {
				System.out.print(a.get(i)+" ");
			}
			System.out.println(); */
		}

		for (int i =0 ;i< x;i++){
			for (int j = 0;j<y;j++){
				map[i][j] = 1;
			}
		}

		for (Point block: blocks) {
			map[block.x()][block.y()] = 0;
		}

		/* Чтобы добраться до монеты,
		прохожусь по всем монетам
		у каждой монеты ищу кратчайший путь для этой монеты
		и из этих кратчайших путей выбираю наикратчайший */
		LinkedList<Section> MinList = new LinkedList<>();
		int flag = 0;
		Point minCoin = new Point(0,0);
		Set<Point> coins = update.getCoins();
		if(coins != null) {
			for (Point coin : coins) {
				int[] start = {coord_of_bot.x(), coord_of_bot.y()};
				int[] end = {coin.x(), coin.y()};
				//System.out.println("Координаты монеты " + coin);
				//System.out.println("Координаты бота " + coord_of_bot);
				LinkedList<Section> path = shortWay(map, coord_of_bot, coin);
				if (path.size() < MinList.size() || flag == 0) {
					MinList.removeAll(MinList);
					MinList.addAll(path);
					flag = 1;
					minCoin = coin;
				}
			}
			//System.out.println("МПУТЬ"+MinList);
			//	System.out.println("ПУТЬ "+path);
			//	System.out.println(path.get(1).x+" "+path.get(1).y);
			/* BFS реализован так, что бот не может двигаться по диагонали(это + лишние шаги)
			Чтобы избавиться от этого я ищу куда можно сместиться по диагонали
			чтобы это было выгодно. Делаю проверку на наличие препятствия.
			BFS в основном юзается, когда бот у препятствия, чтобы его обойти за наименьшее число шагов
			 */
			//System.out.println("Cмещение: "+(MinList.get(1).x-coord_of_bot.x())+" "+(MinList.get(1).y-coord_of_bot.y()));
			int x_d,y_d;
			if (minCoin.x()-coord_of_bot.x()>0) {
				x_d = 1;
			} else if (minCoin.x()-coord_of_bot.x()<0) {
				x_d = -1;
			} else {
				x_d = 0;
			}
			if (minCoin.y()-coord_of_bot.y()>0) {
				y_d = 1;
			} else if (minCoin.y()-coord_of_bot.y()<0) {
				y_d = -1;
			} else {
				y_d = 0;
			}
			/* Приоритет
			1. Идти по диагонали к монете
			2. Идти вверх/вниз/вправо/влево к монете, обходя препятствие
			3. Идти рандомно, если нет монет :(
			*/
			if (map[coord_of_bot.x()+x_d][coord_of_bot.y()+y_d]==1) {
				moveOffset = new Offset(minCoin.x()-coord_of_bot.x(),minCoin.y()-coord_of_bot.y());
			} else{
				moveOffset = new Offset(MinList.get(1).x-coord_of_bot.x(), MinList.get(1).y-coord_of_bot.y());
			}
		} else moveOffset = new Offset(a.get(rnd.nextInt(a.size()-1)).x()- coord_of_bot.x(),a.get(rnd.nextInt(a.size()-1)).y()- coord_of_bot.y());
		moveCounter++;
		Move move = new Move();
		move.setOffset(moveOffset);
		return move;
		/* Недостатки: из-за BFS (строится путь в массиве учитывая границ, скорее всего не получится у границы карты
		телепортнутся в противоположный конец); нет DeatMatch; */
	}

	@Override
	public void onMatchOver(MatchOver matchOver) {
	}

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket();
		socket.setTcpNoDelay(true);
		socket.setSoTimeout(300_000);
		socket.connect(new InetSocketAddress("localhost", 2021));

		SocketSession session = new SocketSession(socket);
		StarterBot bot = new StarterBot(MatchMode.FRIENDLY);
		new BotMatchRunner(bot, session).run();
	}
}