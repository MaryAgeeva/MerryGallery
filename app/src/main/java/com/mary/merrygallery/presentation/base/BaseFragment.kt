package com.mary.merrygallery.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mary.merrygallery.R
import com.mary.merrygallery.utils.color
import com.mary.mvi_core.MviIntent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

abstract class BaseFragment<I: MviIntent> : Fragment() {

    @LayoutRes
    protected abstract fun getViewResource(): Int

    protected val intents = PublishSubject.create<I>()

    protected var disposables: CompositeDisposable = CompositeDisposable()

    protected abstract fun injectDependencies()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getViewResource(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    override fun onResume() {
        super.onResume()
        disposables = CompositeDisposable()
    }

    override fun onPause() {
        if(!disposables.isDisposed)
            disposables.dispose()
        super.onPause()
    }

    protected abstract fun processInteractions()

    protected fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showSnack(parent: View, message: String) {
        Snackbar.make(parent, message, Snackbar.LENGTH_INDEFINITE).apply {
            setAction("OK") { dismiss() }
            .setActionTextColor(activity?.color(R.color.colorAccent) ?: 0)
            .show()
        }
    }
}
