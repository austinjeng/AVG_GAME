package tw.edu.scu.avgexample.framework;

import tw.edu.scu.avgexample.framework.library.KWSoundManager;
import tw.edu.scu.avgexample.framework.utility.KWLog;

public class KWAvgApplication extends KWBaseApplication {
    @Override
    protected void applicationDidEnterBackground() {
        super.applicationDidEnterBackground();
        KWLog.d("[資訊] KWBaseApplication 進入背景");
        KWSoundManager.sharedInstance(this).pauseBgm();
    }

    @Override
    protected void applicationDidBecomeActive() {
        super.applicationDidBecomeActive();
        KWLog.d("[資訊] KWBaseApplication 回到前景");
        KWSoundManager.sharedInstance(this).resumeBgm();
    }
}
