import java.util.Comparator;

public class MazeAStarSearch extends AbstractMazeSearch {

    private PriorityQueue<Cell> priorityQueue;

    public MazeAStarSearch(Maze maze){
        super(maze);
        Comparator<Cell> comparator = new Comparator<>(){

            @Override
            public int compare(Cell cell1, Cell cell2) {
                
                // cell 1
                int numStepsCell1 = traceback(cell1).size();
                int estimatedDistance1 = Math.abs(target.getRow() - cell1.getRow()) + Math.abs(target.getCol() - cell1.getCol());

                int sumSteps1 = numStepsCell1 + estimatedDistance1;

                // cell 2
                int numStepsCell2 = traceback(cell2).size();
                int estimatedDistance2 = Math.abs(target.getRow() - cell2.getRow()) + Math.abs(target.getCol() - cell2.getCol());

                int sumSteps2 = numStepsCell2 + estimatedDistance2;

                if (sumSteps1 == sumSteps2){
                    return 0;
                } else if (sumSteps1 < sumSteps2){
                    return -1;
                } else {
                    return 1;
                }
            }
            
        };
        this.priorityQueue = new Heap<Cell>(comparator, false);
    }


    public void addCell(Cell next){
        priorityQueue.offer(next);
    }

    public Cell findNextCell(){
        return priorityQueue.poll();
    }

    public int numRemainingCells(){
        return priorityQueue.size();
    }

}
