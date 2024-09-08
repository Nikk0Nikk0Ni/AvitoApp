package com.niko.avitoapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.niko.avitoapp.R
import data.repository.FakeShopApiRepositoryImpl
import domain.usecases.LogInUser
import domain.usecases.RegisterUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    private val repositoryImpl = FakeShopApiRepositoryImpl()
    private val logIn = LogInUser(repositoryImpl)
    private val reg = RegisterUser(repositoryImpl)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            reg("niko","nickokar@yandex.ru","1234567qwerty","1234567qwerty"){}
            val a =logIn("nickokar@yandex.ru","1234567qwerty")
            Log.e("AUF","$a")
        }
    }

}