
/*
Given an array of integers, find the one that appears an odd number of times.

There will always be only one integer that appears an odd number of times.

Ref.: https://www.codewars.com/kata/54da5a58ea159efa38000836
*/
use std::collections::HashMap;

// Time: O(N) Space: O(1)
fn find_odd(arr: &[i32]) -> i32 {
    let mut result = 0;
    for num in arr {
        result ^= num;
    }
    result
}

// Time: O(N) Space: O(N)
fn find_odd_naive(arr: &[i32]) -> i32 {
    let mut hash = HashMap::new();
    for num in arr {
        let count = hash.entry(num).or_insert(0);
        *count += 1;
    }
    **hash.iter().find(|&(_, &count)| count % 2 != 0).unwrap().0
}

#[cfg(test)]
mod tests {
    use super::{find_odd, find_odd_naive};

    #[test]
    fn basic_tests() {
        assert_eq!(find_odd(&vec![20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5]), 5);
        assert_eq!(find_odd(&vec![1,1,2,-2,5,2,4,4,-1,-2,5]), -1);
        assert_eq!(find_odd(&vec![20,1,1,2,2,3,3,5,5,4,20,4,5]), 5);
        assert_eq!(find_odd(&vec![10]), 10);
        assert_eq!(find_odd(&vec![1,1,1,1,1,1,10,1,1,1,1]), 10);
        assert_eq!(find_odd(&vec![5,4,3,2,1,5,4,3,2,10,10]), 1);
    }

    #[test]
    fn basic_tests_naive() {
        assert_eq!(find_odd_naive(&vec![20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5]), 5);
        assert_eq!(find_odd_naive(&vec![1,1,2,-2,5,2,4,4,-1,-2,5]), -1);
        assert_eq!(find_odd_naive(&vec![20,1,1,2,2,3,3,5,5,4,20,4,5]), 5);
        assert_eq!(find_odd_naive(&vec![10]), 10);
        assert_eq!(find_odd_naive(&vec![1,1,1,1,1,1,10,1,1,1,1]), 10);
        assert_eq!(find_odd_naive(&vec![5,4,3,2,1,5,4,3,2,10,10]), 1);
    }
}
