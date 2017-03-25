public class erFenfa {

    public static void main(final String[] args) {
        final int[] a = {4, 2, 1, 6, 3, 6, 0, -5, 1, 1};
        int i, j;
        int low, high, mid;
        int temp;
        for (i = 1; i < 10; i++) {
            temp = a[i];
            low = 0;
            high = i - 1;
            while (low <= high) {
                mid = (low + high) / 2;
                if (a[mid] > temp) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }

            }
            for (j = i - 1; j > high; j--) {
                a[j + 1] = a[j];
            }
            a[high + 1] = temp;
        }
        for (i = 0; i < 10; i++) {
            System.out.printf("%d", a[i]);
        }


        //////////////////////////2

       /* final int A[] = { 5, 2, 9, 4, 7, 6, 1, 3, 8 };// 从小到大二分插入排序
        final int n = A.length;
        int i, j, get, left, right, middle;

        for (i = 1; i < n; i++)                 // 类似抓扑克牌排序
        {
            get = A[i];                         // 右手抓到一张扑克牌
            left = 0;                           // 拿在左手上的牌总是排序好的,所以可以用二分法
            right = i - 1;                      // 手牌左右边界进行初始化
            while (left <= right)               // 采用二分法定位新牌的位置
            {
                middle = (left + right) / 2;
                if (A[middle] > get) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            for (j = i - 1; j >= left; j--)    // 将欲插入新牌位置右边的牌整体向右移动一个单位
            {
                A[j + 1] = A[j];
            }
            A[left] = get;                    // 将抓到的牌插入手牌
        }
        System.out.println("二分插入排序结果：");
        for (i = 0; i < n; i++)
        {
            System.out.println(A[i]);
        }
        System.out.println("\n");*/
    }

}
