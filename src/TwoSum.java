import java.util.HashSet;

public class TWoSum {
  public static void main(String[] args) {
    int[] a = new int[]{2, 7, 11, 15};
    System.out.println(twoSum1(a, 9));
  }

  // brute-force O(n^2)
  public static boolean twoSum(int[] array, int target){
    for(int i=0; i<array.length; i++){
      for(int j=i+1; j<array.length; j++){
        if(array[i]+array[j] == target){
          return true;
        }
      }
    }
    return false;
  }

  // efficient O(n)
  public static boolean twoSum1(int[] array, int target){
    HashSet<Integer> seen = new HashSet<>();

    for(int n : array){
      if(seen.contains(target-n)){
        return true;
      }else{
        seen.add(n);
      }
    }
    return false;
  }
}