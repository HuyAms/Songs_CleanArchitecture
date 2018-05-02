package com.example.huytrinh.songs.presentation.screen.albumscreen

import com.example.huytrinh.songs.domain.usecase.album.LoadAlbumsUseCase
import com.example.huytrinh.songs.domain.usecase.user.ClearLoginDataUseCase
import com.example.huytrinh.songs.presentation.util.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class AlbumPresenter(private val view: AlbumContract.View,
                     private val loadAlbumsUseCase: LoadAlbumsUseCase,
                     private val clearLoginDataUseCase: ClearLoginDataUseCase,
                     private val schedulerProvider: SchedulerProvider): AlbumContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun loadAlbums() {
        view.showLoading()
        val disposable = loadAlbumsUseCase.execute()
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getUIScheduler())
                .subscribe({albumList ->
                    view.hideLoading()
                    view.onLoadAlbumSuccess(albumList)
                }, { error ->
                    view.hideLoading()
                    view.onError(error.localizedMessage)
                })
        compositeDisposable.add(disposable)
    }

    override fun performLogOut() {
        view.showLoading()
        val disposable = clearLoginDataUseCase.execute()
                .subscribeOn(schedulerProvider.getIOScheduler())
                .observeOn(schedulerProvider.getUIScheduler())
                .subscribe({
                    view.hideLoading()
                    view.onLogOutSuccess()
                }, {error ->
                    view.hideLoading()
                    view.onError(error.localizedMessage)
                })
        compositeDisposable.add(disposable)
    }

    override fun attach() {
        loadAlbums()
    }

    override fun detach() {
        compositeDisposable.clear()
    }
}