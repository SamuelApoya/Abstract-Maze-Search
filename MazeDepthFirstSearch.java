public class MazeDepthFirstSearch extends AbstractMazeSearch{

    //Instance fields
    private Stack<Cell> stack;

    public MazeDepthFirstSearch(Maze maze){
        super(maze);

        this.stack = new LinkedList<>();


        
    }

    @Override
    public Cell findNextCell() {
        return stack.pop();
        
    }

    @Override
    public void addCell(Cell next) {
        stack.push(next);
        
    }

    @Override
    public int numRemainingCells() {
        return stack.size();
       
    }


}