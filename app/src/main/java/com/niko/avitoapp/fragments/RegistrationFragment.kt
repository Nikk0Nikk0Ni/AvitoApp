package com.niko.avitoapp.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.niko.avitoapp.R
import com.niko.avitoapp.databinding.FragmentRegistrationBinding
import com.niko.avitoapp.viewModels.RegisterViewModel
import com.niko.avitoapp.viewModelsFactory.FakeApiViewModelFactory
import di.FakeApiApplication
import javax.inject.Inject

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding
        get() = _binding ?: throw RuntimeException(getString(R.string.fragment_registration_null))

    private val component by lazy {
        (requireActivity().application as FakeApiApplication).component
    }

    @Inject
    lateinit var viewModelFactory: FakeApiViewModelFactory

    private val registrationViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonRegister()
        initLogInTV()
        observeError()
        observeLoading()
        resetError()
        observeCorrectRegistration()
    }

    private fun observeLoading() {
        registrationViewModel.loading.observe(viewLifecycleOwner){
            if(it)
                binding.regProgressBar.visibility = View.VISIBLE
            else
                binding.regProgressBar.visibility = View.GONE
        }
    }

    private fun initLogInTV() {
        binding.tvGoToLogin.setOnClickListener {
            LoginFragment.navigate(this)
        }
    }

    private fun observeCorrectRegistration() {
        registrationViewModel.isSuccessfulRegistration.observe(viewLifecycleOwner) {
            if (it) {
                LoginFragment.navigate(this)
            }
        }
    }

    private fun resetError() = with(binding) {
        tieName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registrationViewModel.resetNameError()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        tieEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registrationViewModel.resetEmailError()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        tiePassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registrationViewModel.resetPasswordError()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        tieConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registrationViewModel.resetPasswordError()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        btnRetry.setOnClickListener{
            registrationViewModel.resetIsError()
            tilName.error = null
            tilEmail.error = null
            tilPassword.error = null
            tilConfirmPassword.error = null
            layoutError.visibility = View.GONE
        }
    }

    private fun observeError() = with(registrationViewModel) {
        isEmptyName.observe(viewLifecycleOwner) {
            if (it)
                binding.tilName.error = getString(R.string.mustntEmptyField)
            else
                binding.tilName.error = null
        }
        isEmptyEmail.observe(viewLifecycleOwner) {
            if (it)
                binding.tilEmail.error = getString(R.string.mustntEmptyField)
            else
                binding.tilEmail.error = null
        }
        isIncorrectEmail.observe(viewLifecycleOwner) {
            if (it)
                binding.tilEmail.error = getString(R.string.incorrectEmail)
            else
                binding.tilEmail.error = null
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
        isntSamePassword.observe(viewLifecycleOwner) {
            if (it) {
                binding.tilPassword.error = getString(R.string.isntSamePassword)
                binding.tilConfirmPassword.error = getString(R.string.isntSamePassword)
            } else {
                binding.tilPassword.error = null
                binding.tilConfirmPassword.error = null
            }
        }
        isEmptyCPassword.observe(viewLifecycleOwner) {
            if (it)
                binding.tilConfirmPassword.error = getString(R.string.mustntEmptyField)
            else
                binding.tilConfirmPassword.error = null
        }
        isError.observe(viewLifecycleOwner){
            if (it) {
                Toast.makeText(requireContext(),getString(R.string.registration_error),Toast.LENGTH_SHORT).show()
                binding.layoutError.visibility = View.VISIBLE
            }
        }
    }

    private fun initButtonRegister() = with(binding) {
        btnRegistration.setOnClickListener {
            val name = tieName.text.toString()
            val email = tieEmail.text.toString()
            val password = tiePassword.text.toString()
            val cpassword = tieConfirmPassword.text.toString()
            registrationViewModel.register(name, email, password, cpassword)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun navigate(fragment: Fragment) {
            fragment.findNavController().navigate(R.id.registrationFragment, null, navOptions {
                anim {
                    enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                    popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                    exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                }
                popUpTo(R.id.registrationFragment){
                    inclusive = true
                }
            })
        }
    }
}