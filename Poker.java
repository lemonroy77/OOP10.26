import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Poker{
    public static void main(String[] args) {
        String[] poker = {
                "红桃A", "红桃2", "红桃3", "红桃4", "红桃5", "红桃6", "红桃7", "红桃8", "红桃9", "红桃10", "红桃J", "红桃Q", "红桃K",
                "黑桃A", "黑桃2", "黑桃3", "黑桃4", "黑桃5", "黑桃6", "黑桃7", "黑桃8", "黑桃9", "黑桃10", "黑桃J", "黑桃Q", "黑桃K",
                "方块A", "方块2", "方块3", "方块4", "方块5", "方块6", "方块7", "方块8", "方块9", "方块10", "方块J", "方块Q", "方块K",
                "梅花A", "梅花2", "梅花3", "梅花4", "梅花5", "梅花6", "梅花7", "梅花8", "梅花9", "梅花10", "梅花J", "梅花Q", "梅花K",
                "大王","小王",
                "红桃A", "红桃2", "红桃3", "红桃4", "红桃5", "红桃6", "红桃7", "红桃8", "红桃9", "红桃10", "红桃J", "红桃Q", "红桃K",
                "黑桃A", "黑桃2", "黑桃3", "黑桃4", "黑桃5", "黑桃6", "黑桃7", "黑桃8", "黑桃9", "黑桃10", "黑桃J", "黑桃Q", "黑桃K",
                "方块A", "方块2", "方块3", "方块4", "方块5", "方块6", "方块7", "方块8", "方块9", "方块10", "方块J", "方块Q", "方块K",
                "梅花A", "梅花2", "梅花3", "梅花4", "梅花5", "梅花6", "梅花7", "梅花8", "梅花9", "梅花10", "梅花J", "梅花Q", "梅花K",
                "大王","小王"
        };

        // 洗牌
        Random random = new Random();
        for (int i = 0; i < 20000; i++) {
            int index1 = random.nextInt(108);
            int index2 = random.nextInt(108);
            String temp = poker[index1];
            poker[index1] = poker[index2];
            poker[index2] = temp;
        }

        // 发牌
        String[] player1 = new String[25];
        String[] player2 = new String[25];
        String[] player3 = new String[25];
        String[] player4 = new String[25];
        String[] bottomCards = new String[8];

        int index = 0;
        for (int i = 0; i < 25; i++) {
            player1[i] = poker[index++];
        }
        for (int i = 0; i < 25; i++) {
            player2[i] = poker[index++];
        }
        for (int i = 0; i < 25; i++) {
            player3[i] = poker[index++];
        }
        for (int i = 0; i < 25; i++) {
            player4[i] = poker[index++];
        }
        for (int i = 0; i < 8; i++) {
            bottomCards[i] = poker[index++];
        }

        // 对手牌进行排序
        Arrays.sort(player1, new Comparator<String>() {
            public int compare(String card1, String card2) {
                int colorDiff = getCardColor(card1) - getCardColor(card2);
                if (colorDiff != 0) {
                    return colorDiff;
                } else {
                    int numDiff = getCardNumber(card1) - getCardNumber(card2);
                    return numDiff == 0 ? 0 : (numDiff > 0 ? -1 : 1);
                }
            }
        });

        Arrays.sort(player2, new Comparator<String>() {
            public int compare(String card1, String card2) {
                int colorDiff = getCardColor(card1) - getCardColor(card2);
                if (colorDiff != 0) {
                    return colorDiff;
                } else {
                    int numDiff = getCardNumber(card1) - getCardNumber(card2);
                    return numDiff == 0 ? 0 : (numDiff > 0 ? -1 : 1);
                }
            }
        });

        Arrays.sort(player3, new Comparator<String>() {
            public int compare(String card1, String card2) {
                int colorDiff = getCardColor(card1) - getCardColor(card2);
                if (colorDiff != 0) {
                    return colorDiff;
                } else {
                    int numDiff = getCardNumber(card1) - getCardNumber(card2);
                    return numDiff == 0 ? 0 : (numDiff > 0 ? -1 : 1);
                }
            }
        });

        Arrays.sort(player4, new Comparator<String>() {
            public int compare(String card1, String card2) {
                int colorDiff = getCardColor(card1) - getCardColor(card2);
                if (colorDiff != 0) {
                    return colorDiff;
                } else {
                    int numDiff = getCardNumber(card1) - getCardNumber(card2);
                    return numDiff == 0 ? 0 : (numDiff > 0 ? -1 : 1);
                }
            }
        });

        // 输出结果
        System.out.println("甲：");
        printCards(player1);
        System.out.println();
        System.out.println("乙：");
        printCards(player2);
        System.out.println();
        System.out.println("丙：");
        printCards(player3);
        System.out.println();
        System.out.println("丁：");
        printCards(player4);
        System.out.println();
        System.out.println("底牌：");
        printCards(bottomCards);
    }

    // 输出玩家手牌
    private static void printCards(String[] cards) {
        for (String card : cards) {
            System.out.print(card + " ");
        }
    }

    // 获取牌的花色
    private static int getCardColor(String card) {
        if (card.contains("红桃")) {
            return 1;
        } else if (card.contains("黑桃")) {
            return 2;
        } else if (card.contains("方块")) {
            return 3;
        } else if (card.contains("梅花")) {
            return 4;
        } else if (card.contains("大王") || card.contains("小王")) {
            return 5;
        } else {
            return 0;
        }
    }

    // 获取牌的数字大小
    private static int getCardNumber(String card) {
        if (card.contains("A")) {
            return 1 ;
           } else if (card.contains("2")) {
                return 2;
            } else if (card.contains("3")) {
                return 3;
            } else if (card.contains("4")) {
                return 4;
            } else if (card.contains("5")) {
                return 5;
            } else if (card.contains("6")) {
                return 6;
            } else if (card.contains("7")) {
                return 7;
            } else if (card.contains("8")) {
                return 8;
            } else if (card.contains("9")) {
                return 9;
            } else if (card.contains("10")) {
                return 10;
            } else if (card.contains("J")) {
                return 11;
            } else if (card.contains("Q")) {
                return 12;
            } else if (card.contains("K")) {
                return 13;
            } else {
                return 0;
            }
        }
    }
