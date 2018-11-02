package com.example.hungnb.basicroomrxjavainkotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.hungnb.basicroomrxjavainkotlin.InjectionApp
import com.example.hungnb.basicroomrxjavainkotlin.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_input_name.*

class InputNameActivity : AppCompatActivity() {
    companion object {
        private val TAG = InputNameActivity::class.java.simpleName
    }

    private lateinit var viewmodelFactory: ViewModelFactory
    private lateinit var viewModel: UserViewModel
    private val disposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_name)
        viewmodelFactory = InjectionApp.provideViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewmodelFactory).get(UserViewModel::class.java)
        update_user_button.setOnClickListener { updateUSer() }
    }

    private fun updateUSer() {
        val userName = user_name_input.text.toString()
        update_user_button.isEnabled = false
        disposable.add(viewModel.updateUserName(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                update_user_button.isEnabled = true
            }, { error ->
                Log.e(TAG, "Unable to update username", error)
            })
        )
    }

    override fun onStart() {
        super.onStart()
        disposable.add(
            viewModel.userName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.user_name.text = it
                }, { error ->
                    Log.e(TAG, "Unable to get userName", error)
                })
        )
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}
