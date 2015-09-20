
public class DepthLimitedMinMaxPlayer implements Player {


	public int firstPlayer;
	public int secondPlayer;
	private SimpleBoard board;
	public int outcol;
	public int maxDepth = 3;
	boolean win;
	boolean lose;
	
	
	
	
    @Override
    public void setMove(int col) {
    }

    @Override
    public int getType() {
        return 2;
    }

    @Override
    public void go(SimpleBoard b) {
    	int m1 = 0;
        // Write Your Code Here ...  
        // Set m to column number you want to put your coins
        firstPlayer = b.next_player;
        secondPlayer= 3 - b.next_player;
        b.setFirstPlayer(firstPlayer);
        b.setSecondPlayer(secondPlayer);
//        winner2 = 0;
        board = b;
        m1 = alphaBeta(firstPlayer);
        
        // Do not modify this line !
        b.Move(m1);
    }
    private int evaluateOneMove(int depth, int maxDepth, int col) {
        int max = Integer.MIN_VALUE, score = 0;
        if (col != -1) {
            score = board.getHeuristicScore(secondPlayer, col, depth , maxDepth);
            if (board.getWinner2() == secondPlayer) {
                lose = true;	//redWinFound = true;
                return score;
            }
        }
        if (depth == maxDepth) {
            return score;
        }
        for (int c = 0; c < 7; c++) {
            if (board.isAvailable(c)) {
                board.set(c, firstPlayer);
                int value = evaluateTwoMove(depth + 1, maxDepth, c);
                board.unset(c);
                if (value > max) {
                    max = value;
                    if (depth == 0) {
                        outcol = c;
                    }
                }
                
            }
        }
        if (max == Integer.MIN_VALUE) {
            return 0;
        }
        return max;
    }
    
    
    
    private int evaluateTwoMove(int depth, int maxDepth, int col) { 
        int min = Integer.MAX_VALUE, score = 0;
        if (col != -1) {
            score = board.getHeuristicScore(firstPlayer, col, depth , maxDepth);
            if (board.getWinner2() == firstPlayer) { //board.blackWinFound()
                win = true;	//blackWinFound = true;
                return score;
            }
        }
        if (depth == maxDepth) {
            return score;
        }
        for (int c = 0; c < 7; c++) {
            if (board.isAvailable(c)) {
                board.set(c, secondPlayer);
                int value = evaluateOneMove(depth + 1, maxDepth, c);
                board.unset(c);
                if (value < min) {
                    min = value;
                    if (depth == 0) {
                        outcol = c;
                    }
                }
                
            }
        }
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }
    
    
    
    
    public int alphaBeta(int player) {
//        winner2 = 0;
		win = lose = false;
        
            evaluateOneMove(0, 1, -1);
            if (win) {
//            	System.out.println("bord ");
                return outcol;
            }
            win = lose = false;
            evaluateTwoMove(0, 1, -1);
            if (lose) {
//            	System.out.println("bakht");
                return outcol;
            }
            evaluateOneMove(0, maxDepth, -1);
        
        return outcol;
    }

    

	

}
