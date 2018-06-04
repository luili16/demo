package com.example.testrxjava;

import android.util.Log;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

import static java.lang.System.out;

/**
 * Created by liu on 18-5-31.
 */

public class Main {

    public static void main(String[] args) {
        Flowable<Integer> flow = Flowable.range(1, 5).
                map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                out.println("integer is : " + integer);
                return integer * integer;
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                out.println("test is : " + integer);
                return integer % 3 == 0;
            }
        });
        flow.startWith(1);
        out.println("flow is " + flow);
    }


}
