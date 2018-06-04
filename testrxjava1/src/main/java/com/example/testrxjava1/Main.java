package com.example.testrxjava1;

import java.util.Arrays;
import java.util.Collections;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static java.lang.System.out;

/**
 * Created by liu on 18-6-1.
 */

public class Main {

    public static void main(String[] args) {


        Subscriber<Course> subscriber = new Subscriber<Course>() {

            @Override
            public void onCompleted() {
                out.println("subscriber Completed");
            }

            @Override
            public void onError(Throwable e) {
                out.println("subscriber Error");
            }

            @Override
            public void onNext(Course s) {
                out.println("subscriber : Item : " + s);
            }
        };


        //observable.subscribe(observer).unsubscribe();

        Observable<Student> observable1 = Observable.just(new Student("jack", Arrays.asList(new Course("Tom"),new Course("D"))));

        Subscription subscribe = observable1.flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                //return Observable.from();
                return Observable.from(student.getCourse());
            }
        }).subscribe(subscriber);
        subscribe.unsubscribe();

        out.println("done");
    }

}
