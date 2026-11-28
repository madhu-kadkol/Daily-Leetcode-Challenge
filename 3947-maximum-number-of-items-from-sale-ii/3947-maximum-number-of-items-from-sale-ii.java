class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxFactor = 0;
        int minPrice = Integer.MAX_VALUE;
        int[] freq = new int[n + 1];
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            freq[factor]++;
            maxFactor = Math.max(maxFactor, factor);
            minPrice = Math.min(minPrice, price);
        }
        int[] gain = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            int count = 0;
            for (int j = f; j <= maxFactor; j += f) {
                count += freq[j];
            }
            gain[f] = count - 1;
        }
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order,
            (a, b) -> Integer.compare(
                items[a][1], items[b][1]
            )
        );
        long ans = 0;
        long remaining = budget;
        for (int idx : order) {
            int factor = items[idx][0];
            int price = items[idx][1];
            if (price > 2L * minPrice) {
                break;
            }
            int available = gain[factor];
            if (available <= 0) {
                continue;
            }
            long count = Math.min(
                available,
                remaining / price
            );
            ans += count * 2;
            remaining -= count * price;
            if (remaining < price) {
                break;
            }
        }
        ans += remaining / minPrice;
        return (int) ans;
    }
}