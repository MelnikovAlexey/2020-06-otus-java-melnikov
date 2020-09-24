package org.otus.education;


import com.google.gson.Gson;
import org.otus.education.mson.Mson;
import org.otus.education.mson.MsonImpl;

import static java.lang.System.out;

public class AppLauncher {
    public static void main(String[] args) {
        //массивы и листы объявлены внутри класса
        AnyObject origin = new AnyObject.Builder()
                .setBoolean(false)
                .setInt(100)
                .setStr(null)
                .setChar('r')
                .build();

        serializeAndPrintStatistic(origin);
    }

    protected static void serializeAndPrintStatistic(AnyObject origin) {
        out.println("Origin object:");
        out.println(origin.toString());

        Mson mson = new MsonImpl();
        String msonString = mson.toJson(origin);
        out.println("Mson String: " + msonString);

        Gson gson = new Gson();
        String gsonSting = gson.toJson(origin);
        out.println("Gson String: " + gsonSting);

        AnyObject msonRebuildAny = gson.fromJson(msonString, AnyObject.class);
        out.println("Recreate from Mson String: ");
        out.println(msonRebuildAny.toString());
        AnyObject gsonObject = gson.fromJson(gsonSting, AnyObject.class);
        out.println("Recreate from Gson String: ");
        out.println(gsonObject.toString());

        out.println("Result comparison Mson with Origin: " + msonRebuildAny.equals(origin));
        out.println("Result comparison Mson with Gson: " + msonRebuildAny.equals(gsonObject));
    }
}
