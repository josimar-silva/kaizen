/*
Description:

Consider the array [3,6,9,12]. If we generate all the combinations
with repetition that sum to 12, we get 5 combinations: [12], [6,6], [3,9], [3,3,6], [3,3,3,3].
The length of the sub-arrays (such as [3,3,3,3] should be less than or equal to the length
of the initial array ([3,6,9,12]).

Given an array of positive integers and a number n,
count all combinations with repetition of integers that sum to n.
For example: find([3,6,9,12],12) = 5.

Kata: https://www.codewars.com/kata/59f3178e3640cef6d90000d5
*/

pub fn find(arr: &[u32], target_number: u32) -> u32 {
    if arr.is_empty() {
        return 0;
    }

    let mut combinations_arr = vec![vec![0; (target_number + 1) as usize]; arr.len() + 1];

    combinations_arr[0][0] = 1;

    for num in arr {
        for length in 1..=arr.len() {
            for sum in *num..=target_number {
                if sum >= *num {
                    combinations_arr[length][sum as usize] +=
                        combinations_arr[length - 1][(sum - *num) as usize];
                }
            }
        }
    }

    let mut total_combinations = 0;
    for length in 1..=arr.len() {
        total_combinations += combinations_arr[length][target_number as usize];
    }

    total_combinations
}

#[cfg(test)]
mod tests {
    use super::find;

    #[test]
    fn test_basic_case() {
        assert_eq!(find(&[3, 6, 9, 12], 12), 5);
    }

    #[test]
    fn test_length_constraint() {
        // Combinations for 4 with [1, 2] are:
        // [2, 2] (len 2, valid as <= len of [1,2])
        // [1, 1, 2] (len 3, invalid as > len of [1,2])
        // [1, 1, 1, 1] (len 4, invalid as > len of [1,2])
        assert_eq!(find(&[1, 2], 4), 1);
    }

    #[test]
    fn test_kata_example_1() {
        // [3], [1,2], [1,1,1]
        assert_eq!(find(&[1, 2, 3], 3), 3);
    }

    #[test]
    fn test_kata_example_2() {
        // [1,4], [2,3], [1,1,3], [1,2,2], [1,1,1,2]
        // [1,1,1,1,1] is invalid because len 5 > 4
        assert_eq!(find(&[1, 2, 3, 4], 5), 5);
    }

    #[test]
    fn test_no_combinations_possible() {
        // Combination [1,1,1,1,1,1,1,1,1,1] has length 10, which is > 1
        assert_eq!(find(&[1], 10), 0);
    }

    #[test]
    fn test_single_element_match() {
        assert_eq!(find(&[10], 10), 1);
    }

    #[test]
    fn test_zero_target() {
        // Empty combination has sum 0 and length 0.
        assert_eq!(find(&[1, 2, 3], 0), 0);
    }

    #[test]
    fn test_empty_array() {
        assert_eq!(find(&[], 5), 0);
    }

    #[test]
    fn test_target_smaller_than_elements() {
        assert_eq!(find(&[5, 10], 1), 0);
    }

    #[test]
    fn test_codewars_examples() {
        assert_eq!(find(&[1, 2, 3], 10), 0);
        assert_eq!(find(&[1, 2, 3], 7), 2);
        assert_eq!(find(&[1, 2, 3], 5), 3);
        assert_eq!(find(&[3, 6, 9, 12], 12), 5);
        assert_eq!(find(&[1, 4, 5, 8], 8), 3);
        assert_eq!(find(&[3, 6, 9, 12], 15), 5);
        assert_eq!(find(&[3, 6, 9, 12, 14, 18], 30), 21);
    }
}
