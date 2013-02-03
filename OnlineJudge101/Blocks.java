import java.util.Scanner;
import java.util.*;

class Blocks {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    int[] block = new int[N];    // position of block i
    Map<Integer, List<Integer>> position = new HashMap<Integer, List<Integer>>(50);    // blocks at position i
    for (int i=0; i<N; i++) {
      block[i] = i;
      position.put(i, new ArrayList<Integer>(N));
      position.get(i).add(i);
    }

    while (true) {
      String s1 = scanner.next();
      if (s1.equals("quit")) break;
      int i1 = scanner.nextInt();
      String s2 = scanner.next();
      int i2 = scanner.nextInt();
      if ( (i1==i2) || (block[i1]==block[i2]) ) continue;

      if (s1.equals("move")) {
        int p = position.get(block[i1]).size() - 1;
        int top = position.get(block[i1]).get(p);
        while (top != i1) {
          position.get(top).add(position.get(block[i1]).remove(p));
          block[top] = top; 
          p--;
          top = position.get(block[i1]).get(p);
        }
      }

      if (s2.equals("onto")) {
        int p = position.get(block[i2]).size() - 1;
        int top = position.get(block[i2]).get(p);
        while (top != i2) {
          position.get(top).add(position.get(block[i2]).remove(p));
          block[top] = top; 
          p--;
          top = position.get(block[i2]).get(p);
        }
      }

      int b1 = block[i1];
      int p = position.get(b1).indexOf(i1);
      while (p<position.get(b1).size()) {
        int e = position.get(b1).remove(p);
        position.get(block[i2]).add(e);
        block[e] = block[i2];
      }
    }
			
    StringBuilder str = new StringBuilder();
    for (int i=0; i<N; i++) {
      str.append(i + ":");
      for (Integer b: position.get(i))
        str.append(" " + b);
        str.append("\n");
      }
    System.out.print(str.toString());
    scanner.close();
  }
	
}
