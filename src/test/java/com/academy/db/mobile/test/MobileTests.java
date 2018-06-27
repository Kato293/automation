package com.academy.db.mobile.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MobileTests {

    @Test(groups="db", dataProvider = "ageData")
    public void testSubscriberAge(int age, String gender) {
        System.out.print("age: " + age);
        System.out.println(" gender: " + gender);
        Assert.assertTrue(age > 5);
        Assert.assertTrue(age < 90);
        Assert.assertEquals(gender, "f");
    }

    @DataProvider(name="ageData")
    public Object[][] ageDataProvider() throws Exception {
        List<Object[]> resultData = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/mobile?user=root&password=root&serverTimezone=UTC&useSSL=false";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from abonent where gender ='f'");

            while (resultSet.next()) {
//                int id = resultSet.getInt("abonent_id");
//                String fName = resultSet.getString("first_name");
//                String lName = resultSet.getString("last_name");
//                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                resultData.add(new Object[]{age, gender});

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData.toArray(new Object[][]{});
    }
}
