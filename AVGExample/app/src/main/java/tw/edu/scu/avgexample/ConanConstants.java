package tw.edu.scu.avgexample;

public class ConanConstants {


    /*
    * 0是黑衣人
    * 1是怪盜基德
    * 2是琴酒一
    * 3是琴酒二
    * 4是都不是
    * */
    public static final boolean[] ending_finished = new boolean[5];

    public static int showFinishedEndings()
    {
        int finishedCount = 0;

        for (boolean x : ending_finished)
        {
            if (x)
            {
                finishedCount++;
            }
        }

        return finishedCount;
    }

    //
    public static boolean isAllEndingFinished()
    {
        if (showFinishedEndings() == 5)
            return true;

        return false;
    }

    //set every element in ending_finished as true for testing
    public static void testing()
    {
        for (int i = 0; i < 5; i++)
        {
            ConanConstants.ending_finished[i] = true;
        }
    }

    public static void clearEndingFinished()
    {
        for (int i = 0; i < 5; i++)
        {
            ConanConstants.ending_finished[i] = false;
        }
    }

}
