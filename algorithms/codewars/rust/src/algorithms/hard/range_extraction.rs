/*
Description:

A format for expressing an ordered list of integers is to use a comma separated list of either

- individual integers
- or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'.
The range includes all integers in the interval including both endpoints. It is not considered a range unless it spans at least 3 numbers. For example "12,13,15-17"

Complete the solution so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.

Kata: https://www.codewars.com/kata/51ba717bb08c1cd60f00002f
*/
// Time: O(N) Space: O(N)
pub fn range_extraction(integers: &[i32]) -> String {
    if integers.is_empty() {
        return "".to_string();
    }

    let mut result = vec![];
    let mut index = 0;

    while (index < integers.len()) {
        let mut current = index;
        let mut next = index + 1;

        while next < integers.len() && integers[current] == integers[next] - 1 {
            current += 1;
            next += 1;
        }

        if (next - index) >= 3 {
            result.push(format!("{}-{}", integers[index], integers[next - 1]));
            index = next
        } else {
            result.push(format!("{}", integers[index]));
            index += 1;
        }
    }

    result.join(",")
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn basic_cases() {
        assert_eq!(range_extraction(&[]), "");
        assert_eq!(
            range_extraction(&[
                -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20
            ]),
            "-6,-3-1,3-5,7-11,14,15,17-20"
        );
        assert_eq!(
            range_extraction(&[-3, -2, -1, 2, 10, 15, 16, 18, 19, 20]),
            "-3--1,2,10,15,16,18-20"
        );
    }
}
