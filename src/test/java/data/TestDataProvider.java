package data;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.railway.User;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TestDataProvider {

    @DataProvider(name = "bookTicketChapter3")
    public Object[][] getBookTicketChapter3() {
        return getTestData("testChapter3");
    }

    @DataProvider(name = "bookTicketChapter5_8")
    public Object[][] getBookTicketChapter5_8() {
        JSONArray testData = loadTestData();
        List<Object[]> testDataList = new ArrayList<>();

        for (int i = 0; i < testData.length(); i++) {
            JSONObject testDataItem = testData.getJSONObject(i);
            if (testDataItem.has("testChapter5_8")) {
                JSONObject data = testDataItem.getJSONObject("testChapter5_8");
                User user = new User();
                user.setUsername(data.optString("username", ""));
                user.setDomain(data.optString("domain", ""));
                user.setPassword(data.optString("password", ""));
                user.setDepart(data.optString("depart", ""));
                user.setArrive(data.optString("arrive", ""));
                user.setSeatType(data.optString("seatType", ""));
                user.setAmountTicket(data.optString("amountTicket", ""));
                user.setDepartDate(data.optString("departDate", ""));
                user.setEmail(generateEmail(data.optString("username", ""), data.optString("domain", "")));

                String expectedMessage = data.optString("expectedMessage", "");
                int date = data.optInt("date", 0);

                testDataList.add(new Object[]{user, expectedMessage, date});
            }
        }

        return convertToObjectArray(testDataList);
    }

    @DataProvider(name = "chapter10Testcase1")
    public Object[][] getLoginChapter10Testcase1() {
        return getTestData("testChapter10", "testCase1");
    }

    @DataProvider(name = "chapter10Testcase2")
    public Object[][] getLoginChapter10Testcase2() {
        return getTestData("testChapter10", "testCase2");
    }

    @DataProvider(name = "chapter10Testcase3")
    public Object[][] getLoginChapter10Testcase3() {
        return getTestData("testChapter10", "testCase3");
    }

    @DataProvider(name = "chapter10Testcase4")
    public Object[][] getLoginChapter10Testcase4() {
        String chapterKey = "testChapter10";
        String testCaseKey = "testCase4";
        JSONArray testData = loadTestData();
        JSONObject chapterData = getChapterData(testData, chapterKey);
        JSONObject testCaseData = getTestCaseData(chapterData, testCaseKey);

        User user = new User();
        user.setUsername(testCaseData.optString("username", ""));
        user.setDomain(testCaseData.optString("domain", ""));
        user.setPassword(testCaseData.optString("password", ""));
        if (testCaseData.has("email")) {
            user.setEmail(testCaseData.optString("email", ""));
        } else {
            user.setEmail(generateEmail(testCaseData.optString("username", ""), testCaseData.optString("domain", "")));
        }

        String attempErrorMessage = testCaseData.optString("attempErrorMessage", "");
        String errorMessage = testCaseData.optString("errorMessage", "");

        return new Object[][]{{user, errorMessage, attempErrorMessage}};
    }

    @DataProvider(name = "chapter10Testcase5")
    public Object[][] getLoginChapter10Testcase5() {
        return getTestData("testChapter10", "testCase5");
    }

    @DataProvider(name = "chapter10Testcase6")
    public Object[][] getLoginChapter10Testcase6() {
        String chapterKey = "testChapter10";
        String testCaseKey = "testCase6";

        JSONArray testData = loadTestData();
        JSONObject chapterData = getChapterData(testData, chapterKey);
        JSONObject testCaseData = getTestCaseData(chapterData, testCaseKey);

        User user = new User();
        user.setUsername(testCaseData.optString("username", ""));
        user.setDomain(testCaseData.optString("domain", ""));
        user.setPassword(testCaseData.optString("password", ""));
        if (testCaseData.has("email")) {
            user.setEmail(testCaseData.optString("email", ""));
        } else {
            user.setEmail(generateEmail(testCaseData.optString("username", ""), testCaseData.optString("domain", "")));
        }

        return new Object[][]{{user}};
    }



    private Object[][] getTestData(String chapterKey) {
        JSONArray testData = loadTestData();
        List<Object[]> testDataList = parseTestData(testData, chapterKey);
        return convertToObjectArray(testDataList);
    }

    private Object[][] getTestData(String chapterKey, String testCaseKey) {
        JSONArray testData = loadTestData();
        JSONObject chapterData = getChapterData(testData, chapterKey);
        JSONObject testCaseData = getTestCaseData(chapterData, testCaseKey);

        User user = new User();
        user.setUsername(testCaseData.optString("username", ""));
        user.setDomain(testCaseData.optString("domain", ""));
        user.setPassword(testCaseData.optString("password", ""));
        if (testCaseData.has("email")) {
            user.setEmail(testCaseData.optString("email", ""));
        } else {
            user.setEmail(generateEmail(testCaseData.optString("username", ""), testCaseData.optString("domain", "")));
        }

        String expectedMessage = testCaseData.optString("expectedMessage", "");
        String errorMessage = testCaseData.optString("errorMessage", "");


        if (!expectedMessage.isEmpty()) {
            return new Object[][]{{user, expectedMessage}};
        } else {
            return new Object[][]{{user, errorMessage}};
        }
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
            JSONTokener tokener = new JSONTokener(reader);
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
                JSONObject data = testDataItem.getJSONObject(dataKey);
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
                if (testDataItem.has("email")) {
                    user.setEmail(data.optString("email", ""));
                } else {
                    user.setEmail(generateEmail(data.optString("username", ""), data.optString("domain", "")));
                }

                String expectedMessage = data.optString("expectedMessage", "");
                String errorMessage = data.optString("errorMessage", "");

                if (!expectedMessage.isEmpty() && !errorMessage.isEmpty()) {
                    testDataList.add(new Object[]{user, expectedMessage, errorMessage});
                } else if (!expectedMessage.isEmpty()) {
                    testDataList.add(new Object[]{user, expectedMessage});
                } else if (!errorMessage.isEmpty()) {
                    testDataList.add(new Object[]{user, errorMessage});
                } else {
                    testDataList.add(new Object[]{user});
                }
            }
        }
        return testDataList;
    }

    private String generateEmail(String username, String domain) {
        return username + "@" + domain;
    }

    private JSONObject getChapterData(JSONArray testData, String chapterKey) {
        for (int i = 0; i < testData.length(); i++) {
            JSONObject testDataItem = testData.getJSONObject(i);
            if (testDataItem.has(chapterKey)) {
                return testDataItem.getJSONObject(chapterKey);
            }
        }
        throw new IllegalArgumentException("Chapter key not found: " + chapterKey);
    }

    private JSONObject getTestCaseData(JSONObject chapterData, String testCaseKey) {
        if (chapterData.has(testCaseKey)) {
            return chapterData.getJSONObject(testCaseKey);
        }
        throw new IllegalArgumentException("Test case key not found: " + testCaseKey);
    }
}
