
public class manacher {
    public static void main(String[] args) {
        String s = "baccabddba";
        System.out.println(manacherDemo.longestPalindrome(s));
    }
}


class manacherDemo{
    public static String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        sb.append('#');
        for(int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }

        String str = sb.toString();
        n = str.length();
        int[] P = new int[n];
        int C = 0;
        int R = 0;
        int maxLen = 0;
        int start = 0;
        for(int i = 0; i < n; i++) {
            int iM = 2 * C - i;
            if(R > i) {
                P[i] = Math.min(P[iM], R - i);
            }


            int left = i - 1 - P[i];
            int right = i + 1 + P[i];

            while(left >= 0 && right < n && str.charAt(left) == str.charAt(right)) {
                left--;
                right++;
                P[i]++;
            }
            if(i + P[i] > R) {
                C = i;
                R = C + P[i];
            }

            if(P[i] > maxLen) {
                maxLen = P[i];
                start = (i - maxLen) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }
}