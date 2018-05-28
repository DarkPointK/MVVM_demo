package com.dpk.mvvm_iv.model

import io.reactivex.FlowableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


object SwitchSchedulers {
    fun unsubscribe(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose()
        }
    }

    fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applyMaybeSchedulers(): MaybeTransformer<T, T> {
        return MaybeTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun <T> applySingleSchedulers(): SingleTransformer<T, T> {
        return SingleTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }

    }

    fun <T> applyFlowableSchedulers(): FlowableTransformer<T, T> {
        return FlowableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }


    /**
     * 切换到Main 线程
     */
    fun <T> toMainThread(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }

    }


    /**
     * 还是在IO线程
     */
    fun <T> toIoThread(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
        }
    }
}
//    /**
//     * 有进度Schedulers
//     */
//    public static <T> ObservableTransformer<T, T> applySchedulers(@NonNull final Dialog dialog) {
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
//                return upstream
//                        .delay(1, TimeUnit.SECONDS)
//                        .subscribeOn(Schedulers.io())
//                        .doOnSubscribe(new Consumer<Disposable>() {
//                            @Override
//                            public void accept(@NonNull final Disposable disposable) throws Exception {
//                                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                                    @Override
//                                    public void onCancel(DialogInterface dialog) {
//                                        disposable.dispose();
//                                    }
//                                });
//                                dialog.show();
//                            }
//                        })
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnTerminate(new Action() {
//                            @Override
//                            public void run() throws Exception {
//                                dialog.dismiss();
//                            }
//                        });
//            }
//        };
//    }

