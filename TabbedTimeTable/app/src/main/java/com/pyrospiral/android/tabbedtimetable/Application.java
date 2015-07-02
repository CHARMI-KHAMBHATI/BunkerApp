package com.pyrospiral.android.tabbedtimetable;

/**
 * Created by adeshkala on 05/04/15.
 */
import com.parse.Parse;

public class Application extends android.app.Application {

    public void onCreate() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "nM0XvSWdxg4oC5n5FVdMoH9O1gu5qYn7sv6qhkcl", "ERQ8PlB5ax6kGksgENtpfdy4vuGOgfCxKIcyXnRp");
    }

}