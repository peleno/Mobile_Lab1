package com.example.mobile_lab1.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_lab1.R
import com.example.mobile_lab1.presentation.viewmodel.SignUpViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {

    private var signUpButton: Button? = null

    private var nameTextInputLayout: TextInputLayout? = null
    private var nameTextView: TextInputEditText? = null

    private var emailTextInputLayout: TextInputLayout? = null
    private var emailTextView: TextInputEditText? = null

    private var passwordTextInputLayout: TextInputLayout? = null
    private var passwordTextView: TextInputEditText? = null

    private var confirmPasswordInputLayout: TextInputLayout? = null
    private var confirmPasswordTextView: TextInputEditText? = null

    private var signUpToolbar: Toolbar? = null

    private var viewModel: SignUpViewModel? = null

    private lateinit var attachedContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        attachedContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeToolbar(view)
        initializeViews(view)
        initializeViewModel()

        signUpButton?.setOnClickListener {
            clearInputFieldsError()
            try {
                viewModel?.signUp(
                    nameTextView?.text.toString(), emailTextView?.text.toString(),
                    passwordTextView?.text.toString(), confirmPasswordTextView?.text.toString()
                )
//                clearInputFieldsText()
//                displaySuccess()
//                startActivity(Intent(attachedContext, WelcomeActivity::class.java))
            } catch (e: IllegalStateException) {
                Timber.w(e.message!!)
            }
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        viewModel?.nameError?.observe(viewLifecycleOwner) { error ->
            nameTextInputLayout?.error = error
        }

        viewModel?.emailError?.observe(viewLifecycleOwner) { error ->
            emailTextInputLayout?.error = error
        }

        viewModel?.passwordError?.observe(viewLifecycleOwner) { error ->
            passwordTextInputLayout?.error = error
        }

        viewModel?.confirmPasswordError?.observe(viewLifecycleOwner) { error ->
            confirmPasswordInputLayout?.error = error
        }

        viewModel?.user?.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                clearInputFieldsText()
                displaySuccess()
                startActivity(Intent(attachedContext, WelcomeActivity::class.java))
            } else {
                Toast.makeText(
                    attachedContext, "Failed create this user",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SignUpFragment.
         */
        @JvmStatic
        fun newInstance() =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun displaySuccess() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(attachedContext)
        builder.setTitle("Success")
        builder.setMessage("You have successfully signed up!")
        builder.show()
    }

    private fun initializeToolbar(view: View) {
        signUpToolbar = view.findViewById(R.id.sign_up_toolbar)
        signUpToolbar?.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        signUpToolbar?.setNavigationOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(
                R.id.welcome_container,
                MainFragment.newInstance()
            )?.commit()
        }
    }

    private fun initializeViews(view: View) {
        signUpButton = view.findViewById(R.id.sign_up_button)

        nameTextInputLayout = view.findViewById(R.id.name_layout)
        nameTextView = view.findViewById(R.id.name_input_edit_text)

        emailTextInputLayout = view.findViewById(R.id.email_layout)
        emailTextView = view.findViewById(R.id.email_input_edit_text)

        passwordTextInputLayout = view.findViewById(R.id.password_layout)
        passwordTextView = view.findViewById(R.id.password_input_edit_text)

        confirmPasswordInputLayout = view.findViewById(R.id.confirm_password_layout)
        confirmPasswordTextView = view.findViewById(R.id.confirm_password_input_edit_text)
    }

    private fun clearInputFieldsError() {
        nameTextInputLayout?.error = null
        emailTextInputLayout?.error = null
        passwordTextInputLayout?.error = null
        confirmPasswordInputLayout?.error = null
    }

    private fun clearInputFieldsText() {
        nameTextView?.text?.clear()
        emailTextView?.text?.clear()
        passwordTextView?.text?.clear()
        confirmPasswordTextView?.text?.clear()
    }
}
