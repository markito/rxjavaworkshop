package com.ora;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import io.reactivex.functions.Consumer;
import org.junit.Test;

public class CreatingBasicObservableTest {

    @Test
    public void testBasicObservable() {
        Observable<Integer> observable =
                Observable.create(e -> {
                    e.onNext(40);
                    e.onNext(50);
                    e.onComplete();
                });

        //1000s of lines code
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("On Next:" + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("On Error:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("On Complete!");
            }
        });
    }

    @Test
    public void testBasicObservableListeningWithFunctions() {
        Observable<Integer> observable =
                Observable.create(e -> {
                    e.onNext(40);
                    e.onNext(50);
                    e.onError(new RuntimeException("Another Q"));
                    e.onNext(70);
                    e.onComplete();
                });

        observable.subscribe(
                x -> {
                    System.out.println("onNext = " + x);

//                    if (x == 50) {
//                        throw new RuntimeException("Nooo");
//                    } else {
//                        System.out.println("x = " + x);
//                    }
                },
                Throwable::printStackTrace,
                () -> System.out.println("On Completed!"));
    }


    @Test
    public void testBasicObservableIfWeDontListenToErrors() {
        Observable<Integer> observable =
                Observable.create(e -> {
                    e.onNext(40);
                    e.onNext(50);
                    e.onError(new RuntimeException("Runtime Exception"));
                    e.onNext(70);
                    e.onComplete();
                });

        observable.subscribe(integer -> System.out.println("integer = " + integer));
    }

    @Test
    public void testUsingJust() {
        Observable.just(40, 50, 70)
                  .subscribe(System.out::println,
                          Throwable::printStackTrace,
                          () -> System.out.println("On Completed"));
    }


    
}




