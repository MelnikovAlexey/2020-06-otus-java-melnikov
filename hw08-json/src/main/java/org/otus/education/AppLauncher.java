package org.otus.education;


import com.google.gson.Gson;
import org.otus.education.mson.Mson;
import org.otus.education.mson.MsonImpl;

import java.util.Collections;
import java.util.List;

import static java.lang.System.out;
import static java.lang.System.setOut;

public class AppLauncher {
    public static void main(String[] args) {
        //массивы и листы объявлены внутри класса
        AnyObject origin = new AnyObject.Builder()
                .setBoolean(false)
                .setInt(100)
                .setStr(null)
                .setChar('r')
                .build();
        Gson gson = new Gson();
MsonImpl mson = new MsonImpl();
        String s = gson.toJson((byte) 1);
        serializeAndPrintStatisticOld(origin);
        serializeAndPrintStatistic(origin);
    }

    private static void serializeAndPrintStatisticOld(AnyObject origin) {
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

    protected static void serializeAndPrintStatistic(AnyObject origin) {
        out.println("Origin object:");
        out.println(origin.toString());


        Gson gson = new Gson();
        var serializer = new MsonImpl();

        if (!gson.toJson(null).equals(serializer.toJson(null))) {
            throw new RuntimeException("bug0!");
        }
        if (!gson.toJson((byte) 1).equals(serializer.toJson((byte) 1))) {
            throw new RuntimeException("bug1!");
        }
        if (!gson.toJson((short) 1f).equals(serializer.toJson((short) 1f))) {
            throw new RuntimeException("bug2!");
        }
        if (!gson.toJson(1).equals(serializer.toJson(1))) {
            throw new RuntimeException("bug3!");
        }
        if (!gson.toJson(1L).equals(serializer.toJson(1L))) {
            throw new RuntimeException("bug4!");
        }
        if (!gson.toJson(1f).equals(serializer.toJson(1f))) {
            throw new RuntimeException("bug5!");
        }
        if (!gson.toJson(1d).equals(serializer.toJson(1d))) {
            throw new RuntimeException("bug6!");
        }
        if (!gson.toJson("aaa").equals(serializer.toJson("aaa"))) {
            throw new RuntimeException("bug7!");
        }
        if (!gson.toJson('a').equals(serializer.toJson('a'))) {
            throw new RuntimeException("bug8!");
        }
        if (!gson.toJson(new int[]{1, 2, 3}).equals(serializer.toJson(new int[]{1, 2, 3}))) {
            out.println(gson.toJson(new int[]{1, 2, 3}));
            out.println(serializer.toJson(new int[]{1, 2, 3}));
            throw new RuntimeException("bug9!");
        }
        if (!gson.toJson(List.of(1, 2, 3)).equals(serializer.toJson(List.of(1, 2, 3)))) {
            throw new RuntimeException("bug10!");
        }
        if (!gson.toJson(Collections.singletonList(1)).equals(serializer.toJson(Collections.singletonList(1)))) {
            throw new RuntimeException("bug11!");
        }
    }
}
