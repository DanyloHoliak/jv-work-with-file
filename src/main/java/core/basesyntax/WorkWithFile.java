package core.basesyntax;


import java.io.*;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplySum = 0;
        int buySum = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                int amount = Integer.parseInt(parts[1]);
                if (parts[0].equals("supply")) {
                    supplySum += amount;
                } else if (parts[0].equals("buy")) {
                    buySum += amount;
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Can't read data from the file");
        }
        int result = supplySum - buySum;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supplySum);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buySum);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file");
        }
    }
}
