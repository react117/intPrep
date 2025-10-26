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
 * Determine the mid point of the given array, check whether the target bounds to be in the left side of the mid point or the right
 * 
 * To determine this, 1st we need to check whether the left or right side of the mid index is sorted as can't compare the target 
 * with both ends of the non sorted side to the mid index
 * 
 * If the left is sorted and 1st element <= target <= mid element, the target is in the left part and we can safely discard 
 * the right from the middle element, else the target should be in the right side and the left can be safely discarded
 * 
 * If the right side is sorted and mid element <= target <= last element, the target is in the right part and we can safely discard 
 * the left from the middle element, else the target should be in the left side and the right can be safely discarded
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
        int numsLeftPtr = 0, numsRightPtr = nums.length -1;

        while(numsLeftPtr <= numsRightPtr) {
            int numsMidPtr = (numsLeftPtr + numsRightPtr) / 2;
            
            // System.out.println("numsLeftPtr= " + numsLeftPtr + " and numsMidPtr= " + numsMidPtr + " and numsRightPtr= " + numsRightPtr + "\n");

            // If the target exists in the left, right or middle position of the current search area of the array, 
            // we should return it immediately
            if(nums[numsLeftPtr] == target) {
                return numsLeftPtr;
            } else if(nums[numsRightPtr] == target) {
                return numsRightPtr;
            } else if(nums[numsMidPtr] == target) {
                return numsMidPtr;
            }

            if (nums[numsLeftPtr] <= nums[numsMidPtr]) { // Left side of the mid pointer is sorted, the rotation is in the right side
                if (nums[numsLeftPtr] < target && target < nums[numsMidPtr]) { // target is in the sorted side
                    numsRightPtr = numsMidPtr - 1;
                } else {  // target should be in the other side
                    numsLeftPtr = numsMidPtr + 1;
                }
            } else if (nums[numsMidPtr] <= nums[numsRightPtr]) { // Right of the mid pointer is sorted, the rotation is in the left side
                if (nums[numsMidPtr] < target && target < nums[numsRightPtr]) { // target is in the sorted side
                    numsLeftPtr = numsMidPtr + 1;
                } else {  // target should be in the other side
                    numsRightPtr = numsMidPtr - 1;
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