import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by giniyatullina on 19.02.2016.
 */
public class Percolation {

  private WeightedQuickUnionUF percModel;
  private int N;
  private boolean[] isOpen;

  public Percolation(int N) {   // create N-by-N grid, with all sites blocked

    percModel = new WeightedQuickUnionUF(N*N+2);
    this.N=N;
    this.isOpen = new boolean[N*N];
  }
  public void open(int i, int j) throws IndexOutOfBoundsException {  // open site (row i, column j) if it is not open already
    if ((i < 1) || (j < 1) || (i > N ) || (j > N)) throw new IndexOutOfBoundsException("open : 1 - "  + String.valueOf(i) + ", " +  String.valueOf(j));

    int curInd = (i - 1)*N + j - 1;

    if (curInd < 0 || curInd >= N*N) throw new IndexOutOfBoundsException("open : 2 - "  + String.valueOf(i) + ", " +  String.valueOf(j));
    isOpen[curInd] = true;

    int upInd = (i - 2)*N + j - 1;
    int leftInd = (i - 1)*N + j - 2;
    int rightInd = (i - 1)*N + j;
    int bottomInd = i * N + j - 1;

    if (upInd >= 0 && upInd <= N*N-1 && isOpen [upInd]) {
      percModel.union(curInd, upInd);
    }

    if (leftInd >= 0 && leftInd <= N*N-1 && isOpen [leftInd] ) {
      percModel.union(curInd, leftInd);
    }

    if (rightInd >= 0 && rightInd <= N*N-1 && isOpen [rightInd] ) {
      percModel.union(curInd, rightInd);
    }

    if (bottomInd >= 0 && bottomInd <= N*N-1 && isOpen [bottomInd] ) {
      percModel.union(curInd, bottomInd);
    }

    if (i == 1) {
      percModel.union(curInd, N*N);
    }

    if (i == N) {
      percModel.union(curInd, N*N+1);
    }
  }

  public boolean isOpen(int i, int j) throws IndexOutOfBoundsException {   // is site (row i, column j) open?
    if ((i < 1) || (j < 1) || (i > N ) || (j > N)) throw new IndexOutOfBoundsException("isOpen :1 - " + String.valueOf(i) + ", " +  String.valueOf(j));

    int curInd = (i - 1)*N + j - 1;

    if (curInd < 0 || curInd >= N*N) throw new IndexOutOfBoundsException("is Open :2 - " + String.valueOf(i) + ", " +  String.valueOf(j));
    return isOpen[curInd];
  }

  public boolean isFull(int i, int j) throws IndexOutOfBoundsException {     // is site (row i, column j) full?
    if ((i < 1) || (j < 1) || (i > N ) || (j > N)) throw new IndexOutOfBoundsException("is Full : 1 - " + String.valueOf(i) + ", " +  String.valueOf(j));

    int curInd = (i - 1)*N + j - 1;

    if (curInd < 0 || curInd >= N*N) throw new IndexOutOfBoundsException("is Full : 2 - " + String.valueOf(i) + ", " +  String.valueOf(j));

    return percModel.connected(curInd, N*N);
  }

  public boolean percolates() throws IndexOutOfBoundsException {         // does the system percolate?
    return percModel.connected(N*N+1, N*N);
  }

  public void drawModel() {
    for (int i = 1; i<= N; i++) {
      for (int j = 1; j <= N; j++) {
        StdOut.print(isOpen(i,j) ? "+|" : "-|");
      }
      StdOut.print("\n");
    }
  }

  public int openSitesCount() {
    int count = 0;
    for (int i=0; i<N*N; i++)
      if (isOpen[i])
        count++;
    return count;
  }

  public static void main(String[] args) {
    int N =20;
    Percolation percolation = new Percolation(N);
    int i = 0;
    while (!percolation.percolates()) {
      percolation.open(StdRandom.uniform(1, N + 1), StdRandom.uniform(1, N + 1));
      i++;
    }
    percolation.drawModel();
    StdOut.println(i);
    StdOut.println(percolation.openSitesCount());
  }
}
