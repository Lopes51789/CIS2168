import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MazeGridPanel extends JPanel {
	private int rows;
	private int cols;
	private Cell[][] maze;

	// Extra credit
	public void generateMazeByDFS() {
		boolean[][] visited;
		Stack<Cell> stack = new Stack<Cell>();
		Cell start = maze[0][0];
		stack.push(start);
	}

	public void solveMaze() {
		Stack<Cell> stack = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows - 1][cols - 1];
		finish.setBackground(Color.RED);
		stack.push(start);

		// Implement your algorithm here
		int i = 0, j = 0, finX= rows-1, finY= cols-1;
		Cell temp = new Cell(i,j);
		//boolean[][] visited = new boolean[rows-1][cols-1];

		while(!stack.empty()) {
			temp = stack.peek();
			int d = 0;
			i = temp.getX();
			j = temp.getY();

			d = d + 1;
			stack.pop();
			stack.push(temp);

			if (i == finX && j == finY) {
				break;
			}
			if (d == 0) {
				if (i - 1 >= 0 && visited(i - 1, j))//lack 1 condition
				{
					Cell temp1 = new Cell(i - 1, j);
					visited(i-1, j) = false;
					stack.push(temp1);
				}
			}
			if (d == 1) {
				if (i - 1 >= 0 && visited[i - 1][j])//lack 1 condition
				{
					Cell temp1 = new Cell(i, j - 1);
					visited[i][j - 1] = false;
					stack.push(temp1);
				}
			}
			if (d == 2) {
				if (i - 1 >= 0 && visited[i - 1][j])//lack 1 condition
				{
					Cell temp1 = new Cell(i + 1, j);
					visited[i + 1][j] = false;
					stack.push(temp1);
				}
			}
			if (d == 3) {
				if (i - 1 >= 0 && visited[i - 1][j])//lack 1 condition
				{
					Cell temp1 = new Cell(i, j + 1);
					visited[i][j + 1] = false;
					stack.push(temp1);
				}
			} else {
				visited[temp.getX()][temp.getY] = true;
				stack.pop();
			}
		}
	}

	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if (status.equals(Color.WHITE) || status.equals(Color.RED)) {
			return false;
		}

		return true;
	}

	public void generateMaze() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {

				if (row == 0 && col == 0) {
					continue;
				} else if (row == 0) {
					maze[row][col].westWall = false;
					maze[row][col - 1].eastWall = false;
				} else if (col == 0) {
					maze[row][col].northWall = false;
					maze[row - 1][col].southWall = false;
				} else {
					boolean north = Math.random() < 0.5;
					if (north) {
						maze[row][col].northWall = false;
						maze[row - 1][col].southWall = false;
					} else {
						maze[row][col].westWall = false;
						maze[row][col - 1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}

		this.repaint();
	}

	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800, 800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows, cols));
		this.maze = new Cell[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				maze[row][col] = new Cell(row, col);
				this.add(maze[row][col]);
			}
		}

		this.generateMaze();
		this.solveMaze();
	}
}
