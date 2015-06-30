package com.ma.qa.sikuli;

import com.ma.qa.commons.CommonUtil;
import com.ma.qa.utils.Utils;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.util.List;

/**
 * Created by Anson on 18/9/14.
 */
public class SikuliX {

    /**
     * default image recognize sensitive
     */
    public static float imageSimilar = (float) 0.6;

    /**
     *
     * @param image
     * @return boolean value
     * @throws Exception
     */
    public static boolean exists(String image) throws Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        if (s.exists(new Pattern(image).similar(imageSimilar)) != null)
            return true;
        else
            return false;
    }

    /**
     *
     * @param image
     * @param similarValue
     * @return boolean value
     * @throws Exception
     */
    public static boolean exists(String image, float similarValue) throws Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        if (s.exists(new Pattern(image).similar((similarValue))) != null) {
            return true;
        }
        else
            return false;

    }

    /**
     *
     * @param image
     * @return boolean value
     * @throws Exception
     */
    public static boolean existsExact(String image) throws Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        if (s.exists(new Pattern(image).exact()) != null)
            return true;
        else
            return false;
    }

    /**
     *
     * @param image
     * @return boolean value
     * @throws Exception
     */
    public static boolean noExists(String image) throws  Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        if (s.exists(new Pattern(image).similar(imageSimilar)) == null)
            return true;
        else
            return false;

    }

    /**
     *
     * @param image
     * @return boolean value
     * @throws Exception
     */
    public static boolean noExistsExact(String image) throws Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        if (s.exists(new Pattern(image).exact()) == null)
            return true;
        else
            return false;
    }

    /**
     *
     * @param image
     * @return boolean value
     * @throws Exception
     */
    public static boolean find(String image) throws Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        if (s.find(image) != null)
            return true;
        else
            return false;

    }

    /**
     * Sleep 1 second after click action implemented
     * @param image
     * @throws Exception
     */
    public static void click(String image) throws Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        s.click(image);
        //CommonUtil.sleep(1);
    }

    /**
     * Sleep time depend on parameter 'sleepSecond' after click action implemented
     * @param image
     * @param sleepSecond
     * @throws Exception
     */
    public static void click(String image, int sleepSecond) throws Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        s.click(image);

        if (sleepSecond > 0)                //just sleep when the time greater than zero
            CommonUtil.sleep(sleepSecond);
    }

    /**
     * Click some images and the images are stores in List variable
     * @param imageList
     * @throws Exception
     */
    public static void click(List<String> imageList) throws Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        for (String image : imageList) {
            s.click(image);
        }
    }

    /**
     * Sleep 1 second after drag and drop action implemented
     * @param dragImg
     * @param dropImg
     * @throws Exception
     */
    public static void dragAndDrop(String dragImg, String dropImg) throws Exception {
        Utils.iOSSimActive();
        Screen s = new Screen();

        s.dragDrop(dragImg, dropImg);
        CommonUtil.sleep(1);
    }

    /**
     * Reset imageSimilar variable to ZERO
     */
    public static void clear() {
        imageSimilar = 0;
    }

    /**
     *
     * @return image similar value
     */
    public static float getImageSimilarValue() {
        return imageSimilar;
    }

}
