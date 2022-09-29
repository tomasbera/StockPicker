import java.util.Random;

public class StockView {

    int numberOfDays = 9;
    int[] stockList = new int[numberOfDays];
    int[] exactValueOfStocks = new int[stockList.length];

    int currentMaxValue = 0;
    int currentMinValue = 0;
    int currentStockValue = stockList[0];

    int minimalValue = 0;
    int sellingDay = 0;
    int buyingDay = 0;

    public static void main(String[] args) {

        StockView stockView = new StockView();
        stockView.randomNumberGenerator();
        final long startTid = System.currentTimeMillis();
        stockView.stockAlgorithm();
        final long endTid = System.currentTimeMillis();
        System.out.println("Total tid er " + (endTid - startTid));

        System.out.println("Buying Day:"+stockView.buyingDay +"\tSelling Day:"+stockView.sellingDay);
    }

    public void randomNumberGenerator(){
        for (int i = 0; i < stockList.length; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(40)-20;
            stockList[i]=randomNumber;
        }
    }

    public void stockAlgorithm(){
        for (int i = 0; i < stockList.length; i++) {

            if (i==0){
                if (stockList[i] < 0){
                    currentMinValue = stockList[i];
                }
                if (stockList[i] >= 0){
                    currentMaxValue = stockList[i];
                }

            }else {
                currentStockValue += stockList[i];

                if (stockList[i] < 0 && currentMinValue > currentStockValue){
                    currentMinValue = currentStockValue;
                    currentMaxValue = currentMinValue;
                }else{
                    currentMaxValue = currentStockValue;
                }
            }

            if (currentMaxValue - currentMinValue <= 0) {
                exactValueOfStocks[i] = 0;
            } else {
                exactValueOfStocks[i] = currentMaxValue - currentMinValue;
            }
        }

        for (int i = 0; i < exactValueOfStocks.length; i++) {
            if (minimalValue < exactValueOfStocks[i]) {
                minimalValue = exactValueOfStocks[i];
                sellingDay = i+1;
            }
        }

        for (int i = 0; i < sellingDay; i++) {
            if (exactValueOfStocks[i] == 0) {
                buyingDay = i+1;
            }
        }
    }
}