package org.example.ioc;

import org.example.annotation.CustomAutowired;
import java.lang.reflect.Constructor;
import java.util.Arrays;



public class CustomApplicationContext {

    public static <T> T getInstance(Class<T> clazz) throws Exception {

        T instance = createInstance(clazz);
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            if (field.getAnnotation(CustomAutowired.class) != null) { // CustomAutowired 붙은 멤버필드일 경우
                try {
                    Object fieldInstance = createInstance(field.getType()); // 멤버필드에 대한 객체 생성
                    field.setAccessible(true);
                    field.set(instance, fieldInstance); // 생성된 객체를 instance에 셋팅 (DI)
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return instance;
    }
    private static <T> T createInstance(Class<T> clazz) throws Exception {
        Constructor<T> constructor = clazz.getDeclaredConstructor(); // 리플렉션을 통해 클래스의 기본생성자 정보 조회
        constructor.setAccessible(true);

        return constructor.newInstance(); // 객체 생성
    }
}