
public class RandomPlayer implements Player {

    public RandomPlayer() {
        System.out.println("RandomPlayer initialized.");
    }

    public void setMove(int col) {
    }

    public int getType() {
        return 1;
    }

    public void go(SimpleBoard b) {
        int m = (int) (Math.random() * 7);

        while (b.cols[m] == 6) {
            m = (int) (Math.random() * 7);
        }

        b.Move(m);
    }
}
