public class Main {
    /*
     有n个office，输入是calendar，告诉你每个月各个office的一年内每个月holiday数量，
	    另外你有一个matrix cost，cost[i][j]表示从office i 到office j的travel cost，
	    constraint：有一个integer maxCost，你不能take a flight that costs more than that.
	    然后每次travel必须是直达。要求输出一个path of size 12, where path[i]是你month i
     所在的office，so that you maximize the number of holidays you can take.
     */
    public int[] maxHolidays(int[][] costs, int[][] holiday) {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (cost[j][k] < maxCost) {
                        dp[i][j] = max(dp[i][j], dp[i - 1][k] + holiday[i][j])
                    }
                }
            }
        }
        if (cost[k][j])
            }
    
    public static void main(String[] args) {
        
    }
}
