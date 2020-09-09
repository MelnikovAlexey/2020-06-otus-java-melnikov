package org.otus.education;


import com.google.gson.Gson;
import org.otus.education.mson.Mson;
import org.otus.education.mson.MsonImpl;

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

    private static void serializeAndPrintStatistic(AnyObject origin) {
        System.out.println("Origin object:");
        System.out.println(origin.toString());

        Mson mson = new MsonImpl();
        String msonString = mson.toJson(origin);
        System.out.println("Mson String: " + msonString);

        Gson gson = new Gson();
        String gsonSting = gson.toJson(origin);
        System.out.println("Gson String: " + gsonSting);

        AnyObject msonRebuildAny = gson.fromJson(msonString, AnyObject.class);
        System.out.println("Recreate from Mson String: ");
        System.out.println(msonRebuildAny.toString());
        AnyObject gsonObject = gson.fromJson(gsonSting, AnyObject.class);
        System.out.println("Recreate from Gson String: ");
        System.out.println(gsonObject.toString());


        System.out.println("Result comparison Mson with Origin: " + msonRebuildAny.equals(origin));
        System.out.println("Result comparison Mson with Gson: " + msonRebuildAny.equals(gsonObject));
    }
}
