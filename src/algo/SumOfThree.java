package algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SumOfThree {

    public static void main(String[] args) {
        SumOfThree sumOfThree = new SumOfThree();
        int[] nums = {-7,-5,5,-6,-2,1,7,3,-4,-2,-2,-4,-8,-1,8,8,-2,-7,3,2,-7,8,-3,-10,5,2,8,7,7};
        // -4, -1, -1, 0, 1, 2
        System.out.println(sumOfThree.threeSum(nums));
    }


    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        if(nums[0] > 0 || nums[nums.length - 1] <0) return result;

        int left = 0;
        int right = nums.length - 1;
        Set<String> set = new HashSet<>();
        scanSearch(nums, left, right, set, result);
        return result;
    }


    private void scanSearch(int[] nums, int left, int right, Set<String> set, List<List<Integer>> result){
        while(right - left >= 2) {
            int searchNum = 0 - (nums[left] + nums[right]);
            if(searchNum >= 0){
                int tempRight = right-1;
                int start = tempRight;
                while(tempRight > 0 && nums[tempRight] >= 0){
                    if(nums[tempRight] > searchNum){
                        tempRight--;
                    }else if(nums[tempRight] == searchNum){
                        addResult(nums, set, result, left, right, searchNum);
                        tempRight = start-1;
                        start=tempRight;
                        tempRight--;
                    }else{
                        break;
                    }
                }
                if(nums[right-1] >= (0 - (nums[left] + nums[right-1])) ) {
                    scanSearch(nums, left, right - 1, set, result);
                    break;
                }
                left++;
            }else if(searchNum < 0){
                int tempLeft = left+1;
                int start = tempLeft;
                while(tempLeft < nums.length && nums[tempLeft] <= 0){
                    if(nums[tempLeft] < searchNum){
                        tempLeft++;
                    }else if(nums[tempLeft] == searchNum){
                        addResult(nums, set, result, left, right, searchNum);
                        tempLeft = start+1;
                        start=tempLeft;
                        tempLeft++;
                    }else{
                        break;
                    }
                }
                if(nums[left+1] <= (0 - (nums[left+1] + nums[right])) ) {
                    scanSearch(nums, left + 1, right, set, result);
                    break;
                }
                right--;
            }
        }
    }

    public List<List<Integer>> threeSum_1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i + 2 < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
                continue;
            }
            int j = i + 1, k = nums.length - 1;
            int target = -nums[i];
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) j++;  // skip same result
                    while (j < k && nums[k] == nums[k + 1]) k--;  // skip same result
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }



    private void addResult(int[] nums, Set<String> set, List<List<Integer>> result, int left, int right, int searchNum) {
        String val = nums[left] + "" + searchNum + "" + nums[right];
        if(!set.contains(val)) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[left]);
            list.add(searchNum);
            list.add(nums[right]);
            result.add(list);
            set.add(val);
        }
    }
}
