package codeart.cs667.treesearch.impl;

import codeart.cs667.treesearch.SearchNode;
import codeart.cs667.treesearch.SearchProblem;

import java.util.ArrayList;
import java.util.List;

public class RoombaDomain implements SearchProblem {
    public static int SIZE = 11;

    public RoombaDomain() {
        num_rows = SIZE;
        num_cols = SIZE;
        this._grid = this.initGrid();

        this._roombaLoc = new Location(0, 0);
    }

    private RoombaGrid initGrid() {
        RoombaGrid grid = new RoombaGrid();

        grid.chargers.add(new Location(0, 0));
        grid.chargers.add(new Location(0, num_cols - 1));
        grid.chargers.add(new Location(num_rows - 1, num_cols / 2));

        for (int j = 1; j < num_cols - 1; j++) {
            grid.walls.add(new Location(num_rows / 2, j));
        }
        for (int i = 1; i < num_rows - 1; i++) {
            grid.walls.add(new Location(i, num_cols / 2));
        }

        return grid;
        /*
                grid = CLEAN*np.ones((num_rows, num_cols), dtype=int)
        grid[SIZE//2, 1:SIZE-1] = WALL
        grid[1:SIZE//2+1,SIZE//2] = WALL
        grid[0,0] = CHARGER
        grid[0,-1] = CHARGER
        grid[-1,SIZE//2] = CHARGER
         */
    }

    private int num_rows = SIZE;
    private int num_cols = SIZE;
    private int max_power = 2 * SIZE;
    private int power = max_power;
    private Location _roombaLoc;
    private RoombaGrid _grid;

    /*

    def __init__(self):

        # deterministic grid world
        num_rows, num_cols = SIZE, SIZE
        grid = CLEAN*np.ones((num_rows, num_cols), dtype=int)
        grid[SIZE//2, 1:SIZE-1] = WALL
        grid[1:SIZE//2+1,SIZE//2] = WALL
        grid[0,0] = CHARGER
        grid[0,-1] = CHARGER
        grid[-1,SIZE//2] = CHARGER
        max_power = 2*SIZE

        self.grid = grid
        self.max_power = max_power

    def pack(self, g, r, c, p):
        return (g.tobytes(), r, c, p)
    def unpack(self, state):
        grid, r, c, p = state
        grid = np.frombuffer(grid, dtype=int).reshape(self.grid.shape).copy()
        return grid, r, c, p

    def initial_state(self):
        positions = list(zip(*np.nonzero(self.grid == CHARGER)))
        # r, c = positions[np.random.randint(len(positions))]
        r, c = 0, 0

        positions = np.random.permutation(list(zip(*np.nonzero(self.grid == CLEAN))))
        grid = self.grid.copy()
        for dirty in range(5): grid[tuple(positions[dirty])] = DIRTY

        # grid = self.grid + (np.random.rand(*self.grid.shape) < .1)*(self.grid == 2)

        return self.pack(grid, r, c, self.max_power)

     */
    private String pack() {
        return String.format("ROOMBA:{0}\tGRID:{1}\tPOWER:{2}", this._roombaLoc, this._grid, this.power);
    }

    private void unpack(String state) {
        // String.format("{0}\t{1}\t{2}", this._roombaLoc, this._grid, this.power);
        String[] splited = state.split("\t");
        String strroomba = splited[0].split("ROOMBA:")[0];
        String strgrid = splited[0].split("GRID:")[0];
        String strpower = splited[0].split("POWER:")[0];

        this.power = Integer.parseInt(strpower);
        this._roombaLoc.fromString(strroomba);
        this._grid.fromString(strgrid);
    }

    @Override
    public SearchNode getRootNode() {
        return null;
    }

    @Override
    public double getHeuristic(String state) {
        return 0;
    }

    @Override
    public boolean isGoal(String state) {
        return false;
    }

    @Override
    public String[] getValidActions(String state) {
        return new String[0];
    }

    @Override
    public void performAction(String state, String action) {

    }

    class RoombaGrid {
        public RoombaGrid() {

        }

        public List<Location> walls = new ArrayList<>();
        public List<Location> chargers = new ArrayList<>();
        public List<Location> dirts = new ArrayList<>();

        public void fromString(String state) {

        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            for (Location lc : dirts) {
                builder.append(lc.toString());
                builder.append(',');
            }
            return String.format("dirts:{0}", builder.toString());
        }
    }

    class Location implements Comparable<Location> {
        public int x;
        public int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return String.format("({0},{1})", x, y);
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

        @Override
        public int compareTo(Location o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }

        public void fromString(String str) {
            String[] splited = str.split("[(,)]");
            this.x = Integer.parseInt(splited[0]);
            this.y = Integer.parseInt(splited[1]);
        }
    }
}
