public class Main {
  public static void main(String[] args) {
    int[] a = new int[]{2, 7, 11, 15};
    System.out.println(pairSum(a, 12));
  }

  // efficient O(n)
  public static boolean pairSum(int[] array, int target){
    int left = 0;
    int right = array.length-1;
    while (left < right){
      int sum = array[left]+array[right];
      if(sum == target){
        return true;
      }else if(sum < target){
        left++;
      }else{
        right--;
      }
    }
    return false;
  }
}