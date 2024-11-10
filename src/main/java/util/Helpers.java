package util;

import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Step;
import org.json.JSONObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.IntStream;

import static java.time.Duration.ZERO;
import static java.time.Duration.ofSeconds;

import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

public class Helpers {

    public enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private final PointerInput FINGER = new PointerInput(TOUCH, "finger");

    public void swipe(IOSDriver driver, Directions directions) {
        int startX = driver.manage().window().getSize().getWidth() / 2;
        int startY = driver.manage().window().getSize().getHeight() / 2;

        int endX = startX;
        int endY = startY;

        switch (directions) {
            case UP -> endY = (int) (driver.manage().window().getSize().getHeight() * 0.2);
            case DOWN -> endY = (int) (driver.manage().window().getSize().getHeight() * 0.8);
            case LEFT -> {
                startX = (int) (driver.manage().window().getSize().getWidth() * 0.9);
                endX = (int) (driver.manage().window().getSize().getWidth() * 0.1);
            }
            case RIGHT -> {
                startX = (int) (driver.manage().window().getSize().getWidth() * 0.1);
                endX = (int) (driver.manage().window().getSize().getWidth() * 0.9);
            }
            default -> throw new IllegalArgumentException("Invalid direction selected:" + directions);
        }

        Sequence swipe = new Sequence(FINGER, 0);
        swipe.addAction(FINGER.createPointerMove(ZERO, viewport(), startX, startY));
        swipe.addAction(FINGER.createPointerDown(LEFT.asArg()));
        swipe.addAction(FINGER.createPointerMove(ofSeconds(1), viewport(), endX, endY));
        swipe.addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(List.of(swipe));
    }

    @Step("Scrole to element")
    public void scrollTo(IOSDriver driver, WebElement element, Directions directions, int swipeCount) {
        IntStream.range(0, swipeCount).forEach(obj -> {
            if (!element.isDisplayed())
                swipe(driver, directions);
        });
    }

    @Step("Element is long pressed")
    public void longPress(IOSDriver driver, WebElement element) {
        Point location = getCenter(element);
        Sequence longPressAction = new Sequence(FINGER, 0);
        longPressAction.addAction(FINGER.createPointerMove(ZERO, viewport(), location.x, location.y));
        longPressAction.addAction(FINGER.createPointerDown(LEFT.asArg()));
        longPressAction.addAction(FINGER.createPointerMove(ofSeconds(1), viewport(), location.x, location.y));
        longPressAction.addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(List.of(longPressAction));
    }

    public Point getCenter(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        return new Point(location.x + size.getWidth() / 2, location.y + size.getHeight() / 2);
    }

    public static String getRandomActivityName() {
        try {
            URL url = new URL("https://bored-api.appbrewery.com/random");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == 429) {
                System.out.println("Received 429 Too Many Requests. Try again later. Returning default activity.");
                return "Default Activity: Reading a book";
            } else if (responseCode != 200) {
                throw new RuntimeException("Failed: HTTP error code : " + responseCode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(content.toString());
            return jsonResponse.getString("activity");

        } catch (Exception e) {
            e.printStackTrace();
            return "Default Activity: Reading a book";
        }
    }

}
