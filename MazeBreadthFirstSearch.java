public class MazeBreadthFirstSearch extends  AbstractMazeSearch{

    private Queue<Cell> queue;

    public MazeBreadthFirstSearch(Maze maze){
        super(maze);

        this.queue = new LinkedList<>();        
    }

    

    @Override
    public Cell findNextCell() {
     return queue.poll();
    }

    @Override
    public void addCell(Cell next) {
        queue.offer(next);
      
    }

    @Override
    public int numRemainingCells() {
        return queue.size();
   
    }
}