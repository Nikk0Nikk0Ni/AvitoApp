package com.niko.avitoapp.fragments

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.niko.avitoapp.R
import com.niko.avitoapp.databinding.FragmentLoginBinding
import com.niko.avitoapp.viewModels.LogInViewModel
import com.niko.avitoapp.viewModelsFactory.FakeApiViewModelFactory
import data.network.RetrofitClient
import data.repository.FakeShopApiRepositoryImpl
import di.FakeApiApplication
import domain.usecases.GetProductList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginFragment : Fragment() {
    private val component by lazy {
        (requireActivity().application as FakeApiApplication).component
    }

    @Inject
    lateinit var viewModelFactory: FakeApiViewModelFactory
    private val logInViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LogInViewModel::class.java]
    }
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException(getString(R.string.fragment_login_null))

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtnLogin()
        initRegistrationTv()
        observeLoading()
        observeError()
        resetError()
        observeCorrectLogin()
    }


    private fun observeLoading() {
        logInViewModel.loading.observe(viewLifecycleOwner) {
            if (it)
                binding.loginProgressBar.visibility = View.VISIBLE
            else
                binding.loginProgressBar.visibility = View.GONE
        }
    }

    private fun initRegistrationTv() {
        binding.tvRegisteration.setOnClickListener {
            RegistrationFragment.navigate(this)
        }
    }

    private fun observeCorrectLogin() {
        logInViewModel.userFound.observe(viewLifecycleOwner) {
            if (it) {
                ProductListFragment.navigate(this)
            }

        }
    }

    private fun resetError() = with(binding) {
        tieLogin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                logInViewModel.resetEmailError()
                binding.tilLogin.error = null
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        tiePassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                logInViewModel.resetPasswordError()
                binding.tilPassword.error = null
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun observeError() = with(logInViewModel) {
        isEmptyEmail.observe(viewLifecycleOwner) {
            if (it)
                binding.tilLogin.error = getString(R.string.mustntEmptyField)
            else
                binding.tilLogin.error = null
        }
        isIncorrectEmail.observe(viewLifecycleOwner) {
            if (it)
                binding.tilLogin.error = getString(R.string.incorrectEmail)
            else
                binding.tilLogin.error = null
        }
        isEmptyPassword.observe(viewLifecycleOwner) {
            if (it)
                binding.tilPassword.error = getString(R.string.mustntEmptyField)
            else
                binding.tilPassword.error = null
        }
        isIncorrectPassword.observe(viewLifecycleOwner) {
            if (it)
                binding.tilPassword.error = getString(R.string.incorrectPassword)
            else
                binding.tilPassword.error = null
        }
        userFound.observe(viewLifecycleOwner) {
            if (!it) {
                binding.tilLogin.error = getString(R.string.userNotFound)
                binding.tilPassword.error = getString(R.string.userNotFound)
            }
        }
    }

    private fun initBtnLogin() {
        with(binding) {
            btnLogin.setOnClickListener {
                val email = tieLogin.text.toString()
                val password = tiePassword.text.toString()
                logInViewModel.logInUser(email, password)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun navigate(fragment: Fragment) {
            fragment.findNavController().navigate(R.id.loginFragment, null, navOptions {
                anim {
                    enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                    popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                }
                popUpTo(R.id.loginFragment) {
                    inclusive = true
                }
            })
        }
    }

}