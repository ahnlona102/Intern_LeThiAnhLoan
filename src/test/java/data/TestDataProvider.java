package data;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.railway.User;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;

public class TestDataProvider {

    @DataProvider(name = "bookTicketChapter3")
    public Object[][] getBookTicketChapter3() {
        return getTestData("testChapter3");
    }

    @DataProvider(name = "bookTicketChapter5_8")
    public Object[][] getBookTicketChapter5_8() {
        return getTestData("testChapter5_8");
    }

    private Object[][] getTestData(String dataKey) {
        JSONArray testData = loadTestData();
        List<Object[]> testDataList = parseTestData(testData, dataKey);
        return convertToObjectArray(testDataList);
    }

    private Object[][] convertToObjectArray(List<Object[]> testDataList) {
        Object[][] testDataArray = new Object[testDataList.size()][];
        for (int i = 0; i < testDataList.size(); i++) {
            testDataArray[i] = testDataList.get(i);
        }
        return testDataArray;
    }

    private JSONArray loadTestData() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("testdata.json")) {
            if (inputStream == null) {
                throw new RuntimeException("Test data file not found");
            }
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            JSONTokener tokener = new JSONTokener(inputStream);
            JSONObject jsonObject = new JSONObject(tokener);
            return jsonObject.getJSONArray("testData");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    private List<Object[]> parseTestData(JSONArray testData, String dataKey) {
        List<Object[]> testDataList = new ArrayList<>();
        for (int i = 0; i < testData.length(); i++) {
            JSONObject testDataItem = testData.getJSONObject(i);
            if (testDataItem.has(dataKey)) {
                JSONArray testChapterArray = testDataItem.getJSONArray(dataKey);
                for (int j = 0; j < testChapterArray.length(); j++) {
                    JSONObject data = testChapterArray.getJSONObject(j);
                    User user = new User();
                    user.setUsername(data.optString("username", ""));
                    user.setDomain(data.optString("domain", ""));
                    user.setPassword(data.optString("password", ""));
                    user.setConfirmPassword(data.optString("confirmpassword", ""));
                    user.setPassport(data.optString("passport", ""));
                    user.setDepart(data.optString("depart", ""));
                    user.setArrive(data.optString("arrive", ""));
                    user.setSeatType(data.optString("seatType", ""));
                    user.setAmountTicket(data.optString("amountTicket", ""));
                    user.setDepartDate(data.optString("departDate", ""));
                    user.setEmail(generateEmail(data.optString("username", ""), data.optString("domain", "")));

                    String expectedMessage = data.optString("expectedMessage", "");
                    int date = data.optInt("date", 0);

                    if (expectedMessage.isEmpty() && date == 0) {
                        Object[] testDataArray = {user};
                        testDataList.add(testDataArray);
                    } else {
                        Object[] testDataArray = {user, expectedMessage, date};
                        testDataList.add(testDataArray);
                    }
                }
            }
        }
        return testDataList;
    }

    private String generateEmail(String username, String domain) {
        return username + "@" + domain;
    }
}
