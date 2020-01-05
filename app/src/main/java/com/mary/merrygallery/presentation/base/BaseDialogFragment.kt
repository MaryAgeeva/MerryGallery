package com.mary.merrygallery.presentation.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseDialogFragment : DialogFragment() {

    protected abstract val viewResource: Int

    protected val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(viewResource, container, false)
    }

    override fun onDismiss(dialog: DialogInterface) {
        if(!disposables.isDisposed)
            disposables.dispose()
        super.onDismiss(dialog)
    }

    protected fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}