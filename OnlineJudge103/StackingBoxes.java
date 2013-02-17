import java.util.*;

class StackingBoxes {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLine()) {
      int N = 0;
      try {
        N = Integer.parseInt(scanner.nextLine().split("\\s+")[0]);
      } catch (Exception e) {
        System.exit(1);
      }
      List<Box> boxes = new ArrayList<Box>(N);
      for (int i=0; i<N; i++) {
        String line = scanner.nextLine();
        Box box = new Box(i+1, line);
        boxes.add(box);
      }
      Collections.sort(boxes);
      //for (Box box: boxes) box.print();

      List<Integer> result = getLongestSeq(boxes, N);

      System.out.println(result.size());
      for (int i=result.size()-1; i>0; i--)
        System.out.print(boxes.get(result.get(i)).id + " ");
      System.out.println(boxes.get(result.get(0)).id);
    }

    scanner.close();
  }

  private static List<Integer> getLongestSeq(List<Box> boxes, int N) {
    List<Integer> result = new ArrayList<Integer>(N);
    List<Integer> len = new ArrayList<Integer>(N);
    List<Integer> pred = new ArrayList<Integer>(N);
    len.add(1);
    pred.add(-1);

    for (int i=1; i<N; i++) {
      int max = 0;
      int maxp = -1;
      for (int j=0; j<i; j++)
        if (boxes.get(j).nests(boxes.get(i)))
          if (len.get(j)>max) {
            max = len.get(j);
            maxp = j;
          }
      len.add(max+1);
      pred.add(maxp);
    }

    result.add(N-1);
    int p = pred.get(N-1);
    while (p!=-1) {
      result.add(p);
      p = pred.get(p);
    }
    return result;
  }


  private static class Box implements Comparable<Box>{
    int id;
    List<Integer> dim;

    Box(int id, String line) {
      this.id = id;
      String[] tokens = line.split("\\s+");
      dim = new ArrayList<Integer>(tokens.length);
      for (String s: tokens)
        dim.add(Integer.parseInt(s));
      Collections.sort(dim);
    }

    boolean nests(Box b) {
      for (int i=0; i<dim.size(); i++)
        if (dim.get(i) >= b.dim.get(i)) return false;
       return true;
    }

    @Override
    public int compareTo(Box b) {
      for (int i=0; i<dim.size(); i++)
        if (dim.get(i)<b.dim.get(i)) return -1;
        else if (dim.get(i)>b.dim.get(i)) return 1;
      return 0;
    }

    void print() {  // for testing
      System.out.print(id + ":");
      for (int d: dim) System.out.print(" " + d);
      System.out.println();
    }
  }

}
