import java.awt.Graphics;
import java.awt.Color;

public abstract class AbstractMazeSearch{

    private Maze maze;
    private Cell start;
    public Cell target;
    private Cell cur;
    public MazeSearchDisplay mazeSearchDisplay;

    public AbstractMazeSearch(Maze maze){
        this.maze = maze;
        cur = null;
        start = null;
        target = null;
    }

    public abstract Cell findNextCell();

    public abstract void addCell(Cell next);


    public abstract int numRemainingCells();


    public Maze getMaze(){
        return maze;
    }

    public void setTarget(Cell target){
        this.target = target;
    }

    public Cell getTarget(){
        return target;
    }

    public void setCur(Cell cell){
        this.cur = cell;
    }

    public Cell getCur(){
        return cur;
    }

    public void setStart(Cell start){
        this.start = start;
        start.setPrev(start);
    }

    public Cell getStart(){
        return start;
    }

    public void reset(){
        start = null;
        target = null;
    }

    public LinkedList<Cell> traceback(Cell cell){
        Cell curCell = cell;
        LinkedList<Cell> path = new LinkedList<>();
    
        while (curCell != null){
            path.addFirst(cell);
            if (curCell.equals(start)){
                return path; // we've completed the path from the start to the specified cell
            }
            curCell = curCell.getPrev();
        } return null; // we weren't able to find a path, so we return null
    }

    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay){
        setStart(start);
        setTarget(target);
        setCur(start);
    
        //add the starting cell to the set of Cells to explore
        addCell(start);
        if (display == true){
            mazeSearchDisplay = new MazeSearchDisplay(this, 30);
        }

      
        while (numRemainingCells() != 0){
            if(display == true){
                try{
                Thread.sleep(delay);
                mazeSearchDisplay.repaint();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }                
            }
            //set Cur to be the next Cell from the Cells to explore (findNextCell())
            setCur(findNextCell());
    
            for (Cell neighbor : maze.getNeighbors(cur)) {
                if (neighbor.getPrev() == null){
                    //set this neighbor's prev to be cur
                    // setCur(neighbor.getPrev());
                    neighbor.setPrev(this.getCur());
                    //add this neighbor to the future Cells to explore
                    addCell(neighbor);
                    if (neighbor.getRow() == target.getRow() && neighbor.getCol() == target.getCol()){
                        target.setPrev(cur);
                        return traceback(target); // we found the target, we're done
                    }
                }
            }
        }
    
        return null; // we couldn't find the target, but we're done
    }


    public void draw(Graphics g, int scale) {
        // Draws the base version of the maze
        getMaze().draw(g, scale);
        // Draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        // Draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        // Draws the target cell
        getTarget().draw(g, scale, Color.RED);
        // Draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);
    
        // If the target has been found, draws the path taken by the searcher to reach
        // the target sans backtracking.
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }


    public static void main(String[] args){
        Maze maze = new Maze(20, 20, 0.1);

        MazeDepthFirstSearch mazeDFS = new MazeDepthFirstSearch(maze);
        MazeBreadthFirstSearch mazeBFS = new MazeBreadthFirstSearch(maze);
        MazeAStarSearch mazeAStar = new MazeAStarSearch(maze);
        // MazeSearchDisplay md = new MazeSearchDisplay(BFSmaze,1);

       

        // Random rand = new Random();
        // int randRowStart = rand.nextInt(0,20);
        // int randColStart = rand.nextInt(0,20);

        // while (maze.landscape[randRowStart][randColStart].getType() != CellType.FREE){
        //     // rand = new Random();
        //     randRowStart = rand.nextInt(0,20);
        //     randColStart = rand.nextInt(0,20);
        // }

        // int randRowTarget = rand.nextInt(0,20);
        // int randColTarget = rand.nextInt(0,20);

        // while (maze.landscape[randRowTarget][randColTarget].getType() != CellType.FREE){
        //     // rand = new Random();
        //     randRowTarget = rand.nextInt(0,20);
        //     randColTarget = rand.nextInt(0,20);
        // }

        Cell target = new Cell(0, 0, CellType.FREE);
        Cell start = new Cell(19, 19, CellType.FREE);

        //mazeBFS.search(start,target, true, 10);
       // mazeAStar.search(start,target, true, 10);
        mazeDFS.search(start,target, true, 10);
        
    }



}