// tle approach
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int count = 0;

        for (int i = 1; i <= n; i++) {

            boolean left = check(i, 2, 3, 4, 5, reservedSeats);
            boolean middle = check(i, 4, 5, 6, 7, reservedSeats);
            boolean right = check(i, 6, 7, 8, 9, reservedSeats);

            if (left && right) {
                count += 2;
            } 
            else if (left || middle || right) {
                count++;
            }
        }

        return count;
    }

    private boolean check(int row, int j, int k, int l, int m, int[][] reservedSeats) {

        for (int a = 0; a < reservedSeats.length; a++) {

            int[] arr = reservedSeats[a];

            if (arr[0] == row) {

                if (arr[1] == j || 
                    arr[1] == k || 
                    arr[1] == l || 
                    arr[1] == m) {

                    return false;
                }
            }
        }

        return true;
    }
}



//best approach 
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        // Store reserved seats using bitmask
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Only seats 2-9 matter
            if (col >= 2 && col <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Initially every row can have 2 families
        int count = (n - map.size()) * 2;

        for (int mask : map.values()) {

            boolean left = (mask & ((1 << 2) | (1 << 3) | (1 << 4) | (1 << 5))) == 0;

            boolean right = (mask & ((1 << 6) | (1 << 7) | (1 << 8) | (1 << 9))) == 0;

            boolean middle = (mask & ((1 << 4) | (1 << 5) | (1 << 6) | (1 << 7))) == 0;

            if (left && right) {
                count += 2;
            } else if (left || right || middle) {
                count += 1;
            }
        }

        return count;
    }
}