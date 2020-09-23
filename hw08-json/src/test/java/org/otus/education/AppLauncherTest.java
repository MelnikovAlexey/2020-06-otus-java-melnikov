package org.otus.education;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.otus.education.mson.MsonImpl;

import java.util.Collections;
import java.util.List;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование своей реализации Json -> Mson")
class AppLauncherTest {

    @Test
    void serializeAndPrintStatistic() {

        Gson gson = new Gson();
        MsonImpl serializer = new MsonImpl();

        assertEquals(gson.toJson(null),serializer.toJson(null),"Bug 1");
        assertEquals(gson.toJson((byte) 1),serializer.toJson((byte) 1),"Bug 2");
        assertEquals(gson.toJson((short) 1f),serializer.toJson((short) 1f),"Bug 3");
        assertEquals(gson.toJson(1),serializer.toJson(1),"Bug 4");
        assertEquals(gson.toJson(1L),serializer.toJson(1L),"Bug 5");
        assertEquals(gson.toJson(1f),serializer.toJson(1f),"Bug 6");
        assertEquals(gson.toJson(1d),serializer.toJson(1d),"Bug 7");
        assertEquals(gson.toJson("aaa"),serializer.toJson("aaa"),"Bug 8");
        assertEquals(gson.toJson('a'),serializer.toJson('a'),"Bug 9");
        assertEquals(gson.toJson(new int[]{1, 2, 3}),serializer.toJson(new int[]{1, 2, 3}),"Bug 10");
        assertEquals(gson.toJson(List.of(1, 2, 3)),serializer.toJson(List.of(1, 2, 3)),"Bug 11");
        assertEquals(gson.toJson(Collections.singletonList(1)),serializer.toJson(Collections.singletonList(1)),"Bug 12");
    }
}