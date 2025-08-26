/**
 * LC 33: Search in Rotated Sorted Array
 * -------------------------------------
 * There is an integer array nums sorted in ascending order (with distinct values).
 * 
 * Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) 
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 * 
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 * -------------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * Example 1:
 * ----------
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * -------------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * Example 2:
 * ----------
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * -------------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * Example 3:
 * ----------
 * Input: nums = [1], target = 0
 * Output: -1
 * --------------------------------------------------------------------------------------------------------------------------------------------------

 * Constraints:
 * -------------
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 * --------------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * Probable solution
 * ------------------
 * Detemine the mid point of the given array
 * Check whether the target bounds to be in the left side of the mid point or the right
 * To determine this, 1st we need to check whether the left or right side of the mid index is sorted as can't compare the target 
 * with both ends of the non sorted side to the mid index
 * 
 * If the left is sorted and 1st element <= target <= mid element, the targert is in the left part and we can safely discard 
 * the right from the middle element, else the target should be in the right side and the left can be safelt discarded
 * 
 * If the right side is sorted and mid element <= target <= last element, the targert is in the right part and we can safely discard 
 * the left from the middle element, else the target should be in the left side and the right can be safelt discarded
 * 
 * Continue this till we find the target position or determine that the target doesn't belong to the given array
*/
public class RotatedSortedArray {
    /**
     * This method searches and finds the position of a given element into the give sorted array which is possibly rotated.
     * @param nums The input array which has sorted integer values but possibly rotated from an unknown index.
     * @param target The element we need to search for in the given array.
     * @return The index of target in nums if it's present, -1 otherwise.
     */
    public static int search(int[] nums, int target) {
        int numsStartIndex = 0, numsEndIndex = nums.length -1;
        return customBinarySearch(nums, target, numsStartIndex, numsEndIndex);
    }

    /**
     * This method do a custom binary search on the given array between the provided indeices and finds the position of target.
     * @param nums The input array which has sorted integer values but possibly rotated from an unknown index.
     * @param target The element we need to search for in the given array.
     * @param numsStartIndex Starting position of the array from where we need to run the search.
     * @param numsEndIndex Ending position of the array till where we need to run the search.
     * @return The index of target in nums if it's present, -1 otherwise.
     */
    private static int customBinarySearch(int[] nums, int target, int numsStartIndex, int numsEndIndex) {   
        if(numsStartIndex < numsEndIndex) {
            int numsMidIndex = (numsStartIndex + numsEndIndex) / 2;

            // System.out.println("Array Starts at: " + numsStartIndex + " and Array mid is: " + numsMidIndex + " and Array ends at: " + numsEndIndex + "\n");
            // System.out.println("Target element is: " + target + "\n");

            if(nums[0] == target) { // target found at the beginning
                return 0;
            } else if(nums[numsMidIndex] == target) { // target found at the middle
                return numsMidIndex;
            } else if(nums[nums.length - 1] == target) { // target found at the end
                return nums.length - 1;
            } else if(numsStartIndex == numsMidIndex || numsMidIndex == numsEndIndex) { // we are at the middle of the array and the target not found
                return -1;
            } else if (nums[0] < nums[numsMidIndex]) { // Left of the mid point is sorted, the rotation is in the right side
                // System.out.println("In Left Sorted\n");
                if (nums[0] < target && target < nums[numsMidIndex]) { // target is in the sorted side
                    // System.out.println("In Left\n");
                    return customBinarySearch(nums, target, numsStartIndex, numsMidIndex);
                } else {  // target should be in the other side
                    // System.out.println("In Right\n");
                    return customBinarySearch(nums, target, numsMidIndex, numsEndIndex);
                }
            }  else if (nums[numsMidIndex] < nums[numsEndIndex]) { // Right of the mid point is sorted, the rotation is in the left side
                // System.out.println("In Right Sorted\n");
                if (nums[numsMidIndex] < target && target < nums[numsEndIndex]) { // target should be in the sorted side
                    // System.out.println("In Right\n");
                    return customBinarySearch(nums, target, numsMidIndex, numsEndIndex);
                } else {  // target should be in the other side
                    // System.out.println("In Left\n");                    
                    return customBinarySearch(nums, target, numsStartIndex, numsMidIndex);
                }
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,8,9,10,0,1,2,3};
        int target = 9;
        int targetIndex = search(nums, target);
        System.out.println("The index of the given target: " + target + " in the given array is: " + targetIndex);
    }
}